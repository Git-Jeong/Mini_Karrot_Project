package karrot.view.product;

import karrot.model.dto.ProductDTO;
import karrot.model.productAPI.Product_Delete_API;
import karrot.model.productAPI.Product_Update_API;
import karrot.view.util.Input;


public class Product_Update {
    static final int UPDATE_NAME = 1;
    static final int UPDATE_DEAIL = 2;
    static final int UPDATE_PRICE = 3;
    static final int UPDATE_ALL = 4;
    static final String DELETE_PRODUCT = "D";

    public static void productUpdate(ProductDTO product) {
        boolean service = true;
        while (service){
            boolean name_commit_check = false;
            boolean deail_commit_check = false;
            boolean price_commit_check = false;

            int price = product.getPrice();
            int viewCount = product.getViewCount();
            String productName = product.getProductName();
            String detail = product.getDetail();

            System.out.println("========== 상품 세부 설명 ==========");
            System.out.printf("\t 상품명 : %s\n", productName);
            System.out.printf("\t 조회수 : %d\n", viewCount);
            System.out.printf("\t 설명 : %s\n", detail);
            System.out.printf("\t 가격 : %,d원\n", price);

            System.out.println();
            System.out.println("========== 상품정보 수정 ==========");
            System.out.printf("\t[%d] : 상품 이름 변경,   [%d] : 상품 설명 변경  \n", UPDATE_NAME, UPDATE_DEAIL);
            System.out.printf("\t[%d] : 상품 가격 변경,   [%d] : 모든 정보 수정  \n", UPDATE_PRICE, UPDATE_ALL);
            System.out.printf("\t[%s] : 상품 삭제\n", DELETE_PRODUCT);
            System.out.println("\t뒤로가기 : AnyKey");
            System.out.print("원하는 옵션 선택  >>>  ");
            String input = Input.inputStrig();

            if (input.equals(String.valueOf(UPDATE_PRICE))){
                System.out.print("새로운 가격  >>>  ");
                int new_price = Input.inputNumber();
                if (updateCheck()){
                    product.setPrice(new_price);
                    price_commit_check = Product_Update_API.product_Update_Price(product);
                }
            }
            else if (input.equals(String.valueOf(UPDATE_DEAIL))){
                System.out.print("새로은 설명  >>>  ");
                String new_productDetail = Input.inputStrig();
                if (updateCheck()){
                    product.setDetail(new_productDetail);
                    deail_commit_check = Product_Update_API.product_Update_Detail(product);
                }
            }
            else if (input.equals(String.valueOf(UPDATE_NAME))){
                System.out.print("새로은 이름  >>>  ");
                String new_productName = Input.inputStrig();
                product.setProductName(new_productName);
                if (updateCheck()){
                    product.setProductName(new_productName);
                    name_commit_check = Product_Update_API.product_Update_Name(product);
                }
            }
            else if (input.equals(String.valueOf(UPDATE_ALL))){
                System.out.print("새로은 이름  >>>  ");
                String new_productName = Input.inputStrig();
                System.out.print("새로은 설명  >>>  ");
                String new_productDetail = Input.inputStrig();
                System.out.print("새로운 가격  >>>  ");
                int new_price = Input.inputNumber();

                if (updateCheck()){
                    product.setProductName(new_productName);
                    product.setDetail(new_productDetail);
                    product.setPrice(new_price);
                    name_commit_check = Product_Update_API.product_Update_Name(product);
                    deail_commit_check = Product_Update_API.product_Update_Detail(product);
                    price_commit_check = Product_Update_API.product_Update_Price(product);
                }
            }
            else if (input.equalsIgnoreCase(DELETE_PRODUCT)){
                //상품을 삭제하는 코드
                System.out.print("상품을 정말 삭제하시겠습니까? 네 : 'Y',  아니요 : : 'N'  >>> ");
                while (true){
                    String delete_check = Input.inputStrig();
                    if (delete_check.equalsIgnoreCase("Y")){
                        //삭제
                        boolean delete_API_check = Product_Delete_API.product_Delete_API(product);
                        if(delete_API_check){
                            System.out.println("상품을 정상적으로 삭제했습니다.");
                        }
                        service = false;
                        break;
                    }
                    else if (delete_check.equalsIgnoreCase("N")){
                        //미삭제
                        System.out.println("삭제를 취소합니다.");
                        break;
                    }
                }
            }
            else {
                //뒤로가기 버튼
                service = false;
            }

            if (name_commit_check){
                System.out.println("상품이름 변경 성공");
            }
            if (deail_commit_check){
                System.out.println("상품설명 변경 성공 ");
            }
            if (price_commit_check){
                System.out.println("상품가격 변경 성공");
            }
        }

    }

    private static boolean updateCheck(){
        boolean result = false;

        System.out.print("상품 정보를 수정하시겠습니까? 예 : 'Y',  아니요 : AnyKey  >>>  ");
        while (true){
            String input = Input.inputStrig();
            if (input.equalsIgnoreCase("Y")){
                result = true;
                break;
            }
            else if (input.equalsIgnoreCase("N")){
                break;
            }
            else {
                System.out.print("재립력  >>>  ");
            }
        }

        return result;
    }
}
