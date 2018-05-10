package flynas.web.production.regression;

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

public class TC01_verifingChildDiscount extends BookingPageFlow {
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEProdReg"),"TC01");
	

	@Test(dataProvider = "testData",groups={"Production"})
	public  void TC_01_verifingChildDiscount(String bookingClass, String bundle,
			String mobilenum,String paymentType,String newDate,	String Departuredate,String rtnDate,
			String origin,String dest,String triptype,String adult,String child,String totalpass,
			String infant,String seatSelect,String nationality,String docNum,String flightType,
			String Doctype,String Description) throws Throwable {
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
			inputPassengerDetails(flightType, totalpass, nationality, Doctype,docNum,"", mobilenum, username, "", "", "");
			coninueOnBaggage();
			waitforElement(BookingPageLocators.selectseattittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)==true){
				selectSeat(seatSelect, "");
				if(isElementPresent(BookingPageLocators.ok)==true){
				click(BookingPageLocators.ok, "OK");
			}else{
				System.out.println("No seat Available");
			}
			
			payment(paymentType, "");
			verifyPNRforSadad();
			verifingServiceCharge(triptype, bookingClass, totalpass);
			verifingChildDiscount(bookingClass);
			
			Reporter.SuccessReport("TC01_verifingChildDiscount", "Pass");
			
			}	}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC01_verifingChildDiscount", "Failed");
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
	    		"",
	    		xls.getCellValue("Departure Date", "Value"),
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Origin", "Value"),
	    		xls.getCellValue("Destination", "Value"),
	    		xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("total pass", "Value"),
	    		"0",
	    		"",
	    		xls.getCellValue("Nationality", "Value"),
	    		xls.getCellValue("Doc Number", "Value"),
	    		xls.getCellValue("Flight Type", "Value"),
	    		xls.getCellValue("Document Type", "Value"),
	    		"Validate Child Discount"}};
	}
}
