package karrot.view.purchase;

import java.util.ArrayList;
 
import karrot.model.dto.PurchaseDTO;
import karrot.model.purchaseAPI.Completed_PurchaseList_API;

public class Completed_PurchaseList {
    //사용자 본인의 구매 목록을 볼 수 있을 뷰
	public static void completed_PurchaseList() {
		ArrayList<PurchaseDTO> purchaseList = Completed_PurchaseList_API.purchaseListAPI();
		 
		// 최대 자릿수 구하기2 
		 
		System.out.println();
		System.out.println("===== 내가 구매한 상품의 목록 =====");
		for (int i = 0; i < purchaseList.size(); i++) {
			String productName = purchaseList.get(i).getName();
			int price = purchaseList.get(i).getPrice(); 
			System.out.printf("%3d : %10s, %d\n", i+1, productName, price);
		}
		System.out.println();
		
		
	}
}
