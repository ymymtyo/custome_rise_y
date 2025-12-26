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
 * Servlet implementation class CustomerDeleteServlet
 */
@WebServlet("/CustomerDeleteServlet")
public class CustomerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//顧客データ削除サーブレット
		
		
				///フォワード先のURL
				String url = "/view/jsp/completion.jsp"; 
						
						
				//リクエストパラメータの取得
				request.setCharacterEncoding("UTF-8");
				String customer_id = request.getParameter("customer_id");
				
				
				 customer_mstDAO dao = new customer_mstDAO();
				 
			    						 
				 //削除
				try {
					dao.delete(customer_id);
					
					request.setAttribute("msg8","顧客データを削除しました");
							
				    } catch (Exception e) {
					   e.printStackTrace();
				     }	
				
				
			
			    
					
						
				// JSPへフォワード
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc
						.getRequestDispatcher(url);
				rd.forward(request, response);
			 
			 } 
				
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
