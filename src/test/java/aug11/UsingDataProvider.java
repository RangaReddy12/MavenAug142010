package aug11;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class UsingDataProvider {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
@BeforeTest
 public void beforeTest() 
{
	report=new ExtentReports("./ExtentReports/Dataprovider.html");
	driver=new ChromeDriver();
}	
  @Test(dataProvider = "supplyData")
  public void valiadateLogin(String username,String password) throws Throwable
  {
	  test=report.startTest("Verify Login");
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().window().maximize();
	driver.findElement(By.name("txtUsername")).sendKeys(username);
	driver.findElement(By.name("txtPassword")).sendKeys(password);
	driver.findElement(By.name("Submit")).sendKeys(Keys.ENTER);
	Thread.sleep(3000);
	if(driver.getCurrentUrl().contains("dash"))
	{
		Reporter.log("Login Success",true);
		test.log(LogStatus.PASS, "Login Success");
	}
	else
	{
		Reporter.log("Login Fail",true);
		test.log(LogStatus.FAIL, "Login Fail");
	}
  }

  @DataProvider
  public Object[][] supplyData() {
    //store login data using object array
	Object login[][]= new Object[4][2];//four rows two columns
	login[0][0]="Admin";
	login[0][1]="Qedge123!@#";
	login[1][0]="Test";
	login[1][1]="Qedge123!@#";
	login[2][0]="Admin";
	login[2][1]="test";
	login[3][0]="Admin";
	login[3][1]="Qedge123!@#";
	return login;		
  }
  @AfterTest
  public void afterTest() 
  {
	  report.endTest(test);
	  report.flush();
	  driver.close();
  }

}
