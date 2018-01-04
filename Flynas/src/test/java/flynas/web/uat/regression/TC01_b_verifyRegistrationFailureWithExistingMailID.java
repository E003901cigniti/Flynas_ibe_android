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

public class TC01_b_verifyRegistrationFailureWithExistingMailID extends BookingPageFlow{
	
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_01_b_verifyRegistrationFailureWithExistingMailID( String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			//instantiating page objects
			projectUtilities util = new projectUtilities();
			LoginPage LoginPg = new LoginPage();
			MemberRegistrationPage memberRegisterPg = new MemberRegistrationPage();
		
			
			
			String[] Credentials = pickCredentials("UserCredentials");
			String username =Credentials[0];
			
			//navigating to login page
			util.clickLogin();
			LoginPg.ClickJoinNow();
			memberRegisterPg.memberRegistration(username,"Adult");
			util.VerifyErrorMessage("already exists");
				
						
			Reporter.SuccessReport("TC_01_b_verifyRegistrationFailureWithExistingMailID", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.SuccessReport("TC_01_b_verifyRegistrationFailureWithExistingMailID", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{"Validate Error message on Regestration with existing Email ID"}};
	}

}
