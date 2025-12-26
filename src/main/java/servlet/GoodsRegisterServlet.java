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
 * Servlet implementation class GoodsRegisterServlet
 */
@WebServlet("/GoodsRegisterServlet")
public class GoodsRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
      //商品データ登録サーブレット
		
		
		///フォワード先のURL
		String url = "/view/jsp/completion.jsp"; 

		//ID桁数
		int Length = 4;
				
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String goods_id = request.getParameter("goods_id");
		String goods_name = request.getParameter("goods_name");
		int goods_price = Integer.parseInt(request.getParameter("goods_price"));
		int goods_cost = Integer.parseInt(request.getParameter("goods_cost"));
		
		
		
		 //重複チェック
		 goods_mstDAO dao = new goods_mstDAO();
		 
	    try {
			int count = dao.selectByIdCheck(goods_id);
			
			if(count == 0) {
						 
		 //DBデータへデータ登録
		try {
			if(goods_id.length() == Length) {
		
			dao.create(goods_id, goods_name, goods_price, goods_cost);
			
			request.setAttribute("msg3","商品データを登録しました");
			}else {
				request.setAttribute("errMsg8","商品IDは4桁で入力してください");
			}
					
		    } catch (Exception e) {
			   e.printStackTrace();
		     }	
		
		}else {
			request.setAttribute("errMsg3","登録済、または削除済の商品IDと重複しているため登録できませんでした");
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
   