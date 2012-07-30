/**
 * 
 */
package structurer;

/**
 * @author TDEWEERD
 *
 */
public class Constants {
	
	/****************************************
	 * INPUT
	 ****************************************/
	public static final String SOURCECODE = "C:/WorkStorageIFS/input/sourcecode/ifsprd.mdl";
	public static final String GL2PROGRAM_XREF =    "C:/WorkStorageIFS/output/out_program_globallogic.csv";
	public static final String TABLE2PROGRAM_XREF = "C:/WorkStorageIFS/output/out_table_program.csv";
	//public static final String TABLE2PROGRAM_XREF = "/resources/xref_table_program.csv";
	public static final String TABLE2MODULE_XREF = "/resources/xref_table_module.csv";
	
	// Lists of possible values
	public static final String PROGFILE = "C:/WorkStorageIFS/input/list_programs.csv";
	public static final String TABLEFILE = "C:/WorkStorageIFS/input/list_tables.csv";
	public static final String INTERFACEFILE = "/resources/list_interfaces.csv";
	public static final String LBBMODULEFILE = "/resources/list_lbb_modules.csv";
	public static final String IFSMODULEFILE = "/resources/list_ifs_modules.csv";
	
	// Defined|suggested owner lists
	public static final String PROGRAM2MODULE_EXPERT_XREF = "/resources/xref_program_module.csv";
	public static final String PROGRAM2MODULE_CU_XREF = "/resources/xref_program_module_CU.csv";
	public static final String PROGRAM2MODULE_CLOG_XREF = "/resources/xref_program_module_CLog.csv";
	
	// Defined|suggested owner scores
	public static final int SCORETMP_DEFINEDOWNER = 400;
	public static final int SCORETMP_SUGGESTEDOWNER = 7;      // downgrade importance of SUGGESTEDOWNER
	public static final int SCOREFINALOWNER = 999;
	public static final int SCOREGLPROGRAM = 800;
	
	/****************************************
	 * OUTPUT
	 ****************************************/
	public static final String CSV_CONTAINS = "C:/WorkStorageIFS/output/out_TablesAndProgramsContainedInModules.csv";
	public static final String CSV_USED = "C:/WorkStorageIFS/output/out_TablesAndProgramsUsedByModules.csv";	
	
	// Define modularity access check - error codes
	public static final int INFO_AUTHORIZED_TABLE_ACCESS = 10;
	public static final int INFO_NON_AUTHORIZED_TABLE_ACCESS = -10;
	public static final int WARNING_NON_AUTHORIZED_TABLE_ACCESS = -11;
	public static final int FATAL_NON_AUTHORIZED_TABLE_ACCESS = -12;
	public static final int FATAL_TABLE_DOES_NOT_EXIST = -20;
	
	
	public static final String STATUS_MODULAR = "1";
	public static final String STATUS_REMOVED = "3";
	
	public static final String ASSIGNED_PROGRAM2MODULE = "/resources/assigned_program2module.csv";
	public static final String ASSIGNED_TABLE2MODULE = "/resources/assigned_table2module.csv";
	
	public static final String DB_CREATE = "INSERT";
	public static final String DB_UPDATE = "UPDATE";
	public static final String DB_DELETE = "DELETE";
	public static final String DB_READ = "SELECT";
	
}
