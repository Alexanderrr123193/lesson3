import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.Random;

import java.util.Locale;

import static utils.Random.*;

public class DemoQAPageObjectsTests extends TestBase {
    Faker faker = new Faker(new Locale("en"));

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userEmail = faker.internet().emailAddress();
    String userNumber = faker.number().digits(10);
    String address = faker.address().fullAddress();

    String gender = Random.getRandomGender();
    String userSubject = getRandomSubject();
    String hobby = getUserHobbies();
    String state = getRandomState();
    String city = getRandomCity(state);
    String randomPicture = Random.getRandomPicture();
    String[] randomDateOfBirth = Random.TestDataGenerator.getRandomDateOfBirth();
    String day = randomDateOfBirth[0];
    String month = randomDateOfBirth[1];
    String year = randomDateOfBirth[2];
    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void formTest() {
        openFormPage();
        fillInForm();
        submitForm();
        checkResults();
    }

    @Step("Открытие страницы формы")
    private void openFormPage() {
        registrationPage.openPage();
    }

    @Step("Заполнение формы с данными")
    private void fillInForm() {
        registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .selectGender(gender)
                .setUserNumber(userNumber)
                .setDateOfBirth(day, month, year)
                .userSubjectInput(userSubject)
                .selectHobby(hobby)
                .uploadPicture(randomPicture)
                .setAddress(address)
                .selectState(state)
                .selectCity(city);
    }

    @Step("Отправка формы")
    private void submitForm() {
        registrationPage.submitForm();
    }

    @Step("Проверка результатов отправки формы")
    private void checkResults() {
        registrationPage.checkModalTitle("Thanks for submitting the form")
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", day + " " + month + ", " + year)
                .checkResult("Subjects", userSubject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", randomPicture)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }

    @Test
    void negativeTest(){
        registrationPage.openPage()
                .submitForm();

        registrationPage.checkModalTitleNotVisible("Thanks for submitting the form");
    }

    @Test
    void minimalTest(){
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .selectGender(gender)
                .setUserNumber(userNumber)
                .submitForm();

        registrationPage.checkModalTitle("Thanks for submitting the form")
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber);
    }
}
