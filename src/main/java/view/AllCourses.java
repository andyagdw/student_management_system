package view;

import java.util.List;

public class AllCourses {
    public void displayAllCourses(List<String> courses) {
        System.out.println(" ");
        for (int i = 0; i < courses.size(); i++) {
            int courseId = i + 1;
            System.out.println(courseId + ") " + courses.get(i));
        }
        System.out.println(" ");
    }
}
