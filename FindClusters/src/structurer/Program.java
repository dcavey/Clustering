package structurer;

import java.util.ArrayList;
import java.util.Iterator;

public class Program {
	
	private ArrayList<Table> tables;
	private ArrayList<TableProgramXref> tableProgramXrefs;
	private String name;
	private String pgmType;
	private TargetModule module;
	
	public Program(String name, String pType) {
		super();
		this.name = name;
		this.pgmType = pType;
		tables = new ArrayList<Table>();
		tableProgramXrefs = new ArrayList<TableProgramXref>();
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
	
	public String getPgmNameAndType() {
		return name + " [" + pgmType + "]";
	}
	
	public TargetModule getModule(){
		return module;
	}

	public void addTable (Table table) {
		tables.add(table);
	}

	public String getCRUDforTable(Table table) {
		// TODO Auto-generated method stub
		
		String strCRUD = "" ;
		
		Iterator<TableProgramXref>  xrefIterator  = tableProgramXrefs.iterator();
		
		while (xrefIterator.hasNext()) {
			TableProgramXref thisXref = xrefIterator.next();
			
			if (thisXref.getTableName().equals(table.getName()))
			{		
				strCRUD = thisXref.getCreate() + ";" + thisXref.getRead() +";" + thisXref.getUpdate() + ";" + thisXref.getDelete() ;
				//strCRUD = "CRUD=" + thisXref.getCreate() + ";" + thisXref.getRead() +";" + thisXref.getUpdate() + ";" + thisXref.getDelete() ;
			}	
			
		}
		return strCRUD;		
	}

	public void addTableProgramXref(TableProgramXref xref) {
		tableProgramXrefs.add(xref);
	}

	public void setModule(TargetModule module) {
		this.module = module;
	}

}
