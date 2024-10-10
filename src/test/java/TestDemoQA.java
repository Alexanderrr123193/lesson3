import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestDemoQA {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.timeout = 6000;
    }

    @Test
    void formTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Semen");
        $("#lastName").setValue("Petrovich");
        $("#userEmail").setValue("mblo@email.com");
        $("label[for='gender-radio-1']").click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();  // Открываем календарь
        $(".react-datepicker__month-select").selectOption("December");  // Выбор месяца
        $(".react-datepicker__year-select").selectOption("1992");      // Выбор года
        $(".react-datepicker__day--027").click();  // Выбор 10-го числа
        $("#subjectsInput").setValue("E");

                $("#submit").click();


        $("#output").$("#name").shouldHave(text("Alex"));
        $("#output").$("#email").shouldHave(text("alex@mail.ru"));
        $("#output").$("#currentAddress").shouldHave(text("Moscow"));
        $("#output").$("#permanentAddress").shouldHave(text("Rostov"));
    }
}
