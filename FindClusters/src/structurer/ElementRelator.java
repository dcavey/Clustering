package structurer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import resources.LocateResource;

public class ElementRelator {

	private static final String TABLE2PROGRAM_XREF = "xref_table_program.csv";
	private static final String TABLE2MODULE_XREF = "xref_table_module.csv";

	private class TableProgramXref{
		String tableName;
		String programName;
		public TableProgramXref(String tableName, String programName) {
			super();
			this.tableName = tableName;
			this.programName = programName;
		}
	}
	
	private class TableModuleXref{
		String tableName;
		String moduleName;
		public TableModuleXref(String tableName, String moduleName) {
			super();
			this.tableName = tableName;
			this.moduleName = moduleName;
		}
	}
	
	
	
	public ElementRelator() {
		super();
	}

	public  void relateBaseElements ( 	ArrayList<Table> tables,  ArrayList<Program> programs,
										ArrayList<TargetModule> ifsModules, ArrayList<TargetModule> lbbModules)
	{
		ElementRelator relator = new ElementRelator();
		
		// setup relationships between Tables and Programs (from generated Table/Program XREF file)
	
		ArrayList<TableProgramXref> tp_xrefs = readTableProgramXrefs();
		TableProgramXref tp_Xref;
		
		Iterator<TableProgramXref>  tp_xrefIterator  = tp_xrefs.iterator();
		while (tp_xrefIterator.hasNext()) {
			tp_Xref = tp_xrefIterator.next();
			relator.relateTableToProgram (tables, programs, tp_Xref.tableName, tp_Xref.programName);
		}
		
		// setup realtionships between Tables and target Modules (from IFS SME defined Table/Module XREF file)
		ArrayList<TableModuleXref> tm_xrefs = readTableModuleXrefs();
		TableModuleXref tm_Xref;
		
		Iterator<TableModuleXref>  tm_xrefIterator  = tm_xrefs.iterator();
		while (tm_xrefIterator.hasNext()) {
			tm_Xref = tm_xrefIterator.next();
			relator.relateTableToModule (tables, ifsModules, tm_Xref.tableName, tm_Xref.moduleName);
		}
		
		
		// DefineUnitTestData ( tables, programs, ifsModules,  lbbModules);
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
		myModule.addAssignedTable(myTable);
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
	
	
	private ArrayList<TableProgramXref> readTableProgramXrefs() {

		ArrayList<TableProgramXref> outputList = new ArrayList<TableProgramXref>();
		try {
			// Open the file
			FileInputStream fstream = new FileInputStream(LocateResource.getResource(TABLE2PROGRAM_XREF));
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				TableProgramXref currentXref = new TableProgramXref(output[0], output[2]);
				outputList.add(currentXref);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return outputList;
	}

	private ArrayList<TableModuleXref> readTableModuleXrefs() {

		ArrayList<TableModuleXref> outputList = new ArrayList<TableModuleXref>();
		try {
			// Open the file
			FileInputStream fstream = new FileInputStream(LocateResource.getResource(TABLE2MODULE_XREF));
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				TableModuleXref currentXref = new TableModuleXref(output[0], output[2]);
				outputList.add(currentXref);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return outputList;
	}


	
	
	
	
}
