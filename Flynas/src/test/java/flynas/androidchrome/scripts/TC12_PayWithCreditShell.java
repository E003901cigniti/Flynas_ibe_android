package flynas.androidchrome.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.androidchrome.workflows.BookingPageFlow;

public class TC12_PayWithCreditShell extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"FL_WEB_12");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_12_PayWithCreditShell(String tripType, String origin, String dest, 
			String deptDate, String origin2,String departure2, String retdate,String Audalt,String Child,String infant, 
			String promo, String strBookingClass,
			String FlightType,String totalpass,String nationality,String Doctypr,String docNumber,
			String naSmiles,String Mobile,String email ,String SelectSeat,String paymenttype,String bookingtype, 
			String charity,String Currency, String payment2 ,String Description
			) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String	deptdate = pickDate(deptDate);
			inputBookingDetails(tripType, origin, dest, deptdate, origin2, departure2, retdate,Audalt, Child, infant,promo,Currency,paymenttype);
			selectClass(strBookingClass, tripType);
			String FirstLastname[]=inputPassengerDetails(FlightType,totalpass,nationality,Doctypr,docNumber, naSmiles,Mobile,email,"","","");
			System.out.println(FirstLastname[0]);	
			System.out.println(FirstLastname[1]);
			waitforElement(BookingPageLocators.baggagetittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)==true){
				Thread.sleep(1000);
				click(BookingPageLocators.continueBtn, "Continue");
			}else{
				System.out.println("No Baggage Page");
			}		
			selectSeat(SelectSeat, bookingtype);
			payment(paymenttype,"");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);				
			searchFlight(strPNR, email, "", "");
			cancelFlight();
			driver.get(configProps.getProperty("URL"));
			inputBookingDetails(tripType, origin, dest, deptdate, origin2, departure2, retdate,Audalt, Child, infant,promo,Currency,paymenttype);
			selectClass(strBookingClass, tripType);
			inputPassengerDetails(FlightType,totalpass,nationality,Doctypr,docNumber, naSmiles,Mobile,email,FirstLastname[0],FirstLastname[1],payment2);
			if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)==true){
				click(BookingPageLocators.continueBtn, "Continue");
			}else{
				System.out.println("No Baggage Page");
			}	
			selectSeat(SelectSeat, bookingtype);
			payment(payment2,strPNR);
			String strPNR2 = getReferenceNumber();
			System.out.println(strPNR2);
			validate_ticketStatus(strPNR2);
			
			Reporter.SuccessReport("PayWithCreditShell", "Pass");
			
			
			
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("PayWithCreditShell", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{xls.getCellValue("Trip Type", "Value"),
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
	    		xls.getCellValue("Payment Type", "Value"),
	    		"",
    			xls.getCellValue("Charity Donation", "Value"),
    			xls.getCellValue("Currency", "Value"),
    			xls.getCellValue("Payment Type2", "Value"),

	    		"Validate Pay With Credit Shell"}};
	}
	

}
