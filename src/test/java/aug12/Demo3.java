package aug12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Demo3 {

	public static void main(String[] args) {
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.icicidirect.com/");
		driver.manage().window().maximize();
WebDriverWait wait= new WebDriverWait(driver, 400);		
//click on three links
wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Marke")));
		driver.findElement(By.partialLinkText("Marke")).click();
wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Market Sta")));
		driver.findElement(By.partialLinkText("Market Sta")).click();
wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Daily Share Pric")));
		driver.findElement(By.partialLinkText("Daily Share Pric")).click();
	}

}
