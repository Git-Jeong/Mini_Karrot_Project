package karrot.model.productAPI;

import karrot.model.DB_DAO;
import karrot.model.dto.ProductDTO;
import karrot.model.dto.SecureUserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Get_All_Product_API {
	public static ArrayList<ProductDTO> getAllProductAPI() {
		ArrayList<ProductDTO> productList = new ArrayList<>();
		// 상품 목록을 가변배열에 넣기 위한 것

		SecureUserInfo userInfo = SecureUserInfo.getInstance();
		String userId = userInfo.getSecureUserId();

		// DB에서 모든 상품의 LIST를 가지고 오는 것
		String sql = "SELECT PRODUCTID, PRODUCTNAME, PRICE, USERID, DETAIL, ONTIME, VIEWCOUNT  FROM PRODUCT WHERE USERID != ? AND ONSALE != 0";

		try (Connection conn = DB_DAO.getConnection(); PreparedStatement psmt = conn.prepareStatement(sql)) {

			psmt.setString(1, userId); // ID 설정

			try (ResultSet rs = psmt.executeQuery()) {
				while (rs.next()) { // 데이터 존재 여부 확인
					int productID = rs.getInt("PRODUCTID");
					String productName = rs.getString("PRODUCTNAME");
					int price = rs.getInt("PRICE");
					String userID = rs.getString("USERID");
					String detail = rs.getString("DETAIL");
					String timestamp = rs.getString("ONTIME");
					int viewCount = rs.getInt("VIEWCOUNT");

					// productID, userID, productName, price, detail, viewCount, timestamp
					productList
							.add(new ProductDTO(productID, userID, productName, price, detail, viewCount, timestamp));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("상품 조회 중 SQL 오류 발생: " + e.getMessage(), e);
		}

		return productList;
	}

	public static boolean viewCountPlus(ProductDTO product) {
		boolean result = false;
		int productID = product.getProductID();
		int viewCount = product.getViewCount();
		viewCount++;

		String sql = "UPDATE PRODUCT SET VIEWCOUNT = ? WHERE PRODUCTID = ?";

		try (Connection conn = DB_DAO.getConnection(); PreparedStatement psmt = conn.prepareStatement(sql)) {

			psmt.setInt(1, viewCount); // 조회수 설정
			psmt.setInt(2, productID); // ID 설정

			try (ResultSet rs = psmt.executeQuery()) {
				if (rs.next()) { // 데이터 존재 여부 확인
					result = true;
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("조회수 SQL 오류 발생: " + e.getMessage(), e);
		}
		return result;
	}

	public static boolean buyTheProduct(ProductDTO select_product) {
	    boolean result = false;
	    int productId = select_product.getProductID();  

	    SecureUserInfo userInfo = SecureUserInfo.getInstance();
	    String userId = userInfo.getSecureUserId();
	    
	    String sql_1 = "INSERT INTO PURCHASE VALUES(PURCHASE_NUM.NEXTVAL, ?, ?, SYSDATE)"; 
	    
	    try (Connection conn = DB_DAO.getConnection();
	         PreparedStatement psmt = conn.prepareStatement(sql_1)) {

	        psmt.setString(1, userId); // 구매자 ID 설정
	        psmt.setInt(2, productId); // 상품 ID 설정 

	        int rowsInserted = psmt.executeUpdate(); // 변경된 부분
	        
	        if (rowsInserted > 0) { // INSERT 성공 여부 확인
	            boolean updateCheck = updateOnSale(select_product);
	            if (updateCheck) {                        
	                result = true; 
	            }
	        }

	    } catch (SQLException e) {
	        throw new RuntimeException("구매하는 과정(1)에서 SQL 오류 발생: " + e.getMessage(), e);
	    }    
	    return result;
	}

	public static boolean updateOnSale(ProductDTO select_product) {
	    boolean result = false;
	    int productId = select_product.getProductID();  

	    String sql_1 = "UPDATE PRODUCT SET ONSALE = ? WHERE PRODUCTID = ?"; 
	    
	    try (Connection conn = DB_DAO.getConnection();
	         PreparedStatement psmt = conn.prepareStatement(sql_1)) {

	        psmt.setInt(1, 0); // 상품 판매 여부를 0으로 설정 (판매 완료 상태)
	        psmt.setInt(2, productId); // 상품 ID 설정 (인덱스 수정됨)

	        int rowsUpdated = psmt.executeUpdate(); // 변경된 부분

	        if (rowsUpdated > 0) { // UPDATE 성공 여부 확인  
	            conn.commit(); 
	            result = true; 
	        }

	    } catch (SQLException e) {
	        throw new RuntimeException("구매하는 과정(2)에서 SQL 오류 발생: " + e.getMessage(), e);
	    }    
	    return result;
	}

	
}
