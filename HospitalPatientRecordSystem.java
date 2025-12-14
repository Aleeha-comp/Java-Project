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

            int choice = 0;
            do{
                System.out.println("==== Main Menu ====");
                System.out.println("1. Admin Login");
                System.out.println("2. Doctor Login");
                System.out.println("3.Exit");
                System.out.print("Enter choice: ");
                try{
                choice = input.nextInt();
                input.nextLine();
                }catch (InputMismatchException e) {
                    System.out.println("Invalid input! Enter number only.");
                    input.nextLine();
                    continue;
                }

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
                        System.out.println("Invalid Choice!");
                }
            }while (choice != 3);
          
        } catch (Exception e) {
            System.out.println("Unexpected error occurred.");
            e.printStackTrace();
        } finally {
            input.close();
        }
    }  
        
     // ================== Admin LOGIN ==================
    public static void adminLogin(Scanner input) {
    File file = new File("admin.txt");
        if (!file.exists()) {
            System.out.println("Admin file missing!");
            return;
        }

    int attempt = 0;
    boolean loggedIn = false;

    while (attempt < 3) {

        System.out.print("Enter Admin Username: ");
        String user = input.nextLine();

        System.out.print("Enter Admin Password: ");
        String pass = input.nextLine();

        try(Scanner reader = new Scanner(file)){

        if (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] data = line.split(",");

            if (data[0].equals(user) && data[1].equals(pass)) {
                loggedIn = true;
                System.out.println("Login successful! Welcome Admin.");
                adminMenu(input);
                return;
            }
          }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading admin file.");
            return;
        }
        attempt++;
        System.out.println("Incorrect login! Attempts left: " + (3 - attempt));
    }
    System.out.println("All attempts used. Returning to main menu.");
}
        
    // ================== DOCTOR LOGIN ==================                 
    public static void doctorLogin(Scanner input) {
    File file = new File("doctors.txt");
        if (!file.exists()) {
            System.out.println("Doctor file missing!");
            return;
        }

    int attempts = 0;

    while (attempts < 3) {

        System.out.print("Enter Doctor Username: ");
        String user = input.nextLine();

        System.out.print("Enter Doctor Password: ");
        String pass = input.nextLine();

        try(Scanner reader = new Scanner(file)){

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] data = line.split(",");

            String savedUser = data[0];
            String savedPass = data[1];
            String savedDoctorName = data[2];

            if (savedUser.equals(user) && savedPass.equals(pass)) {
                loggedDoctor = savedDoctorName;
                System.out.println("Login successful! Welcome " + loggedDoctor);
                doctorMenu(input);
                return;
            }
        }
        }catch (FileNotFoundException e) {
            System.out.println("Error reading admin file.");
            return;
        }
        attempts++;
        System.out.println("Incorrect login! Attempts left: " + (3 - attempts));
        }
        System.out.println("All attempts used. Returning to main menu.");
    }

    // ==================== ADMIN MENU ====================
    public static void adminMenu(Scanner input) {
	    int choice = 0;
            do{
                System.out.println("\n==== ADMIN MENU ====");
                System.out.println("1. Add Patient Record");
                System.out.println("2. View All Pateints");
                System.out.println("3. Update Patient Record");
                System.out.println("4. Search Patient (by ID)");
                System.out.println("5. Billing");
                System.out.println("6. Logout");

             boolean validInput = false;

            while (!validInput) {
            System.out.print("Enter user choice: ");
            try {
                choice = input.nextInt();
                input.nextLine(); 
                validInput = true; // input is valid, exit inner loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                input.nextLine(); 
                 }
            }
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
                        System.out.println("Invalid Choice!");
                }
            }
        while(choice != 6);
    }

     // ==================== DOCTOR MENU ====================
    public static void doctorMenu(Scanner input) {
        int choice = 0;
            do{
            System.out.println("\n===== DOCTOR MENU =====");
            System.out.println("1. View My Patients");
            System.out.println("2. Search Patients (by ID)");
            System.out.println("3. Prescription");
            System.out.println("4. Test");
            System.out.println("5. Logout");
            boolean validInput = false;

            while (!validInput) {
            System.out.print("Enter user choice: ");
            try {
                choice = input.nextInt();
                input.nextLine(); 
                validInput = true; // input is valid, exit inner loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                input.nextLine(); 
                 }
        }

            switch (choice) {
                case 1:
                    System.out.println("Viewing my patients...");
                    viewMyPatients(input);
                    break;

                case 2:
                    System.out.println("Searching patient by ID...");
                    searchMyPatientByID(input);
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
    public static void addPatientRecord(Scanner input) {
        try{
            if (patientCount == patientID.length){
                expandArray();
            }

            int newPatientId;
            while (true) {
                try{
                    System.out.print("Enter Patient ID: ");
                    newPatientId = input.nextInt();
                    input.nextLine();
            
                    if (isPatientIdExists(newPatientId)) {
                        System.out.println("Patient ID already exists. Please enter a unique Patient ID.");
                    } else {
                        break; // Unique ID entered, exit the loop
                    }
                }catch (InputMismatchException e) {
                    System.out.println("Invalid input! Patient ID must be a number.");
                    input.nextLine(); 
                }
            }
    
            patientID[patientCount] = newPatientId;

            System.out.print("Enter Patient Name: ");
            String name = input.nextLine();
            while (name.trim().isEmpty() || !isValidName(name)) {
                System.out.print("Invalid input! Name must only contain letters and spaces. Enter Patient Name: ");
                name = input.nextLine();
            }
            patientName[patientCount] = name;

            while (true){
                try{
                    System.out.print("Enter Patient Age: ");
                    age[patientCount] = input.nextInt();
                    input.nextLine();
                    if (age[patientCount] >= 0 && age[patientCount] <=120){
                        break;
                    }
                    else{
                        System.out.println("Age must be between 0-120. Invalid Input! Try Again!");
                    }
                }catch (InputMismatchException e) {
                    System.out.println("Invalid input! Age must be a number.");
                    input.nextLine();
                }
            }

            System.out.print("Enter Gender (Male/Female/Other): ");
            String genderInput = input.nextLine();
            while (!(genderInput.equalsIgnoreCase("Male") || genderInput.equalsIgnoreCase("Female") || genderInput.equalsIgnoreCase("Other"))) {
                System.out.print("Invalid input! Please enter Male, Female, or Other: ");
                genderInput = input.nextLine();
            }
            gender[patientCount] = genderInput;

            System.out.print("Enter Disease: ");
            String diseaseInput = input.nextLine();
            while (diseaseInput.trim().isEmpty() || isNumeric(diseaseInput)) {
                System.out.print("Invalid input! Disease cannot be empty or numeric. \nEnter Disease: ");
                diseaseInput = input.nextLine();
            }
            disease[patientCount] = diseaseInput;
  
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
        } catch (Exception e){
            System.out.println("An error occurred while adding the patient.");
            e.printStackTrace();
        }
            return;
    }

    public static boolean isValidName(String name) {
    for (int i = 0; i < name.length(); i++) {
        char c = name.charAt(i);
        // Check if character is not a letter and not a space
        if (!Character.isLetter(c) && c != ' ') {
            return false;
        }
    }
        return true;
    }

    public static boolean isNumeric(String str) {
    for (int i = 0; i < str.length(); i++) {
        if (!Character.isDigit(str.charAt(i))) {
            return false;  // Found a non-digit character
        }
    }
        return true;  // All characters are digits
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

    // ==================== SAVE DATA TO FILE ====================
    public static void saveDataToFile() {
        try{
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
         } catch (IOException e) {
        System.out.println("Error saving data: " + e.getMessage());
        }
    }
    // ==================== READ DATA FROM FILE ====================
    public static void readDataFromFile() {
    File file = new File("patient_data.txt");
    if (!file.exists()) {
        System.out.println("File not Found.");
        return;
    }

    try(Scanner reader = new Scanner(file)){
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
    System.out.println("Patient data loaded successfully.");
    }catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }catch (NumberFormatException e) {
        System.out.println("Data format error in file: " + e.getMessage());
    }
}

    // ==================== VIEW ALL PATIENTS ======================
    public static void viewAllPatients(Scanner input) {
        if (patientCount == 0){
            System.out.println("No patientss available!");
            return;
        }
        for (int i = 0; i < patientCount; i++) {
            displayPatient(i);
        }
    }

    // ================== VIEW MY PATIENTS ==================
    public static void viewMyPatients(Scanner input) {

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
    public static void updatePatientRecord(Scanner input) {
        boolean continueUpdating = true;
        
        while (continueUpdating) {
            int ID = 0;
            boolean validID = false;
            
            // Loop until a valid Patient ID is entered
            while (!validID) {
                System.out.print("Enter Patient ID: ");
                
                try {
                    ID = input.nextInt();
                    input.nextLine();
                    validID = true;  // If we reach here, the input was a valid integer
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Patient ID must be a number.");
                    input.nextLine(); // Clear the invalid input
                    // Continue looping to ask for input again
                }
            }

            boolean patientFound = false;
            
            for (int i = 0; i < patientCount; i++){
                if (patientID[i] == ID){
                    patientFound = true;
                    System.out.println("Current Details: ");
                    displayPatient(i); 

                    System.out.println("What do you want to update? (1-5): ");
                    System.out.println("1. Name");
                    System.out.println("2. Age");
                    System.out.println("3. Disease");
                    System.out.println("4. Doctor");
                    System.out.println("5. Prescription");

                    int choice = 0;
                    while (true){
                        try{
                            System.out.print("Enter your choice: ");
                            choice = input.nextInt();
                            input.nextLine();
                            if (choice >= 1 && choice <= 5){
                                break;
                            }
                            else{
                                System.out.println("Invalid choice! Please choose between 1-5.");
                            }
                            } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a number.");
                            input.nextLine();
                        }
                    }
                    switch (choice){
                        case 1:
                            String name = "";
                            while (true) {
                                System.out.print("Enter new name: ");
                                name = input.nextLine();
                                if (!isValidName(name) || name.trim().isEmpty()) {
                                    System.out.print("Invalid input! Name must only contain letters and spaces. \nEnter new name: ");
                                } else {
                                    break;  // if name is valid
                                }
                            }
                            patientName[i] = name;
                            break;
                        case 2:
                            while (true){
                                try{
                                    System.out.print("Enter Patient Age: ");
                                    age[i] = input.nextInt();  
                                    input.nextLine();
                                    if (age[i] >= 0 && age[i] <= 120){
                                        break;
                                    }
                                    else{
                                        System.out.println("Age must be between 0-120. Invalid Input! Try Again!");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! Age must be a number between 0 and 120.");
                                    input.nextLine();
                                }
                            }
                            break;
                        case 3:
                            String diseaseInput = "";
                            while (true) {
                                System.out.print("Enter new disease: ");
                                diseaseInput = input.nextLine();
                                if (diseaseInput.trim().isEmpty() || isNumeric(diseaseInput)) {
                                    System.out.print("Invalid input! Disease cannot be empty or numeric. \nEnter new disease: ");
                                } else {
                                    break;  // Valid disease
                                }
                            }
                            disease[i] = diseaseInput;

                            // Assign doctor according to the disease       
                            String assignedDoctor = assignDoctorBasedOnDisease(disease[i]);
                            doctor[i] = assignedDoctor;
                            // Display assigned doctor for confirmation
                            System.out.println("Assigned Doctor: " + assignedDoctor);
                            break;
                        case 4:
                           int dChoice = 0;
                            while (true) {
                                System.out.println("Select New Doctor:");
                                for (int d = 0; d < doctorNames.length; d++) {
                                    System.out.println((d + 1) + ". " + doctorNames[d]);
                                }
                                try {
                                    dChoice = input.nextInt();
                                    input.nextLine();
                                    if (dChoice >= 1 && dChoice <= doctorNames.length) {
                                        doctor[i] = doctorNames[dChoice - 1];
                                        break;  // Valid doctor selected
                                    } else {
                                        System.out.println("Invalid choice! Please choose a valid doctor.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! Please enter a number.");
                                    input.nextLine();  // clear invalid input
                                }
                            }
                            break;

                        case 5:
                            System.out.print("Enter new prescription for " + patientName[i] + ": ");
                            String newPrescription = input.nextLine();

                            // Ensure that the prescription is not empty
                            while (newPrescription.trim().isEmpty()) {
                                System.out.print("Invalid input! Prescription cannot be empty. Enter new prescription: ");
                                newPrescription = input.nextLine();
                            }

                            // Update the prescription
                            prescription[i] = newPrescription;
                            saveDataToFile();
                            System.out.println("Prescription updated successfully!");
                            break;
                        default:
                            System.out.println("Invalid choice!");
                        break;
                    }

                    saveDataToFile();
                    System.out.println("Updated Successfully");
                    
                    // Ask if user wants to update another field for the same patient or a different patient
                    System.out.print("Do you want to update another record? (y/n): ");
                    String response = input.nextLine().trim().toLowerCase();
                    if (!response.equals("y") && !response.equals("yes")) {
                        continueUpdating = false;
                    }
                    break; // Exit the for loop after processing the patient
                }
            }
            
            if (!patientFound) {
                System.out.println("Patient Not Found.");
                // Ask if user wants to try again with a different patient ID
                System.out.print("Do you want to try with another Patient ID? (y/n): ");
                String response = input.nextLine().trim().toLowerCase();
                if (!response.equals("y") && !response.equals("yes")) {
                    continueUpdating = false;
                }
            }
        }
    }

    // ==================== SEARCH My PATIENT BY ID ====================
    public static void searchMyPatientByID(Scanner input) {
        int ID ; 
    while (true) {
        try {
            System.out.print("Enter Patient ID: ");
            ID = input.nextInt();
            input.nextLine(); 
            break; 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number for Patient ID.");
            input.nextLine(); 
        }
    }
    for (int i = 0; i < patientCount; i++) {
        if (patientID[i] == ID && doctor[i].equalsIgnoreCase(loggedDoctor)) {
            displayPatient(i);
            return;
        }
    }
    System.out.println("Access Denied!");
    System.out.println("This patient is not assigned to you.");
}
    // ==================== BILLING ====================
    public static void billing(Scanner input) {
    System.out.println("Billing....");

    int ID ;
    while (true) {
        try {
            System.out.print("Enter Patient ID: ");
            ID = input.nextInt();
            input.nextLine(); // clear buffer
            break; // valid input
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a numeric Patient ID.");
            input.nextLine(); // clear invalid input
        }
    }

    boolean patientFound = false;

    for (int i = 0; i < patientCount; i++) {
        if (patientID[i] == ID) {
            patientFound = true;
            double totalBill = 0;

            // Check if the patient has visited the doctor and calculate the fees accordingly
            if (doctorFee[i] > 0 && testFee[i] > 0) {
                totalBill = doctorFee[i] + testFee[i];  // Both doctor and test fees are added
            } else if (doctorFee[i] > 0 && testFee[i] == 0) {
                totalBill = doctorFee[i];  // Only doctor fee is added
            } else if (doctorFee[i] == 0 && testFee[i] == 0) {
                totalBill = 0;  // No doctor visit or test prescribed, so the total bill is zero
            }

            System.out.println("Test Fee = " + testFee[i]);
            System.out.println("Doctor Fee = " + doctorFee[i]);
            System.out.println("Total Bill = " + totalBill);

            saveDataToFile(); 
            return;
        }
    }
    if (!patientFound) {
        System.out.println("Patient Not Found!");
    }
}

    // ==================== SEARCH PATIENT BY ID ====================
    public static void searchPatientByID(Scanner input) {
        int ID;
          while (true) {
        try {
            System.out.print("Enter Patient ID: ");
            ID = input.nextInt();
            input.nextLine(); 
            break; 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number for Patient ID.");
            input.nextLine(); 
        }
    }
    for (int i = 0; i < patientCount; i++) {
        if (patientID[i] == ID) {
            displayPatient(i);
            return;
        }
    }
    System.out.println("Patient Not Found!");
}

    // ================== PRESCRIPTION ==================
    public static void addPrescription(Scanner input) {
         int ID;
          while (true) {
        try {
            System.out.print("Enter Patient ID: ");
            ID = input.nextInt();
            input.nextLine(); 
            break; 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number for Patient ID.");
            input.nextLine(); 
        }
    }
   
    for (int i = 0; i < patientCount; i++) {
            if (patientID[i] == ID) {
                if (!doctor[i].equalsIgnoreCase(loggedDoctor)) {
                System.out.println("Access denied");
                System.out.println("You cannot add prescription for another doctor's patient.");
                return;
            }
                System.out.print("Enter Prescription: ");
                prescription[i] = input.nextLine();

                saveDataToFile();
                System.out.println("Prescription added successfully!");
                return;
            }
        }
        System.out.println("Patient Not Found!");
    }

     // ================== TEST ==================
    public static void addTest(Scanner input) {
    int ID;

    while (true) {
        try {
            System.out.print("Enter Patient ID: ");
            ID = input.nextInt();
            input.nextLine(); 
            break;
        } catch (InputMismatchException e) {
            System.out.println("Invalid Patient ID! Enter numbers only.");
            input.nextLine(); 
        }
    }

    boolean patientFound = false;

    for (int i = 0; i < patientCount; i++) {
        if (patientID[i] == ID) {
            patientFound = true;

            if (!doctor[i].equalsIgnoreCase(loggedDoctor)) {
                System.out.println("Access denied");
                System.out.println("You cannot add test for another doctor's patient.");
                return;
            }

            System.out.print("Enter Test Name: ");
            test[i] = input.nextLine();

            saveDataToFile();
            System.out.println("Test added successfully!");
            return;
        }
    }
    if (!patientFound) {
        System.out.println("Patient Not Found!");
    }
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
    static void displayPatient(int i) {
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
