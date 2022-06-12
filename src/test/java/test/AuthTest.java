package test;

import data.DataHelper;
import org.junit.jupiter.api.Test;
import page.AuthPage;

import static com.codeborne.selenide.Selenide.open;

public class AuthTest {

    @Test
    void successfulAuthTest() {
        var loginPage = open("http://0.0.0.0:9999", AuthPage.class);
        var authLogin = DataHelper.getAuthInfo();
        var authPassword = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authLogin, authPassword);
        var verificationCode = DataHelper.getVerificationCode();
        var cardPage = verificationPage.validVerify(verificationCode);
    }
}