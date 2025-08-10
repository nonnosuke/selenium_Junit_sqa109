package org.example.tests;

import org.example.base.SeleniumBaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

public class AlertsTest extends SeleniumBaseTest {

    @Test
    public void testShowAlert() {
        driver.get("https://ist109.netlify.app/");
        driver.findElement(By.linkText("Alerts & Waits")).click();

        driver.findElement(By.xpath( "/html/body/main/button[1]")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert Text: " + alert.getText());
        alert.accept();
    }

    @Test
    public void testShowPrompt () {
        driver.get("https://ist109.netlify.app/");
        driver.findElement(By.linkText("Alerts & Waits")).click();

        WebElement promptBtn = driver.findElement(By.xpath("/html/body/main/button[3]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", promptBtn);

        Alert promptAlert = driver.switchTo().alert();
        System.out.println("Prompt Text: " + promptAlert.getText());
        promptAlert.accept();
    }
}