package karrot.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_DAO {

    // DB 연결 메서드
    public static Connection getConnection() {
         String URL = "jdbc:oracle:thin:@localhost:1521:xe";
         String ID = "hr";
         String PW = "hr";


        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // JDBC 드라이버 로드
            Connection conn = DriverManager.getConnection(URL, ID, PW);
            return conn;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC 드라이버 로드 실패: " + e.getMessage(), e);
        } catch (SQLException e) {
            throw new RuntimeException("데이터베이스 연결 실패: " + e.getMessage(), e);
        }
    }


    // DB 연결 해제 메서드
    public static void closeDB(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("DB 연결 해제 오류: " + e.getMessage());
            }
        }
    }
}
