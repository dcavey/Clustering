package optimizer;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import structurer.FindClusters;
import structurer.ObjectModel;
import structurer.Table;

public class ClusteringImprover {
	private static final String ERROR_WRONG_INPUTFILE = "File not found!";
	private static final String ERROR_FAULT_INPUTFILE = "File with wrong input!";
	
	public static void main(String[] args) {
		ClusteringImprover ci = new ClusteringImprover();
		if(args.length == 1){
			ci.runForList(readParams(args[0]));
		} else {
			ci.runForModule();
		}
	}

	private static HashMap<String, String> readParams(String file){
		HashMap<String,String> newList = new HashMap<String,String>();
		// Open the file
		InputStream fstream;
		try {
			fstream = new FileInputStream(file);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null){
				String[] line = strLine.split(";");
				if(line.length == 2){
					newList.put(line[0], line[1]);
				} else {
					System.err.println(ERROR_FAULT_INPUTFILE);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println(ERROR_WRONG_INPUTFILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newList;
	}
	
	public void runForList(HashMap<String,String> tablesThatNeedMoving){
		ObjectModel model;
		
		int	nrTablesThatNeedMoving;
		
		System.out.printf("Time at start = %s \n", new Date());
		
		FindClusters findClusters = new FindClusters(true);
		findClusters.run();
			
		model = findClusters.getModel();
			
		TableMover tableMover = new TableMover(model);
			
		nrTablesThatNeedMoving = tablesThatNeedMoving.size();
		System.out.println("Nr of tables that needed to be moved = "+ nrTablesThatNeedMoving);
		
		nrTablesThatNeedMoving = tableMover.findTablesToMoveNewModule(new ArrayList<Table>());
		System.out.println("Nr of wrongly placed tables = "+ nrTablesThatNeedMoving);
		
		Iterator<Entry<String,String>>  tableIterator = tablesThatNeedMoving.entrySet().iterator();
		while (tableIterator.hasNext()) 
		{
			Entry<String,String> myTable = tableIterator.next();
			
			tableMover.moveTableToModule(myTable.getKey(), myTable.getValue());
		}	
		findClusters.placeProgramsInModules(model.getPhysicalModules());

		nrTablesThatNeedMoving = tableMover.findTablesToMoveNewModule(new ArrayList<Table>());
		System.out.println("Nr of still wrongly placed tables = "+ nrTablesThatNeedMoving);
		
		System.out.printf("Time at end = %s", new Date());
	}
	
	public void runForModule() {

		ObjectModel model;
		ArrayList<Table> tablesThatNeedMoving;
		int	nrTablesThatNeedMoving;
		
		System.out.printf("Time at start = %s \n", new Date());
		
		FindClusters findClusters = new FindClusters(true);
		findClusters.run();
			
		model = findClusters.getModel();
			
		TableMover tableMover = new TableMover(model);
			
		tablesThatNeedMoving = new ArrayList<Table>();
			
		nrTablesThatNeedMoving = tableMover.findTablesToMoveNewModule(tablesThatNeedMoving);
		System.out.println("Nr of wrongly placed tables = "+ nrTablesThatNeedMoving);
		
		Iterator<Table>  tableIterator = tablesThatNeedMoving.iterator();
		while (tableIterator.hasNext()) 
		{
			boolean external;
			Table myTable = tableIterator.next();
			
			tableMover.moveTableToModule(myTable.getName(), "TECHNICAL_KERNEL");
		}	
		findClusters.placeProgramsInModules(model.getPhysicalModules());
		
		nrTablesThatNeedMoving = tableMover.findTablesToMoveNewModule(tablesThatNeedMoving);
		System.out.println("Nr of still wrongly placed tables = "+ nrTablesThatNeedMoving);
		
		System.out.printf("Time at end = %s", new Date());

	}

}


