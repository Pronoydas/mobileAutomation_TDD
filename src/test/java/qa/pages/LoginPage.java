package qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import qa.base.BasePage;
import qa.base.BaseTest;



public class LoginPage {
	BaseTest base;
	AppiumDriver driver;
	

	
	@AndroidFindBy(accessibility  = "test-Username")
	private WebElement userName;
	
	@AndroidFindBy(accessibility = "test-Password")
	private WebElement password ;
	
	@AndroidFindBy(accessibility = "test-LOGIN")
	private WebElement loginBtn ;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Username and password do not match any user in this service.\")" )
	private WebElement errorMsg;
	
	
	public LoginPage(AppiumDriver driver) {
		this.driver=driver;
		base=new BaseTest();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public LoginPage enterUserName(String name) {
		base.sendKeys(userName, name);
		return this;
	}
	
	
	public LoginPage enterPassword(String passd) {
		base.sendKeys(password, passd);
		return this;
	}
	
	public ProductPage clickLoginBtn() {
		base.click(loginBtn);
		return new ProductPage(driver);
	}
	
	
	public String getErrTxt() {
		return base.getAttribute(errorMsg, "text");
	}
	
	public ProductPage doLogin(String userName , String password) {
		enterUserName(userName);
		enterPassword(password);
		return clickLoginBtn();
		
	}

	
	
	
	
	
	

}
