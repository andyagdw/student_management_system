package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRemoveDAO {

    private static final String SQL = "DELETE FROM Student WHERE id = ?";

    public static void deleteStudent(int studentId) {

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}