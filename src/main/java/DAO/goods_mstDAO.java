package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbbean.Goods;
import dbbean.GoodsSearch;
import dbbean.GoodsUpdate;

public class goods_mstDAO {
	
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
	
	
	//商品データを新規挿入するクエリ
	private static final String QUERY_INSERT_FOR_GOODS = "INSERT INTO goods_mst ( goods_id, goods_name, goods_price, goods_cost, delete_at ) VALUES ( ?, ?, ?, ?, ? )";
	
	//商品データを参照するクエリ
	private static final String QUERY_SELECT_LIST_BY_GOODS = "SELECT goods_id, goods_name, goods_price, goods_cost FROM goods_mst WHERE delete_at = 0 ORDER BY goods_id ASC";
	
	//商品データの重複をを参照するクエリ
	private static final String QUERY_SELECT_LIST_BY_ID = "SELECT goods_id ,goods_name, goods_price, goods_cost FROM goods_mst WHERE goods_id = ?";
		
	//商品データを検索するクエリ
	private static String QUERY_GOODS_SEARCH= "SELECT goods_id ,goods_name, goods_price, goods_cost FROM goods_mst WHERE delete_at = 0";
	
	//商品データを削除するクエリ
	private static String QUERY_GOODS_DELETE= "UPDATE goods_mst SET delete_at = 1 where goods_id = ?";
	
	//編集する商品データを選択するクエリ
	private static String QUERY_GOODS_UPDATE_SELECT= "SELECT goods_id ,goods_name, goods_price, goods_cost FROM goods_mst where goods_id = ?";
		
	//商品を編集するクエリ
	private static String QUERY_GOODS_UPDATE ="UPDATE goods_mst SET goods_name = ?, goods_price = ?, goods_cost = ? where goods_id = ?";
	
	
		//商品リスト
	List<Goods> goodsList = new ArrayList<Goods>();
	
	//検索結果商品リスト
		List<GoodsSearch> goodsSearchList = new ArrayList<GoodsSearch>();
		
	//編集商品リスト
		GoodsUpdate goodsUpdate = new GoodsUpdate();


		
	
	
	/**
	 * 商品データ（goods）を新たに作成する
	 *
	 * @param account 新たに作成される商品データオブジェクト
	 * @return 挿入されたレコードの数
	 * @throws Exception 
	 */
		public int create(String goods_id, String goods_name, int goods_price, int goods_cost) throws Exception {
		int retValue = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// データベースに接続する
			conn = getConnection();

			// 使用するSQL文（INSERT文）を準備する
			pstmt = conn.prepareStatement(QUERY_INSERT_FOR_GOODS);

			// SQL文のパラメータに登録商品情報を設定する
			pstmt.setString(1, goods_id);
			pstmt.setString(2, goods_name);
			pstmt.setInt(3, goods_price);
			pstmt.setInt(4, goods_cost);
			pstmt.setLong(5, 0);
			

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
			 * 商品テーブルから検索した結果を返す
			 * @param goods_id 商品ID 
			 * @param goods_name 商品名
			 * @param goods_price 商品単価
			 * @param goods_cost 商品原価
			 * @return 商品データ（goodsList）
			 * @throws Exception 
			 */
			public List<Goods> selectByIdNamePriceCost() throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					conn= getConnection();
					pstmt = conn.prepareStatement(QUERY_SELECT_LIST_BY_GOODS);
										
					rs = pstmt.executeQuery();
					
				
					// 結果セットの終わりまで1行ずつ読み込む
					while (rs.next()) {
						// 読み込んだ商品を格納するGoodsオブジェクトを生成する
						Goods goods = new Goods();

						// 結果セットの内容を取得してGoodsオブジェクトに設定する
						goods.setGoods_id(rs.getString("goods_id"));
						goods.setGoods_name(rs.getString("goods_name"));
						goods.setGoods_price(rs.getInt("goods_price"));
						goods.setGoods_cost(rs.getInt("goods_cost"));
						

						// 商品データ情報を格納したGoodsオブジェクトを戻り値用のリストに追加する
						goodsList.add(goods);
						
					}
					// 読み込まれた商品データのリストを返す
					return goodsList;
					
					
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
			public int selectByIdCheck(String goods_id) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int count = 0 ;
				
				try {
					conn= getConnection();
					pstmt = conn.prepareStatement(QUERY_SELECT_LIST_BY_ID);
					
					// SQL文のパラメータに登録商品情報を設定する
					pstmt.setString(1, goods_id);
					rs = pstmt.executeQuery();
					
					
					// 結果セットの終わりまで1行ずつ読み込む
					while (rs.next()) {
						// 読み込んだ商品を格納するGoodsオブジェクトを生成する
						Goods goods = new Goods();

						// 結果セットの内容を取得してGoodsオブジェクトに設定する
						goods.setGoods_id(rs.getString("goods_id"));
						goods.setGoods_name(rs.getString("goods_name"));
						goods.setGoods_price(rs.getInt("goods_price"));
						goods.setGoods_cost(rs.getInt("goods_cost"));
						

						// 商品データ情報を格納したGoodsオブジェクトを戻り値用のリストに追加する
						goodsList.add(goods);
						
						count = goodsList.size();
						
											
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
			public List<GoodsSearch> goodsSearch(String search_goods_id, String search_goods_name) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					conn= getConnection();
					
					if(!search_goods_id.isEmpty() && !search_goods_name.isEmpty()) {
						QUERY_GOODS_SEARCH = "SELECT goods_id ,goods_name, goods_price, goods_cost FROM goods_mst WHERE goods_id LIKE ? and goods_name LIKE ? and delete_at = 0";
					}else if(!search_goods_id.isEmpty() && search_goods_name.isEmpty()){
						QUERY_GOODS_SEARCH = "SELECT goods_id ,goods_name, goods_price, goods_cost FROM goods_mst WHERE goods_id LIKE ? and delete_at = 0";
					}else if(search_goods_id.isEmpty() && !search_goods_name.isEmpty()){
						QUERY_GOODS_SEARCH = "SELECT goods_id ,goods_name, goods_price, goods_cost FROM goods_mst WHERE goods_name LIKE ? and delete_at = 0";
					}else {
						QUERY_GOODS_SEARCH = "SELECT goods_id ,goods_name, goods_price, goods_cost FROM goods_mst WHERE delete_at = 0";
					}
										 					
					pstmt = conn.prepareStatement(QUERY_GOODS_SEARCH);
					
					
					// SQL文のパラメータに検索商品情報を設定する
					
					if(!search_goods_id.isEmpty() && !search_goods_name.isEmpty()){
					  pstmt.setString(1,"%" + search_goods_id +"%");
					  pstmt.setString(2, "%"+search_goods_name +"%");
					}else if(!search_goods_id.isEmpty() && search_goods_name.isEmpty()) {
						pstmt.setString(1,"%" +search_goods_id+ "%");	
					}else if(search_goods_id.isEmpty() && !search_goods_name.isEmpty()) {
						pstmt.setString(1,"%" +search_goods_name+"%") ;
					}
										
					rs = pstmt.executeQuery();
					
				
					// 結果セットの終わりまで1行ずつ読み込む
					while (rs.next()) {
						// 読み込んだ検索商品を格納するSearchGoodsオブジェクトを生成する
						GoodsSearch goodsSearch = new GoodsSearch();

						// 結果セットの内容を取得してGoodsオブジェクトに設定する
						goodsSearch.setSearch_goods_id(rs.getString("goods_id"));
						goodsSearch.setSearch_goods_name(rs.getString("goods_name"));
						goodsSearch.setSearch_goods_price(rs.getInt("goods_price"));
						goodsSearch.setSearch_goods_cost(rs.getInt("goods_cost"));
						
						

						// 商品データ情報を格納したSearchGoodsオブジェクトを戻り値用のリストに追加する
						goodsSearchList.add(goodsSearch);
						
					}
					// 読み込まれた商品データのリストを返す
					return goodsSearchList;
					
					
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
			public void delete(String goods_id) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				Object rs = null;

				try {
					// データベースに接続する
					conn = getConnection();

					// 使用するSQL文（UPDATE文）を準備する
					pstmt = conn.prepareStatement(QUERY_GOODS_DELETE);

					// SQL文のパラメータに登録商品情報を設定する
					pstmt.setString(1, goods_id);
					
					

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
			public GoodsUpdate update_select(String goods_id) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					// データベースに接続する
					conn = getConnection();

					// 使用するSQL文（UPDATE文）を準備する
					pstmt = conn.prepareStatement(QUERY_GOODS_UPDATE_SELECT);

					// SQL文のパラメータに登録商品情報を設定する
					pstmt.setString(1, goods_id);
					

					// SQL文を実行する
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						
						
						//オブジェクトにデータを一時格納
						goodsUpdate.setUpdate_goods_id(rs.getString("goods_id"));
						goodsUpdate.setUpdate_goods_name(rs.getString("goods_name"));
						goodsUpdate.setUpdate_goods_cost(rs.getInt("goods_cost"));
						goodsUpdate.setUpdate_goods_price(rs.getInt("goods_price"));
						
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
				return goodsUpdate ;
				

			
			}
			


			
			//商品データを編集するメソッド
			public GoodsUpdate update(String goods_id,String update_goods_name, int update_goods_price, int update_goods_cost) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				Object rs = null;

				try {
					// データベースに接続する
					conn = getConnection();

					// 使用するSQL文（UPDATE文）を準備する
					pstmt = conn.prepareStatement(QUERY_GOODS_UPDATE);

					// SQL文のパラメータに登録商品情報を設定する
					pstmt.setString(1,update_goods_name);
					pstmt.setInt(2,update_goods_price);
					pstmt.setInt(3,update_goods_cost);
					pstmt.setString(4,goods_id);
					
					

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
				return goodsUpdate;
							
				

			
			}
			


			
			}



			
     
