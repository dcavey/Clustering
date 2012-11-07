package utilities;

import java.util.Date;

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

	}


	public void testAuthorization(String[] args) /* throws IOException */
	{
		int result;
		AccessGuardian guardian = new AccessGuardian();
		
		if(args.length == 3){		
			result = guardian.CheckAccessRules (args[0], args[1], args[2] );
		}

		System.out.println ( "Begin data & time=" +  new Date().toString());    
		guardian.UseBulkTestData();
		System.out.println ( "End data & time=" +  new Date().toString()); 
		
		
	}	

}
