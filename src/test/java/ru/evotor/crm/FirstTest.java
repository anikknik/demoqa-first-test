package ru.evotor.crm;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;


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

    RegistrationFormPage RegistrationFormPage = new RegistrationFormPage();
    Faker faker = new Faker();

    // Значения
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            gender = "Other",
            day = "01",
            month = "April",
            year = "2002",
            phone = faker.phoneNumber().subscriberNumber(10),
            subjects = "Math",
            hobby = "Sports",
            img = "img/testimg.png",
            address = faker.address().streetAddress(),
            state = "Uttar Pradesh",
            city = "Merrut";


    @Test
    void actions() {
    // Ввод
        RegistrationFormPage.OpenPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(phone)
                .setBirthDate(day, month, year)
                .setSubjects(subjects)
                .setUserHobbies(hobby)
                .upLoadPicture(img)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .submitClick()

    // Вывод и проверка
                .checkCompletedForm("Student Name", firstName + " " + lastName)
                .checkCompletedForm("Student Email", userEmail)
                .checkCompletedForm("Gender", gender)
                .checkCompletedForm("Mobile", phone)
                .checkCompletedForm("Date of Birth", day + " " + month + "," + year)
                .checkCompletedForm("Subjects", subjects)
                .checkCompletedForm("Hobbies", hobby)
                .checkCompletedForm("Picture", img.substring(4))
                .checkCompletedForm("Address", address)
                .checkCompletedForm("State and City", state + " " + city);
    }

}
