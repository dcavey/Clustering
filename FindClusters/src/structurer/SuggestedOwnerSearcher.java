package structurer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SuggestedOwnerSearcher extends OwnerSearcher {

	public SuggestedOwnerSearcher(boolean printScore) {
		super(printScore);
	}
	
	protected TargetModule getPhysicalOwner (Program program, ArrayList<TargetModule> modules) {
		TargetModule definedModule = null;
		
		try 
		{
			// Open the file
			InputStream fstream = this.getClass().getResourceAsStream(Constants.PROGRAM2MODULE_CU_XREF);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) 
			{
				String[] output = strLine.split(";");
				if (program.getName().equals(output[0])) {

					if (definedModule != null) {
						SignalScore (Constants.SCORETMP_SUGGESTEDOWNER, "FIT=Y,SEL=N", definedModule.getType(), definedModule.getName(), program );							
					}
					
					definedModule = findModule(modules, output[1]);
				}	
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.out.println("Error: " + e.getMessage());
		}
		
		return definedModule;
		
	}
	
	protected TargetModule getLogicalOwner (Program program, ArrayList<TargetModule> modules) {
		TargetModule definedModule = null;		
		return definedModule;
	}

}
