package jp.ac.uryukyu.ie.e195713;

import java.util.*;

/**
 * Enemy class.
 */
public class Enemy extends Numer0ner{
    ArrayList<Integer> number_list = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    ArrayList<ArrayList<ArrayList<Integer>>> possible_list = new ArrayList<ArrayList<ArrayList<Integer>>>();
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
        String attack_num = null;
        switch(getAttackPhase()){
            case 1:
                Collections.shuffle(number_list);
                attack_num = Integer.toString(number_list.get(0)) + Integer.toString(number_list.get(1)) + Integer.toString(number_list.get(2));
                for(int index = 0; index<=2; index++){
                    number_list.remove(0);
                }
                if(number_list.size() == 1){
                    setAttackPhase(2);
                    if(phaseONE_points == 2){
                        ArrayList<ArrayList<Integer>> restPossible= new ArrayList<ArrayList<Integer>>();
                        for(int i=0; i<=2; i++){
                            restPossible.add(new ArrayList<Integer>(Arrays.asList(number_list.get(0), i)));
                        }
                        possible_list.add(restPossible);
                    }
                }
                break;
            case 2:
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
                        System.out.println(attack_list);
                    }
                    boolean index_condition = attack_list.get(0).get(1)!=attack_list.get(1).get(1) & attack_list.get(0).get(1)!=attack_list.get(2).get(1) & attack_list.get(1).get(1)!=attack_list.get(2).get(1);
                    boolean number_condition = attack_list.get(0).get(0)!=attack_list.get(1).get(0) & attack_list.get(0).get(0)!=attack_list.get(2).get(0) & attack_list.get(2).get(0)!=attack_list.get(1).get(0);
                    if(number_condition & index_condition){
                        number_is_suitable = true;
                    } else {
                        attack_list.clear();
                    }
                }
                int attackThird = 3;
                int attackSecond = 2;
                int attackFirst = 1;
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
                break;
        }
        return attack_num;
    }

    @Override
    void Attack(Numer0ner opponent){
        String attack_num = GenerateAttackNum(attack_phase);
        int atkFirst = Character.getNumericValue(attack_num.charAt(2));
        int atkSecond = Character.getNumericValue(attack_num.charAt(1));
        int atkThird = Character.getNumericValue(attack_num.charAt(0));
        int EAT_num = opponent.JudgeEAT(atkFirst, atkSecond, atkThird);
        int BITE_num = opponent.JudgeBITE(atkFirst, atkSecond, atkThird);
        System.out.println("【Enemy`s Attack】 "+attack_num+" → "+EAT_num+"EAT "+BITE_num+"BITE");
        phaseONE_points += EAT_num + BITE_num;
        if(attack_phase==1){
            AddToPossibleList(attack_num, EAT_num, BITE_num);
        }
        if(phaseONE_points==3){
            attack_phase = 2;
        }
        System.out.println(possible_list); //kfodkfodkfod
    }

    void AddToPossibleList(String rcvNum, int EAT_num, int BITE_num) {
        ArrayList<ArrayList<Integer>> tempEAT = new ArrayList<ArrayList<Integer>>(); //HaspMap<int number, int index>
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
    }
}
