package flynas.web.production.regression;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;
import flynas.web.workflows.BookingPageFlow;

public class TC12_verifingServiceChargeRTSingleFlexIntAR extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestDataIBEProdReg"),"TC12");

	@Test(dataProvider = "testData",groups={"Production"})
	public  void TC_12_verifingServiceChargeRTSingleFlexIntAR( String bookingClass, String bundle,String mobilenum,
			String paymentType,String newDate,String departuredate,String rtnDate,String origin,String dest,String triptype,String adult,String child,String totalpass,String infant,String seatSelect,
			String nationality,String docNum,String flightType,String Doctype,String BookingClassSr,String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			String deptDate = pickDate(departuredate);	
			String rtrnDate = pickDate(rtnDate);
			click(BookingPageLocators.Arabic_pdctn_AR("العربية"), "Arabic Language");
			String[] Credentials = pickCredentials("UserCredentials");
			String username =Credentials[0];
			String password =Credentials[1];
			
			click(BookingPageLocators.login_lnk, "Login");
			login(username,password);
			inputBookingDetails_Arabic(triptype,origin, dest, deptDate , "", "", rtrnDate,adult, child, infant,"","","");
			selectClass(bookingClass, bundle);
			clickContinueBtn();
			upSellPopUpAction("Continue");
			continueOnPassengerDetails();
			coninueOnBaggage();
			waitforElement(BookingPageLocators.selectseattittle);
			waitUtilElementhasAttribute(BookingPageLocators.body);
			if(isElementDisplayedTemp(BookingPageLocators.selectseattittle)==true){
				clickContinueBtn();
				if(isElementDisplayedTemp(BookingPageLocators.ok_pdctn_AR)){
					click(BookingPageLocators.ok_pdctn_AR, "OK");
				}
			}else{
				System.out.println("No seat page Available");
			}			
			payment_Production_Arabic(paymentType);
			verifyPNRforSadad_Arabic();
			
			verifingServiceCharge(triptype, BookingClassSr.trim(), totalpass);
			Reporter.SuccessReport("TC12_verifingServiceChargeRTSingleFlexIntAR", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.SuccessReport("TC12_verifingServiceChargeRTSingleFlexIntAR", "Failed");
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
		    		xls.getCellValue("BookingClassSr", "Value"),
		    		"Verifing Service Charge RT Single Flex International AR"}};
	}

}
