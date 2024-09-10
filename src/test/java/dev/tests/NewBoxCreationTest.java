package dev.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import dev.config.Config;
import dev.newboxtest.NewBoxCreationApprove;
import dev.utils.ScreenshotUtil;

public class NewBoxCreationTest {

    private WebDriver driver;
    private NewBoxCreationApprove newBoxCreationApprove;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        Config config = new Config();
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
        newBoxCreationApprove = new NewBoxCreationApprove(config, screenshotUtil);
    }

    @Test
    public void testNewBoxCreationApprove() {
        try {
            String applicationUrl = "http://172.16.128.33:3000/"; // Your application URL
            driver.get(applicationUrl);
            newBoxCreationApprove.loginAsMaster(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

