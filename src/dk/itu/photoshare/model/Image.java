package dk.itu.photoshare.model;

public class Image {
	
	String id;
	String owner;
	String imageDescription;
	
	public Image(String id, String description, String userId) {
		this.id = id;
		imageDescription = description;
		owner = userId;
	}
	
	public Image(){
		imageDescription = "ERROR";
	}
	
	public String getId() {
		return id;
	}
	
	public String getDescription() {
		return imageDescription;
	}

	public String getOwner() {
		return owner;
	}
	
}
