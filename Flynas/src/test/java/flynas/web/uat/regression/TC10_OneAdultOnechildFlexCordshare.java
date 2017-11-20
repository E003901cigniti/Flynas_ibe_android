package flynas.web.uat.regression;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC10_OneAdultOnechildFlexCordshare extends BookingPageFlow{

	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"FL_WEB_10");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_10_OneAdultOnechildFlexCordshare (String Username, String Password, String tripType, String origin, String dest, String strDepatureDate,
			String origin2,String departure2,String strReturnDate,String Adult,String Child,String infant, 
			String promo,String FlightType,String totalpass,String nationality,String Doctypr,
			String docNumber ,String naSmiles,String Mobile,String email ,String SelectSeat,
			String paymenttype,String Bookingtype,String bookingclass,String Currency,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			//Initializing departure date and return date
			String	deptdate = pickDate(strDepatureDate);
			String	retrndate = pickDate(strReturnDate);
			
			//User Login
			click(BookingPageLocators.login_lnk, "Login");
			switchtoChildWindow();
			login(Username,Password);
			
			//Entering Booking Details
			inputBookingDetails(tripType, origin, dest, deptdate,origin2, departure2,retrndate,Adult, Child, infant,promo,Currency,paymenttype);
			
			//Selecting class in code share flight
			selectCodeshareConectflight(bookingclass,Bookingtype);
			
			//entering passenger details
			inputPassengerDetails(FlightType,totalpass,nationality,Doctypr,docNumber, naSmiles,Mobile,email,"","","");
			
			//Click continue on Baggage page
			if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)==true){
				clickContinueBtn();
			}else{
				System.out.println("No Baggage Page");
			}
			
			//Click continue on Seat selection page
			if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)==true){
				clickContinueBtn();
			}else{
				System.out.println("No Seat Page");
			}
			
			
			payment(paymenttype,"");
			String strpnr = getReferenceNumber();
			String PNR = strpnr.trim();
			System.out.println("PNR**********"+PNR);
			validate_ticketStatus(PNR);
			
			Reporter.SuccessReport("TC_10_OneAdultOnechildFlexCordshare", "Passed");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC_10_OneAdultOnechildFlexCordshare", "Failed");
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
		    		xls.getCellValue("Origin2", "Value"),
		    		xls.getCellValue("Destination2", "Value"),
		    		xls.getCellValue("Return Date", "Value"),
		       		xls.getCellValue("Adults Count", "Value"),
		    		xls.getCellValue("Child Count", "Value"),
		    		xls.getCellValue("Infant Count", "Value"),
		    		xls.getCellValue("Promo", "Value"),
		    		xls.getCellValue("Flight Type", "Value"),
		    		xls.getCellValue("total pass", "Value"),
		    		xls.getCellValue("Nationality", "Value"),
		    		xls.getCellValue("Document Type", "Value"),
		    		xls.getCellValue("Doc Number", "Value"),
		    		xls.getCellValue("na Smiles", "Value"),
		    		xls.getCellValue("Mobile", "Value"),
		    		xls.getCellValue("Email Address", "Value"),
		    		xls.getCellValue("Select Seat", "Value"),
		    		xls.getCellValue("Payment Type", "Value"),
		    		xls.getCellValue("Booking type", "Value"),
		    		xls.getCellValue("Booking Class", "Value"),
		    		"",
		    		"Validate One Adult One child FlexCordshare" }
	    				
	    
	    };
	}
	

}
