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
			PreparedStatement pstmt = c.preparedStatement("SELECT users.username, comments.body, comments.id FROM users, comments WHERE users.id = comments.user_id AND comments.image_id=? ORDER BY id;");
			pstmt.setString(1, image_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				comments.add(new Comment(rs.getString("users.username"), rs.getString("comments.body")));
			}
						
		}
		catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		return comments;
	}
	
	public void deleteComment() {
		try {
			PreparedStatement pstmt = c.preparedStatement("DELETE FROM comments WHERE comment.id=? AND ;");
		}
		catch(Exception e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	public void createComment(String comment, int image_id, int user_id) {
		try{
			PreparedStatement pstmt = c.preparedStatement("INSERT INTO comments (body, image_id, user_id) VALUES (?,?,?)");
			pstmt.setString(1, comment);
			pstmt.setInt(2, image_id);
			pstmt.setInt(3, user_id);
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
}
