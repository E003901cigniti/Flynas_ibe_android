package flynas.androidchrome.scripts;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC24_rtDomestic4AdultBusinessAR extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"FL_WEB_24");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_24_rtDomestic4AdultBusinessAR(String tripType, String origin, String dest,String deptDate,String origin2,String departure2,
			String retdate, String strTolPass, String Adult,String Child,String infant, String promo, String strBookingClass,
			String FlightType,String totalpass,String nationality,String Doctypr,String docNumber,
			String naSmiles,String Mobile,String email ,String SelectSeat,String paymenttype, String bookingtype,String Charity, 
			String Currency,String Description
			)  throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			String deptdate = pickDate(deptDate);
			
			click(BookingPageLocators.Arabic_pdctn_AR("العربية"), "Arabic Language");
			inputBookingDetails_Arabic(tripType,origin, dest, deptdate , origin2, departure2, retdate,Adult, Child, infant,"","","");
			selectClass(strBookingClass,tripType);
			inputPassengerDetails_Arabic(FlightType, totalpass, nationality, Doctypr,docNumber,"", Mobile, email, "", "", "");
			waitforElement(BookingPageLocators.baggagetittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)==true)
			{
				click(BookingPageLocators.continueBtn, "Continue");
			}else{
				System.out.println("No Baggage Page");
			}
			waitforElement(BookingPageLocators.selectseattittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)==true)
			{
				click(BookingPageLocators.continueBtn, "Continue");
				if(isElementDisplayedTemp(BookingPageLocators.ok)==true){
					click(BookingPageLocators.ok, "Ok");
				}
			}else{
				System.out.println("No Seat Page");
			}
			payment(paymenttype, "");
			waitforElement(BookingPageLocators.summaryRefNumber_AR_uat);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			String strpnr = getText(BookingPageLocators.summaryRefNumber_AR_uat,"PNR");
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			validate_ticketStatus_AR(strPNR);
			
			
			Reporter.SuccessReport("TC24_rtDomestic4AdultBusinessAR", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC24_rtDomestic4AdultBusinessAR", "Failed");
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
	    		xls.getCellValue("Total Passenger", "Value"),
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
    			xls.getCellValue("Charity Donation", "Value"),"",
	    		"Validate Round Trip Domestic 4-Adult Business in AR"}};
	}

}
