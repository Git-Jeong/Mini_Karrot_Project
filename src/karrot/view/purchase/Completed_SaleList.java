package karrot.view.purchase;

import java.util.ArrayList;

import karrot.model.dto.ProductDTO;
import karrot.model.purchaseAPI.Completed_SalesList_API;

public class Completed_SaleList {
	public static void completed_SaleList() { 
		ArrayList<ProductDTO> product = Completed_SalesList_API.completed_SalesList_API();  
 
		System.out.println();
		System.out.println("========== 내가 판매 완료한 상품의 목록 ==========");
		for (int i = 0; i < product.size(); i++) {
			// 출력 형식 수정
			String productName = product.get(i).getProductName();
			int price = product.get(i).getPrice(); 
			System.out.printf("%3d : %20s, %10d원\n", i+1, productName, price);
		} 
		System.out.println();
	} 
}
