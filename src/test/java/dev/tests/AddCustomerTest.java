package dev.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import dev.config.Config;
import dev.testing.AddCustomer;
import dev.utils.ScreenshotUtil;

public class AddCustomerTest {

    private WebDriver driver;
    private AddCustomer addCustomer;

    @BeforeClass
    public void setup() {
        // Set up WebDriver path
    	System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver
        driver = new ChromeDriver();
        
        // Maximize browser window
        driver.manage().window().maximize();

        // Initialize Config and ScreenshotUtil classes (Assuming these classes exist)
        Config config = new Config();
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);

        // Initialize the AddCustomer class
        addCustomer = new AddCustomer(driver, config, screenshotUtil);

        // Navigate to the application login page
        driver.get("http://172.16.128.33:3000/");
    }

    @Test
    public void testAddNewCustomer() throws Exception {
        // Log in as master user
        addCustomer.loginAsMaster("master", "P@ssw0rd");

        // Click on the 'Customer' button
        addCustomer.clickCustomerButton();

        // Click on the 'New Customer' button
        addCustomer.clickNewCustomer();

        // Fill in the customer form
        addCustomer.fillCustomerForm();
        
     
    }

    @AfterClass
    public void tearDown() {
        // Wait for 1 minute before closing the browser
        try {
            System.out.println("Waiting for 1 minute before closing the browser...");
            Thread.sleep(60000); // 60000 milliseconds = 1 minute
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser after the wait
        if (driver != null) {
            driver.quit();
        }
    }
}

