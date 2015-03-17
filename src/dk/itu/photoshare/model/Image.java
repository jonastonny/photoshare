package dk.itu.photoshare.model;

public class Image {
	
	String url;
	String owner;
	String imageDescription;
	
	public Image(String url, String description, String userId) {
		this.url = url;
		imageDescription = description;
		owner = userId;
	}
	
	public Image(){
		imageDescription = "ERROR";
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getDescription() {
		return imageDescription;
	}

	public String getOwner() {
		return owner;
	}
	
	public String getOwner() {
		return owner;
	}
	
}
