package edu.uga.cs.project4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question implements Serializable
{


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
         String question;
         String capital;

    public boolean isAnswerCorrect() {
        return isAnswerCorrect;
    }

    public void setAnswerCorrect(boolean answerCorrect) {
        isAnswerCorrect = answerCorrect;
    }

    boolean isAnswerCorrect;

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    boolean isAnswered;

    public Question(){}
    public Question(int id,String question, String capital, String firstCity, String secondCity, String state) {
        this.id=id;
        this.question = question;
        this.capital = capital;
        this.firstCity = firstCity;
        this.secondCity = secondCity;
        this.state = state;

        options.clear();
        options.add(capital);
        options.add(firstCity);
        options.add(secondCity);
        Collections.shuffle(options);

    }

    String firstCity;
         String secondCity;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFirstCity() {
        return firstCity;
    }

    public void setFirstCity(String firstCity) {
        this.firstCity = firstCity;
    }

    public String getSecondCity() {
        return secondCity;
    }

    public void setSecondCity(String secondCity) {
        this.secondCity = secondCity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    String state;

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    List<String> options=new ArrayList<>();

}
