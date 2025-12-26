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

import DAO.user_mstDAO;
import dbbean.User;



/**
 * Servlet implementation class userLogin
 */
@WebServlet("/userLogin")
public class userLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		        // フォワード先のURL
				String url = "/view/jsp/member_menu.jsp"; 

				// リクエストパラメータの参照
				String userId = request.getParameter("userId");
				String userPass = request.getParameter("userPass");

                 //DBにてユーザー認証
				User user = null;
				user_mstDAO dao = new user_mstDAO();
				user = dao.selectByIdPass(userId, userPass);
				
				// 認証の有無をチェック
				if(user != null) {
					//セッション開始
					HttpSession session = request.getSession(true);
					
					session.setAttribute("user",user);
					
					
				}else {
					// リクエストスコープにエラーメッセージ格納
					request.setAttribute("errMsg1", "ユーザーIDまたはパスワードが誤っています");
					url = "/view/jsp/loginerr.jsp";
				}
				
			

				// JSPへフォワード
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc
						.getRequestDispatcher(url);
				rd.forward(request, response);
			
	}

}
