package qa.mobile;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Test1 {
	
	private AppiumDriver driver =null;
	
	
	@BeforeSuite (alwaysRun = true)
	public void setup() throws Exception {
		System.out.println(new Throwable().getStackTrace()[0].getClassName());
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("appium:automationName", "uiautomator2");
		dc.setCapability("appium:platformName", "android");
		dc.setCapability("appium:udid", "RZ8M934BAQH");
		dc.setCapability("appium:newCommandTimeout", 4000);
		//dc.setCapability("appium:appPackage", "com.swaglabsmobileapp");
		//dc.setCapability("appium:appActivity", "com.swaglabsmobileapp/.MainActivity t1405");
		dc.setCapability("appium:unlockType", "pin");		dc.setCapability("appium:unlockKey", "2580");
		
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), dc);
		
		if(((AndroidDriver)driver).isDeviceLocked()) {
			((AndroidDriver)driver).unlockDevice();
		}
		((AndroidDriver)driver).activateApp("com.swaglabsmobileapp");	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	@Test
	public void loginWithInvalidUsername() throws Exception {
		//Thread.sleep(5000);
		WebElement userName=driver.findElement(AppiumBy.accessibilityId("test-Username"));
		WebElement password=driver.findElement(AppiumBy.accessibilityId("test-Password"));
		WebElement loginBtn=driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
		userName.sendKeys("AdminLogin");
		password.sendKeys("secret_sauce");
		loginBtn.click();
		WebElement errorMsg=driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Username and password do not match any user in this service.\")"));
        System.out.println(errorMsg.getDomAttribute("text")); 
	}
	@Test
	public void validLogin() throws Exception {
		//Thread.sleep(5000);
		WebElement userName=driver.findElement(AppiumBy.accessibilityId("test-Username"));
		WebElement password=driver.findElement(AppiumBy.accessibilityId("test-Password"));
		WebElement loginBtn=driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
		userName.clear();
		password.clear();
		userName.sendKeys("standard_user");
		password.sendKeys("secret_sauce");
		loginBtn.click();
		WebElement errorMsg=driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(2)"));
        System.out.println(errorMsg.isDisplayed()); 
	}
	@AfterSuite
	public void tearDown() {
		driver.quit();
		
	}
	

}
