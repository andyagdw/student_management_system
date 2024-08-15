package controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import communication.Communications;
import dao.CourseDAO;
import dao.StudentReadDAO;
import model.Student;
import view.AllCourses;
import view.AllStudents;
import view.GetStudentId;
import view.OneStudent;

public class ViewStudent {

    private Scanner scanner;

    public ViewStudent(Scanner scanner) {
        this.scanner = scanner;
    }

    public void view(int userOption) {

        switch (userOption) {
            case 2:  // View all students
                List<Student> students = StudentReadDAO.getAllStudents();
                // Check if there are any students in the database
                if (students.size() < 1) {
                    Communications.noStudents();
                    break;
                } else {
                    AllStudents.displayAllStudents(students);
                    break;
                }
            case 3:  // View a student
                GetStudentId getStudentId = new GetStudentId(scanner);
                OneStudent oneStudent = new OneStudent();
                int studentId;
    
                try {  // Handle non integer values
                    studentId = getStudentId.getStudentIdFromUser();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    Communications.incorrectInput();
                    scanner.nextLine();
                    break;
                }

                // Get student information from the database
                Student student = StudentReadDAO.getStudentInfo(studentId);

                // Check if student exists in the database
                if (student == null) { 
                    Communications.studentDoesNotExist(studentId);
                    break;
                }

                oneStudent.displayStudentInfo(student);
                break;
            case 4: // View student by first and last name
            case 5:
                ViewStudentName viewStudentName = new ViewStudentName(scanner);
                viewStudentName.viewStudentName(userOption);
                break;
            case 6: // View all courses
                List<String> courses = CourseDAO.getAllCourses();
                AllCourses.displayAllCourses(courses);
                break;
        }
    }
}
