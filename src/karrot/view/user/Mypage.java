package karrot.view.user;

import karrot.model.dto.SecureUserInfo;
import karrot.view.product.ProductList;
import karrot.view.purchase.Completed_PurchaseList;
import karrot.view.purchase.Completed_SaleList;
import karrot.view.util.Input;

public class Mypage {
    static int BACK_BUTTON = 0;
    static int SALE_PRODUCTS_LIST_CODE = 1;
    static int SALE_SOLD_PRODUCTS_LIST_CODE = 2;
    static int PURCHASE_CODE = 3;

    public static void showMypage() {
        SecureUserInfo secureUserInfo = SecureUserInfo.getInstance();
        String userName = secureUserInfo.getSecureUserName();

        System.out.println();
        System.out.printf("====== %s님 안녕하세요! =====\n", userName);

       while (true) {
           System.out.printf("\t[%d] : 내가 판매중인 상품\n", SALE_PRODUCTS_LIST_CODE);
           System.out.printf("\t[%d] : 내가 판매 완료한 상품 \n", SALE_SOLD_PRODUCTS_LIST_CODE);
           System.out.printf("\t[%d] : 내가 구매한 항목 \n", PURCHASE_CODE);
           System.out.printf("\t[%d] : 뒤로가기\n", BACK_BUTTON);
           System.out.print("원하는 항목을 선택하세요  >>>  ");
           
           String selectOption = Input.inputStrig();
           if(selectOption.equals(String.valueOf(SALE_PRODUCTS_LIST_CODE))) {
               //내가 판매중인 상품의 목록 
               ProductList.mySaleList(); 
           }
           else if(selectOption.equals(String.valueOf(SALE_SOLD_PRODUCTS_LIST_CODE))) {
               //내가 판매 완료한 상품의 목록 
               Completed_SaleList.completed_SaleList();
               
           }
           else if(selectOption.equals(String.valueOf(PURCHASE_CODE))) {
               //내가 구매한 상품의 목록 
               Completed_PurchaseList.completed_PurchaseList(); 
           }
           else if(selectOption.equals(String.valueOf(BACK_BUTTON))) {
               //뒤로가기
               break;
           }
       }

    }
}
