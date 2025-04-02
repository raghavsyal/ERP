package com.university.system;

public class Feedback<T> {
    private T feedback;

    public Feedback(T feedback) {
        this.feedback = feedback;
    }

    public void displayFeedback() {
        if (feedback instanceof Integer) {
            int rating = (Integer) feedback;    // int rating=feedback; works fine as already we took Integer type object in input
            if (rating >= 1 && rating <= 5) {
                System.out.println("Rating: " + rating);
            }
        } else if (feedback instanceof String) {
            System.out.println("Text Feedback: " + feedback);
        } else {
            System.out.println("Unsupported feedback type.");
        }
    }
}

