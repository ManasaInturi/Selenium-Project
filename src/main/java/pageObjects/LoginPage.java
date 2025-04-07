package  pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utils.ReusableComponents;

public class LoginPage extends ReusableComponents{

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	    PageFactory.initElements(driver, this);
	}
		
	@FindBy(id="user-name")
	WebElement user;
	@FindBy(id="password")
	WebElement pwd;
	@FindBy(id="login-button")
	WebElement submit;
	
	
	public void LoginApp(String username, String password) {
		user.sendKeys(username);
		pwd.sendKeys(password);
		submit.click();
	}
	
}
