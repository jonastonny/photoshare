package dk.itu.photoshare.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.tomcat.util.http.fileupload.IOUtils;

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
			PreparedStatement pstmt = c.preparedStatement("SELECT images.image AS imageURL, images.description AS imageDescription FROM images WHERE id =? AND user_id =?;");
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
	
}
