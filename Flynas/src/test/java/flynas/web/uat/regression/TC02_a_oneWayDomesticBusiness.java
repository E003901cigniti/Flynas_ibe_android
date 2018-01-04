package flynas.web.uat.regression;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC02_a_oneWayDomesticBusiness extends BookingPageFlow{
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"TC_02_oneWayDomesticBusiness");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_02_a_oneWayDomesticBusiness(String tripType, String origin, String dest,String deptDate,String origin2,
			String departure2, String retdate,String Adult,String Child,String infant, String promo, String strBookingClass,
			String FlightType,String totalpass,String nationality,String Doctypr,String docNumber,String naSmiles,
			String Mobile,String email ,String SelectSeat,String paymenttype,String bookingtype,String charity,
			String Currency,String Description
			) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			String	deptdate = pickDate(deptDate);
			String	retrndate = pickDate(retdate);
			
			String[] Credentials = pickCredentials("UserCredentials");
			String username =Credentials[0];
			String password =Credentials[1];
			click(BookingPageLocators.login_lnk, "Login");
			switchtoChildWindow();
			login(username,password);
			
			inputBookingDetails(tripType, origin, dest, deptdate, origin2,departure2,retrndate,Adult, Child, infant,promo,Currency,paymenttype);
			
			//Selecting a fare class
			selectClass(strBookingClass, tripType);
			
			//Clicking continue button on Passenger details page
			continueOnPassengerDetails();
					
			//Clicking continue button on Baggage details page
			coninueOnBaggage();
			
			//Selecting seat 
			selectSeat(SelectSeat, bookingtype);
			
			//Payment
			payment(paymenttype,"");
			
			//Validating booking status 
			String PNR=getReferenceNumber();
			validate_ticketStatus(PNR);
			
			Reporter.SuccessReport("TC02_a_oneWayDomesticBusiness", "Passed");
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC02_a_oneWayDomesticBusiness", "Failed");
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
		    		xls.getCellValue("na Smiles", "Value"),
		    		xls.getCellValue("Mobile", "Value"),
		    		xls.getCellValue("Email Address", "Value"),
		    		xls.getCellValue("Select Seat", "Value"),
		    		xls.getCellValue("Payment Type", "Value"),
		    		"",
	    			xls.getCellValue("Charity Donation", "Value"),
	    			xls.getCellValue("Currency", "Value"),
		    		"Validate One way Domestic with one Adult With Business"}};
	}

}