package structurer;

public class Interface {

	private String name;
	private String programName;
	
	public Interface(String name, String programName) {
		super();
		this.name = name;
		this.programName = programName;
	}
	
	public String getProgramName(){
		return programName;
	}
	
	public String getName(){
		return name;
	}
}
