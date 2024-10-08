package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constants.Constants;

public class CourseDAO {

    public static void updateStudentCourse(int studentId, int oldCourseId, int newCourseId) {
        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt1 = conn.prepareStatement(Constants.UPDATE_STUDENT_COURSE_SQL);
                PreparedStatement pstmt2 = conn.prepareStatement(Constants.UPDATE_STUDENT_COURSE_GRADE_SQL)) {
            pstmt1.setInt(1, newCourseId);
            pstmt1.setInt(2, studentId);
            pstmt1.setInt(3, oldCourseId);
            pstmt1.executeUpdate();

            pstmt2.setInt(1, newCourseId);
            pstmt2.setInt(2, studentId);
            pstmt2.setInt(3, oldCourseId);
            pstmt2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAllCourses() {
        List<String> courses = new ArrayList<>();
        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(Constants.SELECT_ALL_COURSES_SQL);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String course = rs.getString("course_name");
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}