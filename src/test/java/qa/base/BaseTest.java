package qa.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import qa.utils.Utilities;

public class BaseTest {
	
	private static  AppiumDriver driver;
	
	
	public void driverSetup(String platformName , String newCommandTimeout ) throws MalformedURLException {
		Properties prop=Utilities.loadProperites();
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("appium:automationName", prop.getProperty("automationName"));
		dc.setCapability("platformName", platformName);
		dc.setCapability("appium:udid", prop.getProperty("udid"));
		String appPath=System.getProperty("user.dir")+prop.getProperty("app");
		//dc.setCapability("appium:app", appPath);
		dc.setCapability("appium:newCommandTimeout", Integer.parseInt(newCommandTimeout));
		dc.setCapability("appium:appPackage", prop.getProperty("appPackage"));
		dc.setCapability("appium:appActivity", prop.getProperty("appActivity"));
		dc.setCapability("appium:unlockType", prop.getProperty("unlockType"));
		dc.setCapability("appium:unlockKey", prop.getProperty("unlockKey"));
		URL serverUrl = new URL(prop.getProperty("url"));
		driver = new AppiumDriver(serverUrl, dc);
		
//		if(((AndroidDriver)driver).isDeviceLocked()) {
//			((AndroidDriver)driver).unlockDevice();
//		}
		
	}
	
	public  AppiumDriver getDriver() {
		return driver;
	}
	
	private void elementVisibility(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	
	public void click(WebElement e) {
		elementVisibility(e);
		e.click();
	}
	
	public void sendKeys(WebElement e,String txt) {
		elementVisibility(e);
		e.click();
		e.sendKeys(txt);
	}
	
	public String getAttribute(WebElement e,String attribute) {
		elementVisibility(e);
		return e.getDomAttribute(attribute);
	}
	
	public void quitDriver() {
		driver.quit();
	}
	
	public void perfromLogout() {
		new MenuPage(driver).clickOnMenu().clickOnLogout();
		
	}
	//new UiSelector().description("test-Inventory item page")
	
	public WebElement scrollTOElement(AppiumDriver driver) {
		 return getDriver().findElement(AppiumBy.androidUIAutomator(
				  "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
						  + "new UiSelector().description(\"test-Price\"));"));

	}
	
	public  void takeScreenshort(String testName) throws Exception {
		File imageDir = new File("screenshort");
		if(!imageDir.exists()) {
			imageDir.mkdirs();
		}
		System.out.println(driver);
		
		String imagePath = System.getProperty("user.dir")+"//screenshort//"+testName+"//"+UUID.randomUUID().toString()+".png";
		System.out.println(imagePath);
		File image=driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(image, new File(imagePath));
		Reporter.log("This is the sample Screen short");
		Reporter.log("<a href='"+ imagePath + "'> <img src='"+ imagePath + "' height='400' width='400'/> </a>");

		
	}
	


	
	
	

}
