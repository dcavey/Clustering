package structurer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ElementCreator {

	private boolean fullModel;
	
	public ElementCreator(boolean fullModel) {
		super();	
		this.fullModel = fullModel;
	}
	

	public void createBaseElementsImplementation (ArrayList<Table> tables, ArrayList<Program> programs, ArrayList<Interface> interfaces)
	{
		if (this.fullModel) {
			createBaseElementsTables (tables); 
			createBaseElementsPrograms( programs);
			createBaseElementsInterfaces(interfaces);
		}
		else
		{
			createBaseElementsImplementationTestData (tables, programs);	
		}
	}
	

	public  void createBaseElementsPhysicalModel ( ArrayList<TargetModule> modules)
	{
		if (this.fullModel) {
			createBaseElementsTargetPhysicalModules( modules);			
		}
		else
		{
			createBaseElementsPhysicalModelTestData (modules);			
		}
	}
	
	public  void createBaseElementsLogicalModel ( ArrayList<TargetModule> modules)
	{
		if (this.fullModel) {
			createBaseElementsTargetLogicalModules( modules);			
		}
		else
		{
			createBaseElementsLogicalModelTestData (modules);			
		}
	}	
	
	
	private  void createBaseElementsTargetLogicalModules
	(  ArrayList<TargetModule> lbbModules)
	{
		try {
			// Open the file
			InputStream fstream = this.getClass().getResourceAsStream(Constants.LBBMODULEFILE);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");		
				/* index 
				 * 	0 = Module Name
				 */
				TargetLogicalModule  targetModule = new TargetLogicalModule (output[0].trim());	
				lbbModules.add (targetModule);	
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.out.println("Failed to create LBB modules: " + e.getMessage());
		}		
	}	
	
	
	private  void createBaseElementsTargetPhysicalModules
	(  ArrayList<TargetModule> ifsModules)
	{
		try {
			// Open the file
			InputStream fstream = this.getClass().getResourceAsStream(Constants.IFSMODULEFILE);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");		
				/* index 
				 * 	0 = Module Name
				 */
				TargetPhysicalModule  targetModule = new TargetPhysicalModule (output[0].trim());	
				ifsModules.add (targetModule);	
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.out.println("Failed to create IFS modules: " + e.getMessage());
		}		
	}
			
	private void createBaseElementsPrograms(ArrayList<Program> programs) {
		//ArrayList<String> outputList = new ArrayList<String>();
		try {
			// Open the file
			InputStream fstream = new FileInputStream(Constants.PROGFILE);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				
				/*
				 * index 
				 * 	0 = Type of program (I, R, P, G) 
				 * 	1 = Program Name
				 * 
				 */
				// if (output[0].equals("P"))  {	} else 
				{
					Program  programToAdd = new Program (output[1].trim(),output[0].trim());	
					programs.add (programToAdd);	
				}

			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
	}

	private void createBaseElementsInterfaces(ArrayList<Interface> interfaces) {
		try {
			// Open the file
			InputStream fstream = this.getClass().getResourceAsStream(Constants.INTERFACEFILE);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				
				/*
				 * index 
				 * 	0 = Program Name 
				 * 	1 = Interface Name
				 * 
				 */
				Interface  interfaceToAdd;
				if(output.length == 1){
					interfaceToAdd = new Interface ("DEFAULT", output[0].trim());
				} else {
					interfaceToAdd = new Interface (output[1].trim()	, output[0].trim());
				}
				interfaces.add(interfaceToAdd);	
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
	}
	
	
	private void createBaseElementsTables(ArrayList<Table> tables) {
		try {
			// Open the file
			InputStream fstream = new FileInputStream(Constants.TABLEFILE);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");			
				/*
				 * index 
				 * 	0 = Table Name
				 * 
				 */
				Table  tableToAdd = new Table (output[0].trim());	
				tables.add (tableToAdd);	

			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
	}
	
	private void createBaseElementsImplementationTestData
	( 	ArrayList<Table> tables,  ArrayList<Program> programs  )
    {
		Table Table01 = new Table ("Table01");		tables.add(Table01);
		Table Table02 = new Table ("Table02");		tables.add(Table02);
		Table Table03 = new Table ("Table03");		tables.add(Table03);
		Table Table04 = new Table ("Table04");		tables.add(Table04);
		Table Table05 = new Table ("Table05");		tables.add(Table05);
		
		Program ProgramAAA = new Program ("ProgramAAA", "R"); 		programs.add(ProgramAAA);
		Program ProgramBBB = new Program ("ProgramBBB", "I"); 		programs.add(ProgramBBB);
		Program ProgramCCC = new Program ("ProgramCCC", "G"); 		programs.add(ProgramCCC);	
    }	
	
	private void createBaseElementsPhysicalModelTestData
	( 	ArrayList<TargetModule> modules)
    {
		TargetModule ModuleIFS_AA = new TargetPhysicalModule ("ModuleIFS_AA");		modules.add(ModuleIFS_AA);
		TargetModule ModuleIFS_BB = new TargetPhysicalModule ("ModuleIFS_BB");		modules.add(ModuleIFS_BB);
		TargetModule ModuleIFS_CC = new TargetPhysicalModule ("ModuleIFS_CC"); 		modules.add(ModuleIFS_CC);
    }
	
	private void createBaseElementsLogicalModelTestData
	( 	ArrayList<TargetModule> modules)
    {
		TargetModule ModuleLBB_AA = new TargetLogicalModule ("ModuleLBB_AA");		modules.add(ModuleLBB_AA);
		TargetModule ModuleLBB_BB = new TargetLogicalModule ("ModuleLBB_BB");		modules.add(ModuleLBB_BB);
		TargetModule ModuleLBB_CC = new TargetLogicalModule ("ModuleLBB_CC"); 		modules.add(ModuleLBB_CC);
    }
	
}
