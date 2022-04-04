package ru.evotor.crm;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FirstTest {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void actions() {
        open("/automation-practice-form");

        // Значения
        String firstName = "Vasya";
        String lastName = "Pupkin";
        String userEmail = "vasyap@mail.ru";
        String gender = "Other";
        String phone = "8800500223";
        String subjects = "Math";
        String hobby = "Sports";
        String img = "img/testimg.png";
        String Address = "Pushkina st., 10";
        String state = "Uttar Pradesh";
        String city = "Merrut";

        // Ввод
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2002");
        $(".react-datepicker__month-select").selectOption("April");
        $("[aria-label='Choose Monday, April 1st, 2002']").click();
        $("#subjectsInput").setValue(subjects).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath(img);
        $("#currentAddress").setValue(Address);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();

        // Вывод
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(firstName + " " + lastName),
                text(userEmail),
                text(gender),
                text(phone),
                text("01 April,2002"),
                text(subjects),
                text(hobby),
                text(img.substring(4)),
                text(Address),
                text(state + " " + city)
        );
        $("#closeLargeModal").click();
        $(".modal-content").shouldNotBe(visible);
    }

}
