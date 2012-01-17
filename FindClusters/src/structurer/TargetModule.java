package structurer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;


public class TargetModule {
	
	private ArrayList<Table> assignedTables;
	private ArrayList<TableUsedByProgram> tablesUsedByProgram;
	private ArrayList<Program> programs;
	private String name;
	
	
	private class TableUsedByProgram {
		String		otherModuleName;
		String		otherProgramName;
		String		myProgramName;
		String		commonTableName;
	}
	
	public TargetModule(String name) {
		super();
		this.name = name;
		assignedTables = new ArrayList<Table>();
		tablesUsedByProgram = new ArrayList<TableUsedByProgram>();
		programs = new ArrayList<Program>();
	}

	public ArrayList<Table> getAssignedTables() {
		return assignedTables;
	}
	
	public ArrayList<TableUsedByProgram> getTablesUsedByPrograms() {
		return tablesUsedByProgram;
	}
	public ArrayList<Program> getPrograms() {
		return programs;
	}

	public String getName() {
		return name;
	}
	
	public String getType() {
		String type = "GEN";
		return type;
	}
	
	public void addAssignedTable (Table table) {
		assignedTables.add(table);
	}
	
	
	public void addTableUsedByProgram (TargetModule otherModule, Program otherProgram, Program myProgram, Table commonTable) {
		
		boolean exists = false;
		Iterator<TableUsedByProgram>  iterator  = this.tablesUsedByProgram.iterator();

		while (iterator.hasNext()) 
		{
			TableUsedByProgram tableUsedByProgram= iterator.next();
			
			if (	tableUsedByProgram.otherModuleName.equals(otherModule.getName()) &&
					tableUsedByProgram.otherProgramName.equals(otherProgram.getName()) &&
					tableUsedByProgram.myProgramName.equals(myProgram.getName()) &&
					tableUsedByProgram.commonTableName.equals(commonTable.getName()) ) {
				exists = true;
				break;
			}
			
		}   // end of loop around the module tables
			
		if (!exists) {
			TableUsedByProgram tableUsed = new TableUsedByProgram();
			tableUsed.otherModuleName = otherModule.getName();
			tableUsed.otherProgramName = otherProgram.getName();
			tableUsed.commonTableName = commonTable.getName();
			tableUsed.myProgramName = myProgram.getName();
			
			tablesUsedByProgram.add(tableUsed);			
		}
	}
	
	
	public void showTablesSharedWithOtherModules () {

		Iterator<TableUsedByProgram>  iterator  = this.tablesUsedByProgram.iterator();
		while (iterator.hasNext()) 
		{
			TableUsedByProgram tableUsedByProgram= iterator.next();	
			
			 System.out.printf ("M:%s P:%s has common table:%s with M:%s, P:%s \n", 
					 this.name,
					 tableUsedByProgram.myProgramName,
					 tableUsedByProgram.commonTableName,
					 tableUsedByProgram.otherModuleName,
					 tableUsedByProgram.otherProgramName
					   ); 
			 					
		}   // end of loop around the module tables			
	}
	
	public void addProgram (Program program) 
	{
		programs.add(program);
	}
	
	public int getMatchingScoreForProgram(Program program)
	{
		int	nrOfCommonTables = 0 ;
		Table moduleTable ;
		boolean commonTable;
		boolean moduleHasTables = false;
		boolean readingParamTable = false;
		
		// System.out.printf ("Considering if module <%s> is fitting for program <%s> \n", this.getName(), program.getName()); 

		Iterator<Table>  moduleTableIterator  = assignedTables.iterator();

		while (moduleTableIterator.hasNext()) 
		{
			moduleHasTables = true;
			moduleTable =  moduleTableIterator.next();
			
			// System.out.printf ("Start checking if module table <%s> is common \n", moduleTable.getName()); 

			commonTable = false;
			
			Iterator<Table>  programTableIterator = program.getTables().iterator();
			while (programTableIterator.hasNext()) 
			{
				Table programTable =  programTableIterator.next();

				// System.out.printf ("Trying to match program table <%s> to module table <%s> \n", programTable.getName(), moduleTable.getName() ); 

				if (programTable.getName().equals (moduleTable.getName()))
				{
					signalMatchingData (program, programTable, this);
					commonTable = true;					
					readingParamTable = readingOfParameterTable(this, program, programTable);
					
					break;			// DECISION: table used by Program and by Module
					
				}
				else
				{
					// System.out.printf ("Table <%s> is not common \n", moduleTable.getName()); 
				}
				
				
			} //  end of loop around the program tables
			
			if (commonTable) {
				
				if ( readingParamTable ) {	  } // ParameterFiles are not counted 
				{	
					nrOfCommonTables ++;				
				}
			}
		}   // end of loop around the module tables
		
		if (!moduleHasTables) {
			// System.out.printf ("Module <%s> has no tables at all \n", this.getName() );	
		}

		return nrOfCommonTables;
	}
	
	private boolean readingOfParameterTable (TargetModule module, Program program, Table table)
	{
		if ( module.getPrograms().equals ("TECHNICAL_KERNEL") && program.getCRUDforTable(table).contains("R") ) {
			System.out.printf ("Program=%s reads parameter table=%s \n", program.getName(), table.getName() );	 
		}	
		
		return false;
	}
	
	public void addProgramToModule (Program program)
	{
		programs.add(program);		// TODO check for program already added
	}
	
	public void showComposition () {
		
		Iterator<Table>  tableIterator = this.getAssignedTables().iterator();
		while (tableIterator.hasNext()) 
		{
			Table moduleTable = tableIterator.next();
			signalModuleTableCompositionLine (this.getName(),moduleTable.getName());
		} 

		Iterator<Program>  programIterator = this.getPrograms().iterator();
		while (programIterator.hasNext()) 
		{
			Program moduleProgram = programIterator.next();
			signalModuleProgramCompositionLine (this.getName(),moduleProgram.getName(), moduleProgram.getPgmType());
		} 
		
	}
	
	
	public void showTableUsageAcrossModules (boolean showAll) {
		
		Iterator<Program>  programIterator = this.getPrograms().iterator();
		while (programIterator.hasNext()) 
		{
			Program moduleProgram = programIterator.next();
			
			// find all data usage that is not based on data in this table
			
			Iterator<Table>  programTableIterator = moduleProgram.getTables().iterator();
			while (programTableIterator.hasNext()) 
			{
				boolean external;
				Table programTable = programTableIterator.next();
				
				if (! programTable.isContainedInTableArray(this.assignedTables)) {
						signalTableUsageAcrossModules (this, moduleProgram, programTable, external = true);
				} else
				{
					signalTableUsageAcrossModules (this, moduleProgram, programTable, external = false);
				}
			}
		} 
	}
	
	
	
	public void signalMatchingData (Program program, Table programTable, TargetModule module) 
	{
		System.out.printf ("Program=%s uses module.table=%s.%s for %s \n",  program.getPgmNameAndType(),module.getName(), programTable.getName(), 
																		  program.getCRUDforTable(programTable) );
	}
		
	// overridden by specific module types (e.g. for IFS modules and for LBB modules)
	public void signalModuleTableCompositionLine ( String moduleName, String tableName)
	{
		System.out.printf ("GEN Module=%s contains table=%s \n", moduleName, tableName);
	}
	
	public void signalModuleProgramCompositionLine (String moduleName, String programName, String pgmType)
	{
		System.out.printf ("GEN Module=%s contains [%s]program=%s \n", moduleName, pgmType, programName);  
	}
	public void signalTableUsageAcrossModules (TargetModule module, Program program, Table table, boolean external)
	{
		try {
		{
			String usageType;
			if (external) 
			{usageType = "external"; 	}
			else {usageType = "internal";}
			
			System.out.printf ("module.program=%s.%s uses %s module.table=%s.%s for %s \n",  
					module.getName(), program.getPgmNameAndType(),  usageType, table.getAssignedModule().getName() , 
					table.getName(),   program.getCRUDforTable (table));
	    }	
		} catch (Exception e) {// Catch exception if any
			System.out.printf("Error to place table %s in a module \n", table.getName()   );
		}
	}
	
	
}
