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
ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"ErrorMessage_7");
	
	@Test(dataProvider = "testData",groups={"Chrome"})
	public void TC_21_ConformBooking1Adult1ChildRT(String strTripType,String strFlightType,String strOrigin,String strDestination,
			String strDepatureDate,String origin2,String departure2, String strReturnDate, String strTotalPessenger,String strAdultCount,
			String strChildCount,String strInfantCount,String strPromo,String strBookingClass,String strNationality,String strDocumentType,
			String strDocumentNum,String strNaSmile,String strMobile,String strEmail,String strSelectSeat,String strPaymentType,String bookingtype,
			String strNewDate, String charity,String Currency,String description)throws Throwable{
			try{
					
					
					TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
					String depdate = pickDate(strDepatureDate);
													
					String trip = strTripType.split("/")[0];
					inputBookingDetails(trip, strOrigin, strDestination, depdate,origin2,departure2,strReturnDate,
							strAdultCount, strChildCount, strInfantCount, strPromo,Currency,strPaymentType);
					selectClass(strBookingClass, trip);
					inputPassengerDetails(strFlightType, strTotalPessenger, strNationality, strDocumentType, 
							strDocumentNum, strNaSmile, strMobile, strEmail,"","","");
					waitforElement(BookingPageLocators.baggagetittle);
					waitUtilElementhasAttribute(BookingPageLocators.body);
					if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)){
					clickContinueBtn();
						}else{
							System.out.println("No Baggage Page");
						}
					selectallSeats(strSelectSeat,strTotalPessenger, "");
					payment(strPaymentType,"");
					String strPNR = getReferenceNumber();
					searchFlightCheckin(strPNR, strEmail, "", "");					
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
		    	xls.getCellValue("Trip Type", "Value"),
		    	xls.getCellValue("Flight Type", "Value"),
		    	xls.getCellValue("Origin", "Value"),
		    	xls.getCellValue("Destination", "Value"),
		    	xls.getCellValue("Departure Date", "Value"),"","",
		    	xls.getCellValue("Return Date", "Value"),
		    	xls.getCellValue("total pass", "Value2"),
		    	xls.getCellValue("Adults Count", "Value"),
		    	xls.getCellValue("Child Count", "Value2"),
		    	xls.getCellValue("Infant Count", "Value"),
		    	xls.getCellValue("Promo", "Value"),
		    	xls.getCellValue("Booking class", "Value"),
		    	xls.getCellValue("Nationality", "Value"),
		    	xls.getCellValue("Document Type", "Value"),
		    	xls.getCellValue("Doc Number", "Value"),
		    	"1234567890",
    			xls.getCellValue("Mobile", "Value"),
    			xls.getCellValue("Email Address", "Value"),
    			xls.getCellValue("Select Seat", "Value"),
    			"Credit Card","",
    			xls.getCellValue("New Date", "Value"),
    			xls.getCellValue("Charity Donation", "Value"),"",
    			"Validating Error Message for Conform Booking 1 Adult 1 Child RT STD Above 48 hours"}};
	}

}
