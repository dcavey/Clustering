package structurer;

import java.util.ArrayList;
import java.util.Iterator;

public class ElementRelator {

	public ElementRelator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public  void relateBaseElements ( 	ArrayList<Table> tables,  ArrayList<Program> programs,
										ArrayList<TargetModule> ifsModules, ArrayList<TargetModule> lbbModules)
	{
		ElementRelatorData relatorData = new ElementRelatorData();
		
		/*
		relatorData.relateTablesToProgramsPart01(tables, programs);
		relatorData.relateTablesToProgramsPart02(tables, programs);
		relatorData.relateTablesToProgramsPart03(tables, programs);
		relatorData.relateTablesToProgramsPart04(tables, programs);
		relatorData.relateTablesToIFSModules(tables, ifsModules);
		*/
		DefineUnitTestData ( tables, programs, ifsModules,  lbbModules);

	}
	
	public  void  relateTableToProgram (ArrayList<Table> tables,  ArrayList<Program> programs, String tableName, String programName)
	{
		Program myProgram = findProgram( programs, programName);
		Table myTable  	  = findTable(tables, tableName);
		myProgram.addTable(myTable);
	}

	public void  relateTableToModule (ArrayList<Table> tables,  ArrayList<TargetModule> modules, String tableName, String moduleName)
	{
		TargetModule myModule = findModule( modules, moduleName);
		Table myTable  	  = findTable(tables, tableName);
		myModule.addTable(myTable);
	}

	
	public Table findTable (ArrayList<Table> tables, String tableName) {
		
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
			System.out.printf("failed to find table %s \n", tableName);
			return null;
		}
	}
	
	public Program findProgram (ArrayList<Program> programs, String programName) {
		Iterator<Program>  programIterator  = programs.iterator();
		Program thisProgram = null;
		boolean found = false;	
		while (programIterator.hasNext()) {
			thisProgram = programIterator.next();
			if (thisProgram.getName().equals(programName))
			{ found = true; break;}
		}
		if (found) 
		{ return thisProgram; } 
		else {
			System.out.printf("failed to find program %s \n", programName);
			return null;
		}
		
	}
	
	public TargetModule findModule (ArrayList<TargetModule> modules, String moduleName) {
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
			System.out.printf("failed to find module %s \n", moduleName);
			return null;
		}
	}
	
	private void DefineUnitTestData
		( 	ArrayList<Table> tables,  ArrayList<Program> programs,
				ArrayList<TargetModule> ifsModules, ArrayList<TargetModule> lbbModules)
	{
		relateTableToProgram (tables, programs, "Table01", "ProgramAAA");
		relateTableToProgram (tables, programs, "Table02", "ProgramAAA");
		relateTableToProgram (tables, programs, "Table03", "ProgramAAA");
		relateTableToProgram (tables, programs, "Table04", "ProgramAAA");
		
		relateTableToProgram (tables, programs, "Table03", "ProgramBBB");
		relateTableToProgram (tables, programs, "Table04", "ProgramBBB");
		
		relateTableToProgram (tables, programs, "Table01", "ProgramCCC");		
		relateTableToProgram (tables, programs, "Table02", "ProgramCCC");

		relateTableToModule (tables, ifsModules, "Table01" ,  "Module001");
		relateTableToModule (tables, ifsModules, "Table02" ,  "Module001");
		
		relateTableToModule (tables, ifsModules, "Table03" ,  "Module002");
		
		relateTableToModule (tables, ifsModules, "Table04" ,  "Module003");
		relateTableToModule (tables, ifsModules, "Table05" ,  "Module003");
	}
	
	
	
}
