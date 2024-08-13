package model;

public class StudentCourseGrade {
    private String courseName;
    private String grade; 
    private int courseId; 
    private int gradeId;

    public StudentCourseGrade(String courseName, String grade, int courseId, int gradeId) {
        this.courseName = courseName;
        this.grade = grade;
        this.courseId = courseId;
        this.gradeId = gradeId;
    }

    // Getters and Setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String toString() {
        return "Course: " + this.courseName + " | " + this.courseId + 
                "\nGrade: " + this.grade + " | " + this.gradeId + "\n";
    }
}
