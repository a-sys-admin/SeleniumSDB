package dev.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import dev.config.Config;
import dev.utils.ScreenshotUtil;

public class AddCustomer {

    private WebDriver driver;
    private WebDriverWait wait;
    private ScreenshotUtil screenshotUtil;

    public AddCustomer(WebDriver driver, Config config, ScreenshotUtil screenshotUtil) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.screenshotUtil = screenshotUtil;
    }

    public void loginAsMaster(String username, String password) {
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
            System.err.println("Failed to login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickCustomerButton() {
        WebElement customerButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='SupportAgentIcon']")));
        System.out.println("Clicking Customer button...");
        customerButton.click();
    }

    public void clickNewCustomer() {
        WebElement newCustomerButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='PersonAddIcon']")));
        System.out.println("Clicking New Customer button...");
        newCustomerButton.click();
    }

    public void fillCustomerForm() throws Exception {
        try {
            
         // Select Customer Type
            WebElement customerTypeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("custType")));
            customerTypeDropdown.click();
            
            
            // Wait for and select the 'Individual' option
            WebElement individualOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-value='I']")));
            individualOption.click();
            

            // Select Title
            WebElement titleDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("englishTitle")));
            titleDropdown.click();
            WebElement missOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-value='MR.']")));
            missOption.click();
            

            // Fill First Name
            WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("englishFirstName")));
            firstNameInput.clear();
            firstNameInput.sendKeys("Karthick");
           

            // Fill Last Name
            WebElement lastNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("englishLastName")));
            lastNameInput.clear();
            lastNameInput.sendKeys("Bala");
            

            // Fill Date of Birth
            
            WebElement donebutton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[1]/div/div/div[2]/div/div[1]/div/div/p/div/div[7]/div/div/input")));
            System.out.println("Clicking done button 1");
            donebutton1.click();
           

            // Assuming 'driver' is your WebDriver instance and 'wait' is your WebDriverWait instance

            // Click the Date Of Birth input box
            WebElement dobInputBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiFormControl-root MuiFormControl-fullWidth MuiTextField-root css-wb57ya-MuiFormControl-root-MuiTextField-root'][1]//input[@aria-label='Choose date']")));
            dobInputBox.click();
            
//         // Locate the SVG element using the data-testid attribute and click it
//            WebElement penIcon = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//svg[@data-testid='PenIcon']")
//            ));
//            penIcon.click();
            
//         // Locate the button using the data-testid of the SVG icon
//            WebElement penIconButton = driver.findElement(By.cssSelector("button[aria-label='calendar view is open, go to text input view'] svg[data-testid='PenIcon']"));
//
//            // Click the button
//            penIconButton.click();
           
            
         // Wait for the button to be clickable
            WebElement penIcon1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[1]/div/div[1]/div/button")));
            penIcon1.click();
           

         // Wait for the Date Of Birth input field to be clickable
            WebElement dobInput1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiFormControl-root MuiFormControl-fullWidth MuiTextField-root css-wb57ya-MuiFormControl-root-MuiTextField-root']//input[@placeholder='dd mmm yyyy']")));

            // Click the Date Of Birth input field
            dobInput1.click();

            // Optionally, clear any existing value
            dobInput1.clear();

            // Enter a new value into the Date Of Birth input field
            dobInput1.sendKeys("31 Jan 1997");
            
            
//            // Confirm the date selection by clicking "OK"
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
            okButton.click();
            
            
         // Locate and click the "Gender" dropdown
            WebElement genderDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[1]/div/div/div[2]/div/div[1]/div/div/p/div/div[9]/div/div/div")));
            genderDropdown.click();

            // Wait for the dropdown options to be visible
            WebElement maleOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/ul/li[2]")));

            // Click the desired "Male" option
            maleOption.click();
           
            // Locate and click the "Marital Status" dropdown
            WebElement maritalStatusDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[1]/div/div/div[2]/div/div[1]/div/div/p/div/div[10]/div/div/div")));
            maritalStatusDropdown.click();

            // Wait for the dropdown options to be visible
            WebElement divorcedOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/ul/li[1]")));

            // Click the desired "Divorced" option
            divorcedOption.click();
           
            
         // Locate the "Mobile Number" input field and clear it
            WebElement mobileNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='mobileNbr']")));
            mobileNumberInput.clear();

            // Enter a new value into the "Mobile Number" input field
            mobileNumberInput.sendKeys("9941421868");
          

            // Locate the "Email ID" input field and clear it
            WebElement emailIdInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='emailId']")));
            emailIdInput.clear();

            // Enter a new value into the "Email ID" input field
            emailIdInput.sendKeys("kishore.kumari@aaratech.com");
            
//         // Locate the "Info Two" button by its ID attribute
//            WebElement infoTwoButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("simple-tab-1")));
//
//            // Click the "Info Two" button
//            infoTwoButton.click();
            
            // Locate and click the "Info Three" tab
            WebElement infoTwoButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("simple-tab-1")));
            infoTwoButton.click();
            
          WebElement DoneIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='DoneIcon']")));
          DoneIcon.click();

            
//         // Locate the "Info Two" button by its text content
//            WebElement infoTwoButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@role='tab' and text()='Info Two']")));
//
//            // Click the "Info Two" button
//            infoTwoButton.click();
            
         // Locate and click the "ID Type" dropdown
            WebElement idTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[1]/div/div/div[2]/div/div[2]/div/div/p/div/div[1]/div/div/div")));
            idTypeDropdown.click();

            // Wait for the dropdown options to be visible and select "Aadhaar"
            WebElement aadhaarOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/ul/li[2]")));
            aadhaarOption.click();
            
         // Locate the "ID No" input field
            WebElement idNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("idNbr")));

            // Optionally, clear any existing value
            idNumberInput.clear();

            // Enter the "ID No" into the input field
            idNumberInput.sendKeys("2434567891741123");
            
         // Locate and click the "Income Range" dropdown
            WebElement incomeRangeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[1]/div/div/div[2]/div/div[2]/div/div/p/div/div[3]/div/div/div")));
            incomeRangeDropdown.click();

            // Wait for the dropdown options to be visible and select "14000"
            WebElement option14000 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/ul/li[2]")));
            option14000.click();
            
         // Locate and click the "Source of Income" dropdown
            WebElement sourceOfIncomeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("sourceOfIncome")));
            sourceOfIncomeDropdown.click();

            // Select "Government Officer" from the options (assuming it's the second option)
            WebElement governmentOfficerOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/ul/li[2]")));
            governmentOfficerOption.click();
            
         // Locate and click the "Occupation" dropdown
            WebElement occupationDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[1]/div/div/div[2]/div/div[2]/div/div/p/div/div[5]/div/div/div")));
            occupationDropdown.click();

            // Select the desired option (replace with the correct XPath if necessary)
            WebElement occupationOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/ul/li[1]")));
            occupationOption.click();
            
         // Locate and click the "Type" dropdown
            WebElement typeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("custTypeCode")));
            typeDropdown.click();

            // Select the "Normal" type (assuming it's the first option)
            WebElement normalTypeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/ul/li[1]")));
            normalTypeOption.click();
            
         // Locate the "Telephone" input field and enter a value
            WebElement telephoneInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("Telephoneno")));
            telephoneInput.clear();
            telephoneInput.sendKeys("1456435");
            Thread.sleep(1000);
            
         // Locate the "Telephone Extension" input field and enter a value
            WebElement telephoneExtInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("TelExtn")));
            telephoneExtInput.clear();
            telephoneExtInput.sendKeys("345");
            Thread.sleep(1000);
            
         // Locate the "Fax Number" input field and enter a value
            WebElement faxNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("faxNbr")));
            faxNumberInput.clear();
            faxNumberInput.sendKeys("5678245");
            
         // Locate the "Fax Number Extension" input field and enter a value
            WebElement faxExtInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("faxExtn")));
            faxExtInput.clear();
            faxExtInput.sendKeys("234");
            
         // Locate and click the "Info Three" tab
            WebElement infoThreeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("simple-tab-2")));
            infoThreeButton.click();
        
//         // Locate and click the "Add" button by its parent container (adjust the selector as needed)
//           WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@data-testid, 'ControlPointIcon')]")));
//           addButton.click();
           
//        // Locate the button using its XPath
//           WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[1]/div/div/div[2]/div/div[3]/div/div/p/div[1]/span/button")));
//
//           // Click the button
//           button.click();
           
        // Locate the SVG element using its data-testid attribute
           WebElement controlPointIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='ControlPointIcon']")));

           // Click the SVG element
           controlPointIcon.click();

           
        // Locate and click the "Address Type" dropdown
           WebElement addressTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("addressType")));
           addressTypeDropdown.click();

           // Wait for the dropdown options to be visible and select the desired option (e.g., Mailing Address)
           WebElement mailingAddressOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Mailing Address']")));
           mailingAddressOption.click();
           
        // Locate and enter text in the "Address 1" field
           WebElement address1Input = wait.until(ExpectedConditions.elementToBeClickable(By.id("buildingNbr")));
           address1Input.clear();
           address1Input.sendKeys("2178 Ryan Road, Anderson Street");
           
        // Locate and enter text in the "Address 2" field
           WebElement address2Input = wait.until(ExpectedConditions.elementToBeClickable(By.id("building")));
           address2Input.clear();
           address2Input.sendKeys("635 Grand Avenue");
           
        // Locate and enter text in the "Zipcode" field
           WebElement zipcodeInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("zipCode")));
           zipcodeInput.clear();
           zipcodeInput.sendKeys("94043");
           
        // Locate and enter text in the "Sub District" field
           WebElement subDistrictInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("subDistrict")));
           subDistrictInput.clear();
           subDistrictInput.sendKeys("Washington");
           
        // Locate and enter text in the "District" field
           WebElement districtInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("district")));
           districtInput.clear();
           districtInput.sendKeys("Washington");
           
        // Locate and enter text in the "State" field
           WebElement stateInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("province")));
           stateInput.clear();
           stateInput.sendKeys("CA");
           
        // Locate the SVG element using its data-testid attribute
           WebElement eastIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='EastIcon']")));

           // Click the SVG element
           eastIcon.click();
           Thread.sleep(1000);
           
//        // Locate the SVG element using its data-testid attribute
//           WebElement checkBoxOutlineBlankIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='CheckBoxOutlineBlankIcon']")));
//
//           // Click the SVG element
//           checkBoxOutlineBlankIcon.click();
           
           
           
//           // Select the "Normal" type (assuming it's the first option)
//           WebElement checkBoxOutlineBlankIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[2]/div[1]/label/span[1]/input")));
//           checkBoxOutlineBlankIcon.click();
           
        // Locate the checkbox input element
           WebElement checkBoxInput = driver.findElement(By.cssSelector("input[type='checkbox']"));

           // Click the checkbox using JavaScript
           ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkBoxInput);
           
        // Wait until the "Done" button is clickable
           WebElement doneButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[3]/div/div[2]/div[2]/button[1]")));

           // Click the "Done" button
           doneButton.click();

           // Wait for 5 seconds to allow any action or page load
           Thread.sleep(8000);  // This is a simple wait; you might want to use a more sophisticated wait if there's a specific condition

           // Click the "Done" button again
           doneButton.click();

//
//            
//        // Wait for the DoneIcon button to be clickable and click it
//           WebElement doneIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='DoneIcon']")));
//           doneIcon.click();
//
//           // Wait for the DoneIcon button to be clickable again (after any potential state change)
//           WebElement doneIconAgain = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='DoneIcon']")));
//           doneIconAgain.click();
            
           
//           WebElement DoneIcon1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='DoneIcon']")));
//           DoneIcon1.click();
//           
////           WebElement DoneIcon2 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='DoneIcon']")));
////           DoneIcon2.click();
//
//            
         // Select the "Normal" type (assuming it's the first option)
            WebElement DoneIcon2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[2]/button[1]")));
            DoneIcon2.click();
//            
//         // Select the "Normal" type (assuming it's the first option)
//            WebElement DoneIcon3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[2]/button[1]")));
//            DoneIcon3.click();
//            
            // Select the "Normal" type (assuming it's the first option)
            WebElement Doneok = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[3]/div/div[2]/button[1]")));
            Doneok.click();
//            
//            // Select the "Normal" type (assuming it's the first option)
//            WebElement finish = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div[2]/button[1]")));
//            finish.click();
//            
            WebElement personicon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[3]/div/div[1]/table/tbody/tr[1]/td[2]/button")));
            personicon.click();
////            
            WebElement uploadButton = driver.findElement(By.xpath("/html/body/form[2]/div[3]/div/div[2]/div/div[2]/label/span"));
            uploadButton.click();
//
//            // Locate the file input element and upload the file
            WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
            String filePath = "D:\\selenium\\Johnathan.jpg";
            fileInput.sendKeys(filePath);
//
//            // Wait for the file to be processed (adjust the wait as necessary)
            Thread.sleep(2000); // Adjust the sleep time based on your application's behavior
            
         // Click the "Done" button
            WebElement doneButton2 = driver.findElement(By.xpath("/html/body/form[2]/div[3]/div/div[2]/div/div[2]/button[1]"));
            doneButton2.click();
            
            WebElement okButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
            System.out.println("Clicking OK button");
            okButton1.click();

//          
       // Locate the SVG element with data-testid='BorderColorIcon'
          WebElement borderColorIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='BorderColorIcon']")));

          // Click on the BorderColorIcon SVG element
          borderColorIcon.click();
          
          Thread.sleep(5000);
         
//       // Locate the canvas element
//          WebElement canvas = driver.findElement(By.xpath("/html/body/form[2]/div[3]/div/div[1]/div/div/div/canvas"));
//
//          String script = "var canvas = arguments[0];" +
//                  "var context = canvas.getContext('2d');" +
//                  "context.beginPath();" +
//                  // Draw the left side of the U
//                  "context.moveTo(50, 50);" +
//                  "context.arcTo(50, 150, 150, 150, 50);" +
//                  // Draw the right side of the U
//                  "context.arcTo(150, 150, 150, 50, 50);" +
//                  "context.stroke();";
//
//     ((JavascriptExecutor) driver).executeScript(script, canvas);
          
          WebElement DoneIcon3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form[2]/div[3]/div/div[2]/div/button[2]")));
          DoneIcon3.click();
          
          WebElement okButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
          System.out.println("Clicking OK button");
          okButton2.click();
          
          WebElement DoneIcon4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[3]/div/div[2]/button[1]")));
          DoneIcon4.click();

        } catch (Exception e) {
            screenshotUtil.takeScreenshotWithDate("fillCustomerForm_error");
            throw e; // Re-throw the exception to handle it elsewhere if needed
        }
    }

    public static void main(String[] args) {
        // Setup WebDriver (e.g., ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        
        // Create Config and ScreenshotUtils instances
        Config config = new Config();
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);

        try {
            // Open the application URL
            driver.get("http://172.16.128.33:3000/");

            // Create an instance of CustomerFormTest
            AddCustomer customerFormTest = new AddCustomer(driver, config, screenshotUtil);

            // Login as Master with username and password
            customerFormTest.loginAsMaster("master", "P@ssw0rd");

            // Click Customer and New Customer
            customerFormTest.clickCustomerButton();
            customerFormTest.clickNewCustomer();

            // Fill out the customer form
            customerFormTest.fillCustomerForm();

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
      } 
 //           finally {
//            // Clean up and close the browser
//            driver.quit();
//        }
    }
}
