import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestDemoQAPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void formTest() {
        registrationPage.openPage()
                .setFirstName("Semen")
                .setLastName("Petrovich")
                .setUserEmail("pochta@mail.com")
                .selectMaleGender()
                .setUserNumber("1234567890")
                .setDateOfBirth("027", "December", "1992");
        $("#subjectsInput").setValue("E");
        $$(".subjects-auto-complete__menu-list div").findBy(text("English")).shouldBe(visible).click();
        $("label[for='hobbies-checkbox-2']").click();
        File file = new File("C:\\Users\\a.reshetnikov\\IdeaProjects\\lesson3\\src\\test\\resources\\picture.png");
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").setValue("SomeText");
        $("#state").shouldBe(visible).click();
        $(byText("NCR")).click();
        $("#city").shouldBe(visible).click();
        $(byText("Noida")).click();
        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));

        registrationPage.checkResult("Student Name","Semen Petrovich")
                        .checkResult("Student Email","pochta@mail.com");
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
