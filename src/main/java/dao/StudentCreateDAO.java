package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import constants.Constants;
import model.Student;

public class StudentCreateDAO {
    
    // Create a new student
    public static void addStudent(Student student) {
        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(Constants.INSERT_STUDENT_DETAILS_SQL)) {

            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            // Turn to string as Gender is stored as enum. Do the same to Ethnicity
            pstmt.setString(3, student.getGender().toString());
            pstmt.setString(4, student.getEthnicity().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addStudentCourses(int latestStudentId, ArrayList<Integer> studentCourses) {
        for (int i = 0; i < Constants.NUM_OF_COURSES; i++) {
            try (Connection conn = Database.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(Constants.INSERT_INTO_STUDENT_COURSE_SQL)) {
                pstmt.setInt(1, latestStudentId);
                pstmt.setInt(2, studentCourses.get(i)); // Get Course ID
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addStudentGrades(int latestStudentId, ArrayList<Integer> studentGrades, ArrayList<Integer> studentCourses) {
        for (int i = 0; i < Constants.NUM_OF_COURSES; i++) {
            try (Connection conn = Database.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(Constants.INSERT_INTO_STUDENT_COURSE_GRADE_SQL)) {
                pstmt.setInt(1, latestStudentId);
                pstmt.setInt(2, studentCourses.get(i)); // Get Course ID
                pstmt.setInt(3, studentGrades.get(i)); // Get Grade
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
   }
}
