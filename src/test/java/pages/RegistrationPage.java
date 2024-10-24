package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.Calendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    public RegistrationPage openPage(){
        open("/automation-practice-form");

        return this;

    }

    private static SelenideElement firstNameInput = $("#firstName"), lastNameInput = $("#lastName"),userEmailInput = $("#userEmail"), userNumberInput = $("#userNumber");

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;

    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;

    }
    public RegistrationPage setUserEmail(String value) {
       userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPage selectMaleGender() {
        $("label[for='gender-radio-1']").click();
        return this;
    }

    public RegistrationPage setUserNumber(String value){
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year){
        $("#dateOfBirthInput").click();
        new Calendar().setDate(day, month,year);
        return this;
    }

    public RegistrationPage checkResult(String key, String value) {
        $$(".table-responsive tbody tr")
                .filterBy(text(key))
                .first()
                .shouldHave(text(value));
        return this;
    }
}