package dbbean;

import java.io.Serializable;

public class GoodsUpdate implements Serializable {
	
	private String update_goods_id; //商品ID
	private String update_goods_name; //商品名
	private int update_goods_price; //商品単価
	private int update_goods_cost; //商品原価
	
	
	public String getUpdate_goods_id() {
		return update_goods_id;
	}
	public void setUpdate_goods_id(String update_goods_id) {
		this.update_goods_id = update_goods_id;
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

}
