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
 * Servlet implementation class ProceedsResisterServlet
 */
@WebServlet("/ProceedsResisterServlet")
public class ProceedsResisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//商品データ登録サーブレット
		
		
				///フォワード先のURL
				String url = "/view/jsp/completion.jsp"; 
				
				 						
				//リクエストパラメータの取得
				request.setCharacterEncoding("UTF-8");
				String person = request.getParameter("person");
				String sales_date = request.getParameter("sales_date");
				String customer_id = request.getParameter("customer_id");
				int pcs = Integer.parseInt(request.getParameter("pcs"));
				String goods_id = request.getParameter("goods_id");
				
		
				
				//sales_dateをStringからDate型へ変換
				
//				try {
//					SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
//					java.util.Date date = sdFormat.parse(sales_date);
//					
//				}catch(ParseException e) {
//					e.printStackTrace();
//				}
//				
						
				
				proceeds_mstDAO dao = new proceeds_mstDAO();
								 
				 //DBデータへデータ登録
				try {
					
					if(pcs >= 1 && pcs <= 99) {
						
					dao.create(person, sales_date, customer_id, goods_id, pcs);
					
					request.setAttribute("msg6","売上データを登録しました");
					
					}else {
						request.setAttribute("errMsg6","売上データを登録できませんでした。数量は1～99ヶで入力してください。");
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
		   