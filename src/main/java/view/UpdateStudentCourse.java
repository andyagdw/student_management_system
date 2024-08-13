package view;

import java.util.Scanner;

import communication.Communications;

public class UpdateStudentCourse {
    
    private Scanner scanner;

    public UpdateStudentCourse(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getStudentId() {
        Communications.enterStudentId();
        int studentId = scanner.nextInt();
        return studentId;
    }

    public Integer[] getOldAndNewCourseId() {
        System.out.println("\nEnter old course ID: \n");
        int oldCourseId = scanner.nextInt();
        System.out.print("\nEnter new course ID: \n");
        int newCourseId = scanner.nextInt();
        Integer[] ids = { oldCourseId, newCourseId };
        return ids;
    }
    }
