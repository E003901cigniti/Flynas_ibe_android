package flynas.androidchrome.scripts;


import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ConfiguratorSupport;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.androidchrome.workflows.BookingPageFlow;

public class TC05_onewaydomestic8adults extends BookingPageFlow {
	
//	public static Logger logger = Logger.getLogger(TC05_onewaydom8Business.class.getName());
	
	public static ConfiguratorSupport configProps=new ConfiguratorSupport("config.properties");
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataForAndroid"),"TC05_oneWayDom8AdultCheckin");
	
	@Test(dataProvider = "testData",groups={"Chrome"})
	public void TC_05_onewaydom8Business(String strTripType, String strFlightType, String strOrigin,
			String strDestination, String strDepatureDate,String origin2,String departure2, String strReturnDate, String strTotPass,
			String strAdultCount, String strChildCount, String strInfantCount, String strPromo, 
			String strBookingClass, String strNationality, String strDocumentType,	String strDocumentNum,
			String strNaSmile,  String strMobile, String strEmail, String strSelectSeat, String strPaymentType,String bookingtype,
			String strNewDate, String charity,String Currency)throws Throwable{
				try{
					String description = "Validate oneway Domastic 8 Adults Business";
					TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
					
					strDepatureDate = pickDate(strDepatureDate);
					inputBookingDetails(strTripType, strOrigin, strDestination, strDepatureDate, origin2,departure2,strReturnDate,
							strAdultCount, strChildCount, strInfantCount, strPromo,Currency,strPaymentType);
					selectClass(strBookingClass, strTripType);
					String strLastName[] = inputPassengerDetails(strFlightType, strTotPass, strNationality, strDocumentType, 
							strDocumentNum, strNaSmile, strMobile, strEmail,"","","");
					
					Baggage_Extra(strTripType);
					addSportsEqpmnt(strTripType);
					click(BookingPageLocators.continueBtn, "Continue");
					selectallSeats(strSelectSeat,strTotPass,strTripType);
					payment(strPaymentType,"");
					String strpnr = getReferenceNumber();
					String strPNR = strpnr.trim();
					validate_ticketStatus(strPNR);
					searchFlightCheckin(strPNR, "", "", strLastName[1]);
					performCheckin(strSelectSeat, strPaymentType,strTotPass);
					validateCheckin();
					
					Reporter.SuccessReport("TC_05_onewaydom8Business", "Pass");
					
								
					}catch(Exception e){
						e.printStackTrace();
						Reporter.failureReport("TC_05_onewaydom8Business", "Fail");
						
					}
	}
		
		@DataProvider(name="testData")
		public Object[][] createdata1() {
		    return (Object[][]) new Object[][] {{
		    	xls.getCellValue("Trip Type", "Value"),
		    	xls.getCellValue("Flight Type", "Value"),
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
		    	xls.getCellValue("Nationality", "Value"),
		    	xls.getCellValue("Document Type", "Value"),
		    	xls.getCellValue("Doc Number", "Value"),
		    	"1234567890",
    			xls.getCellValue("Mobile", "Value"),
    			xls.getCellValue("Email Address", "Value"),
    			xls.getCellValue("Select Seat", "Value"),
    			"Credit Card",
    			"",
    			xls.getCellValue("New Date", "Value"),
    			xls.getCellValue("Charity Donation", "Value"),""}};
	}
	
}

