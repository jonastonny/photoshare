package dk.itu.photoshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dk.itu.photoshare.model.LoginStatements;
import dk.itu.photoshare.model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("views/user/login.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginStatements ls = new LoginStatements();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(ls.login(username, password)) {
			User currentUser = ls.getUser(username);
			HttpSession session = request.getSession();
			session.setAttribute("user", currentUser);
			response.sendRedirect(""); // main page
		}
		else {
			request.setAttribute("error", "Username or password was incorrect");
			RequestDispatcher view = request.getRequestDispatcher("views/user/login.jsp");
			view.forward(request, response);
		}
	}
}
