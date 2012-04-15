package structurer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ElementRelator {

	private boolean fullModel;
	
	public ElementRelator(boolean fullModel) {
		super();
		this.fullModel = fullModel;;
	}

	
	public void relateImplementationModelInternally (ArrayList<Table> tables,  ArrayList<Program> programs)
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
		} else
		{
			RelateImplementationModelInternallyForTestData ( tables, programs);
		}
	}
	
	public HashMap<Program, ArrayList<Program>> getGlobalLogicToPrograms(ArrayList<Program> programs) {
		HashMap<Program, ArrayList<Program>> glProgs = new HashMap<Program, ArrayList<Program>>();
		try {
			// Open the file
			InputStream fstream = new FileInputStream(Constants.GL2PROGRAM_XREF);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				/*
				 * index 
				 * 	1 = Global Logic Name 
				 * 	3 = Program Name
				 * 
				 */
				Program prog = findProgram(programs, output[3]);
				Program glProg = findProgram(programs, output[1]);
				ArrayList<Program> listProgs;
				if(glProgs.containsKey(glProg)){
					listProgs = glProgs.get(glProg);
				} else {
					listProgs = new ArrayList<Program>();
				}
				listProgs.add(prog);
				glProgs.put(glProg, listProgs);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		return glProgs;
	}


	public void relateImplementationToPhysicalModel (ArrayList<Table> tables, 
										ArrayList<TargetModule> physicalModules)
	{
		if (this.fullModel) {
			// setup relationships between Tables and target Modules (from IFS SME defined Table/Module XREF file)
			ArrayList<TableModuleXref> tm_xrefs = readTablePhysicalModuleXrefs();
			TableModuleXref tm_Xref;
		
			Iterator<TableModuleXref>  tm_xrefIterator  = tm_xrefs.iterator();
			while (tm_xrefIterator.hasNext()) {
				tm_Xref = tm_xrefIterator.next();
				this.relateTableToModule (tables, physicalModules, tm_Xref);
			} 
		
		} else
		{
			relateImplementationToPhysicalModelForTestData ( tables, physicalModules);
		}
		
	}
	
	public void relateImplementationToLogicalModel (ArrayList<Table> tables, 
			ArrayList<TargetModule> logicalModules)
	{
		if (this.fullModel) {
			// setup relationships between Tables and target Modules (from IFS SME defined Table/Module XREF file)
			ArrayList<TableModuleXref> tm_xrefs = readTableLogicalModuleXrefs();
			TableModuleXref tm_Xref;
		
			Iterator<TableModuleXref>  tm_xrefIterator  = tm_xrefs.iterator();
			while (tm_xrefIterator.hasNext()) {
				tm_Xref = tm_xrefIterator.next();
				this.relateTableToModule (tables, logicalModules, tm_Xref);
			} 
		} else
		{
			relateImplementationToLogicalModelForTestData ( tables, logicalModules);
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

	public void  relateTableToModule (ArrayList<Table> tables,  ArrayList<TargetModule> modules, TableModuleXref xref)
	{
		try {
			TargetModule myModule = findModule( modules, xref.getModuleName());
			Table myTable  	  = findTable(tables, xref.getTableName());
			// setup relationship in two ways
			myModule.addAssignedTable(myTable);
			myTable.setAssignedModule(myModule);
		} catch (Exception e) {// Catch exception if any
			System.out.printf("Error:  failed to relate table %s to module %s \n", xref.getTableName(), xref.getModuleName());
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
			InputStream fstream = new FileInputStream(Constants.TABLE2PROGRAM_XREF);
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
			e.printStackTrace();
		}
		return outputList;
	}

	private ArrayList<TableModuleXref> readTablePhysicalModuleXrefs() {

		TableModuleXref currentXref;
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
				currentXref = new TableModuleXref(output[0], output[2],"","","","");  					
				outputList.add(currentXref);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		return outputList;
	}

	private ArrayList<TableModuleXref> readTableLogicalModuleXrefs() {

		TableModuleXref currentXref;
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
				currentXref = new TableModuleXref(output[0], output[1],"","","","");  					
				outputList.add(currentXref);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		return outputList;
	}

	
	private void RelateImplementationModelInternallyForTestData 
	( 	ArrayList<Table> tables,  ArrayList<Program> programs)
	{
		relateTableToProgram (tables, programs, new TableProgramXref("Table01", "ProgramAAA", "C", "R", "U", ""));
		relateTableToProgram (tables, programs, new TableProgramXref("Table02", "ProgramAAA", "C", "R", "", ""));
		relateTableToProgram (tables, programs, new TableProgramXref("Table03", "ProgramAAA", "", "R", "", "D"));
		relateTableToProgram (tables, programs, new TableProgramXref("Table04", "ProgramAAA", "", "R", "U", "D"));
	
		relateTableToProgram (tables, programs, new TableProgramXref("Table03", "ProgramBBB", "C", "R", "", "D"));
		relateTableToProgram (tables, programs, new TableProgramXref("Table04", "ProgramBBB", "C", "R", "", "D"));
		relateTableToProgram (tables, programs, new TableProgramXref("Table05", "ProgramBBB", "", "R", "U", ""));	
	
		relateTableToProgram (tables, programs, new TableProgramXref("Table01", "ProgramCCC", "C", "R", "U", "D"));
		relateTableToProgram (tables, programs, new TableProgramXref("Table02", "ProgramCCC", "C", "R", "U", "D"));
	}
	
	public void relateImplementationToPhysicalModelForTestData (ArrayList<Table> tables,  
			ArrayList<TargetModule> physicalModules)
	{
		relateTableToModule (tables, physicalModules, new TableModuleXref("Table01","ModuleIFS_AA", "","","","")) ;
		relateTableToModule (tables, physicalModules, new TableModuleXref("Table02","ModuleIFS_AA", "","","","")) ;
	
		relateTableToModule (tables, physicalModules, new TableModuleXref("Table03","ModuleIFS_BB", "","","","")) ;
	
		relateTableToModule (tables, physicalModules, new TableModuleXref("Table04","ModuleIFS_CC", "","","","")) ; 
		relateTableToModule (tables, physicalModules, new TableModuleXref("Table05","ModuleIFS_CC", "","","","")) ;
	}
	
	public void relateImplementationToLogicalModelForTestData (ArrayList<Table> tables,  
			ArrayList<TargetModule> logicalModules)
	{
		relateTableToModule (tables, logicalModules, new TableModuleXref("Table01", "ModuleLBB_AA","","","","")) ;
		relateTableToModule (tables, logicalModules, new TableModuleXref("Table02", "ModuleLBB_AA","","","","")) ;
		
		relateTableToModule (tables, logicalModules, new TableModuleXref("Table03","ModuleLBB_BB","","","","")) ;
		
		relateTableToModule (tables, logicalModules, new TableModuleXref("Table04", "ModuleLBB_CC","","","","")) ; 
		relateTableToModule (tables, logicalModules, new TableModuleXref("Table05","ModuleLBB_CC","","","","")) ;
	}
	
	
}
