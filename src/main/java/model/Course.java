package model;

import java.util.ArrayList;

public class Course {
    private CourseName courseName;

    // All Course Names
    public enum CourseName {
        ACCOUNTING,
        ART,
        BIOLOGY,
        BUSINESS_STUDIES,
        CHEMISTRY,
        COMPUTER_SCIENCE,
        CRIMINOLOGY,
        DESIGN_AND_TECHNOLOGY,
        DRAMA_AND_THEATRE,
        ECONOMICS,
        ENGINEERING,
        ENGLISH,
        ENGLISH_LANGUAGE,
        ENGLISH_LITERATURE,
        EXTENDED_PROJECT_QUALIFICATION,
        FASHION_AND_TEXTILES,
        FOOD_TECHNOLOGY,
        FILM_STUDIES,
        FRENCH,
        FURTHER_MATHS,
        GEOGRAPHY,
        GERMAN,
        GOVERNMENT_AND_POLITICS,
        GRAPHIC_DESIGN,
        HISTORY,
        LAW,
        MATHS,
        MEDIA_STUDIES,
        MUSIC,
        PHILOSOPHY,
        PHYSICAL_EDUCATION,
        PHYSICS,
        PSYCHOLOGY,
        RELIGIOUS_STUDIES,
        SCIENCE,
        SOCIOLOGY,
        SPANISH,
        STATISTICS,
        THEOLOGY,
        WORLD_DEVELOPMENT
    }

    // Getters and setters
    public CourseName getCourseName() {
        return courseName;
    }

    public void setCourseName(CourseName courseName) {
        this.courseName = courseName;
    }

    public static ArrayList<CourseName> getAllCourses() {
        ArrayList<CourseName> courseNames = new ArrayList<>();

        for (CourseName courseName : CourseName.values()) {
            courseNames.add(courseName);
        }
        return courseNames;
    }
}
