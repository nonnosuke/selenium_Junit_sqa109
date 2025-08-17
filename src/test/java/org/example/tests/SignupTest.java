package org.example.tests;

import io.qameta.allure.Description;
import org.example.base.SeleniumBaseTest;
import org.example.pages.AuthPage;
import org.example.pages.HomePage;
import org.example.utilites.WaitUtility;
import org.example.pages.DashboardPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignupTest extends SeleniumBaseTest {

    @DisplayName("SignupTest")
    @Description("Checking Signup and Login are working properly.")
    @ParameterizedTest
    @CsvSource({
            "kikuchi, Test123!, Rino",
            "tester, test123, QA",
            "alex, user1pass1, Alex",
            "samira, user2pass2, Samira",
    })

    public void testSignupAndLogin(String username, String password, String fullName) {
        HomePage homePage = new HomePage(driver);
        AuthPage authPage = new AuthPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        WaitUtility waitUtility = new WaitUtility(driver);

        homePage.open();
        homePage.clickLogin();
        waitUtility.waitForClickable(authPage.submitButton, Duration.ofSeconds(5));

        //String fullName = "Test User";
        //String username = "user" + System.currentTimeMillis();
        //String password = "password123";

        authPage.signup(fullName, username, password);
        authPage.waitForConfirmBox(Duration.ofSeconds(10));
        authPage.setCloseBtn();

        authPage.login(username, password);

        waitUtility.waitForUrl("dashboard.html",Duration.ofSeconds(5));
        String greeting = dashboardPage.getGreeting();
        assertTrue(greeting.contains(fullName), "Expected greeting to contain: " + fullName);
    }
}
