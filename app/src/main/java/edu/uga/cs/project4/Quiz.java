package edu.uga.cs.project4;

/**
 * This class represents a single quiz, including all values from columns in the DB.
 */
public class Quiz {


    String date;

    public Quiz(String date, String q1, String q2, String q3, String q4, String q5, String q6, String questionAnswered, String score) {
        this.date = date;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.questionAnswered = questionAnswered;
        this.score = score;
    }
    public Quiz(){};

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public String getQ4() {
        return q4;
    }

    public void setQ4(String q4) {
        this.q4 = q4;
    }

    public String getQ5() {
        return q5;
    }

    public void setQ5(String q5) {
        this.q5 = q5;
    }

    public String getQ6() {
        return q6;
    }

    public void setQ6(String q6) {
        this.q6 = q6;
    }

    public String getQuestionAnswered() {
        return questionAnswered;
    }

    public void setQuestionAnswered(String questionAnswered) {
        this.questionAnswered = questionAnswered;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    String q1;
    String q2;
    String q3;
    String q4;
    String q5;
    String q6;
    String questionAnswered;
    String score;


}
