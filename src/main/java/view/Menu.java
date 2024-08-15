package view;

import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public int displayMainMenu() {
        System.out.println("What would you like to do:");
        System.out.println("\n1) Create new student");
        System.out.println("2) View all students");
        System.out.println("3) View a student");
        System.out.println("4) View student(s) by first name");
        System.out.println("5) View student(s) by last name");
        System.out.println("6) View all courses");
        System.out.println("7) Upgrade student details");
        System.out.println("8) Upgrade student grade");
        System.out.println("9) Upgrade student course");
        System.out.println("10) Remove a student");
        System.out.println("11) Exit\n");

        int option = scanner.nextInt();
        return option;
    }
}
