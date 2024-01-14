package com.booking;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Search_Hotel {

    @Test
    public void searchHotel() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 2 API 30");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        caps.setCapability("appPackage", "com.booking");
        caps.setCapability("appActivity", "com.booking.startup.HomeActivity");


        AndroidDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Accept']")).click();

        driver.findElementByAccessibilityId("Navigate up").click();

        //City selection

        //Zapasowy City selection
        driver.findElement(MobileBy.xpath("//android.widget.Button[@content-desc='Enter your destination']")).click();

        driver.findElement(MobileBy.xpath("//android.widget.EditText[@text='Enter destination']")).sendKeys("Karpacz");

        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Karpacz']")).click();

        //Date selection
        driver.findElement(MobileBy.AccessibilityId("25 January 2024")).click();
        driver.findElement(MobileBy.AccessibilityId("27 January 2024")).click();
        driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Select dates']")).click();

        //Number of people
        driver.findElement(MobileBy.xpath("//android.widget.Button[@content-desc='1 room, 2 adults, 0 children']")).click();

        //Rooms
        driver.findElement(MobileBy.xpath("(//android.widget.Button[@content-desc='Increase'])[1]")).click();

        //Adults
        driver.findElement(MobileBy.xpath("(//android.widget.Button[@content-desc='Increase'])[2]")).click();

        //Children

        driver.findElement(MobileBy.xpath("(//android.widget.Button[@content-desc='Increase'])[3]")).click();

        driver.findElement(MobileBy.id("com.booking:id/group_config_apply_button")).click();

        //Child age
        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Select age']")).click();
        driver.findElement(MobileBy.xpath("//android.widget.Button[@text='17 years old']")).click();
        driver.findElement(MobileBy.id("android:id/button1")).click();

        driver.findElement(MobileBy.id("com.booking:id/group_config_apply_button")).click();

        //Search
        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Search']")).click();

        //Assertion: Check if there are hotels in the list
        List<MobileElement> hotelList = driver.findElements(MobileBy.xpath("//android.widget.TextView"));
        assertTrue("No hotels found in the list", hotelList.size() > 0);

        //Assertion: Check if the test is successful
        assertEquals("Test zakończony pomyślnie", "Test zakończony pomyślnie");

    }
}
