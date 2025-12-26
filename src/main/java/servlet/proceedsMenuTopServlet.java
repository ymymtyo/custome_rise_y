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

import DAO.customer_mstDAO;
import DAO.goods_mstDAO;
import DAO.proceeds_mstDAO;
import dbbean.Customer;
import dbbean.CustomerRegister;
import dbbean.Goods;

/**
 * Servlet implementation class proceedsMenuTopServlet
 */
@WebServlet("/proceedsMenuTopServlet")
public class proceedsMenuTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//フォワード先のURL
		String url = "/view/jsp/proceeds_menu.jsp"; 
		
		
		try {				
		//DBから売上データ情報取得
		List<CustomerRegister> customerRegisterList = null;
		proceeds_mstDAO daoCustomerRegister = new proceeds_mstDAO();
		customerRegisterList = daoCustomerRegister.selectByProceeds();
		
		//DBから顧客データ情報取得
		 List<Customer> customerList = null;
		 customer_mstDAO daoCustomer = new customer_mstDAO();
		 customerList = daoCustomer.selectByIdName();
		
				
		//DBから商品データ情報取得
		List<Goods> goodsList = null;
		goods_mstDAO daoGoods = new goods_mstDAO();
		goodsList = daoGoods.selectByIdNamePriceCost();
		
		//セッション開始
		HttpSession session = request.getSession(false);
	
			//リクエストスコープに保存
		 session.setAttribute("customerRegisterList", customerRegisterList);
		 session.setAttribute("goodsList",  goodsList);
		 session.setAttribute("customerList", customerList);
		
		 
		 
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
