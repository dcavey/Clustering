package structurer;

import java.util.ArrayList;
import java.util.Iterator;



public  class MatchMaker {

	public MatchMaker() {
		super();
	}

	public void findBestFittingModuleForProgram (Program program, ArrayList<TargetModule> modules) {
		
		int	bestScoreSoFar = 0 ;
		int	score = 0 ;
		TargetModule bestModuleSoFar = null;
		boolean needToAdaptBestScore = false;
		
		Iterator<TargetModule>  moduleIterator  = modules.iterator();
		
		while (moduleIterator.hasNext()) {
			
			TargetModule module = moduleIterator.next();
					
			score = module.getMatchingScoreForProgram(program);
			
			if (needToAdaptBestScore = HandleNewScore ( bestScoreSoFar, score, bestModuleSoFar, module, program )) 
			{
				bestScoreSoFar = score;
				bestModuleSoFar = module;
			}			
		}

		if ((bestScoreSoFar > 0) && (bestScoreSoFar != 0 )) {
			bestModuleSoFar.addProgramToModule(program); 
			SignalScore (bestScoreSoFar, "FIT=Y,SEL=Y", bestModuleSoFar.getType(), bestModuleSoFar.getName(), program );
			// System.out.printf ("%s Module-Program fit BEST     : <%s>-<%s> - Score<%d> \n\n",  bestModuleSoFar.getTypedName(), program.getName(), bestScoreSoFar ); 			
		} else
		{
			SignalScore (-1, "FIT=N,SEL=N", "N/A", "NO_MODULE_AT_ALL", program );	// not classifiable at all		
		}
	}	

	private boolean HandleNewScore ( int bestScoreSoFar, int newScore, TargetModule bestModuleSoFar, TargetModule newModule, Program program ) {
		boolean update = false;
		boolean signal = true;
		
		String fitMessage ="";

		if (newScore != 0) 
		{
			if (newScore >= bestScoreSoFar) {
				fitMessage = "FIT=Y,SEL=u";
				update = true;
				signal = false;  // will be signalled later (when better is found or at end of search)  
			}
			
			if (newScore < bestScoreSoFar) {
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
		 System.out.printf ("program=%s %s into %s module=%s with score=%d> \n",  program.getPgmNameAndType(), scoreQualifier, moduleType, moduleName, score ); 
	}
}

