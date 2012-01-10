package structurer;

public class TableModuleXref{
	String tableName;
	String physModuleName;
	String logModuleName;
	String create; String read; String update; String delete;
	public TableModuleXref(String tableName, String logModuleName, String physModuleName,  String create, String read, String update, String delete) {
		super();
		this.tableName = tableName;
		this.physModuleName = physModuleName;
		this.create = create;
		this.read = read;
		this.update = update;
		this.delete = delete;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPhysModuleName() {
		return physModuleName;
	}
	public void setPhysModuleName(String moduleName) {
		this.physModuleName = moduleName;
	}
	
	public String getLogModuleName() {
		return logModuleName;
	}
	public void setLogModuleName(String logModuleName) {
		this.logModuleName = logModuleName;
	}
	public String getCreate() {
		return create;
	}
	public void setCreate(String create) {
		this.create = create;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	
	
}