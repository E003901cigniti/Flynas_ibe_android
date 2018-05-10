package flynas.web.workflows;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;

import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;

public class LoginPage<RenderedWebElement> extends BookingPageLocators {
	projectUtilities util = new projectUtilities();
	
	public void WaittillPageLoad() throws InterruptedException{
		waitUtilElementhasAttribute(BookingPageLocators.body);		
	}
	
	public void  login(String username,String password) throws Throwable
	{	
		WaittillPageLoad();		
		type(BookingPageLocators.email, username, "Email");
		type(BookingPageLocators.password, password, "Password");
		click(BookingPageLocators.login_btn, "Login");		
		if(isElementDisplayed(BookingPageLocators.logout_lnk)==true)
			Reporter.SuccessReport("Verifing login ", "User Log-in successful");
		else
			Reporter.failureReport("Verifing login ", "User Log-in failed");
	}
	
	public void  SelectEmployeelogin() throws Throwable
	{
		WaittillPageLoad();
		click(BookingPageLocators.emplogin_lnk, "Login");				
	}
	
	public void  SelectAgencieslogin() throws Throwable
	{
		WaittillPageLoad();
		click(BookingPageLocators.agency_lnk, "Login");				
	}
	
	public void  SelectCorporatelogin() throws Throwable
	{
		WaittillPageLoad();
		click(BookingPageLocators.corporatelogin_lnk, "Login");				
	}
	
	public void  SelectManageBooking() throws Throwable
	{
		WaittillPageLoad();
		click(BookingPageLocators.Manage_booking_lnk, "Login");				
	}
	
	public void  SelectWebCheckIn() throws Throwable
	{
		WaittillPageLoad();
		click(BookingPageLocators.WebCheckIn_lnk, "Login");				
	}
	
	public void  ClickJoinNow() throws Throwable
	{
		WaittillPageLoad();
		click(BookingPageLocators.JoinNow, "JoinNow");				
	}
	
	public void  lockAccount(String username) throws Throwable
	{
		
		try{
		String password = RandomStringUtils.random(8, true, true);
		WaittillPageLoad();
		for(int i=0;i<=10;i++){
			type(BookingPageLocators.email, username, "Email");
			type(BookingPageLocators.password, password, "Password");
			click(BookingPageLocators.login_btn, "Login");
			if(i!=10)
			util.verifyAlertPopup();
			}
			Reporter.SuccessReport("Lock account", "account Locked");
		}catch(Exception e){
			Reporter.SuccessReport("Lock account", "account not locked");
		}
					
	}
	
	

}
