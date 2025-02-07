package karrot.model.db_connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB_DAO {

    private static String URL;
    private static String ID;
    private static String PW;

    static {
        try {
            Properties properties = new Properties();

            // 클래스패스에서 파일 로드
            InputStream input = DB_DAO.class.getClassLoader().getResourceAsStream("db.properties");

            if (input == null) {
                throw new FileNotFoundException("DB 설정 파일을 찾을 수 없습니다.");
            }

            properties.load(input);

            String driver = properties.getProperty("db.driver");
            String host = properties.getProperty("db.host");
            String port = properties.getProperty("db.port");
            String service = properties.getProperty("db.service");

            ID = properties.getProperty("db.username");
            PW = properties.getProperty("db.password");

            URL = String.format("%s:@%s:%s:%s", driver, host, port, service);
        } catch (IOException e) {
            throw new RuntimeException("DB 설정 파일 로드 실패", e);
        }
    }



    // DB 연결 메서드
    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(URL, ID, PW);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC 드라이버 로드 실패", e);
        } catch (SQLException e) {
            throw new RuntimeException("DB 연결 실패: " + e.getSQLState() + " - " + e.getMessage(), e);
        }
    }

    // DB 연결 해제 메서드
    public static void closeDB(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("DB 연결 해제 오류: " + e.getSQLState() + " - " + e.getMessage());
            }
        }
    }
}
