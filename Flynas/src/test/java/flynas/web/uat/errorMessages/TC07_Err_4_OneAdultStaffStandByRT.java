package flynas.web.uat.errorMessages;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC07_Err_4_OneAdultStaffStandByRT extends BookingPageFlow{
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"ErrorMessage_4");
	
	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_07_Err_4_OneAdultStaffStandByRT(String username,String password,String bookingClass,
			String mobilenum,String paymentType,String newDate,String pickDate,String origin,String dest,
			String rtndate,String triptype,String adult,String child,String infant,
			String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			 String depdat = pickDate(pickDate);
			 String trip = triptype.split("/")[0];
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			click(emplogin_lnk, "Employe Login");
			switchtoChildWindow();
			login(username,password);
			inputBookingDetails(trip,origin, dest, depdat, "", "", rtndate,adult, child, infant,"","",paymentType);
			selectClassForStaff(bookingClass);
			waitforElement(BookingPageLocators.mobileNum);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(getText(BookingPageLocators.mobileNum,"MobileNumber").length() == 0)
			{
				type(BookingPageLocators.mobileNum, mobilenum, "MobileNumber");
				waitUtilElementhasAttribute(BookingPageLocators.body);
				clickContinueBtn();
			}
			else
			{
				clickContinueBtn();
			}
			payment(paymentType, "");
			String strpnr = getReferenceNumber().trim();
			searchFlightCheckin(strpnr, username, "", "");
			if(isElementDisplayedTemp(BookingPageLocators.ErrorMsg1)){
				String ErrorMsg = getText(BookingPageLocators.ErrorMsg1, "Error Message");
				if(ErrorMsg.contains("Your fare class is not eligible for online check-in. Please check-in at the airport.")){
					Reporter.SuccessReport("Validating Error Message For WCI", "Successfully Verified:"+ ErrorMsg);
				}else{
					Reporter.failureReport("Validating Erroe Message For WCI", "Expected Error Message not Came");
				}
				
			}	
					
		
			Reporter.SuccessReport("TC07_Err_4_OneAdultStaffStandByRT", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC07_Err_4_OneAdultStaffStandByRT", "Failed");
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
	    		xls.getCellValue("Departure date", "Value"),
	    		xls.getCellValue("Origin", "Value"),
	    		xls.getCellValue("Destination", "Value"),
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		"Validating Error Message for Conform Booking 1 Adult StaffStandby  RoundTrip"}};
	}
}
