package flynas.web.uat.regression;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPage;
import flynas.web.workflows.BookingPageFlow;
import flynas.web.workflows.MemberDashboard;
import flynas.web.workflows.MyProfilePage;
import flynas.web.workflows.projectUtilities;

public class TC01_d_updatemyProfileOfMemberDetails extends BookingPageFlow {
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"FL_WEB_21");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_01_d_updatemyProfileOfMemberDetails( String EmailAddress,String Password,			
			String Description) throws Throwable {
		try {
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			//instantiating page objects
			projectUtilities util = new projectUtilities();
			MyProfilePage profilePage = new MyProfilePage();
			MemberDashboard memberdb = new MemberDashboard();
			
			//navigating to login page
			util.clickLogin();
			
			//Signing in and navigating to my profile page
			login(EmailAddress, Password);
			memberdb.navigateToMyProfile();		
			
			//Capturing current DOB,Document number, Document expire date & Mobile number
			String firstDOB[] = profilePage.getdate("Date of birth");
			
			String firstDocNum = getText(BookingPageLocators.docnumber_reg, "Documen Type");
			String firstDocExpDt[] = profilePage.getdate("Document expiry date");
			String FirstMobileNum = getText(BookingPageLocators.mobileNum, "MobileNumber");
			
			//Updating DOB,Document number, Document expire date & Mobile number
			profilePage.ModifyDate("Date of birth");
			profilePage.ModifyDocNum();
			profilePage.ModifyDate("Document expiry date");
			profilePage.updateMobilenum();
			profilePage.clickUpdatebtn();
			
			//Verifying successful update message
			verifingProfileUpdatemessage();
			
			//Refreshing the page and capturing the all the modified data 
			refreshPage();
			String[] updateDOB = profilePage.getdate("Date of birth");
			String updatedDocNum = getText(BookingPageLocators.docnumber_reg, "Documen Type");
			String[] updateDocExpdt = profilePage.getdate("Date of birth");
			String updatedMobileNum = getText(BookingPageLocators.mobileNum, "MobileNumber");
			
			//Verifying if data is modified
			util.verifyDateupdate("Date of birth",firstDOB,updateDOB);
			//util.verifytextupdate("Document Number",firstDocNum,updatedDocNum);
			util.verifyDateupdate("Document expire date",firstDocExpDt,updateDocExpdt);
			//util.verifytextupdate("Mobile Number",FirstMobileNum,updatedMobileNum);
			
			Reporter.SuccessReport("TC01_d_updatemyProfileOfMemberDetails", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.SuccessReport("TC01_d_updatemyProfileOfMemberDetails", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    			"cherry9@yopmail.com",
	    			"Flynas@1234",
	    	   		"Modify Profile for Registered member"}};
	}

}
