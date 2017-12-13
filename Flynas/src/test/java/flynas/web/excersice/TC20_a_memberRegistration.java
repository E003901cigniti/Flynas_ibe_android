package flynas.web.excersice;

import org.openqa.selenium.By;
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

public class TC20_a_memberRegistration extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("Credentialsdata"),"UserCredentials");
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_20_memberRegistration(String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			//instantiating page objects
			projectUtilities util = new projectUtilities();
			LoginPage LoginPg = new LoginPage();
			MemberRegistrationPage memberRegisterPg = new MemberRegistrationPage();
			
			int row = xls.getRowCount("UserCredentials");
		
			for(int i=1;i<=row;i++){
			String username = xls.getCellValue("credentials"+i, "userid");
			String password = xls.getCellValue("credentials"+i, "password");
			String firstName = xls.getCellValue("credentials"+i, "firstname");
			String lastName = xls.getCellValue("credentials"+i, "lastname");
			util.clickLogin();
			LoginPg.ClickJoinNow();
			memberRegisterPg.memberRegistration(username,password,firstName,lastName,"Adult"); // Registering a new Adult member
			if(isElementDisplayedTemp(BookingPageLocators.ok)==true){
				util.clickok();
			}else{
			memberRegisterPg.verifingMemberRegistration();			
			util.logout();
			util.clickok();	
			}
			}
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
	    		{"Validate Member Login"}};
	}

}
