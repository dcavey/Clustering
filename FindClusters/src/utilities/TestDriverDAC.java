package utilities;

import structurer.Constants;


public class TestDriverDAC {
	
	/**
	 * @param args
	 * 0: Table being accessed
	 * 1: Program making the access
	 * 3: Type of access being requested
	 */
	public static void main(String[] args) {

		TestDriverDAC fc = new TestDriverDAC(); 
		if(args.length < 3){
			System.out.println ("Not all required arguments are provided");
		} 

		fc.testAuthorization(args);
		fc.testAccess(args);
	}

	private String program;
	private String table;

	public void testAuthorization(String[] args) /* throws IOException */
	{
		int result;
		AccessGuardian guardian = new AccessGuardian();
		
		result = guardian.CheckAccessRules (table="AAFRO", program="M101C", Constants.DB_CREATE );
		result = guardian.CheckAccessRules (table="AAFRO", program="M101C", Constants.DB_READ );
		result = guardian.CheckAccessRules (table="AAFRO", program="M101C", Constants.DB_UPDATE );
		result = guardian.CheckAccessRules (table="AAFRO", program="M101C", Constants.DB_DELETE );
	}
	
	public void testAccess( String[] args)
	{
		String Columns[] = null;
		String Values[] = null;
		String WHERE_Columns[] = null;
		String WHERE_Values[] = null;
		
		AccessExecutor executor = new AccessExecutor();
		
		executor.setExecutingProgram(program="M101C");
	
		executor.Insert(table="AAFRO", Columns, Values								);
		executor.Update(table="AAFRO", Columns, Values, WHERE_Columns, WHERE_Values	);
		executor.Select(table="AAFRO", Columns, 		WHERE_Columns, WHERE_Values	);
		executor.Delete(table="AAFRO",					WHERE_Columns, WHERE_Values	);

	}
	
	
}
