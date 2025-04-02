package com.university.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Professor extends User {

    private List<Course> prerequisites = new ArrayList<>();

    public Professor(String id, String password) {
        super(id, password, "Professor");
    }

    public Professor(){
        super();
    }



    public void manageCourse(List<Course> availableCourses) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter course code to manage:");
        String courseCode = sc.nextLine();


        Course courseToManage = null;
        for (Course course : availableCourses) {
            if (course.getCode().equals(courseCode)) {
                courseToManage = course;
                break;

            }
        }
        if (courseToManage == null) {
            System.out.println("Course not found.");
            return;
        }

        while (true){


            System.out.println("1. Add Prerequisites\n2. Class Timings\nEnter choice:");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.println("Enter the prerequisite (codes separated by ','), if null then just press enter");
                String prerequisitesAdd = sc.nextLine();
                // prereq can be none
                String[] prerequisiteArray = prerequisitesAdd.split(",");
                for (String code : prerequisiteArray){
                    code=code.trim();
                    boolean a = false;
                    Course c2 = null;

                    for (Course course : availableCourses ){
                        if (course.getCode().equals(code)){
                            a = true;
                            c2=course;
                            break;
                        }
                    }
                    if (a){
                        prerequisites.add(c2);
                    }
                }

                courseToManage.setPrerequisites(prerequisites);
                break;
            } else if (choice==2) {
                System.out.println("Enter Class Timings: ");
                String timings = sc.nextLine();
                courseToManage.setSchedule(timings);
                break;

            }else{
                System.out.println("Invalid Choice, exiting...");
                return;
            }

        }
    }

    public void viewEnrolledStudents(List<Course> availableCourses) {
        for (Course course : availableCourses) {
            if (course.getProfessor().getEmail().equals(this.getEmail())) {
                System.out.println("Enrolled Students for " + course.getTitle() + ":");
                for (Student student : course.getEnrolledStudents()) {
                    System.out.println(student.getEmail());
                }
                return;
            }
        }
        System.out.println("No course assigned to you.");
    }

    public void viewFeedback(List<Feedback<?>> feedbacks) {
        for (Feedback feedback : feedbacks) {
            feedback.displayFeedback();
        }
    }


    public void assignCredits(List<Course> availableCourses) {
        viewEnrolledStudents(availableCourses);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student Id for whom you want to enter credits: ");
        String userid = sc.nextLine();
        System.out.println("Enter course code: ");
        String courseCode = sc.nextLine();

        for (Course course : availableCourses){
            for (Student student : course.getEnrolledStudents()){
//                if (!userid.equals(student.getEmail())){
//                    System.out.println("invalid student id");
//                }
//                if (!courseCode.equals(course.getCode())){
//                    System.out.println("invalid student code");
//                }
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
        System.out.println("1. View and Manage Course Details\n2. View Enrolled Students\n3. Assign Credits\n4. View Feedbacks\n" +
                "0. Logout");
    }
}

