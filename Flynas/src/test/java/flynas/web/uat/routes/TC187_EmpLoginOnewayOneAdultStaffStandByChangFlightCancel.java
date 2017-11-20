package flynas.web.uat.routes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC187_EmpLoginOnewayOneAdultStaffStandByChangFlightCancel extends BookingPageFlow{
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUAT"),"FL_WEB_15");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_187_EmpLoginOnewayOneAdultStaffStandByChangFlightCancel( String bookingClass,
			String mobilenum,
			String paymentType,
			String newDate,
			String depDate,
			String origin,
			String dest,String triptype,String adult,String child,String infant,
			String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
						
			String[] Credentials = pickCredentials("UATcredentials");
			String username =Credentials[0];
			String password =Credentials[1];			
			String deptDate = pickDate(depDate);
						
			click(emplogin_lnk, "Employe Login");
			switchtoChildWindow();
			login(username,password);
			inputBookingDetails(triptype,origin, dest, deptDate, "", "", "",adult, child, infant,"","",paymentType);
			selectClassForStaff(bookingClass);
			waitforElement(BookingPageLocators.passengerDetailsTittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(getText(BookingPageLocators.mobileNum,"MobileNumber").length() == 0)
			{
				type(BookingPageLocators.mobileNum, mobilenum, "MobileNumber");
				clickContinueBtn();
			}
			else
			{
				clickContinueBtn();
			}
			/*Thread.sleep(3000);
			clickContinueBtn();
			Thread.sleep(5000);
			clickContinueBtn();
			Thread.sleep(5000);
			click(BookingPageLocators.ok, "OK");*/
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			
			String newdate = pickDate(newDate);
			String strPNRChangeDate = changeDate(strPNR, username, mobilenum, "", newdate, "","",bookingClass,0);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			System.out.println(strPNRChangeDate);
			if(strPNRChangeDate.trim().equalsIgnoreCase(strPNR)){
				Reporter.SuccessReport("Change Flight Date", "Flight Date has changed successfully");
			}else{
				Reporter.SuccessReport("Change Flight Date", "Flight Date has NOT changed successfully");
			}			
			waitforElement(BookingPageLocators.Home);
			click(BookingPageLocators.Home, "Home");
			waitUtilElementhasAttribute(BookingPageLocators.body);
			driver.navigate().refresh();
			waitUtilElementhasAttribute(BookingPageLocators.body);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[contains(text(),'"+strPNR+"')]")));
			waitUtilElementhasAttribute(BookingPageLocators.body);
			click(BookingPageLocators.manageMyBookings(strPNR), "ManageMyBookings");
			cancelFlight();
			
			Reporter.SuccessReport("TC187_EmpLoginOnewayOneAdultStaffStandByChangFlightCancel", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC187_EmpLoginOnewayOneAdultStaffStandByChangFlightCancel", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    		xls.getCellValue("EmployeEmail", "Value"),
	    		
	    		xls.getCellValue("Booking Class", "Value"),
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Payment Type", "Value"),
	    		xls.getCellValue("New Date", "Value"),
	    		xls.getCellValue("Departure Date", "Value"),
	    		xls.getCellValue("Origin", "Value"),
	    		xls.getCellValue("Destination", "Value"),
	    		xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		"Validate Employe Login Oneway One Adult StaffStandBy ChangFlight Cancel"}};
	}
}
