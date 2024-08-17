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
    
    public void create() {
        AddStudent addStudent = new AddStudent(scanner);
        // Get student details from user
        Student newStudent = addStudent.createNewStudent();
        // Get student courses from user
        ArrayList<Integer> studentCourses = addStudent.createStudentCourses();
        // Get student grades from user
        ArrayList<Integer> studentGrades = addStudent.createStudentGrades(studentCourses);
        // Add all to the database and create new student
        StudentCreateDAO.addStudent(newStudent);
        int latestStudentId = StudentReadDAO.getLatestAddedStudent();
        StudentCreateDAO.addStudentCourses(latestStudentId, studentCourses);
        StudentCreateDAO.addStudentGrades(latestStudentId, studentGrades, studentCourses);
        Communications.studentCreated();
    }
}
