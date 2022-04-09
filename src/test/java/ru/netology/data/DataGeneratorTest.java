package ru.netology.data;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;

class DataGeneratorTest {


    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void shouldRegisterClient() {
        browserSize = "1900x900";
        $("[data-test-id ='city'] input").val(DataGenerator.randomCity());
        $("[data-test-id ='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").val(DataGenerator.generateDate());
        $("[name='name']").val(DataGenerator.randomName());
        $("[name='phone']").val(DataGenerator.randomPhoneNumber());
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='success-notification']").shouldHave(text("Успешно!\n" +
                "Встреча успешно запланирована на " + DataGenerator.generateDate()));


    }

    @Test
    void shouldRegisterClientOnAnotherDate() {
        browserSize = "1900x900";
        $("[data-test-id ='city'] input").val(DataGenerator.randomCity());
        $("[data-test-id ='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").val(DataGenerator.generateDate());
        $("[name='name']").val(DataGenerator.randomName());
        $("[name='phone']").val(DataGenerator.randomPhoneNumber());
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='success-notification']").shouldHave(text("Успешно!\n" +
                "Встреча успешно запланирована на " + DataGenerator.generateDate()));
        $("[data-test-id ='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").val(DataGenerator.generateAnotherDate());
        $(".button__text").click();
        $("[data-test-id='replan-notification']").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id='replan-notification'] .button__text").click();
        $("[data-test-id='success-notification']").shouldHave(text("Успешно!\n" +
        "Встреча успешно запланирована на " + DataGenerator.generateAnotherDate()), Duration.ofSeconds(5));
    }
}