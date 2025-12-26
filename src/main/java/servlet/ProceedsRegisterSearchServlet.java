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

import DAO.proceeds_mstDAO;
import dbbean.CustomerRegisterSearch;

/**
 * Servlet implementation class CustomerRegisterSearchServlet
 */
@WebServlet("/ProceedsRegisterSearchServlet.java")
public class ProceedsRegisterSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//顧客データ検索サーブレット
		
		
				///フォワード先のURL
				String url = "/view/jsp/proceeds_search.jsp"; 
						
						
				//リクエストパラメータの取得
				request.setCharacterEncoding("UTF-8");
				String search_person = request.getParameter("search_person");
				String search_sales_date = request.getParameter("search_sales_date");
				String search_customer_name = request.getParameter("search_customer_name");
				String search_goods_name = request.getParameter("search_goods_name");
				
				
				
				
			
				//DBから検索商品データ情報取得
				List<CustomerRegisterSearch> customerRegisterSearchList = null;
				proceeds_mstDAO dao = new proceeds_mstDAO();
				
				
				
				try {
					customerRegisterSearchList = dao.customerRegisterSearch(search_person, search_sales_date, search_customer_name ,search_goods_name);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(customerRegisterSearchList != null) {
					//セッション開始
					HttpSession session = request.getSession(false);
					
					//商品データをセッションスコープに保存
					session.setAttribute("customerRegisterSearchList",customerRegisterSearchList);
							
				}else {
					// リクエストスコープにエラーメッセージ格納
					request.setAttribute("errMsg7", "売上データが取得できませんでした");
					url = "/view/jsp/errPage1.jsp";
					
				}
			
						
				// JSPへフォワード
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc
						.getRequestDispatcher(url);
				rd.forward(request, response);


		}

		}