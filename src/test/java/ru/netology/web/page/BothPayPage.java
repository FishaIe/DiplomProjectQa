package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class BothPayPage {

    private SelenideElement cardNumber = $("[maxlength=\"19\"]");
    private SelenideElement month = $$("[maxlength=\"2\"]").first();
    private SelenideElement year = $$("[maxlength=\"2\"]").last();
    private SelenideElement holder = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private SelenideElement cvc = $("[maxlength=\"3\"]");
    private SelenideElement continueButton = $x("//*[@id=\"root\"]/div/form/fieldset/div[4]/button/span/span");
    private SelenideElement successMsg = $x("//*[contains(text(), 'Успешно')]");
    private SelenideElement errorMsg = $x("//*[contains(text(), 'Ошибка')]");
    private SelenideElement errorMonthYearMsg = $x("//*[contains(text(), 'Неверно указан срок действия карты')]");
    private SelenideElement wrongFormatMsg = $x("//*[contains(text(), 'Неверный формат')]");


    public BothPayPage validPay(DataHelper.CardInfo info){
        cardNumber.setValue(info.getCardNumber());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        holder.setValue(info.getHolder());
        cvc.setValue(info.getCvc());
        continueButton.click();
        return this;
    }

    public void validPayMsg() {
        successMsg.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
    public void invalidPayMsg() {
        errorMsg.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
    public void invalidMonthYearMsg(){errorMonthYearMsg.shouldBe(Condition.visible);}
    public void wrongFormatMsg(){wrongFormatMsg.shouldBe(Condition.visible);}

}
