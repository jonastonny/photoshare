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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dk.itu.photoshare.model.ImageStatements;
import dk.itu.photoshare.model.User;

/**
 * Servlet implementation class ImageController
 */
@WebServlet("/ImageUploadController")
@MultipartConfig(maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)
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
		HttpSession s = request.getSession();
		User user = (User) s.getAttribute("user");
		
		if(user == null){
			request.setAttribute("error", "You need to login to upload idiot!");
			RequestDispatcher view = request.getRequestDispatcher("views/index.jsp");
			view.forward(request, response);
			return;
		}
		RequestDispatcher view = request.getRequestDispatcher("views/images/upload.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		User user = (User) s.getAttribute("user");
		if(user == null){
			request.setAttribute("error", "You need to login to upload idiot!");
			RequestDispatcher view = request.getRequestDispatcher("views/index.jsp");
			view.forward(request, response);
			return;
		}
		
		
		String description = request.getParameter("description");
		Part image = request.getPart("image");	// get image

		if(!(image.getSize() <= 0)){
		    //String imgName = getImageName(image);
			InputStream imageContent = image.getInputStream();
			ImageStatements upload = new ImageStatements();
			upload.uploadImgToDB(imageContent, description, Integer.toString(user.getId()));
			RequestDispatcher view = request.getRequestDispatcher("views/images/image.jsp");
			view.forward(request, response);
		}
		else{
			System.out.println("No image to upload");
			request.setAttribute("error", "An error occurred, please try uploading youre image again");
			RequestDispatcher view = request.getRequestDispatcher("views/images/upload.jsp");
			view.forward(request, response);
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
