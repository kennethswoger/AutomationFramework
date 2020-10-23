package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

    @Test
    public void loginTest() throws InterruptedException {
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
        username.sendKeys("tomsmith");

        //  enter password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

        //  click login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@class='radius']"));
        WebElement logingButton = driver.findElement(By.tagName("button"));
        loginButton.click();


//    verifications:
        //    new url
        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actually page url not same as expected");


        //    logout button visible
        WebElement logoutButton = driver.findElement(By.xpath("//a[@href='/logout']"));

        //    successful login message
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='flash success']"));
        successMessage.isDisplayed();

        // Close browser
        driver.quit();

    }

}
