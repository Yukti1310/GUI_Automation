package TestCases;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleTestIT {

	static WebDriver driver;

	@Test
	public void i_have_the_Chrome() throws IOException 
	{
		String projectPath = System.getProperty("user.dir");
		System.out.println("Project Path :: " + projectPath);
		String url ="https://www.google.com/?gws_rd=ssl";
		System.out.println("Before URL");
	//	System.setProperty("webdriver.chrome.driver","./Driver/Chrome/chromedriver.exe");

		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--disable-extensions");
		options.addArguments("--proxy-server='direct://'");
		options.addArguments("--proxy-bypass-list=*");
		options.addArguments("--start-maximized");
		options.addArguments("---disable-gpu");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--no-sandbox");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--allow-running-insecure-content");
		driver = new ChromeDriver(options);
		ScreenShot("a1");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Before URL 2");
		ScreenShot("a2");
		driver.get(url);	
		ScreenShot("a3");
		System.out.println("Before URL 3");
		i_use_the_search("Facebook wiki");
		i_click_on_wiki();

	}


	public void i_use_the_search(String arg1) throws IOException 
	{
		System.out.println("Before URL 4");
		driver.findElement(By.name("q")).sendKeys(arg1);
		driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
		ScreenShot("a4");
	}


	public void i_click_on_wiki() throws IOException 
	{
		System.out.println("Before URL 5");
		driver.findElement(By.xpath("//*[@class='LC20lb DKV0Md']")).click();
		System.out.println("completed test");
		ScreenShot("a5");
	}

	public static void ScreenShot(String image) throws IOException
	{
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File scr= scrShot.getScreenshotAs(OutputType.FILE);
		String d = "./Screenshot/"+image+".png";
		File dest = new File(d);
		FileUtils.copyFile(scr, dest);
	}

}
