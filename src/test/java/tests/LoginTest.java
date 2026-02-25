package tests;
import base.BaseTest;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.log;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
public class LoginTest extends BaseTest {
	
	
	@DataProvider(name="LoginData")
	public Object[][] getLoginData() throws IOException{
		
		String filePath = System.getProperty("user.dir")+"/testdata/Logindata.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount-1][2];
		
		for(int i=1; i<rowCount; i++) {
			
			data[i-1][0] = ExcelUtils.getCellData(i, 0);	// Username
			data[i-1][1] = ExcelUtils.getCellData(i, 1);	// Password
		}
		ExcelUtils.closeExcel();
		return data;
	}
	
	@DataProvider(name="LoginData2")
	public Object[][] geData()
	{
		return new Object[][]
				{
			{"user1","pass1"},
			{"admin@yourstore.com","admin"},
			{"user3","pass3"}
		
				};
	}
	//(dataProvider = "LoginData2")
    @Test 
    @Parameters({"username","password"})
    public void testValidLogin(String username,String password) throws InterruptedException {
    	
    	log.info("Starting login test");
    	test = ExtentReportManager.createTest("LoginTest");
    	test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);
        log.info("Adding Credentials");
        test.info("Adding Credentials");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
       // loginPage.enterUsername("admin@yourstore.com");
        //loginPage.enterPassword("admin");
        loginPage.clickLogin();
        log.info("Verifying Page title");
        test.info("Verifying Page title");
        Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
        test.pass("Login Successful");
    }
    
    @Test
    
 public void testInvalidLogin() throws InterruptedException {
    	
    	log.info("Starting login test");
    	test = ExtentReportManager.createTest("LoginTest with Invalid credentials");
    	test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);
        log.info("Adding Credentials");
        test.info("Adding Credentials");
        loginPage.enterUsername("usertest");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        log.info("Verifying Page title");
        test.info("Verifying Page title");
        Assert.assertNotEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
        test.pass("Login Failed due to invalid credential");
    }
}


