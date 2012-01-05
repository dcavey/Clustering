package structurer;
import java.util.ArrayList;
import java.util.Iterator;


public class Table {

	private String name;
	private ArrayList<Program> programs;
	private TargetModule assignedModule;
	
	public Table(String name) {
		super();
		this.name = name;
		programs = new ArrayList<Program>();
	}
		
	public void addProgram (Program program) 
	{
		programs.add(program);
	}
	
	
	public void setAssignedModule(TargetModule assignedModule) {
		this.assignedModule = assignedModule;
	}

	public TargetModule getAssignedModule () 
	{
		return assignedModule;		// we assume a table can be assigned to only one module 
	}

	
	public String getName() 
	{
		return name;
	}
	
	
	public boolean isContainedInTableArray (ArrayList<Table> tables) {
		boolean found = false;
		Iterator<Table>  tableIterator  = tables.iterator();
		
		while (tableIterator.hasNext()) {
			Table thisTable = tableIterator.next();
			if (thisTable.getName().equals(this.name))
			{ found = true; break;}
		}
		return found;		
	}

}
