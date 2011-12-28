package structurer;


public class TargetIFSModule extends TargetModule {

	public TargetIFSModule(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void showModuleTableCompositionLine ( String moduleName, String tableName)
	{
		System.out.printf ("IFS Module=%s contains table=%s \n", moduleName, tableName);
	}
	
	public void showModuleProgramCompositionLine (String moduleName, String programName, String pgmType)
	{
		System.out.printf ("IFS Module=%s contains [%s]program=%s \n", moduleName, pgmType, programName); 
	}
	
	public String getType() {
		String type = "IFS";
		return type;
	}
	
}
