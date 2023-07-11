package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.BothPayPage;
import ru.netology.web.page.PayChoosePage;

import static com.codeborne.selenide.Selenide.open;

public class PayTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    void SuccessPayTest() {
        val PayChoosePage = new PayChoosePage();
        val cardInfo = DataHelper.getRightAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.validPayMsg();
        val actual = SQLHelper.getVerificationPayStatus();
        val expected = "APPROVED";
        Assert.assertEquals(expected,actual);
    }

    @Test
    void ShouldBeDeclinedPayTest() {
        val PayChoosePage = new PayChoosePage();
        val cardInfo = DataHelper.getDeclinedCardAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.invalidPayMsg();
        val actual = SQLHelper.getVerificationPayStatus();
        val expected = "DECLINED";
        Assert.assertEquals(expected,actual);
    }

    @Test
    void RandomCardNumberPayTest() {
        val PayChoosePage = new PayChoosePage();
        val cardInfo = DataHelper.getWrongCardAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.invalidPayMsg();
    }

    @Test
    void WrongMonthPayTest() {
        val PayChoosePage = new PayChoosePage();
        val cardInfo = DataHelper.getWrongMonthAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void WrongYearPayTest() {
        val PayChoosePage = new PayChoosePage();
        val cardInfo = DataHelper.getWrongYearAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void WrongHolderPayTest() {
        val PayChoosePage = new PayChoosePage();
        val cardInfo = DataHelper.getWrongHolderAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.wrongFormatMsg();
    }

    @Test
    void SuccessCreditPayTest() {
        val PayChoosePage = new PayChoosePage();
        val cardInfo = DataHelper.getRightAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.creditPayPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.validPayMsg();
        val actual = SQLHelper.getVerificationCreditPayStatus();
        val expected = "APPROVED";
        Assert.assertEquals(expected,actual.getCode());
    }

    @Test
    void ShouldBeDeclinedCreditPayTest() {
        val PayChoosePage = new PayChoosePage();
        val cardInfo = DataHelper.getDeclinedCardAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.invalidPayMsg();
        val actual = SQLHelper.getVerificationPayStatus();
        val expected = "DECLINED";
        Assert.assertEquals(expected,actual);
    }

    @Test
    void RandomCardNumberCreditPayTest() {
        val PayChoosePage = new PayChoosePage();
        val cardInfo = DataHelper.getWrongCardAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.invalidPayMsg();
    }

    @Test
    void WrongMonthCreditPayTest() {
        val PayChoosePage = new PayChoosePage();
        val cardInfo = DataHelper.getWrongMonthAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void WrongYearCreditPayTest() {
        val PayChoosePage = new PayChoosePage();
        val cardInfo = DataHelper.getWrongYearAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void WrongHolderCreditPayTest() {
        val PayChoosePage = new PayChoosePage();
        val cardInfo = DataHelper.getWrongHolderAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.wrongFormatMsg();
    }
}
