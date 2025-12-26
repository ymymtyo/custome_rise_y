package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.customer_mstDAO;
import dbbean.CustomerUpdate;

/**
 * Servlet implementation class CustomerUpdateServlet
 */
@WebServlet("/CustomerUpdateServlet")
public class CustomerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 「顧客編集画面」への遷移
		
		///フォワード先のURL
		String url = "view/jsp/customer_update.jsp"; 
		
		
				
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String customer_id = request.getParameter("customer_id");
		

		   customer_mstDAO dao = new customer_mstDAO();
		   
		 
				
			// リンクで選択された顧客情報を取得する
		   CustomerUpdate customerUpdate = null;
		   
		 
				
				try {
					customerUpdate = dao.update_select(customer_id);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				

				// 遷移先画面に値を渡す
				request.setAttribute("customerUpdate", customerUpdate);
				request.setAttribute("customer_id", customer_id);
				

				RequestDispatcher dispatcher =
						request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 顧客編集処理
		
				///フォワード先のURL
						String url = "view/jsp/completion.jsp"; 
				
				        // 文字コードの設定
						response.setContentType("text/html; charset=UTF-8");
						request.setCharacterEncoding("UTF-8");

						String customer_id = request.getParameter("customer_id");
						String update_customer_name = request.getParameter("update_customer_name");
						

						customer_mstDAO dao = new customer_mstDAO();
						
						// リンクで選択された顧客情報を取得する
						CustomerUpdate customerUpdate = null;
						
						// 顧客情報編集（更新）処理を実行
						try {
							customerUpdate = dao.update(customer_id, update_customer_name);
						} catch (Exception e) {
							
							e.printStackTrace();
						}

						
						
						
						// 格納した商品情報を遷移先の画面に渡す
						request.setAttribute("msg11", "顧客情報を更新しました");

						RequestDispatcher dispatcher =
								request.getRequestDispatcher(url);
						dispatcher.forward(request, response);
				
			}
	}


