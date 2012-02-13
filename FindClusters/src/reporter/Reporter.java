package reporter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import structurer.Program;
import structurer.Table;
import structurer.TargetModule;

public class Reporter {
	private boolean tocsv = false;
	private boolean toStdOut = false;
	
	public Reporter() {
		super();
	}
	
	public Reporter(boolean tocsv, boolean toStdOut) {
		super();
		this.tocsv = tocsv;
		this.toStdOut = toStdOut;
	}
	
	public void showModules (ArrayList<TargetModule> modules) {
		
		Iterator<TargetModule>  moduleIterator  = modules.iterator();
		
		while (moduleIterator.hasNext()) {
			TargetModule module = moduleIterator.next();	
			module.showComposition(tocsv,toStdOut);
		}
	}
	
	public void showTableUsageAcrossModules (ArrayList<TargetModule> modules, boolean showAll) {
		
		Iterator<TargetModule>  moduleIterator  = modules.iterator();
		
		while (moduleIterator.hasNext()) {
			TargetModule module = moduleIterator.next();	
			module.showTableUsageAcrossModules(true,tocsv, toStdOut);
		}
		
	}

	
	
	
	public void ShowSharedTables (ArrayList<TargetModule> modules) {
		
		ArrayList<CoupleOfModules> coupleOfModules = getUniqueCouplesOfModules(modules);
		
		Iterator<CoupleOfModules>  coupleIterator  = coupleOfModules.iterator();
		
		while (coupleIterator.hasNext()) {
			
			CoupleOfModules couple = coupleIterator.next();
			
			System.out.printf ("Start matching %s of modules %s & %s \n", new Date(), couple.module1.getName(), couple.module2.getName() );
			FindSharedTablesForTwoModules (couple.module1, couple.module2);
			System.out.printf ("Stop matching %s of modules %s & %s \n", new Date(), couple.module1.getName(), couple.module2.getName() );
		}

		Iterator<TargetModule>  moduleIterator  = modules.iterator();
		
		while (moduleIterator.hasNext()) {
			
			TargetModule module = moduleIterator.next();

			System.out.printf ("Start showing %s of module %s \n", new Date(), module.getName() );
			module.showTablesSharedWithOtherModules();
			System.out.printf ("Stop showing %s of modules %s \n", new Date(), module.getName() );
		}
		
	}
	
	public void FindSharedTablesForTwoModules (TargetModule module1, TargetModule module2) {
		/* Loop over all programs and find if their tables are common to tables used by the programs of the other module
		 */
		
		Iterator<Program>  programIteratorModule1  = module1.getPrograms().iterator();
		
		while (programIteratorModule1.hasNext()) {
			
			Program programFromModule1 = programIteratorModule1.next();

			Iterator<Program>  programIteratorModule2  = module2.getPrograms().iterator();
			while (programIteratorModule2.hasNext()) {
				Program programFromModule2 = programIteratorModule2.next();
				
				FindSharedTablesForTwoPrograms (module1, programFromModule1, module2,programFromModule2);
			}
		}
	}
	
	
	private void FindSharedTablesForTwoPrograms (TargetModule module1, Program program1, TargetModule module2, Program program2) {
		
		Iterator<Table>  tableIteratorProgram1  = program1.getTables().iterator();
		
		while (tableIteratorProgram1.hasNext()) {
			
			Table tableFromProgram1 = tableIteratorProgram1.next();

			Iterator<Table>  tableIteratorProgram2  = program2.getTables().iterator();
			while (tableIteratorProgram2.hasNext()) {
				Table tableFromProgram2 = tableIteratorProgram2.next();

				if (tableFromProgram1.getName().equals(tableFromProgram2.getName())) {
					module1.addTableUsedByProgram(module2, program2, program1, tableFromProgram1 );
					module2.addTableUsedByProgram(module1, program1, program2, tableFromProgram2 );
				}
			}
		}
	}
	
	private class CoupleOfModules {
		TargetModule module1;
		TargetModule module2;
	}
	
	private ArrayList<CoupleOfModules> getUniqueCouplesOfModules (ArrayList<TargetModule> modules) {
		
		ArrayList<CoupleOfModules> uniqueCouples;
		
		uniqueCouples = new ArrayList<CoupleOfModules>();
		
		Iterator<TargetModule>  moduleIterator1  = modules.iterator();
		
		while (moduleIterator1.hasNext()) {
			
			TargetModule module1 = moduleIterator1.next();

			Iterator<TargetModule>  moduleIterator2  = modules.iterator();
			
			while (moduleIterator2.hasNext()) {
			
				TargetModule module2 = moduleIterator2.next();
				
				CoupleOfModules couple = new CoupleOfModules();
				
				if (module1.getName().compareTo (module2.getName()) < 0 ) {
					couple.module1 = module1;
					couple.module2 = module2;					
				} else
				{
					couple.module1 = module2;
					couple.module2 = module1;					
				}			
				if (  coupleRequired (couple, uniqueCouples)) {
					uniqueCouples.add(couple);	
				}
			}
		}
		return uniqueCouples;
		
	}
	
	private boolean coupleRequired (CoupleOfModules newCouple, ArrayList<CoupleOfModules> arrayOfCouples) {
		boolean required = true;

		if (newCouple.module1.getName().equals(newCouple.module2.getName()) ) {
			return false;
		}
		
		if ((newCouple.module1.getName().equals("CORE") ) || 
			(newCouple.module2.getName().equals("CORE") ) ||
			(newCouple.module1.getName().equals("auxiliary") ) ||
			(newCouple.module2.getName().equals("auxiliary") ))
			{
			return false;
		}
		
		
		Iterator<CoupleOfModules>  coupleIterator  = arrayOfCouples.iterator();
	
		while (coupleIterator.hasNext()) {
			
			CoupleOfModules couple = coupleIterator.next();
			
			if (	couple.module1.getName().equals(newCouple.module1.getName())  && 
					couple.module2.getName().equals(newCouple.module2.getName())     ) 
			{
				required = true;
				break;
			}
		}
		
		return required;
	}
	
}


