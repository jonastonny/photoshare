package dk.itu.photoshare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dk.itu.photoshare.model.DBConnect;

public class ImageStatements {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	DBConnect c;
	
	public ImageStatements() {
        try {
        	c = new DBConnect();
			this.conn = c.getCon();
        } catch (Exception ex) {
        	ex.getMessage();
        }
    }
	
	public String showImage (int id, int user_id) {
		try {
			PreparedStatement pstmt = c.preparedStatement("SELECT images.url AS imageURL FROM images WHERE id ='?' AND user_id ='?';");
			pstmt.setInt(1, id);
			pstmt.setInt(2, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getString("imageURL");
		}
		catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		return "No image for you, sir";
	}
}
