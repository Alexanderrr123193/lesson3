import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

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
                .setDateOfBirth("27", "December", "1992")
                .userSubjectInput("English")
                .selectHobby("Reading")
                .uploadPicture("src/test/resources/picture.png")
                .setAddress("SomeText")
                .selectState("NCR")
                .selectCity("Noida")
                .submitForm();

        registrationPage.checkModalTitle("Thanks for submitting the form")
                .checkResult("Student Name", "Semen Petrovich")
                .checkResult("Student Email", "pochta@mail.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "1234567890")
                .checkResult("Date of Birth", "27 December,1992")
                .checkResult("Subjects", "English")
                .checkResult("Hobbies", "Reading")
                .checkResult("Picture", "picture.png")
                .checkResult("Address", "SomeText")
                .checkResult("State and City", "NCR Noida");
    }
    @Test
    void negative(){
        registrationPage.openPage()
                .submitForm();

        registrationPage.checkModalTitleNotVisible("Thanks for submitting the form");


    }
    @Test
    void minimal(){
        registrationPage.openPage()
                .setFirstName("Semen")
                .setLastName("Petrovich")
                .selectMaleGender()
                .setUserNumber("1234567890")
                .submitForm();

        registrationPage.checkModalTitle("Thanks for submitting the form")
                .checkResult("Student Name", "Semen Petrovich")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "1234567890");



    }
}
