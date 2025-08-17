package org.example.tests;

import io.qameta.allure.Description;
import org.example.base.SeleniumBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class AlertsTest extends SeleniumBaseTest {

    @Test
    @DisplayName("AlertsTest")
    @Description("Checking Alerts are working properly.")
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