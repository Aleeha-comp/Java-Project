import java.util.*;

public class HospitalPatientRecordSystem {

    // ==================== ARRAYS ====================
    static int[] patientID = new int[50];
    static String[] patientName = new String[50];
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

     // ================== Admin LOGIN ==================
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

    // ================== DOCTOR LOGIN ==================
    public static void doctorLogin(Scanner input){
        String correctUser = "doctor";
        String passKey = "1234";
        int attempts = 0;
        boolean loggedIn = false;

        while(attempts < 3){
            System.out.print("Enter Doctor Username: ");
            String user = input.nextLine();
            System.out.print("Enter Doctor Password: ");
            String pass = input.nextLine();
            if(user.equals(correctUser) && pass.equals(passKey)){
                loggedIn = true;
                System.out.println("Login successfully! Welcome Doctor.");
                doctorMenu(input);
                break;
            }
            else {
                attempts++;
                System.out.println("Incoorect login! Attempts left:" +(3- attempts));
            }
        }
        if(!loggedIn){
            System.out.println("\nYou have used all the Attempts! Returning to MAIN MENU..");
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

     // ==================== DOCTOR MENU ====================
    public static void doctorMenu(Scanner input){
        int choice;
            do{
            System.out.println("\n===== DOCTOR MENU =====");
            System.out.println("1. View My Patients");
            System.out.println("2. Search Patients (by ID)");
            System.out.println("3. Prescription");
            System.out.println("4. Test");
            System.out.println("5. Logout");
            System.out.print("Enter user choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Viewing my patients...");
                    break;

                case 2:
                    System.out.println("Searching patient by ID...");
                    break;

                case 3:
                    System.out.println("Prescription section...");
                    break;

                case 4:
                    System.out.println("Test section...");
                    break;

                case 5:
                    System.out.println("Logging Out......");
                    System.out.println("Returning to Main Menu.....");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

    }

    // ================== ADD PATIENT RECORD ==================
    public static void addPatientRecord(Scanner input){
        if (patientCount == patientID.length){
            expandArray();
        }

        System.out.print("Enter Patient ID: ");
        patientID[patientCount] = input.nextInt();
        input.nextLine();

        System.out.print("Enter Patient Name: ");
        patientName[patientCount] = input.nextLine();

        while (true){
            System.out.print("Enter Patient Age: ");
            age[patientCount] = input.nextInt();
            input.nextLine();
            if (age[patientCount] >= 0 && age[patientCount] <=120){
                break;
            }
            else{
                System.out.println("Age must be between 0-120. Invalid Input! Try Again!");
            }
        }

        System.out.print("Enter Gender: ");
        gender[patientCount] = input.nextLine();

        System.out.print("Enter Disease: ");
        disease[patientCount] = input.nextLine();
  
        // Assigning Doctor not done yet
        System.out.print("Assign Doctor: ");
        doctor[patientCount] = input.nextLine();

        prescription[patientCount] = "Not added";
        test[patientCount] = "Not added";

        testFee[patientCount] = 0;
        doctorFee[patientCount] = 0;
        totalBill[patientCount] = 0;

        patientCount++;

        System.out.println("Patient Added Successfully!");
        return;
    }

    // ================== EXPAND ARRAYS ==================
    public static void expandArray() {
        int newSize = patientID.length * 2;

        patientID = copyInt(patientID, newSize);
        age = copyInt(age, newSize);

        patientName = copyString(patientName, newSize);
        gender = copyString(gender, newSize);
        disease = copyString(disease, newSize);
        doctor = copyString(doctor, newSize);

        prescription = copyString(prescription, newSize);
        test = copyString(test, newSize);

        testFee = copyDouble(testFee, newSize);
        doctorFee = copyDouble(doctorFee, newSize);
        totalBill = copyDouble(totalBill, newSize);

        System.out.println("Arrays expanded to size: " + newSize);
    }

    // ==================== COPY METHODS FOR EXPANSION ====================
    public static int[] copyInt(int[] oldArr, int newSize) {
        int[] newArr = new int[newSize];
        for (int i = 0; i < oldArr.length; i++) newArr[i] = oldArr[i];
        return newArr;
    }

    public static double[] copyDouble(double[] oldArr, int newSize) {
        double[] newArr = new double[newSize];
        for (int i = 0; i < oldArr.length; i++) newArr[i] = oldArr[i];
        return newArr;
    }

    public static String[] copyString(String[] oldArr, int newSize) {
        String[] newArr = new String[newSize];
        for (int i = 0; i < oldArr.length; i++) newArr[i] = oldArr[i];
        return newArr;
    }

    // ==================== VIEW ALL PATIENTS ====================
    public static void viewAllPatients(Scanner input){
        if (patientCount == 0){
            System.out.println("No patientss available!");
            return;
        }
        for (int i = 0; i < patientCount; i++) {
            displayPatient(i);
        }
    }

    // ==================== UPDATE PATIENT RECORD ====================
    public static void updatePatientRecord(Scanner input){
        System.out.print("Enter Patient ID: ");
        int ID = input.nextInt();
        input.nextLine();
        int choice;
        for (int i = 0; i < patientCount; i++){
            if (patientID[i] == ID){
                System.out.println("Current Details: ");
                System.out.println("1. Name");
                System.out.println("2. Age");
                System.out.println("3. Disease");
                System.out.println("4. Doctor");

                System.out.println("What do you want to update? (1-4): ");
                choice = input.nextInt();
                input.nextLine();

                switch (choice){
                    case 1:
                        System.out.println("Enter new name: ");
                        patientName[i] = input.nextLine();
                        break;
                    case 2:
                        while (true){
                            System.out.print("Enter Patient Age: ");
                            age[patientCount] = input.nextInt();
                            input.nextLine();
                            if (age[patientCount] >= 0 && age[patientCount] <=120){
                                break;
                            }
                            else{
                                System.out.println("Age must be between 0-120. Invalid Input! Try Again!");
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Enter new disease: ");
                        disease[i] = input.nextLine();
                        break;
                    case 4:
                        System.out.println("Enter new Available Doctor:");
                        //       not done yet
                        break;
                }
                System.out.println("Updated Successfully");
            }
        }
        System.out.println("Patient Not Found.");                                                     //// Check(This line is printing even when it should not)
            return;
        }

    // ==================== SEARCH PATIENT BY ID ====================
    public static void searchPatientByID(Scanner input){
        System.out.print("Enter Patient ID: ");
        int ID = input.nextInt();

        for (int i = 0; i < patientCount; i++) {
            if (patientID[i] == ID) {
                displayPatient(i);
                return;
            }
        }

        System.out.println("Patient Not Found!");
        return;
    }

    // ==================== BILLING ====================
    public static void billing(Scanner input){
        System.out.println("Billing....");
        return;
    }

    public static void displayPatient(int i){
        // it will include all the prestriction , fees, test, etc etc
        // not done yet
        return;
    }
 }
