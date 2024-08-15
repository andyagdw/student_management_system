package view;

import java.util.Scanner;

public class MenuStudentName {

    private Scanner scanner;

    public MenuStudentName(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public int menuStudentName() {
        int option;
        while (true) {
            System.out.println("\nHow would you like to retrieve student names: ");
            System.out.println("\n1) By names that start with the letter(s) I want");
            System.out.println("2) By names that contains the letter(s) I want");
            System.out.println("3) By names that end with the letter(s) I want:");

            option = scanner.nextInt();

            if (option <= 3 && option >= 1) {
                break;
            } else {
                System.out.println("\nPlease select either 1, 2, or 3.\n");
            }
        }
        return option;
    }
}
