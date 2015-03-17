package dk.itu.photoshare.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.itu.photoshare.model.FlashMessage;
import dk.itu.photoshare.model.ImageStatements;
import dk.itu.photoshare.model.User;

/**
 * Servlet implementation class DeleteImageController
 */
@WebServlet("/DeleteImageController")
public class DeleteImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteImageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String image_id = request.getParameter("id");
		User user = (User) request.getSession().getAttribute("user");
		ImageStatements is = new ImageStatements();
		
		if (user == null || image_id == null){
			new FlashMessage().sendFlashMessage(request, "1. Could not remove image.", "error");
			response.sendRedirect("");
			return;
			
		}
		
		
		if (is.isOwner(Integer.toString(user.getId()), image_id)){
			// delete picture
			is.deleteImage(image_id);
			new FlashMessage().sendFlashMessage(request, "Image " + image_id + " has been deleted.", "msg");
			response.sendRedirect("");
			return;
		}
		new FlashMessage().sendFlashMessage(request, "Could not remove image.", "error");
		response.sendRedirect("");

	}

}
