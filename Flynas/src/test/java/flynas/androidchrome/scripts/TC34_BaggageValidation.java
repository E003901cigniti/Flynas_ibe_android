package flynas.androidchrome.scripts;

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

public class TC34_BaggageValidation extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"TC_01_oneWayDomesticEcoSADAD");

	@Test(dataProvider = "testData",groups={"Economy"})
	public  void TC_34_BaggageValidation( String username,String password,String bookingClass,String mobilenum,String paymentType,
			String newDate,String pickDate,String rtnDate,String origin,String dest,String triptype,String adult,String child,
			String infant,String seatSelect,String Description) throws Throwable {
		try {
			
			DateFormat dateFormat = new SimpleDateFormat("dd-MMMM yyyy");
			Date date = new Date();
			String deptdate = dateFormat.format(date);
			String 	depdate = newDateForCheckIN(deptdate);
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
										
			click(BookingPageLocators.login_lnk, "Login");
			login(username,password);
			inputBookingDetails(triptype,origin, dest, depdate , "", "", rtnDate,adult, child, infant,"","","");
			selectClass(bookingClass, triptype);
			waitforElement(BookingPageLocators.passengerDetailsTittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			click(BookingPageLocators.continueBtn, "Continue");
			validating_BaggageWeights();
				
			Reporter.SuccessReport("TC34_BaggageValidation", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC34_BaggageValidation", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    		xls.getCellValue("username", "Value"),
		    	xls.getCellValue("password", "Value"),
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
	    		"Validate Excess Baggage Adding"}};
	}

}
