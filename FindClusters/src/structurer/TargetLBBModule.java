package structurer;

import reporter.CSVWriter;


public class TargetLBBModule extends TargetModule{

	public TargetLBBModule(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public String getType() {
		String type = "LBB";
		return type;
	}
}
