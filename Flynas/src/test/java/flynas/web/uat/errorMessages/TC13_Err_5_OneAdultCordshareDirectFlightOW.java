package flynas.web.uat.errorMessages;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC13_Err_5_OneAdultCordshareDirectFlightOW extends BookingPageFlow{

	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"ErrorMessage_5");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_13_Err_5_OneAdultCordshareDirectFlightOW (String tripType, String origin, String dest, String deptDate,String origin2,String departure2,String retdate,String Audalt,String Child,String infant, 
			String promo,String FlightType,String totalpass,String nationality,String Doctypr,String docNumber,String naSmiles,String Mobile,String email ,String SelectSeat,
			String paymenttype,String Bookingtype,String bookingclass,String Currency,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String	deptdate = pickDate(deptDate);
			String trip = tripType.split("/")[2];
			inputBookingDetails(trip, origin, dest, deptdate,origin2, departure2,retdate,Audalt, Child, infant,promo,Currency,paymenttype);
			selectCodeshareConectflight(bookingclass,Bookingtype);
			inputPassengerDetails(FlightType,totalpass,nationality,Doctypr,docNumber, naSmiles,Mobile,email,"","","");
			if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)==true){
				clickContinueBtn();
			}else{
				System.out.println("No Baggage Page");
			}
			if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)==true){
				clickContinueBtn();
				if(isElementDisplayedTemp(BookingPageLocators.ok)){
					click(BookingPageLocators.ok, "OK");
				}
			}else{
				System.out.println("No Seat Page");
			}
			payment(paymenttype,"");
			String strpnr = getReferenceNumber().trim();
			searchFlightCheckin(strpnr, email, "", "");
			if(isElementDisplayedTemp(BookingPageLocators.ErrorMsg1)){
				String ErrorMsg = getText(BookingPageLocators.ErrorMsg1, "Error Message");
				if(ErrorMsg.contains("This flight is not eligible for online check-in. Please check-in at the airport")){
					Reporter.SuccessReport("Validating Error Message For WCI", "Successfully Verified:"+ ErrorMsg);
				}else{
					Reporter.failureReport("Validating Erroe Message For WCI", "Expected Error Message not Came");
				}
				
			}	
			
			Reporter.SuccessReport("TC13_Err_5_OneAdultCordshareDirectFlightOW", "Passed");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC13_Err_5_OneAdultCordshareDirectFlightOW", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Origin", "Value2"),xls.getCellValue("Destination", "Value2"),xls.getCellValue("Departure Date", "Value"),
	    		xls.getCellValue("Origin2", "Value"),xls.getCellValue("Destination2", "Value"),xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),xls.getCellValue("Child Count", "Value"),xls.getCellValue("Infant Count", "Value"),
	    		xls.getCellValue("Promo", "Value"),xls.getCellValue("Flight Type", "Value"),xls.getCellValue("total pass", "Value"),
	    		xls.getCellValue("Nationality", "Value"),xls.getCellValue("Document Type", "Value"),xls.getCellValue("Doc Number", "Value"),
	    		xls.getCellValue("na Smiles", "Value"),xls.getCellValue("Mobile", "Value"),xls.getCellValue("Email Address", "Value"),
	    		xls.getCellValue("Select Seat", "Value"),"Credit Card",xls.getCellValue("Booking type", "Value"),
	    		xls.getCellValue("Booking Class", "Value"),"",
	    		"Validate Error Message For One Adult Cordshare OW Direct Flight CheckIn" }
	    				
	    
	    };
	}
	

}
