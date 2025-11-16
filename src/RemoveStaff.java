import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class RemoveStaff {
    public static void _rmvStaff(Scanner scan) {
        try {
            Connection updConn = DbConnection.getConnection();

            System.out.print("\nEnter Staff id :: ");
            int updId = scan.nextInt();

            String rmQuery = "DELETE FROM techstaff where tid=?";

            PreparedStatement rmPrep = updConn.prepareStatement(rmQuery);
            rmPrep.setInt(1,updId);

            int rowAffected = rmPrep.executeUpdate();

            if (rowAffected > 0) {
                System.out.println("\n" + updId + " Deleted Successfully!");
            } else {
                System.out.println("\n" + updId + " Not Deleted!");
            }



        } catch (Exception e) {
            System.out.println("Error :: "+e.getMessage());
        }
    }
}
