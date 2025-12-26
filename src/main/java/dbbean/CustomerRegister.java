//売上データ一覧表示用bean

package dbbean;

import java.io.Serializable;
import java.sql.Date;

public class CustomerRegister implements Serializable {
	
	private String proceeds_id; //ID
	private String person; //担当者
	private int pcs; //数量
	private int profit; //利益（単価-原価）
	private Date sales_date; //売上日
	private int sales; //売上金（数量＊商品単価）
	private String goods_name; //商品名
	private int goods_price; //商品単価
	private int goods_cost; //商品原価
	private String customer_name; //顧客名
	

	
	public String getPerson() {
		return person;
	}
	
	public void setPerson(String person) {
		this.person = person;
	}
	
	public int getPcs() {
		return pcs;
	}
	
	public void setPcs(int pcs) {
		this.pcs = pcs;
	}
	
	public int getProfit() {
		int profit = 0;
		profit = (goods_price - goods_cost)*pcs;
		return profit;
	}
	
	public void setProfit(int profit) {
		this.profit = profit;
	}
	
	public Date getSales_date() {
		return sales_date;
	}
	
	public void setSales_date(Date sales_date) {
		this.sales_date = sales_date;
	}
	
	public int getSales() {
		int sales = 0;
		sales = pcs * goods_price;
		return sales;
	}
	
	public void setSales(int sales) {
		this.sales = sales;
	}
	
	public String getGoods_name() {
		return goods_name;
	}
	
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	public int getGoods_price() {
		return goods_price;
	}
	
	public void setGoods_price(int goods_price) {
		this.goods_price = goods_price;
	}
	
	public int getGoods_cost() {
		return goods_cost;
	}
	
	public void setGoods_cost(int goods_cost) {
		this.goods_cost = goods_cost;
	}
	
	public String getCustomer_name() {
		return customer_name;
	}
	
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getProceeds_id() {
		return proceeds_id;
	}

	public void setProceeds_id(String proceeds_id) {
		this.proceeds_id = proceeds_id;
	}
	
	
	
	
		
	

}