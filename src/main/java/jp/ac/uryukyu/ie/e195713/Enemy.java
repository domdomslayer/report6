package jp.ac.uryukyu.ie.e195713;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Enemy class.
 */
public class Enemy extends Numer0ner{
    ArrayList<Integer> number_list = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    HashMap<Integer, Integer> BITE_dict = new HashMap();
    HashMap<Integer, Integer> EAT_dict = new HashMap();
    private int attack_phase = 1;
    private int phaseONE_points = 0;

    public int getAttackPhase(){
        return  attack_phase;
    }

    public void setAttackPhase(int attack_phase){
        this.attack_phase = attack_phase;
    }

    /**
     * Create enemy's number randomly.
     */
    Enemy(){
        ArrayList<Integer> number_list2 = (ArrayList<Integer>) number_list.clone();
        Collections.shuffle(number_list2);
        String pre_number = Integer.toString(number_list2.get(0)) + Integer.toString(number_list2.get(1)) + Integer.toString(number_list2.get(2));
        setNumber(pre_number);
        setFirstDigit(Character.getNumericValue(pre_number.charAt(2)));
        setSecondDigit(Character.getNumericValue(pre_number.charAt(1)));
        setThirdDigit(Character.getNumericValue(pre_number.charAt(0)));
    }

    String GenerateAttackNum(int attack_phase){
        switch(attack_phase){
            case 1:
                Collections.shuffle(number_list);
                String attack_num = Integer.toString(number_list.get(0)) + Integer.toString(number_list.get(1)) + Integer.toString(number_list.get(2));
                for(int index = 0; index<=2; index++){
                    number_list.remove(0);
                }
                if(number_list.size() == 1){
                    attack_phase = 2;
                }
                return attack_num;
            case 2:
            default:
                return "123"; //
        }
    }

    @Override
    void Attack(Numer0ner opponent){
        String attack_num = GenerateAttackNum(attack_phase);
        int atkFirst = Character.getNumericValue(attack_num.charAt(2));
        int atkSecond = Character.getNumericValue(attack_num.charAt(1));
        int atkThird = Character.getNumericValue(attack_num.charAt(0));
        System.out.println("【Enemy`s Attack】 "+attack_num+" → "+opponent.JudgeEAT(atkFirst, atkSecond, atkThird)+"EAT "+opponent.JudgeBITE(atkFirst, atkSecond, atkThird)+"BITE");
        phaseONE_points += opponent.JudgeEAT(atkFirst, atkSecond, atkThird) + opponent.JudgeBITE(atkFirst, atkSecond, atkThird);
        //BITE_dict, EAT_dictに突っ込むコード
        System.out.println(phaseONE_points);
        if(phaseONE_points==3){
            attack_phase = 2;
        }
    }
}