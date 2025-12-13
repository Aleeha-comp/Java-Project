import java.util.*;
import java.io.*;

public class HospitalPatientRecordSystem {

    // ==================== ARRAYS ====================
    static int[] patientID = new int[10];
    static String[] patientName = new String[10];
    static int[] age = new int[10];
    static String[] gender = new String[10];
    static String[] disease = new String[10];
    static String[] doctor = new String[10];

    static double[] testFee = new double[10];
    static double[] doctorFee = new double[10];
    static double[] totalBill = new double[10];

    static String[] prescription = new String[10];
    static String[] test = new String[10];

    static int patientCount = 0;     // number of patients

    // ========== DOCTORS ==========
    static String[] doctorUsernames = {"doctor1", "doctor2", "doctor3", "doctor4", "doctor5", "doctor6", "doctor7", "doctor8", "doctor9", "doctor10"};
    static String[] doctorPasswords = {"1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999", "1010"};
    static String[] doctorNames = {"Dr.Ali", "Dr.Khan", "Dr.Sara", "Dr.Zara", "Dr.Mohammad", "Dr.Tariq", "Dr.Fatima", "Dr.Jameela", "Dr.Hassan", "Dr.Nina"};
    static String[] doctorSpecialties = {
                                            "Cardiologist",  // Dr.Ali
                                            "Neurologist",   // Dr.Khan
                                            "Orthopedic",    // Dr.Sara
                                            "Ophthalmologist",  // Dr.Zara
                                            "Dermatologist", // Dr.Mohammed
                                            "General Surgeon", // Dr.Tariq
                                            "Gynecologist",  // Dr.Fatima
                                            "Psychiatrist",  // Dr.Jameela
                                            "Urologist",     // Dr.Hassan
                                            "Endocrinologist" // Dr.Nina
                                        };

    static String loggedDoctor = "";

    // ==================== MAIN MENU ==================== 
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);

        try{
            // Read data from file before showing the main menu
            readDataFromFile();

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
            
        }
        catch (IOException e){
            System.out.println("An error occured with file handling.");
            e.printStackTrace();
        }
        catch(Exception e){
            System.out.println("An unexpected error occured.");
            e.printStackTrace();
        }
        finally{
            input.close();
        } 
        
    }

     // ================== Admin LOGIN ==================
    public static void  adminLogin(Scanner input) throws IOException{
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
            System.out.println("All attempts used. Returning to main menu.....");
            return;
        }
    }

    // ================== DOCTOR LOGIN ==================                  ( the doctor should only see his patients )
    public static void doctorLogin(Scanner input) throws IOException{
        int attempts = 0;
       
         while(attempts < 3){
            System.out.print("Enter Doctor Username: ");
            String user = input.nextLine();
            System.out.print("Enter Doctor Password: ");
            String pass = input.nextLine();

            for (int i = 0; i < doctorUsernames.length; i++) {
                if(user.equals(doctorUsernames[i]) && pass.equals(doctorPasswords[i])) {
                    loggedDoctor = doctorNames[i];
                    System.out.println("Login successful! Welcome " + loggedDoctor);
                    doctorMenu(input);
                    return;
                }
            }

            attempts++;
            System.out.println("Incorrect login! Attempts left: " + (3 - attempts));
        }

        System.out.println("All attempts used. Returning to main menu.");
    }

    // ==================== ADMIN MENU ====================
    public static void adminMenu(Scanner input) throws IOException{
	    int choice;
            do{
                System.out.println("\n==== ADMIN MENU ====");
                System.out.println("1. Add Patient Record");
                System.out.println("2. View All Pateints");
                System.out.println("3. Update Patient Record");
                System.out.println("4. Search Patient (by ID)");
                System.out.println("5. Billing");
                System.out.println("6. Logout");
                System.out.print("Enter user choice: ");
                choice = input.nextInt();
                input.nextLine();

                switch(choice){
                    case 1:
                        System.out.println("Adding Patient Record....");
                        addPatientRecord(input);
                        break;
                    case 2:
                        System.out.println("Viewing All Patients Record....");
                        viewAllPatients(input);
                        break;
                    case 3:
                        System.out.println("Updating Patient Record....");
                        updatePatientRecord(input);
                        break;
                    case 4:
                        System.out.println("Searching Patient By ID....");
                        searchPatientByID(input);
                        break;
                    case 5:
                        System.out.println("Patient Bill...");
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
    public static void doctorMenu(Scanner input) throws IOException{
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
                    viewMyPatients(input);
                    break;

                case 2:
                    System.out.println("Searching patient by ID...");
                    searchPatientByID(input);
                    break;

                case 3:
                    System.out.println("Prescription section...");
                    addPrescription(input);
                    break;

                case 4:
                    System.out.println("Test section...");
                    addTest(input);
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
    public static void addPatientRecord(Scanner input) throws IOException{
        if (patientCount == patientID.length){
            expandArray();
        }

        int newPatientId;
        while (true) {
            System.out.print("Enter Patient ID: ");
            newPatientId = input.nextInt();
            input.nextLine();
            
            if (isPatientIdExists(newPatientId)) {
                System.out.println("Patient ID already exists. Please enter a unique Patient ID.");
            } else {
                break; // Unique ID entered, exit the loop
            }
        }
        
        patientID[patientCount] = newPatientId;

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
  
        // Assign doctor according to the disease       
        String assignedDoctor = assignDoctorBasedOnDisease(disease[patientCount]);
        doctor[patientCount] = assignedDoctor;
        
        // Display assigned doctor for confirmation
        System.out.println("Assigned Doctor: " + assignedDoctor);
        

        prescription[patientCount] = "Not added";
        test[patientCount] = "Not added";

        testFee[patientCount] = 0;
        doctorFee[patientCount] = 0;
        totalBill[patientCount] = 0;

        // Display the newly added patient record
        System.out.println("Patient Added Successfully!");
        System.out.println("Added Patient Details.");
        displayPatient(patientCount);
        
        patientCount++;
        saveDataToFile();
        return;
    }

    // ================== ASSIGN DOCTOR ACCORDING TO THE DISEASE ====================
    public static String assignDoctorBasedOnDisease(String disease) {
    // Check if the disease contains certain keywords and assign the doctor
    
    // Handle null or empty disease
    if (disease == null || disease.trim().isEmpty()) {
        return "Dr.Ali";  // Default to Dr.Ali if disease is null or empty
    }
    
    String diseaseLower = disease.toLowerCase().trim();
    
    if (diseaseLower.contains("heart")) {
        return doctorNames[0];  // Assign Dr.Ali (Cardiologist)
    } 
    else if (disease.toLowerCase().contains("brain") || disease.toLowerCase().contains("neurology")) {
        return doctorNames[1];  // Assign Dr.Khan (Neurologist)
    } 
    else if (disease.toLowerCase().contains("bone") || disease.toLowerCase().contains("orthopedic")) {
        return doctorNames[2];  // Assign Dr.Sara (Orthopedic)
    } 
    else if (disease.toLowerCase().contains("eye") || disease.toLowerCase().contains("ophthalmologist")) {
        return doctorNames[3];  // Assign Dr.Zara (Ophthalmologist)
    } 
    else if (disease.toLowerCase().contains("skin") || disease.toLowerCase().contains("dermatology")) {
        return doctorNames[4];  // Assign Dr.Mohammed (Dermatologist)
    } 
    else if (disease.toLowerCase().contains("surgery") || disease.toLowerCase().contains("general")) {
        return doctorNames[5];  // Assign Dr.Tariq (General Surgeon)
    } 
    else if (disease.toLowerCase().contains("pregnancy") || disease.toLowerCase().contains("gynecology")) {
        return doctorNames[6];  // Assign Dr.Fatima (Gynecologist)
    } 
    else if (disease.toLowerCase().contains("mental") || disease.toLowerCase().contains("psychiatry")) {
        return doctorNames[7];  // Assign Dr.Jameela (Psychiatrist)
    } 
    else if (disease.toLowerCase().contains("urinary") || disease.toLowerCase().contains("kidney")) {
        return doctorNames[8];  // Assign Dr.Hassan (Urologist)
    } 
    else if (disease.toLowerCase().contains("hormone") || disease.toLowerCase().contains("endocrinology")) {
        return doctorNames[9];  // Assign Dr.Nina (Endocrinologist)
    } 
    else {
        return "Dr.Ali";  // Default to Dr.Ali if no disease match
    }
}
    // ================== EXPAND ARRAYS ==================
    public static void expandArray() throws IOException{
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
    public static int[] copyInt(int[] oldArr, int newSize) throws IOException{
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

    // ==================== SAVE DATA TO FILE ====================
     public static void saveDataToFile() throws IOException {
        FileWriter fw = new FileWriter("patient_data.txt", false);  // True--> Append Data To File
        BufferedWriter writer = new BufferedWriter(fw);

        for (int i = 0; i < patientCount; i++) {
            // In file each patient details are separated by commas
            writer.write(patientID[i] + "," + patientName[i] + "," + age[i] + "," + gender[i] + "," + disease[i] + "," + doctor[i] + ","
                    + prescription[i] + "," + test[i] + "," + testFee[i] + "," + doctorFee[i] + "," + totalBill[i]);
            writer.newLine(); // New line after each patient's data
        }
        writer.close();
        System.out.println("Patient data saved successfully.");
    }
    // ==================== READ DATA FROM FILE ====================
    public static void readDataFromFile() throws IOException {
    File file = new File("patient_data.txt");
    if (!file.exists()) {
        System.out.println("File not Found.");
        return;
    }

    Scanner reader = new Scanner(file);
    while (reader.hasNextLine()) {
        String line = reader.nextLine();
        String[] data = line.split(",");
        if (data.length == 11) {  // Ensure that all necessary fields are present

            // Check if array needs expansion
            if (patientCount >= patientID.length) {
                expandArray(); // Expand array if full
            }

            patientID[patientCount] = Integer.parseInt(data[0]);
            patientName[patientCount] = data[1];
            age[patientCount] = Integer.parseInt(data[2]);
            gender[patientCount] = data[3];
            disease[patientCount] = data[4];
            doctor[patientCount] = data[5];
            prescription[patientCount] = data[6];
            test[patientCount] = data[7];
            testFee[patientCount] = Double.parseDouble(data[8]);
            doctorFee[patientCount] = Double.parseDouble(data[9]);
            totalBill[patientCount] = Double.parseDouble(data[10]);
            
            patientCount++;  // Increment the patient count as data is added
        }
    }
    reader.close();
    System.out.println("Patient data loaded successfully.");
}

    // ==================== VIEW ALL PATIENTS ======================
    public static void viewAllPatients(Scanner input) throws IOException{
        if (patientCount == 0){
            System.out.println("No patientss available!");
            return;
        }
        for (int i = 0; i < patientCount; i++) {
            displayPatient(i);
        }
    }

    // ================== VIEW MY PATIENTS ==================
    public static void viewMyPatients(Scanner input) throws IOException{

        boolean found = false;

        for (int i = 0; i < patientCount; i++) {
            if (doctor[i].equalsIgnoreCase(loggedDoctor)) {
                displayPatient(i);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No patients assigned to you.");
        }
    }


    // ==================== UPDATE PATIENT RECORD ====================
public static void updatePatientRecord(Scanner input) throws IOException{
    System.out.print("Enter Patient ID: ");
    int ID = input.nextInt();
    input.nextLine();
    int choice;
    boolean patientFound = false;
    
    for (int i = 0; i < patientCount; i++){
        if (patientID[i] == ID){
            patientFound = true;
            System.out.println("Current Details: ");
            displayPatient(i); 

            System.out.println("What do you want to update? (1-4): ");
            System.out.println("1. Name");
            System.out.println("2. Age");
            System.out.println("3. Disease");
            System.out.println("4. Doctor");

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
                        age[i] = input.nextInt();  
                        input.nextLine();
                        if (age[i] >= 0 && age[i] <= 120){
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
                    // Assign doctor according to the disease       
                    String assignedDoctor = assignDoctorBasedOnDisease(disease[i]);
                    doctor[i] = assignedDoctor;
                    // Display assigned doctor for confirmation
                    System.out.println("Assigned Doctor: " + assignedDoctor);
                    break;
                case 4:
                   System.out.println("Select New Doctor:");
                        for (int d = 0; d < doctorNames.length; d++) {
                            System.out.println((d + 1) + ". " + doctorNames[d]);
                        }
                        int dChoice = input.nextInt();
                        input.nextLine();
                        if (dChoice >= 1 && dChoice <= doctorNames.length) {
                            doctor[i] = doctorNames[dChoice - 1];
                        } else {
                            System.out.println("Invalid!");
                        }
                        break;
                default:
                    System.out.println("Invalid choice!");
            }
            saveDataToFile();
            System.out.println("Updated Successfully");
            return;
        }
    }
    if (!patientFound) {
        System.out.println("Patient Not Found.");
    }
}

    // ==================== SEARCH PATIENT BY ID ====================
    public static void searchPatientByID(Scanner input) throws IOException{
        System.out.print("Enter Patient ID: ");
        int ID = input.nextInt();

        for (int i = 0; i < patientCount; i++) {
            if (patientID[i] == ID) {
                displayPatient(i);
                return;
            }
        }

        System.out.println("Patient Not Found!");
    }

    // ==================== BILLING ====================
    public static void billing(Scanner input) throws IOException{
        System.out.println("Billing....");
        System.out.print("Enter Patient ID: ");
        int ID = input.nextInt();
        for (int i = 0; i < patientCount; i++) {
            if (patientID[i] == ID) {

                System.out.print("Enter Test Fee: ");
                testFee[i] = input.nextDouble();

                System.out.print("Enter Doctor Fee: ");
                doctorFee[i] = input.nextDouble();

                totalBill[i] = testFee[i] + doctorFee[i];

                System.out.println("Total Bill = " + totalBill[i]);
                saveDataToFile();
                return;
            }
        }
        System.out.println("Patient Not Found!");
    }     

    // ================== PRESCRIPTION ==================
    public static void addPrescription(Scanner input) throws IOException{
        System.out.print("Enter Patient ID: ");
        int ID = input.nextInt();
        input.nextLine();

        for (int i = 0; i < patientCount; i++) {
            if (patientID[i] == ID) {

                System.out.print("Enter Prescription: ");
                prescription[i] = input.nextLine();

                saveDataToFile();

                System.out.println("Prescription added!");
                return;
            }
        }

        System.out.println("Patient Not Found!");
    }

     // ================== TEST ==================
    static void addTest(Scanner input) throws IOException{
        System.out.print("Enter Patient ID: ");
        int ID = input.nextInt();
        input.nextLine();

        for (int i = 0; i < patientCount; i++) {
            if (patientID[i] == ID) {

                System.out.print("Enter Test Name: ");
                test[i] = input.nextLine();

                System.out.println("Test added!");

                saveDataToFile();
                
                return;
            }
        }

        System.out.println("Patient Not Found!");
    }

    // ================== CHECK IF PATIENT ID EXISTS ==================
    public static boolean isPatientIdExists(int id) {
        for (int i = 0; i < patientCount; i++) {
            if (patientID[i] == id) {
                return true;
            }
        }
        return false;
    }

    // ================== DISPLAY PATIENT ==================
    static void displayPatient(int i) throws IOException{
        System.out.println("\n----------------------------");
        System.out.println("ID: " + patientID[i]);
        System.out.println("Name: " + patientName[i]);
        System.out.println("Age: " + age[i]);
        System.out.println("Gender: " + gender[i]);
        System.out.println("Disease: " + disease[i]);
        System.out.println("Doctor: " + doctor[i]);
        System.out.println("Prescription: " + prescription[i]);
        System.out.println("Test: " + test[i]);
        System.out.println("Test Fee: " + testFee[i]);
        System.out.println("Doctor Fee: " + doctorFee[i]);
        System.out.println("Total Bill: " + totalBill[i]);
        System.out.println("----------------------------");
    }
}

