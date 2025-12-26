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

import DAO.goods_mstDAO;
import dbbean.Goods;
import dbbean.GoodsSearch;

/**
 * Servlet implementation class GoodsSearchServlet
 */
@WebServlet("/GoodsSearchServlet")
public class GoodsSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//商品データ検索サーブレット
		
		
				///フォワード先のURL
				String url = "/view/jsp/goods_search.jsp"; 
						
						
				//リクエストパラメータの取得
				request.setCharacterEncoding("UTF-8");
				String search_goods_id = request.getParameter("search_goods_id");
				String search_goods_name = request.getParameter("search_goods_name");
				
				
				
				
			
				//DBから検索商品データ情報取得
				List<Goods> goodsList = null;
				List<GoodsSearch> goodsSearchList = null;
				goods_mstDAO dao = new goods_mstDAO();
				
				
				
				try {
					goodsSearchList = dao.goodsSearch(search_goods_id, search_goods_name);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(goodsSearchList != null) {
					//セッション開始
					HttpSession session = request.getSession(false);
					
					//検索商品データをセッションスコープに保存
					session.setAttribute("goodsSearch",goodsSearchList);
					
					//商品データをセッションスコープに保存
					session.setAttribute("goods",goodsList);
							
				}else {
					// リクエストスコープにエラーメッセージ格納
					request.setAttribute("errMsg2", "商品データが取得できませんでした");
					url = "/view/jsp/errPage1.jsp";
					
				}
			
						
				// JSPへフォワード
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc
						.getRequestDispatcher(url);
				rd.forward(request, response);
		
		
	}

}
