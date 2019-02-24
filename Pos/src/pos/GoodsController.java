package pos;

import java.io.*;
import java.util.*;

public class GoodsController {
	private Scanner scan = new Scanner(System.in);
	private GoodsMethod goo[] = new GoodsMethod[100];

	GoodsController() throws Exception {
		while(true) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\DBFILE\\goods.txt"));
				for (int i = 0; i < goo.length; i++) {
					try {
						goo[i] = (GoodsMethod) in.readObject();
					} catch (Exception e) {
						break;
					}
				}
				in.close();
				break;
			}catch(Exception e) {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\DBFILE\\goods.txt"));
				for (int i = 0; i < goo.length; i++) {
					out.writeObject(goo[i]);
				}
				out.close();
			}
		}
	}
	public void saveFile() throws Exception{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\DBFILE\\goods.txt"));
		for (int i = 0; i < goo.length; i++) {
			out.writeObject(goo[i]);
		}
		out.close();
	}
	public void GoodsInsert() throws Exception { // 상품 추가
		while (true) {

			try {
				System.out.println("추가 상품 입력");
				System.out.println("(상품코드, 상품명, 판매가격, 재고)");
				System.out.print(">");
				String goods = scan.next();

				StringTokenizer st = new StringTokenizer(goods, ":");
				String goodsCode = st.nextToken();
				String goodsName = st.nextToken();
				int goodsPrice = Integer.parseInt(st.nextToken());
				int stock = Integer.parseInt(st.nextToken());
				for (int i = 0; i < goo.length; i++) {
					if (goo[i] == null) {
						goo[i] = new GoodsMethod(goodsCode, goodsName, goodsPrice, stock);
						System.out.println("상품 코드: "+goo[i].getGoodsCode()+", 상품명: "+goo[i].getGoodsName()+" 의");
						System.out.println("등록이 완료 되었습니다.");
						System.out.println();
						break;
					}
				}
				break;
			} catch (Exception e) {
				System.out.println("상품코드:상품명:판매가격:재고");
				System.out.println("위 형식에 맞게 입력해주세요.");
				System.out.println();
			}
		}
		saveFile();
	}

	public void showGoods() {
		for (int i = 0; i < goo.length; i++) {
			if (goo[i] != null) {
				goo[i].showInfo();	
			}else {
				if(i==0) {
					System.out.println("등록된 상품이 없습니다.");
				}
			}
		} // 출력
		System.out.println();
	}

	public void StockUpdate(String x) throws Exception {
		for (int i = 0; i < goo.length; i++) {
			if (goo[i] != null) {
				if (x.equals(goo[i].getGoodsCode())) {
					System.out.println("현재 " + goo[i].getGoodsName() + "의 재고: " + goo[i].getStock());
					System.out.print("수정 할 재고 값: ");
					int newStock = scan.nextInt();
					goo[i].setStock(newStock);
					System.out.println("재고 수정 완료");
					System.out.println();
					saveFile();
					break;
				}
			} else {
				System.out.println("상품 코드를 확인해 주세요.");
				System.out.println();
				break;
			}
		}
	}

	public void GoodsDelete(String x) throws Exception {
		for (int i = 0; i < goo.length; i++) {
			if (goo[i] != null) {
				if (x.equals(goo[i].getGoodsCode())) {
					arrPush(i);
					System.out.println("상품 삭제 완료");
					System.out.println();
					saveFile();
					break;
				}
			} else {
				System.out.println("상품 코드를 확인해 주세요.");
				System.out.println();
				break;
			}
		}
	}
	public void arrPush(int x) {
		for(int i = x;i<goo.length-1;i++) {
			goo[i]=goo[i+1];
		}
	}
}
