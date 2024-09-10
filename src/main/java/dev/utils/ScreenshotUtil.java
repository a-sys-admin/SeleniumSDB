package dev.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    private WebDriver driver;

    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshotWithDate(String screenshotName) {
        // Create the screenshot file
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Create date and time-stamped directory path
        String dateName = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String timeName = new SimpleDateFormat("HH-mm-ss").format(new Date());
        String dirPath = "reports" + File.separator + "screenshots" + File.separator + dateName + File.separator + timeName;
        File dir = new File(dirPath);

        // Create the directories if they do not exist
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directories created: " + dirPath);
            } else {
                System.out.println("Failed to create directories: " + dirPath);
            }
        }

        // Define the file name and path
        String fileName = dirPath + File.separator + screenshotName + ".jpg";
        File destFile = new File(fileName);

        try {
            // Copy the screenshot file to the destination
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved as: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
