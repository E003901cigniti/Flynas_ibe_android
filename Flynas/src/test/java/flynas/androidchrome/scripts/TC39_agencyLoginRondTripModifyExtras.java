package flynas.androidchrome.scripts;

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

public class TC39_agencyLoginRondTripModifyExtras extends BookingPageFlow {
	
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"FL_WEB_19");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_39_agencyLoginRondTripModifyExtras( String username,String password,String bookingClass,String mobilenum,
			String paymentType,String newDate,String pickDate,String rtnDate,String origin,String dest,String triptype,String adult,
			String child,String infant,String totalPass,String smiles,String nationality,String doctype,String docNum,String emailId,
			String domOrInt,String seatSelect,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			
			String	deptdate = pickDate(pickDate);
			
			click(BookingPageLocators.agency_lnk, "Agency Login");
			switchtoChildWindow();
			login(username,password);
			inputBookingDetails(triptype,origin, dest, deptdate, "", "", rtnDate,adult, child, infant,"","","");
			selectClass(bookingClass, "");
			inputPassengerDetails(domOrInt, totalPass, nationality, doctype, docNum, smiles, mobilenum, emailId, "", "", "");
			waitforElement(BookingPageLocators.baggagetittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.baggagetittle)){
			click(BookingPageLocators.continueBtn, "Continue");
				}else{
					System.out.println("No Baggage Page");
				}
			waitforElement(BookingPageLocators.selectseattittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)){
			click(BookingPageLocators.continueBtn, "Continue");
			if(isElementDisplayedTemp(BookingPageLocators.ok)){
				click(BookingPageLocators.ok, "OK");
			}
				
			}else{
					System.out.println("No Seat Page");
			}
			payment(paymentType, "");
			String strPNR = getReferenceNumber().trim();
			searchFlight(strPNR, username+"@gmail.com", "", "");
			click(BookingPageLocators.modifyExtras, "Modify Extras");
			Baggage_Extra(triptype);
			addSportsEqpmnt(triptype);
			Select_A_Meal();
			Selecting_loung();
			inputExtras("12");
			waitforElement(BookingPageLocators.manageMyBookingTittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			click(BookingPageLocators.modifySeat, "Seat Selection");
			waitforElement(BookingPageLocators.selectseattittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)){
				selectallSeats(seatSelect, "1", triptype);
			}else{
				System.out.println("No seat Select Page");
			}
			waitforElement(BookingPageLocators.paynow);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			click(BookingPageLocators.paynow, "Pay Now");
			payment(paymentType, "");
			String strpnr = getReferenceNumber().trim();
			validate_ticketStatus(strpnr);
			Reporter.SuccessReport("TC39_agencyLoginRondTripModifyExtras", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.SuccessReport("TC39_agencyLoginRondTripModifyExtras", "Failed");
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
	    		xls.getCellValue("Total Passenger", "Value"),
	    		xls.getCellValue("na Smiles", "Value"),
	    		xls.getCellValue("Nationality", "Value"),
	    		xls.getCellValue("Document Type", "Value"),
	    		xls.getCellValue("Doc Number", "Value"),
	    		xls.getCellValue("Email Address", "Value"),
	    		xls.getCellValue("Flight Type", "Value"),
	    		xls.getCellValue("Select Seat", "Value"),
	    		"Validate Agency Login RondTrip ModifyExtras"}};
	}
}