package structurer;

import java.util.ArrayList;


public class ObjectModel {
	
	boolean createRealModel; // Create real model or create model with test data
	
	private ArrayList<Program> 		programs;
	private ArrayList<Table> 		tables;	
	private ArrayList<TargetModule> ifsModules;
	private ArrayList<TargetModule> lbbModules;
	private ArrayList<TargetModule> targetModules;
	
	
	public ObjectModel(boolean realModel) {
	
		super();
		this.createRealModel = realModel;
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
	
	public ArrayList<TargetModule> getTargetModules() {
		return targetModules;
	}

	public void setTargetModules(ArrayList<TargetModule> targetModules) {
		this.targetModules = targetModules;
	}

	public ArrayList<TargetModule> getIFSModules(){
		return ifsModules;
	}	
	
	public ArrayList<TargetModule> getLBBModules(){
		return lbbModules;
	}	

	private void populateObjectModel(  ArrayList<Table> tables,  ArrayList<Program> programs,
			ArrayList<TargetModule> ifsModules, ArrayList<TargetModule> lbbModules) 
	{
		ElementCreator creator = new ElementCreator(createRealModel);
		creator.createBaseElements(tables, programs, ifsModules, lbbModules);

		ElementRelator relator = new ElementRelator (createRealModel);
		relator.relateBaseElements (tables, programs, ifsModules, lbbModules);
		
	}
}
