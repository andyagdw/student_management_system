package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.Course;
import model.Grade;
import model.Course.CourseName;
import model.Grade.Mark;

public class Database {
    // DB file
    private static final String URL = "jdbc:sqlite:student.db";
    private static final int QUERY_TIMEOUT = 30;
    // Enable this as SQLite does not do this by default
    private static final String ENABLE_FOREIGN_KEYS = "PRAGMA foreign_keys = ON";

    // SQL Create table queries
    private static final String CREATE_STUDENT_TABLE = "CREATE TABLE IF NOT EXISTS Student (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "first_name TEXT NOT NULL, " +
            "last_name TEXT NOT NULL, " +
            "gender TEXT NOT NULL, " +
            "ethnicity TEXT NOT NULL" +
            ");";

    private static final String CREATE_COURSE_TABLE = "CREATE TABLE IF NOT EXISTS Course (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "course_name TEXT NOT NULL UNIQUE" +
            ");";

    private static final String CREATE_GRADE_TABLE = "CREATE TABLE IF NOT EXISTS Grade (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "grade TEXT NOT NULL UNIQUE" +
            ");";

    private static final String CREATE_STUDENT_GRADE_TABLE = "CREATE TABLE IF NOT EXISTS StudentCourseGrade (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "student_id INTEGER NOT NULL, " +
            "course_id INTEGER NOT NULL, " +
            "grade_id INTEGER NOT NULL," +
            "FOREIGN KEY(student_id) REFERENCES Student(id) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(course_id) REFERENCES Course(id) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(grade_id) REFERENCES Grade(id) ON DELETE CASCADE ON UPDATE CASCADE" +
            ");";

    private static final String CREATE_STUDENT_COURSE_TABLE = "CREATE TABLE IF NOT EXISTS StudentCourse (" +
            "student_id INTEGER NOT NULL, " +
            "course_id INTEGER NOT NULL, " +
            "PRIMARY KEY(student_id, course_id), " +
            "FOREIGN KEY(student_id) REFERENCES Student(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
            "FOREIGN KEY(course_id) REFERENCES Course(id) ON DELETE CASCADE ON UPDATE CASCADE" +
            ");";
    
    // SQL insert courses and grades
    private static final String INSERT_COURSE = "INSERT OR IGNORE INTO Course (course_name) VALUES (?)";
    private static final String INSERT_GRADE = "INSERT OR IGNORE INTO Grade (grade) VALUES (?)";

    public static Connection getConnection() {
        try {
            // Create a database connection
            Connection connection = DriverManager.getConnection(URL);
            try (Statement statement = connection.createStatement()) {
                // Maximum time in seconds that the db will wait for a query before throwing
                // exception
                statement.setQueryTimeout(QUERY_TIMEOUT);
                statement.executeUpdate(ENABLE_FOREIGN_KEYS);
            }
            // Return the established connection
            return connection;
        } catch (SQLException e) {
            // If the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
            return null; // Return null if connection fails
        }
    }

    static void insertCourses(Connection connection) throws SQLException {  // Insert courses into db
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE)) {
            for (CourseName courseName : Course.getAllCourses()) {
                preparedStatement.setString(1, courseName.toString());
                // Use `addBatch` as similar SQL statements are being executed multiple times
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    static void insertGrades(Connection connection) throws SQLException {  // Insert grades into db
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GRADE)) {
            Grade grade = new Grade();
            for (Mark mark : grade.getAllGrades()) {
                preparedStatement.setString(1, mark.toString());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    public static void createTables() {
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_STUDENT_TABLE);

            statement.executeUpdate(CREATE_COURSE_TABLE);
            insertCourses(connection);
            statement.executeUpdate(CREATE_GRADE_TABLE);
            insertGrades(connection);
            statement.executeUpdate(CREATE_STUDENT_GRADE_TABLE);
            statement.executeUpdate(CREATE_STUDENT_COURSE_TABLE);

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
