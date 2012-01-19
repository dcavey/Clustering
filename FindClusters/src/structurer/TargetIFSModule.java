package structurer;

import reporter.CSVWriter;


public class TargetIFSModule extends TargetModule {

	public TargetIFSModule(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void signalModuleTableCompositionLine ( String moduleName, String tableName, boolean tocsv)
	{
		if(tocsv){
			CSVWriter writer = new CSVWriter();
			String lineToWrite = "IFS Module;" + moduleName + ";contains;table;" + tableName;
			writer.writeLineToFile("out_TablesAndProgramsContainedInModules.csv", lineToWrite);
		} else {
			System.out.printf ("IFS Module=%s contains table=%s \n", moduleName, tableName);
		}
	}
	
	public void signalModuleProgramCompositionLine (String moduleName, String programName, String pgmType, boolean tocsv)
	{
		if(tocsv){
			CSVWriter writer = new CSVWriter();
			String lineToWrite = "IFS Module;" + moduleName + ";contains;["+ pgmType +"]program;" + programName;
			writer.writeLineToFile("out_TablesAndProgramsContainedInModules.csv", lineToWrite);
		} else {
			System.out.printf ("IFS Module=%s contains [%s]program=%s \n", moduleName, pgmType, programName); 
		}
	}
	
	public String getType() {
		String type = "IFS";
		return type;
	}
	
}
