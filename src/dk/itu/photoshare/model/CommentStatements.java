package dk.itu.photoshare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CommentStatements {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	DBConnect c;
	
	public CommentStatements() {
        try {
        	c = new DBConnect();
			this.conn = c.getCon();
        } catch (Exception ex) {
        	ex.getMessage();
        }
    }
	
	public int countComments(String image_id){
		int result = 0;
		
		PreparedStatement pstmt;
		try {
			pstmt = c.preparedStatement("SELECT COUNT(*) FROM comments WHERE image_id =?;");
			pstmt.setString(1, image_id);
			rs = pstmt.executeQuery();
			if (rs.next()){
				result = rs.getInt("image_id");
			}
			return result; 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return result;
		
	}
	
	
	public Comment[] showComment (String image_id, String comment) {
		
		Comment[] comments = new Comment[countComments(image_id)];
		
		try {
			PreparedStatement pstmt = c.preparedStatement("SELECT user_id AS User , body AS Comment FROM comments WHERE image_id =? AND user_id =?;");
			pstmt.setString(1, image_id);
			pstmt.setString(2, comment); // TODO hent fra session
			rs = pstmt.executeQuery();
			
			for (int i = 0 ; i < comments.length ; i++){
				comments[i] = new Comment(rs.getString("image_id"), rs.getString("comment"));
			}
			
		}
		catch (Exception e1) {
			System.out.println(e1.getMessage());
		}

		return comments;
	}
}
