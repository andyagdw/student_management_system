package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import communication.Communications;
import dao.CourseDAO;
import dao.GradeDAO;
import dao.StudentReadDAO;
import dao.StudentUpdateDAO;
import model.Course;
import model.Student;
import model.StudentCourse;
import util.Utility;
import view.GetStudentId;
import view.UpdateStudentCourse;
import view.UpdateStudentDetails;
import view.UpdateStudentGrade;

public class UpdateStudent {

    int studentId;
    int courseId;
    int gradeId;
    int oldCourseId;
    int newCourseId;
    Integer[] ids;
    Student student;
    ArrayList<StudentCourse> studentCourseIds;
    private Scanner scanner;

    public UpdateStudent(Scanner scanner) {
        this.scanner = scanner;
    }

    public void update(int userOption) {

        switch (userOption) {
            case 7: // Upgrade student details
                GetStudentId getStudentId = new GetStudentId(scanner);
                UpdateStudentDetails upgradeStudentDetails = new UpdateStudentDetails(scanner);
                StudentUpdateDAO studentUpdateDAO = new StudentUpdateDAO();

                try {  // Handle non-integer values
                    studentId = getStudentId.getStudentIdFromUser();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    Communications.incorrectInput();
                    scanner.nextLine();
                    break;
                }
                // Get student information from database
                student = StudentReadDAO.getStudentInfo(studentId);
                // Check if student exists
                if (student == null) {
                    Communications.studentDoesNotExist(studentId);
                    break;
                }

                // Pass student information to the view
                student = upgradeStudentDetails.updateStudentDetails(student);
                // Update student details
                studentUpdateDAO.updateStudentDetails(student, studentId);
                Communications.studentDetailsUpdated();
                break;

            case 8: // Upgrade student grade
                GetStudentId getStudentId2 = new GetStudentId(scanner);
                UpdateStudentGrade updateStudentGrade = new UpdateStudentGrade(scanner);
                GradeDAO gradeDAO = new GradeDAO();
                try {  // Handle non-integer values
                    studentId = getStudentId2.getStudentIdFromUser();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    Communications.incorrectInput();
                    scanner.nextLine();
                    break;
                }
                // Get student information from database
                student = StudentReadDAO.getStudentInfo(studentId);
                if (student == null) { // Check if student exists
                    Communications.studentDoesNotExist(studentId);
                    break;
                }

                while (true) {
                    try {  // Handle non-integer values
                        courseId = updateStudentGrade.getCourseIdFromUser();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        Communications.incorrectInput();
                        scanner.nextLine();
                        continue;
                    }
    
                    studentCourseIds = student.getStudentCourseIds();
                    boolean courseIdInStudentCoursesGrades = Utility.checkValInArr(studentCourseIds, courseId);
    
                    // Check if course ID entered by user exists within student current courses
                    if (!courseIdInStudentCoursesGrades) {
                        String currentText = "current";
                        Communications.enterValidCourseId(currentText);
                        continue;
                    }
    
                    // Get student grade
                    String studentGrade = gradeDAO.getStudentGrade(studentId, courseId);
    
                    try {
                        // Get new student mark
                        gradeId = updateStudentGrade.getNewStudentGrade(studentId, courseId, student, studentGrade);
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        Communications.incorrectInput();
                        scanner.nextLine();
                        continue;
                    }
                }
                // Upgrade student grade
                gradeDAO.updateStudentGrade(studentId, courseId, gradeId);
                Communications.gradeUpdated();
                break;

            case 9: // Upgrade student course
                GetStudentId getStudentId3 = new GetStudentId(scanner);
                UpdateStudentCourse updateStudentCourse = new UpdateStudentCourse(scanner);
                try {
                    // Handle non-integer values
                    studentId = getStudentId3.getStudentIdFromUser();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    Communications.incorrectInput();
                    scanner.nextLine();
                    break;
                }

                // Get student information from database
                student = StudentReadDAO.getStudentInfo(studentId);
                if (student == null) {
                    Communications.studentDoesNotExist(studentId);
                    break;
                }
                
                while (true) {
                    try {  // Handle non-integer values
                        ids = updateStudentCourse.getOldAndNewCourseId(); // Get old and new course ID
                        scanner.nextLine();
                        
                        oldCourseId = ids[0];
                        newCourseId = ids[1];
        
                        studentCourseIds = student.getStudentCourseIds();
        
                        boolean oldCourseIdInStudentCoursesGrades = Utility.checkValInArr(studentCourseIds, oldCourseId);
                        boolean newCourseIdInStudentCoursesGrades = Utility.checkValInArr(studentCourseIds, newCourseId);
                        int studentCoursesLength = Course.getAllCourses().size();
        
                        // Check if old course ID is valid
                        if (!oldCourseIdInStudentCoursesGrades) {
                            String oldText = "old";
                            Communications.enterValidCourseId(oldText);
                            continue;
                        }
                        // Check if new course ID is valid
                        if (newCourseId > studentCoursesLength || newCourseId < 1 || newCourseIdInStudentCoursesGrades) {
                            String newText = "new";
                            Communications.enterValidCourseId(newText);
                            Communications.sameCourse();
                            continue;
                        }
                        break;
                    } catch (InputMismatchException e) {
                        Communications.incorrectInput();
                        scanner.nextLine();
                        continue;
                    }
                }
                int oldCourseIdInt = oldCourseId;
                int newCourseIdInt = newCourseId;
                // Update student course
                CourseDAO.updateStudentCourse(studentId, oldCourseIdInt, newCourseIdInt);
                Communications.courseUpdated();
                break;
        }
    }
}
