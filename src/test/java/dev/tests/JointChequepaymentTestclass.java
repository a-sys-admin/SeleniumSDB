package dev.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import dev.config.Config;
import dev.newboxtest.JointChequepayment;
import dev.utils.ScreenshotUtil;
import dev.utils.ExcelUtils;

import static org.testng.Assert.assertTrue;

public class JointChequepaymentTestclass {

    private WebDriver driver;
    private Config config;
    private ScreenshotUtil screenshotUtil;
    private ExcelUtils excelUtil;
    private JointChequepayment jointChequepayment;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();

        // Create Config, ScreenshotUtil, and ExcelUtil instances
        config = new Config();
        screenshotUtil = new ScreenshotUtil(driver);
        excelUtil = new ExcelUtils("D:\\selenium\\ExcelCostumer.xlsx");

        // Create an instance of Netbanking
        jointChequepayment = new JointChequepayment(config, screenshotUtil, excelUtil);
    }

    @Test
    public void testLoginAsMaster() {
        try {
            driver.get("http://172.16.128.33:3000/");
            jointChequepayment.loginAsMaster(driver);
            // You can add assertions here to verify expected outcomes
            assertTrue(true); // Placeholder assertion, replace with actual checks

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false, "Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

