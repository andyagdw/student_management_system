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
    String columnName;
    int userOption;
    private Scanner scanner;

    public ViewStudentName(Scanner scanner) {
        this.scanner = scanner;
    }

    public void viewStudentName(int selectedOption) {
        while (true) {
            try { // Handle non-integer values
                MenuStudentName menuStudentName = new MenuStudentName(scanner);
                userOption = menuStudentName.menuStudentName();
                scanner.nextLine();
                System.out.println("\nEnter letter(s) that you would like to search for: ");
                pattern = scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                Communications.incorrectInput();
                scanner.nextLine();
                continue;
            }
        }

        switch (selectedOption) {
            case 4: // View student(s) by first name
                columnName = "first_name";
                if (userOption == 1) {
                    List<Student> students = StudentReadDAO.getStudentsByNameStart(columnName, pattern);
                    if (students.size() < 1) {
                        Communications.noStudentsWithMatchingCriteria(pattern);
                    } else {
                        AllStudents.displayAllStudents(students);
                    }
                } else if (userOption == 2) {
                    List<Student> students = StudentReadDAO.getStudentsByNameMiddle(columnName, pattern);
                    if (students.size() < 1) {
                        Communications.noStudentsWithMatchingCriteria(pattern);
                    } else {
                        AllStudents.displayAllStudents(students);
                    }
                } else if (userOption == 3) {
                    List<Student> students = StudentReadDAO.getStudentsByNameEnd(columnName, pattern);
                    if (students.size() < 1) {
                        Communications.noStudentsWithMatchingCriteria(pattern);
                    } else {
                        AllStudents.displayAllStudents(students);
                    }
                }
                break;

            case 5:  // View student(s) by last name
                columnName = "last_name";
                if (userOption == 1) {
                    List<Student> students = StudentReadDAO.getStudentsByNameStart(columnName, pattern);
                    if (students.size() < 1) {
                        Communications.noStudentsWithMatchingCriteria(pattern);
                    } else {
                        AllStudents.displayAllStudents(students);
                    }
                } else if (userOption == 2) {
                    List<Student> students = StudentReadDAO.getStudentsByNameMiddle(columnName, pattern);
                    if (students.size() < 1) {
                        Communications.noStudentsWithMatchingCriteria(pattern);
                    } else {
                        AllStudents.displayAllStudents(students);
                    }
                } else if (userOption == 3) {
                    List<Student> students = StudentReadDAO.getStudentsByNameEnd(columnName, pattern);
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
