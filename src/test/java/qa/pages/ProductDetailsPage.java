package qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import qa.base.BaseTest;

public class ProductDetailsPage {
	
	private AppiumDriver driver;
	private BaseTest base;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Sauce Labs Backpack\")") 
	private WebElement SLBTitle;
	
	@AndroidFindBy(accessibility = "test-BACK TO PRODUCTS") 
	private WebElement backToproductBtn;
	
	@AndroidFindBy(accessibility = "test-Price") 
	private WebElement SLBItemPrice;
	
	
	
	
	public ProductDetailsPage(AppiumDriver driver) {
		this.driver= driver;
		base= new BaseTest();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public ProductPage clickOnProductBackBtn() {
		base.click(backToproductBtn);
		return new ProductPage(driver);
	}
	
	public String getText() {
		return SLBTitle.getText();
	}
	
//	public String scroll() {
//		return base.scrollTOElement(driver).getText();
//	}

}
