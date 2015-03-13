package dk.itu.photoshare.model;

public final class DB {
	
	public static String db_name = "photoshare";
	public static String user = "root";
	public static String password = "";
	public static String url = "jdbc:mysql://localhost:3306/" + db_name;
	
	private DB() { } // Do not instantiate this class
}
