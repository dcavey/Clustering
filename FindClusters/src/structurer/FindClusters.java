package structurer;


import java.util.ArrayList;
import java.util.Iterator;

import reporter.Reporter;


public class FindClusters  {
	/**
	 * If you want the output "out_TablesAndProgramsContainedInModules" (still in console)
	 * 			use TEST=false; PRINTSCORE=false; PRINTUSE=false; PRINTCONTAINS=1
	 * 
	 * If you want output "out_TablesAndProgramsUsedByModules" - ProgramModuleUsesTableModule (still in console)
	 * 			use TEST=false; PRINTSCORE=false; PRINTUSE=true; PRINTCONTAINS=0
	 * 
	 * @Tom: If you want complete output
	 * 			use TEST=false; PRINTSCORE=true; PRINTUSE=true; PRINTCONTAINS=0
	 */
	private static boolean TEST = false; 			// true => test model, false => real model
	private static boolean PRINTSCORE = false;		// true => print score, false => no scores displayed
	private static boolean PRINTUSE = true;		// true => print use, false => no use displayed
	private static int PRINTCONTAINS = 0;			// 0 => nothing displayed
													// 1 => showModules
													// 2 => ShowSharedTables
													// 3 => showTableUsageAcrossModules
	
	public FindClusters(){
		super();
	}

	public void run() /* throws IOException */ {
	
		ObjectModel model = new ObjectModel(!TEST);		
		
		DoForModules ( model.getPrograms(), model.getIFSModules());
		//DoForModules ( model.getPrograms(), model.getLBBModules());
	}
	
	
	private void DoForModules (ArrayList<Program> programs, ArrayList<TargetModule> modules)
	{
		MatchMaker matchMaker = new MatchMaker (PRINTSCORE, PRINTUSE);
		Reporter reporter = new Reporter();
				
		Iterator<Program>  programIterator  = programs.iterator();
		
		while (programIterator.hasNext()) {
			Program program = programIterator.next();		
			matchMaker.findBestFittingModuleForProgram(program, modules);
		}
		
		switch(PRINTCONTAINS){
			case 1: reporter.showModules(modules);
					break;
			case 2: reporter.ShowSharedTables(modules);
					break;
			case 3: reporter.showTableUsageAcrossModules(modules, true);
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
