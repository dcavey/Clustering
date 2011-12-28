package structurer;

import java.util.ArrayList;
import java.util.Iterator;


public class TargetModule {
	
	private ArrayList<Table> tables;
	private ArrayList<Program> programs;
	private String name;
	
	public TargetModule(String name) {
		super();
		this.name = name;
		tables = new ArrayList<Table>();
		programs = new ArrayList<Program>();
	}

	private ArrayList<Table> getTables() {
		return tables;
	}
	
	private ArrayList<Program> getPrograms() {
		return programs;
	}

	public String getName() {
		return name;
	}
	
	public String getType() {
		String type = "GEN";
		return type;
	}
	
	public void addTable (Table table) {
		tables.add(table);
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
		
		// System.out.printf ("Considering if module <%s> is fitting for program <%s> \n", this.getName(), program.getName()); 

		Iterator<Table>  moduleTableIterator  = tables.iterator();

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
					SignalMatchingData (program, programTable, this);
					
					commonTable = true;
					break;			// DECISION: table used by Program and by Module
				}
				else
				{
					// System.out.printf ("Table <%s> is not common \n", moduleTable.getName()); 
				}
			} //  end of loop around the program tables
			
			if (commonTable) 
			{ 
				nrOfCommonTables ++;
			}
		}   // end of loop around the module tables
		
		if (!moduleHasTables) {
			// System.out.printf ("Module <%s> has no tables at all \n", this.getName() );	
		}

		
		return nrOfCommonTables;
	}
	
	public void addProgramToModule (Program program)
	{
		programs.add(program);		// TODO check for program already added
	}
	
	public void showComposition () {
		
		Iterator<Table>  tableIterator = this.getTables().iterator();
		while (tableIterator.hasNext()) 
		{
			Table moduleTable = tableIterator.next();
			showModuleTableCompositionLine (this.getName(),moduleTable.getName());
		} 

		Iterator<Program>  programIterator = this.getPrograms().iterator();
		while (programIterator.hasNext()) 
		{
			Program moduleProgram = programIterator.next();
			showModuleProgramCompositionLine (this.getName(),moduleProgram.getName(), moduleProgram.getPgmType());
		} 
		
	}
	
	public void SignalMatchingData (Program program, Table programTable, TargetModule module) 
	{
		System.out.printf ("[%s]Program=%s uses table=%s of module=%s\n", program.getPgmType(), program.getName(), programTable.getName(), module.getName() );
	}
	
	
	
	// overridden by specific module types (e.g. for IFS modules and for LBB modules)
	public void showModuleTableCompositionLine ( String moduleName, String tableName)
	{
		System.out.printf ("GEN Module=%s contains table=%s \n", moduleName, tableName);
	}
	
	public void showModuleProgramCompositionLine (String moduleName, String programName, String pgmType)
	{
		System.out.printf ("GEN Module=%s contains [%s]program=%s \n", moduleName, pgmType, programName);  
	}
	
}
