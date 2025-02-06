package karrot.view.product;

import karrot.model.dto.ProductDTO;
import karrot.model.dto.SecureUserInfo;
import karrot.model.productAPI.Product_Sale_API;
import karrot.view.util.Input;

public class ProductSale {
	// 여기는 사용자가 물건을 판매하는 용도의 뷰
	public static void productSale() {
		SecureUserInfo secureUserInfo = SecureUserInfo.getInstance();
		String userID = secureUserInfo.getSecureUserId();

		String productName;
		String productDescription ;
		int productPrice;

		System.out.print("상품 이름 : ");
		productName = Input.inputStrig();
		System.out.print("상품 설명 : ");
		productDescription = Input.inputStrig();
		System.out.print("상품 가격 : ");
		productPrice = Input.inputNumber();

		ProductDTO productDTO = new ProductDTO(userID ,productName, productDescription, productPrice);

		boolean sale_commit_check = Product_Sale_API.createProduct(productDTO);

		if (sale_commit_check) {
			//상품 등록에 성공
			System.out.println("상품을 정상적으로 등록하였습니다.");
		}
	}
}
