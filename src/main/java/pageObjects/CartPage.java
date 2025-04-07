package  pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import Utils.ReusableComponents;

public class CartPage extends ReusableComponents{

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	    PageFactory.initElements(driver, this);
	}
		
	
	@FindBy(id="[class='shopping_cart_badge']")
	WebElement shoppingCart;
	@FindBy(xpath="(//div[@class='cart_list'])[1]")
	WebElement cartList;
	
	
	
	public void clickOnShoppingCart() {
		
		shoppingCart.click();	
	}
	
	public void verifyNoOfItemsOnCartList() {
		waitForWebElementToBeVisible(cartList);
		Assert.assertEquals(cartList.getSize(), "2");
	}
	
}
