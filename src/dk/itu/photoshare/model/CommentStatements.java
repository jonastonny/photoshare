package dk.itu.photoshare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
			
			System.out.println("I have now counted the comments");
			return result; 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return result;
		
	}
	
	
	public ArrayList<Comment> showComment (String image_id) {
		
		ArrayList<Comment> comments = new ArrayList<Comment>();
		
		try {
			PreparedStatement pstmt = c.preparedStatement("SELECT user_id AS userid, body AS comment FROM comments WHERE image_id=? ;");
			pstmt.setString(1, image_id);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String username = getUserName(rs.getString("userid"));
				System.out.println(username);
				comments.add(new Comment(username, rs.getString("comment")));
			}
						
		}
		catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		return comments;
	}
	
	public String getUserName (String userId) {
		try {
			PreparedStatement pstmt = c.preparedStatement("SELECT username FROM users WHERE id=?;");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String username = rs.getString("username");
				return username;
			}
		}
		catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		return "no username";
	}
}
