package flynas.androidchrome.scripts;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ConfiguratorSupport;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.androidchrome.workflows.BookingPageFlow;

public class TC07_RTDom4adultBusinessCC extends BookingPageFlow {
	
	public static Logger logger = Logger.getLogger(TC07_RTDom4adultBusinessCC.class.getName());
	
	public static ConfiguratorSupport configProps=new ConfiguratorSupport("config.properties");
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataForAndroid"),"TC07_RTDom4adultBusinessCC");
	
	@Test(dataProvider = "testData",groups={"Chrome"})
	public void TC_07_RTDom4adultBusinessCC(String strTripType, String strFlightType, String strOrigin,String strDestination,
			String strDepatureDate, String origin2,String departure2,String strReturnDate, String strTotalPessenger,String strAdultCount,
			String strChildCount,String strInfantCount,String strPromo,String strBookingClass,String strNationality, String strDocumentType,	String strDocumentNum,String strNaSmile, String strMobile,String strEmail, String strSelectSeat, String strPaymentType,String bookingtype,
			String strNewDate, String charity,String Currency)throws Throwable{
				try{
					String description = "Validate RT Domestic 4adult BusinessCC ";
					TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
					String	deptdate = pickDate(strDepatureDate);
					inputBookingDetails(strTripType, strOrigin, strDestination, deptdate,origin2,departure2, strReturnDate,
							strAdultCount, strChildCount, strInfantCount, strPromo,Currency,strPaymentType);
					selectClass(strBookingClass, strTripType);
					inputPassengerDetails(strFlightType, strTotalPessenger, strNationality, strDocumentType, 
							strDocumentNum, strNaSmile, strMobile, strEmail,"","","");
					waitforElement(BookingPageLocators.baggagetittle);
					waitUtilElementhasAttribute(BookingPageLocators.body);
					if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)==true){
						click(BookingPageLocators.continueBtn, "Continue");
					}else{
						System.out.println("No Baggage Page");
					}
					
					selectallSeats(strSelectSeat, strTotalPessenger, strTripType);
					payment(strPaymentType,"");
					String strPNR = getReferenceNumber();
					System.out.println(strPNR);
					validate_ticketStatus(strPNR);
					
					Reporter.SuccessReport("TC07_RTDom4adultBusinessCC", "Pass");
					
					driver.close();
			
					}catch(Exception e){
						e.printStackTrace();
						Reporter.failureReport("TC07_RTDom4adultBusinessCC", "Fail");
						driver.close();
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
		    	"","",
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
    			"Credit Card","",
    			xls.getCellValue("New Date", "Value"),
    			xls.getCellValue("Charity Donation", "Value"),""}};
	}
	
}

