package dk.itu.photoshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.itu.photoshare.model.CommentStatements;
import dk.itu.photoshare.model.User;

/**
 * Servlet implementation class CreateCommentController
 */
@WebServlet("/CreateCommentController")
public class CreateCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCommentController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String comment = request.getParameter("comment");
		int image_id = Integer.parseInt(request.getParameter("id"));
		User user = (User) request.getSession().getAttribute("user");
		int user_id = user.getId();
		CommentStatements sm = new CommentStatements();
		sm.createComment(comment, image_id, user_id);
		response.sendRedirect("view?id="+image_id);
	}
	
}
