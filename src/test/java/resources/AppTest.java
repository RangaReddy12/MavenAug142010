package resources;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExcelFileUtil;

public class AppTest {
WebDriver driver;
ExtentReports report;
ExtentTest test;
String inputpath="E://Logindata.xlsx";
String outputpath="E://Results.xlsx";
@BeforeTest
public void setUp()
{
	report=new ExtentReports("./ExtenReports/Report.html");
	driver=new ChromeDriver();
}
@Test
public void verifyLogin()throws Throwable
{
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	//count no of row
	int rc=xl.rowCount("Login");
	Reporter.log("No of rowsr are::"+rc);
	for(int i=1;i<=rc;i++)
	{
	test=report.startTest("Validate Login");
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().window().maximize();
	String username=xl.getCellData("Login", i, 0);
	String password=xl.getCellData("Login", i, 1);
	driver.findElement(By.name("txtUsername")).sendKeys(username);
	driver.findElement(By.name("txtPassword")).sendKeys(password);
	driver.findElement(By.name("Submit")).sendKeys(Keys.ENTER);
	Thread.sleep(4000);
	if(driver.getCurrentUrl().contains("dash"))
	{
		Reporter.log("Login Success",true);
		xl.setCellData("Login", i, 2, "Login Success", outputpath);
		xl.setCellData("Login", i, 3, "Pass", outputpath);
		test.log(LogStatus.PASS, "Login Success");
	}
	else
	{
	//capture error message
		String message=driver.findElement(By.id("spanMessage")).getText();
		Reporter.log(message,true);
		xl.setCellData("Login", i, 2, message, outputpath);
		xl.setCellData("Login", i, 3, "Fail", outputpath);
		test.log(LogStatus.FAIL, message);
	}
	report.endTest(test);
	report.flush();
	}
}
@AfterTest
public void tearDown() {
	driver.close();
}
}













