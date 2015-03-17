package dk.itu.photoshare.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
			PreparedStatement pstmt = c.preparedStatement("SELECT images.image AS imageURL, images.description AS imageDescription FROM images WHERE id =?;");
			pstmt.setString(1, id);
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
	
	public Image getImage(String image_id){
		try {
			PreparedStatement pstmt = c.preparedStatement("SELECT images.description, images.user_id FROM images WHERE images.id = ?");
			pstmt.setString(1, image_id); 
			rs = pstmt.executeQuery();
			if(rs.next()){
				Image img = new Image("image?id="+image_id, rs.getString("description"), rs.getString("user_id"));
				return img;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean hasPerm(String userId, String imageId) {
		try {
			PreparedStatement pstmt = c.preparedStatement("SELECT user_id FROM image_users WHERE image_id =? AND user_id=?;");
			pstmt.setString(1, imageId);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
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
	
	public boolean sharePermission(String username, int image_id){
		try {
			System.out.println(username);
			PreparedStatement pstmtUser = c.preparedStatement("SELECT id FROM users WHERE username=?;");
			pstmtUser.setString(1, username);
			rs = pstmtUser.executeQuery();
			if(rs.next()) {	// user has been spotted
				PreparedStatement pstmt = c.preparedStatement("INSERT INTO image_users (image_id, user_id) VALUES(?, ?);");
				pstmt.setInt(1, image_id);
				pstmt.setInt(2, Integer.parseInt(rs.getString("id")));
				pstmt.executeUpdate();
				System.out.println("user is updated in perm.");
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("new user has not been granted perm");
			return false;
		}
	}
	
	
	
	public ArrayList<Image> getOwnImages(String user_id){	
		ArrayList <Image> results = new ArrayList<Image>();
		try {
			PreparedStatement pstmt = c.preparedStatement("SELECT id, description FROM photoshare.images WHERE user_id =?");
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				results.add(new Image(rs.getString("id"), rs.getString("description"), user_id));
			}
		
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return results;
	}
	
	public ArrayList<Image> getImagesSharedWithMe(String user_id){
		ArrayList <Image> results = new ArrayList<Image>();
		try {
			PreparedStatement pstmt = c.preparedStatement("SELECT image_id, images.description FROM images, (SELECT image_id FROM image_users WHERE image_users.image_id NOT IN (SELECT images.id FROM images WHERE user_id = ?) AND image_users.user_id = ?) AS result WHERE images.id = result.image_id;");
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				results.add(new Image(rs.getString("image_id"), rs.getString("description"), user_id));
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return results;
	}
}
