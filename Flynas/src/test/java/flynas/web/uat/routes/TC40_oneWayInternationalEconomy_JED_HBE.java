package flynas.web.uat.routes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC40_oneWayInternationalEconomy_JED_HBE extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"TC_01_oneWayDomesticEcoSADAD");

	@Test(dataProvider = "testData",groups={"Economy"})
	public  void TC_40_oneWayInternationalEconomy_JED_HBE( String bookingClass,
			String mobilenum,
			String paymentType,
			String newDate,
			String depDate,String rtnDate,
			String origin,
			String dest,String triptype,String adult,String child,String infant,String seatSelect,
			String newdate,	String strTolPass,String domOrInt,String Description) throws Throwable {
		try {
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String[] Credentials = pickCredentials("UATcredentials");
			String username =Credentials[0];
			String password =Credentials[1];			
			String deptDate = pickDate(depDate);
			String retrnDate = pickDate(rtnDate);
			click(BookingPageLocators.login_lnk, "Login");
			login(username,password);
			inputBookingDetails(triptype,origin, dest, deptDate , "", "", retrnDate,adult, child, infant,"","","");
			selectClass(bookingClass, triptype);
			waitforElement(BookingPageLocators.passengerDetailsTittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			clickContinueBtn();
			waitforElement(BookingPageLocators.baggagetittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			clickContinueBtn();
			waitForElementPresent(BookingPageLocators.selectseattittle, "SelectSeatTittle");
			waitUtilElementhasAttribute(BookingPageLocators.body);	
			clickContinueBtn();
			if(isElementDisplayedTemp(BookingPageLocators.ok)){
				click(BookingPageLocators.ok, "OK");
			}
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			validate_ticketStatus(strPNR);
							
		
			Reporter.SuccessReport("TC40_oneWayInternationalEconomy_JED_HBE", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC40_oneWayInternationalEconomy_JED_HBE", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    			
		    		
	    		xls.getCellValue("Booking Class", "Value"),
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Payment Type", "Value"),
	    		xls.getCellValue("NewDate", "Value"),
	    		xls.getCellValue("Departure Date", "Value"),
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Origin", "Value40"),
	    		xls.getCellValue("Destination", "Value40"),
	    		xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		xls.getCellValue("Select Seat", "Value"),
	    		"01-April 2017",
	    		xls.getCellValue("Total Passenger", "Value"),
	    		xls.getCellValue("Flight Type", "Value"),
	    		"Validate oneWay International Economy_JED_HBE"}};
	}


}