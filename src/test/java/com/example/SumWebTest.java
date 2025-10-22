package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class SumWebTest {

    private WebDriver driver;

   @Before
public void setUp() {
    System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver-win64");

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless=new");
    options.addArguments("--allow-file-access-from-files");

    driver = new ChromeDriver(options);
}
    @Test
    public void testSum() {
        // Load local HTML file
        String url = new File("src/test/resources/sum.html").getAbsoluteFile().toURI().toString();
        driver.get(url);

        // Locate input fields and button
        WebElement num1 = driver.findElement(By.id("num1"));
        WebElement num2 = driver.findElement(By.id("num2"));
        WebElement button = driver.findElement(By.id("addButton"));
        WebElement result = driver.findElement(By.id("result"));

        // Perform test
        num1.sendKeys("5");
        num2.sendKeys("7");
        button.click();

        // Validate result
        assertEquals("12", result.getText());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

