package com.university.system;

import java.util.List;
import java.util.Scanner;

class Administrator extends User {
    public Administrator(String id, String password) {
        super(id, password, "Administrator");
    }

    public void manageCourseCatalog(List<Course> availableCourses) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add Course\n2. Drop Course\nEnter choice:");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.println("Enter course details (code, title, credits, semester):");
            String code = sc.nextLine();
            String title = sc.nextLine();
            int credits = sc.nextInt();
            int semester = sc.nextInt();
            availableCourses.add(new Course(code, title, null, null, credits, semester, null, null, null));
            System.out.println("Course added.");
        } else if (choice == 2) {
            System.out.println("Enter course code to delete:");
            String courseCode = sc.nextLine();
            availableCourses.removeIf(course -> course.getCode().equals(courseCode));
            System.out.println("Course deleted.");
        }
    }

    public void manageStudentRecords(List<Student> students) {
        int i = 1;
        for (Student student : students) {
            System.out.println(i+". Student: " + student.getEmail() + ", Semester: " + student.getSemester() + " Credits: " + student.getTotalCredits());
            i++;
        }

    }

    public void assignProfessorToCourse(List<Course> availableCourses, List<Professor> professors) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter course code to assign professor:");
        String courseCode = sc.nextLine();
        System.out.println("Enter professor email:");
        String email = sc.nextLine();

        for (Course course : availableCourses) {
            if (course.getCode().equals(courseCode)) {
                for (Professor professor : professors) {
                    if (professor.getEmail().equals(email)) {
                        course.setProfessor(professor);
                        //professor.().add(course);
                        System.out.println("Assigned " + professor.getEmail() + " as prof to "+course.getTitle());
                        return;
                    }
                }
            }
        }
        System.out.println("Course or professor not found.");
    }

    public void assignTAtoCourse(List<Course> availableCourses, List<TA> tas){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter course code to assign TA:");
        String courseCode = sc.nextLine();
        System.out.println("Enter TA email:");
        String email = sc.nextLine();
        for (Course course : availableCourses) {
            if (course.getCode().equals(courseCode)) {
                for (TA ta : tas) {
                    if (ta.getEmail().equals(email)) {
                        course.setTA(ta);

                        System.out.println("Assigned " + ta.getEmail() + " as TA to " + course.getTitle());
                        return;
                    }
                }
            }
        }
        System.out.println("Course or TA not found.");
    }

    public void handleComplaints(List<Complaint> complaints) {
        for (Complaint complaint : complaints) {
            System.out.println("Complaint: " + complaint.getDescription() + ", Status: " + complaint.getStatus());
        }
    } // add change complaint status code

    @Override
    void showMenu() {
        System.out.println("1. Manage Course Catalog\n2. Manage Student Records\n3. Assign Professors to Courses\n" +
                "4. Assign TA to Course\n5. Handle Complaints\n0. Logout");

    }
}
