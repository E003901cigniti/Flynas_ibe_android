package flynas.android.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class BookingPageLocators extends ActionEngine{
	
	
	//Common
	public static By loadingBar = By.xpath("//div[@class='loading sk-wave']");
	public static By continueBtn = By.xpath("//button[contains(text(),'Continue')]");
	public static By Deny = By.xpath("//*[@resource-id='com.android.packageinstaller:id/permission_deny_button']");
	public static By tittleBack = By.xpath("//*[@resource-id='com.flynas.android.app:id/titleBack']");
	public static By done = By.xpath("//*[@resource-id='com.flynas.android.app:id/btnSelectPaxNumDone']");
	public static By cancel = By.xpath("//*[@resource-id='com.flynas.android.app:id/btnSelectPaxNumCancel']");
	public static By  elmWithText(String lable)
	{
		return By.xpath("//*[@text='"+lable+"']");
	}
	
 	//booking page
	public static By roundTrip = By.xpath("//*[@resource-id='com.flynas.android.app:id/vgSearchFlightReturn']");
	public static By oneWay = By.xpath("//*[@resource-id='com.flynas.android.app:id/vgSearchFlightOneWay']");
	public static By multiCity = By.xpath("//*[@resource-id='com.flynas.android.app:id/multicitySearchFlightButton']");
	
	public static String getelement(int index)
	{
		return "//android.widget.NumberPicker[@index='"+index+"']//android.widget.Button[@index=1]";
	}
	
	
	public static By bookingpageorigin = By.xpath("//div[@name='origin']/div[@placeholder='Origin']/span[@tabindex='-1']");
	public static By bookingpagetxtorigin = By.xpath("//div[@name='origin']/input[@placeholder='Origin']");
	public static By bookingpageDestination = By.xpath("//div[@name='destination']/div[@placeholder='Destination']/span[@tabindex='-1']");
	public static By bookingpagetxtDestination = By.xpath("//div[@name='destination']/input[@placeholder='Destination']");

	public static By origin = By.xpath("//*[@resource-id='com.flynas.android.app:id/vgSelectFlightOrigin']");
	public static By search = By.xpath("//*[@resource-id='com.flynas.android.app:id/findButton']");
	public static By searchEdit = By.xpath("//*[@resource-id='com.flynas.android.app:id/searchEdit']");
	public static By searched_city = By.xpath("//*[@resource-id='com.flynas.android.app:id/cityCode']");

	public static By Departuredate = By.xpath("//*[@resource-id='com.flynas.android.app:id/vgSearchFlightDepatureDate']");
	public static By Select_date =  By.xpath("//*[@resource-id='com.flynas.android.app:id/selectDateButton']");
	public static By Select_CC_Expdate =  By.xpath("//*[@resource-id='com.flynas.android.app:id/buttonDefaultPositive']");
	
	public static By Departuredate_rtp = By.xpath("//*[@resource-id='com.flynas.android.app:id/departure1SearchFlightButton']");
	public static By Returndate_rtp = By.xpath("//*[@resource-id='com.flynas.android.app:id/return1SearchFlightButton']");
	
	public static By Departuredate_multicity = By.xpath("//*[@resource-id='com.flynas.android.app:id/departure2SearchFlightButton']");
	public static By Returndate_multicity = By.xpath("//*[@resource-id='com.flynas.android.app:id/return2SearchFlightButton']");
	public static By origin_multicity = By.xpath("//*[@resource-id='com.flynas.android.app:id/origin2SearchFlightButton']");
	
	public static By Passengercount = By.id("com.flynas.android.app:id/tvSearchFlightPassengers");
	public static By Audaltplusbutton = By.id("com.flynas.android.app:id/adultPlusButton");
	public static By childplusbutton = By.id("com.flynas.android.app:id/childPlusButton");
	public static By infantplusbutton = By.id("com.flynas.android.app:id/infantPlusButton");
	public static By findFlights = By.id("com.flynas.android.app:id/vgSearchFlightFindFlights");
	
	public static By Currency = By.id("com.flynas.android.app:id/vgSelectCurrency");
	public static By currencytype(String Currency) {
		return By.xpath("//*[@text='"+Currency+"']");
	}

	//Passenger details
	public static By passengerLable = By.xpath("//*[@resource-id='com.flynas.android.app:id/passengerDetailsRowName']");
	public static By title = By.xpath("//*[@resource-id='com.flynas.android.app:id/passengerDetailTitle']");
	public static By passengertitle = By.xpath("//*[@resource-id='com.flynas.android.app:id/textLabel']");
	public static By fName = By.id("com.flynas.android.app:id/passengerDetailFirstName");
	public static By lName = By.id("com.flynas.android.app:id/passengerDetailLastName");	
	public static By dateofbirth = By.xpath("//*[@resource-id='com.flynas.android.app:id/passengerDetailDateOfBirth']");	
	public static By datepicker = By.xpath("//*[@resource-id='android:id/numberpicker_input']");
	public static By selectdate = By.xpath("//*[@resource-id='com.flynas.android.app:id/buttonDefaultPositive']");

	public static By documenttype = By.xpath("//*[@resource-id='com.flynas.android.app:id/passengerDetailDocumentType']");
	public static By idnumber = By.id("com.flynas.android.app:id/passengerDetailIDNumber");
	public static By idexpdate = By.xpath("//*[@resource-id='com.flynas.android.app:id/passengerDetailIDExpirationDate']");
	public static By smily = By.id("com.flynas.android.app:id/passengerDetailLoyaltyNumber");
	public static By mobilenum = By.id("com.flynas.android.app:id/contactDetailsMobileNumber");
	public static By email = By.id("com.flynas.android.app:id/contactDetailsEmailAddress");
	public static By cnfmemail = By.xpath("//*[@resource-id='com.flynas.android.app:id/contactDetailsEmailConfirm']");
	public static By continuebtn = By.xpath("//*[@resource-id='com.flynas.android.app:id/continueButton']");
	public static By continuebtntxt = By.xpath("//*[@resource-id='com.flynas.android.app:id/continueButtonText]");
	public static By checkIn_btn = By.xpath("//*[@text='Check-in']");

	public static By seatplusbutton = By.xpath("//*[@resource-id='com.flynas.android.app:id/seatPlusButton']");
	public static By seatselectLbtn = By.xpath("//*[@resource-id='com.flynas.android.app:id/seatSelectPassengerLeftButton']");
	public static By seatselectRbtn = By.xpath("//*[@resource-id='com.flynas.android.app:id/seatSelectPassengerRightButton']");
	public static By seatSelecttionTittle = By.xpath("//*[@text='Seat Selection']");
	public static By  seatsofType(String seattype)
	{
		return By.xpath("//*[@text='"+seattype+"']/parent::android.widget.LinearLayout/following-sibling::android.widget.LinearLayout[@index=1]/android.widget.RelativeLayout");
	}
	
	

	public static By  ExtraLegSeats =By.xpath("//android.widget.LinearLayout[@index=1]/android.widget.RelativeLayout[@index=0]/android.widget.ImageView[@index=0]");
	public static By ExtraSeats = By.xpath("//*[@resource-id='com.flynas.android.app:id/select_seat_column6_image']");
	public static By seatSelectLeftButton =By.xpath("//*[@resource-id='com.flynas.android.app:id/titleBack']");
	public static By Seating = By.xpath("//*[@resource-id='com.flynas.android.app:id/title']");

	public static By addedProtection = By.xpath("//*[@resource-id='com.flynas.android.app:id/drawer']");
	public static By Submit = By.xpath("//android.widget.Button[@index=0]");
	public static By confirmation_Status = By.xpath("//*[@resource-id='com.flynas.android.app:id/confirmationStatus']");
	public static By confirmation_PNR  = By.xpath("//*[@resource-id='com.flynas.android.app:id/confirmationPNR']");

	
	//Baggage
	
	public static By baggagetittle = By.xpath("//*[@resource-id='com.flynas.android.app:id/checkinBaggageTitleText']");
	public static By baggageAddPlusButton = By.xpath("//*[@resource-id='com.flynas.android.app:id/showHideBaggageBarIndicator']");
	public static By baggagePlusButton = By.xpath("//*[@resource-id='com.flynas.android.app:id/baggageSectionExpansionButton']");
	public static By availablebaggages = By.xpath("//*[@resource-id='com.flynas.android.app:id/availableBaggageItemPlus']");
	
	
	public static By txtorigin = By.xpath("//input[@placeholde='Origin' and @aria-owns = 'ui-select-choices-0']");
	public static By selectOrigin = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[2]/div/div/div/ul/li//descendant::div[@class='ui-select-choices-row']");	
	public static By secOrigin = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[2]/div[4]/div/div/div/div/div");
	public static By selectSecOrigin = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[2]/div[4]/div/div/div/div/ul/li//descendant::div[@class='ui-select-choices-row']");
	public static By secDest = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[2]/div[4]/div/div[2]/div/div/div");
	public static By selectSecDest = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[2]/div[4]/div/div[2]/div/div/ul/li//descendant::div[@class='ui-select-choices-row']");
	public static By dest = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[2]/div[2]/div");
	public static By selectDest = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[2]/div[2]/div/div/ul/li//descendant::div[@class='ui-select-choices-row']");
	public static By dpDate = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[2]/div[3]/div");
	public static String selectDate = "//div[@class='input-group datepicker']/ul/li/div/table";
	public static By rtDate = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[2]/div[5]/div");
	public static By selectRTDate = By.xpath("");
	public static By adult = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[3]/div/div");
	public static By selectAdult = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[3]/div/div/div/ul/li//descendant::div[@class='ui-select-choices-row']");
	public static By child = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[3]/div/div[2]");
	public static By selectChild = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[3]/div/div[2]/div/ul/li//descendant::div[@class='ui-select-choices-row']");
	public static By infant = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[3]/div/div[3]");
	public static By selectInfant = By.xpath("//div[@class='main_con']/div[2]//descendant::form/div[3]/div/div[3]/div/ul/li//descendant::div[@class='ui-select-choices-row']");
	public static By promo = By.id("com.flynas.android.app:id/etSearchFlightPromoCode");
	
	
	
	
	
	//Class selection page
	//public static By economyOW = By.xpath("//div[@class='main_con']/div/div/div[2]/div/flight-select/div/form/div//descendant::table/tbody//descendant::td[5]/button");
	//public static By economyOW = By.xpath("//div/button[@class='btn selectflightbtn col1']");
	public static By flights = By.xpath("//*[@resource-id='com.flynas.android.app:id/selectFlightDateBar']//android.widget.LinearLayout[@index=1]/android.widget.LinearLayout");
	public static By economyOW = By.xpath("//*[@resource-id='com.flynas.android.app:id/select_flight_extra_economy_block']");
	public static By flexOW = By.xpath("//*[@resource-id='com.flynas.android.app:id/select_flight_extra_flex_block']");
	public static By busOW = By.xpath("//*[@resource-id='com.flynas.android.app:id/select_flight_extra_business_block']");
	public static String rtClassStr= "//div[@class='main_con']/div/div/div[2]/div/flight-select/div/form/div[2]//descendant::table/tbody/tr";
	public static By rtClass = By.xpath("//div[@class='main_con']/div/div/div[2]/div/flight-select/div/form/div[2]//descendant::table/tbody/tr");
	public static By okBtn = By.xpath("//div[@class='modal-dialog']/div/div[3]/button[@class='btn btn-primary']");
	public static By cancelBtn = By.xpath("//div[@class='modal-dialog']/div/div[3]/button[@class='btn btn-warning']");
	public static By selectFlightUpDownArrow = By.xpath("//*[@resource-id='com.flynas.android.app:id/select_flight_updown_arrow']");
	public static By flightNumber = By.xpath("//*[@resource-id='com.flynas.android.app:id/select_flight_extra_ticket_number']");
	public static By rightarrow = By.xpath("//*[@resource-id='com.flynas.android.app:id/imageView2']");
	public static By nextFlight = By.xpath("//*[@resource-id='com.flynas.android.app:id/lowPriceDay3']");
	public static By econnmyPrice = By.xpath("//*[@resource-id='com.flynas.android.app:id/select_flight_extra_economy_price']");
	public static By flexPrice = By.xpath("//*[@resource-id='com.flynas.android.app:id/select_flight_extra_flex_price']");
	public static By bussPrice = By.xpath("//*[@resource-id='com.flynas.android.app:id/select_flight_extra_business_price']");
	public static By cartSummaryBalance = By.xpath("//*[@resource-id='com.flynas.android.app:id/cartSummaryDueBalance']");
	//Input Passenger Details
	public static String passType = "//div[@class='pass_tab']/div[#]/h3";

	public static String titleSelect = "//div[@class='pass_tab']/div[#]/div/ng-form//descendant::div[@class='dropdowntitlecon']/div/ul/li//descendant::div[@class='ui-select-choices-row']";
	
	
	public static String dd = "//div[@class='pass_tab']/div[#]//descendant::div[@class='dob_con dob_conday']";
	public static String selectDD = "//div[@class='pass_tab']/div[#]//descendant::div[@class='dob_con dob_conday']/div/ul/li//descendant::div[@class='ui-select-choices-row'][";
	public static String mm = "//div[@class='pass_tab']/div[#]//descendant::div[@class='dob_con dob_conmonth']";
	public static String selectMM = "//div[@class='pass_tab']/div[#]//descendant::div[@class='dob_con dob_conmonth']/div/ul/li//descendant::div[@class='ui-select-choices-row'][";
	public static String yyyy = "//div[@class='pass_tab']/div[#]//descendant::div[@class='dob_conyear']";
	public static String selectYYYY = "//div[@class='pass_tab']/div[#]//descendant::div[@class='dob_conyear']/div/ul/li//descendant::div[@class='ui-select-choices-row'][";
	public static String selectyyinfant = "//div[@class='pass_tab']/div[#]//descendant::div[@class='dob_conyear']/div/ul/li/div[contains(@class,'ui-select-choices-row')]";
	public static String nation = "//div[@class='pass_tab']/div[#]//descendant::div[@class='nationdropdown']";
	public static String selectNation = "//div[@class='pass_tab']/div[#]//descendant::div[@class='nationdropdown']/div/ul/li//descendant::div[@class='ui-select-choices-row']";
	
	//public static By travelDoc = By.xpath("//div[@class='traveldocdropdown']/div/div/span/span[2]/span");
	public static String travelDoc = "//booking-passengers/div/ng-form/div/div[#]/div/ng-form/div/div[2]/div[1]/div/div/div/span/span[2]/span";
	//public static By selecttravelDoc = By.xpath("//div[@class='traveldocdropdown']/div/ul/li//descendant::div[@class='ui-select-choices-row']");
	//public static String selecttravelDoc = "//booking-passengers/div/ng-form/div/div[#]/div/ng-form/div/div[2]/div[2]/input";
	public static String inputDoc = "//booking-passengers/div/ng-form/div/div[#]/div/ng-form/div/div[2]/div[2]/input";
	//public static By naSmiles = By.xpath("//input[@name='asiamiles']");
	public static String naSmiles = "//booking-passengers/div/ng-form/div/div[#]/div/ng-form/div/div[2]/div[*]/div/input";
	
	public static String ppNumber= "/html/body/div[1]/div/div/div[2]/div/passengers-contacts/div/form/booking-passengers/div/ng-form/div/div[#]/div/ng-form/div/div[2]/div[3]/input";
	public static String ppExpDD= "/html/body/div[1]/div/div/div[2]/div/passengers-contacts/div/form/booking-passengers/div/ng-form/div/div[#]/div/ng-form/div/div[2]/div[4]/div[1]/div/div/span/span[1]";	
	public static String ppExpMM = "/html/body/div[1]/div/div/div[2]/div/passengers-contacts/div/form/booking-passengers/div/ng-form/div/div[#]/div/ng-form/div/div[2]/div[4]/div[2]/div/div/span/span[1]";
	public static String ppExpYY = "/html/body/div[1]/div/div/div[2]/div/passengers-contacts/div/form/booking-passengers/div/ng-form/div/div[#]/div/ng-form/div/div[2]/div[4]/div[3]/div/div/span/span[1]";
	public static String ppSelectDD = "//ul/li/descendant::div[@class='ui-select-choices-row'][";
	
	
	public static By countryCode = By.xpath("//div[@placeholder='Country code*']");
	public static By selectCountCode = By.xpath("//div[@name='countrycode']/ul/li//descendant::div[@class='ui-select-choices-row']"); 
	public static By mobileNum = By.xpath("//input[@name='mobilenum']");
	public static By emailAdd = By.xpath("//input[@name='emailaddress']");
	
	//Extras
	//public static By selectCharity = By.xpath("//span[@tabindex='-1']");
	public static By selectCharity = By.xpath("//i[@class='caret pull-right']");
	public static By charitydisable = By.xpath("//label[contains(text(),'Amount options')]/following::div[2]");
	//Seat Selection
	public static By selbusSeat = By.xpath("//div[@class='seatmap']/div/div//descendant::div[@class='seatimg type1']");
	public static By selExLegSeat = By.xpath("//div[@class='seatmap']/div/div//descendant::div[@class='seatimg type2']");
	public static By selPremSeat = By.xpath("//div[@class='seatmap']/div[2]/div//descendant::div[@class='seatimg type3']");
	public static By selUpfrontSeat = By.xpath("//div[@class='seatmap']/div[3]/div//descendant::div[@class='seatimg type4']");
	public static By selExLeg2Seat = By.xpath("//div[@class='seatmap']/div[4]/div//descendant::div[@class='seatimg type2']");
	public static By selStdSeat = By.xpath("//div[@class='seatmap']/div[5]/div//descendant::div[@class='seatimg type5']");
	public static By seatSelTabs = By.xpath("//div[@class='tabwrap']/ul/li");
	
	//Payment Page
	public static By Payment_title = By.xpath("//*[@text='Payment']");
	public static By paymentOptions = By.xpath("//div[@class='pass_tab']/div/div/ul/li");
	public static By paymentName = By.xpath("/a/div");
	public static By cardNumber = By.xpath("//*[@resource-id='com.flynas.android.app:id/creditCardNumber']");
	public static By cardName = By.xpath("//*[@resource-id='com.flynas.android.app:id/creditCardHolder']");
	public static By expdate = By.xpath("//*[@resource-id='com.flynas.android.app:id/creditCardExpiry']");
	public static By selectExpMonth = By.xpath("//div[@class='pass_tab']//descendant::div[@name='expMonth']/ul/li//descendant::div[@class='ui-select-choices-row']");
	public static By expYear = By.xpath("//div[@class='pass_tab']//descendant::div[@name='expYear']");
	public static By selectExpYear = By.xpath("//div[@class='pass_tab']//descendant::div[@name='expYear']/ul/li//descendant::div[@class='ui-select-choices-row']");
	public static By cvvNum = By.xpath("//*[@resource-id='com.flynas.android.app:id/creditCardCVV']");
	public static By ccCheckbox = By.xpath("//input[@type='checkbox']");
	public static By voucherNum = By.xpath("//voucher[@form='paymentForm']/div/div/input");
	public static By retrieveVoucher = By.xpath("//voucher[@form='paymentForm']//descendant::button[@class='btn btn-primary']");
	public static By tabCreditCard = By.xpath("//a/div[text()='Credit Card']");
	public static By tabNasCredit = By.xpath("//*[@resource-id='com.flynas.android.app:id/paymentType3ButtonInner']");
	public static By tabSadad = By.xpath("//*[@resource-id='com.flynas.android.app:id/paymentType2ButtonInner']");
	public static By creditShellAmount = By.xpath("//*[@resource-id='com.flynas.android.app:id/creditShellAmount']");
	public static By totalSAR = By.xpath("//*[@resource-id='com.flynas.android.app:id/paymentPriceSummaryText']");
	
	public static By terms = By.xpath("//*[@resource-id='com.flynas.android.app:id/paymentTermsCheckBox']");
	public static By pnrstatus = By.xpath("//*[@resource-id='com.flynas.android.app:id/confirmationStatus']");
//	public static By PNR = By.xpath("//*[@resource-id='com.flynas.android.app:id/confirmationStatus']");
	public static By tittleHome = By.xpath("//*[@resource-id='com.flynas.android.app:id/titleHome']");
	public static By loveFlynasApp = By.xpath("//*[@resource-id='com.flynas.android.app:id/title']");
	public static By noThanks = By.xpath("//*[@resource-id='com.flynas.android.app:id/buttonDefaultNegative']");
	
	public static By txtPNR = By.xpath("//span[text()='PNR: ']");
	public static By sadadOP = By.xpath("//sadadop[@form='paymentForm']/div/div/div/input");
	public static By sadadEnter = By.xpath("//sadadop[@form='paymentForm']//descendant::button[@class='btn btn-primary']");
	public static By ccSubmit = By.xpath("//input[@name='UsernamePasswordEntry']");
	public static By paymentType = By.xpath("//div[@class='passblock']");
	public static By ipt_pnr  = By.xpath("//input[@name='pnr']");
	public static By Amount = By.xpath("//input[@name='creditFileAmount']");
	public static By Retrive = By.xpath("//button[contains(text(),'Continue')]");
	public static By creditbal = By.xpath("//div[@class='col-md-6'][1]/p");
	public static By pasword = By.xpath("//input[@type='password']");
	
	//Summary Page
	public static By summaryRefNumber = By.xpath("//*[@resource-id='com.flynas.android.app:id/confirmationPNR']");
	public static By summaryStatus = By.xpath("//*[@resource-id='com.flynas.android.app:id/confirmationStatus']");
	
	//Summary Page with Edit Flight
//	public static By summaryChangeFlight = By.xpath("//*[@resource-id='com.flynas.android.app:id/mmbChangeFlightBtn']");
	
	public static By summaryCancelFlight = By.xpath("//a[@class='btn btn-primary'][2]");
	public static By selectFlightstoCancel = By.xpath("//input[@name='cfcheckbox']");
	public static By cancelflightBtn = By.xpath("//*[@resource-id='com.flynas.android.app:id/mmbCancelFlightBtn']");
	public static By conformCharges = By.xpath("//a[contains(text(),'Click here to confirm changes')]");
	public static By conformedAftercharges = By.xpath("//b[contains(text(),'Confirmed')]");
	
	/*
	 * change flight elements 
	 */
	//Change Date
	public static By calender = By.xpath("//div[@class='padcon']");
	public static By btnchngFlight = By.xpath("//*[@resource-id='com.flynas.android.app:id/mmbChangeFlightBtn']");
	public static By flightToChange = By.xpath("//*[@resource-id='com.flynas.android.app:id/extraFlightTickBox']");
	public static By cnfmChanges = By.xpath("//*[@resource-id='com.flynas.android.app:id/mmbPurchaseBtn']");
	
	
	public static By btnpopupchngFlight = By.xpath("//button[text()='Change flight']");
	public static By selflnextbutton = By.xpath("//a[@class='btn_next last']");
	public static By selfltxtNoFlight = By.xpath("//ng-form/div[3]/div[1]/div/div");
	public static By selflCurrentDate = By.xpath("//*[@id='select_departure']/div/ul/li[4]/a/span");
	public static By selflEcoFlight = By.xpath("//div[@class='btn_td col-xs-4']/"
			+ "button[@class = 'btn selectflightbtn col1']");			
	public static By selflightContinue = By.xpath("//button[text()='Continue']");
	public static By popupButtonOk = By.xpath("//button[@class='btn btn-primary']");
	public static By confirmmsg = By.xpath("//a[text()='Click here to confirm changes']");
	
	//Search Flight Page
	public static By sfpbookingReference = By.xpath("//input[@name='inputpnr']");
	public static By sfpEmail = By.xpath("//input[@name='inputemail']");
	public static By sfpMoblie = By.xpath("//input[@name='inputmobile']");
	public static By sfpLastName = By.xpath("//input[@name='inputlastname']");
	public static By sfpFindBooking = By.xpath("//span[text()='Find booking']");
	
	//Add extra
	
	public static By mealExpansionButton = By.xpath("//*[@resource-id='com.flynas.android.app:id/mealSectionExpansionButton']");
	public static By availableMeal = By.xpath("//*[@resource-id='com.flynas.android.app:id/availableMealItemInner']");
	public static By mealTitle = By.xpath("//*[@resource-id='com.flynas.android.app:id/flightMealsTitleText']");
	
	public static By Adult_baggagae = By.xpath("//h1[contains(text(),' Baggage')]/following-sibling::p/following::div[3]/ul/li/a");
	public static By inflightmeal = By.xpath("//h1[contains(text(),'In-flight meals')]/following-sibling::p/following::div[3]/ul/li/a");
	public static By selectedflightes = By.xpath("//h1[contains(text(),'In-flight meals')]/following::ul[2]/li/a");
	public static By meal = By.xpath("//th[contains(text(),'Meal')]");
	public static By select = By.xpath("//a[contains(text(),'Select')]");
	public static By Loung = By.xpath("//table[@class='table bloungetable']/tbody/tr[1]/td");
	public static By Selected_Flights = By.xpath("//div[@class='row baggage_row']");
	
	public static By Baggage_weight = By.xpath("//label[@name='baggage']");
	public static By important = By.xpath("//h3[contains(text(),'Important')]");
	public static By ok=By.xpath("//*[@resource-id='com.flynas.android.app:id/buttonDefaultPositive']");
	
	//checkins
	public static By checkInReference = By.id("com.flynas.android.app:id/etOnlineCheckinPNR");
	public static By lastname_incheckin = By.id("com.flynas.android.app:id/etOnlineCheckinLastname");
	public static By checkin_btn = By.id("com.flynas.android.app:id/btnOnlineCheckin");
	
	public static By checkInFlight = By.xpath("//*[@resource-id='com.flynas.android.app:id/extraFlightTickBox']");
	public static By passengers_incheckin = By.xpath("//*[@resource-id='com.flynas.android.app:id/onlineCheckinPassengerLineTickBox']");
	public static By checkinConformation = By.xpath("//android.widget.TextView[@index=1]");
	public static By checkinBarCode = By.xpath("//*[@resource-id='com.flynas.android.app:id/boardingBarCode']");
	public static By travelDocuments = By.xpath("//*[@text='Travel Documents']");
	/*public static By passengers_checkterms = By.xpath("//a[text()='Terms and conditions']/preceding-sibling::i/preceding-sibling::input");
	public static By checkinConformation = By.xpath("//p[(text()='Click below to view your boarding pass(es). You`ll also receive it in an email.')]");
	public static By sfpChekin = By.xpath("//button[text()='Check-in now']");
	*/
	
	//Manage my bookings
	public static By bookingReference = By.id("com.flynas.android.app:id/etFindBookingPNR");
	public static By lastName_mb = By.id("com.flynas.android.app:id/etFindBookingLastname");
	public static By email_mb = By.xpath("//*[@resource-id='com.flynas.android.app:id/onlineCheckinEmail']");
	public static By findBooking_btn = By.id("com.flynas.android.app:id/btnFindBooking");
	
	public static By selectDateButton_mb = By.xpath("//*[@resource-id='com.flynas.android.app:id/selectDateButton']");
	public static By  manage(String PNR)
	{
		return By.xpath("//*[@text='"+PNR+"']/parent::android.widget.LinearLayout[@index=1]/parent::android.widget.LinearLayout[@index=0]/following-sibling::android.widget.LinearLayout[@index=1]/android.widget.TextView[@index='0']");
	}
	
}
