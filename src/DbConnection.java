import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String pswd = "";

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pswd);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return con;
    }
}
