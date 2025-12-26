package dbbean;

import java.io.Serializable;

public class GoodsSearch implements Serializable {
	
	private String search_goods_id; //商品ID
	private String search_goods_name; //商品名
	private int search_goods_price; //商品単価
	private int search_goods_cost; //商品原価
	
	
	public String getSearch_goods_id() {
		return search_goods_id;
	}
	public void setSearch_goods_id(String search_goods_id) {
		this.search_goods_id = search_goods_id;
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
	
	

}
