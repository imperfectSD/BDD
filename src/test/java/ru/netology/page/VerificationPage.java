package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[type='text']");
    private SelenideElement verifyButton = $("[type='button']");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
