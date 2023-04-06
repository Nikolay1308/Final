package org.ex;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
public class loginUI {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "emulator-5554");
        desiredCapabilities.setCapability("appium:appPackage", "com.shashank.platform.loginui");
        desiredCapabilities.setCapability("appium:appActivity", "com.shashank.platform.loginui.MainActivity");
        desiredCapabilities.setCapability("appium:noRest", true);
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test //Test if element is exists
    public void elementTest() {
        WebElement input = driver.findElement(By.id("com.shashank.platform.loginui:id/textView"));
        input.click();
        Assertions.assertTrue(input.isEnabled());
    }
    @Test // Test for set email address
    public void setEmailTest(){
        WebElement input = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText"));
        input.click();
        input.sendKeys("example@gmail.com");
        Assertions.assertTrue(input.isEnabled());
    }
    @Test  // Test for deleting email
    public void deleteEmail(){
        WebElement input = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText"));
        input.click();
        input.sendKeys("example@gmail.com");
        input.clear();
        Assertions.assertTrue(input.isEnabled());
    }
    @Test // Test for set password
    public void setPasswordTest(){
        WebElement input = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText"));
        input.click();
        input.sendKeys("passs123");
        input.getText();
        Assertions.assertTrue(input.isDisplayed());
    }
    @Test // Test for deleting password
    public void deletePassword(){
        WebElement input = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText"));
        input.click();
        input.sendKeys("passs123");
        input.clear();
        Assertions.assertTrue(input.isEnabled());
    }
    @Test     // This test is testing left swipe
    public void testSwipeToLeft() {
        // Find the element to swipe
        WebElement elementToSwipe = driver.findElement(By.id("com.shashank.platform.loginui:id/imageView"));

        // Get the start and end coordinates of the element
        int startX = elementToSwipe.getLocation().getX() + elementToSwipe.getSize().getWidth() - 1; // Subtract 1 to avoid rounding errors
        int startY = elementToSwipe.getLocation().getY() + elementToSwipe.getSize().getHeight() / 2; // Swipe halfway across the height
        int endX = startX - elementToSwipe.getSize().getWidth() / 2; // Swipe halfway across the width
        int endY = startY;

        // Perform the swipe action
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(startX, startY))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }

    @Test  //This test is testing right swipe
    public void testSwipeToRight() {
        // Find the element to swipe
        WebElement elementToSwipe = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView"));

        // Get the start and end coordinates of the element
        int startX = elementToSwipe.getLocation().getX();
        int startY = elementToSwipe.getLocation().getY() + elementToSwipe.getSize().getHeight() / 2; // Swipe halfway across the height
        int endX = startX + elementToSwipe.getSize().getWidth() / 2; // Swipe halfway across the width
        int endY = startY;

        // Perform the swipe action
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(startX, startY))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }

    /*@After
    public void tearDown() {
        driver.quit();
    }*/
}
