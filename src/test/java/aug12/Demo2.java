package aug12;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo2 {

	public static void main(String[] args) {
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.icicidirect.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//click on three links
		driver.findElement(By.partialLinkText("Marke")).click();
		driver.findElement(By.partialLinkText("Market Sta")).click();
		driver.findElement(By.partialLinkText("Daily Share Pric")).click();
	}

}
