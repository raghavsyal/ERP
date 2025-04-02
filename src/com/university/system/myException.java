package com.university.system;

public class myException{
    public static class CourseFullException extends Exception {
        public CourseFullException (String message) {
            super(message);
        }
    }

    public static class InvalidLoginException extends Exception {
        public InvalidLoginException (String message) {
            super(message);
        }

    }

    public static class DropDeadlinePassedException extends RuntimeException {
        public DropDeadlinePassedException (String message) {
            super(message);
        }
    }
}
