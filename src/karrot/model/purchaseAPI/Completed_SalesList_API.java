package karrot.model.purchaseAPI;

import karrot.model.DB_DAO;
import karrot.model.dto.ProductDTO;
import karrot.model.dto.SecureUserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Completed_SalesList_API {
    //클라이언트(뷰)랑, 서버(DB) 사이의 컨트롤러가 될 예정
    //판매 기록을  조회할 것. <- select문의 조건에 사용자의 id값이 들어가야 됨
    // 
    public static ArrayList<ProductDTO> completed_SalesList_API() {
        //사용자의 계정의 경우는 userId로 사용
        SecureUserInfo secureUserInfo = SecureUserInfo.getInstance();
        String userId = secureUserInfo.getSecureUserId();

        ArrayList<ProductDTO> purchaseList = new ArrayList<>();
        
        String sql = "SELECT PRODUCTNAME, PRICE, DETAIL FROM PRODUCT WHERE ONSALE = ? AND USERID = ?";
        //로그인된 아이디 PE.USERID
        try (Connection conn = DB_DAO.getConnection();
               PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setInt(1, 0); // 판매 완료된거  
            psmt.setString(2, userId); // ID 설정  
        
        try (ResultSet rs = psmt.executeQuery()) {

            // 쿼리 실행 후 첫 번째 rs.next()만 호출 
                while (rs.next()) {
                  //rs.next에 값이 있는 만큼 
                  String productName = rs.getString("PRODUCTNAME"); //PURCHASE PRODUCT INNERJOINT 되어서 PR_NAME,  
                  int price = rs.getInt("PRICE");   
                  String detail = rs.getString("DETAIL");
                  purchaseList.add(new ProductDTO(productName, price, detail));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("구매 내역 조회 중 SQL 오류 발생: " + e.getMessage(), e);
		}
        return purchaseList;
    }
}
