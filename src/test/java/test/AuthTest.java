package test;

import data.DataHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.AuthPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.clearAll;

public class AuthTest {

    @AfterAll
    public static void clear() {
        clearAll();
    }

    @Test
    void successfulAuthTest() {
        var loginPage = open("http://localhost:9999", AuthPage.class);
        var authLogin = DataHelper.getAuthInfo();
        var authPassword = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authLogin, authPassword);
        var verificationCode = DataHelper.getVerificationCode();
        var cardPage = verificationPage.validVerify(verificationCode);
    }
}