package utilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import structurer.Constants;

public class CreateTestDataForModularityLogging {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			CreateTestDataForModularityLogging creTestData = new CreateTestDataForModularityLogging();
			creTestData.getTestDataForLogging();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
		}
	}
	
	public void getTestDataForLogging () {
		
		try {
			// Open the file
			FileInputStream fstream = new FileInputStream(Constants.TABLE2PROGRAM_XREF);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");   				// Table	C	R	U	D	Program
		
				if (output[1].equals("C")) {
					System.out.println (output[0] + ","+ output[5] + "," + "CREATE");
				}
				if (output[2].equals("R")) {
					System.out.println (output[0] + ","+ output[5] + "," + "READ");
				}
				if (output[3].equals("U")) {
					System.out.println (output[0] + ","+ output[5] + "," + "UPDATE");
				}
				if (output[4].equals("D")) {
					System.out.println (output[0] + ","+ output[5] + "," + "DELETE");
				}
			}
			// Close the input stream
			
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}

	}

	
}


