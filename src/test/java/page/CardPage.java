package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CardPage {
    private SelenideElement headingH2 = $("h2");

    public CardPage() {
        headingH2.shouldBe(Condition.visible);
    }
}