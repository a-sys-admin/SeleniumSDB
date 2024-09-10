package dev.newboxtest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.config.Config;
import dev.utils.ScreenshotUtil;

public class NewBoxCreationApprove {

    private Config config;
    private ScreenshotUtil screenshotUtil;

    // Constructor to initialize Config and ScreenshotUtil
    public NewBoxCreationApprove(Config config, ScreenshotUtil screenshotUtil) {
        this.config = config;
        this.screenshotUtil = screenshotUtil;
    }

    public void loginAsMaster(WebDriver driver) throws Exception {
        try {
            login(driver, config.getMasterUsername(), config.getPassword());
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("loginAsMaster_error");
            throw e; // Re-throw the exception to handle it elsewhere if needed
        }
    }

    public void loginAsDev(WebDriver driver) throws Exception {
        try {
            login(driver, config.getDevUsername(), config.getPassword());
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("loginAsDev_error");
            throw e;
        }
    }      

    private void login(WebDriver driver, String username, String password) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

            usernameField.sendKeys(username);
            passwordField.sendKeys(password);
            loginButton.click();
            System.out.println("Login successfully");

            // Check the user type and perform actions accordingly
            switch (username) {
                case "master":
                    clickAdminButtonAndAddUser(driver);
                    break;
                default:
                    System.out.println("No specific actions for user: " + username);
                    break;
            }
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("login_error");
            throw e; // Re-throw the exception to handle it elsewhere if needed
        }
    }       

    private void clickAdminButtonAndAddUser(WebDriver driver) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement adminButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='SettingsIcon']")));
            System.out.println("Clicking Admin button------------->Module-2");
            adminButton.click();

            WebElement securityButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='DisplaySettingsIcon']")));
            System.out.println("Clicking Security button---->Adm-subModule-2");
            securityButton.click();

            // Click the Authorize Users button
            WebElement SDBMasterButton = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button' and .//span[text()='Sdb Master']]")));
            System.out.println("Clicking Authorize Users button---->Adm-subModule-2");
            SDBMasterButton.click();
            
            // Click the Manage Users button
            WebElement SDBManageButton = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button' and .//span[text()='Manage Sdb Master']]")));
            System.out.println("Clicking Manage Users button---->Adm-subModule-2");
            SDBManageButton.click();
            
//            WebElement AddButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='AddOutlinedIcon']")));
//            System.out.println("Clicking Security button---->Adm-subModule-2");
//            AddButton.click();
            // Locate and click the "Size" dropdown
            WebElement AddButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div/div[2]/form/div/div[3]/div[2]/div/button")));
            AddButton.click();

            fillUserDetails(driver);
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("clickAdminButtonAndAddUser_error");
            throw e; // Re-throw the exception to handle it elsewhere if needed
        }
    }

    private void fillUserDetails(WebDriver driver) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Locate the "Sdb No" input field and click it to focus
            WebElement sdbNoInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-sizeSmall css-md26zr-MuiInputBase-root-MuiOutlinedInput-root']//input[@name='SDBno']")));
            sdbNoInput.click();

            // Clear any existing value in the "Sdb No" input field
            sdbNoInput.clear();

            // Enter a new value into the "Sdb No" input field
            sdbNoInput.sendKeys("8568");
           
            
         // Locate and click the "Size" dropdown
            WebElement sizeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[1]/p/div/form/div[4]/div/div/div")));
            sizeDropdown.click();

            // Wait for the dropdown options to be visible
            WebElement extralargeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[3]/ul/li[1]")));

            // Click the "Large" option
            extralargeOption.click();
           
            
         // Locate and click the "Dimension" dropdown
            WebElement dimensionDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[1]/p/div/form/div[5]/div/div/div")));
            dimensionDropdown.click();

            // Wait for the dropdown options to be visible
            // Assuming you want to select the first option; adjust XPath if needed
            WebElement dimensionOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[3]/ul/li")));

            // Click the desired option
            dimensionOption.click();
           
            
         // Locate and click the "Group" dropdown
            WebElement groupDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[1]/p/div/form/div[7]/div/div/div")));
            groupDropdown.click();

            // Wait for the dropdown options to become visible
            WebElement groupOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[3]/ul/li[1]")));

            // Click the desired "Group" option
            groupOption.click();
            
            
         // Locate the "Master Key" input field and click it to focus
            WebElement masterKeyInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-sizeSmall css-md26zr-MuiInputBase-root-MuiOutlinedInput-root']//input[@name='masterKey']")));
            masterKeyInput.click();

            // Clear any existing value in the "Master Key" input field
            masterKeyInput.clear();

            // Enter a new value into the "Master Key" input field
            masterKeyInput.sendKeys("6021"); // Replace with the desired value
            
            
         // Locate the "Customer Key" input field and click it to focus
            WebElement customerKeyInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-sizeSmall css-md26zr-MuiInputBase-root-MuiOutlinedInput-root']//input[@name='Key']")));
            customerKeyInput.click();

            // Clear any existing value in the "Customer Key" input field
            customerKeyInput.clear();

            // Enter a new value into the "Customer Key" input field
            customerKeyInput.sendKeys("6489"); // Replace with the desired value
           
            
         // Locate the "Access Card Number" input field and click it to focus
            WebElement accessCardNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-sizeSmall css-md26zr-MuiInputBase-root-MuiOutlinedInput-root']//input[@name='accessCardNum']")));
            accessCardNumberInput.click();

            // Clear any existing value in the "Access Card Number" input field
            accessCardNumberInput.clear();

            // Enter a new value into the "Access Card Number" input field
            accessCardNumberInput.sendKeys("98012"); // Replace with the desired value
           
            
         // Locate the "Room Number" input field and click it to focus
            WebElement roomNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='RoomNum']")));
            roomNumberInput.click();

            // Clear any existing value in the "Room Number" input field
            roomNumberInput.clear();

            // Enter a new value into the "Room Number" input field
            roomNumberInput.sendKeys("5"); // Replace with the desired value
            
         // Locate the "Row Number" input field and click it to focus
            WebElement rowNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='RowNum']")));
            rowNumberInput.click();

            // Clear any existing value in the "Row Number" input field
            rowNumberInput.clear();

            // Enter a new value into the "Row Number" input field
            rowNumberInput.sendKeys("9");
            
            
         // Locate the "Column Number" input field and click it to focus
            WebElement columnNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='ColumnNum']")));
            columnNumberInput.click();

            // Clear any existing value in the "Column Number" input field
            columnNumberInput.clear();

            // Enter a new value into the "Column Number" input field
            columnNumberInput.sendKeys("12");
            
            
         // Locate the "Distance" input field and click it to focus
            WebElement distanceInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='distanceFromEntrance']")));
            distanceInput.click();

            // Clear any existing value in the "Distance" input field
            distanceInput.clear();

            // Enter a new value into the "Distance" input field
            distanceInput.sendKeys("16"); // Replace with the desired value
            
            
         // Locate the "Height" input field and click it to focus
            WebElement heightInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='height']")));
            heightInput.click();

            // Clear any existing value in the "Height" input field
            heightInput.clear();

            // Enter a new value into the "Height" input field
            heightInput.sendKeys("14"); // Replace with the desired value
            
         // Locate and click the "Group" dropdown
            WebElement approveicon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[1]/p/div/form/div[14]/button[2]")));
            approveicon.click();
            
          // Click the OK button
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
            System.out.println("Clicking OK button");
            okButton.click();
            
            WebElement powerButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='PowerSettingsNewIcon']")));
            System.out.println("Clicking Power button");
            powerButton.click();

            WebElement agreeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Agree']")));
            System.out.println("Clicking Agree button");
            agreeButton.click();

            // Log in as dev, perform actions, and then log in as kishore
            loginAsDev(driver);
            performActionsAsDev(driver);
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("fillUserDetails_error");
            throw e; // Re-throw the exception to handle it elsewhere if needed
        }
    }

    public void performActionsAsDev(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Click the Admin button
            WebElement adminButton = wait
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='SettingsIcon']")));
            System.out.println("Clicking Admin button------------->Module-2");
            adminButton.click();
            

            WebElement securityButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='DisplaySettingsIcon']")));
            System.out.println("Clicking Security button---->Adm-subModule-2");
            securityButton1.click();

            // Click the Authorize Users button
            WebElement SDBMasterButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button' and .//span[text()='Sdb Master']]")));
            System.out.println("Clicking Authorize Users button---->Adm-subModule-2");
            SDBMasterButton.click();
            
         // Click the Manage Users button
            WebElement SDBManageButton = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button' and .//span[text()='Authorize Sdb Master']]")));
            System.out.println("Clicking Manage Users button---->Adm-subModule-2");
            SDBManageButton.click();
            
         // Locate and click the "Group" dropdown
            WebElement approveicon2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div/div[2]/form/div/div[4]/div/div[2]/div[2]/div/div/div/div[1]/div[9]/div/button[2]")));
            approveicon2.click();
            
         // Locate and click the "Group" dropdown
            WebElement approveicon3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[1]/p/div/form/div[15]/button[1]")));
            approveicon3.click();
            
            // Click the OK button
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
            System.out.println("Clicking OK button");
            okButton.click();
            
            
            
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("performActionsAsDev_error");
            throw e; // Re-throw the exception to handle it elsewhere if needed
        }
    }

    public static void main(String[] args) {
        // Setup WebDriver (e.g., ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Create Config and ScreenshotUtil instances
        Config config = new Config();
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);

        // Create an instance of NewBoxCreationApprove and perform login and actions
        NewBoxCreationApprove newBoxCreationApprove = new NewBoxCreationApprove(config, screenshotUtil);

        try {
            // Open the application URL
            String applicationUrl = "http://172.16.128.33:3000/"; // Your application URL
            driver.get(applicationUrl);

            // Perform login and actions
            newBoxCreationApprove.loginAsMaster(driver);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } 
    }
}





