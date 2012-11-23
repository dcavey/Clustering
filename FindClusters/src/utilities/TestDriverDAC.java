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
        Date d1 = null;
        Date d2 = null;
        
		AccessGuardian guardian = new AccessGuardian();
		
		if(args.length == 3){		
			result = guardian.CheckAccessRules (args[0], args[1], args[2] );
		}

		d1 = new Date();  	
		System.out.println ( "Begin data & time=" +  d1.toString());    
		d1.toString();

		
		long counter = guardian.UseBulkTestData();
		
		/*
		for (long wait=0; wait<400000; wait++) {
			System.out.println ( "Waiting" + wait); 
		}
		*/
		
		d2 = new Date();  	
		System.out.println ( "End data & time=" +  d2.toString()); 
		  

        // Get msec from each, and subtract.
        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000; 
		
		System.out.println ( "Did " + counter + " loggings in " +  diff + " ms"); 
        long diffPerLog = diff / counter; 
		System.out.println ( "Took " + diffPerLog + "ms per logging"); 
		
	}	

}
