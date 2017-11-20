package flynas.androidchrome.scripts;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC21_updatemyProfileOfMemberDetails extends BookingPageFlow {
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"FL_WEB_21");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_21_updatemyProfileOfMemberDetails( String Password,String Nationality,String DocumentType,
			String DocNumber,
			String Mobile,
			String EmailAddress,
			String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			click(BookingPageLocators.login_lnk, " Login");
			login(EmailAddress, Password);
			waitforElement(BookingPageLocators.myProfile);
			click(BookingPageLocators.myProfile, "My Profile");
			
		//	type(BookingPageLocators.pasword_reg_uppage, Password, "Password");
			//type(BookingPageLocators.cnfmpasword_reg, newPwd, "Conform Password");
			waitforElement(BookingPageLocators.dd_reg);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			click(BookingPageLocators.dd_reg, "Date");
			click(By.xpath(BookingPageLocators.selectdd_reg+randomNumericString()+"]"), "Date");
			click(BookingPageLocators.mm_reg, "Month");
			click(By.xpath(BookingPageLocators.selectmm_reg+randomNumericString()+"]"), "Month");
			click(BookingPageLocators.yy_reg, "year");
			click(By.xpath(BookingPageLocators.selectyy_reg+randomNumber(12, 19)+"]"), "Year");
			click(BookingPageLocators.update_Btn, "Update");
			verifingMemberUpdates_Production();
			
			Reporter.SuccessReport("TC21_updatemyProfileOfMemberDetails", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.SuccessReport("TC21_updatemyProfileOfMemberDetails", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    			"Flynas@1234",
	    		xls.getCellValue("Nationality", "Value"),
	    		xls.getCellValue("Document Type", "Value"),
	    		xls.getCellValue("Doc Number", "Value"),
	    		xls.getCellValue("Mobile", "Value"),
	    		"cherry9@yopmail.com",
	    		"Modify Profile for Registered member"}};
	}

}
