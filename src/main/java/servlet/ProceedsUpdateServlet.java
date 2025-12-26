package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.proceeds_mstDAO;
import dbbean.CustomerRegisterUpdate;

/**
 * Servlet implementation class ProceedsUpdate
 */
@WebServlet("/ProceedsUpdateServlet")
public class ProceedsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 「売上編集画面」への遷移
		
				///フォワード先のURL
				String url = "view/jsp/proceeds_update.jsp"; 
				
				
						
				
				//リクエストパラメータの取得
				request.setCharacterEncoding("UTF-8");
				String proceeds_id = request.getParameter("proceeds_id");
				

				   proceeds_mstDAO dao = new proceeds_mstDAO();
				   
				 
						
					// リンクで選択された商品情報を取得する
				   CustomerRegisterUpdate  customerRegisterUpdate = null;
				   
				 
						
						try {
							customerRegisterUpdate = dao.update_select(proceeds_id);
						} catch (Exception e) {
							
							e.printStackTrace();
						}
						
						

						// 遷移先画面に値を渡す
						request.setAttribute("customerRegisterUpdate", customerRegisterUpdate);
						request.setAttribute("proceeds_id", proceeds_id);
						

						RequestDispatcher dispatcher =
								request.getRequestDispatcher(url);
						dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 売上編集処理
		
				///フォワード先のURL
						String url = "view/jsp/completion.jsp"; 
				
				        // 文字コードの設定
						response.setContentType("text/html; charset=UTF-8");
						request.setCharacterEncoding("UTF-8");

						String proceeds_id = request.getParameter("proceeds_id");
						String update_person = request.getParameter("update_person");
						String update_sales_date = request.getParameter("update_sales_date");
						String update_customer_id = request.getParameter("update_customer_id");
						String update_goods_id = request.getParameter("update_goods_id");
						int update_pcs = Integer.parseInt(request.getParameter("update_pcs"));

						 proceeds_mstDAO dao = new proceeds_mstDAO();
						
						// リンクで選択された商品情報を取得する
						 CustomerRegisterUpdate  customerRegisterUpdate = null;
						
						// 商品情報編集（更新）処理を実行
						try {
							customerRegisterUpdate = dao.update(proceeds_id, update_person, update_sales_date, update_customer_id,update_goods_id,update_pcs);
						} catch (Exception e) {
							
							e.printStackTrace();
						}

						
						
						
						// 格納した商品情報を遷移先の画面に渡す
						request.setAttribute("msg12", "売上情報を更新しました");

						RequestDispatcher dispatcher =
								request.getRequestDispatcher(url);
						dispatcher.forward(request, response);
		
		
	}

}
