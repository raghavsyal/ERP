package com.university.system;

import java.util.List;
import java.util.Scanner;

class TA extends Student {

    private Professor professor;

    public TA(String id, String password) {
        super(id, password);
        this.professor = new Professor();
    }

    public void viewEnrolledStudents(List<Course> availableCourses) {
        for (Course course : availableCourses) {
            if (course.getTa() != null && course.getTa().getEmail().equals(this.getEmail())) {
                System.out.println("Enrolled Students for " + course.getTitle() + ":");
                for (Student student : course.getEnrolledStudents()) {
                    System.out.println(student.getEmail());
                }
            }
        }
    }

    public void assignCredits(List<Course> availableCourses){
        viewEnrolledStudents(availableCourses);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student Id for whom you want to enter credits: ");
        String userid = sc.nextLine();
        System.out.println("Enter course code: ");
        String courseCode = sc.nextLine();

        for (Course course : availableCourses){
            for (Student student : course.getEnrolledStudents()){

                if (userid.equals(student.getEmail()) && courseCode.equals(course.getCode())){
                    student.setGrades(student.getGrades()+course.getCredits());
                    student.setCompletedCourses(course);
                    System.out.println("Assigned credits: "+course.getCredits());
                    return;
                }
                else{
                    System.out.println("invalid student code");
                }
            }
        }
    }



    @Override
    void showMenu() {
        System.out.println("1. View Available Courses\n2. Assign Credits\n3. View Enrolled Students\n4. Register for a Course\n" +
                "5. View Schedule\n6. Track Academic Progress\n7. Drop a Course\n8. Submit Complaint\n9. View Complaint Status\n" +
                "0. Logout");

    }
}
