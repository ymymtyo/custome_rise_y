package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbbean.User;

/**
 * Servlet implementation class BackTopServlet
 */
@WebServlet("/BackTopServlet")
public class BackTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  // フォワード先のURL
		String url = "/view/jsp/member_menu.jsp"; 
		

		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("user");
	

		// JSPへフォワード
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc
				.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	

}
