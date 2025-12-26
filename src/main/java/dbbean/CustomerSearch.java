package dbbean;

import java.io.Serializable;

public class CustomerSearch implements Serializable{
	
	private String search_customer_id; //顧客ID
	private String search_customer_name; //顧客名
	
	public String getSearch_customer_id() {
		return search_customer_id;
	}
	public void setSearch_customer_id(String search_customer_id) {
		this.search_customer_id = search_customer_id;
	}
	public String getSearch_customer_name() {
		return search_customer_name;
	}
	public void setSearch_customer_name(String search_customer_name) {
		this.search_customer_name = search_customer_name;
	}
	

}
