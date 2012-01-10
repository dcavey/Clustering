package structurer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ElementCreator {

	private boolean fullModel;
	
	public ElementCreator(boolean fullModel) {
		super();	
		this.fullModel = fullModel;
	}
	
	
	
	public  void createBaseElements ( ArrayList<Table> tables,  ArrayList<Program> programs,
	ArrayList<TargetModule> ifsModules, ArrayList<TargetModule> lbbModules)
	{	

		if (this.fullModel) {
			createBaseElementsTables (tables); 
			createBaseElementsPrograms( programs);
			createBaseElementsTargetIFSModules( ifsModules);
			createBaseElementsTargetLBBModules( lbbModules);		
		} else
		{
			DefineUnitTestData (tables, programs, ifsModules, lbbModules);
		}
	}

	
	private  void createBaseElementsTargetLBBModules
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
				TargetLBBModule  targetModule = new TargetLBBModule (output[0]);	lbbModules.add (targetModule);	
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.out.println("Failed to create LBB modules: " + e.getMessage());
		}		
	}	
	
	
	private  void createBaseElementsTargetIFSModules
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
				TargetIFSModule  targetModule = new TargetIFSModule (output[0]);	ifsModules.add (targetModule);	
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
			InputStream fstream = this.getClass().getResourceAsStream(Constants.PROGFILE);
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
					Program  programToAdd = new Program (output[1],output[0]);	programs.add (programToAdd);	
				}

			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	private void createBaseElementsTables(ArrayList<Table> tables) {
		try {
			// Open the file
			InputStream fstream = this.getClass().getResourceAsStream(Constants.TABLEFILE);
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
				Table  tableToAdd = new Table (output[0]);	tables.add (tableToAdd);	

			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	private void DefineUnitTestData
	( 	ArrayList<Table> tables,  ArrayList<Program> programs,
			ArrayList<TargetModule> ifsModules, ArrayList<TargetModule> lbbModules)
    {
		Table Table01 = new Table ("Table01");		tables.add(Table01);
		Table Table02 = new Table ("Table02");		tables.add(Table02);
		Table Table03 = new Table ("Table03");		tables.add(Table03);
		Table Table04 = new Table ("Table04");		tables.add(Table04);
		Table Table05 = new Table ("Table05");		tables.add(Table05);
		
		Program ProgramAAA = new Program ("ProgramAAA", "R"); 		programs.add(ProgramAAA);
		Program ProgramBBB = new Program ("ProgramBBB", "I"); 		programs.add(ProgramBBB);
		Program ProgramCCC = new Program ("ProgramCCC", "G"); 		programs.add(ProgramCCC);
		
		TargetModule Module001 = new TargetIFSModule ("Module001");		ifsModules.add(Module001);
		TargetModule Module002 = new TargetIFSModule ("Module002");		ifsModules.add(Module002);
		TargetModule Module003 = new TargetIFSModule ("Module003"); 		ifsModules.add(Module003);

    }	
	
}
