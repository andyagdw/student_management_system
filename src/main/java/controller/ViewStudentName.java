package controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import communication.Communications;
import dao.StudentReadDAO;
import model.Student;
import view.AllStudents;
import view.MenuStudentName;

public class ViewStudentName {
    String pattern;
    int userOption;
    private Scanner scanner;

    public ViewStudentName(Scanner scanner) {
        this.scanner = scanner;
    }

    public void viewStudentName(int selectedOption) {
        try {
            MenuStudentName menuStudentName = new MenuStudentName(scanner);
            userOption = menuStudentName.menuStudentName();
            scanner.nextLine();
            Communications.searchPattern();
            pattern = scanner.nextLine();
        } catch (InputMismatchException e) {
            Communications.incorrectInput();
            scanner.nextLine();
            return;
        }

        switch (selectedOption) {
            case 4: // views student(s) by first name
                if (userOption == 1) {
                    List<Student> students = StudentReadDAO.getStudentsByNameStart("first_name", pattern);
                    if (students.size() < 1) {
                        Communications.noStudentsWithMatchingCriteria(pattern);
                    } else {
                        AllStudents.displayAllStudents(students);
                    }
                } else if (userOption == 2) {
                    List<Student> students = StudentReadDAO.getStudentsByNameMiddle("first_name", pattern);
                    if (students.size() < 1) {
                        Communications.noStudentsWithMatchingCriteria(pattern);
                    } else {
                        AllStudents.displayAllStudents(students);
                    }
                } else {
                    List<Student> students = StudentReadDAO.getStudentsByNameEnd("first_name", pattern);
                    if (students.size() < 1) {
                        Communications.noStudentsWithMatchingCriteria(pattern);
                    } else {
                        AllStudents.displayAllStudents(students);
                    }
                }
                break;
            case 5:
                if (userOption == 1) {
                    List<Student> students = StudentReadDAO.getStudentsByNameStart("last_name", pattern);
                    if (students.size() < 1) {
                        Communications.noStudentsWithMatchingCriteria(pattern);
                    } else {
                        AllStudents.displayAllStudents(students);
                    }
                } else if (userOption == 2) {
                    List<Student> students = StudentReadDAO.getStudentsByNameMiddle("last_name", pattern);
                    if (students.size() < 1) {
                        Communications.noStudentsWithMatchingCriteria(pattern);
                    } else {
                        AllStudents.displayAllStudents(students);
                    }
                } else {
                    List<Student> students = StudentReadDAO.getStudentsByNameEnd("last_name", pattern);
                    if (students.size() < 1) {
                        Communications.noStudentsWithMatchingCriteria(pattern);
                    } else {
                        AllStudents.displayAllStudents(students);
                    }
                }
                break;
        }
    }
}
