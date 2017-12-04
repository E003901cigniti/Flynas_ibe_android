package flynas.web.uat.regression;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.workflows.BookingPage;
import flynas.web.workflows.BookingPageFlow;
import flynas.web.workflows.MemberDashboard;
import flynas.web.workflows.MemberProfilePage;
import flynas.web.workflows.projectUtilities;

public class TC21_updatemyProfileOfMemberDetails extends BookingPageFlow {
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"FL_WEB_21");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_21_updatemyProfileOfMemberDetails( String Password,String Nationality,String DocumentType,
			String DocNumber,
			String Mobile,
			String EmailAddress,
			String Description) throws Throwable {
		try {
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			//instantiating page objects
			projectUtilities util = new projectUtilities();
			MemberProfilePage profilePage = new MemberProfilePage();
			MemberDashboard memberdb = new MemberDashboard();
			
			//navigating to login page
			util.clickLogin();
			
			//Signing in and navigating to my profile page
			login(EmailAddress, Password);
			memberdb.navigateToMyProfile();		
			
			//Capturing current DOB & Document expire date
			String firstDOB[] = profilePage.getdate("Date of birth");
			String firstDocExpDt[] = profilePage.getdate("Document expiry date");
			
			//Updating DOB & Document expire date
			profilePage.ModifyDate("Date of birth");
			profilePage.ModifyDate("Document expiry date");
			profilePage.clickUpdatebtn();			
			//Verifying successful update message
			verifingProfileUpdatemessage();
			
			//Refreshing the page and capturing the DOB again 
			refreshPage();
			String[] updateDOB = profilePage.getdate("Date of birth");
			String[] updateDocExpdt = profilePage.getdate("Date of birth");
			
			//Verifying Both the DOBs
			profilePage.verifyDOBupdate(firstDOB,updateDOB);
			profilePage.verifyDOBupdate(firstDocExpDt,updateDocExpdt);
			
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
