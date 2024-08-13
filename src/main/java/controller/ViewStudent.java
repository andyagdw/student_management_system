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
                if (students.size() < 1) {  // Check if there are any students in the database
                    Communications.noStudents();
                    break;
                } else {
                    AllStudents.displayAllStudents(students);
                    break;
                }
            case 3:  // View a student
                OneStudent oneStudent = new OneStudent(scanner);
                int studentId = -1;
    
                try {  // Handle non integer values
                    studentId = oneStudent.getStudentIdFromUser();
                } catch (InputMismatchException e) {
                    Communications.incorrectInput();
                    return;
                }

                Student student = StudentReadDAO.getStudentInfo(studentId);

                if (student == null) {  // Check if student exists in the database
                    Communications.studentDoesNotExist(studentId);
                    return;
                }

                oneStudent.displayStudentInfo(student);
                break;
            case 4: // View student by first name and last name
            case 5:
                ViewStudentName viewStudentName = new ViewStudentName(scanner);
                viewStudentName.viewStudentName(userOption);
                break;
            case 6: // View all courses
                List<String> courses = CourseDAO.getAllCourses();
                AllCourses allCourses = new AllCourses();
                allCourses.displayAllCourses(courses);
                break;
        }
    }
}
