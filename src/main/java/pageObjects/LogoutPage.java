package  pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utils.ReusableComponents;

public class LogoutPage extends ReusableComponents{

	WebDriver driver;
	
	public LogoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	    PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//button[@id='finish']")
	WebElement finishButton;
	@FindBy(xpath="(//a[normalize-space()='Logout'])[1]")
	WebElement logoutOption;
	@FindBy(xpath="(//input[@id='login-button'])[1]")
	WebElement loginButton;
	
	public void clickOnFinish() {
		finishButton.click();
	}
	
	public void logout() {
		logoutOption.click();
	}
	
	public void verifyLoginPageAppears() {
		waitForWebElementToBeVisible(loginButton);
		
		
	}
}
