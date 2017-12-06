package flynas.web.uat.regression;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC11_oneAdultoneChildoneInfantPartCodeShare extends BookingPageFlow {

	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"FL_WEB_11");
	
	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_11oneAdultoneChildoneInfantPartCodeShare (
			String tripType, String origin, String dest, String departureDate, String origin2,
			String departure2,String retdate,String Adult,String Child,String infant, String promo,
			String FlightType,String totalpass,String nationality,String Doctypr, String docNumber, 
			String naSmiles,String Mobile,String email ,String SelectSeat,String paymenttype,
			String Bookingtype, String bookingclass,String Currency,String Description) throws Throwable {
		try {
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			//Initializing departure date and return date
			String	deptdate = pickDate(departureDate);
			String	retrndate = pickDate(retdate);
			
			//User Login
			String[] Credentials = pickCredentials("UATcredentials");
			String username =Credentials[0];
			String password =Credentials[1];
			click(BookingPageLocators.login_lnk, "Login");
			switchtoChildWindow();
			login(username,password);
			
			//Entering Booking Details
			inputBookingDetails(tripType, origin, dest, deptdate,origin2, departure2,retrndate,Adult, Child, infant,promo,Currency,paymenttype);
			
			//Selecting class in code share flight
			selectClasswithPartCodeshare(bookingclass,Bookingtype);
			
			//entering passenger details
			inputPassengerDetails(FlightType,totalpass,nationality,Doctypr,docNumber, naSmiles,Mobile,email,"","","");
			waitUtilElementhasAttribute(BookingPageLocators.body);
			//Click continue on Baggage page
			if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)==true){
				clickContinueBtn();
			}else{
				System.out.println("No Baggage Page");
			}
			
			//Click continue on Seat selection page
			waitforElement(BookingPageLocators.selectseattittle);
			if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)==true)
				{
				clickContinueBtn();
				if(isElementDisplayedTemp(BookingPageLocators.ok)==true)
					{
					click(BookingPageLocators.ok, "OK");					
					}
				}
			else{
				System.out.println("No Seat Page");
				}
			
			payment(paymenttype,"");
			String strpnr = getReferenceNumber();
			String PNR = strpnr.trim();
			System.out.println("PNR**********"+PNR);
			validate_ticketStatus(PNR);
			
			Reporter.SuccessReport("TC_11_oneAdultoneChildoneInfantPartCodeShare", "Passed");
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC_11_oneAdultoneChildoneInfantPartCodeShare", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    	{
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
	    		xls.getCellValue("Booking Class", "Value"),"",
	    		"Validate one Adult one Child one Infant PartCodeShare" }
	    				
	    
	    };
	}

}
