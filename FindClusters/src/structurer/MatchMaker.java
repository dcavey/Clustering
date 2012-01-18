package structurer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;



public  class MatchMaker {
	
	private boolean printScore = true;
	private boolean printUse = true;

	public MatchMaker() {
		super();
	}
	
	public MatchMaker(boolean printScore, boolean printUse) {
		super();
		this.printScore = printScore;
		this.printUse = printUse;
	}

	public void findBestFittingModuleForProgram (Program program, ArrayList<TargetModule> modules) {
		
		int	bestScoreSoFar = 0 ;
		int	score = 0 ;
		TargetModule bestModuleSoFar = null;
		TargetModule definedModule;
		boolean needToAdaptBestScore;
				
		definedModule = getDefinedOwner (program, modules);
		
		if (definedModule != null) {
			SignalScore (999, "FIT=Y,SEL=Y", definedModule.getType(), definedModule.getName(), program );	
			return;
		}
		
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
	
	private TargetModule getDefinedOwner (Program program, ArrayList<TargetModule> modules) {
		
		TargetModule definedModule = null;
		
		try 
		{
			// Open the file
			InputStream fstream = this.getClass().getResourceAsStream(Constants.PROGRAM2MODULE_XREF);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) 
			{
				String[] output = strLine.split(";");
				
				/*
				 * index 
				 * 	0 = Program Name
				 * 	1 = Module Name
				 * 
				 */
				if (program.getName().equals(output[0])) {

					if (definedModule != null) {
						SignalScore (998, "FIT=Y,SEL=N", definedModule.getType(), definedModule.getName(), program );							
					}
					
					definedModule = findModule(modules, output[1]);
				}	
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.out.println("Error: " + e.getMessage());
		}
		
		return definedModule;
		
	}
	
	private TargetModule findModule (ArrayList<TargetModule> modules, String moduleName) {
		Iterator<TargetModule>  moduleIterator  = modules.iterator();
		TargetModule thisModule = null;
		boolean found = false;	
		while (moduleIterator.hasNext()) {
			thisModule = moduleIterator.next();
			if (thisModule.getName().equals(moduleName))
			{ found= true; break;  }
		}
		if (found) 
		{ return thisModule; } 
		else {
			System.out.printf("MATCHMAKER: failed to find module %s /n", moduleName);
			return null;
		}
	}
	
	
}