package flynas.androidchrome.scripts;

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

public class TC18_corporateLoginRToneAdultCancelFlight extends BookingPageFlow{
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"FL_WEB_18");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_18_corporateLoginRToneAdultCancelFlight( String username,String password,String bookingClass,
			String mobilenum,String paymentType,String newDate,String pickDate,String rtnDate,String origin,
			String dest,String triptype,String adult,String child,String infant,String seatSelect,String domOrInt,
			String totalPass,String nationality,String docNum,String docType,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			DateFormat dateFormat = new SimpleDateFormat("dd-MMMM yyyy");
			Date date = new Date();
		//	System.out.println(dateFormat.format(date));
			String deptdate = dateFormat.format(date);
			
			
			String 	newdate = pickDate(pickDate);
			
			System.out.println(newdate);
					
			click(BookingPageLocators.corporatelogin_lnk, "Login");
			switchtoChildWindow();
			login(username,password);
			inputBookingDetails(triptype,origin, dest, deptdate , "", "", rtnDate,adult, child, infant,"","","");
			selectClass(bookingClass, "Economy");
			inputPassengerDetails(domOrInt, totalPass, nationality,docType,docNum, "",mobilenum, username+"@gmail.com", "", "", "");;
			waitforElement(BookingPageLocators.baggagetittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)){
			click(BookingPageLocators.continueBtn, "Continue");
				}else{
					System.out.println("No Baggage Page");
				}
			selectSeat(seatSelect, "");	
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			searchFlight(strPNR, username+"@Gmail.com", "", "");
			cancelFlight();
									
			Reporter.SuccessReport("TC18_corporateLoginRToneAdultCancelFlight", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC18_corporateLoginRToneAdultCancelFlight", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    			xls.getCellValue("EmployeEmail", "Value"),
	    			xls.getCellValue("Password", "Value"),
	    		xls.getCellValue("Booking Class", "Value"),
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Payment Type", "Value"),
	    		xls.getCellValue("NewDate", "Value"),
	    		xls.getCellValue("Departure Date", "Value"),
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Origin", "Value"),
	    		xls.getCellValue("Destination", "Value"),
	    		xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		"Extra Leg Room",
	    		xls.getCellValue("Flight Type", "Value"),
	    		xls.getCellValue("Total Passenger", "Value"),
	    		xls.getCellValue("Nationality", "Value"),
	    		xls.getCellValue("Doc Number", "Value"),
	    		xls.getCellValue("Document Type", "Value"),
	    		"Validate member Login Round Trip Cancel Flight"}};
	}

}
