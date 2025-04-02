## note
no changes made in complaints and user class, added feedback, myException, TA classes and new functions in main for loginAsTA and handleTAmenu
and few instances inside classes for TA and all.

## Generic Programming
it is used in creating feedback class, as in feedback we are using or taking in multiple data types for feedback (here, int and string)
so it allows to create multiple data types for feedback

    public class Feedback<T> {
    private T feedback;

    public Feedback(T feedback) {
        this.feedback = feedback;
    }

## Object classes and Inheritance

is used in TA class, extending TA from student is a hierarchical approach, which is as follows
    
    1. User
    2. Student
    3. TA
here, object class (topmost class is User which has login id and pass), TA inherits student, student inherits user
    
    abstract class User {
    private String email;
    private String password;
    private String role;

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    class Student extends User {
    private int semester = 1;
    private int grades;
    private List<Course> completedCourses = new ArrayList<>();
    private List<Course> enrolledCourses  = new ArrayList<>();

    public Student(String id, String password) {
        super(id, password, "Student");
    }

    class TA extends Student {

        private Professor professor;
    
        public TA(String id, String password) {
            super(id, password);
            this.professor = new Professor();
    }

## Exceptional Handling
It is used to throw error while logging in when invalid credentials, when course is full and a student tries to register for it (kept the limit of 5 per subject), and while dropping the course (assuming that if a student allotted credits for a subject then he cannot drop the course)
    
    public class myException {
        public static class CourseFullException extends Exception {
            public CourseFullException (String message) {
                super(message);
            }
        }
same for other two exceptions

    static Student loginAsStudent() throws myException.InvalidLoginException

    for (Student student : students) {
            //System.out.println("check3");
            if (student.getEmail().equals(email)) {

                System.out.println("Enter password: ");
                String password = input.nextLine();


                if (student.checkPassword(password)) {
                    return student;
                } else {
                    throw new myException.InvalidLoginException("Invalid Login Creds");
                }

similar things done for in loginAsProfessor, Admin, TA etc.