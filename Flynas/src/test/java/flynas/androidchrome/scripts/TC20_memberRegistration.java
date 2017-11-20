package flynas.androidchrome.scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC20_memberRegistration extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"FL_WEB_20");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_20_memberRegistration( String Password,String Nationality,String DocumentType,String DocNumber,String Mobile,
			String EmailAddress,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			click(BookingPageLocators.login_lnk, "Agency Login");
			waitforElement(BookingPageLocators.here);
			click(BookingPageLocators.here, "Creat an Account Here");
			memberRegistration(Password,Nationality,DocumentType,DocNumber,Mobile,EmailAddress);
			verifingMemberRegistration();
			waitUtilElementhasAttribute(BookingPageLocators.body);
			click(BookingPageLocators.myProfile, "myProfile");
			waitforElement(BookingPageLocators.changpwdbtn);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			click(BookingPageLocators.changpwdbtn, "Change Password");
			type(BookingPageLocators.currentpwd, Password, "Current Password");
			String newpwd= randomString();
			type(BookingPageLocators.newpwd,newpwd.trim() , "New Password");
			type(BookingPageLocators.cnfmnewpwd, newpwd.trim(), "Confirm New Password");
			click(BookingPageLocators.ConfirmPwdBtn, "Confirm");
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.pwdChngeConmtn)==true){
				Reporter.SuccessReport("Verifing Password Change", "Successfully Changed");
				click(BookingPageLocators.ok, "OK");
			}
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(BookingPageLocators.naSmile_myProfile));
			if(driver.findElement(BookingPageLocators.naSmile_myProfile).isEnabled()==false)
			{
				Reporter.SuccessReport("Verifing naSmile is readable In myProfile", "Successfully Verified");
			}
			/*type(BookingPageLocators.naSmile_myProfile, randomNumericString(), "naSmile");
			Thread.sleep(3000);
			click(BookingPageLocators.updateBtn_myProfile,"Update");
			Thread.sleep(3000);
			if(isElementPresent(BookingPageLocators.error_myProfile)==true)
			{
				Reporter.SuccessReport("Verifing Error Message after editing naSmile In myProfile", "Successfully Verified");
			}
			*/	
						
			Reporter.SuccessReport("TC20_memberRegistration", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.SuccessReport("TC20_memberRegistration", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    		xls.getCellValue("Password", "Value"),
	    		xls.getCellValue("Nationality", "Value"),
	    		xls.getCellValue("Document Type", "Value"),
	    		xls.getCellValue("Doc Number", "Value"),
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Email Address", "Value"),
	    	
	    		"Validate Member Login"}};
	}

}
