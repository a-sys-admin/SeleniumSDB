package dev.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import dev.config.Config;
import dev.testing.UserCreationApprove;
import dev.utils.ScreenshotUtil;

public class UserCreationApproveTestclass {

    private WebDriver driver;
    private Config config;
    private ScreenshotUtil screenshotUtil;
    private UserCreationApprove userCreationApprove;

    @BeforeClass
    public void setUp() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver, Config, ScreenshotUtil, and UserCreationApprove instances
        driver = new ChromeDriver();
        config = new Config();
        screenshotUtil = new ScreenshotUtil(driver);
        userCreationApprove = new UserCreationApprove(config, screenshotUtil);

    }

    @Test
    public void testLoginAsMaster() {
    	try {
            String applicationUrl = "http://172.16.128.33:3000/"; // Your application URL
            driver.get(applicationUrl);
            userCreationApprove.loginAsMaster(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Ensure the driver is closed after tests
        }
    }
}
