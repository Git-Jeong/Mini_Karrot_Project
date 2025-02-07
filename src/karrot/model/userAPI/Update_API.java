package karrot.model.userAPI;

import karrot.model.db_connect.DB_DAO;
import karrot.model.dto.SecureUserInfo;
import karrot.model.dto.MemberDTO;

import java.sql.*;

public class Update_API {
    public static boolean updateDB() {
        boolean result = false;
        MemberDTO memberDTO = MemberDTO.getInstance();
        SecureUserInfo secureUserInfo = SecureUserInfo.getInstance();
        Connection conn = DB_DAO.getConnection(); // DB 연결 가져오기

        if (conn == null) {
            System.out.println("DB 연결 실패!");
            return false;
        }

        String sql = "UPDATE SALE_USER SET USERPW = ?, USERNAME = ? WHERE USERID = ?";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) {
            // 5. PreparedStatement에 값 설정
            psmt.setString(1, memberDTO.getPw()); // 비밀번호 VARCHAR(50)
            psmt.setString(2, memberDTO.getName()); // 이름 VARCHAR(50)
            psmt.setString(3, secureUserInfo.getSecureUserId()); // 이름 VARCHAR(50)

            // 6. SQL 실행 및 결과 확인
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            // 8. ID 중복 시 예외 처리
            System.out.println("이미 존재하는 ID입니다. 다른 ID를 입력하세요.");
        } catch (SQLException e) {
            // 9. SQL 오류 처리
            System.out.println("SQL 오류가 발생했습니다: " + e.getMessage());
        } finally {
            DB_DAO.closeDB(conn);
        }


        return result;
    }
}
