package qa.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import qa.pages.SettingsPage;

public  class MenuPage {
	BaseTest base;
	AppiumDriver driver;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(1)")
	private WebElement menuBtn;
	
	
	public MenuPage (AppiumDriver driver) {
		this.driver = driver;
		base = new BaseTest();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
//	
	public  SettingsPage clickOnMenu() {
		base.click(menuBtn);
		return new SettingsPage(driver);
	}

}
