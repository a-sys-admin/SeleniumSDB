@echo off
REM Change directory to where the pom.xml is located
cd /d C:\ProgramData\Jenkins\.jenkins\workspace\Selenium\NewBox-Add\NewBoxAddApproveTest

REM Set the ChromeDriver path
set CHROME_DRIVER_PATH=%CHROME_DRIVER_PATH%

REM Set the CLASSPATH to include the lib directory
set CLASSPATH=%CLASSPATH%;C:\ProgramData\Jenkins\.jenkins\workspace\Selenium\NewBox-Add\samples\lib

REM Run the Maven command with the specified test class
mvn -f pom.xml -Dtest=dev.tests.CashPaymentTestclass test


