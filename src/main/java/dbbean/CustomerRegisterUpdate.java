package dbbean;

import java.io.Serializable;
import java.sql.Date;

public class CustomerRegisterUpdate implements Serializable {
	

	private String update_proceeds_id; //ID
	private String update_person; //担当者
	private int update_pcs; //数量
	private int update_profit; //利益（単価-原価）
	private Date update_sales_date; //売上日
	private int update_sales; //売上金（数量＊商品単価）
	private String update_goods_name; //商品名
	private int update_goods_price; //商品単価
	private int update_goods_cost; //商品原価
	private String update_customer_name; //顧客名
	private String update_customer_id; //顧客名
	private String update_goods_id; //商品名
	
	
	public String getUpdate_proceeds_id() {
		return update_proceeds_id;
	}
	public void setUpdate_proceeds_id(String update_proceeds_id) {
		this.update_proceeds_id = update_proceeds_id;
	}
	public String getUpdate_person() {
		return update_person;
	}
	public void setUpdate_person(String update_person) {
		this.update_person = update_person;
	}
	public int getUpdate_pcs() {
		return update_pcs;
	}
	public void setUpdate_pcs(int update_pcs) {
		this.update_pcs = update_pcs;
	}
	public int getUpdate_profit() {
		return update_profit;
	}
	public void setUpdate_profit(int update_profit) {
		this.update_profit = update_profit;
	}
	public Date getUpdate_sales_date() {
		return update_sales_date;
	}
	public void setUpdate_sales_date(Date update_sales_date) {
		this.update_sales_date = update_sales_date;
	}
	public int getUpdate_sales() {
		return update_sales;
	}
	public void setUpdate_sales(int update_sales) {
		this.update_sales = update_sales;
	}
	public String getUpdate_goods_name() {
		return update_goods_name;
	}
	public void setUpdate_goods_name(String update_goods_name) {
		this.update_goods_name = update_goods_name;
	}
	public int getUpdate_goods_price() {
		return update_goods_price;
	}
	public void setUpdate_goods_price(int update_goods_price) {
		this.update_goods_price = update_goods_price;
	}
	public int getUpdate_goods_cost() {
		return update_goods_cost;
	}
	public void setUpdate_goods_cost(int update_goods_cost) {
		this.update_goods_cost = update_goods_cost;
	}
	public String getUpdate_customer_name() {
		return update_customer_name;
	}
	public void setUpdate_customer_name(String update_customer_name) {
		this.update_customer_name = update_customer_name;
	}
	public String getUpdate_customer_id() {
		return update_customer_id;
	}
	public void setUpdate_customer_id(String update_customer_id) {
		this.update_customer_id = update_customer_id;
	}
	public String getUpdate_goods_id() {
		return update_goods_id;
	}
	public void setUpdate_goods_id(String update_goods_id) {
		this.update_goods_id = update_goods_id;
	}

}
