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
    private void scrollTo(SelenideElement element) {
        element.scrollIntoView(true);
    }

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        scrollTo(firstNameInput);  // Прокручиваем до поля ввода имени
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        scrollTo(lastNameInput);  // Прокручиваем до поля ввода фамилии
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        scrollTo(userEmailInput);  // Прокручиваем до поля ввода email
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPage selectGender(String gender) {
        scrollTo($("label[for='gender-radio-1']"));  // Прокручиваем до поля выбора пола
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

    public RegistrationPage setUserNumber(String value) {
        scrollTo(userNumberInput);  // Прокручиваем до поля ввода номера телефона
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        scrollTo($("#dateOfBirthInput"));  // Прокручиваем до поля ввода даты рождения
        $("#dateOfBirthInput").click();
        new Calendar().setDate(day, month, year);
        return this;
    }

    public RegistrationPage userSubjectInput(String subject) {
        scrollTo(userSubjectInput);  // Прокручиваем до поля ввода предмета
        userSubjectInput.setValue(subject.substring(0, 1));
        $$(".subjects-auto-complete__menu-list div").findBy(text(subject)).click();
        return this;
    }

    public RegistrationPage selectHobby(String hobby) {
        scrollTo(hobbyCheckbox);  // Прокручиваем до блока выбора хобби
        $("#hobbiesWrapper").$(byText(hobby)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String fileName) {
        scrollTo(pictureLoader);  // Прокручиваем до поля загрузки изображения
        pictureLoader.uploadFromClasspath(fileName);
        return this;
    }

    public RegistrationPage setAddress(String value) {
        scrollTo(setAddressInput);  // Прокручиваем до поля ввода адреса
        setAddressInput.setValue(value);
        return this;
    }

    public RegistrationPage selectState(String state) {
        scrollTo(stateDropdown);  // Прокручиваем до выпадающего списка для выбора штата
        stateDropdown.shouldBe(visible).click();
        $(byText(state)).click();
        return this;
    }

    public RegistrationPage selectCity(String city) {
        scrollTo(cityDropdown);  // Прокручиваем до выпадающего списка для выбора города
        cityDropdown.shouldBe(visible).click();
        $(byText(city)).click();
        return this;
    }

    public RegistrationPage submitForm() {
        scrollTo(submitButton);  // Прокручиваем до кнопки отправки формы
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
    }

    @Step("Close banners")
    public RegistrationPage closeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }
}
