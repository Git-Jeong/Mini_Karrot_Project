package karrot.model.productAPI;

import karrot.model.db_connect.DB_DAO;
import karrot.model.dto.ProductDTO;

import java.sql.*;

public class Product_Update_API {
    static boolean result = false;

    private static boolean updateProductField(String sql, String fieldValue, int productId) {
        try (Connection conn = DB_DAO.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            // PreparedStatement에 값 설정
            psmt.setString(1, fieldValue);  // 첫 번째 파라미터로 필드 값
            psmt.setInt(2, productId);      // 두 번째 파라미터로 상품 ID

            // SQL 실행
            try {
                psmt.executeUpdate();
                conn.commit();
                DB_DAO.closeDB(conn);
                return true;
            }catch (SQLException e){
                conn.rollback();
            }
        } catch (SQLException e) {
            // SQL 오류 처리
            System.out.println("상품 정보를 바꾸는 도중 오류가 발생했습니다: " + e.getMessage());
        }
        return false;
    }

    private static boolean updateProductField(String sql, int fieldValue, int productId) {
        try (Connection conn = DB_DAO.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            // PreparedStatement에 값 설정
            psmt.setInt(1, fieldValue);  // 첫 번째 파라미터로 필드 값
            psmt.setInt(2, productId);   // 두 번째 파라미터로 상품 ID

            // SQL 실행
            int rowsAffected = psmt.executeUpdate();  // executeUpdate()는 수정된 행 수를 반환
            conn.commit();  // 트랜잭션 커밋
            DB_DAO.closeDB(conn);
            return rowsAffected > 0;  // 0개 이상의 행이 수정되었을 경우 true 반환

        } catch (SQLException e) {
            // SQL 오류 처리
            System.out.println("상품 정보를 바꾸는 도중 오류가 발생했습니다: " + e.getMessage());
            return false;
        }
    }

    public static boolean product_Update_Name(ProductDTO product) {
        String productName = product.getProductName();
        int productId = product.getProductID();

        String sql = "UPDATE PRODUCT SET PRODUCTNAME = ? WHERE PRODUCTID = ?";
        result = updateProductField(sql, productName, productId);
        return result;
    }

    public static boolean product_Update_Price(ProductDTO product) {
        int price = product.getPrice();
        int productId = product.getProductID();

        String sql = "UPDATE PRODUCT SET PRICE = ? WHERE PRODUCTID = ?";
        result = updateProductField(sql, price, productId);
        return result;
    }

    public static boolean product_Update_Detail(ProductDTO product) {
        String detail = product.getDetail();
        int productId = product.getProductID();

        String sql = "UPDATE PRODUCT SET DETAIL = ? WHERE PRODUCTID = ?";
        result = updateProductField(sql, detail, productId);
        return result;
    }
}
