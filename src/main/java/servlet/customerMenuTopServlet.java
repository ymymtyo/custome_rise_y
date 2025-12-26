package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.customer_mstDAO;
import dbbean.Customer;


/**
 * Servlet implementation class customerMenuTopServlet
 */
@WebServlet("/customerMenuTopServlet")
public class customerMenuTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//フォワード先のURL
		String url = "/view/jsp/customer_menu.jsp"; 
					
				
				
		//DBから全顧客データ情報取得
		 List<Customer> customerList = null;
		 customer_mstDAO dao = new customer_mstDAO();
				
				
				
		try {
			customerList = dao.selectByIdName();
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			if(customerList != null) {
			//セッション開始
			HttpSession session = request.getSession(false);
					
			//顧客データをセッションスコープに保存
			session.setAttribute("customer",customerList);
							
			}else {
			// リクエストスコープにエラーメッセージ格納
				request.setAttribute("errMsg4", "顧客データが取得できませんでした");
				url = "/view/jsp/errPage1.jsp";
					
				}
						
				
				// JSPへフォワード
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc
						.getRequestDispatcher(url);
				rd.forward(request, response);
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}

}
