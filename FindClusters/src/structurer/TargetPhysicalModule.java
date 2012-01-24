package structurer;


public class TargetPhysicalModule extends TargetModule {

	public TargetPhysicalModule(String name) {
		super(name);
	}
	
	public String getType() {
		String type = "IFS";
		return type;
	}
	
}
