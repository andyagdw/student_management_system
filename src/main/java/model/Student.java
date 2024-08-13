package model;

import java.util.ArrayList;

public class Student {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Ethnicity ethnicity;
    private ArrayList<StudentCourseGrade> courseGrades;
    private ArrayList<StudentCourse> courseIds;

    // All possible genders
    public enum Gender {
        MALE,
        FEMALE
    }

    // All possible ethnicities
    public enum Ethnicity {
        ASIAN,
        BLACK,
        HISPANIC,
        WHITE,
        OTHER
    }

    // Define constructor
    public Student(
            String firstName,
            String lastName,
            Gender gender,
            Ethnicity ethnicity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.ethnicity = ethnicity;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Ethnicity ethnicity) {
        this.ethnicity = ethnicity;
    }

    public ArrayList<StudentCourseGrade> getStudentCourseGrades() {
        return courseGrades;
    }

    public void setCourseGrades(ArrayList<StudentCourseGrade> courseGrades) {
        this.courseGrades = courseGrades;
    }

    public ArrayList<StudentCourse> getStudentCourseIds() {
        return courseIds;
    }

    public void setCourseIds(ArrayList<StudentCourse> courseIds) {
        this.courseIds = courseIds;
    }

    public String toString() {
        return "\nFirstname: " + firstName +
        "\nLastname: " + lastName + 
        "\nGender: " + gender +
        "\nEthnicity: " + ethnicity + "\n";
    }
}
