package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbbean.CustomerRegister;
import dbbean.CustomerRegisterSearch;
import dbbean.CustomerRegisterUpdate;

public class proceeds_mstDAO {
	
	
	/**
	 *コネクションを取得する
	 *@return コネクション
	 *@throws SQLException
	 *@throws ClassNotFoundException
	 */
	
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		Connection conn = null;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerise","root", "mysql");
		return conn;
	}
	
	
	//売上データを新規挿入するクエリ
	private static final String QUERY_INSERT_FOR_PROCEEDS = "INSERT INTO proceeds_mst (person, sales_date, customer_id, goods_id, pcs, delete_at) VALUES (?, ? ,?, ?, ?, ?)";
	
	
	//売上データ、顧客データ、商品データを結合して売上データを参照するクエリ
	private static final String QUERY_SELECT_JOIN_ALL= "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
	
	//売上データを検索するクエリ
		private static String QUERY_PROCEEDS_SEARCH= "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE proceeds_mst1.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";

    //売上データを削除するクエリ
		private static String QUERY_PROCEEDS_DELETE= "UPDATE proceeds_mst SET proceeds_mst.delete_at = 1 where proceeds_id = ?";
	
	//編集する売上データを選択するクエリ
	private static String QUERY_PROCEEDS_UPDATE_SELECT= "SELECT proceeds_mst.goods_id, proceeds_mst.customer_id, proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE proceeds_id = ?";
		
		
		
	//売上表示リスト
	List<CustomerRegister> customerRegisterList = new ArrayList<CustomerRegister>();
		
	//売上検索結果リスト
	List<CustomerRegisterSearch> customerRegisterSearchList = new ArrayList<CustomerRegisterSearch>();
	
	//編集売上リスト
	CustomerRegisterUpdate customerRegisterUpdate = new CustomerRegisterUpdate();
	
	/**
	 * 商品データ（proceeds）を新たに作成する
	 *
	 * @param proceed 新たに作成される商品データオブジェクト
	 * @return 挿入されたレコードの数
	 * @throws Exception 
	 */
		public int create(String person, String sales_date , String customer_id ,String  goods_id, int pcs) throws Exception {
		int retValue = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// データベースに接続する
			conn = getConnection();

			// 使用するSQL文（INSERT文）を準備する
			pstmt = conn.prepareStatement(QUERY_INSERT_FOR_PROCEEDS);

			// SQL文のパラメータに登録商品情報を設定する
			pstmt.setString(1, person);
			pstmt.setString(2, sales_date);
			pstmt.setString(3, customer_id);
			pstmt.setString(4, goods_id);
			pstmt.setInt(5, pcs);
			pstmt.setInt(6, 0);
			
			
			
			
			// SQL文を実行する
			retValue = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

			// システム例外：データベースアクセス中にエラーが発生した
			throw new Exception(e.getMessage());
		} finally {
			// 準備したSQL文の後片付けを行う
			if (null != pstmt) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}

			// データベース接続の後片付けを行う
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		// 挿入されたレコードの数を返す
		return retValue;
	}
	
	
	
			/**
			 * proceedsテーブルから検索した結果を返す
			 * * @param proceeds_id 売上ID
			 * @param person 担当者
			 * @param sales_date 売上日
			 * @param customer_name 顧客名
			 * @param goods_name 商品名
			 * @param goods_price 商品単価
			 * @param pcs 数量
			 * @param goods_cost 原価 
			 * @return 売上データ（proceedsList）
			 * @throws Exception 
			 */
			public List<CustomerRegister> selectByProceeds() throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					conn= getConnection();
					pstmt = conn.prepareStatement(QUERY_SELECT_JOIN_ALL); //データ結合後、データ取得
					rs = pstmt.executeQuery();
					
				
					// 結果セットの終わりまで1行ずつ読み込む
					while (rs.next()) {
						// 読み込んだ売上データを格納するproceedsオブジェクトを生成する
						CustomerRegister customerRegister = new CustomerRegister();

						// 結果セットの内容を取得してcustomerRegisterオブジェクトに設定する
						customerRegister.setProceeds_id(rs.getString("proceeds_id"));
						customerRegister.setPerson(rs.getString("person"));
						customerRegister.setPcs(rs.getInt("pcs"));
						customerRegister.setSales_date(rs.getDate("sales_date"));
						customerRegister.setGoods_name(rs.getString("goods_name"));
						customerRegister.setGoods_cost(rs.getInt("goods_cost"));
						customerRegister.setGoods_price(rs.getInt("goods_price"));
						customerRegister.setCustomer_name(rs.getString("customer_name"));
					
						

						// 商品データ情報を格納したGoodsオブジェクトを戻り値用のリストに追加する
						customerRegisterList.add(customerRegister);
						
					}
					// 読み込まれた商品データのリストを返す
					return customerRegisterList;
					
					
				} catch (SQLException e) {
					e.printStackTrace();

					// システム例外：データベースアクセス中にエラーが発生した
					throw new Exception(e.getMessage());
				} finally {
					// 取得した結果セットの後片付けを行う
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException e) {
						}
					}

					// 準備したSQL文の後片付けを行う
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException e) {
						}
					}

					// データベース接続の後片付けを行う
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
				}

				
			}
			
			
			
			//検索メソッド
			public List<CustomerRegisterSearch> customerRegisterSearch(String search_person, String search_sales_date, String search_customer_name ,String search_goods_name) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					conn= getConnection();
					
				    if(!search_person.isEmpty() && !search_sales_date.isEmpty() && !search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE person LIKE ? and sales_date LIKE ? and customer_name LIKE ? and goods_name LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(!search_person.isEmpty() && search_sales_date.isEmpty() && search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE person LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(search_person.isEmpty() && !search_sales_date.isEmpty() && search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE sales_date LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(search_person.isEmpty() && search_sales_date.isEmpty() && !search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE customer_name LIKE ? and proceeds_mst.delete_at = 0 and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(search_person.isEmpty() && search_sales_date.isEmpty() && search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE goods_name LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(!search_person.isEmpty() && !search_sales_date.isEmpty() && search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE person LIKE ? and sales_date LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(!search_person.isEmpty() && search_sales_date.isEmpty() && !search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE person LIKE ? and customer_name LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(!search_person.isEmpty() && search_sales_date.isEmpty() && search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE person LIKE ? and goods_name LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(search_person.isEmpty() && !search_sales_date.isEmpty() && !search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE sales_date LIKE ? and customer_name LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(search_person.isEmpty() && !search_sales_date.isEmpty() && search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE sales_date LIKE ? and goods_name LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(search_person.isEmpty() && search_sales_date.isEmpty() && !search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE customer_name LIKE ? and goods_name LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(!search_person.isEmpty() && !search_sales_date.isEmpty() && !search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE person LIKE ? and sales_date LIKE ? and customer_name LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(!search_person.isEmpty() && !search_sales_date.isEmpty() && search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE person LIKE ? and sales_date LIKE ? and goods_name LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(search_person.isEmpty() && !search_sales_date.isEmpty() && !search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE sales_date LIKE ? and customer_name LIKE ? and goods_name LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else if(!search_person.isEmpty() && !search_sales_date.isEmpty() && !search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE person LIKE ? and sales_date LIKE ? and customer_name LIKE ? and goods_name LIKE ? and proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}else {
						QUERY_PROCEEDS_SEARCH = "SELECT proceeds_mst.proceeds_id, proceeds_mst.person, proceeds_mst.pcs, proceeds_mst.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst JOIN goods_mst ON proceeds_mst.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst.customer_id = customer_mst.customer_id WHERE proceeds_mst.delete_at = 0 ORDER BY proceeds_mst.sales_date ASC";
					}
										 					
					pstmt = conn.prepareStatement(QUERY_PROCEEDS_SEARCH);
					
					
					// SQL文のパラメータに検索商品情報を設定する
					
					if(!search_person.isEmpty() && !search_sales_date.isEmpty() && !search_customer_name.isEmpty() &&! search_goods_name.isEmpty()){
					  pstmt.setString(1,"%" + search_person + "%");
					  pstmt.setString(2, "%" + search_sales_date + "%");
					  pstmt.setString(3, "%" + search_customer_name + "%");
					  pstmt.setString(4, "%" + search_goods_name + "%");
					}else if(!search_person.isEmpty() && search_sales_date.isEmpty() && search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
						pstmt.setString(1,"%" + search_person + "%");	
					}else if(search_person.isEmpty() && !search_sales_date.isEmpty() && search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
						pstmt.setString(1,"%" + search_sales_date + "%");
					}else if(search_person.isEmpty() && search_sales_date.isEmpty() && !search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
	     				pstmt.setString(1,"%" + search_customer_name + "%");
					}else if(search_person.isEmpty() && search_sales_date.isEmpty() && search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						pstmt.setString(1,"%" + search_goods_name + "%");
					}else if(!search_person.isEmpty() && !search_sales_date.isEmpty() && search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
					    pstmt.setString(1,"%" + search_person + "%");
						pstmt.setString(2, "%" + search_sales_date + "%");
					}else if(!search_person.isEmpty() && search_sales_date.isEmpty() && !search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
						 pstmt.setString(1,"%" + search_person + "%");
						 pstmt.setString(2, "%" + search_customer_name + "%");
					}else if(!search_person.isEmpty() && search_sales_date.isEmpty() && search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						 pstmt.setString(1,"%" + search_person + "%");
						 pstmt.setString(2, "%" + search_goods_name + "%");
					}else if(search_person.isEmpty() && !search_sales_date.isEmpty() && !search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
						 pstmt.setString(1,"%" + search_sales_date + "%");
						 pstmt.setString(2, "%" + search_customer_name + "%");
					}else if(search_person.isEmpty() && !search_sales_date.isEmpty() && search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						 pstmt.setString(1,"%" + search_sales_date + "%");
						 pstmt.setString(2, "%" + search_goods_name + "%");
					}else if(search_person.isEmpty() && search_sales_date.isEmpty() && !search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						 pstmt.setString(1,"%" + search_customer_name + "%");
						 pstmt.setString(2, "%" + search_goods_name + "%");
	   			    }else if(!search_person.isEmpty() && !search_sales_date.isEmpty() && !search_customer_name.isEmpty() && search_goods_name.isEmpty()) {
						 pstmt.setString(1,"%" + search_person + "%");
						 pstmt.setString(2, "%" + search_sales_date + "%");
						 pstmt.setString(3, "%" + search_customer_name + "%");
					}else if(!search_person.isEmpty() && !search_sales_date.isEmpty() && search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						 pstmt.setString(1,"%" + search_person + "%");
						 pstmt.setString(2, "%" + search_sales_date + "%");
						 pstmt.setString(3, "%" + search_goods_name + "%");  
					}else if(!search_person.isEmpty() && search_sales_date.isEmpty() && !search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						 pstmt.setString(1,"%" + search_person + "%");
						 pstmt.setString(2, "%" + search_customer_name + "%");
						 pstmt.setString(3, "%" + search_goods_name + "%");
					}else if(search_person.isEmpty() && !search_sales_date.isEmpty() && !search_customer_name.isEmpty() && !search_goods_name.isEmpty()) {
						 pstmt.setString(1,"%" + search_sales_date + "%");
						 pstmt.setString(2, "%" + search_customer_name + "%");
						 pstmt.setString(3, "%" + search_goods_name + "%");
					}
										
					rs = pstmt.executeQuery();
					
				
					// 結果セットの終わりまで1行ずつ読み込む
					while (rs.next()) {
						// 読み込んだ検索商品を格納するSearchGoodsオブジェクトを生成する
						CustomerRegisterSearch customerRegisterSearch = new CustomerRegisterSearch();

						// 結果セットの内容を取得して売上オブジェクトに設定する
						customerRegisterSearch.setSearch_proceeds_id(rs.getString("proceeds_id"));
						customerRegisterSearch.setSearch_person(rs.getString("person"));
						customerRegisterSearch.setSearch_pcs(rs.getInt("pcs"));
						customerRegisterSearch.setSearch_sales_date(rs.getDate("sales_date"));
						customerRegisterSearch.setSearch_goods_name(rs.getString("goods_name"));
						customerRegisterSearch.setSearch_goods_price(rs.getInt("goods_price"));
						customerRegisterSearch.setSearch_goods_cost(rs.getInt("goods_cost"));
						customerRegisterSearch.setSearch_customer_name(rs.getString("customer_name"));
						
						
						
										

						// 商品データ情報を格納したSearchGoodsオブジェクトを戻り値用のリストに追加する
						customerRegisterSearchList.add(customerRegisterSearch);
						
					}
					// 読み込まれた商品データのリストを返す
					return customerRegisterSearchList;
					
					
				} catch (SQLException e) {
					e.printStackTrace();

					// システム例外：データベースアクセス中にエラーが発生した
					throw new Exception(e.getMessage());
				} finally {
					// 取得した結果セットの後片付けを行う
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException e) {
						}
					}

					// 準備したSQL文の後片付けを行う
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException e) {
						}
					}

					// データベース接続の後片付けを行う
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
				}

				
			}
				
			
			
			//削除メソッド
			public void delete(String proceeds_id) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				Object rs = null;

				try {
					// データベースに接続する
					conn = getConnection();

					// 使用するSQL文（UPDATE文）を準備する
					pstmt = conn.prepareStatement(QUERY_PROCEEDS_DELETE);

					// SQL文のパラメータに登録商品情報を設定する
					pstmt.setString(1, proceeds_id);
					
					

					// SQL文を実行する
					rs = pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();

					// システム例外：データベースアクセス中にエラーが発生した
					throw new Exception(e.getMessage());
				} finally {
					// 準備したSQL文の後片付けを行う
					if (null != pstmt) {
						try {
							pstmt.close();
						} catch (SQLException e) {
						}
					}

					// データベース接続の後片付けを行う
					if (null != conn) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
				}

			
			}			
			

			//編集する商品データを選択するメソッド
			public CustomerRegisterUpdate update_select(String proceeds_id) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					// データベースに接続する
					conn = getConnection();

					// 使用するSQL文（UPDATE文）を準備する
					pstmt = conn.prepareStatement(QUERY_PROCEEDS_UPDATE_SELECT);

					// SQL文のパラメータに登録商品情報を設定する
					pstmt.setString(1, proceeds_id);
					

					// SQL文を実行する
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						
											
						//オブジェクトにデータを一時格納
						customerRegisterUpdate.setUpdate_proceeds_id(rs.getString("proceeds_id"));
						customerRegisterUpdate.setUpdate_person(rs.getString("person"));
						customerRegisterUpdate.setUpdate_pcs(rs.getInt("pcs"));
						customerRegisterUpdate.setUpdate_sales_date(rs.getDate("sales_date"));
						customerRegisterUpdate.setUpdate_goods_name(rs.getString("goods_name"));
						customerRegisterUpdate.setUpdate_goods_id(rs.getString("goods_id"));
						customerRegisterUpdate.setUpdate_goods_price(rs.getInt("goods_price"));
						customerRegisterUpdate.setUpdate_goods_cost(rs.getInt("goods_cost"));
						customerRegisterUpdate.setUpdate_customer_name(rs.getString("customer_name"));
						customerRegisterUpdate.setUpdate_customer_id(rs.getString("customer_id"));
						
						
					}	
					
				} catch (SQLException e) {
					e.printStackTrace();

					// システム例外：データベースアクセス中にエラーが発生した
					throw new Exception();
				} finally {
					// 準備したSQL文の後片付けを行う
					if (null != pstmt) {
						try {
							pstmt.close();
						} catch (SQLException e) {
						}
					}

					// データベース接続の後片付けを行う
					if (null != conn) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
				}
				return customerRegisterUpdate ;
				

			
			}
			


		
			
			
			
			//売上データを編集するメソッド
			public CustomerRegisterUpdate update(String proceeds_id,String update_person, String update_sales_date, String update_customer_id, String update_goods_id, int update_pcs) throws ClassNotFoundException{
			  
			    	Connection conn = null;
					PreparedStatement parentGoodsStatement = null;
					PreparedStatement parentCustomerStatement = null;
			        PreparedStatement childStatement = null;
			        
			        try {
			        	// データベースに接続する
						conn = getConnection();
						
			            // トランザクションを開始
						conn.setAutoCommit(false);
						
			            // 親テーブルのデータを更新(商品名・顧客名)
						
						String updateParentGoodsSQL = "UPDATE proceeds_mst SET goods_id = ? WHERE proceeds_mst.proceeds_id = ?";
						parentGoodsStatement = conn.prepareStatement(updateParentGoodsSQL);
						parentGoodsStatement.setString(1,update_goods_id);
						parentGoodsStatement.setString(2, proceeds_id);
						parentGoodsStatement.executeUpdate();
			            
						
			            String updateParentCustomerSQL = "UPDATE proceeds_mst SET customer_id = ? WHERE proceeds_mst.proceeds_id = ?";
			            parentCustomerStatement = conn.prepareStatement(updateParentCustomerSQL);
			            parentCustomerStatement.setString(1,update_customer_id);
			            parentCustomerStatement.setString(2, proceeds_id);
			            parentCustomerStatement.executeUpdate();
			            
			            // 子テーブルのデータを更新（売上データテーブル）
			            String updateChildSQL = "UPDATE proceeds_mst SET proceeds_mst.person = ?, proceeds_mst.sales_date = ?, proceeds_mst.pcs = ? WHERE proceeds_id = ?";
			            childStatement = conn.prepareStatement(updateChildSQL);
			            childStatement.setString(1,update_person);
			            childStatement.setString(2,update_sales_date);
			            childStatement.setInt(3,update_pcs);
			            childStatement.setString(4, proceeds_id);
			            childStatement.executeUpdate();
			            
			            // すべての操作が成功した場合、トランザクションをコミット
			            conn.commit();
			            System.out.println("Transaction committed successfully.");
			        } catch (SQLException e) {
			            // エラーが発生した場合、トランザクションをロールバック
			            if (conn != null) {
			                try {
			                	conn.rollback();
			                    System.out.println("Transaction rolled back due to an error.");
			                } catch (SQLException rollbackException) {
			                    rollbackException.printStackTrace();
			                }
			            }
			            e.printStackTrace();
			        } finally {
			            // リソースを解放
			            try {
			                if (parentGoodsStatement != null) parentGoodsStatement.close();
			                if (parentCustomerStatement != null) parentCustomerStatement.close();
			                if (childStatement != null) childStatement.close();
			                if (conn != null) conn.close();
			            } catch (SQLException closeException) {
			                closeException.printStackTrace();
			            }
			        }
					return customerRegisterUpdate;
			    }
			




}
