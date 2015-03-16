package dk.itu.photoshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dk.itu.photoshare.model.CommentStatements;
import dk.itu.photoshare.model.ImageStatements;



import dk.itu.photoshare.model.User;


/**
 * Servlet implementation class ImageController
 */
@WebServlet("/ImageController")
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		// TODO Auto-generated method stub
		String imageId = request.getParameter("id");
		
		ImageStatements is = new ImageStatements();
		CommentStatements cs = new CommentStatements();

		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
			request.setAttribute("image", is.showImage("19",Integer.toString(user.getId())));// TODO hardcoded userid, skal tages fra session
			request.setAttribute("comments", cs.showComment(imageId));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "Doh! Please login to see pictures");
		}

		
		RequestDispatcher view = request.getRequestDispatcher("views/images/image.jsp");
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
