package structurer;

import java.util.ArrayList;


public class ObjectModel {
	
	
	boolean realModel;
	boolean physicalModel;
	boolean logicalModel;
	
	private ArrayList<Program> 		programs;
	private ArrayList<Table> 		tables;	
	private ArrayList<Interface>	interfaces;
	private ArrayList<TargetModule> ifsModules;
	private ArrayList<TargetModule> lbbModules;
	private ArrayList<TargetModule> targetModules;
	private ElementCreator 			creator;
	private ElementRelator 			relator;
	
	
	public ObjectModel(boolean realModel) {
	
		super();
		this.realModel = realModel;
		this.logicalModel = logicalModel;
		this.physicalModel = physicalModel;
		this.programs = new ArrayList<Program>();
		this.tables = new ArrayList<Table>();
		this.interfaces = new ArrayList<Interface>();
		this.ifsModules = new ArrayList<TargetModule>();
		this.lbbModules = new ArrayList<TargetModule>();
		this.creator = new ElementCreator(realModel);
		this.relator = new ElementRelator(realModel);
	}

	public void createImplementationModel () {
		creator.createBaseElementsImplementation (tables,programs,interfaces);
		relator.relateImplementationModelInternally(tables, programs);
	}
	
	public void CreatePhysicalModel() {
		creator.createBaseElementsPhysicalModel(ifsModules); 
		relator.relateImplementationToPhysicalModel(tables, ifsModules);	
	}
	
	public void CreateLogicalModel() {
		creator.createBaseElementsLogicalModel(lbbModules); 
		relator.relateImplementationToLogicalModel(tables, lbbModules);
	}
	
	
	public ArrayList<Table>  getTables() {
		return tables;
	}
	
	public ArrayList<Program> getPrograms(){
		return programs;
	}
	
	public ArrayList<Interface> getInterfaces(){
		return interfaces;
	}
	
	public ArrayList<TargetModule> getTargetModules() {
		return targetModules;
	}

	public void setTargetModules(ArrayList<TargetModule> targetModules) {
		this.targetModules = targetModules;
	}

	public ArrayList<TargetModule> getPhysicalModules(){
		return ifsModules;
	}	
	
	public ArrayList<TargetModule> getLogicalModules(){
		return lbbModules;
	}	

	/*
	private void populateObjectModel(  ArrayList<Table> tables,  ArrayList<Program> programs,
			ArrayList<TargetModule> ifsModules, ArrayList<TargetModule> lbbModules) 
	{
		ElementCreator creator = new ElementCreator(realModel);
		//creator.createBaseElements(tables, programs, ifsModules, lbbModules);
		creator.createBaseElementsImplementation (tables,programs);
		creator.createBaseElementsPhysicalModel(ifsModules);
		creator.createBaseElementsLogicalModel(lbbModules);

		ElementRelator relator = new ElementRelator (realModel);
		
		relator.relateImplementationModelInternally(tables, programs);
		relator.relateImplementationToPhysicalModel(tables, ifsModules);	
		relator.relateImplementationToLogicalModel(tables, lbbModules);
		
	}
	
*/
	
}
