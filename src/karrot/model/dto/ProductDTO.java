package karrot.model.dto;
 

public class ProductDTO {
   //물건을 사고 파는 과정에서 필요한 변수의 클래스 
   
   //필요한 메소드는 조회( 수정(PRICE & DETAIL) 등록(ALL)
   
   
   //product dto
   
   // productID = int
   // userID = String
   // productNAME= String
   //price = int
   //detail = String
   // state = boolean
   // viewcount = num
   // time = timestamp
   
   
   //FIELD
   private int productID;
   private String userID;
   private String productName;
   private int price;
   private String detail;
   private int onSale;   // 1(판매중) or 0(판매완료)
   private int viewCount;
   private int selectProductID;
   private String timestamp;
   

   
   public int getSelectProductID() {
      return selectProductID;
   }
   
   public int getProductID() {
      return productID;
   }
   
   public String getTime() {
	   return timestamp;
   }
   
//productID, userID, productName, price, detail, onSale, viewCount, timestamp
   //METHOD 1. - 상품등록 상품번호,  아이디, 상품의 이름, 상품의 가격, 상품의 상세설명, 상품 판매중, 조회수
   public ProductDTO(int productID, String userID, String productName, 
		   int price, String detail, int viewCount, String timestamp) {       //매개변수 안에 TIMESTAMP 꼭 잊지말고 넣어라
      super();
      this.productID = productID;
      this.userID = userID;
      this.productName = productName;
      this.price = price;
      this.detail = detail; 
      this.viewCount = viewCount;
      this.timestamp = timestamp;
   }
   //METHOD3. 상품 판매를 위한 메소드-> 아이디, 제품 가격, 제품 이름, 제품 설명
   public ProductDTO(String userID, String productName, String detail, int price ) {
      super(); 
	  this.userID = userID;
      this.productName = productName;
      this.detail = detail;
      this.detail = detail;
      this.price = price;
   }

   //METHOD 4. 상품 목록 페이지 -> 상품명, 가격
   public ProductDTO(int productID, String productName, int price, int viewCount, String detail) {
      super();
      this.productID = productID;
      this.productName = productName;
      this.price = price;
      this.viewCount = viewCount;
      this.detail = detail;
   }
   
   
   //METHOD 5. 수정 -> 상품명, 가격, 상세설명
   public ProductDTO(String productName, int price, String detail) {
      super();
      this.productName = productName;
      this.price = price;
      this.detail = detail;
   }
   

   public void setViewCount(int viewCount) {
      this.viewCount = viewCount;
   }

   public void setSelectProductID(int selectProductID) {
	   this.selectProductID = selectProductID;
   }

   public void setProductID(int productID) {
      this.productID = productID;
   }
   public String getUserID() {
      return userID;
   }
   public void setUserID(String userID) {
      this.userID = userID;
   }
   public String getProductName() {
      return productName;
   }
   public void setProductName(String productName) {
      this.productName = productName;
   }
   public int getPrice() {
      return price;
   }
   public void setPrice(int price) {
      this.price = price;
   }
   public String getDetail() {
      return detail;
   }
   public void setDetail(String detail) {
      this.detail = detail;
   }
   public int onSale() {
      return onSale;
   }
   
   public int getViewCount() {
      return viewCount;
   } 
  
}
