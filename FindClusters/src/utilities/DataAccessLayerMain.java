package utilities;

import structurer.FindClusters;
import structurer.ObjectModel;



public class DataAccessLayerMain {

	final int INFO_AUTHORIZED_TABLE_ACCESS = 10;
	final int INFO_NON_AUTHORIZED_TABLE_ACCESS = -10;
	final int WARNING_NON_AUTHORIZED_TABLE_ACCESS = -11;
	final int FATAL_NON_AUTHORIZED_TABLE_ACCESS = -12;
	
	
	/**
	 * @param args
	 * 0: Table being accessed
	 * 1: Program making the access
	 * 3: Type of access being requested
	 */
	public static void main(String[] args) {

		DataAccessLayerMain fc = new DataAccessLayerMain();
		if(args.length < 3){
			System.out.println ("Not all required arguments are provided");
		} 

		fc.run(args);
	}

	public void run(String[] args) /* throws IOException */
	{
		AccessGuardian accessGuardian = new AccessGuardian();
		int result = accessGuardian.CheckAccessRules (args[0], args[1], args[2]);
	}
	
	public void ManipulateData( String someSQLstring)
	{
		// formulate data access request
		// get table and program information from the context and the data access request
		// submit information to check if access if authorized
		
		
		// formulate data access request
			// decide what is the data provider (data schema)
			// launch the data request to that data provider (data schema)
		
			// decide what is the data provider (data service)
			// launch the data request to that service provider (... respect database semantics ???)
		
			// decide how the data can be obtained (reworking data available in two schemas ?)
			// describe the pattern 
		

		
	}
	
	
}
