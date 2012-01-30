package structurer;

import java.util.ArrayList;
import java.util.Iterator;


public  class MatchMaker {
	
	private boolean printScore = true;
	private boolean printUse = true;
	
	public MatchMaker(boolean printScore, boolean printUse) {
		super();
		this.printScore = printScore;
		this.printUse = printUse;
	}

	public void findBestFittingModuleForProgram (Program program, ArrayList<TargetModule> modules, ArrayList<Interface> interfaces) {
		
		int	bestScoreSoFar = 0 ;
		int	score = 0 ;
		TargetModule bestModuleSoFar = null;
		TargetModule definedModule;
		boolean needToAdaptBestScore;
		
		// Check to see if program is already classified
		OwnerSearcher os = new OwnerSearcher(printScore);
		definedModule = os.getOwner (program, modules);
		if (definedModule != null) {
			SignalScore (Constants.SCOREFINALOWNER, "FIT=Y,SEL=N", definedModule.getType(), definedModule.getName(), program );
			if(bestScoreSoFar < Constants.SCOREFINALOWNER){
				bestScoreSoFar = Constants.SCOREFINALOWNER;  
				bestModuleSoFar = definedModule;
			}
		}
		
		// Do reasoning on all possible modules and check number of uses to set score
		Iterator<TargetModule>  moduleIterator  = modules.iterator();
		while (moduleIterator.hasNext()) {
			TargetModule module = moduleIterator.next();
			// Score depends on #used tables from module AND fact if module == TK/MDM/UNUSED or module == AM
			score = module.getMatchingScoreForProgram(program,printUse);
			
			// Check the score for this specific module for a fit and a selection
			if (needToAdaptBestScore = HandleNewScore ( bestScoreSoFar, score, bestModuleSoFar, module, program )) {
				bestScoreSoFar = score;
				bestModuleSoFar = module;
			}			
		}
		
		// Assign program to best fitting module
		if ((bestScoreSoFar > 0) && (bestScoreSoFar != 0 ) /* && (bestModuleSoFar != null) */ ) {
			bestModuleSoFar.addProgramToModule(program);
			int j = 0;
			while(j < interfaces.size() && !interfaces.get(j).getProgramName().equals(program.getName())){
				j++;
			}
			if(j < interfaces.size()){
				bestModuleSoFar.addInterfaceToModule(interfaces.get(j));
			}
			SignalScore (bestScoreSoFar, "FIT=Y,SEL=Y", bestModuleSoFar.getType(), bestModuleSoFar.getName(), program );
			// System.out.printf ("%s Module-Program fit BEST     : <%s>-<%s> - Score<%d> \n\n",  bestModuleSoFar.getTypedName(), program.getName(), bestScoreSoFar ); 			
		} else {
			SignalScore (-1, "FIT=N,SEL=N", "N/A", "N/A", program );	// not classifiable at all		
		}
		
		
	}	

	private boolean HandleNewScore ( int bestScoreSoFar, int newScore, TargetModule bestModuleSoFar, TargetModule newModule, Program program ) {
		boolean update = false;
		boolean signal = true;
		
		String fitMessage ="";

		if (newScore != 0) 
		{
			if (newScore > bestScoreSoFar) {
				fitMessage = "FIT=Y,SEL=u";
				update = true;
				signal = false;  // will be signalled later (when better is found or at end of search)  
			}
			
			if (newScore <= bestScoreSoFar) {
				fitMessage = "FIT=Y,SEL=N";
			}
		} else
		{
			fitMessage = "FIT=N,SEL=N";
		}

		// Log each score
		if (signal) 
		{ SignalScore (newScore, fitMessage, newModule.getType(), newModule.getName(), program ); }	

		
		return update;
	
	}
	
	private void SignalScore (int score, String scoreQualifier,  String moduleType, String moduleName, Program program )  {
		if(printScore){
			System.out.printf ("program=%s %s into %s module=%s with score=%d> \n",  program.getPgmNameAndType(), scoreQualifier, moduleType, moduleName, score );
		}
	}
	
}

