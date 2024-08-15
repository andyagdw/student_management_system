package view;

import java.util.Scanner;

public class UpdateStudentCourse {
    
    private Scanner scanner;

    public UpdateStudentCourse(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer[] getOldAndNewCourseId() {
        System.out.println("\nEnter old course ID:");
        int oldCourseId = scanner.nextInt();
        System.out.print("\nEnter new course ID:\n");
        int newCourseId = scanner.nextInt();
        Integer[] ids = { oldCourseId, newCourseId };
        return ids;
    }
    }
