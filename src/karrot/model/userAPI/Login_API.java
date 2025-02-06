package karrot.model.userAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import karrot.model.DB_DAO;
import karrot.model.dto.SecureUserInfo;
import karrot.model.dto.MemberDTO;

public class Login_API {
	public static void loginAPI() {
		MemberDTO memberDTO = MemberDTO.getInstance();
		SecureUserInfo secureUserInfo = SecureUserInfo.getInstance();
		String sql = "SELECT USERID, USERNAME FROM SALE_USER WHERE USERID=? AND USERPW=?";

		try (Connection conn = DB_DAO.getConnection();
			 PreparedStatement psmt = conn.prepareStatement(sql)) {

			psmt.setString(1, memberDTO.getId()); // ID 설정
			psmt.setString(2, memberDTO.getPw()); // PW 설정

			try (ResultSet rs = psmt.executeQuery()) {

				// 쿼리 실행 후 첫 번째 rs.next()만 호출
				if (rs.next()) {
					String name = rs.getString("USERNAME"); // ResultSet에서 USERNAME 가져오기
					String id = rs.getString("USERID"); // ResultSet에서 USERID 가져오기
					secureUserInfo.setAllSecureUser(name, id);
				} else {
					// 결과가 없을 경우
					System.out.println("로그인 실패 - ID 또는 비밀번호가 일치하지 않음");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("로그인 중 SQL 오류 발생: " + e.getMessage(), e);
		}
	}
}
