package utilities;

import structurer.FindClusters;
import structurer.ObjectModel;



public class AccessGuardianMain {

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

		AccessGuardianMain fc = new AccessGuardianMain();
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
	
	
}
