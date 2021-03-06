package flynas.web.uat.regression;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC27_b_BookingwithInsufficientBronzepoints extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"FL_WEB_27");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC27b_BookingwithinsufficientBronzepoints(String tripType, String origin, String dest, 
			String deptDate, String origin2,String departure2, String retdate,String Adult,String Child,String infant, String promo, String strBookingClass, String bundle,
			String FlightType,String totalpass,String nationality,String Doctypr,String docNumber,
			String naSmiles,String Mobile,String SelectSeat,String paymenttype,String bookingtype, 
			String charity,String Currency, String accType,String Description
			) throws Throwable {
		try {
			//System.out.println(paymenttype);
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String deptdate = pickDate(deptDate);
			
			String[] Credentials = pickCredentials("UserCredentials");
			String username =Credentials[0];
			String password =Credentials[1];
			click(BookingPageLocators.login_lnk, "Login");
			switchtoChildWindow();
			login(username,password);
			
			inputBookingDetails(tripType, origin, dest, deptdate, origin2, departure2, retdate,Adult, Child, infant,promo,Currency,paymenttype);
			selectClass(strBookingClass, bundle);
			clickContinueBtn();
			upSellPopUpAction("Continue");
			continueOnPassengerDetails();
			coninueOnBaggage();
			continueOnSeatSelection();
			nasmilespayment(username,password);
			verifyAlertPopup();
				
			updateStatus("IBE_UAT_Reg","TC27_b_BookingwithInsufficientBronzepoints","Pass");
			Reporter.SuccessReport("TC27_b_BookingwithinsufficientBronzepoints", "Pass");
			}
		
	catch (Exception e) {
			e.printStackTrace();
			updateStatus("IBE_UAT_Reg","TC27_b_BookingwithInsufficientBronzepoints","Fail");
			Reporter.SuccessReport("TC27_b_BookingwithinsufficientBronzepoints", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    			xls.getCellValue("Trip Type", "Value"),
		    		xls.getCellValue("Origin", "Value2"),
		    		xls.getCellValue("Destination", "Value2"),
		    		xls.getCellValue("Departure Date", "Value2"),
		    		"",
		    		"",
		    		xls.getCellValue("Return Date", "Value2"),
		    		xls.getCellValue("Adults Count", "Value"),
		    		xls.getCellValue("Child Count", "Value"),
		    		xls.getCellValue("Infant Count", "Value"),
		    		xls.getCellValue("Promo", "Value"),
		    		xls.getCellValue("Booking Class", "Value"),

		    		xls.getCellValue("Bundle", "Value"),
		    		xls.getCellValue("Flight Type", "Value2"),
		    		xls.getCellValue("Total Passenger", "Value"),
		    		xls.getCellValue("Nationality", "Value"),
		    		xls.getCellValue("Document Type", "Value"),
		    		xls.getCellValue("Doc Number", "Value"),
		    		xls.getCellValue("naSmile", "Value"),
		    		xls.getCellValue("Mobile", "Value"),
		    		xls.getCellValue("Select Seat", "Value"),
		    		xls.getCellValue("Payment Type", "Value"),
		    		"",
	    			xls.getCellValue("Charity Donation", "Value"),
	    			xls.getCellValue("Currency", "Value"),
	    			xls.getCellValue("AccountType", "Value"),
		    		"Validate failure on Booking with insufficient Bronze points"}};
	}

}
