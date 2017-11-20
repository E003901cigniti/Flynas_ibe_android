package flynas.android.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class HomePageLocators extends ActionEngine{
	
	//Menu locators
	public static By tittleMenu = By.xpath("//*[@resource-id='com.flynas.android.app:id/titleMenu']");
	public static By login = By.xpath("//*[@resource-id='com.flynas.android.app:id/navDrawMemberName']");
	public static By email = By.xpath("//*[@resource-id='com.flynas.android.app:id/loginViewEmailText']");
	public static By pasword = By.xpath("//*[@resource-id='com.flynas.android.app:id/loginViewPasswordText']");
	public static By Login_btn = By.xpath("//*[@resource-id='com.flynas.android.app:id/loginViewLoginBtn']");
	public static By logout_btn = By.xpath("//*[@text='Log Out']");
	
	//Icon Locators
	public static By bookFlights = By.xpath("//*[@resource-id='com.flynas.android.app:id/vgTabBookUnloggedin']");
	public static By onlineCheckin = By.xpath("//*[@resource-id='com.flynas.android.app:id/vgTabCheckinUnloggedin']");
	public static By manageBookings = By.xpath("//*[@resource-id='com.flynas.android.app:id/vgTabManageUnloggedin']");
	
	public static By rgstrdUsrIcnBF = By.id("com.flynas.android.app:id/vgTabBook");
	public static By rgstrdUsrIcnCI = By.id("com.flynas.android.app:id/vgTabCheckin");
	public static By rgstrdUsrIcnMMB = By.id("com.flynas.android.app:id/vgTabManage");
	public static By rgstrdUsrIcnBP = By.id("com.flynas.android.app:id/vgTabBoardingPasses");
	public static By rgstrdUsrIcnHm = By.id("com.flynas.android.app:id/vgTabHome");
	
	
	
}
