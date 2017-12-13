package flynas.androidchrome.workflows;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ctaf.support.ExcelReader;
import com.ctaf.utilities.Reporter;

import flynas.androidchrome.testObjects.BookingPageLocators;

public class BookingPageFlow<RenderedWebElement> extends BookingPageLocators{
	
	
	
	public void  login(String username,String password) throws Throwable
	{
		waitforElement(BookingPageLocators.email);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		type(BookingPageLocators.email, username, "Email");
		type(BookingPageLocators.password, password, "Password");
		click(BookingPageLocators.login_btn, "Login");
				
	}
	
	public boolean inputBookingDetails(String tripType, String origin, String dest, String deptDate,
			String origin2, String departure2, String retDate, String adults, String child, String infant, String promo,String Currency,String payment) throws Throwable{
		waitforElement(BookingPageLocators.oneWay);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='loading sk-wave']"))));
		if(Currency!="")
		{
			click(BookingPageLocators.Currency_link, "Currency");
			Thread.sleep(2000);
			//driver.findElement(By.xpath("//div[contains(text(),'"+Currency+"')]")).click();
			click(BookingPageLocators.currencytyp(Currency), Currency);
		}
		//Select Trip Mode
		if(tripType.equalsIgnoreCase("Round Trip")){
			click(BookingPageLocators.roundTrip, "Round Trip");
		} else if(tripType.equalsIgnoreCase("One Way")){
			click(BookingPageLocators.oneWay, "One Way");
		} else if(tripType.equalsIgnoreCase("Multi City")){
			click(BookingPageLocators.multiCity, "Multi City");
		}
		click(BookingPageLocators.origin, "Origin");
		selectCity(BookingPageLocators.selectOrigin, "Origin", origin);
		click(BookingPageLocators.dest, "Destination");
		selectCity(BookingPageLocators.selectDest, "Destination", dest);
		/*click(BookingPageLocators.dest, "Destination");
		selectCity(BookingPageLocators.selectDest, "Destination", dest);*/
		click(BookingPageLocators.dpDate,"Departure Date");
		selectDate(BookingPageLocators.selectDate,"Departure Date",deptDate);
		
		if(tripType.equalsIgnoreCase("Round Trip")){
			DateFormat dateFormat = new SimpleDateFormat("dd-MMMM yyyy");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			String todaydate = dateFormat.format(date);
			if(deptDate.equalsIgnoreCase(todaydate))
			{
				click(BookingPageLocators.rtDate,"Return Date");
			}
			selectDate(BookingPageLocators.selectDate,"Return Date",retDate);
		}
		if(tripType.equalsIgnoreCase("Multi City")){
			click(BookingPageLocators.secOrigin, "Origin");
			selectCity(BookingPageLocators.selectOrigin, "Origin2", origin2);
			selectCity(BookingPageLocators.selectDest, "Destination2", departure2);
			click(BookingPageLocators.rtDate,"Return Date");
			selectDate(BookingPageLocators.selectDate,"Return Date",retDate);
		}
		if(Integer.valueOf(adults)>1){
			click(BookingPageLocators.adult, "Adult");
			System.out.println("Adults: "+adults);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[text()='"+adults+"']")));
			//driver.findElement(By.xpath("//div[text()='"+adults+"']")).click();
			//selectValueFromDropDown(BookingPageLocators.selectAdult, "Adult", adults);
		}
		if(Integer.valueOf(child)>=1){
			click(BookingPageLocators.child, "Child");
			driver.findElement(By.xpath("//div[text()='"+child+"']")).click();
			//selectValueFromDropDown(BookingPageLocators.selectChild, "Child", child);
		}
		if(Integer.valueOf(infant)>=1){
			click(BookingPageLocators.infant, "Infant");
			driver.findElement(By.xpath("//div[text()='"+infant+"']")).click();
			//selectValueFromDropDown(BookingPageLocators.selectInfant, "Infant", infant);
		}
		if(payment.trim().equalsIgnoreCase("naSmile")){
			click(BookingPageLocators.payWithnaSmile, "payWithnaSmile");
		}
		if(!promo.equalsIgnoreCase("")){
			type(BookingPageLocators.promo, promo, "Promo");
		}
		click(BookingPageLocators.findFlights,"Find Flights");
		explicityWait(BookingPageLocators.selectflightsection, "Select your Departing Flight");
		return true;
	}
	
	public boolean selectClass(String bookingClass, String tripType) throws Throwable{
		boolean flag=false;
		
		waitUtilElementhasAttribute(BookingPageLocators.body);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,750)", "");
		List<WebElement> flighttables = driver.findElements(By.xpath("//div[@class='pdis picon picon_plane']/following::div[@class='flight_mtable']"));
		List<WebElement> nxtbtn = driver.findElements(By.xpath("//a[@class='btn_next last']")); 
		List<WebElement> current = driver.findElements(By.xpath("//li[@class='current']"));
		for(int j=0;j<flighttables.size();j++){
			List<WebElement> Ecocols = flighttables.get(j).findElements(By.xpath("div/div/div/div/button[@class='btn selectflightbtn col1']"));
			List<WebElement> Flexcols = flighttables.get(j).findElements(By.xpath("div/div/div/div/button[@class='btn selectflightbtn col2']"));
			List<WebElement> Buscols = flighttables.get(j).findElements(By.xpath("div/div/div/div/button[@class='btn selectflightbtn col3']"));
			String flightstatus=current.get(j).findElement(By.xpath("a/span[2]")).getText();
		
			if(flightstatus.equalsIgnoreCase("No flights")||flightstatus.equalsIgnoreCase("Uçuş bulunamadı")
				||Ecocols.size()==0 ||Buscols.size()==0 || Flexcols.size()==0)
			{
				List<WebElement> nextflights =current.get(j).findElements(By.xpath("following-sibling::li"));
				int i=1;
				while(Ecocols.size()==0	|| Buscols.size()==0 ||Flexcols.size()==0)
				{
					flighttables.get(j).findElement(By.xpath("preceding::a[1]")).click();
					current.get(j).findElement(By.xpath("following-sibling::li["+i+"]")).click();
					waitUtilElementhasAttribute(BookingPageLocators.body);
					Buscols = flighttables.get(j).findElements(By.xpath("div/div/div/div/button[@class='btn selectflightbtn col3']"));
					Ecocols = flighttables.get(j).findElements(By.xpath("div/div/div/div/button[@class='btn selectflightbtn col1']"));
					Flexcols = flighttables.get(j).findElements(By.xpath("div/div/div/div/button[@class='btn selectflightbtn col2']"));
					i=i+1;
					if(bookingClass.equalsIgnoreCase("Business")){
						if(Buscols.size()>=1){
							waitUtilElementhasAttribute(BookingPageLocators.body);
					}}
					if(bookingClass.equalsIgnoreCase("Economy")){
						if(Ecocols.size()>=1){
							waitUtilElementhasAttribute(BookingPageLocators.body);
					}}
					if(bookingClass.equalsIgnoreCase("Flex")){
						if(Flexcols.size()>=0){
							waitUtilElementhasAttribute(BookingPageLocators.body);
					}}
			}
				
		}
		
		if(bookingClass.equalsIgnoreCase("Economy")){
			List<WebElement> ClassEco = flighttables.get(j).findElements(By.xpath("div/div/div/div/button[@class='btn selectflightbtn col1']"));
				for(int i=0;i<ClassEco.size();i++){			
					if(ClassEco.get(i).findElement(By.tagName("div")).getText().equalsIgnoreCase("Sold out")){
						System.out.println("Sold Out");
					}
					else{
						
						ClassEco.get(i).click();
						waitUtilElementhasAttribute(BookingPageLocators.body);
						break;
					}
					
				}
		}else if(bookingClass.equalsIgnoreCase("Flex")){
			List<WebElement> ClassFlex = flighttables.get(j).findElements(By.xpath("div/div/div/div/button[@class='btn selectflightbtn col2']"));
				for(int i=0;i<ClassFlex.size();i++){
					if(ClassFlex.get(i).findElement(By.tagName("div")).getText().equalsIgnoreCase("Sold out")){
						System.out.println("Sold Out");
					}else{
						ClassFlex.get(i).click();
						waitUtilElementhasAttribute(BookingPageLocators.body);
						break;
					}
					
				}
		}else if(bookingClass.equalsIgnoreCase("Business")){
			List<WebElement> ClassBusines = flighttables.get(j).findElements(By.xpath("div/div/div/div/button[@class='btn selectflightbtn col3']"));
				for(int i=0;i<ClassBusines.size();i++){
					if(ClassBusines.get(i).findElement(By.tagName("div")).getText().equalsIgnoreCase("Sold out")
							||ClassBusines.get(i).findElement(By.tagName("div")).getText().contains("لا تتوفر مقاعد")
							||ClassBusines.get(i).findElement(By.tagName("div")).getText().equalsIgnoreCase("Tükendi")){
						System.out.println("Sold Out");
					}else{
						ClassBusines.get(i).click();
						waitUtilElementhasAttribute(BookingPageLocators.body);
						break;
					}
					
				}
				
			
		}
		}
		waitUtilElementhasAttribute(BookingPageLocators.body);
		click(BookingPageLocators.continueBtn, "Continue");
		return true;
	}
	//public boolean inputPassengerDetails(String domOrInt, String totalPass, String nationality,
	public String[] inputPassengerDetails(String domOrInt, String totalPass, String nationality,String travelDoc,
	String docNum, String naSmiles, String mobileNum, String emailId,String fname,String lname,String payment2) 
					throws Throwable{
		waitforElement(BookingPageLocators.passengerDetailsTittle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		String lastname = null,firstname=null;
		String[] FirstLastName = null;
		Integer min=0,max=0;
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath(BookingPageLocators.title.replace("#", String.valueOf(1)))));
		try{
			for(Integer count = 1; count<=Integer.valueOf(totalPass); count++){
				if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").contains("Adult")){
					min = 14; max = 19;
				} else if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").contains("Child")){
					min = 5; max = 9;
				} else if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").contains("Infant")){
					min = 1; max = 2;
				}
				
				click(By.xpath(BookingPageLocators.title.replace("#", String.valueOf(count))),"Title");
			
				click(By.xpath(BookingPageLocators.titleSelect.replace("#", String.valueOf(count))), "Title");
			
				if(payment2.equalsIgnoreCase("Nas"))
				{
					type(By.xpath(BookingPageLocators.fName.replace("#", String.valueOf(count))), fname, "First Name");

				}
				else if(payment2.equalsIgnoreCase("naSmile"))
				{
						type(By.xpath(BookingPageLocators.fName.replace("#", String.valueOf(count))), fname, "First Name");
				}
				else
				{
				//Thread.sleep(1000);
				firstname = randomString();
				type(By.xpath(BookingPageLocators.fName.replace("#", String.valueOf(count))), firstname, "First Name");
				}
				if(payment2.equalsIgnoreCase("Nas"))
				{
					type(By.xpath(BookingPageLocators.lName.replace("#", String.valueOf(count))), lname, "Last Name");

				}
				else if(payment2.equalsIgnoreCase("naSmile"))
				{
					type(By.xpath(BookingPageLocators.lName.replace("#", String.valueOf(count))), lname, "Last Name");
				}
				else
				{
				lastname = randomString();
				type(By.xpath(BookingPageLocators.lName.replace("#", String.valueOf(count))), lastname, "Last Name");
				}
				
				click(By.xpath(BookingPageLocators.dd.replace("#", String.valueOf(count))), "DD");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.selectDD.replace("#", String.valueOf(count))+randomNumericString()+"]"), "DD");
				
				click(By.xpath(BookingPageLocators.mm.replace("#", String.valueOf(count))), "MM");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.selectMM.replace("#", String.valueOf(count))+randomNumericString()+"]"), "MM");
				
				click(By.xpath(BookingPageLocators.yyyy.replace("#", String.valueOf(count))), "YYYY");
				//Thread.sleep(3000);
				if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").contains("Infant"))
				{
					List<WebElement> year =driver.findElements(By.xpath(BookingPageLocators.selectyyinfant.replace("#", String.valueOf(count))));
					year.get(1).click();
				}
				else
				{
					executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@class='pass_tab']/div["+count+"]//descendant::div[@class='dob_conyear']/div/ul/li//descendant::div[@class='ui-select-choices-row']["+randomNumber(min,max)+"]")));
					//click(By.xpath(BookingPageLocators.selectYYYY.replace("#", String.valueOf(count))+randomNumber(min,max)+"]"), "YYYY");
				}
				
				click(By.xpath(BookingPageLocators.nation.replace("#", String.valueOf(count))), "Nationality");
				//Thread.sleep(3000);
				executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[text()='"+nationality+"']")));
				//click(By.xpath("//div[text()='"+nationality+"']"), "Natioanlity");
				//selectValueFromDropDown(By.xpath(BookingPageLocators.selectNation.replace("#", String.valueOf(count))), "Nationality", nationality);
				
				
				if(domOrInt.equalsIgnoreCase("Domestic")){
					assertText(By.xpath(BookingPageLocators.travelDoc.replace("#", String.valueOf(count))), "National ID Card");
				}
				else{
					assertText(By.xpath(BookingPageLocators.travelDoc.replace("#", String.valueOf(count))), "Passport");
				}
				//click(BookingPageLocators.travelDoc,"Travel Document");
				//click(By.xpath("//div[text()='"+travelDoc+"']"), "Travel Document");
				// - no need selectValueFromDropDown(BookingPageLocators.selecttravelDoc, "Travel Document", travelDoc);
				//type(BookingPageLocators.inputDoc, docNum, "Document Number");
				
				if(travelDoc.equalsIgnoreCase("National ID Card"))
				{
					click(By.xpath(BookingPageLocators.travelDoc.replace("#", String.valueOf(count))),"Travel Document");
					click(By.xpath("//div[text()='"+travelDoc+"']"), "Travel Document");
					type(By.xpath(BookingPageLocators.inputDoc.replace("#", String.valueOf(count))), docNum, "Document Number");
				}
				else if (travelDoc.equalsIgnoreCase("Passport"))
				
				{	
					
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.ppNumber.replace("#", String.valueOf(count))), "Passport text box");
				type(By.xpath(BookingPageLocators.ppNumber.replace("#", String.valueOf(count))), randomString(), "Passport Number");
				
				click(By.xpath(BookingPageLocators.ppExpDD.replace("#", String.valueOf(count))), "DD");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.ppSelectDD.replace("#", String.valueOf(count))+randomNumericString()+"]"), "DD");	
				
				click(By.xpath(BookingPageLocators.ppExpMM.replace("#", String.valueOf(count))), "MM");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.ppSelectDD.replace("#", String.valueOf(count))+randomNumericString()+"]"), "MM");
				
				click(By.xpath(BookingPageLocators.ppExpYY.replace("#", String.valueOf(count))), "YYYY");
			//	Thread.sleep(3000);
			//	click(By.xpath(BookingPageLocators.ppSelectDD.replace("#", String.valueOf(count))+randomNumber(min,max)+"]"), "YYYY");
			//	click(By.xpath(BookingPageLocators.ppSelectDD+randomNumber(min,max)+"]"), "YYYY");
				
				executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//ul/li/descendant::div[@class='ui-select-choices-row']["+randomNumber(min,max)+"]")));
				}
			
				if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").equalsIgnoreCase("Infant 1"))
				{
					System.out.println("No Smily for  "+ getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title"));
					
				}
				else
				{
						if(!naSmiles.equalsIgnoreCase("") && !naSmiles.equalsIgnoreCase("Value"))
						type(By.xpath(BookingPageLocators.naSmiles.replace("#", String.valueOf(count))), naSmiles, "na Smiles");
				}
			
			}
			type(BookingPageLocators.mobileNum, mobileNum, "Mobile Number");
			type(BookingPageLocators.emailAdd, emailId, "Email Address");
			click(BookingPageLocators.continueBtn, "Continue");
			 FirstLastName = new String[2];
			 FirstLastName[0] =firstname;
			 FirstLastName[1] =lastname;
			 return FirstLastName;
		}catch(Exception e){
			e.printStackTrace();
			return FirstLastName;
		}
		
	}
	
	public boolean inputExtras(String charity) throws Throwable{
		/*WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(BookingPageLocators.loadimg)));		*/
	//	waitforElement(BookingPageLocators.baggagetittle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		if (Integer.parseInt(charity)>1){
			
			if(isElementDisplayedTemp(By.xpath("//h1[contains(text(),'Charity donation')]"))){
				if(driver.findElement(BookingPageLocators.selectCharity).isEnabled()==true){
					click(BookingPageLocators.selectCharity,"Charity");
					waitUtilElementhasAttribute(BookingPageLocators.body);
					click(By.xpath("//ul[@role='listbox']/li/div/a/div[contains(text(),'"+charity+"')]"), "Charity Amount");
					waitUtilElementhasAttribute(BookingPageLocators.body);
					click(BookingPageLocators.selectCharity,"Charity");
					waitUtilElementhasAttribute(BookingPageLocators.body);
					click(By.xpath("//ul[@role='listbox']/li/div/a/div[contains(text(),'0.00')]"), "Charity Amount");
					waitUtilElementhasAttribute(BookingPageLocators.body);
					if(isElementDisplayedTemp(BookingPageLocators.otherfeeinSummary)==false){
						Reporter.SuccessReport("Verifing Remove Charity", "Successfully Removed");
					}
					click(BookingPageLocators.continueBtn, "Continue");
				}else{
					System.out.println("Charity is Disabled");
				}
			
			
			}
		}
		else
		{			
			click(BookingPageLocators.continueBtn, "Continue");
					
		}
			return true;
		
	}

	public void selectallSeats(String seatSelect,String totalpass,String triptype) throws Throwable {
		waitforElement(BookingPageLocators.selectseattittle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		List<WebElement> Flights = driver.findElements(By.xpath("//ul[@class='nav nav-tabs']/li"));
		List<WebElement> ele = driver.findElements((By.xpath("//div[contains(@class, 'seatpassrow')]"))); 
		if(ele.size()==0)
		{
			click(BookingPageLocators.continueBtn, "Continue");
		}
		else
		{
			int k;
			List<WebElement> extlegseats = driver.findElements(BookingPageLocators.selExLegSeat);
			List<WebElement> busiseats = driver.findElements(BookingPageLocators.selbusSeat);
			int loop=0;
			for(int j=0;j<Flights.size();j++){
				for(int i=0;i<Integer.parseInt(totalpass);i++){
					if(seatSelect.equalsIgnoreCase("Extra Leg Room")){
						if(Flights.get(j).getAttribute("class").contains("active")){
							for(k=loop;k<extlegseats.size();k++)
							{
								if(extlegseats.get(k).isDisplayed()==true){
									extlegseats.get(k).click();
									Reporter.SuccessReport("Click", "Successfully Click on Extra Leg Room ");
									if(isElementDisplayedTemp(BookingPageLocators.ok)){
										click(BookingPageLocators.ok, "OK");
										waitUtilElementhasAttribute(BookingPageLocators.body);
										
									}	
									loop=loop+1;
									break;
								}else{
									System.out.println("Seat was Already Selected");
									loop=loop+1;
									
								}
																	
								}
							/*if(i==Integer.parseInt(totalpass)-1){
									break;
							}*/	
							
								
							//continue;	
						}
						
					}
					if(seatSelect.equalsIgnoreCase("Business")){
						if(Flights.get(j).getAttribute("class").contains("active")){
							for(k=loop;k<busiseats.size();k++)
							{
								if(busiseats.get(k).isDisplayed()==true){
									busiseats.get(k).click();
									Reporter.SuccessReport("Click", "Successfully Click on Business");
									if(isElementDisplayedTemp(BookingPageLocators.ok)){
										click(BookingPageLocators.ok, "OK");
										
									}	
									loop=loop+1;
									break;
								}else{
									System.out.println("Seat was Already Selected");
									loop=loop+1;
									
								}
																	
								}
							/*if(i==Integer.parseInt(totalpass)-1){
									break;
							}*/	
							
								
							//continue;	
						}
						
				}
				
			}
			
		}
		
			waitUtilElementhasAttribute(BookingPageLocators.body);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(BookingPageLocators.continueBtn));
			click(BookingPageLocators.continueBtn, "Continue");
			if(isElementDisplayedTemp(By.xpath("//button[text()='OK']"))==true){
			click(By.xpath("//button[text()='OK']"), "OK Button");
			}
			else{
			System.out.println("No alert Present");
			}
		}
	}
	public void removeallseats(String seatSelect,String totalpass,String triptype) throws Throwable{
		List<WebElement> remove = driver.findElements(BookingPageLocators.Remove);
		List<WebElement> flights = driver.findElements(BookingPageLocators.flightsInSeat);
		flights.get(0).click();
		int j=1,loop=0;
		for(int i=loop;i<remove.size();i++){
			if(remove.get(i).isDisplayed()==false){
				flights.get(j).click();
				j=j+1;
				remove.get(i).click();
				loop=loop+1;
				waitUtilElementhasAttribute(BookingPageLocators.body);
			}else{
				remove.get(i).click();
				loop=loop+1;
				waitUtilElementhasAttribute(BookingPageLocators.body);
			}
		}
		List<WebElement> seatinsummary = driver.findElements(BookingPageLocators.seatInsummary);
		if(seatinsummary.size()==0){
			Reporter.SuccessReport("Verifing Seat Removing", "Successfully Removed");
		}
			
	}
	public boolean selectSeat(String seatSelect, String bookingtype) throws Throwable {
		waitForElementPresent(BookingPageLocators.selectseattittle, "SelectSeatTittle");
		waitUtilElementhasAttribute(BookingPageLocators.body);	
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("window.scrollBy(0,250)", "");
		List<WebElement> Flights = driver.findElements(By.xpath("//div[@class='tabwrap']/ul/li/a"));
		if(bookingtype.equalsIgnoreCase("CodeShare")||bookingtype.equalsIgnoreCase("PartcodeShare"))
		{
			click(BookingPageLocators.continueBtn, "Continue");
			if(isElementDisplayedTemp(By.xpath("//button[text()='OK']"))){
				click(By.xpath("//button[text()='OK']"), "OK Button");
			}
			else
			{
				System.out.println("No Alert Present");
			}

		}
		else
		{	
					
			for(int i=0;i<Flights.size();i++)
			{			
				//Flights.get(i).click();
				Thread.sleep(3000);
				if(seatSelect.equalsIgnoreCase("Extra Leg Room")){
					if(Flights.size()>1 && i==1)
					{
						List<WebElement> Extraseats = driver.findElements(BookingPageLocators.selExLegSeat);
						for(int k=Extraseats.size()/2;k<Extraseats.size();k++)
						{
							Extraseats.get(k+3).click();
							break;
						}
					}
					else
						{
					//	executor.executeScript("arguments[0].scrollIntoView(true);",BookingPageLocators.selExLegSeat);
						click(BookingPageLocators.selExLegSeat,"Extra Leg Room");
						}
					}
					
				else if(seatSelect.equalsIgnoreCase("Premium"))
					click(BookingPageLocators.selPremSeat,"Premium");
				else if(seatSelect.equalsIgnoreCase("Upfront"))
					click(BookingPageLocators.selUpfrontSeat,"Upfront");
				else if(seatSelect.equalsIgnoreCase("Extra Leg Room2"))
					click(BookingPageLocators.selExLeg2Seat,"Extra Leg Room2");
				else if(seatSelect.equalsIgnoreCase("Standard"))
					click(BookingPageLocators.selStdSeat,"Standard");
				else if(seatSelect.equalsIgnoreCase("Business"))
					if(Flights.size()>1 && i==1)
					{
						List<WebElement> Busseats = driver.findElements(BookingPageLocators.selbusSeat);
						for(int k=Busseats.size()/2;k<Busseats.size();k++)
						{
							Busseats.get(k).click();
							break;
						}
					}
					else
					{
						//executor.executeScript("arguments[0].scrollIntoView(true);",BookingPageLocators.selExLegSeat);	
						click(BookingPageLocators.selbusSeat, "Business Seat");
					}
		
				/*if(isElementPresent(By.xpath("//button[text()='OK']"))){
				click(By.xpath("//button[text()='OK']"), "OK Button");
				Thread.sleep(3000);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(BookingPageLocators.continueBtn));
				click(BookingPageLocators.continueBtn, "Continue");
				//Thread.sleep(2000);
			}*/
 			if(isElementDisplayedTemp(By.xpath("//button[text()='OK'  or ./text()='موافق' or ./text()='Tamam']")))
			{
				click(By.xpath("//button[text()='OK' or ./text()='موافق' or ./text()='Tamam']"), "OK Button");
				Thread.sleep(5000);
				/*((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(BookingPageLocators.continueBtn));
				click(BookingPageLocators.continueBtn, "Continue");*/
			}
			else
			{
				System.out.println("No Ok");
			}
		}
			waitUtilElementhasAttribute(BookingPageLocators.body);	
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(BookingPageLocators.continueBtn));
			click(BookingPageLocators.continueBtn, "Continue");
			waitUtilElementhasAttribute(BookingPageLocators.body);	
			if(isElementDisplayedTemp(By.xpath("//button[text()='OK' or ./text()='موافق' or ./text()='Tamam']"))){
				click(By.xpath("//button[text()='OK' or ./text()='موافق' or ./text()='Tamam']"), "OK Button");
			}
			else
			{
				System.out.println("No Alert Present");
			}
		
		}
		return true;
}
	
	
	public boolean payment(String paymentType,String value) throws Throwable {		
		
 		waitforElement(BookingPageLocators.paymentTittle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		System.out.println(paymentType);
			if(paymentType.equalsIgnoreCase("Credit Card"))
			{
				List<WebElement> paymentss = driver.findElements(BookingPageLocators.paymentType);
				for(int i=0;i<paymentss.size();i++){
					if(paymentss.get(i).getText().contains("Credit Card")
					||paymentss.get(i).getText().contains("Kredi Kartı")
					||paymentss.get(i).getText().contains("البطاقات الإئتمانية")){
						paymentss.get(i).click();
						waitUtilElementhasAttribute(BookingPageLocators.body);
						break;
					}
				}
				waitforElement(BookingPageLocators.cardNumber);
				type(BookingPageLocators.cardNumber,configProps.getProperty("cardNumber").trim(),"Card Number");
				type(BookingPageLocators.cardName,configProps.getProperty("cardHolderName"),"Card Holder Name");
				click(BookingPageLocators.expMonth,"Expiry Month");
				//	driver.findElement(By.xpath("//a/div[text()='"+configProps.getProperty("expMM")+"' OR text()='يناير']")).click();
				driver.findElement(By.xpath("//a/div[contains(text(),'"+configProps.getProperty("expMM")+"') or ./text()='يناير' or ./text()='Ocak']")).click();
				//	selectValueFromDropDown(BookingPageLocators.selectExpMonth, "Expiry Month", configProps.getProperty("expMM"));
				click(BookingPageLocators.expYear,"Expiry Year");
				driver.findElement(By.xpath("//a/div[text()='"+configProps.getProperty("expYYYY")+"']")).click();
				//selectValueFromDropDown(BookingPageLocators.selectExpYear, "Expiry Year", configProps.getProperty("expYYYY"));
				type(BookingPageLocators.cvvNum,configProps.getProperty("cvv"),"cvv");
			}
			else if(paymentType.equalsIgnoreCase("Voucher"))
			{
				type(BookingPageLocators.voucherNum,configProps.getProperty("voucher"),"Voucher");
				click(BookingPageLocators.retrieveVoucher, "Retrieve Voucher");
			} 
			else if(paymentType.equalsIgnoreCase("SADAD"))
			{
		
				List<WebElement>  paymentss = driver.findElements(BookingPageLocators.paymentType);
				for(int i=0;i<paymentss.size();i++)
				{
					if(paymentss.get(i).getText().equalsIgnoreCase("SADAD"))
					{
						paymentss.get(i).click();					
						break;
					}
				}
			}
			else if(paymentType.equalsIgnoreCase("Nas"))
			{
				List<WebElement> payments = driver.findElements(BookingPageLocators.paymentType);
				for(int i=0;i<payments.size();i++)
				{
					if(payments.get(i).getText().contains("Nas Credit"))
					{
						payments.get(i).click();
						type(BookingPageLocators.ipt_pnr, value, "PNR");
						Thread.sleep(3000);
						click(BookingPageLocators.Retrive, "Retrive");
						Thread.sleep(5000);
						/*String totaldue = getText(BookingPageLocators.totaldue, "TotalDue");
						String due[] = totaldue.split("\\s");*/
						
						String creditbalance = getText(BookingPageLocators.creditbal, "Credit Balance");
						String credit[] = creditbalance.split("\\s");
						type(BookingPageLocators.Amount, credit[5], "Amount");
						break;
										
						
					}
				}

			}
			else if(paymentType.equalsIgnoreCase("naSmile"))
			{
				List<WebElement> paymentss = driver.findElements(BookingPageLocators.paymentType);
				for(int i=0;i<paymentss.size();i++)
				{
					if(paymentss.get(i).getText().contains("naSmiles"))
					{
						paymentss.get(i).click();					
						break;
					}
				}
				type(BookingPageLocators.naSmileId, value.trim(), "naSmileID");
				type(BookingPageLocators.naSmilepwd, configProps.getProperty("napwd").trim(), "naSmilePwd");
				click(BookingPageLocators.signIn_lnk, "SignIn");
				Thread.sleep(5000);
				if(isElementDisplayedTemp(BookingPageLocators.Error)==true)
				{
					System.out.println("NO Sufitiant points");
				}
				else
				{
				click(BookingPageFlow.redeem, "Redeem");
				Thread.sleep(3000);
				if(isElementDisplayedTemp(BookingPageLocators.ok)==true)
				{
					click(BookingPageLocators.ok, "OK");
					
					payment("Credit Card", "");
					paymentType="naSmile";
					
				}
				}
				
			}
			else if(paymentType.equalsIgnoreCase("Agency Payment"))
			{
				List<WebElement> paymentss = driver.findElements(BookingPageLocators.paymentType);
				for(int i=0;i<paymentss.size();i++)
				{
					if(paymentss.get(i).getText().contains("Agency Payment"))
					{
						paymentss.get(i).click();					
						break;
					}
				}
			}
			else if(paymentType.equalsIgnoreCase("Hold Booking")){
				List<WebElement> paymentss = driver.findElements(BookingPageLocators.paymentType);
				for(int i=0;i<paymentss.size();i++)
				{
					if(paymentss.get(i).getText().contains("Hold Booking"))
					{
						paymentss.get(i).click();					
						break;
					}
				}
			}
		if(!paymentType.equalsIgnoreCase("naSmile"))
		{
			Thread.sleep(2000);
			/*if(driver.findElement(BookingPageLocators.ccCheckbox).isSelected()==true)
			{
				System.out.println("Already Checked");
			}
			else
			{
			click(BookingPageLocators.ccCheckbox, "Terms & Conditions");
			}
			Thread.sleep(2000);*/
			click(BookingPageLocators.continueBtn, "Continue");
			
		if(isElementDisplayedTemp(BookingPageLocators.Error)==true && paymentType.equalsIgnoreCase("SADAD"))
		{
			click(BookingPageLocators.ok, "OK");
			Reporter.SuccessReport("Verifing SADAD", "Payment through SADAD not posible");
			
		}
		else
		{
			System.out.println("No Error Message");
		}
		if(isElementDisplayedTemp(BookingPageLocators.ok)==true)
		{
			click(BookingPageLocators.ok, "OK");
			payment("Credit Card", "");
		}
		else
		{
			System.out.println("No Alert");
		}
		if(paymentType.equalsIgnoreCase("Credit Card")){
			if(isElementDisplayedTemp(BookingPageLocators.pasword)==true)
			{
				type(BookingPageLocators.pasword, "1234", "Password");
				click(BookingPageLocators.ccSubmit,"Submit Button");
				if(isElementDisplayedTemp(BookingPageLocators.ok)==true)
				{
					click(BookingPageLocators.ok, "OK");
					payment("Credit Card", "");
				}
			}
			else
			{
				System.out.println("No Secure Page");
			}
			
		}
		else
		{
			System.out.println(" ");
		}
		}
		return true;
	}
	
	public String getReferenceNumber() throws Throwable{
		waitforElement(BookingPageLocators.summaryRefNumber);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		System.out.println(getText(BookingPageLocators.summaryRefNumber, "Reference Number"));
		return getText(BookingPageLocators.summaryRefNumber, "Reference Number");
	}
	
	public void searchFlight(String referenceNum, String email, String mobile, String lastName) throws Throwable{
		// add validation
		driver.get(configProps.getProperty("URL_Search"));
		waitforElement(BookingPageLocators.sfpbookingReference);
		type(BookingPageLocators.sfpbookingReference, referenceNum, "Reference Number");
		type(BookingPageLocators.sfpEmail, email, "Email");
		//type(BookingPageLocators.sfpMoblie, mobile, "Mobile");
		//type(BookingPageLocators.sfpLastName, lastName, "Last Name");
		click(BookingPageLocators.sfpFindBooking, "Find booking");	
		waitforElement(BookingPageLocators.manageMyBookingTittle);
	
	}
	
	
	public void searchFlightCheckin(String referenceNum, String email, String mobile, String lastName) throws Throwable{
		// add validation
		driver.get(configProps.getProperty("URL_Checkin"));
		waitForElementPresent(BookingPageLocators.sfpbookingReference, "Reference Number");
		type(BookingPageLocators.sfpbookingReference, referenceNum, "Reference Number");
		type(BookingPageLocators.sfpEmail, email, "Email");
		//type(BookingPageLocators.sfpMoblie, mobile, "Mobile");
		type(BookingPageLocators.sfpLastName, lastName, "Last Name");
		click(BookingPageLocators.checkInNow, "Check in now");
		
	}

	
	public String changeDate(String referenceNum, String email, String mobile, String lastName, String newDate, 
			String selectSeat,String bookingtype,String bookingClass,int i) throws Throwable{
		
		searchFlight(referenceNum, email, mobile, lastName);
		waitforElement(BookingPageLocators.btnchngFlight);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		click(BookingPageLocators.btnchngFlight, "Change Flight");
		waitforElement(BookingPageLocators.btnpopupchngFlight);
		List<WebElement> calenders = driver.findElements(BookingPageLocators.calender);
		calenders.get(i).click();
	//	click(BookingPageLocators.calender, "Flight Calender");
		selectDate(BookingPageLocators.selectDate,"Departure Date", newDate);
		click(BookingPageLocators.btnpopupchngFlight, "Change flight");
		
		waitUtilElementhasAttribute(BookingPageLocators.body);
		
		List<WebElement> Eco = driver.findElements(BookingPageLocators.economyOW);
		List<WebElement> Fle = driver.findElements(BookingPageLocators.flexOW);
		List<WebElement> Bus = driver.findElements(BookingPageLocators.busOW);
	
		if(bookingClass.equalsIgnoreCase("Economy")){
			for(int j=0;j<Eco.size();j++){
				if(Eco.get(j).isDisplayed()==true){
					Eco.get(j).click();
					break;
				}
			}
			
		}else if(bookingClass.equalsIgnoreCase("Flex")){
			click(BookingPageLocators.flexOW, "Flex");
		}else if(bookingClass.equalsIgnoreCase("Business")){
			for(int j=0;j<Bus.size();j++){
				if(Bus.get(j).isDisplayed()==true){
					Bus.get(j).click();
					break;
				}
			}
		}
		/*else
			if(bookingClass.equalsIgnoreCase("Staff Confirmed"))
			{			
				waitForElementPresent(By.xpath("//table[@class='table flight_table']"), "");
				List<WebElement> flighttables = driver.findElements(By.xpath("//table[@class='table flight_table']"));
				List<WebElement> stafcnfdClass = flighttables.get(i).findElements(By.xpath("tbody/tr/td[7]"));
				for(int j=0;j<stafcnfdClass.size();j++)
				{
					if(stafcnfdClass.get(j).findElement(By.tagName("div")).getText().equalsIgnoreCase("Sold out"))
					{
						System.out.println("Sold Out");
					}
					else
					{
						stafcnfdClass.get(j).click();
						break;
					}
			
				}

			}
		else
		{
			waitForElementPresent(By.xpath("//table[@class='table flight_table']"), "");
			List<WebElement> flighttables = driver.findElements(By.xpath("//table[@class='table flight_table']"));
			List<WebElement> stafstandClass = flighttables.get(i).findElements(By.xpath("tbody/tr/td[6]"));
			for(int j=0;j<stafstandClass.size();j++)
			{
				if(stafstandClass.get(j).findElement(By.tagName("div")).getText().equalsIgnoreCase("Sold out"))
				{
					System.out.println("Sold Out");
				}
				else
				{
					stafstandClass.get(j).click();
					break;
				}
		
			}
		}*/
		waitforElement(BookingPageLocators.continueBtn);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		click(BookingPageLocators.continueBtn, "Continue");
		selectSeat(selectSeat,bookingtype);
		waitForElementPresent(BookingPageLocators.manageMyBookingTittle, "Manage My Booking");
		waitUtilElementhasAttribute(BookingPageLocators.body);
		if(isElementPresent(BookingPageLocators.paynow)==true)
		{
			System.out.println("In paynow");
			/*String balance= getText(BookingPageLocators.balance, "Balance");
			String bal[] = balance.split("\\s");*/
			click(BookingPageLocators.paynow, "PayNow");
			
			payment("Credit Card", "");
		}
		else
		{
			System.out.println("In confirmmsg");
			if(isElementPresent(BookingPageFlow.confirmmsg)==true)
			{
				click(BookingPageLocators.confirmmsg, "Confirmation");
				if(isElementPresent(BookingPageLocators.ok)==true)
				{
					click(BookingPageLocators.ok, "OK");
					waitForElementPresent(BookingPageLocators.manageMyBookingTittle, "Manage My Bookings");
					waitUtilElementhasAttribute(BookingPageLocators.body);
				}
			}
		}
		//Alert();
		return getReferenceNumber();
	}	
	
/*	public void searchFlightinCheckin(String referenceNum, String email, String mobile, String lastName) throws Throwable{
		// add validation
		driver.get(configProps.getProperty("URL_Checkin"));
		Thread.sleep(5000);
		type(BookingPageLocators.sfpbookingReference, referenceNum, "Reference Number");
		//type(BookingPageLocators.sfpEmail, email, "Email");
		//type(BookingPageLocators.sfpMoblie, mobile, "Mobile");
		type(BookingPageLocators.sfpLastName, lastName, "Last Name");
		
		click(BookingPageLocators.sfpChekin, "Check in");
	}*/
	
	
	public void performCheckin(String SelectSeat,String paymenttype, String strPassenger) throws Throwable
	{
		waitforElement(BookingPageLocators.checkinTitle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
 		List<WebElement> passengers = driver.findElements(BookingPageLocators.passengers_incheckin);
		System.out.println(passengers.size());
		for(int i=0;i<passengers.size();i++)
		{
			passengers.get(i).click();
		}
		waitUtilElementhasAttribute(BookingPageLocators.body);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(BookingPageLocators.continueBtn));
		click(BookingPageLocators.passengers_checkterms, "CheckBox for agriment");
		click(BookingPageLocators.continueBtn, "Continue");
		waitforElement(BookingPageLocators.emailAdd);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		String emailformat = getText(BookingPageLocators.emailAdd, "EmailAddress");
		if(!emailformat.contains("@"))
		{
			type(BookingPageLocators.emailAdd, "flynasqa@gmail.com", "EmailAddress");
		}
		waitforElement(BookingPageLocators.continueBtn);
		click(BookingPageLocators.continueBtn, "Continue");
		waitforElement(BookingPageLocators.baggagetittle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		click(BookingPageLocators.continueBtn, "Continue");
		waitforElement(BookingPageLocators.selectseattittle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		if(isElementDisplayedTemp(BookingPageLocators.selectseattittle))
		{
			selectSeat(SelectSeat, "");
		}else
		{
			System.out.println("No Seat Page is Available in CheckIn");
		}
		if(isElementDisplayedTemp(BookingPageLocators.paymentTittle))
		{
			payment(paymenttype, "");
		}else
		{
			System.out.println("No Payment is Available in CheckIn");
		}
		
	}

	public void Baggage(String bookingtype) throws Throwable
	{
		explicityWait(BookingPageLocators.Adult_baggagae, "Baggage");
		
		if(bookingtype.equalsIgnoreCase("CodeShare")||bookingtype.equalsIgnoreCase("PartcodeShare"))

		{

			click(BookingPageLocators.continueBtn, "Continue");	

		}

		else

		{
		
		List<WebElement>  Adults = driver.findElements(BookingPageLocators.Adult_baggagae);
		for(int i=0;i<Adults.size();i++)
		{
			String value=Adults.get(i).getText();
			if(value.equalsIgnoreCase("Adult 1"))
			{
				Adults.get(i).click();
				ImplicitWait();
				List<WebElement>  Baggage_weight = driver.findElements(BookingPageLocators.Baggage_weight);
				for(int j=1;j<=Baggage_weight.size();j++)
				{
					Baggage_weight.get(j).click();
					ImplicitWait();
					break;

				}
				

			}
			else
				if(value.equalsIgnoreCase("Child 1"))
				{
					Adults.get(i).click();
					ImplicitWait();
					List<WebElement>  Baggage_weight = driver.findElements(BookingPageLocators.Baggage_weight);
					for(int j=1;j<=Baggage_weight.size();j++)
					{
						Baggage_weight.get(j).click();
						ImplicitWait();
						break;
					}

					
				}
		}

				
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(BookingPageLocators.continueBtn));
		click(BookingPageLocators.continueBtn, "Continue");
	}	
	
	public void validateCheckin() throws Throwable
	{
		waitforElement(BookingPageLocators.checkinConformation);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		if(isElementPresent(BookingPageLocators.checkinConformation,"Check-in Conformation"))
		{
			Reporter.SuccessReport("Validating check in", "Checkin is Done");
		}
		else
		{
			Reporter.failureReport("Validating check in", "Check in is Not Done");
		}
	}
	
	public void validate_ticketStatus(String pnr) throws Throwable
	{
		waitforElement(BookingPageLocators.summaryStatus);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		if(getText(BookingPageLocators.summaryStatus,"Status").trim().equals("Confirmed"))
		{
			Reporter.SuccessReport("Ticket Confirmation", "Ticket has booked with PNR "+pnr);
			writingPNR("UATRoutes_PNR",pnr);
		}
		else
		{
			Reporter.failureReport("Ticket Confirmation", "Ticket has not booked");
			writingPNR("UATRoutes_PNR","Fail");
		}
	}
	public void validate_ticketStatus_AR(String pnr) throws Throwable
	{
		waitforElement(BookingPageLocators.summaryStatus_AR_uat);
		if(getText(BookingPageLocators.summaryStatus_AR_uat,"Status").trim().equals("مؤكد"))
		{
			Reporter.SuccessReport("Ticket Confirmation", "Ticket has booked with PNR"+" "+pnr);
			writingPNR("UATRoutes_PNR",pnr);
		}
		else
		{
			Reporter.failureReport("Ticket Confirmation", "Ticket has not booked");
			writingPNR("UATRoutes_PNR","Fail");
		}
	}
	public void validate_ticketStatus_TR(String pnr) throws Throwable
	{
		waitforElement(BookingPageLocators.summaryStatus_TR_uat);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		if(getText(BookingPageLocators.summaryStatus_TR_uat,"Status").trim().equals("Confirmed")
			||getText(BookingPageLocators.summaryStatus_TR_uat,"Status").trim().equals("Onaylandı")	)
		{
			Reporter.SuccessReport("Ticket Confirmation", "Ticket has booked with PNR"+" "+pnr);
			writingPNR("UATRoutes_PNR",pnr);
		}
		else
		{
			Reporter.failureReport("Ticket Confirmation", "Ticket has not booked");
			writingPNR("UATRoutes_PNR","Fail");
		}
	}
	
	public void verifyPNRforSadad() throws Throwable{
		
		if(isElementPresent(BookingPageLocators.txtPNR, "Booking Reference")){
			String pnr = driver.findElement(By.xpath("//div[text()='Booking Reference: ']/b")).getText();
			Reporter.SuccessReport("Ticket Confirmation", "Ticket has booked,PNR : " + pnr);
			writingPNR("ProductionRoutes_pnr",pnr);
		}
		else
		{
			Reporter.SuccessReport("Ticket Confiramation", "Ticket has not booked");
			writingPNR("ProductionRoutes_pnr","Fail");
		}	
	}
	public void writingPNR(String sheetname,String value)
	{
		ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),sheetname);
		int rownum = xls.getRowCount(sheetname);
		if(xls.getCellData(sheetname, "PNR", rownum)==null){
			xls.setCellData(sheetname, "PNR", rownum, value);
			xls.setCellData(sheetname, "TestCaseNum", rownum,Integer.toString(rownum));
			
		}else{
			xls.setCellData(sheetname, "PNR", rownum+1, value);
			xls.setCellData(sheetname, "TestCaseNum", rownum+1,testName);
		}
	}
	public void verifingStatusSadad() throws Throwable
	{
		if(getText(BookingPageLocators.summaryStatus, "PNR Status").equalsIgnoreCase("Pending"))
		{
			Reporter.SuccessReport("PNR Status", "Pending");
		}
		else
		{
			Reporter.SuccessReport("PNR Status", "Not Pending");
		}
	}
	
	public static String pickDate(String xlsDate){
		
		String[] depdate = xlsDate.split("\\^");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, Integer.parseInt(depdate[1])); // Adding 1 days
		String newDeptDate = sdf.format(c.getTime());
		return newDeptDate;
		
	}
	public static String newDateForCheckIN(String date) throws ParseException
	{
		String dt = date;  // Start date
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(dt));
		c.add(Calendar.DATE,1);  // number of days to add
		dt = sdf.format(c.getTime());  // dt is now the new date
		return dt;
	}
	
	public void selctClasswithCodeshare(String bookingtype,String bookingClass,String tripType) throws Throwable
	{
		waitforElement(BookingPageLocators.selectflightsection);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		boolean flag=false;
		int count=0;
		List<WebElement> flighttables = driver.findElements(By.xpath("//div[@class='pdis picon picon_plane']/following::div[@class='flight_mtable']"));
		for(int i=0;i<flighttables.size();i++){
				List<WebElement> flexFlt = flighttables.get(i).findElements(By.xpath("div/div/div/div/button[@class='btn selectflightbtn col2']"));
				
				for(int j=0;j<flexFlt.size();j++){
					
					String flightNumber = flexFlt.get(j).findElement(By.xpath("preceding::span[6]")).getText();
					count=0;
					for(int k=0;k<flightNumber.length();k++){
						 char result = flightNumber.charAt(k);
						 	 if(Character.isDigit(result)){
						 		 	count++;
						 	 	}
					}
					if(count==8 && bookingtype.equalsIgnoreCase("CodeShare")){
						flexFlt.get(j).click();
						Reporter.SuccessReport("Verifing selecting Codeshare Flight", "Successfully clicked codeshare flight");
						waitUtilElementhasAttribute(BookingPageLocators.body);
						flag=true;
						break;
					}
					if(count==7 && bookingtype.equalsIgnoreCase("PartcodeShare")){
						flexFlt.get(j).click();
						Reporter.SuccessReport("Verifing selecting Part Codeshare Flight", "Successfully clicked Partcodeshare flight");
						waitUtilElementhasAttribute(BookingPageLocators.body);
						flag=true;
						break;
					}
				}
				
		}
			if(flag==false)
			{
				System.out.println("Flights are not available,Try with another booking class or Another date");
				Reporter.failureReport("Verifing Select Booking Class with "+ bookingtype, "Required Booking class not available,select another booking class");
			}
		
		waitUtilElementhasAttribute(BookingPageLocators.body);
		click(BookingPageLocators.continueBtn, "Continue");
	}
	public void selectCodeshareConectflight(String bookingClass,String bookingtype) throws Throwable{
		waitforElement(BookingPageLocators.selectflightsection);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		boolean flag=false;
		int count=0;
		List<WebElement> flighttables = driver.findElements(By.xpath("//table[@class='table flight_table']"));
			for(int i=0;i<flighttables.size();i++)
			{
				
					List<WebElement> Flights_row = flighttables.get(i).findElements(By.tagName("tr"));
					System.out.println("ROWS COUNT"+ Flights_row.size());
					for(int j=1;j<Flights_row.size();j++)
					{ 
						count=0;
						List<WebElement> Flights_td = Flights_row.get(j).findElements(By.tagName("td"));
						System.out.println("COL COUNT"+ Flights_td.size());
						
						String stop =  Flights_row.get(j).findElement(By.xpath("td[2]/div/div/span")).getText();
						if(stop.contains("1 Stop")){
							WebElement Flightnumber_span =Flights_td.get(3).findElement(By.xpath("div/div/span"));
							String Flightnumber = Flightnumber_span.getText();
							System.out.println(Flightnumber);
							for(int k=0;k<Flightnumber.length();k++){
							 char result = Flightnumber.charAt(k);
							 	 if(Character.isDigit(result)) {
								 count++;
							 }
						}
						}
						if((count==8 ||count==4)&& bookingtype.equalsIgnoreCase("CodeShare"))
						{
							if(bookingClass.equalsIgnoreCase("Flex")){
								if((Flights_td.get(5).findElement(By.tagName("div")).getText().equalsIgnoreCase("Sold out"))){
									System.out.println("Sold out");
								}else{
									Flights_td.get(5).findElement(By.tagName("button")).click();
									Thread.sleep(2000);
									flag=true;
									break;
									
								}
							}
							if(bookingClass.equalsIgnoreCase("Economy")){
								if((Flights_td.get(4).findElement(By.tagName("div")).getText().equalsIgnoreCase("Sold out"))){
									System.out.println("Sold out");
								}else{
									Flights_td.get(4).findElement(By.tagName("button")).click();
									Thread.sleep(2000);
									flag=true;
									break;
									
								}
							}
						}		
	}
			}
	}
	public void Select_A_Meal() throws Throwable
	{
	//	waitforElement(BookingPageLocators.baggagetittle);
		if(isElementDisplayedTemp(BookingPageLocators.InFlightMeal)==true){
		waitUtilElementhasAttribute(BookingPageLocators.body);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,600)", "");
		removeMeal();
		List<WebElement> flights = driver.findElements(BookingPageLocators.flightsinSelectmeal);
		List<WebElement> passengers = driver.findElements(BookingPageLocators.passengersToSelectMeal);
		List<WebElement> select = driver.findElements(BookingPageLocators.select);
		List<WebElement> error = driver.findElements(BookingPageLocators.mealError);
		List<WebElement> meal =driver.findElements(BookingPageLocators.meal);
		int k=0, i=1;
			if(error.size()==0){
				flights.get(0).click();
				for(int j=0;j<passengers.size();j++){
					
					if(passengers.get(j).isDisplayed()==true){
						passengers.get(j).click();
						select.get(k).click();
						Reporter.SuccessReport("Verifing Select Meal", "Selected");
						k=k+2;
						waitUtilElementhasAttribute(BookingPageLocators.body);
					}else{
						flights.get(i).click();
						i=i+1;
					}
			}	
			}else{
				
				if(error.size()>0){
					int a=0;
					for(int f=0;f<flights.size();f++){
					flights.get(f).click();
									
					if(error.get(a).isDisplayed()==true){
						if(error.size()==1){
							a=0;
						}else{
							a=a+1;
						}
						Reporter.SuccessReport("Verifing Select Meal", "No Meal Available to select"+f);
					
					}else{
						for(int p=0;p<passengers.size();p++){
							if(p>passengers.size()){
								break;
							}
							if(passengers.get(p).isDisplayed()==false){
									break;
							}		
						
						passengers.get(p).click();
						select.get(k).click();
						waitUtilElementhasAttribute(BookingPageLocators.body);
						Reporter.SuccessReport("Verifing Select Meal", "Selected");
						k=k+2;
						waitUtilElementhasAttribute(BookingPageLocators.body);
					}					
					}
					
				}
			}
			}	
		}else{
			System.out.println("NO Meal Available in this Route");
			Reporter.SuccessReport("Verifing Meal Select", "NO Meal Available in this Route");
		}
	}
	public void removeMeal() throws Throwable{
		List<WebElement> error = driver.findElements(BookingPageLocators.mealError);
		List<WebElement> passengers = driver.findElements(BookingPageLocators.passengersToSelectMeal);
		List<WebElement> flights = driver.findElements(BookingPageLocators.flightsinSelectmeal);
		List<WebElement> remove = driver.findElements(BookingPageLocators.Remove);
		int i=1;
		if(error.size()==0){
			for(int j=0;j<remove.size();j++){
				if(remove.get(j).isDisplayed()==true){
					remove.get(j).click();
					waitUtilElementhasAttribute(BookingPageLocators.body);
					
				}
				else{
					flights.get(i).click();
					i=i+1;
				}
		}	
		}else
		{
			if(error.size()>0){
				int a=0;
				for(int f=0;f<flights.size();f++){
				flights.get(f).click();
								
				if(error.get(a).isDisplayed()==true){
					if(error.size()==1){
						a=0;
					}else{
						a=a+1;
					}
					Reporter.SuccessReport("Verifing Select Meal", "No Meal Available to Remove"+f);
				
				}else{
					for(int p=0;p<remove.size();p++){
						if(p>remove.size()){
							break;
						}
						if(remove.get(p).isDisplayed()==false){
								break;
						}		
					
						remove.get(p).click();
						waitUtilElementhasAttribute(BookingPageLocators.body);
				}					
				}
				
			}
		}
			
		}
		List<WebElement> mealSummary = driver.findElements(BookingPageLocators.mealInSummary);
		if(mealSummary.size()==0){
			Reporter.SuccessReport("Verifing Meal Removing Function", "Successfully Removed");
			
		}
	}
	public void Select_longue() throws Throwable
	{
		if(isElementPresent(BookingPageLocators.Loung)==true)
		{
			
			List<WebElement> allPassengers=driver.findElements(BookingPageLocators.allPassengers_Loung);
			for(int i=0;i<allPassengers.size();i++)
			{
				allPassengers.get(i).click();
				waitUtilElementhasAttribute(BookingPageLocators.body);
				Reporter.SuccessReport("Verifing Selectinh Loung", "Successfully Selected");
				
			}
		/*List<WebElement> Loung = driver.findElements(BookingPageLocators.Loung_table);
		
			for(int i=1 ;i<Loung.size()-1;i++)
		{
			if(Loung.get(i).findElement(By.tagName("input")).getAttribute("value")=="true")
			{
				Loung.get(i).findElement(By.tagName("input")).click();
				break;
			}
			else
			{
				Loung.get(i+1).findElement(By.tagName("input")).click();
				break;
			}
		}*/
		}
		
		else
		{
			System.out.println("No Loung");
			Reporter.SuccessReport("Verifing Loung Select", "No Loung is available to select");
		}
		//click(BookingPageLocators.continueBtn, "Continue");
	}
	public  String cancelFlight() throws Throwable
	{
		waitforElement(BookingPageLocators.manageMyBookingTittle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		scrollToElement(BookingPageLocators.summaryCancelFlight);
		click(BookingPageLocators.summaryCancelFlight, "CancelFlight");
		List<WebElement> cancelflights = driver.findElements(BookingPageLocators.selectFlightstoCancel);
		for(int i=0;i<cancelflights.size();i++)
		{
			cancelflights.get(i).click();
		}
		waitUtilElementhasAttribute(BookingPageLocators.body);
		click(BookingPageLocators.cancelflightBtn, "CancelFlight");
		waitforElement(BookingPageLocators.priceBeforeChange);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		String priceBeforChange = getText(BookingPageLocators.priceBeforeChange, "PriceBeforeChange");
		/*String[] price = priceBeforChange.split("\\s");*/
		waitforElement(BookingPageLocators.priceBeforeChange);
		click(BookingPageLocators.conformCharges, "Conform Charges");
		if(isElementDisplayedTemp(BookingPageLocators.ok)){
		click(BookingPageLocators.ok, "ok");
		}
		//Thread.sleep(5000);
		verifyConformcharges();
		
		return "";
		
	}
	public void verifyConformcharges() throws Throwable
	{
		waitforElement(BookingPageLocators.conformedAftercharges);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		if(isElementDisplayedTemp(BookingPageLocators.conformedAftercharges)==true)
		{
			Reporter.SuccessReport("Verifing Confirmed after clicking conform charges", "Successfully Verified Cancel Conformed");
		}
		else
		{
			Reporter.failureReport("Verifing Confirmed after clicking conform charges", "fail to verify Cancel Conformed");
		}
	}
	public void Baggage_Extra(String Triptype) throws Throwable
	{
		waitforElement(BookingPageLocators.baggagetittle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		boolean flag=true;
		if(Triptype.equalsIgnoreCase("One way"))
		{
			List<WebElement>  passengers = driver.findElements(BookingPageLocators.Adult_baggagae);
			List<WebElement> addBaggage = driver.findElements(By.xpath("//div[@class='baggage_box']/div[2]/a[2]/i"));
			int loop=0;
			for(int i=0;i<passengers.size();i++)
			{
				
					passengers.get(i).click();
					Thread.sleep(2000);
					List<WebElement>  Baggage_weight = driver.findElements(BookingPageLocators.Baggage_weight);
					for(int j=loop;j<=Baggage_weight.size();j++)
					{
						if(addBaggage.size()!=0)
						{
							addBaggage.get(j).click();
							waitforElement(BookingPageLocators.Adult_baggagae);
							waitUtilElementhasAttribute(BookingPageLocators.body);
							Reporter.SuccessReport("Add Extra Baggage", "Selected");
							loop=j+3;
							break;
						}
						else
						{
							Reporter.SuccessReport("Add Extra Baggage", "No Baggage Available");
						}
					}
				
				
			}
			
	

	}
		else
			
		{ 
			
			int x=0;
			List<WebElement>  flights = driver.findElements(BookingPageLocators.selected_Flights_baggage);
		
			for(int j=0;j<flights.size();j++)
			{
				if(flights.get(j).isDisplayed()==false)
				{
					List<WebElement>  pasenger = driver.findElements(BookingPageLocators.Adult_baggagae);
					pasenger.get(pasenger.size()-1).click();
					j=j-1;
					continue;
					
				}
				System.out.println(flights.get(j)+" "+ ":" +j);
				List<WebElement> Weight= flights.get(j).findElements(By.xpath("following::div[3]/div[1]/div/div/div[2]/a[2]/i"));
			//	List<WebElement> addBaggage = driver.findElements(By.xpath("//div[@class='baggage_box']/div[2]/a[2]/i"));
				if(Weight.size()==3)
				{   

					Weight.get(x).click();
					waitUtilElementhasAttribute(BookingPageLocators.body);
					Reporter.SuccessReport("Extra Baggage", "Selected");
					
										
				}
				else
				{
					System.out.println("No Extra Baggage is Available to select");
				}

			}
			
			
			
		}
}
	
	public void validatingRemoveBaggage() throws Throwable
	{
		waitforElement(BookingPageLocators.baggagetittle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		boolean flag=true;
		List<WebElement> plusBaggage = driver.findElements(By.xpath("//div[@class='baggage_box']/div[2]/a[2]/i"));
		for(int i=0;i<2;i++){
			plusBaggage.get(i).click();
			waitUtilElementhasAttribute(BookingPageLocators.body);
		}
		List<WebElement> minusBaggage = driver.findElements(By.xpath("//div[@class='baggage_box']/div[2]/a[1]/i"));
		for(int i=0;i<minusBaggage.size()-1;i++){
			minusBaggage.get(i).click();
			waitUtilElementhasAttribute(BookingPageLocators.body);
		}
		List<WebElement> baginsummary = driver.findElements(BookingPageLocators.baggageInSummary);
		if(baginsummary.size()==0){
			Reporter.SuccessReport("Validating Remove Baggage Functionality", "Baggage Removed Successfully");
		}
		/*if(isElementDisplayedTemp(BookingPageLocators.baggageInSummary)==false){
			Reporter.SuccessReport("Validating Remove Baggage Functionality", "Baggage Removed Successfully");
		}else{
			Reporter.failureReport("Validating Remove Baggage Functionality", "Baggage Not Removed");
		}*/
	}
	public void addSportsEqpmnt(String Triptype) throws Throwable
	{
		waitUtilElementhasAttribute(BookingPageLocators.body);
		List<WebElement>  passengers = driver.findElements(BookingPageLocators.Adult_baggagae);
		List<WebElement> sportsEqpmntLink = driver.findElements(BookingPageLocators.sportsEqpmntLink);
		
		if(Triptype.equalsIgnoreCase("One way")){
			for(int i=0;i<passengers.size();i++)
			{
				passengers.get(i).click();
				waitforElement(BookingPageLocators.sportsEqpmntLink);
				waitUtilElementhasAttribute(BookingPageLocators.body);
				sportsEqpmntLink.get(i).click();
				List<WebElement> sportsEqpmntdrop = driver.findElements(BookingPageLocators.sportsEqpmtDrop);
				sportsEqpmntdrop.get(i).click();
				click(BookingPageLocators.bicycle, "Bicycle");
				waitUtilElementhasAttribute(BookingPageLocators.body);
			}
		}
		else
		{
			passengers.get(0).click();
			List<WebElement>  flights = driver.findElements(BookingPageLocators.selected_Flights_baggage);
			for(int j=0;j<flights.size();j++){
				if(flights.get(j).isDisplayed()==false)
				{
					List<WebElement>  pasenger = driver.findElements(BookingPageLocators.Adult_baggagae);
					pasenger.get(pasenger.size()-1).click();
					j=j-1;
					continue;
					
				}
				System.out.println(flights.get(j)+" "+ ":" +j);
				WebElement sortsLink= flights.get(j).findElement(By.xpath("following::a[@class='link']"));
			//	List<WebElement> addBaggage = driver.findElements(By.xpath("//div[@class='baggage_box']/div[2]/a[2]/i"));
				if(sortsLink.isDisplayed())
				{   
					sortsLink.click();
					flights.get(j).findElement(By.xpath("following::div[@name='sporteq']")).click();
					click(BookingPageLocators.bicycle, "Bicycle");
					waitUtilElementhasAttribute(BookingPageLocators.body);
														
				}
				else
				{
					System.out.println("No Sports Equipment is Available to select");
				}
			}
			
	}
		
	}
	public void selectClassForStaff(String bookingClass) throws Throwable
	{
		waitforElement(BookingPageLocators.selectflightsection);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		List<WebElement> flighttables = driver.findElements(By.xpath("//table[@class='table flight_table']"));	
		
		
		for(int i=0;i<flighttables.size();i++)
		{
			List<WebElement> flightstatus = driver.findElements(By.xpath("//li[@class='current']/a/span[2]"));
			List<WebElement> standByClass = flighttables.get(i).findElements(By.xpath("tbody/tr/td[6]/button"));
			List<WebElement> stafcnfdClass = flighttables.get(i).findElements(By.xpath("tbody/tr/td[7]/button"));
			
			if(flightstatus.get(i).getText().equalsIgnoreCase("No flights") || standByClass.size()==0
					|| stafcnfdClass.size()==0)
			{
				for(int k=1;k<=10;k++)
				{
				String status=driver.findElement(By.xpath("//li[@class='current']/following-sibling::li["+k+"]/a/span[2]")).getText();
				if(!status.equalsIgnoreCase("No flights"))
				{
					driver.findElement(By.xpath("//li[@class='current']/following-sibling::li["+k+"]")).click();
					waitUtilElementhasAttribute(BookingPageLocators.body);
					if(bookingClass.contains("Staff Stand By")){
						standByClass = flighttables.get(i).findElements(By.xpath("tbody/tr/td[6]/button"));
						if(standByClass.size()!=0){
							break;
						}
					}else{
						if(bookingClass.contains("Staff Confirmed")){
							stafcnfdClass = flighttables.get(i).findElements(By.xpath("tbody/tr/td[7]/button"));
							if(stafcnfdClass.size()!=0){
								break;
							}
						}
					}
					
				}
				else
				{
					System.out.println("NO Flights");
				}
			}
		}
		
			if(bookingClass.equalsIgnoreCase("Staff Stand By"))
			{
				
					standByClass = flighttables.get(i).findElements(By.xpath("tbody/tr/td[6]"));
					for(int j=0;j<standByClass.size();j++)
					{
						if(standByClass.get(j).findElement(By.tagName("div")).getText().equalsIgnoreCase("Sold out"))
						{
							System.out.println("Sold Out");
						}
						else
						{
							standByClass.get(j).click();
							Thread.sleep(2000);
							break;
						}
					
					}
				
			}
			else
				if(bookingClass.trim().equalsIgnoreCase("Staff Confirmed"))
				{					
					
						stafcnfdClass = flighttables.get(i).findElements(By.xpath("tbody/tr/td[7]"));
						for(int j=0;j<stafcnfdClass.size();j++)
						{
							if(stafcnfdClass.get(j).findElement(By.tagName("div")).getText().equalsIgnoreCase("Sold out"))
							{
								System.out.println("Sold Out");
							}
							else
							{
								stafcnfdClass.get(j).click();
								Thread.sleep(2000);
								break;
							}
						
						}
					
				}
		}
		click(BookingPageLocators.continueBtn, "Continue");
	}
	public void VerifingPNR_AgentPOrtal(String pnr) throws Throwable
	{
		String bookingReference = getText(BookingPageLocators.BookingRef, "Booking Reference");
		if(pnr.equalsIgnoreCase(bookingReference))
		{
			Reporter.SuccessReport("Verifing PNR", "PNR is Successfully Generated");
		}
		else
		{
			Reporter.SuccessReport("Verifing PNR", "PNR is not Generated");
		}
	}
	public String memberRegistration(String pwd,String natinality,String doctype,String docnum,String mobile,String emailadd) throws Throwable
	{
		waitforElement(BookingPageLocators.userEmail);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		String  username = randomString();
		String  email = username+"@cigniti.com";
	
		type(BookingPageLocators.userEmail, email, "UserEmail");
		type(BookingPageLocators.pasword_reg, pwd, "Password");
		type(BookingPageLocators.cnfmpasword_reg, pwd, "Conform Password");
		click(BookingPageLocators.title_reg, "Title");
		click(By.xpath(BookingPageLocators.selecttitle_reg.replace("#", "1")), "Title");
		type(BookingPageLocators.fname_reg, randomString(), "Firstname");
		type(BookingPageLocators.lname_reg, randomString(), "Last Name");
		click(BookingPageLocators.dd_reg, "Date");
		click(By.xpath(BookingPageLocators.selectdd_reg+randomNumericString()+"]"), "Date");
		click(BookingPageLocators.mm_reg, "Month");
		click(By.xpath(BookingPageLocators.selectmm_reg+randomNumericString()+"]"), "Month");
		click(BookingPageLocators.yy_reg, "year");
		click(By.xpath(BookingPageLocators.selectyy_reg+randomNumber(12, 19)+"]"), "Year");
		click(BookingPageLocators.natinality_reg, "Nationality");
		click(BookingPageLocators.selectnatinality_reg(natinality), "Nationality");
		click(BookingPageLocators.doctype_reg, "Document Type");
		click(BookingPageLocators.selectdoctype_reg(doctype), "Document Type");
		if(doctype.equalsIgnoreCase("National ID Card"))
		{
			type(BookingPageLocators.docnumber_reg, docnum, "Document Number");
			click(BookingPageLocators.expdd, "Document Expiry Date");
			click(BookingPageLocators.selectDocExpdd, "Expiry Date");
			click(BookingPageLocators.expmm, "Expiry Month");
			click(By.xpath(BookingPageLocators.ppSelectDD+randomNumericString()+"]"), "Expiry Month");
			click(BookingPageLocators.expyy, "Expiry year");
			click(By.xpath(BookingPageLocators.ppSelectDD+randomNumber(10,15)+"]"), "Expiry Year");
			
			
		}
		else
		{
			System.out.println("Passport");
		}
		type(BookingPageLocators.mobileNum, mobile, "MobileNumber");
	//	type(BookingPageLocators.emailAdd, email, "Email Address");
		click(BookingPageLocators.register, "Register");
		return email;
	}
	public void verifingMemberRegistration() throws Throwable
	{
		waitforElement(BookingPageLocators.registerConformation);
		if(isElementPresent(BookingPageLocators.registerConformation)==true)
		{
			Reporter.SuccessReport("Verifing Member Registratin", "Member is Successfully Registered");
		}
		else
		{
			Reporter.failureReport("Verifing Member Registratin", "Member is not Successfully Registered");
		}
	}
	public int verifingFlightsAvailablity() throws InterruptedException
	{
		int count=0;
		List<WebElement> flightTable = driver.findElements(BookingPageLocators.flighttable);
		for(int j=0;j<flightTable.size();j++)
		{
			List<WebElement> flights=flightTable.get(j).findElements(By.xpath("tbody/tr/td[7]"));
			for(int k=0;k<flights.size();k++)
			{
				if(flights.get(k).findElement(By.tagName("button")).isDisplayed()==true)
				{
					count++;
					break;
				}
				else
				{
					System.out.println("All tickets are sold");
				}
			}
		}
		return count;
	}
	public boolean verifingFile()
	{
		boolean flag=false;
		File f=new File("C:\\Users\\E002693\\Downloads");
		File[] files=f.listFiles();
		System.out.println(files.length);
		
		 File lastModifiedFile = files[0];
		 
		    for (int i = 1; i < files.length; i++) {
		       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
		           lastModifiedFile = files[i];
		          // break;
		          }
		    }	
		    
		    if(lastModifiedFile.getName().contains("download")||lastModifiedFile.getName().contains("bp")||lastModifiedFile.getName().contains("NFO9pOA9"))
		    {
		    	flag=true;
		    }
		   
		   /* String str = lastModifiedFile.toString();
		    String[] file = str.split("\\\\");
		    System.out.println(lastModifiedFile);
		    System.out.println(file[6]);
		    
		    PdfReader p =new PdfReader("C:\\Users\\E002693\\Documents\\CLEDownloads\\Download\\"+file[6]);
		    String data = PdfTextExtractor.getTextFromPage(p,1);
		    return data;*/
		 //   System.out.println(data);
	
		return flag;
	}

	public void validateFare(String fare) throws Throwable
	{
		
		By Fare = By.xpath("//span[contains(text(),'"+fare+"')]");
		if(isElementPresent(Fare)==true)
		{
			Reporter.SuccessReport("Revalidate the ticket with same fare", "Ticket has revalidated without any fare changes");
		}
	}
	//production
	public void verifingServiceCharge(String tripType,String Bookingcls,String totalpass) throws Throwable
	{		
		if(Bookingcls.equalsIgnoreCase("Economy")){
			Bookingcls="Simple";
		}else if(Bookingcls.equalsIgnoreCase("Flex")){
			Bookingcls="Extra";
		}
			List<WebElement> collapseButton = driver.findElements(BookingPageLocators.collapsebtn(Bookingcls));
			List<WebElement> flights = driver.findElements(By.xpath("//div[@class='s_flightinfo1 clearfix']/span[1]"));
					
			for(int i=0;i<collapseButton.size();i++)
			{
				collapseButton.get(i).click();
				Thread.sleep(3000);
				List<WebElement> srcharge = driver.findElements(BookingPageLocators.serviceCharge);
				String serviceCharge = srcharge.get(i).getText();
				String[] scharges = serviceCharge.split("\\s");
				float ServiceCharge = Float.parseFloat(scharges[1]);
				
				if(ServiceCharge==25.00*(Integer.parseInt(totalpass)))
				{
					System.out.println("Successfully Verified Service Charge");
					Reporter.SuccessReport("Verifing service Charge Per Person as "+serviceCharge, "Successfully Verified");
				}
				else if(flights.get(0).getText().contains("/"))
				{
					if(ServiceCharge==50.00*(Integer.parseInt(totalpass)))
					{
						System.out.println("Successfully Verified Service Charge");
						Reporter.SuccessReport("Verifing service Charge Per Person as "+serviceCharge, "Successfully Verified");
					}
				}
				collapseButton.get(i).click();
				Thread.sleep(2000);
			}
		
	}
	public void verifingChildDiscount(String bookingClass) throws Throwable
	{
		//String childFare=null;
		if(bookingClass.equalsIgnoreCase("Economy")){
			bookingClass="Simple";
		}else if(bookingClass.equalsIgnoreCase("Flex")){
			bookingClass="Extra";
		}
		List<WebElement> collapseButton = driver.findElements(BookingPageLocators.collapsebtn(bookingClass));
		for(int i=0;i<collapseButton.size();i++)
		{		
			collapseButton.get(i).click();
			Thread.sleep(2000);
			List<WebElement> childamt = driver.findElements(BookingPageLocators.childamt);
			String amount = childamt.get(i).getText();
			String[] Amount = amount.split("\\s");
			String childamount = Amount[1];
			if(childamount.contains("\\,"))
			{
				childamount = childamount.replaceAll("\\,", "");
			}
			float childFre = Float.parseFloat(childamount);
			float avg = (childFre*25)/100;
			String Avg=String.valueOf(avg).split("\\.")[0];
			
			String  discount= getText(BookingPageLocators.discount, "Discount");
			String[] disAmount = discount.split("\\s");
			float Discount = Float.parseFloat(disAmount[1]);
			if(disAmount[1].contains(Avg))
			{
				System.out.println("Successfully Verified Discount");
				Reporter.SuccessReport("Verifing Child Discount of 25%", "Discount Successfully Applied");
			}
			/*String[] childamount = Amount[1].split("\\.");
			int avg=0;
			if(childamount[0].contains("\\,"))
			{
				String childFare = childamount[0].replaceAll("\\,", "");
				int fare = Integer.parseInt(childFare);
				avg = (fare*25)/100;
			}				
			else
			{
				int fare = Integer.parseInt(childamount[0]);
				avg = (fare*25)/100;
			}
		
			if(getText(BookingPageLocators.discount, "Discount").contains(Integer.toString(avg)))
			{
				System.out.println("Successfully Verified Discount");
				Reporter.SuccessReport("Verifing Child Discount of 25%", "Discount Successfully Applied");
			}*/
		}
	}
	public boolean inputBookingDetails_Arabic(String tripType, String origin, String dest, String deptDate,
			String origin2, String departure2, String retDate, String adults, String child, String infant, String promo,String Currency) throws Throwable
	{
		waitforElement(BookingPageLocators.oneWay_pdctn_AR);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		if(tripType.equalsIgnoreCase("Round Trip")){
			click(BookingPageLocators.roundTrip_pdctn_AR, "Round Trip");
		} else if(tripType.equalsIgnoreCase("One Way")){
			click(BookingPageLocators.oneWay_pdctn_AR, "One Way");
		} else if(tripType.equalsIgnoreCase("Multi City")){
			click(BookingPageLocators.multiCity_pdctn_AR, "Multi City");
		}
		click(BookingPageLocators.origin, "Origin");
		selectCity(BookingPageLocators.selectOrigin, "Origin", origin);
		click(BookingPageLocators.dest, "Destination");
		selectCity(BookingPageLocators.selectDest, "Destination", dest);
		/*click(BookingPageLocators.dest, "Destination");
		selectCity(BookingPageLocators.selectDest, "Destination", dest);*/
		click(BookingPageLocators.dpDate_pdctn_AR,"Departure Date");
		selectDate(BookingPageLocators.selectDate,"Departure Date",deptDate);
		
		if(tripType.equalsIgnoreCase("Round Trip")){
		//	click(BookingPageLocators.rtDate,"Return Date");
			selectDate(BookingPageLocators.selectDate,"Return Date",retDate);
		}
		if(tripType.equalsIgnoreCase("Multi City")){
			click(BookingPageLocators.secOrigin, "Origin");
			selectCity(BookingPageLocators.selectOrigin, "Origin", origin2);
			selectCity(BookingPageLocators.selectDest, "Destination", departure2);
			click(BookingPageLocators.rtnDate_pdctn_AR,"Return Date");
			selectDate(BookingPageLocators.selectDate,"Return Date",retDate);
		}
		if(Integer.valueOf(adults)>1){
			click(BookingPageLocators.adult, "Adult");
			System.out.println("Adults: "+adults);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[text()='"+adults+"']")));
			//driver.findElement(By.xpath("//div[text()='"+adults+"']")).click();
			//selectValueFromDropDown(BookingPageLocators.selectAdult, "Adult", adults);
		}
		if(Integer.valueOf(child)>=1){
			click(BookingPageLocators.child_pdctn_AR, "Child");
			driver.findElement(By.xpath("//div[text()='"+child+"']")).click();
			//selectValueFromDropDown(BookingPageLocators.selectChild, "Child", child);
		}
		if(Integer.valueOf(infant)>=1){
			click(BookingPageLocators.infant, "Infant");
			driver.findElement(By.xpath("//div[text()='"+child+"']")).click();
			//selectValueFromDropDown(BookingPageLocators.selectInfant, "Infant", infant);
		}
		if(!promo.equalsIgnoreCase("")){
			type(BookingPageLocators.promo, promo, "Promo");
		}
		click(BookingPageLocators.findFlights_pdctn_AR,"Find Flights");
		return true;
	}
	public String[] inputPassengerDetails_Arabic(String domOrInt, String totalPass, String nationality, 
			String travelDoc, String docNum, String naSmiles, String mobileNum, String emailId,String fname,String lname,String payment2) 
					throws Throwable{
			waitforElement(By.xpath(BookingPageLocators.title.replace("#", String.valueOf(1))));
			waitUtilElementhasAttribute(BookingPageLocators.body);
		
		String lastname = null,firstname=null;
		String[] FirstLastName = null;
		Integer min=0,max=0;
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		try{
			for(Integer count = 1; count<=Integer.valueOf(totalPass); count++){
				if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").contains("بالغ")){
					min = 14; max = 19;
				} else if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").contains("طفل")){
					min = 5; max = 9;
				} else if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").contains("Infant")){
					min = 1; max = 2;
				}
				click(By.xpath(BookingPageLocators.title.replace("#", String.valueOf(count))),"Title");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.titleSelect.replace("#", String.valueOf(count))), "Title");
			
				if(payment2.equalsIgnoreCase("Nas"))
				{
					type(By.xpath(BookingPageLocators.fName.replace("#", String.valueOf(count))), fname, "First Name");

				}
				else
				{
				//Thread.sleep(1000);
				firstname = randomString();
				type(By.xpath(BookingPageLocators.fName.replace("#", String.valueOf(count))), firstname, "First Name");
				}
				if(payment2.equalsIgnoreCase("Nas"))
				{
					type(By.xpath(BookingPageLocators.lName.replace("#", String.valueOf(count))), lname, "Last Name");

				}
				else
				{
				lastname = randomString();
				type(By.xpath(BookingPageLocators.lName.replace("#", String.valueOf(count))), lastname, "Last Name");
				}
				
				click(By.xpath(BookingPageLocators.dd.replace("#", String.valueOf(count))), "DD");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.selectDD.replace("#", String.valueOf(count))+randomNumericString()+"]"), "DD");
				
				click(By.xpath(BookingPageLocators.mm.replace("#", String.valueOf(count))), "MM");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.selectMM.replace("#", String.valueOf(count))+randomNumericString()+"]"), "MM");
				
				click(By.xpath(BookingPageLocators.yyyy.replace("#", String.valueOf(count))), "YYYY");
				//Thread.sleep(3000);
				if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").contains("Infant"))
				{
					List<WebElement> year =driver.findElements(By.xpath(BookingPageLocators.selectyyinfant.replace("#", String.valueOf(count))));
					year.get(1).click();
				}
				else
				{
					executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@class='pass_tab']/div["+count+"]//descendant::div[@class='dob_conyear']/div/ul/li//descendant::div[@class='ui-select-choices-row']["+randomNumber(min,max)+"]")));
					//click(By.xpath(BookingPageLocators.selectYYYY.replace("#", String.valueOf(count))+randomNumber(min,max)+"]"), "YYYY");
				}
				
				click(By.xpath(BookingPageLocators.nation.replace("#", String.valueOf(count))), "Nationality");
				//Thread.sleep(3000);
				click(By.xpath("//div[text()='"+nationality.trim()+"']"), "Natioanlity");
				//selectValueFromDropDown(By.xpath(BookingPageLocators.selectNation.replace("#", String.valueOf(count))), "Nationality", nationality);
				
				
				if(domOrInt.equalsIgnoreCase("Domestic")){
					assertText(By.xpath(BookingPageLocators.travelDoc.replace("#", String.valueOf(count))), " الهوية الوطنية ");
				}
				else{
					assertText(By.xpath(BookingPageLocators.travelDoc.replace("#", String.valueOf(count))), " جواز سفر ");
				}
				//click(BookingPageLocators.travelDoc,"Travel Document");
				//click(By.xpath("//div[text()='"+travelDoc+"']"), "Travel Document");
				// - no need selectValueFromDropDown(BookingPageLocators.selecttravelDoc, "Travel Document", travelDoc);
				//type(BookingPageLocators.inputDoc, docNum, "Document Number");
				
				if(travelDoc.equalsIgnoreCase("الهوية الوطنية"))
				{
					click(By.xpath(BookingPageLocators.travelDoc.replace("#", String.valueOf(count))),"Travel Document");
					click(By.xpath("//div[text()='"+travelDoc+"']"), "Travel Document");
					type(By.xpath(BookingPageLocators.inputDoc.replace("#", String.valueOf(count))), docNum, "Document Number");
				}
				else if (travelDoc.equalsIgnoreCase("Passport"))
				
				{	
					
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.ppNumber.replace("#", String.valueOf(count))), "Passport text box");
				type(By.xpath(BookingPageLocators.ppNumber.replace("#", String.valueOf(count))), randomString(), "Passport Number");
				
				click(By.xpath(BookingPageLocators.ppExpDD.replace("#", String.valueOf(count))), "DD");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.ppSelectDD.replace("#", String.valueOf(count))+randomNumericString()+"]"), "DD");	
				
				click(By.xpath(BookingPageLocators.ppExpMM.replace("#", String.valueOf(count))), "MM");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.ppSelectDD.replace("#", String.valueOf(count))+randomNumericString()+"]"), "MM");
				
				click(By.xpath(BookingPageLocators.ppExpYY.replace("#", String.valueOf(count))), "YYYY");
			//	Thread.sleep(3000);
			//	click(By.xpath(BookingPageLocators.ppSelectDD.replace("#", String.valueOf(count))+randomNumber(min,max)+"]"), "YYYY");
			//	click(By.xpath(BookingPageLocators.ppSelectDD+randomNumber(min,max)+"]"), "YYYY");
				
				executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//ul/li/descendant::div[@class='ui-select-choices-row']["+randomNumber(min,max)+"]")));
				}
			
				if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").equalsIgnoreCase("Infant 1"))
				{
					System.out.println("No Smily for  "+ getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title"));
					
				}
				else
				{
						if(!naSmiles.equalsIgnoreCase("") && !naSmiles.equalsIgnoreCase("Value"))
						type(By.xpath(BookingPageLocators.naSmiles.replace("#", String.valueOf(count))), naSmiles, "na Smiles");
				}
			
			}
			type(BookingPageLocators.mobileNum, mobileNum, "Mobile Number");
			type(BookingPageLocators.emailAdd, emailId, "Email Address");
			click(BookingPageLocators.continueBtn, "Continue");
			 FirstLastName = new String[2];
			 FirstLastName[0] =firstname;
			 FirstLastName[1] =lastname;
			 return FirstLastName;
		}catch(Exception e){
			e.printStackTrace();
			return FirstLastName;
		}
		
}
	public void payment_Production_Arabic(String paymentType) throws Throwable
	{
		System.out.println(paymentType);
		waitforElement(BookingPageLocators.paymentTittle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		List<WebElement> paymentss = driver.findElements(BookingPageLocators.paymentType);
		for(int i=0;i<paymentss.size();i++)
		{
			if(paymentss.get(i).getText().contains("سداد"))
			{
				paymentss.get(i).click();					
				break;

			}
			}
		/*Thread.sleep(2000);
		driver.findElement(BookingPageLocators.ccCheckbox).click();*/
		Thread.sleep(2000);
		/*JavascriptExecutor executer = (JavascriptExecutor)driver;
		executer.executeScript("arguments[0].click();", BookingPageLocators.continueBtn);*/
		click(BookingPageLocators.continueBtn, "Continue");
	}
	public boolean selectClass_Arabic(String bookingClass, String tripType) throws Throwable{
		Thread.sleep(3000);
		boolean flag=false;
		List<WebElement> current = driver.findElements(By.xpath("//li[@class='current']"));
		for(int j=0;j<current.size();j++)
		{
			String flightstatus=current.get(j).findElement(By.xpath("a/span[2]")).getText();
		//	String flightstatus = driver.findElement(By.xpath("//li[@class='current']/a/span[2]")).getText();
			if(flightstatus.equalsIgnoreCase("No flights"))
			{
				for(int i=1;i<=10;i++)
				{
					String status=current.get(j).findElement(By.xpath("following-sibling::li["+i+"]/a/span[2]")).getText();
				//	String status=driver.findElement(By.xpath("//li[@class='current']/following-sibling::li["+i+"]/a/span[2]")).getText();
					if(!status.equalsIgnoreCase("No flights"))
					{
						current.get(j).findElement(By.xpath("following-sibling::li["+i+"]")).click();
						Thread.sleep(3000);
						break;
						
						/*int count = verifingFlightsAvailablity();
						if(count==1)
						{
						Thread.sleep(3000);
						break;
						}*/
					}
					else
					{
						System.out.println("NO Flights");
						//continue;
					}
				}
			}
		}
		if(bookingClass.equalsIgnoreCase("اقتصاد")){
			click(BookingPageLocators.economyOW,"Economy");
		} else if(bookingClass.equalsIgnoreCase("ثني")){
			click(BookingPageLocators.flexOW,"Flex");
		} else if(bookingClass.equalsIgnoreCase("عمل")){
			click(BookingPageLocators.busOW,"Business");
			
		}
		if(!tripType.equalsIgnoreCase("One Way")){
			List<WebElement> ele = driver.findElements(BookingPageLocators.rtClass);
			if(bookingClass.equalsIgnoreCase("اقتصاد")){
				/*BookingPageLocators.rtClassStr = BookingPageLocators.rtClassStr + "[" + (ele.size()-1) + "]/td[5]";
				click(By.xpath(rtClassStr),"Economy");*/
				List<WebElement> rtClasBusines = driver.findElements(By.xpath("//div[@class='main_con']/div/div/div[2]/div/flight-select/div/form/div[2]//descendant::table/tbody/tr/td[5]"));
				for(int i=0;i<rtClasBusines.size();i++)
				{
					if(rtClasBusines.get(i).findElement(By.tagName("div")).getText().equalsIgnoreCase("Sold out"))
					{
						System.out.println("Sold Out");
					}
					else
					{
						rtClasBusines.get(i).click();
						break;
					}
					
				}
			} else if(bookingClass.equalsIgnoreCase("ثني")){
				/*BookingPageLocators.rtClassStr = BookingPageLocators.rtClassStr + "[" + (ele.size()-1) + "]/td[6]";
				click(By.xpath(rtClassStr),"Flex");*/
				List<WebElement> rtClasBusines = driver.findElements(By.xpath("//div[@class='main_con']/div/div/div[2]/div/flight-select/div/form/div[2]//descendant::table/tbody/tr/td[6]"));
				for(int i=0;i<rtClasBusines.size();i++)
				{
					if(rtClasBusines.get(i).findElement(By.tagName("div")).getText().equalsIgnoreCase("Sold out"))
					{
						System.out.println("Sold Out");
					}
					else
					{
						rtClasBusines.get(i).click();
						break;
					}
					
				}
			} else if(bookingClass.equalsIgnoreCase("عمل")){
				/*BookingPageLocators.rtClassStr = BookingPageLocators.rtClassStr + "[" + (ele.size()-1) + "]/td[7]";
				click(By.xpath(rtClassStr),"Business");*/
				List<WebElement> rtClasBusines = driver.findElements(By.xpath("//div[@class='main_con']/div/div/div[2]/div/flight-select/div/form/div[2]//descendant::table/tbody/tr/td[7]"));
				for(int i=0;i<rtClasBusines.size();i++)
				{
					if(rtClasBusines.get(i).findElement(By.tagName("div")).getText().equalsIgnoreCase("Sold out"))
					{
						System.out.println("Sold Out");
					}
					else
					{
						rtClasBusines.get(i).click();
						break;
					}
					
				}
				
			}
			
		}
		
		click(BookingPageLocators.continueBtn, "Continue");
		return true;
	}
	public void verifingStatusSadad_Arabic() throws Throwable
	{
		if(getText(BookingPageLocators.summaryStatus_AR, "PNR Status").trim().equalsIgnoreCase("Pending"))
		{
			System.out.println(getText(BookingPageLocators.summaryStatus_AR, "PNR Status"));
			Reporter.SuccessReport("PNR Status", "Pending");
		}
		else
		{
			Reporter.SuccessReport("PNR Status", "Not Pending");
		}
	}

	public void verifyPNRforSadad_Arabic() throws Throwable{
		if(isElementPresent(BookingPageLocators.summaryRefNumber_AR, "Booking Reference")){
			Reporter.SuccessReport("Ticket Confirmation", "Ticket has booked,PNR : " + 
		driver.findElement(By.xpath("//div[text()=' رقم الحجز: ']/b")).getText());
			System.out.println(driver.findElement(By.xpath("//div[text()=' رقم الحجز: ']/b")).getText());
		}
		else
		{
			Reporter.SuccessReport("Ticket Confiramation", "Ticket has not booked");
		}	
	}
	public void verifingChildDiscount_Arabic(String bookingClass) throws Throwable
	{
		
		List<WebElement> collapseButton = driver.findElements(BookingPageLocators.collapsebtn(bookingClass));
		for(int i=0;i<collapseButton.size();i++)
		{		
			collapseButton.get(i).click();
			Thread.sleep(3000);
			List<WebElement> childamt = driver.findElements(BookingPageLocators.childamt_AR);
			String amount = childamt.get(i).getText();
			String[] Amount = amount.split("\\s");
			String[] childamount = Amount[1].split("\\.");
			int avg=0;
			if(childamount[0].contains(","))
			{
				String childFare = childamount[0].replaceAll("\\,", "");
				int fare = Integer.parseInt(childFare);
				avg = (fare*25)/100;
			}				
			else
			{
				int fare = Integer.parseInt(childamount[0]);
				avg = (fare*25)/100;
			}
		
			if(getText(BookingPageLocators.discount_AR, "Discount").contains(Integer.toString(avg)))
			{
				System.out.println("Successfully Verified Discount");
				Reporter.SuccessReport("Verifing Child Discount of 25%", "Discount Successfully Applied");
			}
		}
	}
	public void verifingChildDiscount_Tarkish(String bookingClass) throws Throwable
	{
		
		List<WebElement> collapseButton = driver.findElements(BookingPageLocators.collapsebtn(bookingClass));
		for(int i=0;i<collapseButton.size();i++)
		{		
			collapseButton.get(i).click();
			Thread.sleep(3000);
			List<WebElement> childamt = driver.findElements(BookingPageLocators.childamt_TR);
			String amount = childamt.get(i).getText();
			String[] Amount = amount.split("\\s");
			String[] childamount = Amount[1].split("\\.");
			int avg=0;
			if(childamount[0].contains(","))
			{
				String childFare = childamount[0].replaceAll("\\,", "");
				int fare = Integer.parseInt(childFare);
				avg = (fare*25)/100;
			}				
			else
			{
				int fare = Integer.parseInt(childamount[0]);
				avg = (fare*25)/100;
			}
		
			if(getText(BookingPageLocators.discount_TR, "Discount").contains(Integer.toString(avg)))
			{
				System.out.println("Successfully Verified Discount");
				Reporter.SuccessReport("Verifing Child Discount of 25%", "Discount Successfully Applied");
			}
		}
	}
	
	//Production Tarkish language
	
	public boolean inputBookingDetails_Tarkish(String tripType, String origin, String dest, String deptDate,
			String origin2, String departure2, String retDate, String adults, String child, String infant, String promo,String Currency) throws Throwable
	{
		waitforElement(BookingPageLocators.oneWay_pdctn_TR);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		if(tripType.equalsIgnoreCase("Round Trip")){
			click(BookingPageLocators.roundTrip_pdctn_TR, "Round Trip");
		} else if(tripType.equalsIgnoreCase("One Way")){
			click(BookingPageLocators.oneWay_pdctn_TR, "One Way");
		} else if(tripType.equalsIgnoreCase("Multi City")){
			click(BookingPageLocators.multiCity_pdctn_TR, "Multi City");
		}
		click(BookingPageLocators.origin, "Origin");
		selectCity(BookingPageLocators.selectOrigin, "Origin", origin);
		click(BookingPageLocators.dest, "Destination");
		selectCity(BookingPageLocators.selectDest, "Destination", dest);
		/*click(BookingPageLocators.dest, "Destination");
		selectCity(BookingPageLocators.selectDest, "Destination", dest);*/
		click(BookingPageLocators.dpDate_pdctn_TR,"Departure Date");
		selectDate(BookingPageLocators.selectDate,"Departure Date",deptDate);
		
		if(tripType.equalsIgnoreCase("Round Trip")){
		//	click(BookingPageLocators.rtDate,"Return Date");
			selectDate(BookingPageLocators.selectDate,"Return Date",retDate);
		}
		if(tripType.equalsIgnoreCase("Multi City")){
			click(BookingPageLocators.secOrigin, "Origin");
			selectCity(BookingPageLocators.selectOrigin, "Origin", origin2);
			selectCity(BookingPageLocators.selectDest, "Destination", departure2);
			click(BookingPageLocators.rtnDate_pdctn_TR,"Return Date");
			selectDate(BookingPageLocators.selectDate,"Return Date",retDate);
		}
		if(Integer.valueOf(adults)>1){
			click(BookingPageLocators.adult, "Adult");
			System.out.println("Adults: "+adults);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[text()='"+adults+"']")));
			//driver.findElement(By.xpath("//div[text()='"+adults+"']")).click();
			//selectValueFromDropDown(BookingPageLocators.selectAdult, "Adult", adults);
		}
		if(Integer.valueOf(child)>=1){
			click(BookingPageLocators.child_pdctn_TR, "Child");
			driver.findElement(By.xpath("//div[text()='"+child+"']")).click();
			//selectValueFromDropDown(BookingPageLocators.selectChild, "Child", child);
		}
		if(Integer.valueOf(infant)>=1){
			click(BookingPageLocators.infant, "Infant");
			driver.findElement(By.xpath("//div[text()='"+child+"']")).click();
			//selectValueFromDropDown(BookingPageLocators.selectInfant, "Infant", infant);
		}
		if(!promo.equalsIgnoreCase("")){
			type(BookingPageLocators.promo, promo, "Promo");
		}
		click(BookingPageLocators.findFlights_pdctn_TR,"Find Flights");
		return true;
	}
	public String[] inputPassengerDetails_Tarkish(String domOrInt, String totalPass, String nationality, 
			String travelDoc, String docNum, String naSmiles, String mobileNum, String emailId,String fname,String lname,String payment2) 
					throws Throwable{
		waitforElement(By.xpath(BookingPageLocators.title.replace("#", String.valueOf(1))));
		waitUtilElementhasAttribute(BookingPageLocators.body);
		String lastname = null,firstname=null;
		String[] FirstLastName = null;
		Integer min=0,max=0;
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		try{
			for(Integer count = 1; count<=Integer.valueOf(totalPass); count++){
				if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").contains("Yetişkin")){
					min = 14; max = 19;
				} else if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").contains("Çocuk")){
					min = 5; max = 9;
				} else if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").contains("Infant")){
					min = 1; max = 2;
				}
				click(By.xpath(BookingPageLocators.title.replace("#", String.valueOf(count))),"Title");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.titleSelect.replace("#", String.valueOf(count))), "Title");
			
				if(payment2.equalsIgnoreCase("Nas"))
				{
					type(By.xpath(BookingPageLocators.fName.replace("#", String.valueOf(count))), fname, "First Name");

				}
				else
				{
				//Thread.sleep(1000);
				firstname = randomString();
				type(By.xpath(BookingPageLocators.fName.replace("#", String.valueOf(count))), firstname, "First Name");
				}
				if(payment2.equalsIgnoreCase("Nas"))
				{
					type(By.xpath(BookingPageLocators.lName.replace("#", String.valueOf(count))), lname, "Last Name");

				}
				else
				{
				lastname = randomString();
				type(By.xpath(BookingPageLocators.lName.replace("#", String.valueOf(count))), lastname, "Last Name");
				}
				
				click(By.xpath(BookingPageLocators.dd.replace("#", String.valueOf(count))), "DD");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.selectDD.replace("#", String.valueOf(count))+randomNumericString()+"]"), "DD");
				
				click(By.xpath(BookingPageLocators.mm.replace("#", String.valueOf(count))), "MM");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.selectMM.replace("#", String.valueOf(count))+randomNumericString()+"]"), "MM");
				
				click(By.xpath(BookingPageLocators.yyyy.replace("#", String.valueOf(count))), "YYYY");
				//Thread.sleep(3000);
				if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").contains("Infant"))
				{
					List<WebElement> year =driver.findElements(By.xpath(BookingPageLocators.selectyyinfant.replace("#", String.valueOf(count))));
					year.get(1).click();
				}
				else
				{
					executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@class='pass_tab']/div["+count+"]//descendant::div[@class='dob_conyear']/div/ul/li//descendant::div[@class='ui-select-choices-row']["+randomNumber(min,max)+"]")));
					//click(By.xpath(BookingPageLocators.selectYYYY.replace("#", String.valueOf(count))+randomNumber(min,max)+"]"), "YYYY");
				}
				
				click(By.xpath(BookingPageLocators.nation.replace("#", String.valueOf(count))), "Nationality");
				//Thread.sleep(3000);
				click(By.xpath("//div[text()='"+nationality.trim()+"']"), "Natioanlity");
				//selectValueFromDropDown(By.xpath(BookingPageLocators.selectNation.replace("#", String.valueOf(count))), "Nationality", nationality);
				
				
				if(domOrInt.equalsIgnoreCase("Domestic")){
					assertText(By.xpath(BookingPageLocators.travelDoc.replace("#", String.valueOf(count))), "Kimlik Kartı");
				}
				else{
					assertText(By.xpath(BookingPageLocators.travelDoc.replace("#", String.valueOf(count))), " Pasaport ");
				}
				//click(BookingPageLocators.travelDoc,"Travel Document");
				//click(By.xpath("//div[text()='"+travelDoc+"']"), "Travel Document");
				// - no need selectValueFromDropDown(BookingPageLocators.selecttravelDoc, "Travel Document", travelDoc);
				//type(BookingPageLocators.inputDoc, docNum, "Document Number");
				
				if(travelDoc.trim().equalsIgnoreCase("Kimlik Kartı"))
				{
					click(By.xpath(BookingPageLocators.travelDoc.replace("#", String.valueOf(count))),"Travel Document");
					click(By.xpath("//div[text()='"+travelDoc.trim()+"']"), "Travel Document");
					type(By.xpath(BookingPageLocators.inputDoc.replace("#", String.valueOf(count))), docNum, "Document Number");
				}
				else if (travelDoc.equalsIgnoreCase("Passport"))
				
				{	
					
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.ppNumber.replace("#", String.valueOf(count))), "Passport text box");
				type(By.xpath(BookingPageLocators.ppNumber.replace("#", String.valueOf(count))), randomString(), "Passport Number");
				
				click(By.xpath(BookingPageLocators.ppExpDD.replace("#", String.valueOf(count))), "DD");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.ppSelectDD.replace("#", String.valueOf(count))+randomNumericString()+"]"), "DD");	
				
				click(By.xpath(BookingPageLocators.ppExpMM.replace("#", String.valueOf(count))), "MM");
				//Thread.sleep(3000);
				click(By.xpath(BookingPageLocators.ppSelectDD.replace("#", String.valueOf(count))+randomNumericString()+"]"), "MM");
				
				click(By.xpath(BookingPageLocators.ppExpYY.replace("#", String.valueOf(count))), "YYYY");
			//	Thread.sleep(3000);
			//	click(By.xpath(BookingPageLocators.ppSelectDD.replace("#", String.valueOf(count))+randomNumber(min,max)+"]"), "YYYY");
			//	click(By.xpath(BookingPageLocators.ppSelectDD+randomNumber(min,max)+"]"), "YYYY");
				
				executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//ul/li/descendant::div[@class='ui-select-choices-row']["+randomNumber(min,max)+"]")));
				}
			
				if(getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title").equalsIgnoreCase("Infant 1"))
				{
					System.out.println("No Smily for  "+ getText(By.xpath(BookingPageLocators.passType.replace("#", String.valueOf(count))), "Passenger Title"));
					
				}
				else
				{
						if(!naSmiles.equalsIgnoreCase("") && !naSmiles.equalsIgnoreCase("Value"))
						type(By.xpath(BookingPageLocators.naSmiles.replace("#", String.valueOf(count))), naSmiles, "na Smiles");
				}
			
			}
			type(BookingPageLocators.mobileNum, mobileNum, "Mobile Number");
			type(BookingPageLocators.emailAdd, emailId, "Email Address");
			click(BookingPageLocators.continueBtn, "Continue");
			 FirstLastName = new String[2];
			 FirstLastName[0] =firstname;
			 FirstLastName[1] =lastname;
			 return FirstLastName;
		}catch(Exception e){
			e.printStackTrace();
			return FirstLastName;
		}
}
	public void verifingStatusSadad_Tarkish() throws Throwable
	{
		if(getText(BookingPageLocators.summaryStatus_TR, "PNR Status").trim().equalsIgnoreCase("Pending"))
		{
			System.out.println(getText(BookingPageLocators.summaryStatus_TR, "PNR Status"));
			Reporter.SuccessReport("PNR Status", "Pending");
		}
		else
		{
			Reporter.SuccessReport("PNR Status", "Not Pending");
		}
	}

	public void verifyPNRforSadad_Tarkish() throws Throwable{
		if(isElementPresent(BookingPageLocators.summaryRefNumber_TR, "Booking Reference")){
			Reporter.SuccessReport("Ticket Confirmation", "Ticket has booked,PNR : " + 
		driver.findElement(By.xpath("//div[text()='Reservasyon: ']/b")).getText());
			System.out.println(driver.findElement(By.xpath("//div[text()='Reservasyon: ']/b")).getText());
		}
		else
		{
			Reporter.SuccessReport("Ticket Confiramation", "Ticket has not booked");
		}	
	}
	public void verifingMemberRegistration_Production() throws Throwable
	{
		if(isElementPresent(BookingPageLocators.memberRegistrationConf)==true)
		{
			Reporter.SuccessReport("Verifing Member Registratin", "Member is Successfully Registered");
		}
		else
		{
			Reporter.failureReport("Verifing Member Registratin", "Member is not Successfully Registered");
		}
	}
	public void verifingMemberUpdates_Production() throws Throwable
	{
		waitforElement(BookingPageLocators.memberUpdateConf);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		if(isElementPresent(BookingPageLocators.memberUpdateConf)==true)
		{
			Reporter.SuccessReport("Verifing Member Update", "Member is Successfully Updated");
		}
		else
		{
			Reporter.failureReport("Verifing Member Update", "Member is not Successfully Updated");
		}
	}
	public void validating_BaggageWeights() throws Throwable
	{
		waitforElement(BookingPageLocators.baggagetittle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		int count =0;
		List<WebElement> addBaggage = driver.findElements(By.xpath("//div[@class='baggage_box']/div[2]/a[2]/i"));
		for(int  i=0;i<addBaggage.size()-1;i++)
		{
			addBaggage.get(i).click();
			Thread.sleep(1000);
			count++;
		}
		System.out.println(addBaggage.get(count));
		if(addBaggage.get(count).getAttribute("class").contains("disable"))
		{			
			Reporter.SuccessReport("Validating Excess Baggage Adding", "No Excess Baggage Added");
		}
		else
		{
			Reporter.failureReport("Validating Excess Baggaeg Adding", "Excess Baggage Added");
		}
		
	}
	public void selectPassenger() throws Throwable
	{
		waitforElement(BookingPageLocators.passengerDetailsTittle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		List<WebElement> selPasngr = driver.findElements(BookingPageLocators.selectPassenger);
		selPasngr.get(1).click();
		click(BookingPageLocators.memberDropdown, "Family Member Dropdown");
		List<WebElement> familyMembrs = driver.findElements(BookingPageLocators.familyMembers);
		familyMembrs.get(1).click();
		click(BookingPageLocators.seletcBtn, "Select");
	}
	public void selectallSeatstoremove(String seatSelect,String totalpass,String triptype) throws Throwable {
		waitforElement(BookingPageLocators.selectseattittle);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		List<WebElement> Flights = driver.findElements(By.xpath("//ul[@class='nav nav-tabs']/li"));
		List<WebElement> ele = driver.findElements((By.xpath("//div[contains(@class, 'seatpassrow')]"))); 
		if(ele.size()==0)
		{
			click(BookingPageLocators.continueBtn, "Continue");
		}
		else
		{
			int k;
			List<WebElement> extlegseats = driver.findElements(BookingPageLocators.selExLegSeat);
			List<WebElement> busiseats = driver.findElements(BookingPageLocators.selbusSeat);
			int loop=0;
			for(int j=0;j<Flights.size();j++){
				for(int i=0;i<Integer.parseInt(totalpass);i++){
					if(seatSelect.equalsIgnoreCase("Extra Leg Room")){
						if(Flights.get(j).getAttribute("class").contains("active")){
							for(k=loop;k<extlegseats.size();k++)
							{
								if(extlegseats.get(k).isDisplayed()==true){
									extlegseats.get(k).click();
									Reporter.SuccessReport("Click", "Successfully Click on Extra Leg Room ");
									if(isElementDisplayedTemp(BookingPageLocators.ok)){
										click(BookingPageLocators.ok, "OK");
										waitUtilElementhasAttribute(BookingPageLocators.body);
										
									}	
									loop=loop+1;
									break;
								}else{
									System.out.println("Seat was Already Selected");
									loop=loop+1;
									
								}
																	
								}
							/*if(i==Integer.parseInt(totalpass)-1){
									break;
							}*/	
							
								
							//continue;	
						}
						
					}
					if(seatSelect.equalsIgnoreCase("Business")){
						if(Flights.get(j).getAttribute("class").contains("active")){
							for(k=loop;k<busiseats.size();k++)
							{
								if(busiseats.get(k).isDisplayed()==true){
									busiseats.get(k).click();
									Reporter.SuccessReport("Click", "Successfully Click on Business");
									if(isElementDisplayedTemp(BookingPageLocators.ok)){
										click(BookingPageLocators.ok, "OK");
										
									}	
									loop=loop+1;
									break;
								}else{
									System.out.println("Seat was Already Selected");
									loop=loop+1;
									
								}
																	
								}
							/*if(i==Integer.parseInt(totalpass)-1){
									break;
							}*/	
							
								
							//continue;	
						}
						
				}
				
			}
			
		}
		
			waitUtilElementhasAttribute(BookingPageLocators.body);
			removeallseats(seatSelect, totalpass, triptype);			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(BookingPageLocators.continueBtn));
			click(BookingPageLocators.continueBtn, "Continue");
			if(isElementDisplayedTemp(By.xpath("//button[text()='OK']"))==true){
			click(By.xpath("//button[text()='OK']"), "OK Button");
			}
			else{
			System.out.println("No alert Present");
			}
		}
	}
	public void selectClassWithConnectionFlight(String bookingClass, String tripType) throws Throwable{
		waitforElement(BookingPageLocators.selectflightsection);
		waitUtilElementhasAttribute(BookingPageLocators.body);
		boolean flag=false;
		int count=0;
		List<WebElement> flighttables = driver.findElements(By.xpath("//table[@class='table flight_table']"));
			for(int i=0;i<flighttables.size();i++)
			{
				
					List<WebElement> Flights_row = flighttables.get(i).findElements(By.tagName("tr"));
					System.out.println("ROWS COUNT"+ Flights_row.size());
					for(int j=1;j<Flights_row.size();j++)
					{ 
						count=0;
						List<WebElement> Flights_td = Flights_row.get(j).findElements(By.tagName("td"));
						System.out.println("COL COUNT"+ Flights_td.size());
						
						String stop =  Flights_row.get(j).findElement(By.xpath("td[2]/div/div/span")).getText();
						if(stop.contains("1 Stop")){
							if(bookingClass.equalsIgnoreCase("Economy")){
								if((Flights_td.get(4).findElement(By.tagName("div")).getText().equalsIgnoreCase("Sold out"))){
									System.out.println("Sold out");
								}else{
									Flights_td.get(4).findElement(By.tagName("button")).click();
									Thread.sleep(2000);
									flag=true;
									break;
									
								}
							}
						}
					
						}		
	}
			waitUtilElementhasAttribute(BookingPageLocators.body);
			click(BookingPageLocators.continueBtn, "Continue");
			
}
	//Android Chrome
	
}
//}

