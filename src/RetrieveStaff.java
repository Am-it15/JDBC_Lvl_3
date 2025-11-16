import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class RetrieveStaff {
    public static void _retrieve(Scanner scan) {
        try {
            Connection rtvConn=DbConnection.getConnection();

            System.out.println("\n1: Individual staff");
            System.out.print("2: All Staff");
            System.out.print("\nEnter you choice :: ");
            int rtvChoice= scan.nextInt();

            switch (rtvChoice) {
                case 1 -> indStaff(scan, rtvConn);
                case 2 -> allStaff(rtvConn);
                default -> System.out.println("Enter valid choice");
            }
        } catch (Exception e) {
            System.out.println("Error :: "+e.getMessage());
        }
    }

    private static void allStaff(Connection rtvConn) {
        try {
            String rtvAll="SELECT * FROM techstaff";

            PreparedStatement allRtv= rtvConn.prepareStatement(rtvAll);
            ResultSet rs= allRtv.executeQuery();

            System.out.println("\n--- All TechStaff ---");
            System.out.printf("%-15s %-20s %-25s %-12s %-15s %-15s %-10s %-10s\n",
                    "ID", "Name", "Email", "Phone", "City", "State", "Dept", "Salary");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-15d %-20s %-25s %-12s %-15s %-15s %-10s %-10d\n",
                        rs.getLong("tid"),
                        rs.getString("tsName"),
                        rs.getString("tsEmail"),
                        rs.getLong("tsPhone"),
                        rs.getString("tsCity"),
                        rs.getString("tsState"),
                        rs.getString("tsDept"),
                        rs.getInt("tsSalary"));
            }
        } catch (Exception e) {
            System.out.println("Error :: "+e.getMessage());
        }


    }

    private static void indStaff(Scanner scan, Connection rtvConn) {
        try {
            System.out.print("\nEnter Staff id :: ");
            int rtvId = scan.nextInt();

            String indQuery = "SELECT * FROM techstaff Where tid= ?";
            PreparedStatement rtvPS= rtvConn.prepareStatement(indQuery);
            rtvPS.setInt(1, rtvId);

            ResultSet rtvRs= rtvPS.executeQuery();

            if (rtvRs.next()) {
                System.out.println("\nID : " + rtvRs.getInt("tid"));
                System.out.println("Name : " + rtvRs.getString("tsName"));
                System.out.println("Email : " + rtvRs.getString("tsEmail"));
                System.out.println("Phone : " + rtvRs.getLong("tsPhone"));
                System.out.println("City : " + rtvRs.getString("tsCity"));
                System.out.println("State : " + rtvRs.getString("tsState"));
                System.out.println("Dept : " + rtvRs.getString("tsDept"));
                System.out.println("Salary : " + rtvRs.getInt("tsSalary"));
            } else {
                System.out.println("No Record Found!");
            }
        } catch (Exception e) {
            System.out.println("Error :: " + e.getMessage());
        }
    }


    public static int indId(Connection addCon, String sName, String sEmail, long sPhone) {
        try {
            String indIdQuery = "SELECT * FROM techstaff WHERE tsName=? AND tsEmail=? AND tsPhone=?";
            PreparedStatement indPs = addCon.prepareStatement(indIdQuery);
            indPs.setString(1, sName);
            indPs.setString(2, sEmail);
            indPs.setLong(3, sPhone);

            ResultSet rtvRs = indPs.executeQuery(); // execute the query

            if (rtvRs.next()) {
                return rtvRs.getInt("tid");
            } else {
                System.out.println("No Record Found!");
            }
        } catch (Exception e) {
            System.out.println("Error :: " + e.getMessage());
        }
        return 0;
    }
}
