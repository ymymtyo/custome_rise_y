package dbbean;

import java.io.Serializable;

public class User implements Serializable {
	private String user_id; // ユーザーID
	private String user_pass;	// パスワード
	private String user_name;	// ユーザー
	
	

	public String getId() {
		return user_id;
	}

	public void setId(String user_id) {
		this.user_id = user_id;
	}

	public String getPass() {
		return user_pass;
	}

	public void setPass(String user_pass) {
		this.user_pass = user_pass;
	}

	public String getName() {
		return user_name;
	}

	public void setName(String user_name) {
		this.user_name = user_name;
	}
	
	

}
