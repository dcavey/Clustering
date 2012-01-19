package structurer;

import reporter.CSVWriter;


public class TargetIFSModule extends TargetModule {

	public TargetIFSModule(String name) {
		super(name);
	}
	
	public String getType() {
		String type = "IFS";
		return type;
	}
	
}
