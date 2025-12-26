package dbbean;

import java.io.Serializable;
import java.sql.Date;

public class CustomerRegisterSearch implements Serializable {
	

	private String search_proceeds_id; //ID
	private String search_person; //担当者
	private int search_pcs; //数量
	private int search_profit; //利益（単価-原価）
	private Date search_sales_date; //売上日
	private int search_sales; //売上金（数量＊商品単価）
	private String search_goods_name; //商品名
	private int search_goods_price; //商品単価
	private int search_goods_cost; //商品原価
	private String search_customer_name; //顧客名
	
	
	public String getSearch_person() {
		return search_person;
	}
	public void setSearch_person(String search_person) {
		this.search_person = search_person;
	}
	public int getSearch_pcs() {
		return search_pcs;
	}
	public void setSearch_pcs(int search_pcs) {
		this.search_pcs = search_pcs;
	}
	public int getSearch_profit() {
		int search_profit = 0;
		search_profit = (search_goods_price - search_goods_cost)*search_pcs;
		return search_profit;
	}
	public void setSearch_profit(int search_profit) {
		this.search_profit = search_profit;
	}
	public Date getSearch_sales_date() {
		return search_sales_date;
	}
	public void setSearch_sales_date(Date search_sales_date) {
		this.search_sales_date = search_sales_date;
	}
	public int getSearch_sales() {
		int search_sales = 0;
		search_sales = search_pcs * search_goods_price;
		return search_sales;
	}
	public void setSearch_sales(int search_sales) {
		this.search_sales = search_sales;
	}
	public String getSearch_goods_name() {
		return search_goods_name;
	}
	public void setSearch_goods_name(String search_goods_name) {
		this.search_goods_name = search_goods_name;
	}
	public int getSearch_goods_price() {
		return search_goods_price;
	}
	public void setSearch_goods_price(int search_goods_price) {
		this.search_goods_price = search_goods_price;
	}
	public int getSearch_goods_cost() {
		return search_goods_cost;
	}
	public void setSearch_goods_cost(int search_goods_cost) {
		this.search_goods_cost = search_goods_cost;
	}
	public String getSearch_customer_name() {
		return search_customer_name;
	}
	public void setSearch_customer_name(String search_customer_name) {
		this.search_customer_name = search_customer_name;
	}
	public String getSearch_proceeds_id() {
		return search_proceeds_id;
	}
	public void setSearch_proceeds_id(String search_proceeds_id) {
		this.search_proceeds_id = search_proceeds_id;
	}
	
	
	

}
