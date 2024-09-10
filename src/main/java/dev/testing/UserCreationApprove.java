package dev.testing;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.config.Config;
import dev.utils.ScreenshotUtil;

public class UserCreationApprove {

    private Config config;
    private ScreenshotUtil screenshotUtil;

    // Constructor to initialize Config and ScreenshotUtil
    public UserCreationApprove(Config config, ScreenshotUtil screenshotUtil) {
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

    public void loginAsNewuser(WebDriver driver) throws Exception {
        try {
            // Perform login action
            login(driver, config.getKarthickUsername(), config.getPassword());

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Locate the dropdown and click it
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-sizeSmall css-md26zr-MuiInputBase-root-MuiOutlinedInput-root']")));
            dropdown.click();

            // Select "all access" from the dropdown
            WebElement allAccessOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-kk1bwy-MuiButtonBase-root-MuiMenuItem-root' and @data-value='all access']")));
            allAccessOption.click();

            // Click the OK button
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
            System.out.println("Clicking OK button");
            okButton.click();

            // Click various buttons and perform actions
            performActionsAsKarthick(driver);
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("loginAskarthick_error");
            throw e; // Re-throw the exception to handle it elsewhere if needed
        }
    }

    private void performActionsAsKarthick(WebDriver driver) throws TimeoutException {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement customerButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='SupportAgentIcon']")));
        System.out.println("Clicking Customer button------------->Module-1");
        customerButton.click();

        WebElement individualButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='EmojiPeopleIcon']")));
        System.out.println("Clicking Individual button---->Cus-subModule-1");
        individualButton.click();

        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName")));
        System.out.println("Input Jonathan");
        inputField.sendKeys("Jonathan");

        WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='SearchIcon']")));
        searchIcon.click();

        WebElement eastIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='EastIcon']")));
        eastIcon.click();

        WebElement borderColorIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='BorderColorIcon']")));
        borderColorIcon.click();

        WebElement donebutton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[3]/div/div[2]/div/button[1]")));
        System.out.println("Clicking done button 1");
        donebutton1.click();

        WebElement donebutton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[1]/table/tbody/tr[2]/td[2]/button")));
        System.out.println("Clicking done button 2");
        donebutton2.click();

        WebElement donebutton3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[2]/button[1]")));
        System.out.println("Clicking done button 3");
        donebutton3.click();

        WebElement okButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
        System.out.println("Clicking OK button");
        okButton1.click();
    }catch (Exception e) {
        screenshotUtil.takeScreenshotWithDate("performActionsAsKarthick_error");
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

            // Check the user type and perform actions accordingly
            switch (username) {
                case "master":
                    clickAdminButtonAndAddUser(driver);
                    break;
//                case "dev":
//                    // Perform specific actions for 'dev'
//                	performActionsAsDev(driver);
//                    break;
//                case "karthick":
//                    // Perform specific actions for 'dev'
//                	loginAskarthick(driver);
//                    break;
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
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement adminButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='SettingsIcon']")));
        System.out.println("Clicking Admin button------------->Module-2");
        adminButton.click();

        WebElement securityButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='SecurityIcon']")));
        System.out.println("Clicking Security button---->Adm-subModule-2");
        securityButton.click();

        WebElement addUserButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='AddIcon']")));
        System.out.println("Clicking Add User button---->Adm-subModule-2");
        addUserButton.click();

        fillUserDetails(driver);
    }catch (Exception e) {
        screenshotUtil.takeScreenshotWithDate("clickAdminButtonAndAddUser_error");
        throw e; // Re-throw the exception to handle it elsewhere if needed
    }
}

    private void fillUserDetails(WebDriver driver) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
            firstNameInput.sendKeys("sriramm");

            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("emailId")));
            emailInput.sendKeys("sriram.devikkk@aaratech.com");

            WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("userName")));
            usernameInput.sendKeys("sriramm");

            WebElement lastNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastName")));
            lastNameInput.sendKeys("Pandi");

            WebElement branchIdDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("branchId")));
            branchIdDropdown.click();
            WebElement branchIdOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='1001 - ORLANDO']")));
            branchIdOption.click();

            WebElement designationDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("designation")));
            designationDropdown.click();
            WebElement designationOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Ops Head']")));
            designationOption.click();

            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
            passwordInput.sendKeys("P@ssw0rd");

            WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("status")));
            statusDropdown.click();
            WebElement statusOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Active']")));
            statusOption.click();

            WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Profiles']")));
            profileButton.click();

            WebElement moveAllRightButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='move all right']")));
            moveAllRightButton.click();

            WebElement checkOutAllAccessCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "//span[contains(@class, 'MuiCheckbox-root') and descendant::input[@aria-labelledby='transfer-list-item-all access-label']]")));
            System.out.println("Clicking Check out for all access checkbox");
            checkOutAllAccessCheckbox.click();

            WebElement doneIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='DoneIcon']")));
            System.out.println("Clicking Done icon");
            doneIcon.click();

            WebElement approveIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='ApprovalIcon']")));
            System.out.println("Clicking Approve icon");
            approveIcon.click();

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

            // Log in again as kishore
            loginAsNewuser(driver);
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("fillUserDetails_error");
            throw e; // Re-throw the exception to handle it elsewhere if needed
        }
    }

    public void performActionsAsDev(WebDriver driver) {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Click the Admin button
    WebElement adminButton = wait
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='SettingsIcon']")));
    System.out.println("Clicking Admin button------------->Module-2");
    adminButton.click();

    // Click the Security button
    WebElement securityButton = wait
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='SecurityIcon']")));
    System.out.println("Clicking Security button---->Adm-subModule-2");
    securityButton.click();

    // Click the Authorize Users button
    WebElement authorizeUsersButton = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button' and .//span[text()='Authorize Users']]")));
    System.out.println("Clicking Authorize Users button---->Adm-subModule-2");
    authorizeUsersButton.click();

    // Click the first ThumbsUp button
    WebElement thumbsButton1 = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/form/div/div[3]/div/div[2]/div[2]/div/div/div/div/div[6]/div/button[2]")));
    System.out.println("Clicking ThumbsUp button 1");
    thumbsButton1.click();

    // Click the second ThumbsUp button
    WebElement thumbsButton2 = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[2]/div/button[1]")));
    System.out.println("Clicking ThumbsUp button 2");
    thumbsButton2.click();

    // Click the OK button
    WebElement okButton = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
    System.out.println("Clicking OK button");
    okButton.click();

    // Click the Power button
    WebElement powerButton = wait
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='PowerSettingsNewIcon']")));
    System.out.println("Clicking Power button");
    powerButton.click();

    // Click the Agree button
    WebElement agreeButton = wait
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Agree']")));
    System.out.println("Clicking Agree button");
    agreeButton.click();
}catch (Exception e) {
        screenshotUtil.takeScreenshotWithDate("performActionsAsDev_error");
        throw e; // Re-throw the exception to handle it elsewhere if needed
     }
  }

public static void main(String[] args) throws TimeoutException, InterruptedException {
    // Set the path to the ChromeDriver executable
    System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver-win64\\chromedriver.exe");

    // Initialize a new ChromeDriver instance
    WebDriver driver = new ChromeDriver();

    try {
        // Create Config and ScreenshotUtil instances
        Config config = new Config();
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);

        // Create Login instance, passing Config and ScreenshotUtil
        UserCreationApprove UserCreationApprove = new UserCreationApprove(config, screenshotUtil);

        // Open the specified URL
        driver.get("http://172.16.128.33:3000/");
        
        // Login as master
        UserCreationApprove.loginAsMaster(driver);
        
    } catch (Exception e) {
        e.printStackTrace(); // Handle exceptions if necessary
    }
    // Note: No driver.quit() here
}
}




