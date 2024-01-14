package com.booking;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Registration {

    @Test
    public void registration() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 2 API 30");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        caps.setCapability("appPackage", "com.booking");
        caps.setCapability("appActivity", "com.booking.startup.HomeActivity");


        AndroidDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Accept']")).click();

        driver.findElementByAccessibilityId("Navigate up").click();

        //Click Sign in -> Sign in or create -> account, Continue with email
        driver.findElement(MobileBy.xpath("(//android.widget.ImageView[@resource-id='com.booking:id/navigation_bar_item_icon_view'])[4]")).click();
        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Sign in or create account']")).click();

        try {
            driver.findElement(MobileBy.xpath("//android.widget.ScrollView/android.view.View[4]/android.widget.Button")).click();
        } catch (Exception e) {
            // If the first button is not found, click the second one
            driver.findElement(MobileBy.xpath("//android.widget.TextView[@resource-id='com.booking:id/identity_landing_social_button_text']")).click();
        }

        //Variable email
        int randomNumber = (int) (Math.random() * 1000);
        String email = "jan" + randomNumber + "@gmail.com";

        //Enter email
        driver.findElement(MobileBy.xpath("//android.widget.EditText")).sendKeys(email);
        driver.findElement(MobileBy.xpath("//android.widget.Button")).click();


        //Enter Password
        driver.findElement(MobileBy.xpath("//android.widget.EditText[@text='Enter a password']")).sendKeys("T@est12345");

        //Confirm Password
        driver.findElement(MobileBy.xpath("//android.widget.EditText[@text='Confirm your password']")).sendKeys("T@est12345");

        //Create account and sign in
        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Create account and sign in']")).click();

        //Assertion
        //Assert.assertTrue("Wyświetlona nieprawidłowa wiadomość o błędzie", driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Welcome to Genius! You just unlocked Level 1']")).isDisplayed());
        //Got it
        //driver.findElement(MobileBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[6]/android.widget.Button")).click();

        //Assertion
        String profileHeaderText = driver.findElement(MobileBy.xpath("//android.widget.TextView[@resource-id='com.booking:id/facet_profile_header_name']")).getText();
        Assert.assertEquals("Zawartość elementu nie jest taka sama jak zmienna email", email, profileHeaderText);

    }
}
