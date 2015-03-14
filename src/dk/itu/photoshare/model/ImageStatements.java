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
	
	public Image showImage (String id, String user_id) {
		try {
			PreparedStatement pstmt = c.preparedStatement("SELECT images.url AS imageURL, images.description AS imageDescription FROM images WHERE id =? AND user_id =?;");
			pstmt.setString(1, id);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Image i = new Image(rs.getString("imageURL"), rs.getString("imageDescription"));
				System.out.println(i.getURL() + i.getDescription());
				return i;
			}
		}
		catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		// error pic
		return new Image();
	}
}
