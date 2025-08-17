package org.example.tests;

import io.qameta.allure.Description;
import org.example.base.SeleniumBaseTest;
import org.example.pages.AuthPage;
import org.example.pages.DashboardPage;
import org.example.pages.HomePage;
import org.example.utilites.WaitUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends SeleniumBaseTest {

    @DisplayName("LoginTest")
    @Description("Checking Login is working properly.")
    @ParameterizedTest
    @CsvSource({
            "kikuchi, Test123!, Rino",
            "tester, test123, QA",
            "alex, user1pass1, Alex",
            "samira, user2pass2, Samira",
    })
    void testValidLogin(String username, String password, String name) {
        HomePage homePage = new HomePage(driver);
        AuthPage authPage = new AuthPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        WaitUtility waitUtility = new WaitUtility(driver);

        homePage.open();
        homePage.clickLogin();

        waitUtility.waitForClickable(authPage.submitButton, Duration.ofSeconds(5));
        authPage.login(username, password);

        waitUtility.waitForUrl("dashboard.html",Duration.ofSeconds(5));
        String greeting = dashboardPage.getGreeting();
        assertTrue(greeting.contains(name), "Expected greeting to contain: " + name);
    }


    @DisplayName("Invalid LoginTest")
    @Description("Checking Invalid Login is working properly.")
    @ParameterizedTest
    @MethodSource("invalidLoginProvider")
    void testInvalidLogin(String username, String password, String expectedError) {
        HomePage homePage = new HomePage(driver);
        AuthPage authPage = new AuthPage(driver);
        WaitUtility waitUtility = new WaitUtility(driver);

        homePage.open();
        homePage.clickLogin();
        waitUtility.waitForClickable(authPage.submitButton, Duration.ofSeconds(5));
        authPage.login(username, password);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#loginForm p")));

        String error = authPage.getErrorMessage();
        assertEquals(expectedError, error);
    }

    static Stream<Arguments> invalidLoginProvider() {
        return Stream.of(
                Arguments.of("wronguser", "wrongpass", "Incorrect credential."),
                Arguments.of("mrzg", "wrongpass", "Incorrect credential.")
        );
    }

    @DisplayName("Empty Invalid LoginTest")
    @Description("Checking Empty Invalid Login is working properly.")
    @ParameterizedTest
    @CsvSource({
            "'', Test123!, -",
            "mrzg, '', -",
    })
    void testEmptyInvalidLogin(String username, String password){
        HomePage homePage = new HomePage(driver);
        AuthPage authPage = new AuthPage(driver);
        WaitUtility waitUtility = new WaitUtility(driver);

        homePage.open();
        homePage.clickLogin();
        waitUtility.waitForClickable(authPage.submitButton, Duration.ofSeconds(5));
        authPage.login(username, password);

        if(password.isEmpty() || username.isEmpty()) {
            boolean valid = authPage.isLoginFormValid();
            assertFalse(valid, "Expected the form to be invalid for empty input");
        }

    }

}
