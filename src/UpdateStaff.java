import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateStaff {

    public static void _updStaff(Scanner scan) {
        try {
            Connection updConn = DbConnection.getConnection();
            if (updConn == null) {
                System.out.println("Database connection failed ❌");
                return;
            }

            // Read Staff ID
            System.out.print("\nEnter Staff ID: ");
            int updId = scan.nextInt();
            scan.nextLine();

            // Show update options
            System.out.println("1: Update Email");
            System.out.println("2: Update Phone");
            System.out.println("3: Update Salary");
            System.out.print("Choose field to update: ");
            int choice = Integer.parseInt(scan.nextLine());

            String field;
            switch (choice) {
                case 1 -> field = "tsEmail";
                case 2 -> field = "tsPhone";
                case 3 -> field = "tsSalary";
                default -> {
                    System.out.println("Invalid choice!");
                    return;
                }
            }

            System.out.print("Enter new value: ");
            String newValue = scan.nextLine();

            String updQuery = "UPDATE techstaff SET " + field + "=? WHERE tid=?";
            PreparedStatement updPs = updConn.prepareStatement(updQuery);

            if (field.equals("tsEmail")) {
                updPs.setString(1, newValue);
            } else if (field.equals("tsPhone")) {
                updPs.setLong(1, Long.parseLong(newValue));
            } else {
                updPs.setInt(1, Integer.parseInt(newValue));
            }

            updPs.setInt(2, updId);

            int rowsAffected = updPs.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Staff updated successfully ✅");
            } else {
                System.out.println("Staff ID not found ❌");
            }

        } catch (Exception e) {
            System.out.println("Error :: " + e.getMessage());
        }
    }

}
