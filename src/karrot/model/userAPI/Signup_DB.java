package karrot.model.userAPI;

import karrot.model.DB_DAO;
import karrot.model.dto.MemberDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Signup_DB {
    public static boolean signupDB() {
        boolean result = false;
        Connection conn = DB_DAO.getConnection(); // DB 연결 가져오기
        MemberDTO memberDTO = MemberDTO.getInstance();

        if (conn == null) {
            System.out.println("DB 연결 실패!");
            return false;
        }

        // 트랜잭션 관리: 명시적으로 auto-commit을 false로 설정
        try {
            conn.setAutoCommit(false);  // 트랜잭션 시작

            String sql = "INSERT INTO SALE_USER (userID, userPW, userNAME) VALUES (?, ?, ?)";

            try (PreparedStatement psmt = conn.prepareStatement(sql)) {
                // PreparedStatement에 값 설정
                psmt.setString(1, memberDTO.getId()); // 사용자 ID
                psmt.setString(2, memberDTO.getPw()); // 비밀번호
                psmt.setString(3, memberDTO.getName()); // 이름

                // SQL 실행 및 결과 확인
                int db_result = psmt.executeUpdate();
                if (db_result > 0) {
                    // 삽입 성공 시 트랜잭션 커밋
                    conn.commit();
                    result = true;
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                // ID 중복 시 예외 처리
                System.out.println("이미 존재하는 ID입니다. 다른 ID를 입력하세요.");
            } catch (SQLException e) {
                // 기타 예외 발생 시 출력
                System.out.println("SQL 오류 발생: " + e.getMessage());
                conn.rollback(); // 오류 발생 시 롤백
            }

        } catch (SQLException e) {
            System.out.println("DB 연결 오류: " + e.getMessage());
        } finally {
            // DB 연결 해제
            DB_DAO.closeDB(conn);
        }
        return result; // 회원가입 성공 여부 반환
    }
}
