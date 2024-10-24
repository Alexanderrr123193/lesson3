package pages.components;

import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.$;

public class Calendar {

    public void setDate(String day, String month, String year) {
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("1992");
        $(".react-datepicker__day--027").click();
    }
}