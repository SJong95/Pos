package pos;

import java.io.Serializable;

public class GoodsMethod implements Serializable {
	private String goodsCode;// 상품코드
	private String goodsName;// 상품명
	private int goodsPrice;// 판매가격
	private int stock;// 재고

	GoodsMethod(String x, String y, int z, int i) {
		goodsCode = x;
		goodsName = y;
		goodsPrice = z;
		stock = i;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public int getGoodsPrice() {
		return goodsPrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void showInfo() {
		System.out.println("상품코드: " + goodsCode + ", 상품명: " + goodsName + ", 판매가격: " + goodsPrice + ", 재고: " + stock);
	}

	public void minusStock(int x) {
		stock -= x;
	}

	public void addStock(int x) {
		stock += x;
	}
}
