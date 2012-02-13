package optimizer;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import structurer.FindClusters;
import structurer.ObjectModel;
import structurer.Table;
import structurer.TargetModule;

public class ClusteringImprover {

	public static void main(String[] args) {

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
		
		
		Iterator<Table>  tableIterator = tablesThatNeedMoving.iterator();
		while (tableIterator.hasNext()) 
		{
			boolean external;
			Table myTable = tableIterator.next();
			
			tableMover.moveTableToModule(myTable.getName(), "TECHNICAL_KERNEL");
		}	

		findClusters.placeProgramsInModules(model.getPhysicalModules());
		
		nrTablesThatNeedMoving = tableMover.findTablesToMoveNewModule(tablesThatNeedMoving);
		
		System.out.printf("Time at end = %s", new Date());

	}

}


