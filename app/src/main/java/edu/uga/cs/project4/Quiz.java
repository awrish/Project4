package edu.uga.cs.project4;

/**
 * This class (a POJO) represents a single quiz, including all the column values in the table.
 * ID is -1 if object has not been persisted in database yet.
 */
public class Quiz {

    private long id;
    private int date; //or long??
    private long q1;
    private long q2;
    private long q3;
    private long q4;
    private long q5;
    private long q6;
    private int result;
    private int answered;


    /** Values set to -1 if not initialized yet */
    public Quiz() {
        this.id = -1;
        this.date = -1;
        this.q1 = -1;
        this.q2 = -1;
        this.q3 = -1;
        this.q4 = -1;
        this.q5 = -1;
        this.q6 = -1;
        this.result = -1;
        this.answered = -1;
    }

    public Quiz( int date, long q1, long q2, long q3, long q4, long q5, long q6, int result, int answered ) {
        this.id = -1; // will be set by setter method
        this.date = date;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.result = result;
        this.answered = answered;
    }

    public long getID() { return id; }

    public void setId(long id ) { this.id = id; } /** do i have to do this for q1 - q6 ??? */

    public int getDate() { return date; }

    public void setDate(int date) { this.date = date; }

    public long getQ1() { return q1; }

    public void setQ1(long q1) { this.q1 = q1; }

    public long getQ2() { return q2; }

    public void setQ2(long q2) { this.q2 = q2; }

    public long getQ3() { return q3; }

    public void setQ3(long q3) { this.q3 = q3; }

    public long getQ4() { return q4; }

    public void setQ4(long q4) { this.q4 = q4; }

    public long getQ5() { return q5; }

    public void setQ5(long q5) { this.q5 = q5; }

    public long getQ6() { return q6; }

    public void setQ6(long q6) { this.q6 = q6; }

    public int getResult() { return result; }

    public void setResult(int result) { this.result = result; }

    public int getAnswered() { return answered; }

    public void setAnswered(int answered) { this.answered = answered; }

    public String toString()
    {
        return id + ": " + date + " " + q1 + " " + q2 + " " + q3 + " " + q4 + "" + q5 + " " + q6 + " " + result + " " + answered;
    }
}
