package qa.test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.AppiumDriver;
import qa.base.BaseTest;
import qa.pages.LoginPage;
import qa.pages.ProductDetailsPage;
import qa.pages.ProductPage;
import qa.utils.Utilities;

public class ProductTests {
	LoginPage lp;
	ProductPage pp ;
	BaseTest base ;
	AppiumDriver driver;
	JSONObject jsonObject;
	Map<String, String> map;
	
	@Parameters({"platformName","newCommandTimeout"})
	@BeforeClass
	public void initDriver( @Optional("Android")String platformName,  @Optional("4000")String newCommandTimeout) throws MalformedURLException {
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
	
	@BeforeMethod()
	public void before_Method(Method m) {
		lp = new LoginPage(driver);
		jsonObject=new Utilities().getTestDataFromJson();
		System.out.println("Running TestCase Name "+m.getName());
		
	}
	@AfterMethod
	public void after_Method() {
		
	}
	
	
	@Test(enabled = false)
	public void validateProductInProductPage() {
		String user = jsonObject.getJSONObject("validUser").getString("username");
		String pwd = jsonObject.getJSONObject("validUser").getString("password");
		pp=lp.doLogin(user, pwd);
		String title=pp.getSLBTitle();
		String price = pp.getSLBPrice();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(title, map.get("products_page_slb_title"));
		sa.assertEquals(price, map.get("products_page_slb_price"));	
		sa.assertAll();
		base.perfromLogout();
	}
	
	@Test(enabled = false)
	public void validateProductInProductDetailsPage() {
		String user = jsonObject.getJSONObject("validUser").getString("username");
		String pwd = jsonObject.getJSONObject("validUser").getString("password");
		pp=lp.doLogin(user, pwd);
		ProductDetailsPage pdp=pp.clickOnSLBProduct();
		String str=pdp.getText();
		//String itemPrice=pdp.scroll();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(str, map.get("products_page_slb_title"));
		//sa.assertEquals(itemPrice,map.get("products_page_slb_price"));
		sa.assertAll();
		base.perfromLogout();
	}

}
