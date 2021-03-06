package dk.itu.photoshare.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		try {
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
			
			byte[] img = is.showImage(id, Integer.toString(user.getId()));
			if (img != null){				
				response.setContentType("image/jpg");
				response.setContentLength(img.length);
				response.getOutputStream().write(img);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
