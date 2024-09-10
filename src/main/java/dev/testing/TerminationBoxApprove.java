package dev.testing;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import dev.config.Config;
import dev.utils.ScreenshotUtil;

public class TerminationBoxApprove {

    private Config config;
    private ScreenshotUtil screenshotUtil;
    private String currentUser  = "";

    // Constructor to initialize Config and ScreenshotUtil
    public TerminationBoxApprove(Config config, ScreenshotUtil screenshotUtil) {
        this.config = config;
        this.screenshotUtil = screenshotUtil;
    }

    public void login(WebDriver driver, String userType) throws Exception {
        try {
            switch (userType.toLowerCase()) {
                case "master":
                    loginAsMaster(driver);
                    break;
                case "dev":
                    loginAsDev(driver);
                    break;
//                case "newuser":
//                    loginAsNewuser(driver);
//                    break;
                default:
                    throw new IllegalArgumentException("Invalid user type: " + userType);
            }
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("login_" + userType + "_error");
            throw e;
        }
    }

    private void loginAsMaster(WebDriver driver) throws Exception {
        try {
            login(driver, config.getMasterUsername(), config.getPassword());
            if (shouldRunAdminAndUserActions("master")) {
                clickAdminButtonAndAddUser(driver, "master");
            }
            fillUserDetails(driver, "master");
            logout(driver, "master");
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("loginAsMaster_error");
            throw e;
        }
    }

    private void loginAsDev(WebDriver driver) throws Exception {
        try {
            login(driver, config.getDevUsername(), config.getPassword());
            if (shouldRunAdminAndUserActions("dev")) {
                clickAdminButtonAndAddUser(driver, "dev");
            }
            performActionsAsDev(driver, "dev");
//            logout(driver, "dev");
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("loginAsDev_error");
            throw e;
        }
    }

    private void loginAsNewuser(WebDriver driver) throws Exception {
        try {
        	if(currentUser.equalsIgnoreCase(config.getKarthickUsername())) {
        		System.out.println("User already logged in...");
        		return ;
            }
            login(driver, config.getKarthickUsername(), config.getPassword());
            if (shouldRunAdminAndUserActions("newuser")) {
                clickAdminButtonAndAddUser(driver, "newuser");
            }
            performActionsAsDev(driver, "newuser");
//            logout(driver, "newuser");
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("loginAsNewuser_error");
            throw e;
        }
    }

    private boolean shouldRunAdminAndUserActions(String userType) {
        return userType.equalsIgnoreCase("master") || userType.equalsIgnoreCase("dev") || userType.equalsIgnoreCase("newuser");
    }

    private void login(WebDriver driver, String username, String password) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
            
           if(!currentUser.equalsIgnoreCase(username)) {
        	   currentUser = username;
               usernameField.sendKeys(username);
               passwordField.sendKeys(password);
               loginButton.click();
               System.out.println("Login successfully");
           } else {
        	   System.out.println("User already logged in...");
           }

        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("login_error");
            throw e;
        }
    }

    private void clickAdminButtonAndAddUser(WebDriver driver, String userType) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Click the Customer button
            WebElement customerButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='SupportAgentIcon']")));
            customerButton.click();
            System.out.println("Clicking Customer button");
            

            // Click the Individual button
            WebElement individualButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='EmojiPeopleIcon']")));
            individualButton.click();
            System.out.println("Clicking Individual button");
            

            // Search for customer
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName")));
            inputField.sendKeys("Jonathan");
            System.out.println("Type the Customer name");
                        

            // Perform search
            WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='SearchIcon']")));
            searchIcon.click();
            System.out.println("Clicking search button");
            

            // Click the East icon to approve
            WebElement eastIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='EastIcon']")));
            eastIcon.click();
            System.out.println("Clicking next button");
            

            WebElement borderColorIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='BorderColorIcon']")));
            borderColorIcon.click();
            System.out.println("Clicking Signature button");
            ;

            WebElement doneButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[3]/div/div[2]/div/button[1]")));
            System.out.println("Clicking Signature verify");
            doneButton1.click();
           

            WebElement doneButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[1]/table/tbody/tr[2]/td[2]/button")));
            System.out.println("Clicking ID Card verify");
            doneButton2.click();
            

            WebElement doneButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[2]/button[1]")));
            System.out.println("Clicking done button");
            doneButton3.click();
           

            WebElement okButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
            System.out.println("Clicking OK button");
            okButton1.click();
            
            
            // Locate the Manage Termination icon using its data-testid attribute and click it
			WebElement manageTerminationIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='EventBusyIcon']")));
			manageTerminationIcon.click();

            if ("master".equalsIgnoreCase(userType)) {
                fillUserDetails(driver, "master");
            }
            
            if ("dev".equalsIgnoreCase(userType)) {
            	performActionsAsDev(driver, "dev");
            }
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("clickAdminButtonAndAddUser_" + userType + "_error");
            throw e;
        }
    }

    private void fillUserDetails(WebDriver driver, String userType) throws Exception {
        if ("master".equalsIgnoreCase(userType)) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Locate the Manage Termination icon using its data-testid attribute and click it
                WebElement manageTerminationIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='EventBusyIcon']")));
                manageTerminationIcon.click();

                // Locate the Add icon using its data-testid attribute and click it
                WebElement addIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='AddOutlinedIcon']")));
                addIcon.click();
                
//             // Locate the Safe Box using XPath and click it
//                WebElement safeBoxButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[2]/button")));
//                safeBoxButton.click();

                
    	         WebElement safeBoxButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-id='8567']//button[@aria-label='SELECT']")));
    			 safeBoxButton.click();

//                // Locate the Safe Box using XPath and click it
//                WebElement safeBoxButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[1]/form/div[2]/div/div[2]/div[2]/div/div/div/div[9]/div[8]/button")));
//                safeBoxButton.click();

                // Locate the East Icon using its data-testid attribute and click it
                WebElement eastIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='EastIcon']")));
                eastIcon.click();

                // Locate the Termination Box using its ID and click it to open the dropdown
                WebElement terminationBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("terminationType")));
                terminationBox.click();

                WebElement terminationOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Voluntary Termination']")));
                terminationOption.click();
                System.out.println("Select the terminationType");

                // Locate the Key Condition dropdown using its ID and click it to open the dropdown
                WebElement keyConditionDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("keyCondition")));
                keyConditionDropdown.click();

                WebElement keyConditionOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Lost']")));
                keyConditionOption.click();
                System.out.println("Select the keyCondition");

                // Locate the Reason textarea using its ID and enter the reason
                WebElement reasonTextarea = wait.until(ExpectedConditions.elementToBeClickable(By.id("reason")));
                reasonTextarea.clear(); // Clear any existing text
                reasonTextarea.sendKeys("S");
                
              // Locate the Safe Box using XPath and click it
              WebElement DoneButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[2]/div/button[1]")));
              DoneButton.click();
              
           // Locate the Safe Box using XPath and click it
              WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[2]/button")));
              okButton.click();
              
           // Locate the Safe Box using XPath and click it
              WebElement eastButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[1]/div/div/div/div/div[1]/div/div/p/div/div/div[2]/div[2]/div/div/div/div/div[7]/div/button")));
              eastButton.click();
              
              WebElement CustomerButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='RemoveRedEyeIcon']")));
              CustomerButton.click();
              System.out.println("Clicking Customer done Button");
             
//               // Locate the Safe Box using XPath and click it
//              WebElement CustomerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[1]/table/tbody/tr[1]/td[2]/button")));
//              CustomerButton.click();

              // Locate the Safe Box using XPath and click it
              WebElement CustomerdoneButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[3]/div/div[2]/div/button[1]")));
              CustomerdoneButton.click();
              
              WebElement borderColorIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='BorderColorIcon']")));
              borderColorIcon.click();
              System.out.println("Clicking Signature button");
              

              WebElement doneButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[3]/div/div[2]/div/button[1]")));
              System.out.println("Clicking Signature verify");                                      
              doneButton1.click();
              

              WebElement doneButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[1]/table/tbody/tr[3]/td[2]/button")));
              System.out.println("Clicking ID Card verify");                                         
              doneButton2.click();
              

              WebElement doneButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[2]/button[1]")));
              System.out.println("Clicking done button");
              doneButton3.click();
              
              
              WebElement doneIcon1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[2]/div/button[1]")));
              doneIcon1.click();
              System.out.println("Clicking done1 button");

              // Locate the OK button using its XPath and click it
              WebElement okButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[2]/button")));
              okButton1.click();
              System.out.println("Clicking ok button");
              
              Thread.sleep(6000);

              // Locate the East icon using its XPath and click it
              WebElement eastIcon1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[2]/div/button[1]")));
              eastIcon1.click();
              System.out.println("Clicking easticon1 button");

              // Locate the Done icon using its XPath and click it again
              WebElement doneIconAgain = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[2]/div/button[1]")));
              doneIconAgain.click();
              System.out.println("Clicking doneagain button");

              // Locate the OK icon using its XPath and click it again
              WebElement okIconAgain = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[2]/button")));
              okIconAgain.click();
              System.out.println("Clicking okagain button");
              
              // Click the Power button
              WebElement powerButton = wait
                      .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='PowerSettingsNewIcon']")));
              System.out.println("Clicking Power button");
              powerButton.click();

              // Click the Agree button
              WebElement agreeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Agree']")));
              System.out.println("Clicking Agree button");
              agreeButton.click();
              
              loginAsDev(driver);

            } catch (Exception e) {
                screenshotUtil.takeScreenshotWithDate("fillUserDetails_" + userType + "_error");
                throw e;
            }
        }
    }

    private void performActionsAsDev(WebDriver driver, String userType) throws Exception {
        if ("dev".equalsIgnoreCase(userType) || "newuser".equalsIgnoreCase(userType)) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                WebElement AuthorizeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div")));
                AuthorizeButton.click();
                
                WebElement authorizetermination = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div[2]/div[2]/div/div/div/div[1]/div[10]/div/div/button[2]")));

                authorizetermination.click();
                
//                WebElement boxCodeDiv = wait.until(ExpectedConditions.elementToBeClickable(
//                	    By.xpath("//div[@data-field='boxCode' and text()='4567']")
//                	));
//                	boxCodeDiv.click();


//                WebElement authorizetermination = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/button")));
//                
//                authorizetermination.click();

//                WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div[2]/div[2]/div/div/div/div/div[7]/div/div/button[2]")));
//                approveButton.click();

                WebElement easticonnxt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[2]/div/button[1]")));
                easticonnxt.click();

                WebElement approvicon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[2]/div/button[1]")));
                approvicon.click();

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
                
                
                loginAsNewuser(driver);

            } catch (Exception e) {
            	e.printStackTrace();
                screenshotUtil.takeScreenshotWithDate("performActionsAsDev_" + userType + "_error");
                throw e;
            }
        }
    }

    private void logout(WebDriver driver, String userType) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement powerButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='PowerSettingsNewIcon']")));
            System.out.println("Clicking Power button");
            powerButton.click();

            WebElement agreeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Agree']")));
            System.out.println("Clicking Agree button");
            agreeButton.click();
            System.out.println("Logout successfully from " + userType);
        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("logout_" + userType + "_error");
            throw e;
        }
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            Config config = new Config();
            ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);

            TerminationBoxApprove terminationBoxApprove = new TerminationBoxApprove(config, screenshotUtil);

            driver.get("http://172.16.128.33:3000/");

            terminationBoxApprove.login(driver, "master");
            terminationBoxApprove.login(driver, "dev");
            terminationBoxApprove.login(driver, "newuser");

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
