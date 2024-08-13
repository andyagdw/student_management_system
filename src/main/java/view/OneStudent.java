package view;

import java.util.List;
import java.util.Scanner;

import communication.Communications;
import model.Student;
import model.StudentCourseGrade;

public class OneStudent {

    private Scanner scanner;

    public OneStudent(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getStudentIdFromUser() {
        Communications.enterStudentId();
        int studentId = scanner.nextInt();
        return studentId;
    }

    public void displayStudentInfo(Student student) {
        System.out.println(student.toString());
        List<StudentCourseGrade> courseGrades = student.getStudentCourseGrades();
        for (int i = 0; i < courseGrades.size(); i++) {
            System.out.println(courseGrades.get(i));
        }
    }
}