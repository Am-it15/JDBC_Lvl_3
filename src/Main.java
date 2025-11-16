import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n=================== Teaching Staff Manage ===================");
            System.out.println("1 : Add Staff");
            System.out.println("2 : Retrieve Staff");
            System.out.println("3 : Update Staff");
            System.out.println("4 : Delete Staff");
            System.out.println("5 : Exit");
            System.out.print("\nPlease enter your choice :: ");

            int choice = scan.nextInt();

            switch (choice) {
                case 1 -> AddStaff.add(scan);
                case 2 -> RetrieveStaff._retrieve(scan);
                case 3 -> UpdateStaff._updStaff(scan);
                case 4 -> RemoveStaff._rmvStaff(scan);
                case 5 -> exit();
                default -> System.out.println("Enter valid choice.....");
            }
        }
    }

    private static void exit() {
        System.out.println("Bye bye 👋👋");
        System.exit(0);
    }
}