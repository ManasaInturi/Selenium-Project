package Tests;

import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import Utils.DataReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import pageObjects.LandingPage;
import pageObjects.LoginPage;

public class addItemsToCartTest extends BaseTest{

	WebDriverWait wait;
	String totalPrice;

	
	@Test(dataProvider = "getData")
	public void addItems(HashMap<String,String> input) throws IOException, InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.LoginApp(input.get("username"), input.get("password"));
	
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		boolean presentFlag = false;

		  try {

		   // Check the presence of alert
		   Alert alert = driver.switchTo().alert();
		   // Alert present; set the flag
		   presentFlag = true;
		   // if present consume the alert
		   alert.accept();

		  } catch (NoAlertPresentException ex) {
		   // Alert not present
		   ex.printStackTrace();
		  }
//		wait.until(ExpectedConditions.alertIsPresent());
//		driver.switchTo().alert().accept();
		
		
		LandingPage landingPage = new LandingPage(driver);
		//Verify the prescence of inventory list
		landingPage.waitForInventoryListToBeAppear();
				
		String firstItemName = landingPage.firstItemName();
		System.out.println("The first item name on the cart list is "+ firstItemName);
		String secondItemName = landingPage.secondItemName();
		System.out.println("The second item name on the cart list is "+ secondItemName);
		totalPrice = landingPage.totPriceOfItemsSelected();
		
		//Adding first two intems to the cart
		landingPage.addItemsToCart();
		
	
	}
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		DataReader dr = new DataReader();
		List<HashMap<String, String>> data= dr.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\main\\java\\TestData\\LoginData.json");
		
		return new Object[][] {{data.get(0)}};
	}
	
}
