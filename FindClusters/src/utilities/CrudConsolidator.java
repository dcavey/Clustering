package utilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class CrudConsolidator {

	public static final String inputCrud = "C:/temp/inputCRUD.csv";
	
	public CrudConsolidator() {
		super();

	}
	
	public String getConsolidatedCrud () {

		String crud = null;
		
		try {
			// Open the file
			FileInputStream fstream = new FileInputStream(inputCrud);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				getConsolidatedCrudForInteraction (output[2], output[5], output[3] );

			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}

		
		return crud;
	}

	
	private  String getConsolidatedCrudForInteraction (	String provider, String consumer, String table)
	{
		char[] crudChars = new char[10]  ;
		String consoCrud;

		try {
			// Open the file
			FileInputStream fstream = new FileInputStream(inputCrud);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				
				if ( provider.equals(output[2])  &&
				     consumer.equals(output[5]) &&
				     table.equals(output[3]) ) {
					addOperationToCrud (crudChars, output[6], output[7], output[8], output[9] );
				}
						
				
//				getConsolidatedCrudForInteraction (output[2], output[5], output[3], output[6], output[7], output[8], output[9] );

			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
		
				
		return "";
		
	}
	

	private void addOperationToCrud (char[] consoCrud, String create, String read, String update, String delete )
	{
		if (create.equals("C") )  { consoCrud[0] = 'C'; }
		if (read.equals("R") )  { consoCrud[1] = 'R'; }
		if (update.equals("U") )  { consoCrud[2] = 'U'; }
		if (delete.equals("D") )  { consoCrud[3] = 'D'; }
		
		System.out.printf ("%s \n",  consoCrud[1] );

	}

}

