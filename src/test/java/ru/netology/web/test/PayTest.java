package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
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
        open("http://localhost:8080/");
    }

    @Test
    void successPayTest() {
        var PayChoosePage = new PayChoosePage();
        var cardInfo = DataHelper.getRightCardInfo();
        var BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.validPayMsg();
        var actual = SQLHelper.getVerificationPayStatus();
        var value = System.getProperty("db.approved","APPROVED");
        var expected = value; //APPROVED
        Assert.assertEquals(expected,actual);
    }

    @Test
    void shouldBeDeclinedPayTest() {
        var PayChoosePage = new PayChoosePage();
        var cardInfo = DataHelper.getDeclinedCardInfo();
        var BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.invalidPayMsg();
        var actual = SQLHelper.getVerificationPayStatus();
        var value = System.getProperty("db.declined","DECLINED");
        var expected = value; // DECLINED
        Assert.assertEquals(expected,actual);
    }

    @Test
    void randomCardNumberPayTest() {
        var PayChoosePage = new PayChoosePage();
        var cardInfo = DataHelper.getWrongCardInfo();
        var BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.invalidPayMsg();
    }

    @Test
    void wrongMonthPayTest() {
        var PayChoosePage = new PayChoosePage();
        var cardInfo = DataHelper.getWrongMonthCardInfo();
        var BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void wrongYearPayTest() {
        var PayChoosePage = new PayChoosePage();
        var cardInfo = DataHelper.getWrongYearCardInfo();
        var BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.invalidMonthYearMsg();
    }

    @Test
    void wrongHolderPayTest() {
        var PayChoosePage = new PayChoosePage();
        var cardInfo = DataHelper.getWrongHolderCardInfo();
        var BothPayPage = new BothPayPage();
        PayChoosePage.payPage();
        BothPayPage.validPay(cardInfo);
        BothPayPage.wrongFormatMsg();
    }
}
