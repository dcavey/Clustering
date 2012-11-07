package utilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import structurer.Constants;

public class AccessGuardian {
	
	public AccessGuardian() {
		super();
	}


	public Integer CheckAccessRules (String table, String program, String accessType) 
	{
		int result =Constants.MODULARITY_DECISION_AWAITED;

		
		SignalAccessCheck (table, program, accessType);
	
		// int[] rules = GetRulesForTable (table);
		
		String module_T = GetModuleOfTable(table);
		String module_P = GetModuleOfProgram(program);

		
		if ( (module_T != null) && (module_P != null)) {		// if we know both the table and the program, we can do a real check
			
	 		List<Integer> ruleTypes = GetRuleTypes();	
	 		List<Integer> rules = GetRulesForTable (table);	
	 		
	 		
			int moduleType_T = GetModuleType (module_T);
			int moduleType_P = GetModuleType (module_P);
			

			for (int rt_i = 0; rt_i < ruleTypes.size(); rt_i++) 
			{
				for (int r_i = 0; r_i < rules.size(); r_i++) 
				{
					if (rules.get(r_i) == ruleTypes.get(rt_i) )		// Are we doing this ruleType now?
					{
						result = HandleRule (table, program, moduleType_T, moduleType_P, accessType, rules.get(r_i));					
					}
				
					if (result != Constants.MODULARITY_DECISION_AWAITED)
					{
						break;		// Decision reached ... stop processing rules of this rule type
					}
				}
			}
		}
		else
		{
			result = Constants.MODULARITY_CHECK_NEGATIVE;
		}
		
		SignalAccessCheckWithResult (table, program, accessType, result);
				
		return result;
	}
	
	private int HandleRule (String table, String program, int moduleType_T, int moduleType_P, String accessType, int ruleId)
	{
		CheckModularityRules checkModularityRules = new CheckModularityRules();
		
		int result =Constants.MODULARITY_DECISION_AWAITED;
		switch (ruleId) 
		{
			case Constants.RULE_OFA: result = checkModularityRules.CheckRule_OFA (table, program, moduleType_T, moduleType_P, accessType);break;	
			case Constants.RULE_ARC: result = checkModularityRules.CheckRule_ARC (table, program, moduleType_T, moduleType_P, accessType);break;
			case Constants.RULE_CRA: result = checkModularityRules.CheckRule_CRA (table, program, moduleType_T, moduleType_P, accessType);break;
			case Constants.RULE_XRA: result = checkModularityRules.CheckRule_XRA (table, program, moduleType_T, moduleType_P, accessType);break;
			case Constants.RULE_XUA: result = checkModularityRules.CheckRule_XUA (table, program, moduleType_T, moduleType_P, accessType);break;
			case Constants.RULE_XCA: result = checkModularityRules.CheckRule_XCA (table, program, moduleType_T, moduleType_P, accessType);break;
			case Constants.RULE_XDA: result = checkModularityRules.CheckRule_XDA (table, program, moduleType_T, moduleType_P, accessType);break;
			case Constants.RULE_XFA: result = checkModularityRules.CheckRule_XFA (table, program, moduleType_T, moduleType_P, accessType);break;
			case Constants.RULE_RMV: result = checkModularityRules.CheckRule_RMV (table, program, moduleType_T, moduleType_P, accessType);break;
			case Constants.RULE_C2T: result = checkModularityRules.CheckRule_C2T (table, program, moduleType_T, moduleType_P, accessType);break;
			case Constants.RULE_C2S: result = checkModularityRules.CheckRule_C2S (table, program, moduleType_T, moduleType_P, accessType);break;
			case Constants.RULE_VLN: result = checkModularityRules.CheckRule_VLN (table, program, moduleType_T, moduleType_P, accessType);break;
			default: 	
				System.out.println ("Unknown rule: %d" + ruleId);
				break;
		    }
		
		return result;

	}
	

	public void UseBulkTestData () {
		
		int result;
		
		try {
			// Open the file
			FileInputStream fstream = new FileInputStream(Constants.TABLE2PROGRAM__OPERATIONS);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");   				// Table	Program	  Action	Type
		
				result = CheckAccessRules (output[0], output[1], output[2]);
				
			}
			// Close the input stream
			
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
	}
	

	
	private String GetModuleOfTable (String table) {
		
		String result = null;
		try {
			// Open the file
			InputStream fstream = 	this.getClass().getResourceAsStream(Constants.TABLES2MODULE);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				if (table.equals(output[0])) {
			//		if (action.equals(output[2]) ) {
						result = output[1];
						break;
			//		}
				}
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		return result;
	}
	
	private List<Integer> GetRulesForTable (String table) {
		
		List<Integer> rules = new ArrayList<Integer>();
		
		try {
			// Open the file
			InputStream fstream = 	this.getClass().getResourceAsStream(Constants.MODULARITY_RULES);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				
				if (output[0].equals(Constants.WILDCARD)) {
					// take this rule because ot applies to all
					rules.add(ConvertRuleToInt (output[1]));
				}
				
				if (table.equals(output[0])) {
					// take this rule because it applies to this table itself
					rules.add(ConvertRuleToInt (output[1]));
					break;
				}
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		return rules;
	}

	private List<Integer> GetRuleTypes () {
		
		List<Integer> ruleTypes = new ArrayList<Integer>();
		
		try {
			// Open the file
			InputStream fstream = 	this.getClass().getResourceAsStream(Constants.MODULARITY_RULE_TYPES);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// RuleID	RuleName	Data	Type_OwnerPackage	Type_AccessorPackage	C	R	U	D	Rule Description			
				String[] output = strLine.split(";"); 
				ruleTypes.add(ConvertRuleToInt (output[0]));
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		return ruleTypes;
	}

	
	
	private String GetAccessTypeOfTable (String table, String action) {
		
		String result = null;
		try {
			// Open the file
			InputStream fstream = 	this.getClass().getResourceAsStream(Constants.TABLES2MODULE);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				if (table.equals(output[0])) {
					if (action.equals(output[2]) ) {
						result = output[1];
						break;
					}
				}
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		return result;
	}
	
	private String GetModuleOfProgram (String program) {
		
		String result = null;
		try {
			// Open the file
			InputStream fstream = 	this.getClass().getResourceAsStream(Constants.PROGRAMS2MODULE);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				if (program.equals(output[0])) {
					result = output[1];
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

	private int GetModuleType (String module) {
		
		int result = 0;
		try {
			// Open the file
			InputStream fstream = 	this.getClass().getResourceAsStream(Constants.MODULES);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				if (module.equals(output[0])) {
					return ConvertModuleTypeToInt (output[1]);		
				}
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		return result;
	}
	
	
	private int ConvertRuleToInt (String rule)
	{
		if (rule.contains("OFA"))  { return Constants.RULE_OFA;}	
		if (rule.contains("ARC"))  { return Constants.RULE_ARC;}
		if (rule.contains("CRA"))  { return Constants.RULE_CRA;}
		if (rule.contains("XRA"))  { return Constants.RULE_XRA;}
		if (rule.contains("XUA"))  { return Constants.RULE_XUA;}
		if (rule.contains("XCA"))  { return Constants.RULE_XCA;}
		if (rule.contains("XDA"))  { return Constants.RULE_XDA;}
		if (rule.contains("XFA"))  { return Constants.RULE_XFA;}
		if (rule.contains("RMV"))  { return Constants.RULE_RMV;}
		if (rule.contains("C2T"))  { return Constants.RULE_C2T;}
		if (rule.contains("C2S"))  { return Constants.RULE_C2S;}
		if (rule.contains("VLN"))  { return Constants.RULE_VLN;}
		
		return -1;
	}
	
	private int ConvertModuleTypeToInt (String rule)
	{
		if (rule.contains("Core"))  				{ return Constants.CORE_BANKING_PACKAGE;}	
		if (rule.contains("Add_On")) 			{ return Constants.ADD_ON_BUSINESS_PACKAGE;}
		
		return -1;
	}
	
	private String ConvertIntToRule (int rule)
	{
		switch (rule) 
		{
			case Constants.RULE_OFA: return "OFA"; 	
			case Constants.RULE_ARC: return "ARC"; 
			case Constants.RULE_CRA: return "CRA"; 
			case Constants.RULE_XRA: return "XRA"; 
			case Constants.RULE_XUA: return "XUA"; 
			case Constants.RULE_XCA: return "XCA"; 
			case Constants.RULE_XDA: return "XDA"; 
			case Constants.RULE_XFA: return "XFA"; 
			case Constants.RULE_RMV: return "RMV"; 
			case Constants.RULE_C2T: return "C2T"; 
			case Constants.RULE_C2S: return "C2S"; 
			case Constants.RULE_VLN: return "VLN"; 
			default: return "???";
		}
	}

	
	private String GetAccessStatement (String table, String program, String accessType)
	{
		String message;
		String SqlStm = "";
		
		if (accessType.equals(Constants.DB_CREATE)) SqlStm = "INSERT INTO " + table + " (columnP,...) VALUES (valueP,...) " ;
		if (accessType.equals(Constants.DB_READ)) SqlStm   = "SELECT (columnP,...) FROM " + table + " WHERE columnX=valueX";
		if (accessType.equals(Constants.DB_UPDATE)) SqlStm = "UPDATE " + table + " SET columnP=valueP WHERE columnX=valueX";
		if (accessType.equals(Constants.DB_DELETE)) SqlStm = "DELETE FROM " + table + " WHERE columnX=valueX              ";
		

		//message = "Program " + program + " requesting DB operation " + '"'+ SqlStm + '"';
		message = "Program=" + program + ",Table=" + table + ",DB operation="+ accessType;
		
		return message;
		
	}
	private String SignalAccessCheck (String table, String program, String accessType)
	{
		String message;

		message = GetAccessStatement( table,  program,  accessType);
		// System.out.println(message);
		
		return message;
		
	}
	
	private String SignalAccessCheckWithResult (String table, String program, String accessType, int result)
	{
		
		String message;
		
		message = GetAccessStatement( table,  program,  accessType) +  " resulting in: " + CheckModularityRules.ConvertConstantToString(result); 
		//System.out.println(message);
		return message;
	}

	
	public String ConvertCodeToString (int result)
	{
		String message;
		
		switch (result) {
		case  Constants.INFO_AUTHORIZED_TABLE_ACCESS: message = "INFO_AUTHORIZED_TABLE_ACCESS";  break;
		case  Constants.INFO_NON_AUTHORIZED_TABLE_ACCESS: message = "INFO_NON_AUTHORIZED_TABLE_ACCESS";  break;
		case  Constants.WARNING_NON_AUTHORIZED_TABLE_ACCESS: message = "WARNING_NON_AUTHORIZED_TABLE_ACCESS";  break;
		case  Constants.FATAL_NON_AUTHORIZED_TABLE_ACCESS: message = "FATAL_NON_AUTHORIZED_TABLE_ACCESS";  break;
		case  Constants.FATAL_TABLE_DOES_NOT_EXIST: message = "FATAL_TABLE_DOES_NOT_EXIST";  break;
		case  Constants.MODULARITY_CHECK_NEGATIVE: message = "MODULARITY CHECK NEGATIVE";  break;
		case  Constants.MODULARITY_CHECK_POSITIVE: message = "MODULARITY CHECK POSITIVE";  break;
		case  Constants.MODULARITY_DECISION_AWAITED: message = "MODULARITY DECISION AWAITED";  break;
		
		default: message = "xxxxxxxxxxxxxxxxxxxxxx"; break;
		}
		
		return message;
	}
	


}
