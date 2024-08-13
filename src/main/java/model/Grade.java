package model;

import java.util.ArrayList;

public class Grade {
    private Mark grade;

    // All possible grades
    public enum Mark {
        A,
        B,
        C,
        D,
        E,
        F,
        U
    }

    // Getters and setters
    public Mark getGrade() {
        return grade;
    }

    public void setGrade(Mark grade) {
        this.grade = grade;
    }

    public ArrayList<Mark> getAllGrades() {
        ArrayList<Mark> allGrades = new ArrayList<>();

        for (Mark mark : Mark.values()) {
            allGrades.add(mark);
        }
        return allGrades;
    }
}
