package dk.itu.photoshare.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dk.itu.photoshare.model.FlashMessage;
import dk.itu.photoshare.model.Image;
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

		ImageStatements is = new ImageStatements();
		User user = (User) session.getAttribute("user");
		ArrayList <Image> images = is.getOwnImages(Integer.toString(user.getId()));
		ArrayList <Image> imagesSharedWithMe = is.getImagesSharedWithMe(Integer.toString(user.getId()));
		request.setAttribute("images", images);
		request.setAttribute("imagesSharedWithMe", imagesSharedWithMe);
		
		
		FlashMessage fm = new FlashMessage();
		fm.getFlashMessage(request, "msg");
		fm.getFlashMessage(request, "error");
		RequestDispatcher view = request.getRequestDispatcher("views/index.jsp");
		view.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
