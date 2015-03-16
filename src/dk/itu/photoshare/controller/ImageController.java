package dk.itu.photoshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.itu.photoshare.model.CommentStatements;
import dk.itu.photoshare.model.ImageStatements;
import dk.itu.photoshare.model.Image;


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
		
		ImageStatements is = new ImageStatements();
		CommentStatements cs = new CommentStatements();
		String imgPath = request.getServletContext().getRealPath("") + "/images/";
		System.out.println(imgPath);
		
		String path2 = request.getContextPath();
		System.out.println(path2+"/WebContent/images");
		
		
		request.setAttribute("image", is.showImage("16", imgPath, "1"));// TODO hardcoded userid, skal tages fra session
		request.setAttribute("comments", cs.showComment(id));
		
		RequestDispatcher view = request.getRequestDispatcher("views/images/image.jsp");
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
