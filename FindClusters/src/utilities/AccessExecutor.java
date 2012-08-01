package utilities;

import structurer.Constants;

public class AccessExecutor {

	
	private String executingProgram;
	private AccessGuardian guardian;

	public AccessExecutor() {
		guardian = new AccessGuardian();
	}
	
	public String getExecutingProgram() {
		return executingProgram;
	}

	public void setExecutingProgram(String program) {
		this.executingProgram = program;
	}



	public int 	Delete ( String table_name,  String columns[],  String values[])
	{
		int result = guardian.CheckAccessRules (table_name, getExecutingProgram(), Constants.DB_DELETE );
		return result;
	}
	 
	public int Update (String table_name, String update_columns[], String values[], 
			String match_columns[], String match_values[])
	{
		int result = guardian.CheckAccessRules (table_name, getExecutingProgram(), Constants.DB_UPDATE );
		return result;
	}

	public int Insert (String table_name, String columns[], String values[])
	{
		int result = guardian.CheckAccessRules (table_name, getExecutingProgram(), Constants.DB_CREATE);
		return result;
	}

	public int Select (String table_name, String _select_columns[], String match_columns[], String match_values[])
	{
		int result = guardian.CheckAccessRules (table_name, getExecutingProgram(), Constants.DB_READ );
		return result;
	}
	
	

}
