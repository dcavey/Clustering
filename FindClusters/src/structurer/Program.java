package structurer;

import java.util.ArrayList;

public class Program {
	
	private ArrayList<Table> tables;
	private String name;
	private String pgmType;
	
	public Program(String name, String pType) {
		super();
		this.name = name;
		this.pgmType = pType;
		tables = new ArrayList<Table>();
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public String getName() {
		return name;
	}
	
	public String getPgmType() {
		return pgmType;
	}

	public void addTable (Table table) {
		tables.add(table);
	}
	

}
