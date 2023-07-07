package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;


public class DataHelper {
    private DataHelper() {
    }

    private static final Faker faker = new Faker(new Locale("en"));

    @Value
    public static class AuthInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String holder;
        private String cvc;
    }

    public static AuthInfo getRightAuthInfo() {
        return new AuthInfo(getFirstCard(), getMonth(),getYear(),getHolder(),getCvc());
    }

    public static AuthInfo getCorrectAuthInfo() {
        return new AuthInfo(getFirstCard(), "12","23",getHolder(),getCvc());
    }
    public static AuthInfo getDeclinedCardAuthInfo() {
        return new AuthInfo(getSecondCard(),getMonth(),getYear(),getHolder(),getCvc());
    }
    public static AuthInfo getWrongCardAuthInfo() {
        return new AuthInfo(getRandomCard(),getMonth(),getYear(),getHolder(),getCvc());
    }

    public static AuthInfo getWrongMonthAuthInfo() {
        return new AuthInfo(getRandomCard(),"30",getYear(),getHolder(),getCvc());
    }

    public static AuthInfo getWrongYearAuthInfo() {
        return new AuthInfo(getRandomCard(),getMonth(),"50",getHolder(),getCvc());
    }

    public static AuthInfo getWrongHolderAuthInfo() {
        return new AuthInfo(getRandomCard(),getMonth(),getYear(),"4215125",getCvc());
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

    public static String getMonth() {
        return "0" + String.valueOf( faker.number().numberBetween(1,9));
    }

    public static String getYear() {
        return String.valueOf(faker.number().numberBetween(24,28));
    }

    public static String getHolder() {
        return faker.name().fullName();
    }

    @Value
    public static class verificationPayStatus {
        String code;
    }
    @Value
    public static class verificationCreditPayStatus {
        String code;
    }


}