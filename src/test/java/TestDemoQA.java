import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.util.Map;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class TestDemoQA {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.timeout = 6000;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @Test
    void formTest() {
        open("/automation-practice-form");

        // Заполнение формы
        $("#firstName").setValue("Semen");
        $("#lastName").setValue("Petrovich");
        $("#userEmail").setValue("mblo@email.com");
        $("label[for='gender-radio-1']").click();
        $("#userNumber").setValue("1234567890");

        // Установка даты рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("1992");
        $(".react-datepicker__day--027").click();

        // Дополнительные данные
        $("#subjectsInput").setValue("E");
        $$(".subjects-auto-complete__menu-list div").findBy(text("English")).shouldBe(visible).click();
        $("label[for='hobbies-checkbox-2']").click();

        // Загрузка файла
        File file = new File("C:\\Users\\a.reshetnikov\\IdeaProjects\\lesson3\\src\\test\\resources\\picture.png");
        $("#uploadPicture").uploadFile(file);

        // Ввод адреса
        $("#currentAddress").setValue("SomeText");

        // Прокрутка страницы до элемента #state перед кликом
        $("#state").scrollIntoView(true).shouldBe(visible).click();

        $(byText("NCR")).click();

        // Клик по городу
        $("#city").shouldBe(visible).click();
        $(byText("Noida")).click();

        // Отправка формы
        $("#submit").click();

        // Проверка успешной отправки
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));

        // Проверки на странице результатов
        $$(".table-responsive tbody tr").filterBy(text("Student Name")).first().shouldHave(text("Semen Petrovich"));
        $$(".table-responsive tbody tr").filterBy(text("Student Email")).first().shouldHave(text("mblo@email.com"));
        $$(".table-responsive tbody tr").filterBy(text("Gender")).first().shouldHave(text("Male"));
        $$(".table-responsive tbody tr").filterBy(text("Mobile")).first().shouldHave(text("1234567890"));
        $$(".table-responsive tbody tr").filterBy(text("Date of Birth")).first().shouldHave(text("27 December,1992"));
        $$(".table-responsive tbody tr").filterBy(text("Subjects")).first().shouldHave(text("English"));
        $$(".table-responsive tbody tr").filterBy(text("Hobbies")).first().shouldHave(text("Reading"));
        $$(".table-responsive tbody tr").filterBy(text("Picture")).first().shouldHave(text("picture.png"));
        $$(".table-responsive tbody tr").filterBy(text("Address")).first().shouldHave(text("SomeText"));
        $$(".table-responsive tbody tr").filterBy(text("State and City")).first().shouldHave(text("NCR Noida"));
    }
}
