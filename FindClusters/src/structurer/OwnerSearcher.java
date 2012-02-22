package structurer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class OwnerSearcher {
	private boolean printScore;
	private int score;
	private HashMap<String, Integer> inputFileScore;
	
	public OwnerSearcher(boolean printScore){
		this.printScore = printScore;
		score = -1;
		inputFileScore = new HashMap<String, Integer>();
		fillInFiles();
	}
	
	private void fillInFiles(){
		inputFileScore.put(Constants.PROGRAM2MODULE_CU_XREF,Constants.SCORETMP_SUGGESTEDOWNER);
		inputFileScore.put(Constants.PROGRAM2MODULE_CLOG_XREF, Constants.SCORETMP_SUGGESTEDOWNER);
		inputFileScore.put(Constants.PROGRAM2MODULE_EXPERT_XREF,Constants.SCORETMP_DEFINEDOWNER);
	}
	
	public TargetModule getOwner (Program program, ArrayList<TargetModule> modules) {
		
		TargetModule definedModule = null;
		String type = modules.get(0).getType();
		if(type.equals("IFS")){
			definedModule = getPhysicalOwner (program, modules); 
		} else if(type.equals("LBB")){
			definedModule = getLogicalOwner (program, modules); 
		}
		return definedModule;
	}

	private TargetModule getPhysicalOwner (Program program, ArrayList<TargetModule> modules) {
		TargetModule definedModule = null;
		try {
			Iterator<String> fileIterator = inputFileScore.keySet().iterator();
			while(fileIterator.hasNext()){
				String file = fileIterator.next();
				InputStream fstream = this.getClass().getResourceAsStream(file);
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine = br.readLine();
				// Read File Line By Line
				while ((strLine = br.readLine()) != null) 
				{
					String[] output = strLine.split(";");
					if (program.getName().equals(output[0].trim()) && !output[1].trim().isEmpty()) {
						TargetModule tmpModule = findModule(modules, output[1].trim()); 
						SignalScore (inputFileScore.get(file), "FIT=Y,SEL=N", tmpModule.getType(), tmpModule.getName(), program );
						if(inputFileScore.get(file) > score){
							definedModule = tmpModule;
							score = inputFileScore.get(file);
						}
					}	
				}
				// Close the input stream
				in.close();
			}
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		return definedModule;
	}
	
	private TargetModule getLogicalOwner (Program program, ArrayList<TargetModule> modules) {
		TargetModule definedModule = null;
		try {
			Iterator<String> fileIterator = inputFileScore.keySet().iterator();
			while(fileIterator.hasNext()){
				String file = fileIterator.next();
				InputStream fstream = this.getClass().getResourceAsStream(file);
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine = br.readLine();
				// Read File Line By Line
				while ((strLine = br.readLine()) != null) 
				{
					String[] output = strLine.split(";");
					if (program.getName().equals(output[0].trim()) && !output[2].trim().isEmpty()) {
						TargetModule tmpModule = findModule(modules, output[2].trim()); 
						SignalScore (inputFileScore.get(file), "FIT=Y,SEL=N", tmpModule.getType(), tmpModule.getName(), program );
						if(inputFileScore.get(file) > score){
							definedModule = tmpModule;
						}
					}	
				}
				// Close the input stream
				in.close();
			}
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
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
			System.out.printf("OWNERSEARCHER: failed to find module %s \n", moduleName);
			return null;
		}
	}
	
	private void SignalScore (int score, String scoreQualifier,  String moduleType, String moduleName, Program program )  {
		if(printScore){
			System.out.printf ("program=%s %s into %s module=%s with score=%d> \n",  program.getPgmNameAndType(), scoreQualifier, moduleType, moduleName, score );
		}
	}
	
}
