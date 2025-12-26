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
import dbbean.CustomerSearch;

/**
 * Servlet implementation class CustomerSearchServlet
 */
@WebServlet("/CustomerSearchServlet")
public class CustomerSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//顧客データ検索サーブレット
		
		
		///フォワード先のURL
		String url = "/view/jsp/csutomer_search.jsp"; 
				
				
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String search_customer_id = request.getParameter("search_customer_id");
		String search_customer_name = request.getParameter("search_customer_name");
		
		
		
		
	
		//DBから検索商品データ情報取得
		List<CustomerSearch> customerSearchList = null;
		customer_mstDAO dao = new customer_mstDAO();
		
		
		
		try {
			customerSearchList = dao.customerSearch(search_customer_id, search_customer_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(customerSearchList != null) {
			//セッション開始
			HttpSession session = request.getSession(false);
			
			//商品データをセッションスコープに保存
			session.setAttribute("customerSearch",customerSearchList);
					
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

}