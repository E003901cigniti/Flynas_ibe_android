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

public class TC190_corporateLoginRToneAdultCancelFlight extends BookingPageFlow{
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUATRoutes"),"FL_WEB_18");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_190_corporateLoginRToneAdultCancelFlight( String bookingClass,
			String mobilenum,String paymentType,String newDate,String depDate,String rtnDate,String origin,
			String dest,String triptype,String adult,String child,String infant,String totalPass,String nationality,
			String docNum,String seatSelect,String domOrInt,String trveldoc,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String[] Credentials = pickCredentials("UserCredentials");
			String username =Credentials[0];
			String password =Credentials[1];
			String 	deptdate = pickDate(depDate);
					
			click(BookingPageLocators.corporatelogin_lnk, "Login");
			switchtoChildWindow();
			login(username,password);
			inputBookingDetails(triptype,origin, dest, deptdate , "", "", rtnDate,adult, child, infant,"","","");
			selectClass(bookingClass, "");
			waitforElement(BookingPageLocators.passengerDetailsTittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			inputPassengerDetails(domOrInt, totalPass, nationality,trveldoc,docNum, "", mobilenum, username+"@gmail.com", "", "", "");
			waitforElement(BookingPageLocators.baggagetittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(baggagetittle)==true)
			{
				clickContinueBtn();
			}else{
				System.out.println("No Baggage Availab;e");
			}
			selectSeat(seatSelect, "");	
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			searchFlight(strPNR, username+"@gmail.com", mobilenum, "");
			cancelFlight("All");
				
			
			
			
			Reporter.SuccessReport("TC190_corporateLoginRToneAdultCancelFlight", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC190_corporateLoginRToneAdultCancelFlight", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    			xls.getCellValue("EmployeEmail", "Value"),xls.getCellValue("Booking Class", "Value"),
	    			xls.getCellValue("Mobile", "Value"),xls.getCellValue("Payment Type", "Value"),xls.getCellValue("NewDate", "Value"),
	    			xls.getCellValue("Departure Date", "Value"),xls.getCellValue("Return Date", "Value"),xls.getCellValue("Origin", "Value"),
	    			xls.getCellValue("Destination", "Value"),xls.getCellValue("Trip Type", "Value"),xls.getCellValue("Adults Count", "Value"),
	    			xls.getCellValue("Child Count", "Value"),xls.getCellValue("Infant Count", "Value"),xls.getCellValue("Total Passenger", "Value"),
	    			xls.getCellValue("Nationality", "Value"),xls.getCellValue("Doc Number", "Value"),xls.getCellValue("Select Seat", "Value"),xls.getCellValue("Flight Type", "Value"),
	    			xls.getCellValue("Document Type", "Value"),"Validate member Login Round Trip Cancel Flight"}};
	}

}
