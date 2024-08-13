package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeDAO {

    private static final String UPDATE_STUDENT_GRADE_SQL =
    "UPDATE StudentCourseGrade SET grade_id = ? WHERE student_id = ? AND course_id = ?";
    private static final String GET_STUDENT_GRADE_SQL =
    "SELECT sg.grade_id, g.grade " +
            "FROM StudentCourseGrade as sg " +
            "INNER JOIN Grade as g " +
            "ON sg.grade_id = g.id " +
            "WHERE sg.student_id = ? and course_id = ?";

    public void updateStudentGrade(int studentId, int courseId, int gradeId) {
        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(UPDATE_STUDENT_GRADE_SQL)) {

            pstmt.setInt(1, gradeId);
            pstmt.setInt(2, studentId);
            pstmt.setInt(3, courseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getStudentGrade(int studentId, int courseId) {
        String mark = "";
            
        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(GET_STUDENT_GRADE_SQL)) {
                    pstmt.setInt(1, studentId);
                    pstmt.setInt(2, courseId);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        mark = rs.getString("grade");
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(mark);
        return mark;
    }
}