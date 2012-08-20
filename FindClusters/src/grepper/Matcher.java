package grepper;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import structurer.Constants;

public class Matcher {

	/*
	private ArrayList<String> ispecs;
	private ArrayList<String> reports;
	private ArrayList<String> globalLogics;
	private ArrayList<String> profiles;
	*/
	
	private ArrayList<String> programs;
	
	public Matcher() {
		super();
		programs = readProgs();
	}

	
	public ArrayList<String> grepSources() {
		ArrayList<String> outputList = new ArrayList<String>();
		int lineNr = 0;
		String strLine;
		try {
			// Open the file
			FileInputStream fstream = new FileInputStream(Constants.SOURCECODE);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				lineNr ++;
				// matchAndReport(strLine, lineNr);	
				//matchAndReport_FIND_LINC_MIDDLEWARE_USAGE(strLine, lineNr);	// remove comment as required
				 matchAndReport_FIND_LINC_PROGRAMS(strLine, lineNr);			// remove comment as required
				// matchAndReport_FIND_LINC_ROUTINES(strLine, lineNr);			// remove comment as required
				
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		return outputList;
	}
	


	public boolean matchAndReport_FIND_LINC_PROGRAMS(String strLine, int lineNr) {
		
		boolean doOutput = false;
		boolean thisIsALincProgram = false;
		
		if (strLine.startsWith("10") ) {
			if (doOutput) { System.out.printf ("%d: ISPEC  : %1s \n", lineNr,strLine.substring(2,7)); }
			thisIsALincProgram = true;	
		} else
		{
			if (strLine.startsWith("50") ) {
				if (doOutput) { System.out.printf ("%d: REPORT : %1s \n", lineNr,strLine.substring(2,12)); }
				thisIsALincProgram = true;	
			} else
			{
				if (strLine.startsWith("70") ) {
					if (doOutput) { System.out.printf ("%d: LOGIC : %1s \n", lineNr, strLine.substring(2,19)); }
					thisIsALincProgram = true;	
				} else
				{	
					if (strLine.startsWith("60") ) {
						if (doOutput) { System.out.printf ("%d: PROFILE: %1s \n", lineNr, strLine.substring(2,11)); }
						thisIsALincProgram = true;	
					} 
				}
			}
		}
		return thisIsALincProgram;
	}
	
	
	public void matchAndReport_FIND_LINC_MIDDLEWARE_USAGE(String strLine, int lineNr) {

		if ( (strLine.contains("BP-") ) && 
			 (strLine.contains("INS") ) )	{			
			int beginIndex = strLine.indexOf("BP-"); 
			int endIndex = beginIndex + 18;
			String callee= strLine.substring(beginIndex, endIndex);
			
			String tempString = strLine.substring(2,19); 
			endIndex = tempString.indexOf (" ");
			String caller = strLine.substring(2, 2+endIndex);
			
			String tacsyType;
			if (callee.contains("SAG")) {
				tacsyType = "SAGE";
			} else
			{
				tacsyType = "EASY";
			}
			
			//System.out.printf ("TACSY: CALLER=%s; CALLEE=%s; LINE=%s\n", caller, callee, strLine );
			System.out.printf ("TACSY: CALLER=%s; CALLEE=%s; TACSYTYPE=%s \n", caller, callee, tacsyType );
			
		}
		
		if (strLine.contains   ("IFSYS/WF/TDFXFB"))  // ("XFB") )//
		{
			System.out.printf ("XFB: CALLER=%s \n", strLine.substring(2,12), strLine );
		}
	}
	
	public void matchAndReport_FIND_LINC_ROUTINES(String strLine, int lineNr)
	{
		if (strLine.contains  ("INSERT") && strLine.matches(".*G[A-Z]+.*")) // ("IFSYS/WF/TDFXFB"))  // ("XFB") )//
		{
			strLine.replace("  ", " ");
			String[] strWords = strLine.split(" "); 
			for(int i = 1; i < strWords.length; i++){
				if(strWords[i].matches("G[A-Z]+.*") && programs.contains(strWords[0].substring(2))){
					if(programs.contains(strWords[i])){
						System.out.printf ("CALLER=%s \t ROUTINE=%s \n", strWords[0].substring(2) , strWords[i] );
					}
				}
			}
		}
	}
	
	private ArrayList<String> readProgs() {
		ArrayList<String> outputList = new ArrayList<String>();
		try {
			// Open the file
			FileInputStream fstream = new FileInputStream(Constants.PROGFILE);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				outputList.add(output[1]);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		return outputList;
	}
	
	

}
