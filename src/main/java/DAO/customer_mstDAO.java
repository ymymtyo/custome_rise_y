package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbbean.Customer;
import dbbean.CustomerSearch;
import dbbean.CustomerUpdate;

public class customer_mstDAO {
	
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
	
	
	//顧客データを新規挿入するクエリ
	private static final String QUERY_INSERT_FOR_CUSTOMER = "INSERT INTO customer_mst ( customer_id, customer_name, delete_at) VALUES ( ?, ?, ?)";
	
	//顧客データを参照するクエリ
	private static final String QUERY_SELECT_LIST_BY_CUSTOMER = "SELECT customer_id, customer_name  FROM customer_mst WHERE delete_at = 0 ORDER BY customer_id ASC";
	
	//データの重複をを参照するクエリ
	private static final String QUERY_SELECT_LIST_BY_CUSTOMER_ID = "SELECT customer_id, customer_name FROM customer_mst WHERE customer_id = ?";
	
	//顧客データを検索するクエリ
	private static String QUERY_CUSTOMER_SEARCH= "SELECT customer_id ,customer_name FROM customer_mst WHERE delete_at = 0";
	
	//顧客データを削除するクエリ
		private static String QUERY_CUSTOMER_DELETE= "UPDATE customer_mst SET delete_at = 1 where customer_id = ?";

    //編集する顧客データを選択するクエリ
		private static String QUERY_CUSTOMER_UPDATE_SELECT= "SELECT customer_id ,customer_name FROM customer_mst where customer_id = ?";

	//商品を編集するクエリ
	private static String QUERY_CUSTOMER_UPDATE ="UPDATE customer_mst SET customer_name = ? where customer_id = ?";	
		
		
	//顧客リスト
	List<Customer> customerList = new ArrayList<Customer>();

	//検索結果顧客リスト
			List<CustomerSearch> customerSearchList = new ArrayList<CustomerSearch>();
			
	//編集顧客リスト
	CustomerUpdate customerUpdate = new CustomerUpdate();
	
			
	/**
	 * 顧客データ（customer）を新たに作成する
	 *
	 * @param account 新たに作成される商品データオブジェクト
	 * @return 挿入されたレコードの数
	 * @throws Exception 
	 */
	
	public int create(String customer_id, String customer_name) throws Exception {
		int retValue = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// データベースに接続する
			conn = getConnection();

			// 使用するSQL文（INSERT文）を準備する
			pstmt = conn.prepareStatement(QUERY_INSERT_FOR_CUSTOMER);

			// SQL文のパラメータに顧客商品情報を設定する
			pstmt.setString(1, customer_id);
			pstmt.setString(2, customer_name);
			pstmt.setLong(3, 0);
			
			

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
			 * 顧客テーブルから検索した結果を返す
			 * @param customer_id 顧客ID 
			 * @paramcustomer_name 顧客名
			 * @return 顧客データ（customerList）
			 * @throws Exception 
			 */
			public List<Customer> selectByIdName() throws Exception {
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					conn= getConnection();
					pstmt = conn.prepareStatement(QUERY_SELECT_LIST_BY_CUSTOMER);
										
					rs = pstmt.executeQuery();
					
				
					// 結果セットの終わりまで1行ずつ読み込む
					while (rs.next()) {
						// 読み込んだ商品を格納するCustomerオブジェクトを生成する
						Customer customer = new Customer();

						// 結果セットの内容を取得してGoodsオブジェクトに設定する
						 customer.setCustomer_id(rs.getString("customer_id"));
						 customer.setCustomer_name(rs.getString("customer_name"));
						
						

						// 顧客データ情報を格納したGoodsオブジェクトを戻り値用のリストに追加する
						 customerList.add(customer);
						
					}
					// 読み込まれた顧客データのリストを返す
					return customerList;
					
					
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
			
			
			//重複チェックメソッド
			public int selectByCustomerIdCheck(String customer_id) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int count = 0 ;
				
				try {
					conn= getConnection();
					pstmt = conn.prepareStatement(QUERY_SELECT_LIST_BY_CUSTOMER_ID);
					
					// SQL文のパラメータに登録商品情報を設定する
					pstmt.setString(1, customer_id);
					rs = pstmt.executeQuery();
					
					
					// 結果セットの終わりまで1行ずつ読み込む
					while (rs.next()) {
						// 読み込んだ商品を格納するCustomerオブジェクトを生成する
						Customer customer = new Customer();

						// 結果セットの内容を取得してCustomerオブジェクトに設定する
						 customer.setCustomer_id(rs.getString("customer_id"));
						 customer.setCustomer_name(rs.getString("customer_name"));
						

						// 顧客データ情報を格納したGoodsオブジェクトを戻り値用のリストに追加する
						 customerList.add(customer);
						
						count = customerList.size();
						
											
					}
					// 読み込まれた商品データの重複件数を返す
					return count;
					
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
			public List<CustomerSearch> customerSearch(String search_customer_id, String search_customer_name) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					conn= getConnection();
					
					if(!search_customer_id.isEmpty() && !search_customer_name.isEmpty()) {
						QUERY_CUSTOMER_SEARCH = "SELECT customer_id ,customer_name FROM customer_mst WHERE customer_id LIKE ? and customer_name LIKE ? and delete_at = 0";
					}else if(!search_customer_id.isEmpty() && search_customer_name.isEmpty()){
						QUERY_CUSTOMER_SEARCH = "SELECT customer_id ,customer_name FROM customer_mst WHERE customer_id LIKE ? and delete_at = 0";
					}else if(search_customer_id.isEmpty() && !search_customer_name.isEmpty()){
						QUERY_CUSTOMER_SEARCH = "SELECT customer_id ,customer_name FROM customer_mst WHERE customer_name LIKE ? and delete_at = 0";
					}else {
						QUERY_CUSTOMER_SEARCH = "SELECT customer_id ,customer_name FROM customer_mst WHERE delete_at = 0";
					}
										 					
					pstmt = conn.prepareStatement(QUERY_CUSTOMER_SEARCH);
					
					
					// SQL文のパラメータに検索商品情報を設定する
					
					if(!search_customer_id.isEmpty() && !search_customer_name.isEmpty()){
					  pstmt.setString(1,"%" + search_customer_id + "%");
					  pstmt.setString(2, "%" + search_customer_name + "%");
					}else if(!search_customer_id.isEmpty() && search_customer_name.isEmpty()) {
						pstmt.setString(1,"%" + search_customer_id + "%");	
					}else if(search_customer_id.isEmpty() && !search_customer_name.isEmpty()) {
						pstmt.setString(1,"%" + search_customer_name + "%");
					}
										
					rs = pstmt.executeQuery();
					
				
					// 結果セットの終わりまで1行ずつ読み込む
					while (rs.next()) {
						// 読み込んだ検索商品を格納するSearchCustomerオブジェクトを生成する
						CustomerSearch customerSearch = new CustomerSearch();

						// 結果セットの内容を取得してcustomerオブジェクトに設定する
						customerSearch.setSearch_customer_id(rs.getString("customer_id"));
						customerSearch.setSearch_customer_name(rs.getString("customer_name"));
						
						
						

						// 商品データ情報を格納したSearchCustomerオブジェクトを戻り値用のリストに追加する
						customerSearchList.add(customerSearch);
						
					}
					// 読み込まれた商品データのリストを返す
					return customerSearchList;
					
					
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
			public void delete(String customer_id) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				Object rs = null;

				try {
					// データベースに接続する
					conn = getConnection();

					// 使用するSQL文（UPDATE文）を準備する
					pstmt = conn.prepareStatement(QUERY_CUSTOMER_DELETE);

					// SQL文のパラメータに登録顧客情報を設定する
					pstmt.setString(1, customer_id);
					
					

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
			public CustomerUpdate update_select(String customer_id) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					// データベースに接続する
					conn = getConnection();

					// 使用するSQL文（UPDATE文）を準備する
					pstmt = conn.prepareStatement(QUERY_CUSTOMER_UPDATE_SELECT);

					// SQL文のパラメータに登録商品情報を設定する
					pstmt.setString(1, customer_id);
					

					// SQL文を実行する
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						
						
						//オブジェクトにデータを一時格納
						customerUpdate.setUpdate_customer_id(rs.getString("customer_id"));
						customerUpdate.setUpdate_customer_name(rs.getString("customer_name"));
						
						
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
				return customerUpdate ;
				

			
			}
	
			
			//顧客データを編集するメソッド
			public CustomerUpdate update(String customer_id,String update_customer_name) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				Object rs = null;

				try {
					// データベースに接続する
					conn = getConnection();

					// 使用するSQL文（UPDATE文）を準備する
					pstmt = conn.prepareStatement(QUERY_CUSTOMER_UPDATE);

					// SQL文のパラメータに登録商品情報を設定する
					pstmt.setString(1,update_customer_name);
					pstmt.setString(2,customer_id);
					
					

					// SQL文を実行する
					rs = pstmt.executeUpdate();
					
					
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
				return customerUpdate;
							
				

			
			}

			



			
			}



			
     


			






