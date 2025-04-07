package Tests;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import Utils.DataReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import pageObjects.LoginPage;

public class LoginTest extends BaseTest{

	
	@Test(dataProvider = "getData")
	public void loginAppTest(HashMap<String,String> input) throws IOException, InterruptedException {

		LoginPage lp = new LoginPage(driver);
		lp.LoginApp(input.get("username"), input.get("password"));
	}	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		DataReader dr = new DataReader();
		List<HashMap<String, String>> data= dr.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\main\\java\\TestData\\LoginData.json");
		
		return new Object[][] {{data.get(0)}};
	}
	
}
