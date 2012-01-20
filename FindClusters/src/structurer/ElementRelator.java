package structurer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class ElementRelator {

	private boolean fullModel;
	

	

	
	public ElementRelator(boolean fullModel) {
		super();
		this.fullModel = fullModel;
	}

	public  void relateBaseElements ( 	ArrayList<Table> tables,  ArrayList<Program> programs,
										ArrayList<TargetModule> ifsModules, ArrayList<TargetModule> lbbModules)
	{		
		if (this.fullModel) {
			
		// setup relationships between Tables and Programs (from generated Table/Program XREF file)
	
		ArrayList<TableProgramXref> tp_xrefs = readTableProgramXrefs();
		TableProgramXref tp_Xref;
		
		Iterator<TableProgramXref>  tp_xrefIterator  = tp_xrefs.iterator();
		while (tp_xrefIterator.hasNext()) {
			tp_Xref = tp_xrefIterator.next();
			this.relateTableToProgram (tables, programs, tp_Xref);
		}
		
		// setup relationships between Tables and target Modules (from IFS SME defined Table/Module XREF file)
		ArrayList<TableModuleXref> tm_xrefs = readTableModuleXrefs();
		TableModuleXref tm_Xref;
		
		Iterator<TableModuleXref>  tm_xrefIterator  = tm_xrefs.iterator();
		while (tm_xrefIterator.hasNext()) {
			tm_Xref = tm_xrefIterator.next();
			this.relateTableToModule (tables, ifsModules, tm_Xref.physModuleName, tm_Xref);
			this.relateTableToModule (tables, lbbModules, tm_Xref.logModuleName, tm_Xref);
		} 
		
		} else
		{
			DefineUnitTestData ( tables, programs, ifsModules,  lbbModules);
		}	
	}
	
	public  void  relateTableToProgram (ArrayList<Table> tables,  ArrayList<Program> programs, TableProgramXref xref)
	{
		try {
			Program myProgram = findProgram( programs, xref.programName);
			Table myTable  	  = findTable(tables, xref.tableName);
			myProgram.addTable(myTable);
			myProgram.addTableProgramXref(xref);
		} catch (Exception e) {// Catch exception if any
		System.out.printf("Error:  failed to relate program %s to table %s \n", xref.programName, xref.tableName);
		}
	}

	public void  relateTableToModule (ArrayList<Table> tables,  ArrayList<TargetModule> modules, String moduleName, TableModuleXref xref)
	{
		try {
			TargetModule myModule = findModule( modules, moduleName);
			Table myTable  	  = findTable(tables, xref.tableName);
			// setup relationship in two ways
			myModule.addAssignedTable(myTable);
			myTable.setAssignedModule(myModule);
		} catch (Exception e) {// Catch exception if any
			System.out.printf("Error:  failed to relate table %s to module %s \n", xref.tableName, xref.physModuleName);
		}
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
			System.out.printf("RELATOR: failed to find module %s \n", moduleName);
			return null;
		}
	}
	
	
	private ArrayList<TableProgramXref> readTableProgramXrefs() {

		ArrayList<TableProgramXref> outputList = new ArrayList<TableProgramXref>();
		try {
			// Open the file
			InputStream fstream =this.getClass().getResourceAsStream(Constants.TABLE2PROGRAM_XREF);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				/* column index in xref_table_program.csv
				 *  0 = table
				 *  1 = C 		(Create from CRUD)
				 *  2 = R 		(Read from CRUD)
				 *  3 = U 		(Update from CRUD)
				 *  4 = D 		(Delete from CRUD) 
				 *  5 = program 
				 */
				TableProgramXref currentXref = new TableProgramXref(output[0],output[5],
						output[1],output[2],output[3],output[4] );
				outputList.add(currentXref);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.out.println("Error: " + e.getMessage());
		}
		return outputList;
	}

	private ArrayList<TableModuleXref> readTableModuleXrefs() {

		int		counter = 0;
		ArrayList<TableModuleXref> outputList = new ArrayList<TableModuleXref>();
		try {
			// Open the file
			InputStream fstream = this.getClass().getResourceAsStream(Constants.TABLE2MODULE_XREF);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();			// BEWARE: Read of header line here. Make sure the input file has a header line!!! 
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				// index: 0 = table, 1 = LBB module, 2= IFS Module
				TableModuleXref currentXref = new TableModuleXref(output[0], output[1], output[2],"","","","");  
				outputList.add(currentXref);
				counter++;
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.out.println("Error: " + e.getMessage());
		}
		return outputList;
	}

	private void DefineUnitTestData
	( 	ArrayList<Table> tables,  ArrayList<Program> programs,
			ArrayList<TargetModule> ifsModules, ArrayList<TargetModule> lbbModules)
	{
		final boolean read = true; boolean delete = true; boolean update = true; boolean create = true;
		final boolean noRead = false; boolean noDelete = true; boolean noUpdate = true; boolean noCreate = true;		
	
		
		relateTableToProgram (tables, programs, new TableProgramXref("Table01", "ProgramAAA", "C", "R", "U", ""));
		relateTableToProgram (tables, programs, new TableProgramXref("Table02", "ProgramAAA", "C", "R", "", ""));
		relateTableToProgram (tables, programs, new TableProgramXref("Table03", "ProgramAAA", "", "R", "", "D"));
		relateTableToProgram (tables, programs, new TableProgramXref("Table04", "ProgramAAA", "", "R", "U", "D"));
	
		relateTableToProgram (tables, programs, new TableProgramXref("Table03", "ProgramBBB", "C", "R", "", "D"));
		relateTableToProgram (tables, programs, new TableProgramXref("Table04", "ProgramBBB", "C", "R", "", "D"));
		relateTableToProgram (tables, programs, new TableProgramXref("Table05", "ProgramBBB", "", "R", "U", ""));	
	
		relateTableToProgram (tables, programs, new TableProgramXref("Table01", "ProgramCCC", "C", "R", "U", "D"));
		relateTableToProgram (tables, programs, new TableProgramXref("Table02", "ProgramCCC", "C", "R", "U", "D"));
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/*
		relateTableToModule (tables, ifsModules, new TableModuleXref("Table01","IFSModule01", "Module101","","","","")) ;
		relateTableToModule (tables, ifsModules, new TableModuleXref("Table02","IFSModule01", "Module101","","","","")) ;
	
		relateTableToModule (tables, ifsModules, new TableModuleXref("Table03","IFSModule02", "Module102","","","","")) ;
	
		relateTableToModule (tables, ifsModules, new TableModuleXref("Table04","IFSModule003","Module103","","","","")) ; 
		relateTableToModule (tables, ifsModules, new TableModuleXref("Table05","IFSModule003","Module103","","","","")) ;
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
		relateTableToModule (tables, lbbModules, new TableModuleXref("Table01","LBBModule01", "Module201","","","","")) ;
		relateTableToModule (tables, lbbModules, new TableModuleXref("Table02","LBBModule01", "Module201","","","","")) ;
		
		relateTableToModule (tables, lbbModules, new TableModuleXref("Table03","LBBModule02", "Module202","","","","")) ;
		
		relateTableToModule (tables, lbbModules, new TableModuleXref("Table04","LBBModule003","Module203","","","","")) ; 
		relateTableToModule (tables, lbbModules, new TableModuleXref("Table05","LBBModule003","Module203","","","","")) ;
	*/
	}	
	
}
