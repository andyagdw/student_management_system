package util;

import java.util.ArrayList;

import model.StudentCourse;

public class Utility {

    public static boolean checkValInArr(ArrayList<StudentCourse> studentCourseArr, int valToCheck) {
        ArrayList<Integer> courseIdsIntegerArr = new ArrayList<>();
        // Loop through each value and store value as Integer in new array
        for (StudentCourse sc : studentCourseArr) {
            Integer ab = (Integer) sc.getCourseId();
            courseIdsIntegerArr.add(ab);
        }

        // Check if value is inside new array
        for (Integer i : courseIdsIntegerArr) {
            if (i == valToCheck) {
                return true;
            }
        }
        return false;
    }
}
