package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.Calendar;
import pages.components.ResultTable;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private ResultTable resultTable = new ResultTable();
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userNumberInput = $("#userNumber"),
            userSubjectInput = $("#subjectsInput"),
            hobbiesCheckboxReading = $("label[for='hobbies-checkbox-2']"),
            pictureLoader = $("#uploadPicture"),
            setAddressInput = $("#currentAddress"),
            stateDropdown = $("#state"),
            cityDropdown = $("#city"),
            submitButton = $("#submit"),
            modalTitle = $(".modal-title");


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

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

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        new Calendar().setDate(day, month, year);
        return this;
    }

    public RegistrationPage userSubjectInput(String subject) {
        userSubjectInput.setValue(subject.substring(0, 1));
        $$(".subjects-auto-complete__menu-list div").findBy(text(subject)).click();
        return this;
    }

    public RegistrationPage selectHobby(String hobby) {
        hobbiesCheckboxReading.click();
        return this;
    }

    public RegistrationPage uploadPicture(String fileName) {
        pictureLoader.uploadFromClasspath(fileName);
        return this;
    }

    public RegistrationPage setAddress(String value) {
        setAddressInput.setValue(value);
        return this;
    }

    public RegistrationPage selectState(String state) {
        stateDropdown.shouldBe(visible).click();
        $(byText(state)).click();
        return this;
    }

    public RegistrationPage selectCity(String city) {
        cityDropdown.shouldBe(visible).click();
        $(byText(city)).click();
        return this;
    }

    public RegistrationPage submitForm() {
        submitButton.click();
        return this;
    }

    public RegistrationPage checkModalTitle(String title) {
        modalTitle.shouldHave(text(title));
        return this;
    }

    public RegistrationPage checkResult(String key, String value) {
        resultTable.checkResult(key, value);
        return this;
    }
    public RegistrationPage checkModalTitleNotVisible(String title) {
        modalTitle.shouldNotBe(visible);
        return this;
    }}

