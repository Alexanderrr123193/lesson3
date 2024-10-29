import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.util.Locale;

public class DemoQAPageObjectsTests extends TestBase {
    Faker faker = new Faker(new Locale("en"));

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userEmail = faker.internet().emailAddress();
    String userNumber = faker.number().digits(10);
    String address = faker.address().fullAddress();
    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void formTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .selectMaleGender()
                .setUserNumber(userNumber)
                .setDateOfBirth("27", "December", "1992")
                .userSubjectInput("English")
                .selectHobby("Reading")
                .uploadPicture("picture.png")
                .setAddress(address)
                .selectState("NCR")
                .selectCity("Noida")
                .submitForm();

        registrationPage.checkModalTitle("Thanks for submitting the form")
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", "Male")
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", "27 December,1992")
                .checkResult("Subjects", "English")
                .checkResult("Hobbies", "Reading")
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
                .selectMaleGender()
                .setUserNumber(userNumber)
                .submitForm();

        registrationPage.checkModalTitle("Thanks for submitting the form")
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", "Male")
                .checkResult("Mobile", userNumber);



    }
}
