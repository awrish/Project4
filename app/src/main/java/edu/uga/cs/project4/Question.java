package edu.uga.cs.project4;

public class Question {

    private long id;
    //private String q;
    private String capital;
    private String firstOption;
    private String secondOption;
    private String stateName;

    public Question()
    {
        this.id = -1;
        //this.q = null;
        this.capital = null;
        this.firstOption = null;
        this.secondOption = null;
        this.stateName = null;
    }

    public Question( String capital, String firstOption, String secondOption, String stateName)
    {
        this.id = -1; //will be set by setter
        this.capital = capital;
        this.firstOption = firstOption;
        this.secondOption = secondOption;
        this.stateName = stateName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
     */

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFirstOption() {
        return firstOption;
    }

    public void setFirstOption(String firstOption) {
        this.firstOption = firstOption;
    }

    public String getSecondOption() {
        return secondOption;
    }

    public void setSecondOption(String secondOption) {
        this.secondOption = secondOption;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String toString() {
        return id + ": "  + capital + " " + firstOption + " " + secondOption + " " + stateName;
    }

}

