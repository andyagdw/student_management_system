package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import communication.Communications;
import constants.Constants;
import model.Course;
import model.Grade;
import model.Student;
import model.Course.CourseName;
import model.Grade.Mark;
import model.Student.Ethnicity;
import model.Student.Gender;

public class AddStudent {
    Gender studentGender;
    Ethnicity studentEthnicity;
    String firstName;
    String lastName;
    int gender;

    private Scanner scanner;

    public AddStudent(Scanner scanner) {
        this.scanner = scanner;
    }

    public String createStudentFirstName() {
        System.out.println("\nEnter first name: ");
        String studentFirstName = scanner.nextLine();
        return studentFirstName.trim();
    }

    public String createStudentLastName() {
        System.out.println("Enter last name: ");
        String studentLastName = scanner.nextLine();
        return studentLastName.trim();
    }

    public int createStudentGender() {
        System.out.println("\nEnter gender: ");
        System.out.println("1) " + Gender.MALE);
        System.out.println("2) " + Gender.FEMALE);
        int studentGender = scanner.nextInt();
        return studentGender;
    }

    public int createStudentEthnicity() {
        System.out.println("\nEnter ethnicity: ");
        System.out.println("1) " + Ethnicity.BLACK);
        System.out.println("2) " + Ethnicity.WHITE);
        System.out.println("3) " + Ethnicity.ASIAN);
        System.out.println("4) " + Ethnicity.HISPANIC);
        System.out.println("5) " + Ethnicity.OTHER);
        int studentEthnicity = scanner.nextInt();
        return studentEthnicity;
    }

    public Student createNewStudent() {

        String firstName;
        String lastName;
        int gender;
        int ethnicity;

        while (true) {

            firstName = createStudentFirstName();
            if (firstName == "") {
                Communications.enterAValue();
                continue;
            }

            lastName = createStudentLastName();
            if (lastName == "") {
                Communications.enterAValue();
                continue;
            }

            try {
                gender = createStudentGender();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                Communications.incorrectInput();
                scanner.nextLine();
                continue;
            }

            switch (gender) {
                case 1:
                    studentGender = Gender.MALE;
                    break;
                case 2:
                    studentGender = Gender.FEMALE;
                    break;
                default:
                    Communications.incorrectInput();
                    continue;
            }

            try {
                ethnicity = createStudentEthnicity();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                Communications.incorrectInput();
                scanner.nextLine();
                continue;
            }

            switch (ethnicity) {
                case 1:
                    studentEthnicity = Ethnicity.BLACK;
                    break;
                case 2:
                    studentEthnicity = Ethnicity.WHITE;
                    break;
                case 3:
                    studentEthnicity = Ethnicity.ASIAN;
                    break;
                case 4:
                    studentEthnicity = Ethnicity.HISPANIC;
                    break;
                case 5:
                    studentEthnicity = Ethnicity.OTHER;
                    break;
                default:
                    Communications.incorrectInput();
                    continue;
            }
            break;
        }
        Student newStudent = new Student(firstName, lastName, studentGender, studentEthnicity);
        return newStudent;
    }

    public ArrayList<Integer> createStudentCourses() {
        ArrayList<CourseName> courses = Course.getAllCourses();
        ArrayList<Integer> studentCourseIds = new ArrayList<>();
        int selectedCourse;

        for (int x = 0; x < Constants.NUM_OF_COURSES; x++) {
            int currentCourseChoiceNumber = x + 1;
            System.out.print("\n" + currentCourseChoiceNumber);
            System.out.print(". Enter student course id:\n\n");
            // Print out all available courses
            for (int i = 0; i < courses.size(); i++) {
                int courseId = i + 1;
                System.out.println(courseId + ") " + courses.get(i));
            }

            while (true) {
                try {
                    selectedCourse = scanner.nextInt();
                    scanner.nextLine();
                    if (
                        selectedCourse < 1 ||
                        selectedCourse > courses.size() ||
                        studentCourseIds.contains(selectedCourse)
                        ) {
                        System.out.print("\nPlease enter a valid course ID ");
                        System.out.print("between 1 and " + courses.size() + ".\n");
                        System.out.print("\nPlease also note that students cannot ");
                        System.out.print("be enrolled on the same course twice.\n");
                        continue;
                    } else {
                        studentCourseIds.add(selectedCourse);
                    }
                } catch (InputMismatchException e) {
                    Communications.incorrectInput();
                    scanner.nextLine();
                    continue;
                }
                break;
            }
        }
        return studentCourseIds;
    }

    public ArrayList<Integer> createStudentGrades(ArrayList<Integer> studentCourses) {
        int studentGrade;
        int userOption;
        ArrayList<Mark> grades = Grade.getAllGrades();
        ArrayList<Integer> studentGrades = new ArrayList<>();

        for (int x = 0; x < Constants.NUM_OF_COURSES; x++) {
            int currentCourseChoiceNumber = x + 1;
            System.out.print("\n" + currentCourseChoiceNumber);
            System.out.print(". Enter student grade for course id " + studentCourses.get(x) + ":\n\n");
            // Print out all grades
            for (int i = 0; i < grades.size(); i++) {
                int gradePosition = i + 1;
                System.out.println(gradePosition + ") " + grades.get(i));
            }
            while (true) {
                try {
                    userOption = scanner.nextInt();
                    scanner.nextLine();
                    switch (userOption) {
                        case 1:
                            studentGrade = 1;
                            break;
                        case 2:
                            studentGrade = 2;
                            break;
                        case 3:
                            studentGrade = 3;
                            break;
                        case 4:
                            studentGrade = 4;
                            break;
                        case 5:
                            studentGrade = 5;
                            break;
                        case 6:
                            studentGrade = 6;
                            break;
                        case 7:
                            studentGrade = 7;
                            break;
                        default:
                            Communications.incorrectInput();
                            continue;
                    }
                    studentGrades.add(studentGrade);
                } catch (InputMismatchException e) {
                    Communications.incorrectInput();
                    scanner.nextLine();
                    continue;
                }
                break;
            }
        }
        return studentGrades;
    }
}