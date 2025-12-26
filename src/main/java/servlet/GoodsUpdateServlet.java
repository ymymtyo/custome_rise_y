package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.goods_mstDAO;
import dbbean.GoodsUpdate;

/**
 * Servlet implementation class GoodsUpdateServlet
 */
@WebServlet("/GoodsUpdateServlet")
public class GoodsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 「商品編集画面」への遷移
		
		///フォワード先のURL
		String url = "view/jsp/goods_update.jsp"; 
		
		
				
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String goods_id = request.getParameter("goods_id");
		

		   goods_mstDAO dao = new goods_mstDAO();
		   
		 
				
			// リンクで選択された商品情報を取得する
		   GoodsUpdate goodsUpdate = null;
		   
		 
				
				try {
					goodsUpdate = dao.update_select(goods_id);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				

				// 遷移先画面に値を渡す
				request.setAttribute("goodsUpdate", goodsUpdate);
				request.setAttribute("goods_id", goods_id);
				

				RequestDispatcher dispatcher =
						request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 商品編集処理
		
		///フォワード先のURL
				String url = "view/jsp/completion.jsp"; 
		
		        // 文字コードの設定
				response.setContentType("text/html; charset=UTF-8");
				request.setCharacterEncoding("UTF-8");

				String goods_id = request.getParameter("goods_id");
				String update_goods_name = request.getParameter("update_goods_name");
				int update_goods_price = Integer.parseInt(request.getParameter("update_goods_price"));
				int update_goods_cost = Integer.parseInt(request.getParameter("update_goods_cost"));

				goods_mstDAO dao = new goods_mstDAO();
				
				// リンクで選択された商品情報を取得する
				   GoodsUpdate goodsUpdate = null;
				
				// 商品情報編集（更新）処理を実行
				try {
					goodsUpdate = dao.update(goods_id, update_goods_name, update_goods_price, update_goods_cost);
				} catch (Exception e) {
					
					e.printStackTrace();
				}

				
				
				
				// 格納した商品情報を遷移先の画面に渡す
				request.setAttribute("msg10", "商品情報を更新しました");

				RequestDispatcher dispatcher =
						request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
		
	}

}
