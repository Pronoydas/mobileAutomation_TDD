package qa.test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;

import org.json.JSONObject;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import qa.base.BaseTest;
import qa.pages.LoginPage;
import qa.pages.ProductPage;
import qa.utils.Utilities;

//@Listeners(qa.listner.TestListner.class)
public class LoginTest {
	LoginPage lp;
	ProductPage pp ;
	BaseTest base ;
	AppiumDriver driver;
	JSONObject jsonObject;
	Map<String, String> map;
	
	@Parameters({"platformName","newCommandTimeout"})
	@BeforeClass
	public void initDriver(@Optional("Android")String platformName,  @Optional("4000")String newCommandTimeout) throws MalformedURLException {
		base = new BaseTest();
		base.driverSetup(platformName, newCommandTimeout);
		driver = base.getDriver();
		try {
			map = new Utilities().parseStringXML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void tearDown() {
		base.quitDriver();
		
	}
	
	@BeforeMethod
	public void before_Method(Method m) {
		lp = new LoginPage(driver);
		jsonObject=new Utilities().getTestDataFromJson();
		System.out.println("Running TestCase Name "+m.getName());
		((CanRecordScreen)(AndroidDriver)driver).startRecordingScreen();
		
	}
	@AfterMethod
	public void after_Method(ITestResult result) {
		String record=((CanRecordScreen)(AndroidDriver)driver).stopRecordingScreen();
		String dir = "videos"+File.separator +LocalDateTime.now().toString() + result.getTestClass().getClass().getSimpleName();
		File videoDir= new File(dir);
		if(!videoDir.exists()) {
			videoDir.mkdirs();
		}
		try {
			FileOutputStream fis = new FileOutputStream(dir+File.separator+result.getName()+".mp4");
			fis.write(Base64.getDecoder().decode(record));
			fis.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		
	}
	
	
	@Test(description = "Login With Invalid Details")
	public void loginWithInvalidUsername() throws Exception {
		lp.enterUserName(jsonObject.getJSONObject("invalidUser").getString("username"));
		lp.enterPassword(jsonObject.getJSONObject("invalidUser").getString("password"));
		lp.clickLoginBtn();
		assertEquals(lp.getErrTxt()+"f", map.get("err_invalid_username_or_password"));
	}
	@Test(description = "Login With Valid Details" ,enabled = false)
	public void validLogin() throws Exception {
		lp.enterUserName(jsonObject.getJSONObject("validUser").getString("username"));
		lp.enterPassword(jsonObject.getJSONObject("validUser").getString("password"));
		pp=lp.clickLoginBtn();
		boolean bb=pp.isElementDisplay();
		System.out.println("Is Display "+ bb);
		 
	}

}
