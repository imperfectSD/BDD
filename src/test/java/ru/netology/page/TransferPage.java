package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement transferButton = $("[type='button']");

    public DashboardPage moneyTransfer (int transferAmount, DataHelper.CardNumber fromCardInfo) {
        amount.setValue(String.valueOf(transferAmount));
        from.setValue(fromCardInfo.getNumber());
        transferButton.click();
        return new DashboardPage();
    }
}
