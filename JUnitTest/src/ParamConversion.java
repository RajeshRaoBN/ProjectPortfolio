
//package com.resftful.FPDS_Restful;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class ParamConversion {
	
	public static void log(String strMsg) {
		System.out.println(strMsg);
	}

	
	private static String getFirstDayOfQuarter() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    return  dateFormat.format(cal.getTime());
	}

	private static String getFirstDayOfNextQuarter() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3 +3);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    return dateFormat.format(cal.getTime()); 
	}

	private static String getLastDayOfQuarter() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3 + 2);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	    return dateFormat.format(cal.getTime());
	}

	private static String getLastDayOfNextQuarter() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3 + 5);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	    return dateFormat.format(cal.getTime());
	}

	private static String getFirstDayOfCurrentFiscalYear() {
		Calendar cal = Calendar.getInstance();
		java.util.Date date= new Date();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		log("Calendar month: " + month);
		if(month<=8) {//sepetmber is month 8
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-1);
			log("Calendar year: " + cal.get(Calendar.YEAR));
		}	
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
	}

	private static String getLastDayOfCurrentFiscalYear() {
		Calendar cal = Calendar.getInstance();
		java.util.Date date= new Date();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		log("Calendar month: " + month);
		if(month>8) {//sepetmber is month 8
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)+1);
			log("Calendar year: " + cal.get(Calendar.YEAR));
		}
		cal.set(Calendar.MONTH, 9);
		cal.set(Calendar.DAY_OF_MONTH, 30);
		return new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
	}

	private static String getFirstDayOfNextFiscalYear() {
		Calendar cal = Calendar.getInstance();
		java.util.Date date= new Date();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		log("Calendar month: " + month);
		if(month>8) {//sepetmber is month 8
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)+1);
			log("Calendar year: " + cal.get(Calendar.YEAR));
		}
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
	}

	private static String getLastDayOfNextFiscalYear() {
		Calendar cal = Calendar.getInstance();
		java.util.Date date= new Date();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		log("Calendar month: " + month);
		if(month>8) {//sepetmber is month 8
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)+2);
			log("Calendar year: " + cal.get(Calendar.YEAR));
		}
		cal.set(Calendar.MONTH, 9);
		cal.set(Calendar.DAY_OF_MONTH, 30);
		return new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
	}

	private static String getdateAfter(int months) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, months); // previous 10 months
	    return new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
	}

	private static String getCurrentDate() {
	    return new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
	}

	public static HashMap<String, String> Process(HashMap<String,String> newInput) throws InterruptedException {
		
		HashMap<String, String> output= newInput;
		String result = "success";
	
		try {
		
			// just changing the name of variable in the output
			output.put("CanIncumbentRecompete", newInput.get("Recompete"));		
			String download_location= System.getenv("download_location");

			if(newInput.get("NAICS")!= null) {
				log("Provided NAICS: "+ newInput.get("NAICS"));
				String valueNaics = newInput.get("NAICS").split(" ")[0];
				log("After split NAICS: "+valueNaics);
				if(valueNaics!= null) {	
					if(valueNaics.trim()!= null) {
						valueNaics=valueNaics.trim();
						if(valueNaics.length()==6) {
							if(Integer.parseInt(valueNaics)>99999 && Integer.parseInt(valueNaics)<1000000)  {
								output.put("NAICSCode", valueNaics);
							}
						}else { output.put("NAICSCode", "");}
					}
					else { 	output.put("NAICSCode", "");}
	
				}
				else { 	output.put("NAICSCode", "");}
			}
	
			else { 	output.put("NAICSCode", "");}
	
			//	Set Aside
			String setAsideValue = newInput.get("Type of Set Aside");
			if(setAsideValue!=null) {
				setAsideValue=setAsideValue.trim();
				output.put("TypeofSetAside", setAsideValue);
			}
	
			String valueCountry = newInput.get("Country");
			if(	valueCountry != null) {	
				if(valueCountry.trim() != null) {valueCountry=valueCountry.trim();
					if(valueCountry.toUpperCase().contains("ANY")) {
						output.put("Country", "");
					}
					else if(valueCountry.length()==3) {		
						output.put("Country", valueCountry);
					}
					else {output.put("Country", "");}
				}
				else {output.put("Country", "");}
			}
			else {output.put("Country", "");}
	
			// providing extracted contract values
			String 	minDateValue=getCurrentDate();
			String 	maxDateValue=getdateAfter(12);
			String	valuePE = newInput.get("Period Expiring");
			if(valuePE!=null) {
				log("Period Expiring: "+valuePE);	
				switch (valuePE) {
				case "Current Quarter":
					minDateValue=getFirstDayOfQuarter();
					maxDateValue=getLastDayOfQuarter();
					break;
	
				case "Next Quarter":
					minDateValue=getFirstDayOfNextQuarter();
					maxDateValue=getLastDayOfNextQuarter();
					break;
	
				case "Next Six Months":
					minDateValue=getCurrentDate();
					maxDateValue=getdateAfter(6);
					break;
	
				case "Current Fiscal Year":
					minDateValue=getFirstDayOfCurrentFiscalYear();
					maxDateValue=getLastDayOfCurrentFiscalYear();
					break;
	
				case "Next Fiscal Year":
					minDateValue=getFirstDayOfNextFiscalYear();
					maxDateValue=getLastDayOfNextFiscalYear();
					break;
	
				case "Next Twelve Months":
					minDateValue=getCurrentDate();
					maxDateValue=getdateAfter(12);
					break;
					
				default: 
					minDateValue=getCurrentDate();
					maxDateValue=getdateAfter(12);
					break;
				}
			}
			
			/* Not passing the Min Est Ultimate Completion Date or Max Est Ultimate Completion Date to FPDS 
				 These 2 dates are returned to Rest response for using them as filter in SP - GetExpiringSetAsideContracts
			 */
			output.put("MinEstUltimateCompletionDate",minDateValue);
			output.put("MaxEstUltimateCompletionDate",maxDateValue);

			String	valueAgency = newInput.get("Agency");  //converting into update case as it is required by FPDS
			if(	valueAgency!=null) {
				if(valueAgency.trim()!=null && valueAgency.length()>3) {valueAgency=valueAgency.trim();
					if(valueAgency.toUpperCase().contains("ANY") ) {
						output.put("Agency", "");
					}
					else {
						output.put("Agency", valueAgency);// it is required as it is converted into upper case.
					}
				}
				else { 	output.put("Agency", "");
				}	
			}
			else { 	output.put("Agency", "");}
	
			String  valueCity = newInput.get("City");
			if(valueCity!=null) {	
				if(valueCity.trim()!=null  ) {	valueCity=valueCity.trim();
					if(valueCity.toUpperCase().contains("ANY") ) {	
						output.put("City", "");
					}
					else if(valueCity.length()>0) {
						output.put("City", valueCity);
					}
				}
			}
			
			String	valuePSC = newInput.get("PSC");
			if(	valuePSC !=null) {
				if(	valuePSC.trim() !=null) {valuePSC=valuePSC.trim();
					if(valuePSC.toUpperCase().contains("ANY") ) {	
						output.put("PSC", "");
					}
					else if(valuePSC.length()>0){
						 output.put("PSC", valuePSC);
					}
				}
			}
	
			String valueZip = newInput.get("Zip Code");
			if(valueZip!=null) {
				if(	valueZip.trim() !=null && valueZip.length()==5) {valueZip=valueZip.trim();
				if(valueZip.toUpperCase().contains("ANY")) {
					output.put("ZipCode", "");
				}
	
				else if(Integer.parseInt(valueZip)>9999 && Integer.parseInt(valueZip)<100000 ) {
					output.put("ZipCode", valueZip);
				}	
				}	else {output.put("ZipCode", "");}
	
			}else {	output.put("ZipCode", "");}
			
			String  valueST = newInput.get("State");
			if(	(valueST!=null )) {
				if(	valueST.trim() !=null) {
					valueST=valueST.trim();
					if(valueST.toUpperCase().contains("ANY")) {	
						output.put("State", "");
					}
				else if(valueST.length()==2 ) {
						output.put("State", valueST);
					}					
				}
				else {output.put("State", "");}
			}
			else {output.put("State", "");}	
	
			String valueToC = newInput.get("Type of Contract");   //not in use with current process
			if(valueToC != null) {
				if(	valueToC.trim() !=null) {
					valueToC=valueToC.trim();	
					if(valueToC.toUpperCase().contains("ANY")) {	
						output.put("TypeofContract", ""); 				
					} else {	
						output.put("TypeofContract", valueToC); 
					}
				}
			}
	
			String valueCOffice = newInput.get("Contracting Office");
			if(	(valueCOffice!=null )) {
				if(	(valueCOffice.trim()!=null && valueCOffice.length()>0 && (!valueCOffice.toUpperCase().contains("ANY")) )) {
					valueCOffice=valueCOffice.trim();
					output.put("ContractingOffice", valueCOffice); //this is used in SP and input form
				}
				else {output.put("ContractingOffice", ""); }
			}
			String valueFundingOffice = newInput.get("Funding Office");
			if(	(valueFundingOffice!=null )) {
				if(	(valueFundingOffice.trim()!=null && valueFundingOffice.length()>0 && (!valueFundingOffice.toUpperCase().contains("ANY")) )) {
					valueFundingOffice=valueFundingOffice.trim();
					output.put("FundingOffice", valueFundingOffice); //this is used in SP and input form
				}
				else {output.put("FundingOffice", "");}
			}
			else {output.put("FundingOffice", "");}
			
			String valueMajorCommand = newInput.get("Major Command");
			if(	(valueMajorCommand!=null)) {
				if(valueMajorCommand.trim()!=null && (!valueMajorCommand.toUpperCase().contains("ANY"))&& valueMajorCommand.length()>0) {
					valueMajorCommand=valueMajorCommand.trim();
					output.put("MajorCommand", valueMajorCommand); //this is used in SP and input form
				}
				else {output.put("MajorCommand", ""); }
			}
			else {output.put("MajorCommand", ""); }
			
			String valueSolProc = newInput.get("Solicitation Procedures");
			if(valueSolProc!=null ){
				if(valueSolProc.trim()!=null && (!valueSolProc.toUpperCase().contains("ANY"))&& valueSolProc.length()>0) {
					valueSolProc=valueSolProc.trim();
					output.put("SolicitationProcedures", valueSolProc); //this is used in SP and input form
				}
				else {output.put("SolicitationProcedures", "");}
			}
			else {output.put("SolicitationProcedures", "");}
			
			String valueOffers = newInput.get("Offers Received");
			if((valueOffers!=null) && valueOffers.length()>0 ) {
				if((valueOffers.trim()!=null) && !valueOffers.toUpperCase().contains("ANY")) {	
					if (valueOffers.trim().chars().allMatch( Character::isDigit )) {	
						output.put("OffersReceived", valueOffers); //this is used in SP and input form
					}else {output.put("OffersReceived", "");}
				}else {output.put("OffersReceived", "");}
			}else {output.put("OffersReceived", "");}
			
			// providing extracted contract values
			String minValue="", maxValue="";
			String valueCV = newInput.get("Contract Value");
			switch (valueCV) {
			case "Any amount":
				// not setting min and max values;
				break;        
	
			case "$0 to $3000":
				minValue="0";
				maxValue= "3000";
				break;        
	
			case "$3001 to $24999":
				minValue="3001";
				maxValue= "24999";
				break;        
	
			case "$25000 to $100000":
				minValue="25000";
				maxValue= "100000";
				break;        
	
			case "$100001 to $249999":
				minValue="100001";
				maxValue= "249999";
				break;
	
			case "Under Half Million":
				minValue="0";
				maxValue= "500000";
				break;
	
			case "Under 1 Million":
				minValue="0";
				maxValue= "999999";
				break;
	
			case "Under 4 Million":
				minValue="0";
				maxValue= "3999999";
				break;   
	
			case "Under 10 Million":
				minValue="0";
				maxValue= "9999999";
				break;  
	
			default: 
				// not setting any values;
				break;
			}
			if (!(minValue=="" && maxValue=="")) {
				output.put("MinUltimateContractValue", minValue);
				output.put("MaxUltimateContractValue", maxValue);
			}

		//	output.put("File Path", download_location);
		//	output.put("result", result);
			
			// adding Dates
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();			
			Calendar prevYear = cal;
			prevYear.add(Calendar.MONTH, -69); // previous 5 yrs and 9 months
			String startdate1 = dateFormat.format(prevYear.getTime());

			cal = Calendar.getInstance();
			Calendar prevYear1 = cal;
			prevYear1.add(Calendar.MONTH, -65); // previous 5 yrs and 5 months
			String startdate2 = dateFormat.format(prevYear1.getTime());

			cal = Calendar.getInstance();
			Calendar prevYear2 = cal;
			prevYear2.add(Calendar.MONTH, -10); // previous 10 months
			String enddate1 = dateFormat.format(prevYear2.getTime());

			cal = Calendar.getInstance();
			Calendar prevYear3 = cal;
			prevYear3.add(Calendar.MONTH, -6); // previous 10 months
			String enddate2 = dateFormat.format(prevYear3.getTime());
			
			output.put("startdate1", startdate1);
			output.put("enddate1", enddate1);
			output.put("startdate2", startdate2);
			output.put("enddate2", enddate2);
			
			String valueSS = newInput.get("Business Size Selection");  // not used in GUI
			output.put("Business Size Selection", valueSS);
			
			String valueDoR = newInput.get("Requirement Description"); // not used in GUI
			output.put("Requirement Description", valueDoR);
	
			return output;
	
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		//	output.put("result", result);
			return output;
	
		} 
	}
}