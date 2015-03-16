package dk.itu.photoshare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CreateUserStatements {
	
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	DBConnect c;
	
	public CreateUserStatements() {
        try {
        	c = new DBConnect();
			this.conn = c.getCon();
        } catch (Exception ex) {
        	ex.getMessage();
        }
    }
	
	public boolean addUser(String username, String password, String role){
		try {
			PreparedStatement pstmt = c.preparedStatement("INSERT INTO users (username, password) VALUES (?, ?)");
			pstmt.setString(1, username);
			pstmt.setString(2, SHA.hash256(password));
			pstmt.executeUpdate();
			
			int userId = getUserId(username);
			addUserRole(userId, role);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean addUserRole(int userId, String role){
		try {
			pstmt = c.preparedStatement("INSERT INTO role (user_id, role) VALUES (?, ?)");
			pstmt.setInt(1, userId);
			pstmt.setString(2, role);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public int getUserId(String username){
		try {
			pstmt = c.preparedStatement("SELECT users.id FROM users WHERE users.username = ?");
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean userExists(String username){
		try {
			pstmt = c.preparedStatement("SELECT users.username FROM users WHERE users.username = ?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (rs.next()) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
