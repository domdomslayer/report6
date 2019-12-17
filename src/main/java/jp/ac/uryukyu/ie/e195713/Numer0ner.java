package jp.ac.uryukyu.ie.e195713;

public class Numer0ner {
    private String number;
    private boolean loser = false;

    boolean isLoser(){
        return loser;
    }

    public void setNumber(String args){
        this.number = args;
    }

    public String getNumber(){
        return number;
    }
}