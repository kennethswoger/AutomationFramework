package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

    @Test
    public void negativeUsernameTest() throws InterruptedException {
        System.out.println("Starting loginTest");

        //  create driver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();

        //  maximize window
        driver.manage().window().maximize();


        Thread.sleep(3000);


        //  open test page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened.");


        Thread.sleep(3000);


        //  enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tom");

        //  enter password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

        //  click login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@class='radius']"));
        WebElement logingButton = driver.findElement(By.tagName("button"));
        loginButton.click();


//    verifications:
        //    new url
        String expectedUrl = "https://the-internet.herokuapp.com/login";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actually page url not same as expected");

        //    login button visible
        WebElement logButton = driver.findElement(By.xpath("//button[@type='submit']"));
        Assert.assertTrue(logButton.isDisplayed(), "Is displayed");

        //    successful negative login message
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='flash error']"));
        String expectedMessage = "Your username is invalid!";
        String actualMessage = successMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not contain expected message. \nActual Message: " + actualMessage + "\nExpected Message: " + expectedMessage);

        // Close browser
        driver.quit();

    }
}
