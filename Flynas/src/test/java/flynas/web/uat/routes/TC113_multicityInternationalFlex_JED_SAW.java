package flynas.web.uat.routes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC113_multicityInternationalFlex_JED_SAW extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUATRoutes"),"AllRoutes");

	@Test(dataProvider = "testData",groups={"Flex"})
	public  void TC_113_multicityInternationalFlex_JED_SAW( String bookingClass, String bundle,
			String mobilenum,
			String paymentType,
			String newDate,
			String depDate,String rtnDate,
			String origin,
			String dest,String triptype,String adult,String child,String infant,String strTolPass,String seatSelect,
			String Description) throws Throwable {
		try {
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String[] Credentials = pickCredentials("UserCredentials");
			String username =Credentials[0];
			String password =Credentials[1];			
			String deptDate = pickDate(depDate);
			String retrnDate = pickDate(rtnDate);
			click(BookingPageLocators.login_lnk, "Login");

			login(username,password);
			inputBookingDetails(triptype,origin, dest, deptDate , "", "", retrnDate,adult, child, infant,"","","");
			selectClass(bookingClass, bundle); 
			clickContinueBtn();
			upSellPopUpAction("Continue");
			waitforElement(BookingPageLocators.passengerDetailsTittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			clickContinueBtn();
			waitforElement(BookingPageLocators.baggagetittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			clickContinueBtn();
			waitforElement(BookingPageLocators.selectseattittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			selectSeat(seatSelect, "");
			if(isElementDisplayedTemp(BookingPageLocators.ok)){
				click(BookingPageLocators.ok, "OK");
			}
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			validate_ticketStatus(strPNR);
		
			searchFlightCheckin(strPNR, username, "","");
			performCheckin(seatSelect,paymentType,strTolPass);
			validateCheckin();
		
			Reporter.SuccessReport("TC113_multicityInternationalFlex_JED_SAW", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC113_multicityInternationalFlex_JED_SAW", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    		xls.getCellValue("Booking Class", "Value"),
	    		xls.getCellValue("Bundle", "Value"),
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Payment Type", "Value"),
	    		xls.getCellValue("NewDate", "Value"),
	    		xls.getCellValue("Departure Date", "Value"),
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Origin", "Value51"),
	    		xls.getCellValue("Destination", "Value51"),
	    		xls.getCellValue("Trip Type", "Value3"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		xls.getCellValue("Total Passenger", "Value"),
	    		xls.getCellValue("Select Seat", "Value"),
	    		"Validate MultiCity International Flex_JED_SAW"}};
	}

}
