package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.proceeds_mstDAO;

/**
 * Servlet implementation class ProceedsDeleteServlet
 */
@WebServlet("/ProceedsDeleteServlet")
public class ProceedsDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//売上データ削除サーブレット
		
		
				///フォワード先のURL
				String url = "/view/jsp/completion.jsp"; 
						
						
				//リクエストパラメータの取得
				request.setCharacterEncoding("UTF-8");
				String proceeds_id = request.getParameter("proceeds_id");
				
				
				 proceeds_mstDAO dao = new proceeds_mstDAO();
				 
			    						 
				 //削除
				try {
					dao.delete(proceeds_id);
					
					request.setAttribute("msg9","売上データを削除しました");
							
				    } catch (Exception e) {
					   e.printStackTrace();
				     }	
				
				
			
			    
					
						
				// JSPへフォワード
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc
						.getRequestDispatcher(url);
				rd.forward(request, response);
			 
			 } 
						
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
