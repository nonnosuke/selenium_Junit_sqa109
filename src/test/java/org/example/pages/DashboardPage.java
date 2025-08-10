package org.example.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;

    public final By greetingMessage = By.id("greetingMessage");
    public final By logoutButton = By.id("logoutBtn");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getGreeting() {
        return driver.findElement(greetingMessage).getText();
    }

    public void clickLogout() {
        driver.findElement(logoutButton).click();
    }
}
