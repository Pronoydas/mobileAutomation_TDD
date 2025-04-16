package qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import qa.base.BasePage;
import qa.base.BaseTest;

public class ProductPage  {
	AppiumDriver driver;
	BaseTest base;
	
	@AndroidFindBy(uiAutomator  = "new UiSelector().className(\"android.widget.ImageView\").instance(2)")
	private WebElement productBanner;
	
	//new UiSelector().text("Sauce Labs Backpack")
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Sauce Labs Backpack\")") 
	private WebElement SLBTitle;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"$29.99\")") 
	private WebElement SLBPrice;
	
	
	public ProductPage(AppiumDriver driver) {
		this.driver= driver;
		base= new BaseTest();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	public boolean isElementDisplay() {
		return productBanner.isDisplayed();
	}
	
	public String getSLBTitle() {
		return SLBTitle.getText();
	}
	
	public String getSLBPrice() {
		return SLBPrice.getText();
	}
	
	public ProductDetailsPage clickOnSLBProduct() {
		base.click(SLBTitle);
		return new ProductDetailsPage(driver);
	}

}
