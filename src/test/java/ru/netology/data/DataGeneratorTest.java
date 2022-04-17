package ru.netology.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


class DataGeneratorTest {




        @BeforeEach
        void setUp() {
            open("http://localhost:9999/");
            $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        }


        @Test
        void shouldSuccessfulPlanMeeting() {
            var daysToAddForFirstMeeting = 4;
            var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
            $("[data-test-id='city'] input").val(DataGenerator.generateCity());
            $("[data-test-id='date'] input").sendKeys(firstMeetingDate);
            $("[data-test-id='name'] input").val(DataGenerator.generateName());
            $("[data-test-id='phone'] input").val(DataGenerator.generatePhone());
            $("[data-test-id='agreement']").click();
            $(".button").click();
            $("[data-test-id='success-notification']").shouldHave(text("Встреча успешно запланирована на "
                    + firstMeetingDate));
        }


        @Test
        void shouldSuccessfulPlanAndReplanMeeting() {
            var daysToAddForFirstMeeting = 4;
            var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
            var daysToAddForSecondMeeting = 7;
            var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
            $("[data-test-id='city'] input").val(DataGenerator.generateCity());
            $("[data-test-id='date'] input").sendKeys(firstMeetingDate);
            $("[data-test-id='name'] input").val(DataGenerator.generateName());
            $("[data-test-id='phone'] input").val(DataGenerator.generatePhone());
            $("[data-test-id='agreement']").click();
            $(".button").click();
            $("[data-test-id='success-notification']").shouldHave(text("Встреча успешно запланирована на "
                    + firstMeetingDate));
            $(".button").click();
            $("[data-test-id='replan-notification']").shouldHave(text("У вас уже запланирована встреча на другую дату." +
                    " Перепланировать?"));
            $(byText("Перепланировать")).click();
            $("[data-test-id='success-notification']").shouldHave(text("Встреча успешно запланирована на "
                    + secondMeetingDate));
        }
    }
