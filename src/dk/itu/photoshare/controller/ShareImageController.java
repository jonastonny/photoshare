package dk.itu.photoshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.itu.photoshare.model.FlashMessage;
import dk.itu.photoshare.model.ImageStatements;

/**
 * Servlet implementation class ShareImageController
 */
@WebServlet("/ShareImageController")
public class ShareImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareImageController() {
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
		String shareToUser = request.getParameter("username"); // user who is granted perm
		int image_id = Integer.parseInt(request.getParameter("image_id")); // image_id
		ImageStatements statements = new ImageStatements();
		statements.sharePermission(shareToUser, image_id);
		FlashMessage message = new FlashMessage();
		message.sendFlashMessage(request, "shared with" + shareToUser, "msg");
		response.sendRedirect("view?id="+image_id);
		
	}

}
