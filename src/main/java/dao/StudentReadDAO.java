package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Student;
import model.StudentCourse;
import model.StudentCourseGrade;

public class StudentReadDAO {

    private static final String GET_LATEST_ADDED_STUDENT_SQL = "SELECT id FROM Student ORDER BY id DESC LIMIT 1;";
    private static final String STUDENT_DETAILS_SQL = "SELECT * FROM Student WHERE id = ?;";
    private static final String STUDENT_COURSES_AND_GRADES_SQL = "SELECT c.course_name, g.grade, sg.course_id, sg.grade_id "
            +
            "FROM StudentCourseGrade as sg " +
            "INNER JOIN Course as c ON sg.course_id = c.id " +
            "INNER JOIN Grade as g ON sg.grade_id = g.id " +
            "WHERE sg.student_id = ?";
    private static final String ALL_STUDENTS_SQL = "SELECT * FROM Student";
    private static final String STUDENT_COURSE_IDS_SQL = "SELECT course_id FROM StudentCourse WHERE student_id = ?;";

    public static int getLatestAddedStudent() {
        int studentId = -1;

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        GET_LATEST_ADDED_STUDENT_SQL);
                ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                studentId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentId;
    }

    public static Student getStudentInfo(int student_id) {
        Student student = null;

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmtStudentDetails = conn.prepareStatement(STUDENT_DETAILS_SQL);
                PreparedStatement pstmtCoursesAndGrades = conn.prepareStatement(STUDENT_COURSES_AND_GRADES_SQL);
                PreparedStatement psmtStudentCourseIds = conn.prepareStatement(STUDENT_COURSE_IDS_SQL)) {

            // First query
            pstmtStudentDetails.setInt(1, student_id);
            ResultSet rsStudentDetails = pstmtStudentDetails.executeQuery();
            if (rsStudentDetails.next()) {
                student = new Student(
                        rsStudentDetails.getString("first_name"),
                        rsStudentDetails.getString("last_name"),
                        // Convert fields into back into enums
                        Student.Gender.valueOf(rsStudentDetails.getString("gender")),
                        Student.Ethnicity.valueOf(rsStudentDetails.getString("ethnicity")));
            }

            // Second query
            pstmtCoursesAndGrades.setInt(1, student_id);
            ResultSet rsCoursesAndGrades = pstmtCoursesAndGrades.executeQuery();
            ArrayList<StudentCourseGrade> courseGrades = new ArrayList<>();

            while (rsCoursesAndGrades.next()) {
                courseGrades.add(new StudentCourseGrade(
                        rsCoursesAndGrades.getString("course_name"),
                        rsCoursesAndGrades.getString("grade"),
                        rsCoursesAndGrades.getInt("course_id"),
                        rsCoursesAndGrades.getInt("grade_id")));
            }

            // Third query
            psmtStudentCourseIds.setInt(1, student_id);
            ResultSet rsStudentCourseIds = psmtStudentCourseIds.executeQuery();
            ArrayList<StudentCourse> studentCourseIds = new ArrayList<>();

            while (rsStudentCourseIds.next()) {
                studentCourseIds.add(new StudentCourse(
                        rsStudentCourseIds.getInt("course_id")));
            }

            if (student != null) {
                student.setCourseGrades(courseGrades);
                student.setCourseIds(studentCourseIds);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(ALL_STUDENTS_SQL);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        Student.Gender.valueOf(rs.getString("gender")),
                        Student.Ethnicity.valueOf(rs.getString("ethnicity")));
                // Add student to the list
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static List<Student> getStudentsByNameStart(String columnName, String pattern) {
        String sql = "SELECT * FROM Student WHERE " + columnName + " LIKE ?";
        List<Student> students = new ArrayList<>();

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, pattern + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        Student.Gender.valueOf(rs.getString("gender")),
                        Student.Ethnicity.valueOf(rs.getString("ethnicity")));
                // Add student to the list
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static List<Student> getStudentsByNameMiddle(String columnName, String pattern) {
        String sql = "SELECT * FROM Student WHERE " + columnName + " LIKE ?";

        List<Student> students = new ArrayList<>();

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, "%" + pattern + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        Student.Gender.valueOf(rs.getString("gender")),
                        Student.Ethnicity.valueOf(rs.getString("ethnicity")));
                // Add student to the list
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static List<Student> getStudentsByNameEnd(String columnName, String pattern) {
        String sql = "SELECT * FROM Student WHERE " + columnName + " LIKE ?";
        List<Student> students = new ArrayList<>();
        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, "%" + pattern);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        Student.Gender.valueOf(rs.getString("gender")),
                        Student.Ethnicity.valueOf(rs.getString("ethnicity")));
                // Add student to the list
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
