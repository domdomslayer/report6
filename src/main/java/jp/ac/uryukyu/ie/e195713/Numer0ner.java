package jp.ac.uryukyu.ie.e195713;

/**
 * Numer0ner class. Superclass of Enemy and Player.
 */
public class Numer0ner {
    /**
     * Numer0ner's number.
     */
    protected String number;

    /**
     * Number's first digit.
     */
    protected int first_digit;

    /**
     * Number's second digit.
     */
    protected int second_digit;

    /**
     * Number's third digit.
     */
    protected int third_digit;

    /**
     * Whether Numer0ner is loser.
     */
    private boolean loser = false;

    /**
     * Get whether Numer0ner is lose.
     * @return Whether Numer0ner is lose
     */
    public boolean isLoser(){
        return loser;
    }

    /**
     * set Numer0ner's number, for only UnitTest
     * @param number Numer0ner's number
     */
    public void setNumber(String number){
        this.number = number;
    }
    /**
     * compare number and received number, and return the number of EAT.
     * @param rcvFirst first digit of received number.
     * @param rcvSecond second digit of received number.
     * @param rcvThird third digit of received number.
     * @return the number of EAT
     */
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

    /**
     * compare number and received number, and return the number of BITE.
     * @param rcvFirst first digit of received number.
     * @param rcvSecond second digit of received number.
     * @param rcvThird third digit of received number.
     * @return the number of BITE
     */
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

    /**
     * attack to an opponent.
     * @param opponent Numer0ner who is attacked.
     */
    void Attack(Numer0ner opponent){}
}