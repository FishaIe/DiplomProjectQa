package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Locale;


public class DataHelper {
    private DataHelper() {
    }

    private static final Faker faker = new Faker(new Locale("en"));

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String holder;
        private String cvc;
    }

    public static CardInfo getRightAuthInfo() {
        return new CardInfo(getFirstCard(), getMonth(1),getYear(1),getHolder(),getCvc());
    }
    public static CardInfo getDeclinedCardAuthInfo() {
        return new CardInfo(getSecondCard(),getMonth(1),getYear(1),getHolder(),getCvc());
    }
    public static CardInfo getWrongCardAuthInfo() {
        return new CardInfo(getRandomCard(),getMonth(1),getYear(1),getHolder(),getCvc());
    }

    public static CardInfo getWrongMonthAuthInfo() {
        return new CardInfo(getRandomCard(),"13",getYear(1),getHolder(),getCvc());
    }

    public static CardInfo getWrongYearAuthInfo() {
        return new CardInfo(getRandomCard(),getMonth(1),getYear(6),getHolder(),getCvc());
    }

    public static CardInfo getWrongHolderAuthInfo() {
        return new CardInfo(getRandomCard(),getMonth(1),getYear(1),"4215125",getCvc());
    }

    public static String getFirstCard() {
        return "4444 4444 4444 4441";
    }

    public static String getSecondCard() {
        return "4444 4444 4444 4442";
    }

    public static String getRandomCard() {
        return faker.business().creditCardNumber();
    }

    public static String getCvc() {
        return faker.number().digits(3);
    }

    public static String getMonth(int months) {
        return LocalDate.now().plusMonths(months).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getYear(int years) {
        return LocalDate.now().plusYears(years).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getHolder() {
        return faker.name().fullName();
    }

    @Value
    public static class verificationPayStatus {
        String code;
    }

}