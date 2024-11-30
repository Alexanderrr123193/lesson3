package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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
            hobbyCheckbox = $("#hobbiesWrapper"),
            pictureLoader = $("#uploadPicture"),
            setAddressInput = $("#currentAddress"),
            stateDropdown = $("#state"),
            cityDropdown = $("#city"),
            submitButton = $("#submit"),
            modalTitle = $(".modal-title");

    // Прокрутка до элемента
    @Step("Scroll to element {element}")
    private void scrollTo(SelenideElement element) {
        element.scrollIntoView(true);
    }

    @Step("Open the registration page")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    @Step("Set first name to {value}")
    public RegistrationPage setFirstName(String value) {
        scrollTo(firstNameInput);
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Set last name to {value}")
    public RegistrationPage setLastName(String value) {
        scrollTo(lastNameInput);
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Set user email to {value}")
    public RegistrationPage setUserEmail(String value) {
        scrollTo(userEmailInput);
        userEmailInput.setValue(value);
        return this;
    }

    @Step("Select gender {gender}")
    public RegistrationPage selectGender(String gender) {
        scrollTo($("label[for='gender-radio-1']"));
        switch (gender) {
            case "Male":
                $("label[for='gender-radio-1']").click();
                break;
            case "Female":
                $("label[for='gender-radio-2']").click();
                break;
            case "Other":
                $("label[for='gender-radio-3']").click();
                break;
            default:
                throw new IllegalArgumentException("Unknown gender: " + gender);
        }
        return this;
    }

    @Step("Set user number to {value}")
    public RegistrationPage setUserNumber(String value) {
        scrollTo(userNumberInput);
        userNumberInput.setValue(value);
        return this;
    }

    @Step("Set date of birth to {day}-{month}-{year}")
    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        scrollTo($("#dateOfBirthInput"));
        $("#dateOfBirthInput").click();
        new Calendar().setDate(day, month, year);
        return this;
    }

    @Step("Set subject to {subject}")
    public RegistrationPage userSubjectInput(String subject) {
        scrollTo(userSubjectInput);
        userSubjectInput.setValue(subject.substring(0, 1));
        $$(".subjects-auto-complete__menu-list div").findBy(text(subject)).click();
        return this;
    }

    @Step("Select hobby {hobby}")
    public RegistrationPage selectHobby(String hobby) {
        scrollTo(hobbyCheckbox);
        $("#hobbiesWrapper").$(byText(hobby)).click();
        return this;
    }

    @Step("Upload picture with filename {fileName}")
    public RegistrationPage uploadPicture(String fileName) {
        scrollTo(pictureLoader);
        pictureLoader.uploadFromClasspath(fileName);
        return this;
    }

    @Step("Set address to {value}")
    public RegistrationPage setAddress(String value) {
        scrollTo(setAddressInput);
        setAddressInput.setValue(value);
        return this;
    }

    @Step("Select state {state}")
    public RegistrationPage selectState(String state) {
        scrollTo(stateDropdown);
        stateDropdown.shouldBe(visible).click();
        $(byText(state)).click();
        return this;
    }

    @Step("Select city {city}")
    public RegistrationPage selectCity(String city) {
        scrollTo(cityDropdown);
        cityDropdown.shouldBe(visible).click();
        $(byText(city)).click();
        return this;
    }

    @Step("Submit the form")
    public RegistrationPage submitForm() {
        scrollTo(submitButton);
        submitButton.click();
        return this;
    }

    @Step("Check modal title for {title}")
    public RegistrationPage checkModalTitle(String title) {
        modalTitle.shouldHave(text(title));
        return this;
    }

    @Step("Check result for key {key} and value {value}")
    public RegistrationPage checkResult(String key, String value) {
        resultTable.checkResult(key, value);
        return this;
    }

    @Step("Ensure modal title is not visible")
    public RegistrationPage checkModalTitleNotVisible(String title) {
        modalTitle.shouldNotBe(visible);
        return this;
    }

    @Step("Close banners")
    public RegistrationPage closeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    @Step("Scroll to submit button")
    public RegistrationPage scrollToSubmitButton() {
        submitButton.scrollIntoView(true);
        return this;
    }
}