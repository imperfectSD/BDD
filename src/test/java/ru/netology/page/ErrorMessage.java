package ru.netology.page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class ErrorMessage {
    public static void lowBalanceMessage (){
        $(withText("Недостаточно средств")).shouldBe(visible);
    }
}