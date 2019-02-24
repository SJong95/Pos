package pos;

import java.io.*;
import java.text.*;
import java.util.*;

public class SalesMethod implements Serializable {
	private String orderCode;// 주문코드
	private String goodsCode;// 상품코드
	private String goodsName;// 상품명
	private int goodsPrice;// 상품가격
	private int amount;// 구매수량
	private int sumPrice;// 합계금액
	private GoodsMethod goo[] = new GoodsMethod[100];

	SalesMethod(String y, int z) throws Exception {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		orderCode = sdf.format(now);
		goodsCode = y;
		amount = z;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\DBFILE\\goods.txt"));
		for (int i = 0; i < goo.length; i++) {
			try {
				goo[i] = (GoodsMethod) in.readObject();
			} catch (EOFException e) {
				break;
			}
		}
		in.close();
	}

	public void showInfo() {
		System.out.println("주문코드: " + orderCode + ", 상품코드: " + goodsCode + ",\n상품명: " + goodsName + ", 상품가격: "
				+ goodsPrice + ", 구매수량: " + amount+", 합계금액: "+sumPrice);
	}

	public String getOrderCode() {
		return orderCode;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public GoodsMethod getGoo(int x) {
		return goo[x];
	}

	public int getAmount() {
		return amount;
	}
	public int getSumPrice() {
		return sumPrice;
	}

	public int getGooStock(String x) {
		int temp = 0;
		for (int i = 0; i < goo.length; i++) {
			if (goo[i] != null) {
				if (x.equals(goo[i].getGoodsCode()))
					temp = i;
			}
		}
		return goo[temp].getStock();
	}

	public void setGoodsName() {
		for (int i = 0; i < goo.length; i++) {
			if (goo[i] != null) {
				if (this.goodsCode.equals(goo[i].getGoodsCode())) {
					this.goodsName = goo[i].getGoodsName();
				}
			}
		}
	}
	public void setGoodsPrice() {
		for (int i = 0; i < goo.length; i++) {
			if (goo[i] != null) {
				if (this.goodsCode.equals(goo[i].getGoodsCode())) {
					this.goodsPrice = goo[i].getGoodsPrice();
				}
			}
		}
	}

	public void setSumPrice() {
		int tempPrice = 0;
		for (int i = 0; i < goo.length; i++) {
			if (goo[i] != null) {
				if (this.goodsCode.equals(goo[i].getGoodsCode())) {
					tempPrice = goo[i].getGoodsPrice();
				}
			}
		}
		sumPrice = amount * tempPrice;
	}

	public void minusAmount() throws Exception {
		readGoods();
		for (int i = 0; i < goo.length; i++) {
			if (goo[i] != null) {
				if (this.goodsCode.equals(goo[i].getGoodsCode())) {
					goo[i].minusStock(amount);
				}
			}
		}
		saveGoods();
	}

	public void addAmount() throws Exception {
		readGoods();
		for (int i = 0; i < goo.length; i++) {
			if (goo[i] != null) {
				if (this.goodsCode.equals(goo[i].getGoodsCode())) {
					goo[i].addStock(amount);
				}
			}
		}
		saveGoods();
	}

	public void orderUpdate() throws Exception {
		setGoodsName();
		setGoodsPrice();
		setSumPrice();
		minusAmount();
	}

	public void amountUpdate(int x) throws Exception {
		addAmount();
		amount = x;
		setSumPrice();
		minusAmount();
	}

	public void saveGoods() throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\DBFILE\\goods.txt"));
		for (int i = 0; i < goo.length; i++) {
			out.writeObject(goo[i]);
		}
		out.close();
	}

	public void readGoods() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\DBFILE\\goods.txt"));
		for (int i = 0; i < goo.length; i++) {
			try {
				goo[i] = (GoodsMethod) in.readObject();
			} catch (EOFException e) {
				e.printStackTrace();
				break;
			}
		}
		in.close();
	}

}
