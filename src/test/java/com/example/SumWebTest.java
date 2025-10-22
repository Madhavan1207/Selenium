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
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class SumWebTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Headless mode for Jenkins
        options.addArguments("--allow-file-access-from-files");

        driver = new ChromeDriver(options); // Selenium Manager handles ChromeDriver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Wait for elements
    }

    @Test
    public void testSum() {
        // Load local HTML file
        File htmlFile = new File("src/test/resources/sum.html");
        String url = htmlFile.getAbsoluteFile().toURI().toString();
        driver.get(url);

        // Interact with the page
        WebElement num1 = driver.findElement(By.id("num1"));
        WebElement num2 = driver.findElement(By.id("num2"));
        WebElement button = driver.findElement(By.id("addButton"));
        WebElement result = driver.findElement(By.id("result"));

        num1.sendKeys("10");
        num2.sendKeys("20");
        button.click();

        // Print and assert result
        String output = result.getText();
        System.out.println("Sum is: " + output);
        assertEquals("30", output);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
