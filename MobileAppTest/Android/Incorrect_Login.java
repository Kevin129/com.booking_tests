package com.booking;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Incorrect_Login {

    @Test
    public void incorrectLogin() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 2 API 30");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        caps.setCapability("appPackage", "com.booking");
        caps.setCapability("appActivity", "com.booking.startup.HomeActivity");


        AndroidDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Accept']")).click();

        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Continue with email']")).click();

        //Login
        driver.findElement(MobileBy.xpath("//android.widget.EditText")).sendKeys("jan@gmail.com");
        driver.findElement(MobileBy.xpath("//android.widget.ScrollView/android.view.View[3]")).click();

        //Password
        driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id='com.booking:id/identity_text_input_edit_text']")).sendKeys("Haslo123");
        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Sign in']")).click();

        //Assertion
        Assert.assertTrue("Wyświetlona nieprawidłowa wiadomość o błędzie",
                driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Your password is incorrect – try again or use a verification link']")).isDisplayed());

    }
}
