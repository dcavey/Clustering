package structurer;

import java.util.ArrayList;
import java.util.Iterator;

import reporter.CSVWriter;


public class TargetModule {
	
	private ArrayList<Table> assignedTables;
	private ArrayList<TableUsedByProgram> tablesUsedByProgram;
	private ArrayList<Program> programs;
	private ArrayList<Interface> interfaces;
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
		interfaces = new ArrayList<Interface>();
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
	
	public ArrayList<Interface> getInterfaces() {
		return interfaces;
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
	
	public void addInterface (Interface intface) 
	{
		interfaces.add(intface);
	}
	
	public int getMatchingScoreForProgram(Program program, boolean printUse)
	{
		int	nrOfCommonTables = 0 ;
		int finalScore = 0 ;
		Table moduleTable ;
		boolean commonTable;
		boolean moduleHasTables = false;
		
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
					if(printUse){
						signalMatchingData (program, programTable, this);
					}
					commonTable = true;			
					
					break;			// DECISION: table used by Program and by Module
					
				}
				else
				{
					// System.out.printf ("Table <%s> is not common \n", moduleTable.getName()); 
				}
				
				
			} //  end of loop around the program tables
			
			if (commonTable) {				
				nrOfCommonTables ++;				
			}
		}   // end of loop around the module tables
		
		if (!moduleHasTables) {
			 System.out.printf ("Module <%s> has no tables at all \n", this.getName() );	
		}
		else
		{
			// Adapt score 
			finalScore = AdaptScore(nrOfCommonTables, this);
		}

		return finalScore;
	}
	
	private int AdaptScore(int nrOfCommonTables, TargetModule targetModule) {
		if(nrOfCommonTables != 0){

		if (targetModule.getName().toUpperCase().contains("TECHNICAL_KERNEL") ||
		    (targetModule.getName().toUpperCase().contains("MDM_MANAGEMENT")) ||
		    (targetModule.getName().toUpperCase().contains("UNUSED"))
		    )
		{
			return nrOfCommonTables;
		}
		else if (targetModule.getName().toUpperCase().contains("ACCOUNT_MANAGEMENT")) {
			return nrOfCommonTables +5 ;
		}
		else
		{
			return nrOfCommonTables +10 ;
		}
		}
		return nrOfCommonTables;
	}

	
	public void addProgramToModule (Program program)
	{
		if(!programs.contains(program)){
			programs.add(program);
		}
	}
	
	public void addInterfaceToModule (Interface intface)
	{
		if(!interfaces.contains(intface)){
			interfaces.add(intface);
		}
	}
	
	public void showComposition (boolean tocsv, boolean toStdOut) {
		
		Iterator<Table>  tableIterator = this.getAssignedTables().iterator();
		while (tableIterator.hasNext()) 
		{
			Table moduleTable = tableIterator.next();
			signalModuleTableCompositionLine (this.getName(),moduleTable.getName(), tocsv, toStdOut);
		} 

		Iterator<Program>  programIterator = this.getPrograms().iterator();
		while (programIterator.hasNext()) 
		{
			Program moduleProgram = programIterator.next();
			signalModuleProgramCompositionLine (this.getName(),moduleProgram.getName(), moduleProgram.getPgmType(), tocsv, toStdOut);
		}
		
		Iterator<Interface>  interfaceIterator = this.getInterfaces().iterator();
		while (interfaceIterator.hasNext()) 
		{
			Interface moduleInterface = interfaceIterator.next();
			signalModuleInterfaceCompositionLine (this.getName(),moduleInterface.getName(), moduleInterface.getProgramName(), tocsv, toStdOut);
		}
		
	}
	
	
	public void showTableUsageAcrossModules (boolean showAll, boolean tocsv, boolean toStdOut) {
		
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
						signalTableUsageAcrossModules (this, moduleProgram, programTable, external = true, tocsv, toStdOut);
				} else
				{
					signalTableUsageAcrossModules (this, moduleProgram, programTable, external = false, tocsv, toStdOut);
				}
			}
		} 
	}
	
	public boolean usesExternalTable (Table externalTable) {
		boolean used = false;
		String crud;
		int countExternalUsage = 0;
		Iterator<Program>  programIterator = this.getPrograms().iterator();
		while (programIterator.hasNext()) 
		{
			Program moduleProgram = programIterator.next();
			crud = moduleProgram.getCRUDforTable(externalTable);
			if ( ! crud.equals(""))
			{
//				 System.out.printf ("Table:%s from module:%s is used by module:%s through %s by program:%s \n",
//						 externalTable.getName(), externalTable.getAssignedModule().getName(), 
//						this.getName(), crud, moduleProgram.getPgmNameAndType());
//				
				countExternalUsage++;
			}
		} 
		if ( countExternalUsage > 0) {used = true; }
		
		return used;
	}

	
	
	
	public void signalMatchingData (Program program, Table programTable, TargetModule module) 
	{
		System.out.printf ("Program=%s uses %s module.table=%s.%s for %s \n",  program.getPgmNameAndType(),module.getType(), 
				module.getName(),  programTable.getName(), program.getCRUDforTable(programTable) );
	}
		
	// overridden by specific module types (e.g. for IFS modules and for LBB modules)
	public void signalModuleTableCompositionLine ( String moduleName, String tableName, boolean tocsv, boolean toStdOut)
	{
		if (toStdOut) {
			System.out.printf ( this.getType() + " module=%s contains table=%s \n", moduleName, tableName);
		}
		if(tocsv){
			CSVWriter writer = new CSVWriter();
			String lineToWrite = this.getType() + " module;" + moduleName + ";contains;table;" + tableName;
			writer.writeLineToFile(Constants.CSV_CONTAINS, lineToWrite);
		}
	}
	
	public void signalModuleProgramCompositionLine (String moduleName, String programName, String pgmType, boolean tocsv, boolean toStdOut)
	{
		if (toStdOut) {
			System.out.printf ( this.getType() + " module=%s contains [%s]program=%s \n", moduleName, pgmType, programName);
		}
		if(tocsv){
			CSVWriter writer = new CSVWriter();
			String lineToWrite = this.getType() +  " module;" + moduleName + ";contains;["+ pgmType +"]program;" + programName;
			writer.writeLineToFile(Constants.CSV_CONTAINS, lineToWrite);
		}
	}
	
	public void signalModuleInterfaceCompositionLine (String moduleName, String interfaceName, String programName, boolean tocsv, boolean toStdOut)
	{
		if (toStdOut) {
			System.out.printf ( this.getType() + " module=%S contains [%s]interface=%s \n", moduleName, interfaceName, programName);
		}
		if(tocsv){
			CSVWriter writer = new CSVWriter();
			String lineToWrite = this.getType() +  " module;" + moduleName + ";contains;["+ interfaceName + "+]interface;" + programName;
			writer.writeLineToFile(Constants.CSV_CONTAINS, lineToWrite);
		}
	}
	
	public void signalTableUsageAcrossModules (TargetModule module, Program program, Table table, boolean external, boolean tocsv, boolean toStdOut)
	{
		try {
		{
			String usageType;
			if (external) 
			{usageType = "external"; 	}
			else {usageType = "internal";}
			
			if (toStdOut) {
				System.out.printf ("%s module.program=%s.%s uses %s %s module.table=%s.%s for %s \n",  
					module.getType(),
					module.getName(), program.getPgmNameAndType(),  usageType, table.getAssignedModule().getType(),
					table.getAssignedModule().getName() , 
					table.getName(),   program.getCRUDforTable (table));
			}
// Output to csv-file  as well
			if(tocsv){
				String module4NameProgram;
				String module4nameTable;
				String newUsageType;
				
				module4NameProgram= getModule4Name(module.getName());
				module4nameTable = getModule4Name(table.getAssignedModule().getName());
				
				if (module4NameProgram.equals(module4nameTable) ) {
					newUsageType = usageType.concat("_equal_M4");
				} else
				{
					newUsageType = usageType;
				}
				
				String line = module.getType() + ";module;program;" + getModule4Name(module.getName()) + ";"
							+ module.getName() + ";" + program.getName() +";" + program.getPgmType() 
							+ ";uses;" + table.getAssignedModule().getType() + ";"+  newUsageType +";module;table;" + getModule4Name(table.getAssignedModule().getName()) +
							";" + table.getAssignedModule().getName() 
							+ ";" + table.getName() + ";for;" + program.getCRUDforTable (table);
				CSVWriter writer = new CSVWriter();
				writer.writeLineToFile(Constants.CSV_USED, line);
			}
	    }	
		} catch (Exception e) {// Catch exception if any
			System.out.printf("Error to place table %s in a module \n", table.getName()   );
		}
	}

	private String getModule4Name(String name) {
		String cb = "Core_Banking";
		if (name.equals("MDM_Management") ||
				name.equals("Account_Management") ||
				name.equals("TECHNICAL_KERNEL") ||
				name.equals("Finance") ||
				name.equals("UNUSED"))
			return cb;
		return name;
	}
	
	
}
