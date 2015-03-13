package dk.itu.photoshare.model;

public final class DB {
	
	public static String db_name = "photoshare";
	public static String user = "jonas";
	public static String password = "28608374";
	public static String url = "jdbc:mysql://localhost:3306/" + db_name;
	
	private DB() { } // Do not instantiate this class
}
