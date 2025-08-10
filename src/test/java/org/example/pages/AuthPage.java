package org.example.pages;

import org.example.utilites.JavascriptUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthPage {
    private WebDriver driver;

    public final By usernameInput = By.id("loginUsername");
    public final By passwordInput = By.id("loginPassword");
    public final By submitButton = By.cssSelector("#loginForm button[type='submit']");
    public final By errorMessage = By.cssSelector("#loginForm p");

    public final By signupName = By.id("signupName");
    public final By signupUsername = By.id("signupUsername");
    public final By signupPassword = By.id("signupPassword");
    public final By signupBtn = By.cssSelector("#signupForm button[type='submit']");
    public final By confirmBox = By.id("formConfirm");
    public final By closeBtn = By.id("formConfirmBtn");


    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://ist109.netlify.app/auth.html");
    }

    public void login(String username, String password) {
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isLoginFormValid() {
        JavascriptUtility js = new JavascriptUtility(driver);
        return (boolean) js.run("return document.getElementById('loginForm').checkValidity();");
    }


    public void signup(String fullName, String username, String password) {
        driver.findElement(signupName).sendKeys(fullName);
        driver.findElement(signupUsername).sendKeys(username);
        driver.findElement(signupPassword).sendKeys(password);
        driver.findElement(signupBtn).click();
    }

    public void waitForConfirmBox(Duration timeout) {
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(confirmBox));
    }

    public void setCloseBtn(){
        driver.findElement(closeBtn).click();
    }

}
