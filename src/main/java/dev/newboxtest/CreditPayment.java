package dev.newboxtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import dev.config.Config;
import dev.utils.ScreenshotUtil;
import dev.utils.ExcelUtils;

import java.time.Duration;

public class CreditPayment {

    private Config config;
    private ScreenshotUtil screenshotUtil;
    private ExcelUtils excelUtil;

    // Constructor to initialize Config, ScreenshotUtil, and ExcelUtil
    public CreditPayment(Config config, ScreenshotUtil screenshotUtil, ExcelUtils excelUtil) {
        this.config = config;
        this.screenshotUtil = screenshotUtil;
        this.excelUtil = excelUtil;
    }

    public void loginAsMaster(WebDriver driver) throws Exception {
        try {
            // Login logic for master user
            login(driver, config.getMasterUsername(), config.getPassword());

            // Fetch data from Excel
            String[][] excelData = excelUtil.readExcelData();
            for (String[] row : excelData) {
                String customerName = row[0];
                String bankName = row[1];
                String cardNumber = row[6];
                String authNumber = row[7];
                
                // Navigate to Customer and search for customerName
                navigateToCustomerAndSearch(driver, customerName);
                
                // Perform payment actions
                performCreditPaymentActions(driver, bankName, cardNumber, authNumber);
                
//                // Logout after each customer
//                logout(driver);
//
//                // Re-login for the next customer
//                login(driver, config.getMasterUsername(), config.getPassword());
            }
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("loginAsMaster_error");
            throw e; // Re-throw the exception to handle it elsewhere if needed
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

        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("login_error");
            throw e; // Re-throw the exception to handle it elsewhere if needed
        }
    }

    private void navigateToCustomerAndSearch(WebDriver driver, String customerName) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Click the Customer button
            WebElement customerButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='SupportAgentIcon']")));
            customerButton.click();
            System.out.println("Clicking Customer button");
            Thread.sleep(1000);

            // Click the Individual button
            WebElement individualButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='EmojiPeopleIcon']")));
            individualButton.click();
            System.out.println("Clicking Individual button");
            Thread.sleep(1000);

            // Search for customer
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName")));
            inputField.sendKeys(customerName);
            System.out.println("Type the Customer name");
            Thread.sleep(1000);            

            // Perform search
            WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='SearchIcon']")));
            searchIcon.click();
            System.out.println("Clicking search button");
            Thread.sleep(1000);

            // Click the East icon to approve
            WebElement eastIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='EastIcon']")));
            eastIcon.click();
            System.out.println("Clicking next button");
            Thread.sleep(1000);

            WebElement borderColorIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='BorderColorIcon']")));
            borderColorIcon.click();
            System.out.println("Clicking Signature button");
            Thread.sleep(1000);

            WebElement doneButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[3]/div/div[2]/div/button[1]")));
            System.out.println("Clicking Signature verify");
            doneButton1.click();
            Thread.sleep(1000);

            WebElement doneButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[1]/table/tbody/tr[2]/td[2]/button")));
            System.out.println("Clicking ID Card verify");
            doneButton2.click();
            Thread.sleep(1000);

            WebElement doneButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[2]/button[1]")));
            System.out.println("Clicking done button");
            doneButton3.click();
            Thread.sleep(1000);

            WebElement okButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
            System.out.println("Clicking OK button");
            okButton1.click();
            Thread.sleep(1000);

            // Click the New Box
            WebElement newBoxIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='InventoryIcon']")));
            System.out.println("Clicking the New Box");
            newBoxIcon.click();
            Thread.sleep(1000);
            
            WebElement Easticon1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[1]/div/div/p/main/div/div[3]/div/div[2]/div[2]/div/div/div/div[1]/div[4]/div/button[2]")));
            System.out.println("Select the size of box");
            Easticon1.click();
            Thread.sleep(1000);
            
            WebElement Easticon2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[1]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/button")));
            System.out.println("Clicking the box");
            Easticon2.click();
            Thread.sleep(1000);
            
            WebElement operationDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("operationConditionSelect")));
            operationDropdown.click();
            WebElement operationOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Single Endorser']")));
            operationOption.click();
            System.out.println("Select the operation");
            Thread.sleep(1000);
            
         // Locate the dropdown and click it
            WebElement durationDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-sizeSmall css-md26zr-MuiInputBase-root-MuiOutlinedInput-root']")));
            durationDropdown.click();

            // Select "Yearly" from the dropdown
            WebElement yearlyOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-kk1bwy-MuiButtonBase-root-MuiMenuItem-root' and @data-value='Y']")));
            yearlyOption.click();
            System.out.println("Select the duration");
            
         // Locate the "Duration" input field and click it to focus
            WebElement durationInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-sizeSmall css-md26zr-MuiInputBase-root-MuiOutlinedInput-root']//input[@name='duration']")));
            durationInput.click();

            // Clear any existing value in the "Duration" input field
            durationInput.clear();

            // Enter a new value into the "Duration" input field
            durationInput.sendKeys("2");
            System.out.println("Input the duration");
            Thread.sleep(1000);
            
         // Locate and click the "Product Type" dropdown
            WebElement productTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/form/div/div[2]/div[8]/div/div")));
            productTypeDropdown.click();

            // Wait for the dropdown options to be visible
            WebElement productTypeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-value='PRYEAR1']")));

            // Click the desired option
            productTypeOption.click();
            System.out.println("Select the Product type");
            Thread.sleep(1000);         
            
         // Locate and click the ArrowForwardIcon SVG                        
            WebElement arrowForwardIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='ArrowForwardIcon']")));
            arrowForwardIcon.click();
            System.out.println("Click the Proceed");
            Thread.sleep(1000);
            
         // Locate and click the EastIcon SVG element
            WebElement eastIcon1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='EastIcon']")));
            eastIcon1.click();
            System.out.println("Click the next");
            Thread.sleep(1000);
            
         // Locate and click the EastIcon SVG element
            WebElement nexticon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/button")));
            nexticon.click();
            System.out.println("Click the Confirm");
            Thread.sleep(1000);

        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("navigateToCustomerAndSearch_error");
            throw e; // Re-throw the exception to handle it elsewhere if needed
        }
    }

    private void performCreditPaymentActions(WebDriver driver, String bankName, String cardNumber, String authNumber) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            // Locate and click the Credit Payment icon
            WebElement crediticon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[6]/div/div")));
            crediticon.click();
            System.out.println("Clicked the Credit Payment icon");
            Thread.sleep(1000);

            // Card Number (Last 4 digits) input
            WebElement cardNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-sizeSmall css-md26zr-MuiInputBase-root-MuiOutlinedInput-root']//input[@name='All_Last_Card_No']")));
            cardNumberInput.click();
            cardNumberInput.clear();
            cardNumberInput.sendKeys(cardNumber);
            System.out.println("Entered the Last 4 digits of the Card Number: " + cardNumber);
            Thread.sleep(1000);

            // Issuer Bank Name input
            WebElement bankNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-sizeSmall css-md26zr-MuiInputBase-root-MuiOutlinedInput-root']//input[@name='bankName']")));
            bankNameInput.click();
            bankNameInput.clear();
            bankNameInput.sendKeys(bankName);
            System.out.println("Entered the Issuer Bank Name: " + bankName);
            Thread.sleep(1000);

            // Auth Code input
            WebElement authCodeInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-sizeSmall css-md26zr-MuiInputBase-root-MuiOutlinedInput-root']//input[@name='Authcode']")));
            authCodeInput.click();
            authCodeInput.clear();
            authCodeInput.sendKeys(authNumber);
            System.out.println("Entered the Auth Code: " + authNumber);
            Thread.sleep(1000);

            // Locate and click the next icon button
            WebElement nextIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/form/div/div[5]/button")));
            nextIcon.click();
            System.out.println("Clicked the Next button");
            Thread.sleep(1000);

            // Locate and click the follow icon button
            WebElement followIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/main/div/div[3]/div/button")));
            followIcon.click();
            System.out.println("Clicked the Follow icon button");
            Thread.sleep(1000);

            // Locate and click the right icon button
            WebElement rightIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[4]/button")));
            rightIcon.click();
            System.out.println("Clicked the Right icon button");
            Thread.sleep(1000);

            // Locate and click the right icon button (second instance)
            WebElement rightIcon1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/main/div/div[4]/button")));
            rightIcon1.click();
            System.out.println("Clicked the Right icon button (second instance)");
            Thread.sleep(1000);

        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("performNetbankPaymentActions_error");
            throw e; // Re-throw the exception to handle it elsewhere if needed
        }
    }

//    private void logout(WebDriver driver) throws Exception {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//           WebElement powerButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='PowerSettingsNewIcon']")));
//           System.out.println("Clicking Power button");
//           powerButton.click();
//
//           WebElement agreeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Agree']")));
//           System.out.println("Clicking Agree button");
//           agreeButton.click();
//
//            System.out.println("Logged out successfully");
//
//        } catch (Exception e) {
//            screenshotUtil.takeScreenshotWithDate("logout_error");
//            throw e; // Re-throw the exception to handle it elsewhere if needed
//        }
//    }

   public static void main(String[] args) {
        // Setup WebDriver (e.g., ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Create Config, ScreenshotUtil, and ExcelUtil instances
        Config config = new Config();
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
        ExcelUtils excelUtil = new ExcelUtils("D:\\selenium\\ExcelCostumer.xlsx"); 

        try {
            // Open the application URL
            String applicationUrl = "http://172.16.128.33:3000/";
            driver.get(applicationUrl);

            // Create an instance of CreditPayment and perform login and actions
            CreditPayment creditPayment = new CreditPayment(config, screenshotUtil, excelUtil);
            creditPayment.loginAsMaster(driver);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } 
    }
}


