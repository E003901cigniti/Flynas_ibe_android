package com.ctaf.googledrive;

import java.io.IOException;

public class TestGoogle {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int row = GoogleDriveAPI.fnGetRowNo("IBE_UAT_Reg", "TC03_a_oneWayDomesticChangeDate");
		GoogleDriveAPI.getResponse("IBE_UAT_Reg", "B"+row, "B"+row);
		GoogleDriveAPI.setValue("IBE_UAT_Reg", "B"+row, "B"+row, "pass");
		
	}
	
	

}
