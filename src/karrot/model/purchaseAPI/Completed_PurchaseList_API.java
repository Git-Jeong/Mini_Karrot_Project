package karrot.model.purchaseAPI;

import karrot.model.DB_DAO;
import karrot.model.dto.PurchaseDTO;
import karrot.model.dto.SecureUserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Completed_PurchaseList_API {
    //클라이언트(뷰)랑, 서버(DB) 사이의 컨트롤러가 될 예정
    //사용자 본인의 구매 내역을 조회할 것. <- select문의 조건에 사용자의 id값이 들어가야 됨
    public static ArrayList<PurchaseDTO> purchaseListAPI( ) {
        //사용자의 계정의 경우는 userId로 사용
    	ArrayList<PurchaseDTO> purchaseList = new ArrayList<>();
        SecureUserInfo secureUserInfo = SecureUserInfo.getInstance();
        String userId = secureUserInfo.getSecureUserId();

        String sql = "SELECT PURCHASEID, PRODUCTNAME, PRICE FROM PRODUCT PT INNER JOIN PURCHASE PE ON PT.PRODUCTID = PE.PRODUCTID WHERE PE.USERID = ? ORDER BY PE.ONTIME DESC";
        //로그인된 아이디 PE.USERID
        try (Connection conn = DB_DAO.getConnection();
               PreparedStatement psmt = conn.prepareStatement(sql)) {
               psmt.setString(1, userId); // ID 설정  
        
        try (ResultSet rs = psmt.executeQuery()) {

            // 쿼리 실행 후 첫 번째 rs.next()만 호출 
                while (rs.next()) {
                  //rs.next에 값이 있는 만큼 
                  String productName = rs.getString("PRODUCTNAME"); //PURCHASE PRODUCT INNERJOINT 되어서 PR_NAME, 
                  int purchaseID = rs.getInt("PURCHASEID"); // 판매된 상품일련번호, 판매된 상품 이름 가격
                  int price = rs.getInt("PRICE");   
                  purchaseList.add(new PurchaseDTO(purchaseID, productName, price));
               } 
         }
      } catch (SQLException e) {
         throw new RuntimeException("구매 내역 조회 중 SQL 오류 발생: " + e.getMessage(), e);
      }
      
      
        return purchaseList;
   }
}

