package com.booking;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Registration {

    @Test
    public void registration () {
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
        driver.findElement(By.id("new_password")).sendKeys("Te$t654321");
        driver.findElement(By.id("confirmed_password")).sendKeys("Te$t654321");
        driver.findElement(By.xpath("//*[@id='root']/div/div/div/div[2]/div[1]/div/div/div/div/div/div/form/button")).click();

        // Wait for the button to be present
        WebElement buttonToHold = driver.findElement(By.xpath("//button[contains(text(), 'Naciśnij i przytrzymaj')]"));

        // Use Actions to click and hold for 5 seconds
        Actions action = new Actions(driver);
        action.clickAndHold(buttonToHold).pause(5000).release().build().perform();

        // Zamknij wyskakujące okno
        driver.findElement(By.cssSelector("button[aria-label='Zamknij okno logowania.']")).click();

        //My account
        driver.findElement(By.xpath("//*[@id='b2indexPage']/div[3]/div/header/nav[1]/div[2]/div/span/button/span/div/div[2]/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=':rc:']/div/div/div/div/ul/li[1]/a/div/div[2]")).click();
        driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div/div/div/nav/div[2]/ul/div[1]/div/li/a/div/div/div/h2")).click();

        //Assertion
        String profileHeaderText = driver.findElement(By.xpath("//*[@id='e6bc8822-c51a-4c72-b6cb-8c67bea477af_content']/div/div']")).getText();
        Assert.assertEquals("Zawartość elementu nie jest taka sama jak zmienna email", email, profileHeaderText);
    }
}

