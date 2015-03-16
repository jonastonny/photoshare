package dk.itu.photoshare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dk.itu.photoshare.model.User;
import dk.itu.photoshare.model.DBConnect;

public class LoginStatements {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	DBConnect c;

    public LoginStatements() {
        try {
        	c = new DBConnect();
			this.conn = c.getCon();
        } catch (Exception ex) {
        	ex.getMessage();
        }
    }
    
    public boolean login(String username, String pwd) {
    	try {
    		pwd = SHA.hash256(pwd);
    		pstmt = c.preparedStatement("SELECT username, password FROM users WHERE username =?;");
    		pstmt.setString(1, username);
    		rs = pstmt.executeQuery();
    		if(rs.next()) {
    			if(rs.getString("password").equals(pwd)) {
    				return true;
    			}
    		}
    	}
    	catch(Exception e1) {
    		e1.printStackTrace();
    	}
    	return false;
    }
    
	public User getUser(String email){
		try{
			PreparedStatement pstmt = c.preparedStatement("SELECT users.id, users.username, roles.role FROM users, roles WHERE users.username=? AND roles.user_id=users.id");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()){
				User currentUser = new User();
				currentUser.setId(rs.getInt("id"));
				currentUser.setUsername(rs.getString("username"));
				currentUser.setRole(rs.getString("role"));
				return currentUser;
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}

}
