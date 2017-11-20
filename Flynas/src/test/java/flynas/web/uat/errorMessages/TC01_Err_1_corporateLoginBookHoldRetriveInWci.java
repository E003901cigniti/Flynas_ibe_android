package flynas.web.uat.errorMessages;

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

public class TC01_Err_1_corporateLoginBookHoldRetriveInWci extends BookingPageFlow{
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"ErrorMessages_1");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_01_Err_1_corporateLoginBookHoldRetriveInWci( String username,String password,String bookingClass,
			String mobilenum,String paymentType,String newDate,String pickDate,String rtnDate,String origin,
			String dest,String triptype,String adult,String child,String infant,String seatSelect,String domOrInt,
			String totalPass,String nationality,String docNum,String docType,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String deptdate = pickDate(pickDate);
			
			System.out.println(deptdate);
					
			click(BookingPageLocators.corporatelogin_lnk, "Login");
			switchtoChildWindow();
			login(username,password);
			inputBookingDetails(triptype,origin, dest, deptdate , "", "", rtnDate,adult, child, infant,"","","");
			selectClass(bookingClass, "Economy");
			inputPassengerDetails(domOrInt, totalPass, nationality,docType,docNum, "",mobilenum, username+"@gmail.com", "", "", "");;
			waitforElement(BookingPageLocators.baggagetittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)){
			clickContinueBtn();
				}else{
					System.out.println("No Baggage Page");
				}
			waitForElementPresent(BookingPageLocators.selectseattittle, "SelectSeatTittle");
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)){
				clickContinueBtn();
				 if(isElementDisplayedTemp(BookingPageLocators.ok))
				 {
					 click(BookingPageLocators.ok, "OK");
				 }
			}else{
						System.out.println("No Seat Page");
			}
			payment(paymentType, "");
			String strpnr = getReferenceNumber().trim();
			searchFlightCheckin(strpnr, username+"@gmail.com", "", "");
			if(isElementDisplayedTemp(BookingPageLocators.ErrorMsg1)){
				String ErrorMsg = getText(BookingPageLocators.ErrorMsg1, "Error Message");
				if(ErrorMsg.contains("The booking cannot be changed. Please call our call centre on 92000 1234.")){
					Reporter.SuccessReport("Validating Error Message For WCI", "Successfully Verified:" +ErrorMsg);
				}else{
					Reporter.failureReport("Validating Erroe Message For WCI", "Expected Error Message not Came");
				}
			}	
											
			Reporter.SuccessReport("TC01_Err_1_corporateLoginBookHoldRetriveInWci", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC01_Err_1_corporateLoginBookHoldRetriveInWci", "Failed");
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
	    		"Validate Error Message Book A Hold PNR Try to Retrive in WCI"}};
	}

}
