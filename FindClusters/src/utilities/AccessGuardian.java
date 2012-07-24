package utilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import structurer.Constants;

public class AccessGuardian {
	
	public AccessGuardian() {
		super();
	}
	
	

	public Integer CheckAccessRules (String table, String program, String accessType) 
	{
		int result;

		if ((result = this.CheckAccessRule01_CompletePhaseOut(table, program, accessType)) 
				!= Constants.FATAL_TABLE_DOES_NOT_EXIST)
		{
			result = this.CheckAccessRule02_DataOwnership (table, program, accessType);
		}
		
		SignalAccessCheck (table, program, accessType, result);
		
		return result;
	}

	public Integer CheckAccessRule01_CompletePhaseOut (String table, String program, String accessType) 
	{
		int result =Constants.INFO_AUTHORIZED_TABLE_ACCESS;
		String module_T = GetModuleOfTable(table, Constants.STATUS_REMOVED);
		
		if (module_T != null) {
			result = Constants.FATAL_TABLE_DOES_NOT_EXIST;
		}
		
		return result;
	}
	
	public Integer CheckAccessRule02_DataOwnership (String table, String program, String accessType) 
	{
		int result;
		String module_T = GetModuleOfTable(table, Constants.STATUS_MODULAR);
		String module_P = GetModuleOfProgram(program, Constants.STATUS_MODULAR);
		
		if (! module_T.equals(module_P)) {
			if ((accessType.equals("C"))  ||  (accessType.equals("U")) 	||	(accessType.equals("D")) )   	
			{ 	result =  Constants.WARNING_NON_AUTHORIZED_TABLE_ACCESS;  	}
			else
			{	result =  Constants.INFO_AUTHORIZED_TABLE_ACCESS;			}
		}
		else
		{		result = Constants.INFO_AUTHORIZED_TABLE_ACCESS;			}
		return result;
	}


	public void SignalAccessCheck (String table, String program, String accessType, int result)
	{
		String message;
		
		switch (result) {
		case  Constants.INFO_AUTHORIZED_TABLE_ACCESS: message = "INFO_AUTHORIZED_TABLE_ACCESS";  break;
		case  Constants.INFO_NON_AUTHORIZED_TABLE_ACCESS: message = "INFO_NON_AUTHORIZED_TABLE_ACCESS";  break;
		case  Constants.WARNING_NON_AUTHORIZED_TABLE_ACCESS: message = "WARNING_NON_AUTHORIZED_TABLE_ACCESS";  break;
		case  Constants.FATAL_NON_AUTHORIZED_TABLE_ACCESS: message = "FATAL_NON_AUTHORIZED_TABLE_ACCESS";  break;
		case  Constants.FATAL_TABLE_DOES_NOT_EXIST: message = "FATAL_TABLE_DOES_NOT_EXIST";  break;
		default: message = "xxxxxxxxxxxxxxxxxxxxxx"; break;
		}
		
		System.out.println(message + " of table " + table + " by program "+ program + " access type: " + accessType);
	}
	
	
	
	private String GetModuleOfTable (String table, String action)
	{
		String result = GetModuleFromList(Constants.ASSIGNED_TABLE2MODULE, table, action);
		return result;
	}

	private String GetModuleOfProgram (String table, String action)
	{
		String result = GetModuleFromList(Constants.ASSIGNED_PROGRAM2MODULE, table, action);
		return result;
	}
	
	private String GetModuleFromList (String filename, String program, String status) {
		
		String result = null;
		try {
			// Open the file
			//FileInputStream fstream = new FileInputStream(filename);
			InputStream fstream = 	this.getClass().getResourceAsStream(filename);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				if (program.equals(output[0])) {
					if (status.equals(output[2]) ) {
						result = output[1];
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
}
