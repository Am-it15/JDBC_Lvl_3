import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public static Connection getConnection() {
        String url= "jdbc:mysql://localhost:3306/test";
        String user= "root";
        String pswd="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(url, user, pswd);
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        }
        return null;
    }
}
