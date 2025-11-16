import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddStaff {
    public static void add(Scanner scan) {
        try {
            Connection addCon = DbConnection.getConnection();

            scan.nextLine(); // clear buffer

            System.out.print("Enter Name :: ");
            String sName = scan.nextLine();
            System.out.print("Enter Email :: ");
            String sEmail = scan.nextLine();
            System.out.print("Enter Phone no. :: ");
            long sPhone = scan.nextLong();
            scan.nextLine(); // clear after nextLong()
            System.out.print("Enter City Name :: ");
            String sCity = scan.nextLine();
            System.out.print("Enter State Name :: ");
            String sState = scan.nextLine();
            System.out.print("Enter Department :: ");
            String sDept = scan.nextLine();
            System.out.print("Enter Salary :: ");
            int sSal = scan.nextInt();

            String addQuery = "INSERT INTO techstaff (tsName, tsEmail, tsPhone, tsCity, tsState, tsDept, tsSalary) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement addPrep = addCon.prepareStatement(addQuery);

            addPrep.setString(1, sName);
            addPrep.setString(2, sEmail);
            addPrep.setLong(3, sPhone);
            addPrep.setString(4, sCity);
            addPrep.setString(5, sState);
            addPrep.setString(6, sDept);
            addPrep.setInt(7, sSal);

            int rowAffected = addPrep.executeUpdate();

            if (rowAffected > 0) {
                System.out.println("\n" + sName + " Added Successfully!");
                System.out.print(sName+"'s Staff id :: "+RetrieveStaff.indId(addCon,sName,sEmail, sPhone));
            } else {
                System.out.println("\n" + sName + " Not Added!");
            }

        } catch (Exception e) {
            System.out.println("Error :: " + e.getMessage());
        }
    }
}
