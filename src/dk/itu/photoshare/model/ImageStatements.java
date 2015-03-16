package dk.itu.photoshare.model;

import java.io.InputStream;
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
	
	public byte[] showImage (String id, String user_id) {
		try {
			PreparedStatement pstmt = c.preparedStatement("SELECT images.image AS imageURL, images.description AS imageDescription FROM images WHERE id =? AND user_id =?;");
			pstmt.setString(1, id);
			pstmt.setString(2, user_id); 
			rs = pstmt.executeQuery();
			if(rs.next()) {
				byte[] content = rs.getBytes("imageURL");
				return content;
			}
		}
		catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		return null;
	}
	
	
	
	public void uploadImgToDB(InputStream img, String description, String user_id){
		try {
			PreparedStatement pstmt = c.preparedStatement("INSERT INTO images (image, user_id, description) VALUES(?, ?, ?);");
			pstmt.setBlob(1, img);
			pstmt.setInt(2, Integer.parseInt(user_id));
			pstmt.setString(3, description);
			pstmt.executeUpdate();
			addPermission(user_id);
			
			System.out.println("file succesfully uploaded to db");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 * 
	 * @param user_id
	 */
	public void addPermission(String user_id){
		try {
			PreparedStatement pstmt = c.preparedStatement("INSERT INTO image_users (image_id, user_id) VALUES((SELECT LAST_INSERT_ID()), ?);");
			pstmt.setInt(1, Integer.parseInt(user_id));
			pstmt.executeUpdate();
			System.out.println("updatet in perm.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
