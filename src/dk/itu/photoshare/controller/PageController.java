package dk.itu.photoshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dk.itu.photoshare.model.FlashMessage;
import dk.itu.photoshare.model.ImageStatements;
import dk.itu.photoshare.model.User;

/**
 * Servlet implementation class PageController
 */
@WebServlet("/")
public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			response.sendRedirect("login");
			return;
		}
		
		FlashMessage fm = new FlashMessage();
		fm.getFlashMessage(request, "msg");
		RequestDispatcher view = request.getRequestDispatcher("views/index.jsp");
		view.forward(request, response);
		
		ImageStatements is = new ImageStatements();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
