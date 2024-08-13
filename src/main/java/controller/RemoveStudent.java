package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import communication.Communications;
import dao.StudentReadDAO;
import dao.StudentRemoveDAO;
import model.Student;
import view.DeleteStudent;

public class RemoveStudent {

    private Scanner scanner;

    public RemoveStudent(Scanner scanner) {
        this.scanner = scanner;
    }

    public void remove() {
        int studentId = -1;  // Initialise variable
        DeleteStudent deleteStudent = new DeleteStudent(scanner);

        try {  // Handle non-integer values
            studentId = deleteStudent.deleteStudent();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            Communications.incorrectInput();
            scanner.nextLine();
            return;
        }

        Student student = StudentReadDAO.getStudentInfo(studentId);

        if (student == null) {
            Communications.studentDoesNotExist(studentId);
            return;
        }

        StudentRemoveDAO.deleteStudent(studentId);
        Communications.studentRemoved();
    }
}
