package flynas.web.uat.errorMessages;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC15_Err_6_ConformBooking1Adt1OW extends BookingPageFlow{
ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"ErrorMessage_6");
	
	@Test(dataProvider = "testData",groups={"Chrome"})
	public void TC_15_Err_6_ConformBooking1Adt1OW(String strTripType,String strFlightType,String strOrigin,String strDestination,
			String strDepatureDate,String origin2,String departure2, String strReturnDate, String strTotalPessenger,String strAdultCount,
			String strChildCount,String strInfantCount,String strPromo,String strBookingClass,String strNationality,String strDocumentType,
			String strDocumentNum,String strNaSmile,String strMobile,String strEmail,String strSelectSeat,String strPaymentType,String bookingtype,
			String strNewDate, String charity,String Currency,String description)throws Throwable{
				try{
					
					
					TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
					String	deptdate = pickDate(strDepatureDate);
					String trip = strTripType.split("/")[2];
					inputBookingDetails(trip, strOrigin, strDestination, deptdate,origin2,departure2, strReturnDate,
							strAdultCount, strChildCount, strInfantCount, strPromo,Currency,strPaymentType);
					selectClass(strBookingClass, strTripType);
					inputPassengerDetails(strFlightType, strTotalPessenger, strNationality, strDocumentType, 
							strDocumentNum, strNaSmile, strMobile, strEmail,"","","");
					waitforElement(BookingPageLocators.baggagetittle);
					waitUtilElementhasAttribute(BookingPageLocators.body);
					if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)){
					clickContinueBtn();
						}else{
							System.out.println("No Baggage Page");
						}
					waitForElementPresent(BookingPageLocators.selectseattittle, "Select Seat Tittle");
					waitUtilElementhasAttribute(BookingPageLocators.body);
					if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)==true){
					click(BookingPageLocators.continueBtn, "Contiue");
						if(isElementDisplayedTemp(BookingPageLocators.ok)){
							click(BookingPageLocators.ok, "OK");
						}
					}
					payment(strPaymentType,"");
					String strPNR = getReferenceNumber();
							
					searchFlightCheckin(strPNR, strEmail, "", "");
					if(isElementDisplayedTemp(BookingPageLocators.ErrorMsg1)){
						String ErrorMsg = getText(BookingPageLocators.ErrorMsg1, "Error Message");
						if(ErrorMsg.contains("Online check-in is not available on this route. Please check-in at the airport.")){
							Reporter.SuccessReport("Validating Error Message For WCI", "Successfully Verified:"+ ErrorMsg);
						}else{
							Reporter.failureReport("Validating Erroe Message For WCI", "Expected Error Message not Came");
						}
						
					}	
								
					Reporter.SuccessReport("TC15_Err_6_ConformBooking1Adt1OW", "Pass");
					
					
			
					}catch(Exception e){
						e.printStackTrace();
						Reporter.failureReport("TC15_Err_6_ConformBooking1Adt1OW", "Fail");
						
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
		    	xls.getCellValue("total pass", "Value"),
		    	xls.getCellValue("Adults Count", "Value"),
		    	xls.getCellValue("Child Count", "Value"),
		    	xls.getCellValue("Infant Count", "Value"),
		    	xls.getCellValue("Promo", "Value"),
		    	xls.getCellValue("Booking Class", "Value"),
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
    			"Validating Error Message for Conform Booking 1 Adult OW"}};
	}

}
