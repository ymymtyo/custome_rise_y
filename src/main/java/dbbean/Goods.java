//一覧表示用bean

package dbbean;

import java.io.Serializable;

public class Goods implements Serializable {
	
	private String goods_id; //商品ID
	private String goods_name; //商品名
	private int goods_price; //商品単価
	private int goods_cost; //商品原価
	
	
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
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
	
	

	

}
