package org.example.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SeleniumBaseTest {
    protected WebDriver driver;
    // test server
    // staging server -> load test, smoke
    // production server

    public void driverWait(long milisec)  {
        try {
            WebDriver.Options manager =  this.driver.manage();
            synchronized (manager) {
                manager.wait(milisec);
            }
        }catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    @BeforeAll()
    public static void setupAll() {
        // Example Before All
        // 1. seeding the db to testing user login
        // 2. loading environment variable or config files
        // 3. want to verify that test env
        System.out.println("Setup before all");
    }

    @BeforeEach
    public void setup() {
        // Example of Before Each
        // 1.creating the driver (Selenium)

        System.out.println("Setup before each");
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        // Example of After Each
        // 1. quit the driver (Selenium)
        System.out.println("TearDown after each test!");
        if(this.driver != null) {
            this.driver.quit();
        }

    }

    @AfterAll
    public static void tearDownAll() {
        // Example of After All
        // 1. cleaning up test db
        // 2. reset the env variables
        // 3. notify people
        System.out.println("TearDownAll after all tests!");
    }
}