package structurer;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class OwnerSearcher {
	private boolean printScore;
	
	public OwnerSearcher(boolean printScore){
		this.printScore = printScore;
	}
	
	protected abstract TargetModule getPhysicalOwner(Program program, ArrayList<TargetModule> modules);
	
	protected abstract TargetModule getLogicalOwner(Program program, ArrayList<TargetModule> modules);
	
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
	
	protected TargetModule findModule (ArrayList<TargetModule> modules, String moduleName) {
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
	
	protected void SignalScore (int score, String scoreQualifier,  String moduleType, String moduleName, Program program )  {
		if(printScore){
			System.out.printf ("program=%s %s into %s module=%s with score=%d> \n",  program.getPgmNameAndType(), scoreQualifier, moduleType, moduleName, score );
		}
	}
	
}
