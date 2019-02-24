package pos;


import java.util.*;
public class Common {
	public static void menu() throws Exception {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("POS MENU");
			System.out.println("--------------------");
			System.out.println("1.상품관리 ");
			System.out.println("2.판매관리 ");
			System.out.println("3.프로그램 종료 ");
			System.out.println("--------------------");
			System.out.print(">");
			int me = scan.nextInt();
			if (me == 1) {
				while (true) {
					System.out.println("상품 관리 및 조회를 시작합니다.");
					System.out.println("--------------------");
					System.out.println("1. 상품 등록  |  2. 재고 수정");
					System.out.println("3. 상품 삭제  |  4. 전체 상품");
					System.out.println("5. 이전");
					System.out.println("--------------------");
					System.out.print(">");
					int sub = scan.nextInt();
					if (sub == 1) {// 전체상품 출력
						System.out.println("상품을 등록 합니다.");
						GoodsController gc = new GoodsController();
						gc.GoodsInsert();
					} else if (sub == 2) {// 재고수정
						System.out.println("상품을 수정 합니다.");
						System.out.print("수정 할 상품코드: ");
						String code = scan.next();
						GoodsController gc = new GoodsController();
						gc.StockUpdate(code);
					} else if (sub == 3) {
						System.out.println("상품을 삭제 합니다.");
						System.out.print("삭제 할 상품코드: ");
						String code = scan.next();
						GoodsController gc = new GoodsController();
						gc.GoodsDelete(code);
					} else if (sub == 4) {
						System.out.println("전체 상품 출력");
						GoodsController gc = new GoodsController();
						gc.showGoods();
					} else if (sub == 5) {
						System.out.println("이전으로 돌아갑니다.\n");
						break;
					} else {
						System.out.println("잘못 입력하였습니다.");
						System.out.println();
					}
				}
			} else if (me == 2) {
				while (true) {
					System.out.println("판매 관리 및 조회를 시작합니다.");
					System.out.println("--------------------");
					System.out.println("1. 주문 등록  |  2. 주문 수정(구매 수량)");
					System.out.println("3. 주문 삭제  |  4. 전체 주문");
					System.out.println("5. 전체 상품  |  6. 전체 판매 실적");
					System.out.println("7. 이전");
					System.out.println("--------------------");
					System.out.print(">");
					int sale = scan.nextInt();

					if (sale == 1) {
						System.out.println("새로운 주문을 등록합니다.");
						SalesController sc = new SalesController();
						sc.order();
					} else if (sale == 2) {
						System.out.println("주문을 수정 합니다.");
						System.out.print("주문 코드: ");
						String code = scan.next();
						SalesController sc = new SalesController();
						sc.orderUpdate(code);
					} else if (sale == 3) {
						System.out.println("주문을 삭제 합니다.");
						System.out.print("주문 코드: ");
						String code = scan.next();
						SalesController sc = new SalesController();
						sc.orderDelete(code);
					} else if (sale == 4) {
						System.out.println("전체 주문 내역");
						SalesController sc = new SalesController();
						sc.showOrders();
					} else if (sale == 5) {
						System.out.println("전체 상품 내역");
						SalesController sc = new SalesController();
						sc.showGoods();
					} else if (sale == 6){
						System.out.println("전체 판매 실적");
						SalesController sc = new SalesController();
						sc.salesResult();
					} else if (sale == 7){
						System.out.println("이전으로 돌아갑니다.\n");
						break;
					}else {
						System.out.println("잘못 입력하였습니다.");
						System.out.println();
					}
				}
			} else if (me == 3) {
				System.out.println("POS 프로그램 종료합니다.");
				break;
			} else {
				System.out.println("잘못 입력하였습니다.\n");
			}
		}
	}
}
