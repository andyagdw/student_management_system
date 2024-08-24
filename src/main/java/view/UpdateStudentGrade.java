package view;

import java.util.Scanner;

import communication.Communications;
import model.Student;
import model.Grade.Mark;

public class UpdateStudentGrade {
    private int gradeId;
    
    private Scanner scanner;

    public UpdateStudentGrade(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getCourseIdFromUser() {
        System.out.print("\nEnter course ID:\n");
        int courseId = scanner.nextInt();
        return courseId;
    }

    public int getNewStudentGrade(int studentId, int courseId, Student student, String studentGrade) {
        System.out.println("\nEnter new grade (Old grade: " + studentGrade + ")\n");
        System.out.println("1) " + Mark.A);
        System.out.println("2) " + Mark.B);
        System.out.println("3) " + Mark.C);
        System.out.println("4) " + Mark.D);
        System.out.println("5) " + Mark.E);
        System.out.println("6) " + Mark.F);
        System.out.println("7) " + Mark.U);
        
        while (true) {
            int newGrade = scanner.nextInt();
            switch (newGrade) {
                case 1:
                    gradeId = 1;
                    break;
                case 2:
                    gradeId = 2;
                    break;
                case 3:
                    gradeId = 3;
                    break;
                case 4:
                    gradeId = 4;
                    break;
                case 5:
                    gradeId = 5;
                    break;
                case 6:
                    gradeId = 6;
                    break;
                case 7:
                    gradeId = 7;
                    break;
                default:
                    Communications.incorrectInput();
                    continue;
            }
            break;
        }
        return gradeId;
    }
}