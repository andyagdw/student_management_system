package view;

import java.util.Scanner;

public class GetStudentId {
    private Scanner scanner;

    public GetStudentId(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getStudentIdFromUser() {
        System.out.println("\nEnter student id: ");
        int userOption = scanner.nextInt();
        return userOption;
    }
}
