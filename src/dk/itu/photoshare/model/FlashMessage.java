package dk.itu.photoshare.model;
import javax.servlet.http.HttpSession; 
import javax.servlet.http.HttpServletRequest;

public class FlashMessage {
	
	public void sendFlashMessage(HttpServletRequest request,String message, String parameter){
		HttpSession session = request.getSession();
		if(session.getAttribute(message) != null){
			return;
		}
		session.setAttribute(parameter, message);
	}
	
	public void getFlashMessage(HttpServletRequest request, String parameter){
		HttpSession session = request.getSession();
		request.setAttribute(parameter, (String) session.getAttribute(parameter));
		request.getSession().removeAttribute(parameter);
	}
}
