package com.university.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student extends User {
    private int semester = 1;
    private int grades;
    private List<Course> completedCourses = new ArrayList<>();
    private List<Course> enrolledCourses  = new ArrayList<>();

    public Student(String id, String password) {
        super(id, password, "Student");
    }

    public void viewAvailableCourses(List<Course> availableCourses) {
        System.out.println("Available Courses:");
        for (Course course : availableCourses) {
            System.out.println("Code: "+course.getCode()+" Title: "+course.getTitle()+" Professor: "+course.getProfessor().getEmail()
                    +" Prerequisites: "+course.getPrerequisites());
        }
    }

    public void registerForCourse(List<Course> availableCourses) throws myException.CourseFullException {
        Scanner sc = new Scanner(System.in);
        viewAvailableCourses(availableCourses);
        System.out.println("Enter course code to register:");
        String courseCode = sc.nextLine();

        for (Course course : availableCourses) {

            if (course.getCode().equals(courseCode)) {
                if (completedCourses!=null && !completedCourses.containsAll(course.getPrerequisites()))
                { // for checking second condn, first check that list null naa ho warna error throw krega
                    System.out.println("cannot register, prerequisites not met.");
                } else if (totalCredits() + course.getCredits() > 20) {
                    System.out.println("cannot register, exceeds credit limit.");
                } else if (course.getSemester() != semester) {
                    System.out.println("cannot register, incorrect Semester.");
                } else {
                    enrolledCourses.add(course);
                    course.setEnrolledStudents(this);
                    System.out.println("Registered for " + course.getTitle());
                }
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public void viewSchedule() {
        System.out.println("Schedule for Semester " + semester + ":");
        for (Course course : enrolledCourses) {
            System.out.println(course.getTitle() + ": " + course.getSchedule() + ": Professor: " + course.getProfessor()
                    + " TA: "+ course.getTa());
        }
    }

    public void trackAcademicProgress() {
        int totalCredits = 0;

        for (Course course : completedCourses) {
            totalCredits += course.getCredits();
        }
        if (completedCourses.size()==0){
            System.out.println("No courses completed yet.");
        }
        else {

            System.out.println("Total credits: " + totalCredits +" for "+ completedCourses.size()+" courses.");
        }
    }

    public void dropCourse() throws myException.DropDeadlinePassedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter course code to drop:");
        String courseCode = sc.nextLine();
        for (Course course : completedCourses) {
            //System.out.println("check3");
            if (course.getCode().equals(courseCode)) {
                throw new myException.DropDeadlinePassedException("Courses completed cant be dropped.");
            }
        }
        enrolledCourses.removeIf(course -> course.getCode().equals(courseCode));
        System.out.println("Dropped course " + courseCode);
    }

    public void submitComplaint(List<Complaint> complaints) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter complaint description: ");
        String description = sc.nextLine();

        complaints.add(new Complaint(description));
        System.out.println("Complaint submitted.");

    }

    public void viewComplaintStatus(List<Complaint> complaints){
        for (Complaint complaint : complaints ){
            complaint.printComplaint();
        }
    }

    public void submitFeedback(List<Course> enrolledCourses, List<Feedback<?>> feedbacks){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter course code: ");
        for (Course course: enrolledCourses){
            System.out.println(course.getCode() + ": " + course.getTitle());
        }

        String courseCode = sc.nextLine();

        for (Course course: enrolledCourses){
            if (course.getCode().equals(courseCode)){
                System.out.println("Choose feedback type\n1. integer\n2. text\n0. exit.");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice==1){
                    System.out.println("Enter rating (1-5):");
                    int rating = sc.nextInt();
                    if (rating >= 1 && rating <= 5) {
                        Feedback<Integer> numericFeedback = new Feedback<>(rating);
                        feedbacks.add(numericFeedback);
                        System.out.println("Rating Submitted    ");
                    } else {
                        System.out.println("Invalid rating. ");
                    }
                } else if (choice == 2) {
                    System.out.println("Enter your feedback: ");
                    String textFeedback = sc.nextLine();
                    Feedback<String> stringFeedback = new Feedback<>(textFeedback);
                    feedbacks.add(stringFeedback);
                    System.out.println("Feedback submitted ");
                } else {
                    System.out.println("Invalid choice.");
                }
                return;
            }
        }
        System.out.println("Course not found.");

    }


    private int totalCredits() {
        int total = 0;
        for (Course course : enrolledCourses) {
            total+=course.getCredits();
        }
        return total;
    }

    public int getTotalCredits(){
        return totalCredits();

    }


    public int getSemester() {
        return semester;
    }

    public int getGrades() {
        return grades;
    }

    public void setGrades(int grades) {
        this.grades = grades;
    }

    public void setCompletedCourses(Course course) {
        this.completedCourses.add(course);
    }

    @Override
    void showMenu() {
        System.out.println("1. View Available Courses\n2. Register for Courses\n3. View Schedule\n4. Track Academic Progress\n5. Drop Courses\n" +
                "6. Submit Complaints\n7. View Complaint status\n8. Submit Feedback\n0. Logout");

    }
}