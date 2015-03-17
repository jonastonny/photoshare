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
			System.out.println("file succesfully uploaded to db");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
//	public static String uploadImgToServer(String imageName, String username, InputStream imageContent, String path){
//		String uniqueID = Integer.toString(imageContent.hashCode());
//		String fileName =  uniqueID + username + imageName;
//		String directory = path + fileName;
//		try {
//			OutputStream output = new FileOutputStream(directory); 
//			IOUtils.copy(imageContent, output);
//			System.out.println("File succesfully uploaded to server");
//		    output.flush();
//		    output.close();
//		    return directory;
//		} catch (IOException e) {
//			System.out.println(e);
//			return null;
//		}
//	}
	
	
	public ArrayList<Image> getOwnImagesId(String user_id, String owner){	
		ArrayList <Image> results = new ArrayList<Image>();
		try {
			PreparedStatement pstmt = c.preparedStatement("SELECT `image` FROM `photostream`.`images` WHERE user_id=");
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				results.add(new Image("view?id="+rs.getString("imageId"), rs.getString("description"), rs.getString("user_Id")));
			}
		
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return results;
	
	}
	
	/*public Image getImages(String image_id){
	
	try{
		PreparedStatement pstmt = c.preparedStatement("SELECT `image` AS image FROM `photostream`.`images` WHERE image_id=?;");
		pstmt.setString(1, image_id);
		rs = pstmt.executeQuery();
	}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
		return (Image) rs;
	}*/
}
