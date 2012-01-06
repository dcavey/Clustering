package structurer;

public class TableProgramXref{
		String tableName;
		String programName;
		String create; String read; String update; String delete;
		public TableProgramXref(String tableName, String programName, String create, String read, String update, String delete) {
			super();
			this.tableName = tableName;
			this.programName = programName;
			if (! create.equals("")) { this.create = create; } else { this.create="_";}
			if (! read.equals("")) { this.read = read; } else { this.read="_";}
			if (! update.equals("")) { this.update = update; } else { this.update="_";}
			if (! delete.equals("")) { this.delete = delete; } else { this.delete="_";}
		}
		public String getTableName() {
			return tableName;
		}
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		public String getProgramName() {
			return programName;
		}
		public void setProgramName(String programName) {
			this.programName = programName;
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
