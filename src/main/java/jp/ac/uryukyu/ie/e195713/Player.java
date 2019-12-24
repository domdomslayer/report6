package jp.ac.uryukyu.ie.e195713;

import java.util.Scanner;

/**
 * Player class.
 */
public class Player extends Numer0ner{
    /**
     * Set player's number by input.
     */
    Player(){
        Scanner scan = new Scanner(System.in);
        boolean number_is_suitable = false;
        while(number_is_suitable != true){
            try{
                System.out.println("Please input your number.");
                setNumber(scan.nextLine());
                int pre_number = Integer.parseInt(getNumber());
                setThirdDigit(pre_number / 100);
                setSecondDigit((pre_number / 10) % 10);
                setFirstDigit(pre_number % 10);
                if(getThirdDigit() != getSecondDigit() & getThirdDigit() != getFirstDigit() & getSecondDigit() != getFirstDigit() & getNumber().length() == 3) {
                    number_is_suitable = true;
                }else {
                    System.out.println("It's unsuitable number.");
                }
            }catch(NumberFormatException e){
            }
        }
    }

    @Override
    void Attack(Numer0ner opponent){
        Scanner scan = new Scanner(System.in);
        boolean number_is_suitable = false;
        while(number_is_suitable != true){
            try{
                System.out.println("Please your attack number.");
                String receivedNumber = scan.nextLine();
                int atkFirst = Character.getNumericValue(receivedNumber.charAt(2));
                int atkSecond = Character.getNumericValue(receivedNumber.charAt(1));
                int atkThird = Character.getNumericValue(receivedNumber.charAt(0));
                if(atkFirst != atkSecond & atkFirst != atkThird & atkSecond != atkThird & receivedNumber.length()==3) {
                    number_is_suitable = true;
                    System.out.println("【Player`s Attack】"+receivedNumber+" → "+opponent.JudgeEAT(atkFirst, atkSecond, atkThird)+"EAT "+opponent.JudgeBITE(atkFirst, atkSecond, atkThird)+"BITE");
                }else {
                    System.out.println("It's unsuitable number.");
                }
            }catch(NumberFormatException e){
            }
        }
    }

}