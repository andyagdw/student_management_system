package view;

import java.util.List;

import model.Student;

public class AllStudents {
    
    public static void displayAllStudents(List<Student> students) {
        for (Student i : students) {
            System.out.println(i.toString());
        }
    }
}
