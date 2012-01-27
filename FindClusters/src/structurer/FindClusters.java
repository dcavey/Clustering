package structurer;


import java.util.ArrayList;
import java.util.Iterator;

import reporter.Reporter;


public class FindClusters  {
	/**
	 * If you want the output "out_TablesAndProgramsContainedInModules" (in console)
	 * 			use PRINTSCORE=false; PRINTUSE=false; PRINTCONTAINS=1
	 * 
	 * If you want output "out_TablesAndProgramsUsedByModules" - ProgramModuleUsesTableModule (in console)
	 * 			use PRINTSCORE=false; PRINTUSE=false; PRINTCONTAINS=3
	 * 
	 * If you want any of these two outputs in csv, put TOCSV = true, otherwise TOCSV = false
	 * If you want any of these two outputs in std out, put TOSTDOUT = true, otherwise STDOUT = false
	 * 
	 * If you want to run it only with testdata, put TEST = true, otherwise TEST = false
	 * 
	 * @Tom: If you want complete output
	 * 			use PRINTSCORE=true; PRINTUSE=true; PRINTCONTAINS=3
	 */

	/* COMPLETE OUTPUT settings 
	
	private static boolean 	TEST = false; 			// true => test model, false => real model
	private static boolean 	TOCSV = true;			// true => change output from console to csv (!delete old file first)
	private static boolean 	TOSTDOUT = true;		// true => change output from console to std out
	private static boolean 	PRINTSCORE = true;		// true => print score, false => no scores displayed
	private static boolean 	PRINTUSE = true; 		// true => print use, false => no use displayed
	private static int 		PRINTCONTAINS = 4;		// 0 => nothing displayed
													// 1 => showModules
													// 2 => ShowSharedTables
													// 3 => showTableUsageAcrossModules
													// 4 => showModules + showTableUsageAcrossModules
	private static boolean	PHYSICAL_LEVEL=true;
	private static boolean 	LOGICAL_LEVEL=true;
	*/
		
	private static boolean 	TEST = false; 			
	private static boolean 	TOCSV = true;			
	private static boolean 	TOSTDOUT = true;		
	private static boolean 	PRINTSCORE = true;	
	private static boolean 	PRINTUSE = true; 	
	private static int 		PRINTCONTAINS = 0;		
	private static boolean	PHYSICAL_LEVEL=true;
	private static boolean 	LOGICAL_LEVEL=true;

	
	public FindClusters(){
		super();
	}

	public void run() /* throws IOException */ {
	
		ObjectModel model = new ObjectModel(!TEST);		

		model.createImplementationModel();

		if (PHYSICAL_LEVEL) { 
			model.CreatePhysicalModel();
			PlaceProgramInModules(model.getPrograms(), model.getPhysicalModules(), model.getInterfaces());  
		}
		
		if (LOGICAL_LEVEL) { 
			model.CreateLogicalModel();
			PlaceProgramInModules ( model.getPrograms(), model.getLogicalModules(), model.getInterfaces());  
		}
	}
	
	
	private void PlaceProgramInModules (ArrayList<Program> programs, ArrayList<TargetModule> modules, ArrayList<Interface> interfaces)
	{
		MatchMaker matchMaker = new MatchMaker (PRINTSCORE, PRINTUSE);
		Reporter reporter = new Reporter(TOCSV, TOSTDOUT);
				
		Iterator<Program>  programIterator  = programs.iterator();
		
		while (programIterator.hasNext()) {
			Program program = programIterator.next();		
			matchMaker.findBestFittingModuleForProgram(program, modules, interfaces);
		}
		
		switch(PRINTCONTAINS){
			case 1: reporter.showModules(modules);
					break;
			case 2: reporter.ShowSharedTables(modules);
					break;
			case 3: reporter.showTableUsageAcrossModules(modules, true);
					break;
			case 4: reporter.showModules(modules);						// put option to get both (same as original version)
					reporter.showTableUsageAcrossModules(modules, true);
					break;
			default: break;
		}
	}


	public static void main(String[] args) {

		FindClusters fc = new FindClusters();
		fc.run();
		fc.convertToCSV();

	}

	private void convertToCSV() {
		
		
	}

}
