package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import constants.Constants;
import model.Course;
import model.Grade;
import model.Course.CourseName;
import model.Grade.Mark;

public class Database {
    public static Connection getConnection() {
        try {
            // Create a database connection
            Connection connection = DriverManager.getConnection(Constants.URL);
            try (Statement statement = connection.createStatement()) {
                // Maximum time in seconds that the db will wait for a query before throwing
                // exception
                statement.setQueryTimeout(Constants.QUERY_TIMEOUT);
                statement.executeUpdate(Constants.ENABLE_FOREIGN_KEYS);
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_COURSE)) {
            for (CourseName courseName : Course.getAllCourses()) {
                preparedStatement.setString(1, courseName.toString());
                // Use `addBatch` as similar SQL statements are being executed multiple times
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    static void insertGrades(Connection connection) throws SQLException {  // Insert grades into db
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_GRADE)) {
            for (Mark mark : Grade.getAllGrades()) {
                preparedStatement.setString(1, mark.toString());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    public static void createTables() {
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constants.CREATE_STUDENT_TABLE);

            statement.executeUpdate(Constants.CREATE_COURSE_TABLE);
            insertCourses(connection);
            statement.executeUpdate(Constants.CREATE_GRADE_TABLE);
            insertGrades(connection);
            statement.executeUpdate(Constants.CREATE_STUDENT_GRADE_TABLE);
            statement.executeUpdate(Constants.CREATE_STUDENT_COURSE_TABLE);

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
