package structurer;

public class TableModuleXref{
	String tableName;
	String moduleName;
	String create; String read; String update; String delete;
	public TableModuleXref(String tableName, String moduleName, String create, String read, String update, String delete) {
		super();
		this.tableName = tableName;
		this.moduleName = moduleName;
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
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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