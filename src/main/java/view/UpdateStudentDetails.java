package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import communication.Communications;
import model.Student;
import model.Student.Ethnicity;
import model.Student.Gender;

public class UpdateStudentDetails {
    
    private Scanner scanner;
    int gender;
    int ethnicity;

    public UpdateStudentDetails(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getStudentIdFromUser() {
        Communications.enterStudentId();
        int student_id = scanner.nextInt();
        return student_id;
    }

    public Student updateStudentDetails(Student student) {
        System.out.println("Enter new student firstname (leave empty to use old details): ");
        String studentFirstname = scanner.nextLine();
        if (studentFirstname != "") {
            student.setFirstName(studentFirstname);
        }

        System.out.println("\nEnter new student lastname (leave empty to use old details): ");
        String lastname = scanner.nextLine();
        if (lastname != "") {
            student.setLastName(lastname);
        }

        System.out.println("\nEnter new student gender: ");
        System.out.println("1 = " + Gender.MALE);
        System.out.println("2 = " + Gender.FEMALE);
        System.out.println("Enter any other number to use old details");
        System.out.println("Previous value: " + student.getGender());
        
        while (true) {
            try {
                gender = scanner.nextInt();
                scanner.nextLine();
             } catch (InputMismatchException e) {
                Communications.incorrectInput();
                scanner.nextLine();
                continue;
            }
            switch (gender) {
                case 1:
                    student.setGender(Gender.MALE);
                    break;
                case 2:
                    student.setGender(Gender.FEMALE);
                    break;
                default:
                    break;
            }
            break;
        }

        System.out.println("\nEnter new student ethnicity: ");
        System.out.println("1 = " + Ethnicity.BLACK);
        System.out.println("2 = " + Ethnicity.WHITE);
        System.out.println("3 = " + Ethnicity.ASIAN);
        System.out.println("4 = " + Ethnicity.WHITE);
        System.out.println("5 = " + Ethnicity.HISPANIC);
        System.out.println("6 = " + Ethnicity.OTHER);
        System.out.println("Enter any other number to use old details");
        System.out.println("Previous value: " + student.getEthnicity());
        
        while (true) {
            try {
                ethnicity = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                Communications.incorrectInput();
                scanner.nextLine();
                continue;
            }
            switch (ethnicity) {
                case 1:
                    student.setEthnicity(Ethnicity.BLACK);
                    break;
                case 2:
                    student.setEthnicity(Ethnicity.WHITE);
                    break;
                case 3:
                    student.setEthnicity(Ethnicity.ASIAN);
                    break;
                case 4:
                    student.setEthnicity(Ethnicity.HISPANIC);
                    break;
                case 5:
                    student.setEthnicity(Ethnicity.WHITE);
                    break;
                default:
                    break;
            }
            break;
        }
        return student;
    }
}
