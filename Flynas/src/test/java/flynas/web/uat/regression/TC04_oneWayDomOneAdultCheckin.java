package flynas.web.uat.regression;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC04_oneWayDomOneAdultCheckin extends BookingPageFlow{
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"TC04_oneWayDomAdultCheckin");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_04_oneWayDomOneAdultCheckin (String Username,String Password, String tripType, 
			String origin, String dest, String deptDate,String origin2,String departure2, String retdate,
			String Adult,String Child,String infant, String promo, String strBookingClass, String FlightType,String totalpass,String nationality,String Doctypr,
			String docNumber, String naSmiles,String Mobile,String email ,String SelectSeat,
			String paymenttype, String bookingtype,String Charity, 
			String Currency,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			//Initializing departure date and return date
			String depDate = pickDate(deptDate);
			String retrndate = pickDate(retdate);
			
			//User Login
			click(BookingPageLocators.login_lnk, "Login");
			switchtoChildWindow();
			login(Username,Password);
			
			//Entering Booking Details
			inputBookingDetails(tripType, origin, dest, depDate, origin2,departure2,retrndate,Adult, Child, infant,promo,Currency,paymenttype);
			
			//Selecting flight and traveling class
			selectClass(strBookingClass, tripType);
			
			//Clicking continue button on Passenger details page
			waitforElement(BookingPageLocators.passengerDetailsTittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			clickContinueBtn();
			
			Baggage_Extra(tripType);
			clickContinueBtn();
			selectallSeats(SelectSeat,totalpass,tripType);
			//selectSeat(SelectSeat,bookingtype);
			payment(paymenttype,"");
			String strpnr = getReferenceNumber();
			String PNR = strpnr.trim();
			System.out.println("PNR**********"+PNR);
			validate_ticketStatus(PNR);
			searchFlightCheckin(PNR, email, "", "");
			performCheckin(SelectSeat,paymenttype,totalpass);
			validateCheckin();
			
			Reporter.SuccessReport("TC04_oneWayDomOneAdultCheckin", "Passed");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC04_oneWayDomOneAdultCheckin", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    			xls.getCellValue("username", "Value"),	
	    			xls.getCellValue("password", "Value"),
	    			xls.getCellValue("Trip Type", "Value"),
		    		xls.getCellValue("Origin", "Value"),
		    		xls.getCellValue("Destination", "Value"),
		    		xls.getCellValue("Departure Date", "Value"),
		    		"",
		    		"",
		    		xls.getCellValue("Return Date", "Value"),
		    		xls.getCellValue("Adults Count", "Value"),
		    		xls.getCellValue("Child Count", "Value"),
		    		xls.getCellValue("Infant Count", "Value"),
		    		xls.getCellValue("Promo", "Value"),
		    		xls.getCellValue("Booking Class", "Value"),
		    		xls.getCellValue("Flight Type", "Value"),
		    		xls.getCellValue("Total Passenger", "Value"),
		    		xls.getCellValue("Nationality", "Value"),
		    		xls.getCellValue("Document Type", "Value"),
		    		xls.getCellValue("Doc Number", "Value"),
		    		"1234567890",
		    		xls.getCellValue("Mobile", "Value"),
		    		xls.getCellValue("Email Address", "Value"),
		    		xls.getCellValue("Select Seat", "Value"),
		    		"Credit Card",
		    		"",
	    			xls.getCellValue("Charity Donation", "Value"),
	    			"",
		    		"Validate One way Domestic with one Adualt"}};
	}

}