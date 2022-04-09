package ru.netology.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.github.javafaker.Faker;


public class DataGenerator {
    private DataGenerator() {
    }


    static Faker faker = new Faker(new Locale("ru"));

    public static String randomName() {
        return faker.name().fullName();

    }

    public static String randomPhoneNumber() {
        return faker.number().digits(11);
    }

    public static String randomCity() {
        return faker.address().city();
    }

    public static String generateDate() {
        return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateAnotherDate() {
        return LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }
}