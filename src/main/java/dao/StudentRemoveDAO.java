package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import constants.Constants;

public class StudentRemoveDAO {

    public static void deleteStudent(int studentId) {

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(Constants.REMOVE_STUDENT_SQL)) {

            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}