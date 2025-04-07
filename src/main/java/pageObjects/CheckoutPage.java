package  pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utils.ReusableComponents;

public class CheckoutPage extends ReusableComponents{

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	    PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[@id='checkout']")
	WebElement checkoutButton;
	@FindBy(xpath="//input[@id='first-name']")
	WebElement firstNameField;
	@FindBy(xpath="//input[@id='last-name']")
	WebElement lastNameField;
	@FindBy(xpath="//input[@id='postal-code']")
	WebElement postalCodeField;
	@FindBy(xpath="(//input[@id='continue'])[1]")
	WebElement continueButton;
	@FindBy(xpath="(//span[@class='title'])[1]")
	WebElement overviewTitle;
	
	public void clickOnCheckoutBtn() {
		checkoutButton.click();
	}
	
	public void enterDetailsAndContinue() {
		firstNameField.sendKeys("John");
		lastNameField.sendKeys("Doe");
		postalCodeField.sendKeys("12345");
		continueButton.click();
	}
	public void waitForOverviewPage() {
		waitForWebElementToBeVisible(overviewTitle);
	}
	
}
