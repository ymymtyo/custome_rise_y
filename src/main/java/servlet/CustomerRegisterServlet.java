package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.customer_mstDAO;

/**
 * Servlet implementation class CustomerRegisterServlet
 */
@WebServlet("/CustomerRegisterServlet")
public class CustomerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//顧客データ登録サーブレット
		
		
				///フォワード先のURL
				String url = "/view/jsp/completion.jsp"; 
						
				//ID桁数
				int Length = 4;
				
				
				
				//リクエストパラメータの取得
				request.setCharacterEncoding("UTF-8");
				String customer_id = request.getParameter("customer_id");
				String customer_name = request.getParameter("customer_name");
							
				
			
				
				 //重複チェック
				 customer_mstDAO dao = new customer_mstDAO();
				 
			    try {
					int count = dao.selectByCustomerIdCheck(customer_id);
					
					if(count == 0) {
								 
				 //DBデータへデータ登録
				try {
					if(customer_id.length() == Length) {
						
					dao.create(customer_id,customer_name);
					
					request.setAttribute("msg5","顧客データを登録しました");
					}else {
						request.setAttribute("errMsg7","顧客IDは4桁で入力してください");
					}
							
				    } catch (Exception e) {
					   e.printStackTrace();
				     }	
				
				}else {
					request.setAttribute("errMsg5","登録済、または削除済の顧客IDと重複しているため登録できませんでした");
				  }
			 } catch (Exception e) {
				   e.printStackTrace();
			     }	
			    
				
					
						
				// JSPへフォワード
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc
						.getRequestDispatcher(url);
				rd.forward(request, response);
						
			}
	
	}


