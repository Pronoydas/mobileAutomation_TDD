package qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import qa.base.BaseTest;

public class SettingsPage {
	
	BaseTest base;
	AppiumDriver driver;
	
	
	@AndroidFindBy(accessibility = "test-LOGOUT")
	private WebElement logoutBtn;
	
	
	public SettingsPage (AppiumDriver driver) {
		this.driver = driver;
		base = new BaseTest();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	public void clickOnLogout() {
		base.click(logoutBtn);
	}
	
	

}
