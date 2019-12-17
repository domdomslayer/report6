package jp.ac.uryukyu.ie.e195713;

import java.util.Scanner;

public class Player extends Numer0ner{
    Player(){
        Scanner scan = new Scanner(System.in);
        boolean number_is_suitable = false;
        while(number_is_suitable != true){
            try{
                System.out.println("Please input your number.");
                setNumber(scan.next());
                int pre_number = Integer.parseInt(getNumber());
                int three_digit = pre_number / 100;
                int double_digit = (pre_number / 10) % 10;
                int single_digit = pre_number % 10;
                if(three_digit != double_digit & three_digit != single_digit & double_digit != single_digit & pre_number<999 & pre_number>100) {
                    number_is_suitable = true;
                }else {
                    System.out.println("It's unsuitable number.");
                }
            }catch(NumberFormatException e){
            }
        }
    }
}