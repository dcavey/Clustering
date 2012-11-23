package utilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import structurer.Constants;

public  class  CheckModularityRules {

	public CheckModularityRules() {
		super();
	}

	// Allow that the owner can do anything to its data
	public  int CheckRule_OFA (String table, String program, int moduleType_T, int moduleType_P, String accessType)
	{	
		int result = Constants.MODULARITY_DECISION_AWAITED;
		
		if ( moduleType_T == moduleType_P) 
		{	result = Constants.MODULARITY_CHECK_POSITIVE;		
		} else
		{	result = Constants.MODULARITY_DECISION_AWAITED;			
		}
		logRuleCheckResult ("OFA", table, program, moduleType_T, moduleType_P, accessType, result);
		return result;
	}

	// Allow that a "Core Banking Package" program reads data from any other package 
	public  int CheckRule_CRA (String table, String program, int moduleType_T, int moduleType_P, String accessType)
	{
		int result = Constants.MODULARITY_DECISION_AWAITED;

		if ((moduleType_P == Constants.CORE_BANKING_PACKAGE) && (moduleType_T == Constants.ADD_ON_BUSINESS_PACKAGE)) 
		{	if ( accessType.equals(Constants.DB_READ) )
			{ result = Constants.MODULARITY_CHECK_POSITIVE;
			}
		}		
	
		logRuleCheckResult ("CRA", table, program, moduleType_T, moduleType_P, accessType, result);				
		return result;
	}
	
	// Allow that a "Business Package" program reads a table from a "Core Banking" package  
	public int CheckRule_ARC (String table, String program, int moduleType_T, int moduleType_P, String accessType)
	{
		int result = Constants.MODULARITY_DECISION_AWAITED;

		if ( (moduleType_P == Constants.ADD_ON_BUSINESS_PACKAGE) && (moduleType_T == Constants.CORE_BANKING_PACKAGE) ) 
		{	if (accessType.equals(Constants.DB_READ) )
			{ result = Constants.MODULARITY_CHECK_POSITIVE;
			} 	
		}
		logRuleCheckResult ("ARC", table, program, moduleType_T, moduleType_P, accessType, result);
		return result;
	}

	// Allow that a "Business Package" reads data from another "Business Package" 
	public int CheckRule_XRA (String table, String program, int moduleType_T, int moduleType_P, String accessType)
	{
		int result = Constants.MODULARITY_DECISION_AWAITED;

		if ( (moduleType_P == Constants.ADD_ON_BUSINESS_PACKAGE) && (moduleType_T == Constants.ADD_ON_BUSINESS_PACKAGE) ) 
		{	if (accessType.equals(Constants.DB_READ) )
			{ result = Constants.MODULARITY_CHECK_POSITIVE;
			} 	
		}
		logRuleCheckResult ("XRA", table, program, moduleType_T, moduleType_P, accessType, result);
		return result;
	}

	// Allow that a "Business Package" creates data from another "Business Package" 
	public int CheckRule_XCA (String table, String program, int moduleType_T, int moduleType_P, String accessType)
	{
		int result = Constants.MODULARITY_DECISION_AWAITED;

		if ( (moduleType_P == Constants.ADD_ON_BUSINESS_PACKAGE) && (moduleType_T == Constants.CORE_BANKING_PACKAGE) ) 
		{	if (accessType.equals(Constants.DB_CREATE) )
			{ result = Constants.MODULARITY_CHECK_POSITIVE;
			} 	
		}
		logRuleCheckResult ("XCA", table, program, moduleType_T, moduleType_P, accessType, result);
		return result;
	}	
	
	// Allow that a "Business Package" deletes data from another "Business Package" 
	public int CheckRule_XDA (String table, String program, int moduleType_T, int moduleType_P, String accessType)
	{
		int result = Constants.MODULARITY_DECISION_AWAITED;

		if ( (moduleType_P == Constants.ADD_ON_BUSINESS_PACKAGE) && (moduleType_T == Constants.CORE_BANKING_PACKAGE) ) 
		{	if (accessType.equals(Constants.DB_DELETE) )
			{ result = Constants.MODULARITY_CHECK_POSITIVE;
			} 	
		}
		logRuleCheckResult ("XDA", table, program, moduleType_T, moduleType_P, accessType, result);
		return result;
	}		

	// Allow that a "Business Package" updates data from another "Business Package" 
	public int CheckRule_XUA (String table, String program, int moduleType_T, int moduleType_P, String accessType)
	{
		int result = Constants.MODULARITY_DECISION_AWAITED;

		if ( (moduleType_P == Constants.ADD_ON_BUSINESS_PACKAGE) && (moduleType_T == Constants.CORE_BANKING_PACKAGE) ) 
		{	if (accessType.equals(Constants.DB_UPDATE) )
			{ result = Constants.MODULARITY_CHECK_POSITIVE;
			} 	
		}
		logRuleCheckResult ("XUA", table, program, moduleType_T, moduleType_P, accessType, result);
		return result;
	}	


	// Allow that a "Business Package" creates/reads/updates/deletes data from another "Business Package" 
	public int CheckRule_XFA (String table, String program, int moduleType_T, int moduleType_P, String accessType)
	{
		int result = Constants.MODULARITY_DECISION_AWAITED;

		if ( (moduleType_P == Constants.ADD_ON_BUSINESS_PACKAGE) && (moduleType_T == Constants.CORE_BANKING_PACKAGE) ) 
		{	if ( accessType.equals(Constants.DB_CREATE) ||
				 accessType.equals(Constants.DB_READ)   || 
				 accessType.equals(Constants.DB_UPDATE) ||
				 accessType.equals(Constants.DB_DELETE)	)
			{ result = Constants.MODULARITY_CHECK_POSITIVE;
			} 	
		}
		logRuleCheckResult ("XFA", table, program, moduleType_T, moduleType_P, accessType, result);
		return result;
	}	
	
	// Disallow all access to the table 
	public int CheckRule_RMV (String table, String program, int moduleType_T, int moduleType_P, String accessType)
	{
		int result = Constants.MODULARITY_CHECK_NEGATIVE;
		
		if (table.contains(Constants.WILDCARD)) { result = Constants.MODULARITY_DECISION_AWAITED ;}  // some protection
		logRuleCheckResult ("RMV", table, program, moduleType_T, moduleType_P, accessType, result);
		return result;
	}	
	
	// Catch all rule --- anything is disallowed --- This rule is always present
	public int CheckRule_VLN (String table, String program, int moduleType_T, int moduleType_P, String accessType)
	{
		int result = Constants.MODULARITY_CHECK_NEGATIVE;
		logRuleCheckResult ("VLN", table, program, moduleType_T, moduleType_P, accessType, result);
		return result;
	}	
	
	// Rule to log "Change to table"  --- should not be used for now
	public int CheckRule_C2T (String table, String program, int moduleType_T, int moduleType_P, String accessType)
	{
		int result = Constants.MODULARITY_CHECK_NEGATIVE;
		logRuleCheckResult ("C2T", table, program, moduleType_T, moduleType_P, accessType, result);
		return result;
	}
	
	//  Rule to log "Change to service"   --- should not be used for now
	public int CheckRule_C2S (String table, String program, int moduleType_T, int moduleType_P, String accessType)
	{
		int result = Constants.MODULARITY_CHECK_NEGATIVE;
		logRuleCheckResult ("C2S", table, program, moduleType_T, moduleType_P, accessType, result);
		return result;
	}
	
	//  Rule to log "Change to service"   --- should not be used for now
	private int logRuleCheckResult (String rule, String table, String program, int moduleType_T, int moduleType_P, String accessType, int result)
	{	
		Date date = new Date();  	
		
		if (result != Constants.MODULARITY_DECISION_AWAITED)
		{ 
			String logLevel = GetRuleLogLevel(rule);
			
			if (!logLevel.contains("None")) {    // quick and dirty check to avoid logging right at the end			
				System.out.println (date.toString()+ ",rule=" + rule+ 
					",program=" + program 	+ ",module_P=" 	+ moduleType_P + 
					",table="  	+ table 	+ ",module_T=" 	+ moduleType_T +  
					",access=" + accessType +
					",result=" + ConvertConstantToString(result) + ",logLevel="  + logLevel  );			
			}


		}
		else
		{
			/* do not log intermediate states
			System.out.println (date.toString()+ ",rule=" + rule+ 
					",program=" + program 	+ ",module_P=" 	+ moduleType_P + 
					",table="  	+ table 	+ ",module_T=" 	+ moduleType_T +  
					",access=" + accessType +
					",result=" + ConvertConstantToString(result)   );
			*/
		}
		return result;
	}

	
	public static String ConvertConstantToString (int result)
	{
		String message;
		
		switch (result) {
		case  Constants.INFO_AUTHORIZED_TABLE_ACCESS: message = "INFO_AUTHORIZED_TABLE_ACCESS";  break;
		case  Constants.INFO_NON_AUTHORIZED_TABLE_ACCESS: message = "INFO_NON_AUTHORIZED_TABLE_ACCESS";  break;
		case  Constants.WARNING_NON_AUTHORIZED_TABLE_ACCESS: message = "WARNING_NON_AUTHORIZED_TABLE_ACCESS";  break;
		case  Constants.FATAL_NON_AUTHORIZED_TABLE_ACCESS: message = "FATAL_NON_AUTHORIZED_TABLE_ACCESS";  break;
		case  Constants.FATAL_TABLE_DOES_NOT_EXIST: message = "FATAL_TABLE_DOES_NOT_EXIST";  break;
		case  Constants.MODULARITY_CHECK_NEGATIVE: message = "POSI";  break;
		case  Constants.MODULARITY_CHECK_POSITIVE: message = "NEGA";  break;
		case  Constants.MODULARITY_DECISION_AWAITED: message = "WAIT";  break;
		
		default: message = "xxxxxxxxxxxxxxxxxxxxxx"; break;
		}
		
		return message;
	}
	
	public String GetRuleLogLevel (String rule) {
		
		String result="XXX";
		
		try {
			// Open the file
			InputStream fstream = 	this.getClass().getResourceAsStream(Constants.MODULARITY_RULE_TYPES);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");   				// RuleID	Log Level
				if (rule.equals(output[0]))
				{
					result = output[10];
					break;
				}
			}
			// Close the input stream
			
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		return result;
	}
	

}
