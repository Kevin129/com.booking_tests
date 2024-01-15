package com.booking;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Incorrect_Login {

    @Test
    public void incorrectLogin (){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.booking.com/");
        driver.manage().window().maximize();

        //Accept Cookies
        driver.findElement(By.id("onetrust-button-group")).click();

        //Click ,,Zaloguj się"
        driver.findElement(By.xpath("//*[@id='b2indexPage']/div[2]/div/header/nav[1]/div[2]/div/a/span")).click();

        //Enter e-mail
        driver.findElement(By.id("username")).sendKeys("Jan700@gmail.com");
        driver.findElement(By.xpath("//*[@id='root']/div/div/div/div[2]/div[1]/div/div/div/div/div/div/form/div[3]/button")).click();

        //Enter password
        driver.findElement(By.id("password")).sendKeys("Hasło123");
        driver.findElement(By.xpath("//*[@id='root']/div/div/div/div[2]/div[1]/div/div/div/div/div/div/form/div[2]/button")).click();

        //Assertion
        Assert.assertTrue(driver.findElement(By.id("password-note")).getText().equals("To hasło jest nieprawidłowe. Spróbuj ponownie lub użyj linku weryfikacyjnego."));
    }
}
