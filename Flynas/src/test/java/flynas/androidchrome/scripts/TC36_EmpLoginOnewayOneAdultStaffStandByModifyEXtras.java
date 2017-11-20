package flynas.androidchrome.scripts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC36_EmpLoginOnewayOneAdultStaffStandByModifyEXtras extends BookingPageFlow{
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"FL_WEB_15");
	
	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_36_EmpLoginOnewayOneAdultStaffStandByModifyEXtras(String username,String password,String bookingClass,String mobilenum,
			String paymentType,String newDate,String pickDate,String origin,String dest,String triptype,String adult,String child,
			String infant,String strSelectSeat,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			String depdat = pickDate(pickDate);
			
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			click(emplogin_lnk, "Employe Login");
			switchtoChildWindow();
			login(username,password);
			inputBookingDetails(triptype,origin, dest, depdat, "", "", "",adult, child, infant,"","",paymentType);
			selectClassForStaff(bookingClass);
			waitforElement(BookingPageLocators.mobileNum);
			if(getText(BookingPageLocators.mobileNum,"MobileNumber").length() == 0)
			{
				type(BookingPageLocators.mobileNum, mobilenum, "MobileNumber");
				Thread.sleep(2000);
				click(BookingPageLocators.continueBtn, "Continue");
			}
			else
			{
				click(BookingPageLocators.continueBtn, "Continue");
			}
			/*Thread.sleep(3000);
			click(BookingPageLocators.continueBtn, "Continue");
			Thread.sleep(5000);
			click(BookingPageLocators.continueBtn, "Continue");
			Thread.sleep(5000);
			click(BookingPageLocators.ok, "OK");*/
			payment(paymentType, "");
			String strpnr = getReferenceNumber().trim();
			searchFlight(strpnr, username, "", "");
			click(BookingPageLocators.modifyExtras, "Modify Extras");
			Baggage_Extra(triptype);
			addSportsEqpmnt(triptype);
			Select_A_Meal();
			Selecting_loung();
			inputExtras("12");
			waitforElement(BookingPageLocators.manageMyBookingTittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			click(BookingPageLocators.modifySeat, "Seat Selection");
			waitforElement(BookingPageLocators.selectseattittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)){
				selectallSeats(strSelectSeat, "1", triptype);
			}else{
				System.out.println("No seat Select Page");
			}
			waitforElement(BookingPageLocators.paynow);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			click(BookingPageLocators.paynow, "Pay Now");
			payment(paymentType, "");
			String strPNR = getReferenceNumber().trim();
			validate_ticketStatus(strPNR);
		
			
			Reporter.SuccessReport("TC36_EmpLoginOnewayOneAdultStaffStandByModifyEXtras", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC36_EmpLoginOnewayOneAdultStaffStandByModifyEXtras", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    		xls.getCellValue("EmployeEmail", "Value"),
	    		xls.getCellValue("Password", "Value"),
	    		xls.getCellValue("Booking Class", "Value"),
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Payment Type", "Value"),
	    		xls.getCellValue("NewDate", "Value"),
	    		xls.getCellValue("Departure date", "Value"),
	    		xls.getCellValue("Origin", "Value"),
	    		xls.getCellValue("Destination", "Value"),
	    		xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		xls.getCellValue("Select Seat", "Value"),
	    		"Validate Employe Login Oneway One Adult StaffStandBy Modify Extras"}};
	}
}
