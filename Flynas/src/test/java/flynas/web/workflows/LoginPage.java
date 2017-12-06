package flynas.web.workflows;

import java.util.Random;

import org.openqa.selenium.By;

import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;

public class LoginPage<RenderedWebElement> extends BookingPageLocators {
	
	public void WaittillPageLoad() throws InterruptedException{
		waitUtilElementhasAttribute(BookingPageLocators.body);		
	}
	
	public void  login(String username,String password) throws Throwable
	{			
		type(BookingPageLocators.email, username, "Email");
		type(BookingPageLocators.password, password, "Password");
		click(BookingPageLocators.login_btn, "Login");		
		WaittillPageLoad();
		if(isElementDisplayed(BookingPageLocators.logout_lnk)==true){
			Reporter.SuccessReport("Verifing login: ", "User Log-in successful");
		}else
			Reporter.failureReport("Verifing login: ", "User Log-in failed");
	}
	
	public void  SelectEmployeelogin() throws Throwable
	{
		click(BookingPageLocators.emplogin_lnk, "Login");				
	}
	
	public void  SelectAgencieslogin() throws Throwable
	{
		
		click(BookingPageLocators.agency_lnk, "Login");				
	}
	
	public void  SelectCorporatelogin() throws Throwable
	{
		
		click(BookingPageLocators.corporatelogin_lnk, "Login");				
	}
	
	public void  SelectManageBooking() throws Throwable
	{
		
		click(BookingPageLocators.Manage_booking_lnk, "Login");				
	}
	
	public void  SelectWebCheckIn() throws Throwable
	{
		
		click(BookingPageLocators.WebCheckIn_lnk, "Login");				
	}
	
	public void  ClickJoinNow() throws Throwable
	{
		
		click(BookingPageLocators.JoinNow, "JoinNow");				
	}
	
	
	

}
