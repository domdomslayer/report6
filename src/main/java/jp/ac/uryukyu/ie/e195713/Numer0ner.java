package jp.ac.uryukyu.ie.e195713;

/**
 * Numer0ner class. Superclass of Enemy and Player.
 */
public class Numer0ner {
    private String number;
    private int first_digit;
    private int second_digit;
    private int third_digit;
    private boolean loser = false;



    public int getFirstDigit(){
        return first_digit;
    }

    public void setFirstDigit(int first_digit){
        this.first_digit = first_digit;
    }

    public int getSecondDigit(){
        return second_digit;
    }

    public void setSecondDigit(int second_digit){
        this.second_digit = second_digit;
    }

    public int getThirdDigit(){
        return third_digit;
    }

    public void setThirdDigit(int third_digit){
        this.third_digit = third_digit;
    }

    /**
     * Get whether Numer0ner is lose.
     * @return Whether Numer0ner is lose
     */
    public boolean isLoser(){
        return loser;
    }

    public void setLoser(boolean bool){
        loser = bool;
    }

    /**
     * Set Numer0ner's number.
     * @param args Number which wanted to set
     */
    public void setNumber(String args){
        this.number = args;
    }

    /**
     * Get Numer0ner's number.
     * @return Numer0ner`s number
     */
    public String getNumber(){
        return number;
    }

    int JudgeEAT(int rcvFirst, int rcvSecond, int rcvThird){
        int numEats = 0;
        if(getFirstDigit() == rcvFirst){
            numEats ++;
        }
        if(getSecondDigit() == rcvSecond){
            numEats ++;
        }
        if(getThirdDigit() == rcvThird){
            numEats ++;
        }
        if(numEats == 3){
            setLoser(true);
        }
        return numEats;
    }

    int JudgeBITE(int rcvFirst, int rcvSecond, int rcvThird){
        int numBites = 0;
        if(getFirstDigit() == rcvSecond || getFirstDigit() == rcvThird){
            numBites ++;
        }
        if(getSecondDigit() == rcvFirst || getSecondDigit() == rcvThird){
            numBites ++;
        }
        if(getThirdDigit() == rcvSecond || getFirstDigit() == rcvThird){
            numBites ++;
        }
        return numBites;
    }

    void Attack(Numer0ner opponent){}
}