package flynas.web.uat.regression;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC22_resetPasswordusingmyProfile extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"FL_WEB_22");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_22_resetPasswordusingmyProfile( String Password,String Nationality,String DocumentType,
			String DocNumber,
			String Mobile,
			String EmailAddress,
			String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			
			click(BookingPageLocators.login_lnk, " Login");
			waitUtilElementhasAttribute(BookingPageLocators.body);
			waitforElement(BookingPageLocators.JoinNow);
			click(BookingPageLocators.JoinNow, "Join now button");
			String email=memberRegistration(Password,Nationality,DocumentType,DocNumber,Mobile,EmailAddress);
			xls.setCellData("FL_WEB_22", "Value", 7, email);
			verifingMemberRegistration();
			waitforElement(BookingPageLocators.myProfile);
			click(BookingPageLocators.myProfile, "myProfile");
			String newPwd = RandomStringUtils.random(8, true, true);
			xls.setCellData("FL_WEB_22", "Value", 8, newPwd);
			waitforElement(BookingPageLocators.changeMyPwdBtn);
			click(BookingPageLocators.changeMyPwdBtn, "Change My Password");
			waitforElement(BookingPageLocators.currentpwd);
			type(BookingPageLocators.currentpwd, Password, "Current Password");
			type(BookingPageLocators.newpwd, newPwd, "New Password");
			type(BookingPageLocators.cnfmnewPwd, newPwd, "Conform New Password");
			click(BookingPageLocators.ConfirmPwdBtn, "Confirm");
			waitforElement(BookingPageLocators.pwdChngeComnt);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			click(BookingPageLocators.ok, "OK");
			click(BookingPageLocators.update_Btn, "Update");
			verifingProfileUpdatemessage();
			click(BookingPageLocators.ok, "OK");
			click(BookingPageLocators.logout_lnk, "Logout");
			click(BookingPageLocators.ok, "OK");
			login(email, xls.getCellValue("newPassword", "Value"));
			if(isElementPresent(By.xpath("//h2[contains(text(),'Welcome back, ')]"))==true)
			{
				Reporter.SuccessReport("Verifing Pasword Reset", "User is able to login with new password");
			}
			else
			{
				Reporter.failureReport("Verifing Pasword Reset", "User is unable to login with new password");
			}
			
			Reporter.SuccessReport("TC22_resetPasswordusingmyProfile", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.SuccessReport("TC22_resetPasswordusingmyProfile", "Failed");
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
	    			"Reset Pasword Using My Profile"}};
	}

}
