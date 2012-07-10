package structurer;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import reporter.CSVWriter;
import reporter.Reporter;


public class FindClusters  {
	private static final String ERROR_CLOSE_FILES = "Please close all files!";
	private static final String ERROR_WRONG_INPUTFILE = "File not found! Default parameters are used.";

	private static boolean 	test; 			
	private static boolean 	toCsv ;			
	private static boolean 	toStdOut;		
	private static boolean 	printScore;	
	private static boolean 	printUse; 	
	private static int 		printContains;		
	private static boolean	physicalLevel;
	private static boolean 	logicalLevel;
	ObjectModel model;
	
	public FindClusters(){
		super();
		test = false; 			
		toCsv = true;			
		toStdOut = true;		
		printScore = true;	
		printUse = true; 	
		printContains = 4;		
		physicalLevel = true;
		logicalLevel = false;  // changed default, used to be true
	}
	
	public FindClusters(  boolean forCountingOnly )  {
		super();
		test = false; 			
		toCsv = false;			
		toStdOut = false;		
		printScore = false;	
		printUse = false; 	
		printContains = 0;		
		physicalLevel = true;
		logicalLevel = false;
	}
	

	public void run() /* throws IOException */ {
		clearFiles();
		model = new ObjectModel(!test);		

		model.createImplementationModel();

		if (physicalLevel) { 
			model.CreatePhysicalModel();
			PlaceProgramInModules(model.getPrograms(), model.getPhysicalModules(), model.getInterfaces(), model.getGlPrograms());  
		}
		
		if (logicalLevel) { 
			model.CreateLogicalModel();
			PlaceProgramInModules ( model.getPrograms(), model.getLogicalModules(), model.getInterfaces(), model.getGlPrograms());  
		}
	}
	
	public void placeProgramsInModules (ArrayList<TargetModule> modules ) {
		
		PlaceProgramInModules (model.getPrograms(), modules, model.getInterfaces(), model.getGlPrograms());
	}
	
	private void PlaceProgramInModules (ArrayList<Program> programs, ArrayList<TargetModule> modules, ArrayList<Interface> interfaces, HashMap<Program, ArrayList<Program>> glPrograms)
	{
		MatchMaker matchMaker = new MatchMaker (printScore, printUse);
		Reporter reporter = new Reporter(toCsv, toStdOut);
				
		Iterator<Program>  programIterator  = programs.iterator();
		
		while (programIterator.hasNext()) {
			Program program = programIterator.next();
			if(!program.getPgmType().equals("G")){
				matchMaker.findBestFittingModuleForProgram(program, modules, interfaces, glPrograms);
			}
		}
		
		Iterator<Program>  glProgramIterator  = programs.iterator();
		
		while (glProgramIterator.hasNext()) {
			Program program = glProgramIterator.next();
			if(program.getPgmType().equals("G")){
				matchMaker.findBestFittingModuleForProgram(program, modules, interfaces, glPrograms);
			}
		}
		
		switch(printContains){
			case 1: reporter.showModules(modules);
					break;
			case 2: reporter.ShowSharedTables(modules);
					break;
			case 3: reporter.showTableUsageAcrossModules(modules, true);
					break;
			case 4: reporter.showModules(modules);						// put option to get both (same as original version)
					reporter.showTableUsageAcrossModules(modules, true);
					break;
			default: break;
		}
	}


	public static void main(String[] args) {	
		FindClusters fc = new FindClusters();
		if(args.length > 0){
			readParams(args[0]);
		}
		fc.run();
	}
	
	private static void readParams(String file){
		// Open the file
		InputStream fstream;
		try {
			fstream = new FileInputStream(file);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null){
				if(strLine.matches("TEST *=.*")){
					test = Boolean.parseBoolean(strLine.substring(strLine.indexOf("=")+1).trim());
				} else if(strLine.toUpperCase().matches("TOCSV *=.*")){
					toCsv = Boolean.parseBoolean(strLine.substring(strLine.indexOf("=")+1).trim());			
				} else if(strLine.toUpperCase().matches("TOSTDOUT *=.*")){
					toStdOut = Boolean.parseBoolean(strLine.substring(strLine.indexOf("=")+1).trim());
				} else if(strLine.toUpperCase().matches("PRINTSCORE *=.*")){
					printScore = Boolean.parseBoolean(strLine.substring(strLine.indexOf("=")+1).trim());	
				} else if(strLine.toUpperCase().matches("PRINTUSE *=.*")){
					printUse = Boolean.parseBoolean(strLine.substring(strLine.indexOf("=")+1).trim()); 	
				} else if(strLine.toUpperCase().matches("PRINTCONTAINS *=.*")){
					String value = strLine.substring(strLine.indexOf("=")+1).trim();
					printContains = Integer.parseInt(value);		
				} else if(strLine.toUpperCase().matches("PHYSICAL_LEVEL *=.*")){
					physicalLevel = Boolean.parseBoolean(strLine.substring(strLine.indexOf("=")+1).trim());
				} else if(strLine.toUpperCase().matches("LOGICAL_LEVEL *=.*")){
					logicalLevel = Boolean.parseBoolean(strLine.substring(strLine.indexOf("=")+1).trim());
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println(ERROR_WRONG_INPUTFILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void clearFiles(){
		if(toCsv && (printContains == 1 || printContains == 4)){
			if(new File(Constants.CSV_CONTAINS).exists()){
				if(!(new File(Constants.CSV_CONTAINS).delete())) {
					System.err.println(ERROR_CLOSE_FILES);
					System.exit(-1);
				}
			}
			CSVWriter writer = new CSVWriter();
			String lineToWrite = "Module_Type;Module_Name;Contains;Type;Name";
			writer.writeLineToFile(Constants.CSV_CONTAINS, lineToWrite);
		}
		if(toCsv && (printContains == 3 || printContains == 4)){
			if(new File(Constants.CSV_USED).exists()){
				if(!(new File(Constants.CSV_USED).delete())) {
					System.err.println(ERROR_CLOSE_FILES);
					System.exit(-1);
				}
			}
			CSVWriter writer = new CSVWriter();
			String line = "Module_Type;Module;Program;M4_Program;Module_Name;Program_Name;ProgramType;Uses;Module_Type;Usage_Type;Module;Table;M4_Table;Module_Name;Table_Name;for;C;R;U;D";
			writer.writeLineToFile(Constants.CSV_USED, line);
		}
	}

	public ObjectModel getModel() {
		return model;
	}
	
	
}
