package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import constants.Constants;
import model.Student;

public class StudentUpdateDAO {
    
    public void updateStudentDetails(Student student, int id) {

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(Constants.UPDATE_STUDENT_DETAILS)) {

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
