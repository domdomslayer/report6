package jp.ac.uryukyu.ie.e195713;

/**
 * Numer0ner class. Superclass of Enemy and Player.
 */
public class Numer0ner {
    private String number;
    protected int first_digit;
    protected int second_digit;
    protected int third_digit;
    private boolean loser = false;

    /**
     * Get whether Numer0ner is lose.
     * @return Whether Numer0ner is lose
     */
    public boolean isLoser(){
        return loser;
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
        if(first_digit == rcvFirst){
            numEats ++;
        }
        if(second_digit == rcvSecond){
            numEats ++;
        }
        if(third_digit == rcvThird){
            numEats ++;
        }
        if(numEats == 3){
             loser = true;
        }
        return numEats;
    }

    int JudgeBITE(int rcvFirst, int rcvSecond, int rcvThird){
        int numBites = 0;
        if(first_digit == rcvSecond || first_digit == rcvThird){
            numBites ++;
        }
        if(second_digit == rcvFirst || second_digit == rcvThird){
            numBites ++;
        }
        if(third_digit == rcvSecond || third_digit == rcvFirst){
            numBites ++;
        }
        return numBites;
    }

    void Attack(Numer0ner opponent){}
}