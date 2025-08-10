package org.example.utilites;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtility {

    private WebDriver driver;
    public WaitUtility(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForUrl(String url, Duration timeout) {
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.urlContains(url));
    }

    public void waitForClickable(By selector, Duration timeout) {
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.elementToBeClickable(selector));
    }
}
