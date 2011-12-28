package structurer;
import java.util.ArrayList;



public class Table {

	private String name;
	private ArrayList<Program> programs;
	
	public Table(String name) {
		super();
		this.name = name;
		programs = new ArrayList<Program>();
	}
		
	public void addProgram (Program program) 
	{
		programs.add(program);
	}

	public String getName() {
		return name;
	}
	

}
