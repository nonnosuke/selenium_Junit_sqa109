package org.example.tests;

import org.example.base.SeleniumBaseTest;
import org.example.pages.AuthPage;
import org.example.pages.HomePage;
import org.example.utilites.WaitUtility;
import org.example.pages.DashboardPage;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignupTest extends SeleniumBaseTest {

    @Test
    public void testSignupAndLogin() {
        HomePage homePage = new HomePage(driver);
        AuthPage authPage = new AuthPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        WaitUtility waitUtility = new WaitUtility(driver);

        homePage.open();
        homePage.clickLogin();
        waitUtility.waitForClickable(authPage.submitButton, Duration.ofSeconds(5));

        String fullName = "Test User";
        String username = "user" + System.currentTimeMillis();
        String password = "password123";

        authPage.signup(fullName, username, password);
        authPage.waitForConfirmBox(Duration.ofSeconds(10));
        authPage.setCloseBtn();

        authPage.login(username, password);

        waitUtility.waitForUrl("dashboard.html",Duration.ofSeconds(5));
        String greeting = dashboardPage.getGreeting();
        assertTrue(greeting.contains(fullName), "Expected greeting to contain: " + fullName);
    }
}
