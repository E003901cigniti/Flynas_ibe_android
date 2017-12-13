package flynas.web.uat.routes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC192_agencyLoginRToneAdultoneChildCancelFlight extends BookingPageFlow {
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEUATRoutes"),"FL_WEB_19");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_192_agencyLoginRToneAdultoneChildCancelFlight( String bookingClass,
			String mobilenum,
			String paymentType,
			String newDate,
			String depDate,String rtnDate,
			String origin,
			String dest,String triptype,String adult,String child,String infant,
			String totalPass,String smiles,String nationality,String doctype,String docNum,
			String emailId,String domOrInt,
			String Description) throws Throwable {
		try {
			
					
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String[] Credentials = pickCredentials("UserCredentials");
			String username =Credentials[0];
			String password =Credentials[1];
			String 	deptdate = pickDate(depDate);
			
			click(BookingPageLocators.agency_lnk, "Agency Login");
			switchtoChildWindow();
			login(username,password);
			inputBookingDetails(triptype,origin, dest, deptdate, "", "", rtnDate,adult, child, infant,"","","");
			selectClass(bookingClass, "");
			inputPassengerDetails(domOrInt, totalPass, nationality, doctype, docNum, smiles, mobilenum, emailId, "", "", "");
			waitforElement(BookingPageLocators.baggagetittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(baggagetittle)==true)
			{
				clickContinueBtn();
			}else{
				System.out.println("No Baggage Availab;e");
			}
			waitforElement(BookingPageLocators.selectseattittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(selectseattittle)==true)
			{
				clickContinueBtn();
				if(isElementDisplayedTemp(BookingPageLocators.ok)){
					click(BookingPageLocators.ok, "OK");
				}
			}else{
				System.out.println("No seat Availab;e");
			}
			payment(paymentType, "");
			String strpnr = getReferenceNumber();
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			click(BookingPageLocators.passenger_header, "Passenger");
			waitUtilElementhasAttribute(BookingPageLocators.body);
			driver.navigate().refresh();
			waitforElement(BookingPageLocators.accountBalance);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			String AccountBalanceold = getText(BookingPageLocators.accountBalance, "Account Balance");
			String[] acctbalold = AccountBalanceold.split("SAR");
			String acountbal = acctbalold[1].trim();
			waitUtilElementhasAttribute(BookingPageLocators.body);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//td[contains(text(),'"+strPNR+"')]")));
			waitUtilElementhasAttribute(BookingPageLocators.body);
			
			click(BookingPageLocators.manageBookings_agentportal(strPNR), "manageMyBookings");
			String priceBeforChange = agentcancelFlight("All");
			waitforElement(BookingPageLocators.Home);
			waitUtilElementhasAttribute(BookingPageLocators.body);
 			click(BookingPageLocators.Home, "Home");
 			waitUtilElementhasAttribute(BookingPageLocators.body);
			driver.navigate().refresh();
			waitUtilElementhasAttribute(BookingPageLocators.body);	
			String AccountBalancenew = getText(BookingPageLocators.accountBalance, "Account Balance");
			String[] acctbalnew = AccountBalancenew.split("SAR");
			String newactbal = acctbalnew[1].trim();
			String newbalance = newactbal.replaceAll("\\,", "");
			
			System.out.println(acountbal);
		
			double balance = Double.parseDouble(acountbal.replaceAll("\\,", ""))+Double.parseDouble(priceBeforChange.trim());
			String Balance = Double.toString(balance);
			
			if(newbalance.contains(Balance))
			{
				Reporter.SuccessReport("Verifing Account Balance Changed or not After Cancel Flight", "Account balance increased");
			}
			else
			{
				Reporter.failureReport("Verifing Account Balance Changed or not After Cancel Flight", "Account balance not Changed");
			}
			
			Reporter.SuccessReport("TC192_agencyLoginRToneAdultoneChildCancelFlight", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC192_agencyLoginRToneAdultoneChildCancelFlight", "Failed");
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
	    		xls.getCellValue("Total Passenger", "Value"),
	    		xls.getCellValue("na Smiles", "Value"),
	    		xls.getCellValue("Nationality", "Value"),
	    		xls.getCellValue("Document Type", "Value"),
	    		xls.getCellValue("Doc Number", "Value"),
	    		xls.getCellValue("Email Address", "Value"),
	    		xls.getCellValue("Flight Type", "Value"),
	    		"Validate Agency Login RoundTrip One Adult One CHildCancel Flight"}};
	}
}
