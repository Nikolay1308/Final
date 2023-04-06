package org.ex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class ecomPrjTest {
   public WebDriver driver = new ChromeDriver();

    @BeforeEach
    // משמש לאותת שיש לבצע את השיטה המוערת לפני כל שיטת @Test במחלקת הבדיקה הנוכחית.
    public void initializeSelenium() {
        //מגדיר את ה-webdriver.chrome.driver שהוא מפתח קבוע עם ערך הנתיב
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\nikol\\Downloads\\chromedriver_win32\\chromedriver.exe");// מיקום של הדרייבר
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();//פותח חלון של דפדפן על כל המסך
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    // This function will take screenshot
    public static void SnapShot(WebDriver webdriver, String fileWithPath) {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);//Convert web driver object to TakeScreenshot
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);//Call getScreenshotAs method to create image file
        File DestFile = new File(fileWithPath);//Move image file to new destination
        try {
            FileHandler.copy(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test                 // This test is take screenshot and store is inside my computer
    public void pageScreenShot() {
        driver.get("https://www.amazon.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");
        Assertions.assertEquals("https://www.amazon.com/", driver.getCurrentUrl());
        SnapShot(driver, "C:\\Users\\nikol\\Pictures\\Camera Roll\\1.png");
        js.executeScript("window.scrollBy(0,300)", "");
        Assertions.assertEquals("https://www.amazon.com/", driver.getCurrentUrl());
        SnapShot(driver, "C:\\Users\\nikol\\Pictures\\Camera Roll\\2.png");
    }

    @Test   // This test will take actions on page
    public void actions() throws InterruptedException {
        driver.get("https://www.amazon.com/?language=en_US");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"nav-cart-count-container\"]/span[2]")).click();//Get link by xpath and click it
        Assertions.assertEquals("https://www.amazon.com/gp/cart/view.html?ref_=nav_cart", driver.getCurrentUrl()); //Check that the url is as expected
        Thread.sleep(3000);

        driver.navigate().back();//Go back
        Thread.sleep(3000);
        Assertions.assertEquals("https://www.amazon.com/?language=en_US", driver.getCurrentUrl()); //Check that the url is as expected


        driver.navigate().forward();//Go forward
        Thread.sleep(3000);
        Assertions.assertEquals("https://www.amazon.com/gp/cart/view.html?ref_=nav_cart", driver.getCurrentUrl());


        driver.navigate().refresh();//Refresh the page
        Thread.sleep(3000);
        Assertions.assertEquals("https://www.amazon.com/gp/cart/view.html?ref_=nav_cart", driver.getCurrentUrl());

    }

    @Test  //This test click on checkbox
    public void checkBox() throws InterruptedException {
        driver.get("https://www.amazon.com/?language=en_US");
        WebElement input = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]/div"));
        driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]/span")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"ap_email\"]")).click();
        driver.findElement(By.id("ap_email")).sendKeys("nikolayshmal@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.id("continue")).click();
        WebElement checkbox1 = driver.findElement(By.cssSelector("#authportal-main-section > div:nth-child(2) > div > div > div > form > div > div:nth-child(7) > div > div > label > div > label > input[type=checkbox]"));
        checkbox1.click();
        Assertions.assertTrue(checkbox1.isSelected());

    }

    @Test  // This test is close alert message
    public void alertMessage() throws InterruptedException {
        driver.get("https://www.browserstack.com/users/sign_up");
        driver.findElement(By.id("user_full_name")).sendKeys("Valentine");
        driver.findElement(By.id("user_email_login")).sendKeys("valentine123@gmail.com");
        driver.findElement(By.id("user_password")).sendKeys("123456");
        driver.findElement(By.id("user_submit")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"new\"]/aside/div/a")).click();// לא הצלחתי לסגור בעזרת אוטומציה
    }

    @Test  //This test is input text
    public void inputText() throws InterruptedException {
        driver.get("https://www.amazon.com/?language=en_US");
        Thread.sleep(2000);
        driver.findElement(By.linkText("Sign in")).click();
        Thread.sleep(2000);
        WebElement email = driver.findElement(By.id("ap_email"));
        Thread.sleep(2000);
        email.sendKeys("valentine123@gmail.com");
    }

    @Test //This test delete text
    public void deleteText() throws InterruptedException {
        driver.get("https://www.amazon.com/?language=en_US");
        Thread.sleep(2000);
        driver.findElement(By.linkText("Sign in")).click();
        Thread.sleep(2000);
        WebElement email = driver.findElement(By.id("ap_email"));
        Thread.sleep(2000);
        email.sendKeys("valentine123@gmail.com");
        Thread.sleep(2000);
        email.clear();
    }

    @Test // This test is open Dropdown menu
    public void dropDownBox(){
        driver.get("https://www.amazon.com/");
        Select num = new Select(driver.findElement(By.id("searchDropdownBox")));
        num.selectByVisibleText("Computers");
    }

    @Test // This test is testing copy/paste function
    public void copyPasteText() {
        driver.get("https://www.amazon.com/");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Gigabyte");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.CONTROL,"a");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.CONTROL,"c");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.CONTROL,"v");
        driver.findElement(By.id("nav-search-submit-button")).click();
    }
    @Test //Click on image test
    public void urlFromImage(){
        driver.get("https://www.amazon.com/");
        driver.findElement(By.cssSelector("#nav-logo-sprites")).click();
        Assertions.assertEquals("https://www.amazon.com/ref=nav_logo", driver.getCurrentUrl());
    }
    @Test //This test is testing copy paragraph from wikipedia and store it on my computer
    public void copyFromWiki(){
        driver.get("https://en.wikipedia.org/wiki/Kobe_Bryant");
        String paragraph = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[2]")).getText();
        try {
            FileWriter writer = new FileWriter("paragraph.txt");
            writer.write(paragraph);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
