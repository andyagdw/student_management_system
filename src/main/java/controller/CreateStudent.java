package controller;

import java.util.ArrayList;
import java.util.Scanner;

import communication.Communications;
import dao.StudentCreateDAO;
import dao.StudentReadDAO;
import model.Student;
import view.AddStudent;

public class CreateStudent {

    private Scanner scanner;

    public CreateStudent(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public void createStudent() {
        AddStudent addStudent = new AddStudent(scanner);
        // Get student details
        Student newStudent = addStudent.createNewStudent();
        // Get student courses
        ArrayList<Integer> studentCourses = addStudent.createStudentCourses();
        // Get student grades
        ArrayList<Integer> studentGrades = addStudent.createStudentGrades();
        // Add them to the database
        StudentCreateDAO.addStudent(newStudent);
        int latestStudentId = StudentReadDAO.getLatestAddedStudent();
        StudentCreateDAO.addStudentCourses(latestStudentId, studentCourses);
        StudentCreateDAO.addStudentGrades(latestStudentId, studentGrades, studentCourses);
        Communications.studentCreated();
    }
}
