package com.booking;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchHotel {

    @Test
    public void searchHotel() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.booking.com/");
        driver.manage().window().maximize();

        // Accept Cookies
        driver.findElement(By.id("onetrust-button-group")).click();

        // Enter city
        driver.findElement(By.className("eb46370fe1")).sendKeys("Karpacz");

        // Enter date
        driver.findElement(By.xpath("//*[@id='indexsearch']/div[2]/div/form/div[1]/div[2]/div/div[1]/button[1]")).click();
        driver.findElement(By.xpath("//*[@id='calendar-searchboxdatepicker']/div/div[1]/div/div[1]/table/tbody/tr[4]/td[5]/span")).click();
        driver.findElement(By.xpath("//*[@id='calendar-searchboxdatepicker']/div/div[1]/div/div[1]/table/tbody/tr[4]/td[7]/span")).click();

        // Enter people
        driver.findElement(By.xpath("//*[@id='indexsearch']/div[2]/div/form/div[1]/div[3]/div/button")).click();
        driver.findElement(By.xpath("//*[@id=':rf:']/div/div[1]/div[2]/button[2]/span/span")).click();
        driver.findElement(By.xpath("//*[@id=':rf:']/div/div[2]/div[2]/button[2]")).click();

        // Potwierdź wiek dziecka
        WebElement parentDiv = driver.findElement(By.className("cabad3c686"));
        WebElement dropdown = parentDiv.findElement(By.className("ebf4591c8e"));
        Select select = new Select(dropdown);
        select.selectByValue("17");

        // Rooms
        driver.findElement(By.xpath("//*[@id=':rf:']/div/div[5]/div[2]/button[2]")).click();
        driver.findElement(By.xpath("//*[@id=':rf:']/button")).click();

        // Search
        driver.findElement(By.xpath("//*[@id='indexsearch']/div[2]/div/form/div[1]/div[4]/button")).click();

        // Zamknij wyskakujące okno
        driver.findElement(By.cssSelector("button[aria-label='Zamknij okno logowania.']")).click();

        // Assertion - Check if the specific element is present and contains the expected text
        WebElement updatedResultsElement = driver.findElement(By.cssSelector("h1[aria-live='assertive'][aria-label='Zaktualizowano wyniki wyszukiwania. Karpacz: znaleziono 247 obiektów.'][class='f6431b446c d5f78961c3']"));
        Assert.assertNotNull(updatedResultsElement, "The element is not present on the page");
        Assert.assertEquals(updatedResultsElement.getText(), "Karpacz: znaleziono 247 obiektów", "Unexpected text in the element");

    }
}
