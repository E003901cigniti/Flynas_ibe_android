package flynas.web.uat.routes;

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

public class TC188_EmpLoginStaffConfmedChangeDateCheckIn extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUATRoutes"),"FL_WEB_16");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_188_EmpLoginStaffConfmedChangeDateCheckIn( String bookingClass,
			String mobilenum,
			String paymentType,
			String newDate,
			String depDate,String rtnDate,
			String origin,
			String dest,String triptype,String adult,String child,String infant,String selectseat,
			String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String[] Credentials = pickCredentials("UATcredentials");
			String username =Credentials[0];
			String password =Credentials[1];
			String 	deptdate = pickDate(depDate);
			
			click(emplogin_lnk, "Employe Login");
			switchtoChildWindow();
			login(username,password);
			Thread.sleep(10000);
			inputBookingDetails(triptype,origin, dest, deptdate, "", "", rtnDate,adult, child, infant,"","",paymentType);
			selectClassForStaff(bookingClass);
			waitforElement(BookingPageLocators.passengerDetailsTittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(getText(BookingPageLocators.mobileNum,"MobileNumber")=="")
			{
				type(BookingPageLocators.mobileNum, mobilenum, "MobileNumber");
				clickContinueBtn();
			}
			else
			{
				clickContinueBtn();
			}
			waitforElement(BookingPageLocators.selectseattittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			clickContinueBtn();
			if(isElementDisplayedTemp(BookingPageLocators.ok)==true){
			click(BookingPageLocators.ok, "OK");
			}
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			
			String newdate = pickDate(newDate);
			String strPNRChangeDate = changeDate(strPNR, username, mobilenum, "", newdate, selectseat,"",bookingClass,1);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			System.out.println(strPNRChangeDate);
			if(strPNRChangeDate.trim().equalsIgnoreCase(strPNR)){
				Reporter.SuccessReport("Change Flight Date", "Flight Date has changed successfully");
			}else{
				Reporter.SuccessReport("Change Flight Date", "Flight Date has NOT changed successfully");
			}
			searchFlightCheckin(strPNR, username, "", "");
			performCheckin("","","");
			validateCheckin();
			
			Reporter.SuccessReport("TC188_EmpLoginStaffConfmedChangeDateCheckIn", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC188_EmpLoginStaffConfmedChangeDateCheckIn", "Failed");
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
	    		xls.getCellValue("NewDate", "Value"),
	    		xls.getCellValue("Departure Date", "Value"),
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Origin", "Value"),
	    		xls.getCellValue("Destination", "Value"),
	    		xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		xls.getCellValue("Select Seat", "Value"),
	    		"Validate Employe Login Round Trip One Adult StaffConformed ChangFlight CheckIN"}};
	}

}
