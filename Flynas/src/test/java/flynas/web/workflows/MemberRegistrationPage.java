package flynas.web.workflows;

import java.util.Random;

import org.openqa.selenium.By;

import com.ctaf.utilities.Reporter;

import flynas.web.testObjects.BookingPageLocators;

public class MemberRegistrationPage<RenderedWebElement> extends BookingPageLocators{
	
	public void waitforpageload() throws InterruptedException{	
		waitUtilElementhasAttribute(BookingPageLocators.body);
		}
	
	public String memberRegistration() throws Throwable
	{
		
		String  username = randomString(8);
		String  email = username+"@cigniti.com";
		final String[] Doctype = {"Passport", "National ID Card", "Iqama"};
		Random random = new Random();
		int index = random.nextInt(Doctype.length);
		String doctyp = Doctype[index];
		 
		type(BookingPageLocators.userEmail, email, "UserEmail");
		type(BookingPageLocators.pasword_reg, "Test@1234", "Password");
		type(BookingPageLocators.cnfmpasword_reg, "Test@1234", "Conform Password");
		click(BookingPageLocators.title_reg, "Title");
		click(By.xpath(BookingPageLocators.selecttitle_reg.replace("#", "1")), "Title");
		type(BookingPageLocators.fname_reg, randomString(8), "Firstname");
		type(BookingPageLocators.lname_reg, randomString(5), "Last Name");
		click(BookingPageLocators.dd_reg, "Date");
		click(By.xpath(BookingPageLocators.selectdd_reg+randomNumericString()+"]"), "Date");
		click(BookingPageLocators.mm_reg, "Month");
		click(By.xpath(BookingPageLocators.selectmm_reg+randomNumericString()+"]"), "Month");
		click(BookingPageLocators.yy_reg, "year");
		click(By.xpath(BookingPageLocators.selectyy_reg+randomNumber(12, 19)+"]"), "Year");
		click(BookingPageLocators.natinality_reg, "Nationality");
		click(BookingPageLocators.selectnatinality_reg("Saudi Arabia"), "Nationality");
		click(BookingPageLocators.doctype_reg, "Document Type");
		click(BookingPageLocators.selectdoctype_reg(doctyp), "Document Type");
		type(BookingPageLocators.docnumber_reg, username, "Document Number");
		click(BookingPageLocators.expdd, "Document Expiry Date");
		click(BookingPageLocators.selectDocExpdd, "Expiry Date");
		click(BookingPageLocators.expmm, "Expiry Month");
		click(By.xpath(BookingPageLocators.ppSelectDD+randomNumericString()+"]"), "Expiry Month");
		click(BookingPageLocators.expyy, "Expiry year");
		click(By.xpath(BookingPageLocators.ppSelectDD+randomNumber(10,15)+"]"), "Expiry Year");
		type(BookingPageLocators.mobileNum, "534786324", "MobileNumber");
		type(BookingPageLocators.emailAdd, email, "Email Address");
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

}
