import java.util.*;

public class HospitalPatientRecordSystem {

    static int[] patientID = new int[50];
    static String[] name = new String[50];
    static int[] age = new int[50];
    static String[] gender = new String[50];
    static String[] disease = new String[50];
    static String[] doctor = new String[50];

    static double[] testFee = new double[50];
    static double[] doctorFee = new double[50];
    static double[] totalBill = new double[50];

    static String[] prescription = new String[50];
    static String[] test = new String[50];

    static int patientCount = 0;     // number of patients
    public static void main(String[] args){
        Scanner input = new Scanner (System.in);

        // ==================== MAIN MENU ==================== 
        int choice;
        do{
            System.out.println("==== Main Menu ====");
            System.out.println("1. Admin Login");
            System.out.println("2. Doctor Login");
            System.out.println("3.Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine();
            switch(choice){
                case 1:
                    adminLogin(input);
                    break;
                case 2:
                    doctorLogin(input);
                    break;
                case 3:
                    System.out.println("Exiting.....");
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        }
        while (choice != 3);

        input.close();    
    }

    public static void  adminLogin(Scanner input){
        String username = "admin";
        String password = "admin123";
        int attempt = 0;
        Boolean loggedIn = false;

        while (attempt < 3){
            System.out.print("Enter Admin Username: ");
            String user = input.nextLine();
            System.out.print("Enter Admin Password: ");
            String pass = input.nextLine();
            if (user.equals(username) && pass.equals(password)){
                loggedIn = true;
                System.out.println("Login successfully! Welcome Admin.");
                adminMenu(input);
                break;
            }
            else{
                attempt++;
                System.out.println("Incorrect login! Attempts left : " + (3-attempt));
            }
        }
        if (!loggedIn){
            System.out.println("You have used all attempts. Returning to main menu.....");
            return;
        }
    }

    // ==================== ADMIN MENU ====================
    public static void adminMenu(Scanner input){
	    int choice;
            do{
                System.out.println("\n==== ADMIN MENU ====");
                System.out.println("1. Add Patient Record");
                System.out.println("2. View All Pateints");
                System.out.println("3. Update Patient Record");
                System.out.println("4. Search Patient (by ID)");
                System.out.println("5. Billing");
                System.out.println("6. Logout");
                System.out.print("Enter user choice:");
                choice = input.nextInt();
                input.nextLine();

                switch(choice){
                    case 1:
                        addPatientRecord(input);
                        break;
                    case 2:
                        viewAllPatients(input);
                        break;
                    case 3:
                        updatePatientRecord(input);
                        break;
                    case 4:
                        searchPatientByID(input);
                        break;
                    case 5:
                        billing(input);
                        break;
                    case 6:
                        System.out.println("Logging Out......");
                        System.out.println("Returning to Main Menu.....");
                        return;
                    default:
                        System.out.println("Invalid Input!");
                }
            }
        while(choice != 6);
    }

    public static void addPatientRecord(Scanner input){
        System.out.print("Enter Patient ID: ");
        String patientID = input.nextLine();
        System.out.print("Enter Patient Name: ");
        String patientName = input.nextLine();
        int age = 0;
        while (true){
            System.out.print("Enter Patient Age: ");
            age = input.nextInt();
            if (age >= 0 && age <=120){
                break;
            }
            else{
                System.out.println("Age must be between 0-120. Try Again!");
            }
        }
        System.out.print("Enter Disease: ");
        String disease = input.nextLine();
            // Avaiblable doctor // Assign doctor
        System.out.println("Patient Added Successfully!");
        return;
    }
    public static void viewAllPatients(Scanner input){
        System.out.println("Viewing All Patients....");
        return;
    }
    public static void updatePatientRecord(Scanner input){
        System.out.println("Updating Patient Record....");
        return;
    }
    public static void searchPatientByID(Scanner input){
        System.out.println("Searching Patient By ID....");
        return;
    }
    public static void billing(Scanner input){
        System.out.println("Billing....");
        return;
    }

    public static void doctorLogin(Scanner input){
	return;
    }

}
