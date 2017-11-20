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

public class TC149_roundTripDomesticBusiness_DMM_ABT extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"TC_01_oneWayDomesticEcoSADAD");

	@Test(dataProvider = "testData",groups={"Business"})
	public  void TC_149_roundTripDomesticBusiness_DMM_ABT( String bookingClass,
			String mobilenum,
			String paymentType,
			String newDate,
			String depDate,String rtnDate,
			String origin,
			String dest,String triptype,String adult,String child,String infant,String seatSelect,
			String Description) throws Throwable {
		try {

			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);

			String[] Credentials = pickCredentials("UATcredentials");
			String username =Credentials[0];
			String password =Credentials[1];			
			String deptDate = pickDate(depDate);
			String rtrnDate = pickDate(rtnDate);
			click(BookingPageLocators.login_lnk, "Login");
			login(username,password);
			inputBookingDetails(triptype,origin, dest, deptDate , "", "", rtrnDate,adult, child, infant,"","","");
			selectClass(bookingClass, triptype);
			waitforElement(BookingPageLocators.passengerDetailsTittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			clickContinueBtn();
			Baggage_Extra(triptype);
			clickContinueBtn();
			waitforElement(BookingPageLocators.selectseattittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			clickContinueBtn();
			if(isElementDisplayedTemp(BookingPageLocators.ok)){
				click(BookingPageLocators.ok, "OK");
			}
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			validate_ticketStatus(strPNR);


			Reporter.SuccessReport("TC149_roundTripDomesticBusiness_DMM_ABT", "Pass");

		}

		catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC149_roundTripDomesticBusiness_DMM_ABT", "Failed");
		}
	}

	@DataProvider(name="testData")
	public Object[][] createdata1() {
		return (Object[][]) new Object[][] { 
			{
				
				
				xls.getCellValue("Booking Class", "Value"),
				xls.getCellValue("Mobile", "Value"),
				xls.getCellValue("Payment Type", "Value"),
				xls.getCellValue("NewDate", "Value"),
				xls.getCellValue("Departure Date", "Value"),
				xls.getCellValue("Return Date", "Value"),
				xls.getCellValue("Origin", "Value25"),
				xls.getCellValue("Destination", "Value25"),
				xls.getCellValue("Trip Type", "Value2"),
				xls.getCellValue("Adults Count", "Value"),
				xls.getCellValue("Child Count", "Value"),
				xls.getCellValue("Infant Count", "Value"),
				xls.getCellValue("Select Seat", "Value3"),
			"Validate RoundTrip Domestic Business_DMM_ABT"}};
	}
}
