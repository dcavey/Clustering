package structurer;

import java.util.ArrayList;


public class ObjectModel {
	
	private ArrayList<Program> 		programs;
	private ArrayList<Table> 		tables;	
	private ArrayList<TargetModule> ifsModules;
	private ArrayList<TargetModule> lbbModules;
	
	
	public ObjectModel() {
	
		super();
		this.programs = new ArrayList<Program>();
		this.tables = new ArrayList<Table>();
		this.ifsModules = new ArrayList<TargetModule>();
		this.lbbModules = new ArrayList<TargetModule>();
		populateObjectModel(tables, programs, ifsModules, lbbModules);
	}

	public ArrayList<Table>  getTables() {
		return tables;
	}
	
	public ArrayList<Program> getPrograms(){
		return programs;
	}
	
	public ArrayList<TargetModule> getIFSModules(){
		return ifsModules;
	}	

	private void populateObjectModel(  ArrayList<Table> tables,  ArrayList<Program> programs,
			ArrayList<TargetModule> ifsModules, ArrayList<TargetModule> lbbModules) 
	{
		boolean createRealModel = true; // Create real model or create model with test data
		
		ElementCreator creator = new ElementCreator(createRealModel);
		creator.createBaseElements(tables, programs, ifsModules, lbbModules);

		ElementRelator relator = new ElementRelator (createRealModel);
		relator.relateBaseElements (tables, programs, ifsModules, lbbModules);
		
	}
}
