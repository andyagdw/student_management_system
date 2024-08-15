package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import communication.Communications;
import dao.Database;
import view.Menu;

public class MainController {
    // Create one scanner, which will be passed down to classes that need it
    private Scanner scanner = new Scanner(System.in);

    public void startApplication() {
        while (true) {
            // Create database tables only if they do not exist
            Database.createTables();
            Menu menu = new Menu(scanner);
            int selected_option = -1;  // Initialise variable
            
            try {  // Handle non-integer values
                selected_option = menu.displayMainMenu(); // Display menu
                // Consume newline character left in the input buffer
                scanner.nextLine();
            } catch (InputMismatchException e) {
                Communications.incorrectInput();
                scanner.nextLine();
                continue;  // Restart loop
            }

            switch (selected_option) {
                case 1:
                    CreateStudent createStudent = new CreateStudent(scanner);
                    createStudent.create();
                    break;
                // View all student(s), view student(s) by first name\last name, view all courses
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    ViewStudent viewStudent = new ViewStudent(scanner);
                    viewStudent.view(selected_option);
                    break;
                // Upgrade student details / student grade / student course
                case 7:
                case 8:
                case 9:
                    UpdateStudent updateStudent = new UpdateStudent(scanner);
                    updateStudent.update(selected_option);
                    break;
                case 10: // Remove a student
                    RemoveStudent removeStudent = new RemoveStudent(scanner);
                    removeStudent.remove();
                    break;
                case 11: // Exit
                    Communications.exitProgram();
                    scanner.close();
                    return;
                default:  // For integers that are more than nine and less than one
                    Communications.incorrectInput();
            }
        }
    }
}
