package dk.itu.photoshare.model;

public class Comment {
	
	String userId;
	String body;
	
	public Comment(String Id, String commentBody) {
		userId = Id;
		body = commentBody;
	}
	
	public String getuserId() {
		return userId;
	}
	
	public String getBody() {
		return body;
	}
}
