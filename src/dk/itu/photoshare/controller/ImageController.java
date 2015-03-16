package dk.itu.photoshare.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import dk.itu.photoshare.model.CommentStatements;
import dk.itu.photoshare.model.Image;
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		ImageStatements is = new ImageStatements();
		CommentStatements cs = new CommentStatements();

		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			Image img = is.showImage(id, Integer.toString(user.getId()));
			InputStream imgContent = img.getURL().getBinaryStream();
			OutputStream out = response.getOutputStream();
			IOUtils.copy(imgContent,out);
//			response.setContentType("image/jpg");
			
			request.setAttribute("image", out);// TODO hardcoded userid, skal tages fra session
			request.setAttribute("comments", cs.showComment(id));
			
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
