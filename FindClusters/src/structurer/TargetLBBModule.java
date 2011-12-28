package structurer;


public class TargetLBBModule extends TargetModule{

	public TargetLBBModule(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void showModuleTableCompositionLine ( String moduleName, String tableName)
	{
		System.out.printf ("LBB Module=%s contains table=%s \n", moduleName, tableName);
	}
	
	public void showModuleProgramCompositionLine (String moduleName, String programName, String pgmType)
	{
		System.out.printf ("LBB Module=%s contains [%s]program=%s \n", moduleName, pgmType, programName);  
	}
	
	public String getType() {
		String type = "LBB";
		return type;
	}
}
