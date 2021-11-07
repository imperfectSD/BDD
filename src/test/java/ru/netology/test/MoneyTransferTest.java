package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;

import ru.netology.data.DataHelper;
import ru.netology.page.ErrorMessage;
import ru.netology.page.LoginPageV1;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @Test
    void shouldSuccessfullyTransferFromFirstCardToSecond() {
        val loginPage = open("http://localhost:9999", LoginPageV1.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val firstCardBalanceBeforeTransfer = dashboardPage.getFirstCardBalance();
        val secondCardBalanceBeforeTransfer = dashboardPage.getSecondCardBalance();
        val moneyTransfer = dashboardPage.secondCard();
        int amount = 500;
        moneyTransfer.moneyTransfer(amount, DataHelper.CardNumber.getFirstCard());
        val firstCardBalanceAfterTransfer = dashboardPage.getFirstCardBalance();
        val secondCardBalanceAfterTransfer = dashboardPage.getSecondCardBalance();
        assertEquals((firstCardBalanceBeforeTransfer - amount), firstCardBalanceAfterTransfer);
        assertEquals((secondCardBalanceBeforeTransfer + amount), secondCardBalanceAfterTransfer);
    }

    @Test
    void shouldSuccessfullyTransferFromSecondCardToFirst() {
        val loginPage = open("http://localhost:9999", LoginPageV1.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val firstCardBalanceBeforeTransfer = dashboardPage.getFirstCardBalance();
        val secondCardBalanceBeforeTransfer = dashboardPage.getSecondCardBalance();
        val moneyTransfer = dashboardPage.firstCard();
        int amount = 500;
        moneyTransfer.moneyTransfer(amount, DataHelper.CardNumber.getSecondCard());
        val firstCardBalanceAfterTransfer = dashboardPage.getFirstCardBalance();
        val secondCardBalanceAfterTransfer = dashboardPage.getSecondCardBalance();
        assertEquals((firstCardBalanceBeforeTransfer + amount), firstCardBalanceAfterTransfer);
        assertEquals((secondCardBalanceBeforeTransfer - amount), secondCardBalanceAfterTransfer);
    }

    @Test
    void shouldTransferMoneyMaxAmountPresented() {
        val loginPage = open("http://localhost:9999", LoginPageV1.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val firstCardBalanceBeforeTransfer = dashboardPage.getFirstCardBalance();
        val secondCardBalanceBeforeTransfer = dashboardPage.getSecondCardBalance();
        val moneyTransfer = dashboardPage.secondCard();
        int amount = firstCardBalanceBeforeTransfer;
        moneyTransfer.moneyTransfer(amount, DataHelper.CardNumber.getFirstCard());
        val firstCardBalanceAfterTransfer = dashboardPage.getFirstCardBalance();
        val secondCardBalanceAfterTransfer = dashboardPage.getSecondCardBalance();
        assertEquals((firstCardBalanceBeforeTransfer - amount), firstCardBalanceAfterTransfer);
        assertEquals((secondCardBalanceBeforeTransfer + amount), secondCardBalanceAfterTransfer);
    }

    @Test
    void shouldShowErrorMessageWhenOverdraft() {
        val loginPage = open("http://localhost:9999", LoginPageV1.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val firstCardBalanceBeforeTransfer = dashboardPage.getFirstCardBalance();
        val secondCardBalanceBeforeTransfer = dashboardPage.getSecondCardBalance();
        val moneyTransfer = dashboardPage.firstCard();
        int amount = 25000;
        moneyTransfer.moneyTransfer(amount, DataHelper.CardNumber.getSecondCard());
        ErrorMessage.lowBalanceMessage();
    }
}
