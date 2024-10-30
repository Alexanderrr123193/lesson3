import com.github.javafaker.Faker;
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
    RegistrationPage registrationPage = new RegistrationPage();


    @Test
    void formTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .selectGender(gender)
                .setUserNumber(userNumber)
                .setDateOfBirth("27", "December", "1992")
                .userSubjectInput(userSubject)
                .selectHobby(hobby)
                .uploadPicture("picture.png")
                .setAddress(address)
                .selectState("NCR")
                .selectCity("Noida")
                .submitForm();

        registrationPage.checkModalTitle("Thanks for submitting the form")
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", "27 December,1992")
                .checkResult("Subjects", userSubject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", "picture.png")
                .checkResult("Address", address)
                .checkResult("State and City", "NCR Noida");
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
