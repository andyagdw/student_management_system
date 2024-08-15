package view;

import java.util.List;

import model.Student;
import model.StudentCourseGrade;

public class OneStudent {

    public void displayStudentInfo(Student student) {
        System.out.println(student.toString());
        List<StudentCourseGrade> courseGrades = student.getStudentCourseGrades();
        for (int i = 0; i < courseGrades.size(); i++) {
            System.out.println(courseGrades.get(i));
        }
    }
}