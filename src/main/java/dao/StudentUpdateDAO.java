package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Student;

public class StudentUpdateDAO {
    
    // Update student details
    public void updateStudentDetails(Student student, int id) {
        String sql = "UPDATE Student SET first_name = ?, last_name = ?, gender = ?, ethnicity = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            // Turn to string as Gender is stored as enum. Do the same to Ethnicity
            pstmt.setString(3, student.getGender().toString());
            pstmt.setString(4, student.getEthnicity().toString());
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
