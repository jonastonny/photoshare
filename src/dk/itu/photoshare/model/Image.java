package dk.itu.photoshare.model;

import java.sql.Blob;

public class Image {
	
	Blob imageURL;
	String imageDescription;
	
	public Image(Blob blob, String description) {
		imageURL = blob;
		imageDescription = description;
	}
	
	public Image(){
		imageDescription = "ERROR";
	}
	
	public Blob getURL() {
		return imageURL;
	}
	
	public String getDescription() {
		return imageDescription;
	}
	
}
