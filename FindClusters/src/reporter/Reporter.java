package reporter;

import java.util.ArrayList;
import java.util.Iterator;

import structurer.TargetModule;

public class Reporter {

	public Reporter() {
		super();
	}

	public void showModules (ArrayList<TargetModule> modules) {
		
		Iterator<TargetModule>  moduleIterator  = modules.iterator();
		
		while (moduleIterator.hasNext()) {
			TargetModule module = moduleIterator.next();	
			module.showComposition();
		}
	}
	
	
}


