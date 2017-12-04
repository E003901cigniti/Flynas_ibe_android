package flynas.android.scripts.uat.reg;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.android.testObjects.BookingPageLocators;
import flynas.android.workflows.BookingPageFlow;
import flynas.android.workflows.Homepage;

public class TC16_PaywithNasCredit extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataForAndroid"),"FL_WEB_12");

	@Test(dataProvider = "testData",groups={"Android"})
	public  TC16_PaywithNasCredit(String tripType,String origin,String dest,String deptDate,String origin2,String departure2,
			String retdate,String Audalt,String Child,String infant,String promo,String strBookingClass,String FlightType,String totalpass,
			String namtionality,String Doctypr,String docNumber,String naSmiles,String Mobile,String email ,String SelectSeat,
			String paymenttype,String bookingtype,String charity,String Currency,String payment2,String username,String password,
			String Description)throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			// Handlepopup();		
			

			String depDate = pickDate(deptDate);
			String rtrndate = pickDate(retdate);

			Homepage homepage = new Homepage();

			homepage.select_TittleMenu();
			homepage.Click_login();
			homepage.Login(username,password);
			homepage.select_Bookflights("registered");
			inputBookingDetails(tripType, origin, dest, depDate, origin2, departure2, rtrndate, Audalt, Child, infant, promo, Currency);
			selectClass(strBookingClass, tripType);
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			scrollToText("Email Address*");
			scrollToElement(BookingPageLocators.continuebtn);
			click(BookingPageLocators.continuebtn, "Continue");
			waitforElement(BookingPageLocators.baggagetittle);
			if(isElementDisplayed(BookingPageLocators.baggagetittle)==true)
			{
				click(BookingPageLocators.continuebtn, "Continue");
			}else{
				System.out.println("No Baggage Available");
			}
			selectSeat(SelectSeat, bookingtype, tripType);
			
			payment(paymenttype,"");
			String strPNR = getReferenceNumber();
			System.out.println(strPNR);				
			validate_ticketStatus();
			navigatetoHmPg();
			if(isElementPresent(BookingPageLocators.loveFlynasApp)==true)
			{
				click(BookingPageLocators.noThanks, "No Thanks");
			}
			else
			{
				System.out.println("No Alert");
			}
			homepage.select_Managebooking("Anonymous");
			registeredUsrManageFlight(strPNR);
			registeredUsrcancelFlight();
			confirmChanges();
			waitforElement(BookingPageLocators.tittleHome);
			navigatetoHmPg();
			if(isElementPresent(BookingPageLocators.loveFlynasApp)==true)
			{
				click(BookingPageLocators.noThanks, "No Thanks");
			}
			else
			{
				System.out.println("No Alert");
			}

			homepage.select_Bookflights("registered");
			click(BookingPageLocators.findFlights, "FindFlights");
			selectClass(strBookingClass, tripType);
			//	inputPassengerDetails(FlightType,totalpass,namtionality,Doctypr,docNumber, naSmiles,Mobile,email,"Javeed","Khan",payment2);
			waitForElementPresent(BookingPageLocators.title, "Tittle");
			scrollToText("Email Address*");
			scrollToElement(BookingPageLocators.continuebtn);
			click(BookingPageLocators.continuebtn, "Continue");
			if(isElementDisplayed(BookingPageLocators.baggagetittle)==true)
			{
				click(BookingPageLocators.continuebtn, "Continue");
			}else{
				System.out.println("No Baggage Available");
			}
			selectSeat(SelectSeat, bookingtype,"");
			payment(payment2,strPNR);

			Reporter.SuccessReport("TC16_PaywithNasCredit", "Pass");


		}

		catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC16_PaywithNasCredit", "Failed");
		}
	}

	@DataProvider(name="testData")
	public Object[][] createdata1() {
		return (Object[][]) new Object[][] { 
			{xls.getCellValue("Trip Type", "Value"),xls.getCellValue("Origin", "Value"),xls.getCellValue("Destination", "Value"),
				xls.getCellValue("Departure Date", "Value"),"","",xls.getCellValue("Return Date", "Value"),xls.getCellValue("Adults Count", "Value"),
				xls.getCellValue("Child Count", "Value"),xls.getCellValue("Infant Count", "Value"),xls.getCellValue("Promo", "Value"),
				xls.getCellValue("Booking Class", "Value"),xls.getCellValue("Flight Type", "Value"),xls.getCellValue("Total Passenger", "Value"),
				xls.getCellValue("Nationality", "Value"),xls.getCellValue("Document Type", "Value"),xls.getCellValue("Doc Number", "Value"),
				"",xls.getCellValue("Mobile", "Value"),xls.getCellValue("Email Address", "Value"),xls.getCellValue("Select Seat", "Value"),
				xls.getCellValue("Payment Type", "Value"),"",xls.getCellValue("Charity Donation", "Value"),xls.getCellValue("Currency", "Value"),
				xls.getCellValue("Payment Type2", "Value"),xls.getCellValue("username", "Value"),xls.getCellValue("password", "Value"),
				"Validate Nas Credit One way Domestic with one Adualt"}};
	}



}
