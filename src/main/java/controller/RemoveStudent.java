package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import communication.Communications;
import dao.StudentReadDAO;
import dao.StudentRemoveDAO;
import model.Student;
import view.GetStudentId;

public class RemoveStudent {

    private Scanner scanner;

    public RemoveStudent(Scanner scanner) {
        this.scanner = scanner;
    }

    public void remove() {
        int studentId;
        GetStudentId getStudentId = new GetStudentId(scanner);

        try {  // Handle non-integer values
            studentId = getStudentId.getStudentIdFromUser();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            Communications.incorrectInput();
            scanner.nextLine();
            return;
        }

        // Get student information from database
        Student student = StudentReadDAO.getStudentInfo(studentId);

        if (student == null) {
            Communications.studentDoesNotExist(studentId);
            return;
        }

        System.out.print("\nAre you sure you would like to remove this student? ");
        System.out.print("This action cannot be undone.");
        System.out.println("\n\n'Yes' or 'No':");

        String confirmation = scanner.nextLine();

        if (confirmation.equals("Yes")) {
            // Remove student
            StudentRemoveDAO.deleteStudent(studentId);
            Communications.studentRemoved();
        } else {
            System.out.println("\nAction aborted.\n");
            return;
        }
    }
}
