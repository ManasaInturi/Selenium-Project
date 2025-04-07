package  pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.ReusableComponents;

public class LandingPage extends ReusableComponents{

	WebDriver driver;
	String priceItem1;
	String priceItem2;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	    PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="(//div[@class='inventory_list'])[1]")
	WebElement inventoryList;
	@FindBy(xpath="//div[@class='inventory_list']/div[1]/div[2]/div[1]/a/div")
	WebElement firstItem;
	@FindBy(xpath="//div[@class='inventory_list']/div[1]/div[2]/div[2]/div")
	WebElement priceFirstItem;
	@FindBy(xpath="//div[@class='inventory_list']/div[2]/div[2]/div[1]/a/div")
	WebElement secondItem;
	@FindBy(xpath="//div[@class='inventory_list']/div[2]/div[2]/div[2]/div")
	WebElement priceSecondItem;
	
	@FindBy(xpath="(//div[@class='inventory_list'])[1]/div[1]/div[2]/div[2]/button")
	WebElement item1AddToCartButton;
	@FindBy(xpath="(//button[normalize-space()='Remove'])[1]")
	WebElement item1AddToCartRemoveBtn;
	@FindBy(xpath="(//div[@class='inventory_list'])[1]/div[2]/div[2]/div[2]/button")
	WebElement item2AddToCartButton;
	@FindBy(xpath="(//button[normalize-space()='Remove'])[2]")
	WebElement item2AddToCartRemoveBtn;
	
	public void waitForInventoryListToBeAppear() {
		waitForWebElementToBeVisible(inventoryList);
		return;
	}
	
	public String firstItemName() {
		String firstItemName = firstItem.getText();
		return firstItemName;
	}
	
	public String secondItemName() {
		String secondItemName = secondItem.getText();
		return secondItemName;
	}
	
	public String firstItemPrice() {
		String firstItemPrice = priceFirstItem.getText();
	    priceItem1 = firstItemPrice.split("$")[1].trim();
		return priceItem1;
	}
	
	public String secondItemPrice() {
		
		String secondItemPrice = priceSecondItem.getText();
		priceItem2 = secondItemPrice.split("$")[1].trim();
		return priceItem2;
	}
	
	public String totPriceOfItemsSelected() {	
		
		String price = priceItem1 + priceItem2;
		return price;
	}
	
	public void addItemsToCart() {
		item1AddToCartButton.click();
		waitForWebElementToBeVisible(item1AddToCartRemoveBtn);
		item2AddToCartButton.click();
		waitForWebElementToBeVisible(item2AddToCartRemoveBtn);
		return;
	}
	
}
