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
import dk.itu.photoshare.model.FlashMessage;
import dk.itu.photoshare.model.Image;
import dk.itu.photoshare.model.ImageStatements;
import dk.itu.photoshare.model.User;

/**
 * Servlet implementation class ViewImageController
 */
@WebServlet("/ViewImageController")
public class ViewImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewImageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		new FlashMessage().getFlashMessage(request, "msg");
		
		ImageStatements is = new ImageStatements();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			request.setAttribute("error", "U no user");
			request.getRequestDispatcher("views/index.jsp").forward(request, response);
			return;
		}
		
		if(!is.hasPerm(Integer.toString(user.getId()), id)) {
			request.setAttribute("error", "Has no perm");
			request.getRequestDispatcher("views/index.jsp").forward(request, response);
			return;
		}			
		
		CommentStatements cs = new CommentStatements();
		request.setAttribute("comments", cs.showComment(id));
		request.setAttribute("image", is.getImage(id));
		
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("views/images/image.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
