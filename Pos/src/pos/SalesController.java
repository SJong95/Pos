package pos;

import java.io.*;
import java.util.*;

public class SalesController {
	private Scanner scan = new Scanner(System.in);
	private SalesMethod ord[] = new SalesMethod[100];
	SalesController() throws Exception{
		while(true) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\DBFILE\\orders.txt"));
				for (int i = 0; i < ord.length; i++) {
					try {
						ord[i] = (SalesMethod) in.readObject();
					} catch (EOFException e) {
						break;
					}
				}
				in.close();
				break;
			}catch(Exception e) {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\DBFILE\\orders.txt"));
				for (int i = 0; i < ord.length; i++) {
					out.writeObject(ord[i]);
				}
				out.close();
			}
		}
		
	}
	public void saveFile() throws Exception{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\DBFILE\\orders.txt"));
		for (int i = 0; i < ord.length; i++) {
			out.writeObject(ord[i]);
		}
		out.close();
	}
	public void order() throws Exception{
		while(true) {
			try {
				System.out.println("구입 상품");
				System.out.println("(상품코드, 구매수량)");
				System.out.print(">");
				String tempCode = scan.next();
				StringTokenizer st = new StringTokenizer(tempCode, ":");
				String goodsCode = st.nextToken();
				int amount = Integer.parseInt(st.nextToken());
				for (int i = 0; i < ord.length; i++) {
					if(ord[i]==null) {
						if(codeCheck(goodsCode)) {
							if(stockCheck(goodsCode, amount)) {
								ord[i] = new SalesMethod(goodsCode,amount);
								ord[i].orderUpdate();
								System.out.println("주문 코드: "+ord[i].getOrderCode()+"의");
								System.out.println("등록이 완료되었습니다.");
								System.out.println();
								break;	
							}else {
								System.out.println("입력하신 상품코드인 "+goodsCode+"의");
								System.out.println("재고가 부족합니다.");
								System.out.println("다시 확인 후 입력해주시기 바랍니다.");
								System.out.println();
								break;
							}
						}else {
							System.out.println("입력하신 상품코드인 "+goodsCode+"는 등록되지 않은 코드 입니다.");
							System.out.println("다시 확인 후 입력해주시기 바랍니다.");
							System.out.println();
							break;
						}
					}
				}
				break;
			}catch(Exception e) {
				System.out.println("([상품코드]:[구매수량])");
				System.out.println("위 형식에 맞게 입력해주세요.");
				System.out.println();
			}
		}
		saveFile();
	}
	public void showOrders() {
		for (int i = 0; i < ord.length; i++) {
			if (ord[i] != null) {
				System.out.println("---------------------주문번호 : "+(i+1)+"---------------------");
				ord[i].showInfo();
			}else {
				if(i==0) {
					System.out.println("주문 내역이 없습니다.");
				}
			}
		}
		System.out.println();
	}
	public void showGoods() throws Exception{
		GoodsMethod[] tempGoo = readGoods();
		for (int i = 0; i < tempGoo.length; i++) {
			if (tempGoo[i] != null) {
				tempGoo[i].showInfo();
			}else {
				if(i==0) {
					System.out.println("등록된 상품이 없습니다.");
				}
			}
		}
		System.out.println();
	}
	public void orderUpdate(String x) throws Exception{
		for (int i = 0; i < ord.length; i++) {
			if (ord[i] != null) {
				if (x.equals(ord[i].getOrderCode())) {
					System.out.println("현재 " + ord[i].getGoodsName() + "의 구매 수량: " + ord[i].getAmount());
					System.out.print("수정 할 구매 수량 값: ");
					int newAmount = scan.nextInt();
					if(newAmount<=(ord[i].getGooStock(ord[i].getGoodsCode())+ord[i].getAmount())) {
						ord[i].amountUpdate(newAmount);
						System.out.println("구매 수량 수정 완료");
						saveFile();
						System.out.println();	
						break;
					}else {
						System.out.println("입력하신 주문코드인 "+x+"의");
						System.out.println("상품의 재고가 부족합니다.");
						System.out.println("다시 확인 후 입력해주시기 바랍니다.");
						System.out.println();
						break;
					}
				}
			} else {
				System.out.println("주문 코드를 확인해 주세요.");
				System.out.println();
				break;
			}
		}
	}
	public void orderDelete(String x) throws Exception{
		for (int i = 0; i < ord.length; i++) {
			if (ord[i] != null) {
				if (x.equals(ord[i].getOrderCode())) {
					ord[i].addAmount();
					arrPush(i);
					System.out.println("주문 삭제 완료");
					saveFile();
					System.out.println();
					break;
				}
			} else {
				System.out.println("주문 코드를 확인해 주세요.");
				System.out.println();
				break;
			}
		}
	}
	public void arrPush(int x) {
		for(int i = x;i<ord.length-1;i++) {
			ord[i]=ord[i+1];
		}
	}
	public GoodsMethod[] readGoods() throws Exception{
		GoodsMethod[] tempGoo = new GoodsMethod[100];
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\DBFILE\\goods.txt"));
		for (int i = 0; i < tempGoo.length; i++) {
			try {
				tempGoo[i] = (GoodsMethod) in.readObject();
			} catch (EOFException e) {
				break;
			}
		}
		in.close();
		return tempGoo;
	}
	public boolean codeCheck(String x) throws Exception{
		GoodsMethod[] tempGoo = readGoods();
		for(int i = 0;i<tempGoo.length;i++) {
			if(tempGoo[i]!=null) {
				if(x.equals(tempGoo[i].getGoodsCode())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean stockCheck(String x,int y) throws Exception{
		GoodsMethod[] tempGoo = readGoods();
		for(int i = 0;i<tempGoo.length;i++) {
			if(tempGoo[i]!=null) {
				if(x.equals(tempGoo[i].getGoodsCode())) {
					if((tempGoo[i].getStock()-y)>=0)
					return true;
				}
			}
		}
		return false;
	}
	public void salesResult() {
		int sum=0;
		for(int i = 0;i<ord.length;i++) {
			if(ord[i]!=null) {
				sum+=ord[i].getSumPrice();
			}
		}
		System.out.println("전체 판매 실적: "+sum+"원\n");
	}
}
