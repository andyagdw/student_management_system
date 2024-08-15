package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import constants.Constants;

public class GradeDAO {

    public void updateStudentGrade(int studentId, int courseId, int gradeId) {
        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(Constants.UPDATE_STUDENT_GRADE_SQL)) {

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
                PreparedStatement pstmt = conn.prepareStatement(Constants.GET_STUDENT_GRADE_SQL)) {
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