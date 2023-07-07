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
        val authInfo = DataHelper.getRightAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.validPayMsg();
        val actual = SQLHelper.getVerificationPayStatus();
        val expected = "APPROVED";
        Assert.assertEquals(expected,actual.getCode());
    }

    @Test
    void CorrectPayTest() {
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getCorrectAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.validPayMsg();
        val actual = SQLHelper.getVerificationPayStatus();
        val expected = "APPROVED";
        Assert.assertEquals(expected,actual.getCode());
    }

    @Test
    void ShouldBeDeclinedPayTest() {
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getDeclinedCardAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidPayMsg();
        val actual = SQLHelper.getVerificationPayStatus();
        val expected = "DECLINED";
        Assert.assertEquals(expected,actual.getCode());
    }

    @Test
    void RandomCardNumberPayTest() {
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongCardAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidPayMsg();
    }

    @Test
    void WrongMonthPayTest() {
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongMonthAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void WrongYearPayTest() {
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongYearAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void WrongHolderPayTest() {
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongHolderAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.WrongFormatMsg();
    }

    @Test
    void SuccessCreditPayTest() {
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getRightAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.creditPayPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.validPayMsg();
        val actual = SQLHelper.getVerificationCreditPayStatus();
        val expected = "APPROVED";
        Assert.assertEquals(expected,actual.getCode());
    }

    @Test
    void CorrectCreditPayTest() {
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getCorrectAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.validPayMsg();
        val actual = SQLHelper.getVerificationCreditPayStatus();
        val expected = "APPROVED";
        Assert.assertEquals(expected,actual.getCode());
    }

    @Test
    void ShouldBeDeclinedCreditPayTest() {
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getDeclinedCardAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidPayMsg();
        val actual = SQLHelper.getVerificationPayStatus();
        val expected = "DECLINED";
        Assert.assertEquals(expected,actual.getCode());
    }

    @Test
    void RandomCardNumberCreditPayTest() {
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongCardAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidPayMsg();
    }

    @Test
    void WrongMonthCreditPayTest() {
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongMonthAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void WrongYearCreditPayTest() {
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongYearAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void WrongHolderCreditPayTest() {
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongHolderAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.WrongFormatMsg();
    }
}
