package com.university.system;

import java.util.ArrayList;
import java.util.List;

class Course {

    private String code;
    private String title;
    private int credits;
    private int semester;
    private String schedule;
    private Professor professor;
    private TA ta;
    private List<Student> enrolledStudents = new ArrayList<>();
    private List<Course> prerequisites;

    public Course(String code, String title, Professor professor, TA ta, int credits, int semester, List<Course> prerequisites, String schedule, List<Student> enrolledStudents) {
        this.code = code;
        this.title = title;
        this.professor=professor;
        this.ta=ta;
        this.credits = credits;
        this.semester = semester;
        this.prerequisites = prerequisites;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCode(){
        return code;
    }

    public String getTitle(){
        return title;
    }

    public Professor getProfessor(){
        return professor;
    }

    public int getCredits(){
        return credits;
    }

    public int getSemester() {
        return semester;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setEnrolledStudents(Student student) throws myException.CourseFullException {
        if (enrolledStudents.size()>=5){
            throw new myException.CourseFullException("COurse full, cant register more");
        }
        this.enrolledStudents.add(student);
    }

    public void removeEnrolledStudents(Student student)
    {

    }

    public TA getTa() {
        return ta;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setTA(TA ta){
        this.ta=ta;
    }
}

