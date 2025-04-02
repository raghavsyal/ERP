package com.university.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Student> students = new ArrayList<>();
    static List<Professor> professors = new ArrayList<>();
    static List<TA> tas = new ArrayList<>();
    static List<Course> courseCatalog = new ArrayList<>();
    static List<Complaint> complaints = new ArrayList<>();
    static List<Feedback<?>> feedbacks = new ArrayList<>();
    static Administrator admin = new Administrator("admin@iiitd.ac.in", "admin123");

    public static void main(String[] args)  {
        try {
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("1. Login as Student\n2. Login as Professor\n3. Login as Administrator\n4. Login as TA\n0. Exit");

                int choice = sc.nextInt();

                if (choice == 0) {
                    System.out.println("Exiting the system...");
                    break;
                }

                if (choice == 1) {
                    Student abc = loginAsStudent();
                    if (abc != null) {
                        handleStudentMenu(abc);
                    } else {
                        System.out.println("exiting...");
                    }
                } else if (choice == 2) {
                    Professor efg = loginAsProfessor();

                    if (efg != null) {
                        handleProfessorMenu(efg);
                    } else {
                        System.out.println("exiting...");
                    }
                } else if (choice == 3) {
                    Administrator hij = loginAsAdmin();   // Admin hij = new Admin(); hij.loginAsadmin() technically right but practically some issue
                    if (hij != null) {
                        handleAdminMenu();
                    } else {
                        System.out.println("exiting...");
                    }
                } else if (choice == 4) {
                    TA klm = loginAsTA();
                    if (klm != null) {
                        handleTAMenu(klm);
                    } else {
                        System.out.println("exiting...");
                    }

                } else {
                    System.out.println("Invalid choice.");
                }
            }
        }
        catch (myException.InvalidLoginException | myException.CourseFullException | myException.DropDeadlinePassedException e) {
            System.out.println(e.getMessage());
        }

    }

    static Administrator loginAsAdmin() throws myException.InvalidLoginException {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter email: ");
        String email = input.nextLine();

        if (email == null || email.isEmpty()) {
            System.out.println("Invalid email, exiting... ");
            return null;
        }

        if (email.equals(admin.getEmail())) {
            System.out.println("Enter password: ");
            String password = input.nextLine();

            if (admin.checkPassword(password)) {
                return admin;
            } else {
                throw new myException.InvalidLoginException("Invalid Login Creds");
            }

        }
        System.out.println("Admin with email: '" + email + "' not found");
        return null;
    }

    static Student loginAsStudent() throws myException.InvalidLoginException {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter email: ");
        String email = input.nextLine();

        System.out.println(email);


        if (email == null || email.isEmpty()) {
            System.out.println("Invalid email, exiting... ");

            return null;
        }
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

            }
        }


        System.out.println("Set Password");  // signing up new student if student doesn't exist
        String password = input.nextLine();

        Student s1 = new Student(email, password);
        students.add(s1);
        return s1;


        //System.out.println("check2");
        //return null;
    }


    static Professor loginAsProfessor() throws myException.InvalidLoginException {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter email: ");
        String email = input.nextLine();


        if (email == null || email.isEmpty()) {
            System.out.println("Invalid email, exiting... ");
            return null;
        }
        for (Professor professor : professors) {
            if (professor.getEmail().equals(email)) {

                System.out.println("Enter password: ");
                String password = input.nextLine();


                if (professor.checkPassword(password)) {
                    return professor;
                } else {
                    throw new myException.InvalidLoginException("Invalid Login Creds");
                }
            }
        }


        System.out.println("Set Password");
        String password = input.nextLine();

        Professor p1 = new Professor(email, password);
        professors.add(p1);
        return p1;


        //return null;
    }

    static TA loginAsTA() throws myException.InvalidLoginException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter email: ");
        String email = input.nextLine();

        if (email == null || email.isEmpty()) {

            throw new myException.InvalidLoginException("Invalid Login Creds");
        }
        for (TA ta : tas) {
            if (ta.getEmail().equals(email)) {

                System.out.println("Enter password: ");
                String password = input.nextLine();


                if (ta.checkPassword(password)) {
                    return ta;
                } else {

                    throw new myException.InvalidLoginException("Invalid Login Creds");
                }
            }
        }


        System.out.println("Set Password");
        String password = input.nextLine();

        TA ta1 = new TA(email, password);
        tas.add(ta1);
        students.add(ta1);  // as TA is also a student, so adding to student list
        return ta1;
    }


    static void handleStudentMenu(Student student) throws myException.CourseFullException, myException.DropDeadlinePassedException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            student.showMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                student.viewAvailableCourses(courseCatalog);
            }
            else if (choice == 2) {
                student.registerForCourse(courseCatalog);
            }else if (choice == 3) {
                student.viewSchedule();
            }else if (choice == 4) {
                student.trackAcademicProgress();
            }else if (choice == 5) {
                student.dropCourse();
            }else if (choice == 6) {
                student.submitComplaint(complaints);
            }else if (choice == 7) {
                student.viewComplaintStatus(complaints);
            } else if (choice==8) {
                student.submitFeedback(courseCatalog, feedbacks);

            } else if (choice == 0) {
                return;
            }else {
                System.out.println("Invalid choice.");
            }
        }
    }


    static void handleProfessorMenu(Professor professor) {
        Scanner sc = new Scanner(System.in);
        while (true) {

            professor.showMenu();

            int choice = sc.nextInt();

            if (choice==1) {
                professor.manageCourse(courseCatalog);
            }else if (choice==2) {
                professor.viewEnrolledStudents(courseCatalog);
            }
            else if(choice==3){
                professor.assignCredits(courseCatalog);
            } else if (choice==4) {
                professor.viewFeedback(feedbacks);

            } else if (choice==0) {
                return;
            }
            else{
                System.out.println("Invalid choice.");
            }
        }
    }

    static void handleTAMenu(TA ta) throws myException.CourseFullException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            ta.showMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                ta.viewAvailableCourses(courseCatalog);
            }
            else if (choice==2) {
                ta.assignCredits(courseCatalog);
            }else if (choice==3) {
                ta.viewEnrolledStudents(courseCatalog);
            }
            else if (choice == 4) {
                ta.registerForCourse(courseCatalog);
            }else if (choice == 5) {
                ta.viewSchedule();
            }else if (choice == 6) {
                ta.trackAcademicProgress();
            }else if (choice == 7) {
                ta.dropCourse();
            }else if (choice == 8) {
                ta.submitComplaint(complaints);
            }else if (choice == 9) {
                ta.viewComplaintStatus(complaints);
            }else if (choice == 0) {
                return;
            }else {
                System.out.println("Invalid choice.");
            }
        }
    }


    static void handleAdminMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            admin.showMenu();
            int choice = sc.nextInt();

            if (choice==1) {
                admin.manageCourseCatalog(courseCatalog);
            }else if (choice==2) {
                admin.manageStudentRecords(students);
            }else if (choice==3) {
                admin.assignProfessorToCourse(courseCatalog, professors);
            } else if (choice==4) {
                admin.assignTAtoCourse(courseCatalog,tas);
            } else if (choice==5) {
                admin.handleComplaints(complaints);
            }
            else if (choice==0) {
                return;
            }else {
                System.out.println("Invalid choice.");
            }
        }
    }
}