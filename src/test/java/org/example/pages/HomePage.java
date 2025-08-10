package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public final By loginButton = By.cssSelector("a.login-btn");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://ist109.netlify.app/index.html"); // adjust if hosted elsewhere
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
}
