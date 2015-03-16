package dk.itu.photoshare.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.itu.photoshare.model.CreateUserStatements;
import dk.itu.photoshare.model.FlashMessage;

/**
 * Servlet implementation class CreateUserController
 */
@WebServlet("/CreateUserController")
public class CreateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("views/user/create.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm-password");
		
		Pattern p = Pattern.compile("^.{6}.*$");
		Matcher m = p.matcher(password);
		CreateUserStatements cus = new CreateUserStatements();
		
		if (cus.userExists(username)){
			request.setAttribute("error", "Username already exists");
			RequestDispatcher view = request.getRequestDispatcher("views/user/create.jsp");
			view.forward(request, response);
			return;
		}
		
		else if (!m.matches()){
			request.setAttribute("error", "Password must be 6 or more charachters");
			RequestDispatcher view = request.getRequestDispatcher("views/user/create.jsp");
			view.forward(request, response);
			return;
		}
		
		else if (!password.equals(confirmPassword)){
			request.setAttribute("error", "Your password doesn't match...");
			RequestDispatcher view = request.getRequestDispatcher("views/user/create.jsp");
			view.forward(request, response);
			return;
		}
		
		cus.addUser(username, password, "user");
		
		FlashMessage fm = new FlashMessage();
		fm.sendFlashMessage(request, "User Created. Now please login mofo!", "msg");
		response.sendRedirect("");
	}

}
