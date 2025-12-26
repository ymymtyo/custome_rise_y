package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.goods_mstDAO;

/**
 * Servlet implementation class GoodsDeleteServlet
 */
@WebServlet("/GoodsDeleteServlet")
public class GoodsDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
 //商品データ削除サーブレット
		
		
		///フォワード先のURL
		String url = "/view/jsp/completion.jsp"; 
				
				
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String goods_id = request.getParameter("goods_id");
		
		
		 goods_mstDAO dao = new goods_mstDAO();
		 
	    						 
		 //削除
		try {
			dao.delete(goods_id);
			
			request.setAttribute("msg7","商品データを削除しました");
					
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
		
	}

}
