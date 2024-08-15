package communication;

public class Communications {

    public static void incorrectInput() {
        System.out.println("\nInvalid option. Please try again.\n");
    }

    public static void studentDoesNotExist(int studentId) {
        System.out.println("\nStudent with an id of " + studentId + " does not exist.\n");
    }

    public static void enterAValue() {
        System.out.println("\nThere was an error. Please enter a value.\n");
    }

    public static void noStudents() {
        System.out.println("\nThere are no students in the database.\n");
    }

    public static void noStudentsWithMatchingCriteria(String pattern) {
        System.out.println("\nNo students match this pattern '" + pattern + "'.\n");
    }

    public static void exitProgram() {
        System.out.println("\nExiting the program.");
    }

    public static void studentRemoved() {
        System.out.println("\nStudent has been successfully removed.\n");
    }

    public static void studentCreated() {
        System.out.println("\nStudent was successfully added.\n");
    }

    public static void enterValidCourseId(String text) {
        System.out.println("\nPlease enter a valid " + text + " course id.\n");
    }

    public static void gradeUpdated() {
        System.out.println("\nGrade successfully updated.\n");
    }

    public static void courseUpdated() {
        System.out.println("\nCourse successfully updated.\n");
    }

    public static void studentDetailsUpdated() {
        System.out.println("\nStudent details successfully updated.\n");
    }
}
