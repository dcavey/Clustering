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
	TargetLBBModule Current_Account_Engine = new TargetLBBModule ("Current_Account_Engine");	lbbModules.add (Current_Account_Engine);
	TargetLBBModule Customer_Billing = new TargetLBBModule ("Customer_Billing");	lbbModules.add (Customer_Billing);
	TargetLBBModule Customer_Reporting = new TargetLBBModule ("Customer_Reporting");	lbbModules.add (Customer_Reporting);
	TargetLBBModule Reconciliation_Engine = new TargetLBBModule ("Reconciliation_Engine");	lbbModules.add (Reconciliation_Engine);
	TargetLBBModule Party_Management = new TargetLBBModule ("Party_Management");	lbbModules.add (Party_Management);
	TargetLBBModule Cash_Management_Engine = new TargetLBBModule ("Cash_Management_Engine");	lbbModules.add (Cash_Management_Engine);
	TargetLBBModule Clearing_and_Settlement_Engine = new TargetLBBModule ("Clearing_and_Settlement_Engine");	lbbModules.add (Clearing_and_Settlement_Engine);
	TargetLBBModule Instruction_and_Order_Management = new TargetLBBModule ("Instruction_and_Order_Management");	lbbModules.add (Instruction_and_Order_Management);
	TargetLBBModule Payments_Execution_Engine = new TargetLBBModule ("Payments_Execution_Engine");	lbbModules.add (Payments_Execution_Engine);
	TargetLBBModule Financial_Markets_Trade_engine = new TargetLBBModule ("Financial_Markets_Trade_engine");	lbbModules.add (Financial_Markets_Trade_engine);
	TargetLBBModule Securities_Market_Order_Mngt = new TargetLBBModule ("Securities_Market_Order_Mngt");	lbbModules.add (Securities_Market_Order_Mngt);
	TargetLBBModule Accounting_Rule_Engine = new TargetLBBModule ("Accounting_Rule_Engine");	lbbModules.add (Accounting_Rule_Engine);
	TargetLBBModule General_Ledger = new TargetLBBModule ("General_Ledger");	lbbModules.add (General_Ledger);
	TargetLBBModule Local_GAAP_Reporting_Layer = new TargetLBBModule ("Local_GAAP_Reporting_Layer");	lbbModules.add (Local_GAAP_Reporting_Layer);
	TargetLBBModule Local_Regulatory_Reporting_Layer = new TargetLBBModule ("Local_Regulatory_Reporting_Layer");	lbbModules.add (Local_Regulatory_Reporting_Layer);
	TargetLBBModule Local_Reporting_Layer = new TargetLBBModule ("Local_Reporting_Layer");	lbbModules.add (Local_Reporting_Layer);
	TargetLBBModule Local_Tax_Reporting_Layer = new TargetLBBModule ("Local_Tax_Reporting_Layer");	lbbModules.add (Local_Tax_Reporting_Layer);
	TargetLBBModule Product_Ledger = new TargetLBBModule ("Product_Ledger");	lbbModules.add (Product_Ledger);
	TargetLBBModule Business_Lending_Engine_ = new TargetLBBModule ("Business_Lending_Engine_");	lbbModules.add (Business_Lending_Engine_);
	TargetLBBModule Collateral_management = new TargetLBBModule ("Collateral_management");	lbbModules.add (Collateral_management);
	TargetLBBModule Consumer_Loans_Engine = new TargetLBBModule ("Consumer_Loans_Engine");	lbbModules.add (Consumer_Loans_Engine);
	TargetLBBModule Corporate_and_FI_Lending_Engine = new TargetLBBModule ("Corporate_and_FI_Lending_Engine");	lbbModules.add (Corporate_and_FI_Lending_Engine);
	TargetLBBModule Mortgages_Engine = new TargetLBBModule ("Mortgages_Engine");	lbbModules.add (Mortgages_Engine);
	TargetLBBModule Savings_Engine = new TargetLBBModule ("Savings_Engine");	lbbModules.add (Savings_Engine);
	TargetLBBModule Trade_Finance_Engine = new TargetLBBModule ("Trade_Finance_Engine");	lbbModules.add (Trade_Finance_Engine);
	TargetLBBModule Arrangement_Management = new TargetLBBModule ("Arrangement_Management");	lbbModules.add (Arrangement_Management);
	TargetLBBModule Reference_Data_Manager = new TargetLBBModule ("Reference_Data_Manager");	lbbModules.add (Reference_Data_Manager);
	}
	
	private  void createBaseElementsTargetIFSModules
	(  ArrayList<TargetModule> ifsModules)
	{
		TargetIFSModule General_Ledger = new TargetIFSModule ("General_Ledger");	ifsModules.add (General_Ledger);
		TargetIFSModule Account_Management = new TargetIFSModule ("Account_Management");	ifsModules.add (Account_Management);
		TargetIFSModule Lending = new TargetIFSModule ("Lending");	ifsModules.add (Lending);
		TargetIFSModule Financial_Markets = new TargetIFSModule ("Financial_Markets");	ifsModules.add (Financial_Markets);
		TargetIFSModule Cash_Management = new TargetIFSModule ("Cash_Management");	ifsModules.add (Cash_Management);
		TargetIFSModule CORE = new TargetIFSModule ("CORE");	ifsModules.add (CORE);
		TargetIFSModule auxiliary = new TargetIFSModule ("auxiliary");	ifsModules.add (auxiliary);
		TargetIFSModule UNUSED = new TargetIFSModule ("UNUSED");	ifsModules.add (UNUSED);
		
		
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
			System.err.println("Error: " + e.getMessage());
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
			System.err.println("Error: " + e.getMessage());
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
