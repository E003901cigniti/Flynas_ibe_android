package flynas.web.production.routes;

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

public class TC26_oneWayDomesticEconomy_AHB_ABT extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataProdRoutes"),"Chrome_TestData");

	@Test(dataProvider = "testData",groups={"Economy"})
	public  void TC_26_oneWayDomesticEconomy_AHB_ABT( String bookingClass, String bundle, 
			String mobilenum,
			String paymentType,
			String newDate,
			String Departuredate,String rtnDate,
			String origin,
			String dest,String triptype,String adult,String child,String infant,String seatSelect,
			String newdate,	String Description) throws Throwable {
		try {
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String deptDate = pickDate(Departuredate);
			String retrnDate = pickDate(rtnDate);
			String[] Credentials = pickCredentials("UserCredentials");
				String username =Credentials[0];
				String password =Credentials[1];					
				
				click(BookingPageLocators.login_lnk, "Login");				
				login(username,password);
			inputBookingDetails(triptype,origin, dest, deptDate , "", "", retrnDate,adult, child, infant,"","","");
			selectClass(bookingClass, bundle);
			clickContinueBtn();
			upSellPopUpAction("Continue");
			continueOnPassengerDetails();
			coninueOnBaggage();
			selectSeat(seatSelect,"");
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			verifyPNRforSadad();
							
		
			Reporter.SuccessReport("TC26_oneWayDomesticEconomy_AHB_ABT", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC26_oneWayDomesticEconomy_AHB_ABT", "Failed");
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
	    		xls.getCellValue("Origin", "Value26"),
	    		xls.getCellValue("Destination", "Value26"),
	    		xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		xls.getCellValue("Select Seat", "Value"),
	    		xls.getCellValue("newDate", "Value"),
	    		"Validate oneWay Domestic Economy_AHB_ABT"}};
	}
	
	
}
