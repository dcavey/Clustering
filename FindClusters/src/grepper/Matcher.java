package grepper;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class Matcher {

	private static final String SOURCECODE = "c:/tempSource/ifsprd.mdl";
	
	/*
	private ArrayList<String> ispecs;
	private ArrayList<String> reports;
	private ArrayList<String> globalLogics;
	private ArrayList<String> profiles;
	*/
	
	public Matcher() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public ArrayList<String> grepSources() {
		ArrayList<String> outputList = new ArrayList<String>();
		try {
			// Open the file
			FileInputStream fstream = new FileInputStream(SOURCECODE);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				match(strLine);		
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return outputList;
	}
	


	public void match(String strLine) {
			
		if (strLine.startsWith("10") ) {
			System.out.printf ("ISPEC  : %1s \n", strLine.substring(2,7));
		} else
		{
			if (strLine.startsWith("50") ) {
				System.out.printf ("REPORT : %1s \n", strLine.substring(2,12));
			} else
			{
				if (strLine.startsWith("70") ) {
					System.out.printf ("GLOGIC : %1s \n", strLine.substring(2,19));
				} else
				{	
					if (strLine.startsWith("60") ) {
						System.out.printf ("PROFILE: %1s \n", strLine.substring(2,11));
					} 
				}
			}
		}
	}

}
