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
	
	public String showImage (int id, int user_id) {
		try {
			PreparedStatement pstmt = c.preparedStatement("SELECT images.url AS imageURL FROM images WHERE id ='?' AND user_id ='?';");
			pstmt.setInt(1, id);
			pstmt.setInt(2, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getString("imageURL");
		}
		catch (Exception e1) {
			System.out.println(e1.getMessage());;
		}
		return "No image for you, sir";
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
