package dk.itu.photoshare.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import dk.itu.photoshare.model.ImageStatements;

/**
 * Servlet implementation class ImageController
 */
@WebServlet("/ImageUploadController")
@MultipartConfig
public class ImageUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()	
     */
    public ImageUploadController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("views/images/upload.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String description = request.getParameter("description");
		String user_id = "1";	// hardcoded - will be set by sessionParameter
		String username = "test"; // hardcoded - will be set by sessionParameter
		String appPath = request.getServletContext().getRealPath("");	
	    String savePath = appPath + "/images/";	// construct path of the directory to save uploaded file
		Part image = request.getPart("image");	// get image
		
		if(image != null){
		    String imgName = getImageName(image);
			InputStream imageContent = image.getInputStream();
			ImageStatements upload = new ImageStatements();
			String imgURL = upload.uploadImgToServer(imgName, username, imageContent, savePath);
			upload.uploadImgToDB(imgURL, description, user_id);
		}
		else{
		System.out.println("The image wasn't chosen to be uploaded");
		}
	}
	
	/**
	 * Loops through the headers content-disposition, find and return the filename
	 * of an image
	 * 
	 * @param image
	 * @return String imageName
	 */
	private static String getImageName(Part image){
		for (String cd: image.getHeader("content-disposition").split(";")){	// get content-disposition in array"
			if(cd.trim().startsWith("filename")){	// trim if filename="file.jpg"
				String imageName = cd.substring(cd.indexOf("=")+1).trim().replace("\"", ""); // get imageName and remove = and "
				return imageName;
			}
		}
		System.out.println("empty file");
		return null;
	}

}
