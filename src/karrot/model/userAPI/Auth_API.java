package karrot.model.userAPI;

import karrot.model.DB_DAO;
import karrot.model.dto.SecureUserInfo;
import karrot.model.dto.MemberDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Auth_API {
    public static boolean authAPI() {
        boolean authCheck = false;
        MemberDTO memberDTO = MemberDTO.getInstance();
        SecureUserInfo secureUserInfo = SecureUserInfo.getInstance();
        Connection conn = DB_DAO.getConnection(); // DB 연결 가져오기

        if (conn == null) {
            System.out.println("DB 연결 실패!");
        } else {
            String sql = "SELECT USERID FROM SALE_USER WHERE USERID = ? AND USERPW = ?";

            try (PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setString(1, secureUserInfo.getSecureUserId()); // VARCHAR 50으로 만들어둠
                psmt.setString(2, memberDTO.getPw()); // VARCHAR 50으로 만들어둠
                try (ResultSet rs = psmt.executeQuery()) {
                    if (rs.next()) {
                        try {
                            authCheck = true;
                        } catch (Exception e) {
                            System.out.println("유저정보를 받아오는 도중에 문제가 생겼습니다.");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("SQL 오류가 발생했습니다: " + e.getMessage());
            } finally {
                DB_DAO.closeDB(conn);
            }
        }
        return authCheck;
    }
}
