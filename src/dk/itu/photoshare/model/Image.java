package dk.itu.photoshare.model;

public class Image {
	
	String imageURL;
	String imageDescription;
	
	public Image(String url, String description) {
		imageURL = url;
		imageDescription = description;
	}
	
	public Image(){
		imageURL = "images/ctrlpee.png";
		imageDescription = "ERROR";
	}
	
	public String getURL() {
		return imageURL;
	}
	
	public String getDescription() {
		return imageDescription;
	}
	
}
