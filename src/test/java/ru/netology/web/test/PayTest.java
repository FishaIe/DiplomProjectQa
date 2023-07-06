package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.BothPayPage;
import ru.netology.web.page.PayChoosePage;

import static com.codeborne.selenide.Selenide.open;

public class PayTest {

    @Test
    void SuccessPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getRightAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.validPayMsg();
    }

    @Test
    void CorrectPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getCorrectAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.validPayMsg();
    }

    @Test
    void ShouldBeDeclinedPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getDeclinedCardAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidPayMsg();
    }

    @Test
    void RandomCardNumberPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongCardAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidPayMsg();
    }

    @Test
    void WrongMonthPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongMonthAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void WrongYearPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongYearAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void WrongHolderPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongHolderAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.WrongFormatMsg();
    }

    @Test
    void SuccessCreditPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getRightAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.creditPayPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.validPayMsg();
    }

    @Test
    void CorrectCreditPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getCorrectAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.validPayMsg();
    }

    @Test
    void ShouldBeDeclinedCreditPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getDeclinedCardAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidPayMsg();
    }

    @Test
    void RandomCardNumberCreditPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongCardAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidPayMsg();
    }

    @Test
    void WrongMonthCreditPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongMonthAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void WrongYearCreditPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongYearAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void WrongHolderCreditPayTest() {
        open("http://localhost:8080");
        val PayChoosePage = new PayChoosePage();
        val authInfo = DataHelper.getWrongHolderAuthInfo();
        val BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(authInfo);
        BothPayPage.WrongFormatMsg();
    }
}
