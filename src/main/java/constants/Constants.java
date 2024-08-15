package constants;

public class Constants {
    // Number of courses a student can take
    public static final int NUM_OF_COURSES = 3;

    // CourseDAO
    public static final String UPDATE_STUDENT_COURSE_SQL = 
    "UPDATE StudentCourse SET course_id = ? WHERE student_id = ? AND course_id = ?";
    public static final String UPDATE_STUDENT_COURSE_GRADE_SQL = 
    "UPDATE StudentCourseGrade SET course_id = ? WHERE student_id = ? AND course_id = ?";
    public static final String SELECT_ALL_COURSES_SQL = 
    "SELECT * FROM Course";


    // Database
    // DB file
    public static final String URL = "jdbc:sqlite:student.db";
    public static final int QUERY_TIMEOUT = 30;
    // Enable this as SQLite does not do this by default
    public static final String ENABLE_FOREIGN_KEYS = "PRAGMA foreign_keys = ON";

    // SQL Create table queries
    public static final String CREATE_STUDENT_TABLE = "CREATE TABLE IF NOT EXISTS Student (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "first_name TEXT NOT NULL, " +
            "last_name TEXT NOT NULL, " +
            "gender TEXT NOT NULL, " +
            "ethnicity TEXT NOT NULL" +
            ");";

    public static final String CREATE_COURSE_TABLE = "CREATE TABLE IF NOT EXISTS Course (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "course_name TEXT NOT NULL UNIQUE" +
            ");";

    public static final String CREATE_GRADE_TABLE = "CREATE TABLE IF NOT EXISTS Grade (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "grade TEXT NOT NULL UNIQUE" +
            ");";

    public static final String CREATE_STUDENT_GRADE_TABLE = "CREATE TABLE IF NOT EXISTS StudentCourseGrade (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "student_id INTEGER NOT NULL, " +
            "course_id INTEGER NOT NULL, " +
            "grade_id INTEGER NOT NULL," +
            "FOREIGN KEY(student_id) REFERENCES Student(id) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(course_id) REFERENCES Course(id) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(grade_id) REFERENCES Grade(id) ON DELETE CASCADE ON UPDATE CASCADE" +
            ");";

    public static final String CREATE_STUDENT_COURSE_TABLE = "CREATE TABLE IF NOT EXISTS StudentCourse (" +
            "student_id INTEGER NOT NULL, " +
            "course_id INTEGER NOT NULL, " +
            "PRIMARY KEY(student_id, course_id), " +
            "FOREIGN KEY(student_id) REFERENCES Student(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
            "FOREIGN KEY(course_id) REFERENCES Course(id) ON DELETE CASCADE ON UPDATE CASCADE" +
            ");";

    // SQL insert courses and grades
    public static final String INSERT_COURSE = "INSERT OR IGNORE INTO Course (course_name) VALUES (?)";
    public static final String INSERT_GRADE = "INSERT OR IGNORE INTO Grade (grade) VALUES (?)";


    // GradeDAO
    public static final String UPDATE_STUDENT_GRADE_SQL =
    "UPDATE StudentCourseGrade SET grade_id = ? WHERE student_id = ? AND course_id = ?";
    public static final String GET_STUDENT_GRADE_SQL =
    "SELECT sg.grade_id, g.grade " +
    "FROM StudentCourseGrade as sg " +
    "INNER JOIN Grade as g " +
    "ON sg.grade_id = g.id " +
    "WHERE sg.student_id = ? and course_id = ?";
    

    // StudentCreateDAO
    public static final String INSERT_STUDENT_DETAILS_SQL =
    "INSERT INTO Student (first_name, last_name, gender, ethnicity) VALUES (?, ?, ?, ?)";
    public static final String INSERT_INTO_STUDENT_COURSE_SQL =
    "INSERT INTO StudentCourse (student_id, course_id) VALUES (?, ?)";
    public static final String INSERT_INTO_STUDENT_COURSE_GRADE_SQL = 
    "INSERT INTO StudentCourseGrade (student_id, course_id, grade_id) VALUES (?, ?, ?);";
    
    
    // StudentReadDAO
    public static final String GET_LATEST_ADDED_STUDENT_SQL = "SELECT id FROM Student ORDER BY id DESC LIMIT 1;";
    public static final String STUDENT_DETAILS_SQL = "SELECT * FROM Student WHERE id = ?;";
    public static final String STUDENT_COURSES_AND_GRADES_SQL = 
    "SELECT c.course_name, g.grade, sg.course_id, sg.grade_id " +
    "FROM StudentCourseGrade as sg " +
    "INNER JOIN Course as c ON sg.course_id = c.id " +
    "INNER JOIN Grade as g ON sg.grade_id = g.id " +
    "WHERE sg.student_id = ?";
    public static final String ALL_STUDENTS_SQL = "SELECT * FROM Student";
    public static final String STUDENT_COURSE_IDS_SQL = "SELECT course_id FROM StudentCourse WHERE student_id = ?;";


    // StudentRemoveDAO
    public static final String REMOVE_STUDENT_SQL = "DELETE FROM Student WHERE id = ?";


    // StudentUpdateDAO
    public static final String UPDATE_STUDENT_DETAILS = 
    "UPDATE Student SET first_name = ?, last_name = ?, gender = ?, ethnicity = ? WHERE id = ?";
}
