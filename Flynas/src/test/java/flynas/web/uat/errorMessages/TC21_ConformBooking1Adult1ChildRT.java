package flynas.web.uat.errorMessages;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC21_ConformBooking1Adult1ChildRT extends BookingPageFlow{
ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"ErrorMessage_7");
	
	@Test(dataProvider = "testData",groups={"Chrome"})
	public void TC_21_ConformBooking1Adult1ChildRT( String tripType,String origin, String destination,
			String deptDate, String retDate,String adult,String child,String infant,String promo, String bookingClass,
			String flightType,String totalpsngrs,String nationality,String docType,String docNumber,String naSmiles,String mobile,
			String email ,String selectSeat,String paymentType,String bookingType,String charity,String currency, String payment2 ,
			String ErrorMessage, String Description)throws Throwable{
			try{
					
					
					TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
					String depdate = pickDate(deptDate);
													
					inputBookingDetails(tripType, origin, destination, depdate,"","",retDate,
							adult, child, infant, promo,currency,paymentType);
					selectClass(bookingClass, tripType);
					clickContinueBtn();
					inputPassengerDetails(flightType, totalpsngrs, nationality, docType, 
							docNumber, naSmiles, mobile, email,"","","");
					waitforElement(BookingPageLocators.baggagetittle);
					waitUtilElementhasAttribute(BookingPageLocators.body);
					if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)){
					clickContinueBtn();
						}else{
							System.out.println("No Baggage Page");
						}
					selectallSeats(selectSeat,totalpsngrs, "");
					payment(paymentType,"");
					String PNR = getReferenceNumber();
					searchFlightCheckin(PNR, email, "", "");					
					if(isElementDisplayedTemp(BookingPageLocators.ErrorMsg1)){
						String ErrorMsg = getText(BookingPageLocators.ErrorMsg1, "Error Message");
						if(ErrorMsg.contains("You can check-in online 48 hours before your departure. Online check-in closes 4 hours before departure.")){
							Reporter.SuccessReport("Validating Error Message For WCI", "Successfully Verified:"+ ErrorMsg);
						}else{
							Reporter.failureReport("Validating Erroe Message For WCI", "Expected Error Message not Came");
						}
						
					}	
								
					Reporter.SuccessReport("TC21_ConformBooking1Adult1ChildRT", "Pass");
					
					
			
					}catch(Exception e){
						e.printStackTrace();
						Reporter.failureReport("TC21_ConformBooking1Adult1ChildRT", "Fail");
						
					}
	}
		
		@DataProvider(name="testData")
		public Object[][] createdata1() {
		    return (Object[][]) new Object[][] {{
		    	xls.getCellValue("Trip Type", "Value2"),
		    	xls.getCellValue("Flight Type", "Value"),
		    	xls.getCellValue("origin", "Value"),
		    	xls.getCellValue("destination", "Value"),
		    	xls.getCellValue("Departure Date", "Value"),"","",
		    	xls.getCellValue("Return Date", "Value"),
		    	xls.getCellValue("total pass", "Value2"),
		    	xls.getCellValue("Adults Count", "Value"),
		    	xls.getCellValue("Child Count", "Value2"),
		    	xls.getCellValue("Infant Count", "Value"),
		    	xls.getCellValue("promo", "Value"),
		    	xls.getCellValue("Booking class", "Value"),
		    	xls.getCellValue("nationality", "Value"),
		    	xls.getCellValue("Document Type", "Value"),
		    	xls.getCellValue("Doc Number", "Value"),
		    	"1234567890",
    			xls.getCellValue("mobile", "Value"),
    			xls.getCellValue("email Address", "Value"),
    			xls.getCellValue("Select Seat", "Value"),
    			"Credit Card","",
    			xls.getCellValue("New Date", "Value"),
    			xls.getCellValue("Charity Donation", "Value"),"",
    			"Validating Error Message for Conform Booking 1 Adult 1 Child RT STD Above 48 hours"}};
	}

}
