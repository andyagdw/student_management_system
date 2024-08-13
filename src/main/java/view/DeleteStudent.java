package view;

import java.util.Scanner;

import communication.Communications;

public class DeleteStudent {
    
    private Scanner scanner;

    public DeleteStudent(Scanner scanner) {
        this.scanner = scanner;
    }

    public int deleteStudent() {
        Communications.enterStudentId();
        int userOption = scanner.nextInt();
        return userOption;
    }
}
