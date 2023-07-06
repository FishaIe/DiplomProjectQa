package ru.netology.web.page;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;


public class PayChoosePage {

    private SelenideElement payButton = $x("//*[contains(text(), 'Купить')]");
    private SelenideElement creditPayButton = $x("//*[contains(text(), 'Купить в кредит')]");
    private SelenideElement payHeading = $x("//*[contains(text(), 'Оплата по карте')]");
    private SelenideElement creditPayHeading = $x("//*[contains(text(), 'Кредит по данным карты')]");

    public void payPage(){
        payButton.click();
        payHeading.shouldBe(visible);
    }

    public void creditPayPage(){
        creditPayButton.click();
        creditPayHeading.shouldBe(visible);
    }


}