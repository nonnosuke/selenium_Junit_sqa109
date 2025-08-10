package org.example.utilites;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavascriptUtility {

    private WebDriver driver;
    public JavascriptUtility(WebDriver driver) {
       this.driver = driver;
    }

    public Object run(String statement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(statement);
    }
}
