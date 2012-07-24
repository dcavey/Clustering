package utilities;

public class UtilMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			CrudConsolidator crudConso = new CrudConsolidator();
			crudConso.getConsolidatedCrud();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
		}
	}
	
}


