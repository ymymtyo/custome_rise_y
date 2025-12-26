package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbbean.User;



public class user_mstDAO {
	
	//ユーザーテーブルからユーザIDとパスワードで検索するSQL
		private static final String SQL_SELECT_BY_ID_PASS ="select * from user_mst where user_id = ? and user_pass = ?";
		
		/**
		 *コネクションを取得する
		 *@return コネクション
		 *@throws SQLException
		 *@throws ClassNotFoundException
		 */
		
		public Connection getConnection() throws SQLException, ClassNotFoundException{
			Connection conn = null;
			
			//ローカルホストで実行："jdbc:mysql://localhost:3306/customerise","root", "mysql"
			//AWSで実行："jdbc:mysql://customerise.cr06usyck275.ap-southeast-2.rds.amazonaws.com:3306/customerise","root", "mysqlyamakawa"
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerise","root", "mysql");
			return conn;
		}
		
		/**
		 * ユーザーテーブルからユーザーIDとパスワードで検索した結果を返す
		 * @param user_id ユーザーID 
		 * @param user_pass パスワード
		 * @return ユーザー
		 */
		public User selectByIdPass(String user_id, String user_pass) {
			User user = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn= getConnection();
				pstmt = conn.prepareStatement(SQL_SELECT_BY_ID_PASS);
				pstmt.setString(1, user_id);
				pstmt.setString(2, user_pass);
				rs = pstmt.executeQuery();
				
				//ログインの検索結果があれば、ユーザーオブジェクト生成
				if(rs.next()) {
					user = new User();
					user.setId(rs.getString("user_id"));
					user.setPass(rs.getString("user_pass"));
					user.setName(rs.getString("user_name"));
				}
				return user;
				
			}catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
				
			}finally {
				try {
					if(rs != null) {
						rs.close();
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if(pstmt != null) {
						conn.close();
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
				try {
					if (conn != null) {
						conn.close();
					}
				}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
		
		
		

	

}
