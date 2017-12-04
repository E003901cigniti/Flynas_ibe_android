package flynas.web.uat.regression;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC17_memberLoginRTChangeflightCheckIN extends BookingPageFlow {
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"FL_WEB_17");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_17_memberLoginRTChangeflightCheckIN( String username,String password,String bookingClass,
			String mobilenum,
			String paymentType,
			String newDate,
			String departurDate,String rtnDate,
			String origin,
			String dest,String triptype,String adult,String child,String infant,String seatSelect,
			String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			String 	deptdate = pickDate(departurDate);
			String 	rtrndate = pickDate(rtnDate);
			
		
			click(BookingPageLocators.login_lnk, "Login");
			login(username,password);
			inputBookingDetails(triptype,origin, dest, deptdate , "", "", rtrndate,adult, child, infant,"","","");
			selectClass(bookingClass, "");
			waitforElement(BookingPageLocators.passengerDetailsTittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.passengerDetailsTittle)){
			clickContinueBtn();
			}else{
				System.out.println("No Passenger Page");
			}
			waitforElement(BookingPageLocators.baggagetittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)){
				clickContinueBtn();
				}else{
					System.out.println("No Baggage Page");
				}
			selectSeat(seatSelect, "");	
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			
			String newdate = pickDate(newDate);
			String strPNRChangedate = changeDate(strPNR, username, mobilenum, "", newdate, "","",bookingClass,0);
			String strPNRChangeDate = strPNRChangedate.trim();
			
			System.out.println(strPNRChangeDate);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(strPNRChangeDate.equalsIgnoreCase(strPNR)){
				Reporter.SuccessReport("Change Flight Date", "Flight Date has changed successfully");
			}else{
				Reporter.SuccessReport("Change Flight Date", "Flight Date has NOT changed successfully");
			}
			searchFlightCheckin(strPNRChangeDate, username, "", "");
			performCheckin(seatSelect,paymentType,"");
			validateCheckin();
			
			
			Reporter.SuccessReport("TC17_memberLoginRTChangeflightCheckIN", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC17_memberLoginRTChangeflightCheckIN", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    			xls.getCellValue("username", "Value"),
	    			xls.getCellValue("Password", "Value"),
		    		xls.getCellValue("Booking Class", "Value"),
		    		xls.getCellValue("Mobile", "Value"),
		    		xls.getCellValue("Payment Type", "Value"),
		    		xls.getCellValue("NewDate", "Value"),
		    		xls.getCellValue("Departure Date", "Value"),
		    		xls.getCellValue("Return Date", "Value"),
		    		xls.getCellValue("Origin", "Value"),
		    		xls.getCellValue("Destination", "Value"),
		    		xls.getCellValue("Trip Type", "Value"),
		    		xls.getCellValue("Adults Count", "Value"),
		    		xls.getCellValue("Child Count", "Value"),
		    		xls.getCellValue("Infant Count", "Value"),
		    		"Extra Leg Room",
		    		"Validate member Login Round Trip Changeflight CheckIN"}};
	}

}
