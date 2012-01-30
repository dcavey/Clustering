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
	public static final String TABLE2PROGRAM_XREF = "/resources/xref_table_program.csv";
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
	public static final int SCORETMP_DEFINEDOWNER = 100;
	public static final int SCORETMP_SUGGESTEDOWNER = 400;
	public static final int SCOREFINALOWNER = 999;
	
	/****************************************
	 * OUTPUT
	 ****************************************/
	public static final String CSV_CONTAINS = "C:/WorkStorageIFS/output/out_TablesAndProgramsContainedInModules.csv";
	public static final String CSV_USED = "C:/WorkStorageIFS/output/out_TablesAndProgramsUsedByModules.csv";	
}
