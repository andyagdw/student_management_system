package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import communication.Communications;
import dao.Database;
import view.Menu;

public class MainController {
    // Create one scanner, which will be passed down to classes that need it
    // Dependency injection
    private Scanner scanner = new Scanner(System.in);
    public static int NUM_OF_COURSES = 3;


    public void startApplication() {
        while (true) {
            
            Database.createTables();  // Create database tables only if they do not exist
            Menu menu = new Menu(scanner);
            int selected_option = -1;  // Initialise variable
            
            try {  // Handle non-integer values
                selected_option = menu.displayMenu(); // Display menu
                scanner.nextLine();
            } catch (InputMismatchException e) {
                Communications.incorrectInput();
                scanner.nextLine(); // Clear invalid input
                continue;  // Restart loop
            }

            switch (selected_option) {
                case 1:
                    CreateStudent createStudent = new CreateStudent(scanner);
                    createStudent.createStudent();
                    break;
                // view all students, view student, view student(s) by first name, last name, view all courses
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    ViewStudent viewStudent = new ViewStudent(scanner);
                    viewStudent.view(selected_option);
                    break;
                // Upgrade student details, upgrade student grade, upgrade student course
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
                default:  // Integers that are more than nine and less than one
                    Communications.incorrectInput();
            }
        }
    }
}
