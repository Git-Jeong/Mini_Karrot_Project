package karrot.model.productAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

import karrot.model.db_connect.DB_DAO;
import karrot.model.dto.ProductDTO;
import karrot.model.dto.SecureUserInfo;

public class Product_Sale_API {
    public static boolean createProduct(ProductDTO productDTO) {
        boolean result = false;
        //사용자가 물건을 판매하면 이걸 DB에 저장하는 곳
        //productDTO에  판매자id, 상품 이름, 상풍 설명, 가격 정보  값이 있으니 이걸 db에 저장하면 됨. 
        
        SecureUserInfo userInfo = SecureUserInfo.getInstance();
        String userId = userInfo.getSecureUserId();
        
        //DB에 상품을 등록하는 것
        String sql = "INSERT INTO PRODUCT(PRODUCTID, PRODUCTNAME, PRICE, DETAIL, ONTIME, USERID)  VALUES(PRODUCT_NUM.NEXTVAL, ?, ?, ?, SYSDATE, ?)";

        try (Connection conn = DB_DAO.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setString(1, productDTO.getProductName()); // 상품 이름
            psmt.setInt(2, productDTO.getPrice()); // 상품 가격
            psmt.setString(3, productDTO.getDetail()); // 상품 설명 
            psmt.setString(4, userId); // ID 설정    

           try (ResultSet rs = psmt.executeQuery()) {

              // 쿼리 실행 후 첫 번째 rs.next()만 호출
              if (rs.next()) { 
                 conn.commit();
                 result = true;
              } 
           }
        } catch (SQLException e) {
           throw new RuntimeException("상품 등록 중 SQL 오류 발생: " + e.getMessage(), e);
        }

        return result;
    }
}
