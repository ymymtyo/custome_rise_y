package dbbean;

import java.io.Serializable;

public class CustomerUpdate  implements Serializable{
	
	private String update_customer_id; //顧客ID
	private String update_customer_name; //顧客名
	
	
	public String getUpdate_customer_id() {
		return update_customer_id;
	}
	public void setUpdate_customer_id(String update_customer_id) {
		this.update_customer_id = update_customer_id;
	}
	public String getUpdate_customer_name() {
		return update_customer_name;
	}
	public void setUpdate_customer_name(String update_customer_name) {
		this.update_customer_name = update_customer_name;
	}
	
	

}
