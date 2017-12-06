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
import flynas.web.workflows.LoginPage;
import flynas.web.workflows.MemberDashboard;
import flynas.web.workflows.MemberRegistrationPage;
import flynas.web.workflows.MyProfilePage;
import flynas.web.workflows.projectUtilities;

public class TC20_memberRegistration extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"FL_WEB_20");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_20_memberRegistration( String Password,String Nationality,String DocumentType,String DocNumber,String Mobile,
			String EmailAddress,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			//instantiating page objects
			projectUtilities util = new projectUtilities();
			LoginPage LoginPg = new LoginPage();
			BookingPage Bookingpg = new BookingPage();
			MemberRegistrationPage memberRegisterPg = new MemberRegistrationPage();
			MyProfilePage profilePage = new MyProfilePage();
			MemberDashboard memberdb = new MemberDashboard();
			
			//navigating to login page
			util.clickLogin();
			LoginPg.ClickJoinNow();
			String username = memberRegisterPg.memberRegistration();
			memberRegisterPg.verifingMemberRegistration();
			
			//Loging out
			util.logout();
			util.clickok();
			
			//Verifying member registration by loging in.
			LoginPg.login(username, "Test@1234");		
						
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
