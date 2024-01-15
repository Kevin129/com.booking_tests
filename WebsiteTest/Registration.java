package com.booking;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Registration {

    @Test
    public void registration (){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.booking.com/");
        driver.manage().window().maximize();

        //Accept Cookies
        driver.findElement(By.id("onetrust-button-group")).click();

        //Click ,,Zarejestruj się"
        driver.findElement(By.xpath("//*[@id='b2indexPage']/div[2]/div/header/nav[1]/div[2]/a[2]")).click();

        //Variable email
        int randomNumber = (int) (Math.random() * 1000);
        String email = "Jan" + randomNumber + "@gmail.com";

        //Enter email
        driver.findElement(By.id("username")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id='root']/div/div/div/div[2]/div[1]/div/div/div/div/div/div/form/div[3]/button")).click();

        //Enter password
        driver.findElement(By.id("new_password")).sendKeys("Te$t123456");
        driver.findElement(By.id("confirmed_password")).sendKeys("Te$t123456");
        driver.findElement(By.xpath("//*[@id='root']/div/div/div/div[2]/div[1]/div/div/div/div/div/div/form/button")).click();

        // Wait for the button to be present
        WebElement buttonToHold = driver.findElement(By.xpath("//button[contains(text(), 'Naciśnij i przytrzymaj')]"));

        // Use Actions to click and hold for 5 seconds
        Actions action = new Actions(driver);
        action.clickAndHold(buttonToHold).pause(5000).release().build().perform();

        driver.findElement(By.xpath("//*[@id='b2indexPage']/div[17]/div/div/div/div[1]/div[1]/div[2]/button/span/span")).click();

        //Assertion
    }
}
