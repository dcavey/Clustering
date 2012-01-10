package structurer;


import java.util.ArrayList;
import java.util.Iterator;

import reporter.Reporter;



public class FindClusters  {
	
	public FindClusters(){
		super();
	}

	public void run() /* throws IOException */ {
	
		ObjectModel model = new ObjectModel( true);		// true >> real model, false => test model
		
		DoForModules ( model.getPrograms(), model.getIFSModules());
		//DoForModules ( model.getPrograms(), model.getLBBModules());
	}
	
	
	private void DoForModules (ArrayList<Program> programs, ArrayList<TargetModule> modules)
	{
		MatchMaker matchMaker = new MatchMaker ();
		Reporter reporter = new Reporter();
				
		Iterator<Program>  programIterator  = programs.iterator();
		
		while (programIterator.hasNext()) {
			Program program = programIterator.next();		
			matchMaker.findBestFittingModuleForProgram(program, modules);
		}
		
		reporter.showModules(modules);
		// reporter.ShowSharedTables(ifsModules);
		reporter.showTableUsageAcrossModules(modules, true);
	}


	public static void main(String[] args) {

		FindClusters fc = new FindClusters();
		fc.run();

	}

}
