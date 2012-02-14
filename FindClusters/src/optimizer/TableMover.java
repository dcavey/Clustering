package optimizer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import reporter.Reporter;
import structurer.ObjectModel;
import structurer.Table;
import structurer.TargetModule;


public class TableMover {

	ObjectModel oldModel;
	ObjectModel newModel;

	

	public TableMover( ObjectModel oldModel) {
		this.oldModel = oldModel;
	}

	public int findTablesToMoveNewModule (ArrayList<Table> tables) 
	{
		int score = 0;
		this.newModel = this.oldModel;
		
		score = findTableUsageAcrossTwoModules(oldModel.getPhysicalModules(), "Lending", "Financial_Markets", tables);
//		score = findTableUsageAcrossTwoModules(oldModel.getPhysicalModules(), "TECHNICAL_KERNEL", "Financial_Markets", tables);
		score = score + findTableUsageAcrossTwoModules(oldModel.getPhysicalModules(), "Financial_Markets", "Lending", tables);
		score = score + findTableUsageAcrossTwoModules(oldModel.getPhysicalModules(), "Payment_And_Cash_Management", "Lending", tables);
		score = score + findTableUsageAcrossTwoModules(oldModel.getPhysicalModules(), "Lending", "Payment_And_Cash_Management", tables);
		score = score + findTableUsageAcrossTwoModules(oldModel.getPhysicalModules(), "Payment_And_Cash_Management", "Financial_Markets", tables);
		score = score + findTableUsageAcrossTwoModules(oldModel.getPhysicalModules(), "Financial_Markets", "Payment_And_Cash_Management", tables);
		
//		System.out.printf ("Count of tables usages across all modules %s \n", score );
		
		return score;
		
	}
	
	public int findTableUsageAcrossTwoModules (ArrayList<TargetModule> modules, String nameModuleA, String nameModuleB,ArrayList<Table> outTables)
	{
		int counter=0;
		TargetModule moduleA;
		TargetModule moduleB;
		
		moduleA = findModule (modules, nameModuleA);
		moduleB = findModule (modules, nameModuleB);
		
		Iterator<Table>  tableIterator = moduleA.getAssignedTables().iterator();
		while (tableIterator.hasNext()) 
		{
			Table table = tableIterator.next();

			if (moduleB.usesExternalTable(table))
			{
				if(!outTables.contains(table)){
					counter++;
					outTables.add(table);
				}
				System.out.printf ("Table;%s;from;%s;used by;%s \n", table.getName(), nameModuleA, nameModuleB, table.getName() );
			}
		}		
		
		//System.out.printf ("Count of tables usages across module:%s & module:%s = %s \n", nameModuleA, nameModuleB, counter );

		return counter;
		
	}
	
	
	int getScoreForModuleCouplingThroughTables(ObjectModel oldmodel)
	{
		return 0;
	}

	public void moveTableToModule(String tableName, String moduleName) {
		//System.out.printf("About to move table %s to module %s \n", tableName, moduleName);
		
		TargetModule oldModule;
		TargetModule module = findModule (oldModel.getPhysicalModules(), moduleName);
		Table table = findTable (oldModel.getTables(), tableName);
		
		oldModule = table.getAssignedModule();
		oldModule.getAssignedTables().remove(table);
		module.addAssignedTable(table);
		
		table.setAssignedModule(module);
	
		System.out.printf("TABLEMOVER: moving table %s from module %s to module %s \n", tableName, oldModule.getName(), moduleName);

		
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
			System.out.printf("TABLEMOVER: failed to find module %s \n", moduleName);
			return null;
		}
	}
	
	private Table findTable (ArrayList<Table> tables, String tableName) {
		
		Iterator<Table>  tableIterator  = tables.iterator();
		Table thisTable = null;
		boolean found = false;	
		while (tableIterator.hasNext()) {
			thisTable = tableIterator.next();
			if (thisTable.getName().equals(tableName))
			{ found = true; break;}
		}
		if (found) 
		{ return thisTable; } 
		else {
			System.out.printf("TABLEMOVER: failed to find table %s \n", tableName);
			return null;
		}
	}
}

