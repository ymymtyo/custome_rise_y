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

/**
 * Servlet implementation class goodsMenuTopServlet
 */
@WebServlet("/goodsMenuTopServlet")
public class goodsMenuTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//フォワード先のURL
		String url = "/view/jsp/goods_menu.jsp"; 
			
		
		
		//DBから全商品データ情報取得
		List<Goods> goodsList = null;
		goods_mstDAO dao = new goods_mstDAO();
		
		
		
		try {
			goodsList = dao.selectByIdNamePriceCost();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(goodsList != null) {
			//セッション開始
			HttpSession session = request.getSession(false);
			
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
