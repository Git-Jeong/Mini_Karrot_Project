package karrot.model.productAPI;

import karrot.model.db_connect.DB_DAO;
import karrot.model.dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Product_Delete_API {
    public static boolean product_Delete_API(ProductDTO product){
        boolean result = false;
        int productID = product.getProductID();

        Connection conn = DB_DAO.getConnection();

        if (conn == null) {
            System.out.println("DB 연결 실패!");
        } else {
            String sql = "DELETE FROM PRODUCT WHERE PRODUCTID = ? AND ONSALE = ?";

            try (PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, productID); // 지울 테이블의 기본키
                psmt.setInt(2, 1); // 판매중인 상품
                try (ResultSet rs = psmt.executeQuery()) {
                    if (rs.next()) {
                        conn.commit();
                        result = true;
                    }
                }
            } catch (Exception e) {
                System.out.println("상품을 지우는 과정에서 SQL 오류가 발생했습니다: " + e.getMessage());
            } finally {
                DB_DAO.closeDB(conn);
            }
        }
        return result;
    }
}
