package dk.itu.photoshare.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
			pstmt.setString(2, user_id); // TODO hent fra session
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Image i = new Image(rs.getString("imageURL"), rs.getString("imageDescription"));
				return i;
			}
		}
		catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		// error pic
		return new Image();
	}
	
	
	public static void uploadImgToDB(String imgURL, String description, String userID){
		
	}
	
	public static void uploadImgToServer(String imageName, String username, InputStream imageContent){
		String upload = "/images/";
		File file = new File(upload, (username + imageName));
		System.out.println(file.toPath());
		System.out.println(imageContent.toString());


		try (InputStream input = imageContent) {
				Files.copy(input, file.toPath());	// copy the content of the imageContent to the hardcoded path
				System.out.println("File succesfully uploaded to server");
			} catch (IOException e) {
				System.out.println(e);
		}
	}
	
}
