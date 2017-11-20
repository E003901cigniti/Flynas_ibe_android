package flynas.android.workflows;

import com.ctaf.utilities.Reporter;

import flynas.android.testObjects.HomePageLocators;

public class Homepage extends HomePageLocators{
	
	public void select_Bookflights(String userType) throws Throwable
	{
		if(userType.equalsIgnoreCase("registered")){
			waitforElement(HomePageLocators.rgstrdUsrIcnBF);
			click(HomePageLocators.rgstrdUsrIcnBF, "Book flights");
		}
		else if (userType.equalsIgnoreCase("Anonymous")){
			waitforElement(HomePageLocators.bookFlights);
			click(HomePageLocators.bookFlights, "Book flights");
		}
		
	}
	public void select_Managebooking(String userType) throws Throwable
	{
		if(userType.equalsIgnoreCase("registered")){
			waitforElement(HomePageLocators.rgstrdUsrIcnMMB);
			click(HomePageLocators.rgstrdUsrIcnMMB, "Book flights");
		}else if(userType.equalsIgnoreCase("Anonymous")){
		waitForElementPresent(HomePageLocators.manageBookings, "Book flights");
		click(HomePageLocators.manageBookings, "Manage Bookings");
		}
	}
	public void select_OnlineCheckIn(String userType) throws Throwable
	{
		if(userType.equalsIgnoreCase("registered")){
			waitforElement(HomePageLocators.rgstrdUsrIcnCI);
			click(HomePageLocators.rgstrdUsrIcnCI, "Online CheckIn");
		}else if(userType.equalsIgnoreCase("Anonymous")){
			waitForElementPresent(HomePageLocators.onlineCheckin, "Online CheckIn");
			click(HomePageLocators.onlineCheckin, "Online Check-in");
		}
	}
	public void select_TittleMenu() throws Throwable
	{
//		BookingPageFlow BF = new BookingPageFlow();
//		BF.Handlepopup();
		waitForElementPresent(HomePageLocators.tittleMenu, "Online CheckIn");
		click(HomePageLocators.tittleMenu, "TittleMenu");
	}
	public void Click_login() throws Throwable
	{
		waitForElementPresent(HomePageLocators.login, "Login");
		click(HomePageLocators.login, "Login");
	}
	public void Login(String username,String pwd) throws Throwable
	{try{
		System.out.println("User name : "+username);
		System.out.println("password : "+pwd);
		type(HomePageLocators.email, username, "Email");
		type(HomePageLocators.pasword, pwd, "Password");
		click(HomePageLocators.Login_btn, "Login");
		waitforElement(HomePageLocators.rgstrdUsrIcnBF);
		Reporter.SuccessReport("Login", " login Successfull");
		
	}catch(Exception e){
		Reporter.failureReport("Login failure", "Unable to login");
		
	}
		
	}
	public void Click_logout() throws Throwable
	{
		click(HomePageLocators.logout_btn, "Logout");
	}

}
