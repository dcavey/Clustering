package grepper;

public class Grepper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Matcher matcher = new Matcher();
			matcher.grepSources();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
		}
	}

}
