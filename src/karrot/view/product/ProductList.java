package karrot.view.product;

import java.util.ArrayList;

import karrot.model.dto.ProductDTO;
import karrot.model.productAPI.Get_All_Product_API;
import karrot.model.productAPI.Product_My_Sale_List_API;
import karrot.view.util.Input;

public class ProductList {
	
	
	public static void productList() {
		ArrayList<ProductDTO> product = Get_All_Product_API.getAllProductAPI();
  
		System.out.println();
		System.out.println("========== 사람들이 판매중인 상품의 목록 ==========");
		int i;
		String title_index = "번호";
		String title_prpduct_name = "상품 이름";
		String title_price = "가격";
		String title_viewCount = "조회수";
		String title_time = "시간";
		System.out.printf("%3s : %11s  %9s  %3s %17s\n",
				title_index, title_prpduct_name, title_price, title_viewCount, title_time);
		for (i = 0; i < product.size(); i++) {
			// 출력 형식 수정
			int price = product.get(i).getPrice();
			String productName = product.get(i).getProductName();
			int viewCount = product.get(i).getViewCount();
			String time = product.get(i).getTime();

			System.out.printf("%3d : %13s, %10d, %5d, %10s\n", i+1, productName, price, viewCount, time);
		}

		int back_num = 0;
		String back_string = "뒤로가기";

		System.out.printf("\n%3d : %13s\n\n", back_num, back_string);

		System.out.print("원하는 옵션의 번호를 입력 >>> ");
		i++;
		
		while(true) {  
			int select_product_number = Input.inputNumber();
			if(select_product_number < i && select_product_number > 0) {
				select_product_number = select_product_number -1; 
				System.out.println("========== 상품 세부 정보 ========== ");
				int price = product.get(select_product_number).getPrice();
				String productName = product.get(select_product_number).getProductName();  
				String saler = product.get(select_product_number).getUserID(); 
				String detail = product.get(select_product_number).getDetail();
				int viewCount = product.get(select_product_number).getViewCount() + 1;
				product.get(select_product_number).setViewCount(viewCount);
				
				System.out.printf("\t 판매자 : %s\n", saler); 
				System.out.printf("\t 상품명 : %s\n", productName); 
				System.out.printf("\t 조회수 : %s\n", viewCount); 
				System.out.printf("\t 설명 : %s\n", detail); 
				System.out.printf("\t 가격 : %s\n", price); 
				
				Get_All_Product_API.viewCountPlus(product.get(select_product_number));
				
				System.out.print("상품을 구매하시겠습니까? 네 : 'Y',  뒤로가기 : AnyKey >>> ");
				String option = Input.inputStrig();
				if(option.equalsIgnoreCase("Y")) {  
					boolean buy_check = Get_All_Product_API.buyTheProduct(product.get(select_product_number));
					if(buy_check) {
						System.out.println("성공적으로 구매했습니다! "); 
					}
					else {
						System.out.println("상품 구매에 실패했습니다. ");
					}
                }
                break;
            }
			else if (select_product_number == 0) {
				break;
				
			}
			else {
				System.out.print("숫자를 다시 입력 : ");
			}
		} 
		System.out.println();
		
	}
	
	public static void mySaleList() {
		ArrayList<ProductDTO> product = Product_My_Sale_List_API.my_Sale_List_API();
 
		int index = 1; 
		System.out.println();
		System.out.println("===== 내가 판매중인 상품의 목록 =====");
		for (ProductDTO productDTO : product) {
			// 출력 형식 수정
			System.out.printf("[%3d], %15s, %10d원\n",
					index++, productDTO.getProductName(), productDTO.getPrice());
		} 
		System.out.println();
	} 
}
