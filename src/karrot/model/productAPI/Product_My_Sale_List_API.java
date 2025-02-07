package karrot.model.productAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import karrot.model.db_connect.DB_DAO;
import karrot.model.dto.ProductDTO;
import karrot.model.dto.SecureUserInfo;

public class Product_My_Sale_List_API {
	public static  ArrayList<ProductDTO> my_Sale_List_API() {
		ArrayList<ProductDTO> productList = new ArrayList<>();
		//상품 목록을 가변배열에 넣기 위한 것
		
		SecureUserInfo userInfo = SecureUserInfo.getInstance();
		String userId = userInfo.getSecureUserId(); 

		//DB에서 모든 상품의 LIST를 가지고 오는 것
		String sql = "SELECT PRODUCTID, PRODUCTNAME, PRICE, VIEWCOUNT FROM PRODUCT WHERE USERID = ? AND ONSALE = ?";

		try (Connection conn = DB_DAO.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql)) { 
			
			psmt.setString(1, userId); // ID 설정 
			psmt.setInt(2, 1); // ID 설정 

			try (ResultSet rs = psmt.executeQuery()) {

				// 쿼리 실행 후 첫 번째 rs.next()만 호출
				while (rs.next()) {
					//rs.next에 값이 있는 만큼 
					int productId = rs.getInt("PRODUCTID");
					String productName = rs.getString("PRODUCTNAME"); // ResultSet에서 USERNAME 가져오기
					int price = rs.getInt("PRICE"); // ResultSet에서 USERID 가져오기
					int viewCount = rs.getInt("VIEWCOUNT"); 
					productList.add(new ProductDTO(productId, productName, price, viewCount));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("상품 목록 중 SQL 오류 발생: " + e.getMessage(), e);
		}
		
		
		return productList;
	}
}
