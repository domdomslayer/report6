package jp.ac.uryukyu.ie.e195713;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Enemy class.
 */
public class Enemy extends Numer0ner{

    /**
     * list included numbers which are from 0 to 9
     */
    private ArrayList<Integer> number_list = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

    /**
     * list included lists which gathers possible number and its place
     */
    private ArrayList<ArrayList<ArrayList<Integer>>> possible_list = new ArrayList<ArrayList<ArrayList<Integer>>>();

    /**
     * list included numbers used for attack already
     */
    private ArrayList<String> AlreadyAttackList= new ArrayList<String>();

    /**
     * the phase of Enemy's thought
     * phase 1: narrow possible numbers from all numbers roughly
     * phase 2: guess player's number based on narrowed possible list
     */
    private int attack_phase = 1;

    /**
     * the number of EAT or BITE so far
     */
    private int phaseONE_points = 0;

    /**
     * Create enemy's number randomly.
     */
    Enemy(){
        ArrayList<Integer> number_list2 = (ArrayList<Integer>) number_list.clone();
        Collections.shuffle(number_list2);
        String pre_number = Integer.toString(number_list2.get(0)) + Integer.toString(number_list2.get(1)) + Integer.toString(number_list2.get(2));
        number = pre_number;
        first_digit = Character.getNumericValue(pre_number.charAt(2));
        second_digit = Character.getNumericValue(pre_number.charAt(1));
        third_digit = Character.getNumericValue(pre_number.charAt(0));
    }

    /**
     * Generate attack number during phase 1
     * @return number to attack to player
     */
    String GenerateAttackNum1(){
        String attack_num = null;
        Collections.shuffle(number_list);
        attack_num = Integer.toString(number_list.get(0)) + Integer.toString(number_list.get(1)) + Integer.toString(number_list.get(2));
        for(int index = 0; index<=2; index++){
            number_list.remove(0);
        }
        return attack_num;
    }

    /**
     * Generate attack number during phase 2
     * @return number to attack to player
     */
    String GenerateAttackNum2(){
        String attack_num = null;
        int attackThird = 3;
        int attackSecond = 2;
        int attackFirst = 1;
        Random rand = new Random();
        ArrayList<ArrayList<Integer>> attack_list = new ArrayList<ArrayList<Integer>>();
        boolean number_is_suitable = false;
        while(number_is_suitable != true){
            for(ArrayList<ArrayList<Integer>> list : possible_list){
                if(list.get(list.size()-1).size() != 1){
                    attack_list.add(list.get(rand.nextInt(list.size())));
                } else {
                    attack_list.add(list.get(rand.nextInt(list.size()-1)));
                    attack_list.add(list.get(rand.nextInt(list.size()-1)));
                }
            }
            if(attack_list.size() != 3){
                attack_list.clear();
                continue;
            }
            boolean index_condition = attack_list.get(0).get(1)!=attack_list.get(1).get(1) & attack_list.get(0).get(1)!=attack_list.get(2).get(1) & attack_list.get(1).get(1)!=attack_list.get(2).get(1);
            boolean number_condition = attack_list.get(0).get(0)!=attack_list.get(1).get(0) & attack_list.get(0).get(0)!=attack_list.get(2).get(0) & attack_list.get(2).get(0)!=attack_list.get(1).get(0);
            if(number_condition & index_condition){
                number_is_suitable = true;
                for(ArrayList<Integer> i : attack_list){
                    switch (i.get(1)){
                        case 0:
                            attackThird = i.get(0); break;
                        case 1:
                            attackSecond = i.get(0); break;
                        case 2:
                            attackFirst = i.get(0); break;
                    }
                }
                attack_num = String.valueOf(attackThird) + String.valueOf(attackSecond) + String.valueOf(attackFirst);
                if(AlreadyAttackList.contains(attack_num)){
                    attack_list.clear();
                    number_is_suitable = false;
                }
            } else {
                attack_list.clear();
            }
        }
        return attack_num;
    }

    @Override
    void Attack(Numer0ner opponent){
        String attack_num = null;
        switch(attack_phase){
            case 1:
                attack_num = GenerateAttackNum1(); break;
            case 2:
                attack_num = GenerateAttackNum2(); break;
        }

        int atkFirst = Character.getNumericValue(attack_num.charAt(2));
        int atkSecond = Character.getNumericValue(attack_num.charAt(1));
        int atkThird = Character.getNumericValue(attack_num.charAt(0));
        int EAT_num = opponent.JudgeEAT(atkFirst, atkSecond, atkThird);
        int BITE_num = opponent.JudgeBITE(atkFirst, atkSecond, atkThird);
        System.out.println("【Enemy's Attack】 "+attack_num+" → "+EAT_num+"EAT "+BITE_num+"BITE");
        switch(attack_phase){
            case 1:
                phaseONE_points += EAT_num + BITE_num;
                AddToPossibleList(attack_num, EAT_num, BITE_num);
                if(phaseONE_points == 2 & number_list.size() == 1){
                    ArrayList<ArrayList<Integer>> restPossible= new ArrayList<ArrayList<Integer>>();
                    for(int i=0; i<=2; i++){
                        restPossible.add(new ArrayList<Integer>(Arrays.asList(number_list.get(0), i)));
                    }
                    possible_list.add(restPossible);
                    attack_phase = 2;
                } else if(phaseONE_points==3) {
                    attack_phase = 2;
                }break;
            case 2:
                SubFromPossibleList(atkFirst, atkSecond, atkThird, EAT_num, BITE_num);
                AlreadyAttackList.add(attack_num); break;
        }
    }

    /**
     * make possible list
     * @param rcvNum attack number which Enemy did
     * @param EAT_num the number of EAT
     * @param BITE_num the number of BITE
     */
    void AddToPossibleList(String rcvNum, int EAT_num, int BITE_num) {
        ArrayList<ArrayList<Integer>> tempEAT = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> tempBITE = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= 2; i++) {
            tempEAT.add(new ArrayList<Integer>(Arrays.asList(Character.getNumericValue(rcvNum.charAt(i)), i)));
        }
        for (int i = 0; i <= 2; i++) {
            ArrayList<Integer> index_list = new ArrayList<Integer>(Arrays.asList(0, 1, 2));
            index_list.remove(i);
            for (int x : index_list) {
                tempBITE.add(new ArrayList<Integer>(Arrays.asList(Character.getNumericValue(rcvNum.charAt(i)), x)));
            }
        }
        if(EAT_num != 0){
            for(int i =1; i <EAT_num; i++){
                tempEAT.add(new ArrayList<Integer>(Arrays.asList(999)));
            }
            possible_list.add(tempEAT);

        }
        if(BITE_num != 0){
            for(int i =1; i <BITE_num; i++){
                tempBITE.add(new ArrayList<Integer>(Arrays.asList(999)));
            }
            possible_list.add(tempBITE);
        }
        if(BITE_num ==3){
            caseThreeBITE(Character.getNumericValue(rcvNum.charAt(2)), Character.getNumericValue(rcvNum.charAt(1)), Character.getNumericValue(rcvNum.charAt(0)));
        }
    }

    /**
     * attempt to guess player's number based on possible list
     * @param rcvFirst first digit of attack number which Enemy did
     * @param rcvSecond second digit of attack number which Enemy did
     * @param rcvThird third digit of attack number which Enemy did
     * @param EAT_num the number of EAT
     * @param BITE_num the number of BITE
     */
    void SubFromPossibleList(int rcvFirst, int rcvSecond, int rcvThird, int EAT_num, int BITE_num){
        ArrayList<ArrayList<Integer>> NumIndexList= new ArrayList<ArrayList<Integer>>(Arrays.asList(new ArrayList<Integer>(Arrays.asList(rcvThird ,0)), new ArrayList<Integer>(Arrays.asList(rcvSecond ,1)), new ArrayList<Integer>(Arrays.asList(rcvFirst ,2))));
        ArrayList<Integer> OnlyNumList = new ArrayList<Integer>(Arrays.asList(rcvThird, rcvSecond, rcvFirst));
        if(EAT_num ==0 & BITE_num == 0) {
            for(ArrayList<ArrayList<Integer>> CandicateList : possible_list){
                for(int number : OnlyNumList){
                    for(int i=0; i<=2; i++){
                        if(CandicateList.contains(Arrays.asList(number, i))){
                            CandicateList.remove(Arrays.asList(number, i));
                        }
                    }
                }
            }
        }else if(EAT_num == 0){
            for(ArrayList<ArrayList<Integer>> CandicateList : possible_list){
                for(ArrayList<Integer> NumIndex : NumIndexList){
                    if(CandicateList.contains(NumIndex)){
                        CandicateList.remove(NumIndex);
                    }
                }
            }
        }
        if(BITE_num == 3){
            caseThreeBITE(rcvFirst, rcvSecond, rcvThird);
        }
    }

    /**
     * update possible list considerably when Enemy got 3 BITE
     * @param rcvFirst first digit of attack number which Enemy did
     * @param rcvSecond second digit of attack number which Enemy did
     * @param rcvThird third digit of attack number which Enemy did
     */
    void caseThreeBITE(int rcvFirst, int rcvSecond, int rcvThird){
        ArrayList<ArrayList<Integer>> First_possible = new ArrayList<ArrayList<Integer>>(Arrays.asList(new ArrayList<Integer>(Arrays.asList(rcvSecond, 2)), new ArrayList<Integer>(Arrays.asList(rcvThird, 2))));
        ArrayList<ArrayList<Integer>> Second_possible = new ArrayList<ArrayList<Integer>>(Arrays.asList(new ArrayList<Integer>(Arrays.asList(rcvFirst, 1)), new ArrayList<Integer>(Arrays.asList(rcvThird, 1))));
        ArrayList<ArrayList<Integer>> Third_possible = new ArrayList<ArrayList<Integer>>(Arrays.asList(new ArrayList<Integer>(Arrays.asList(rcvSecond, 0)), new ArrayList<Integer>(Arrays.asList(rcvFirst, 0))));
        possible_list = new ArrayList<ArrayList<ArrayList<Integer>>>(Arrays.asList(First_possible, Second_possible, Third_possible));
    }
}