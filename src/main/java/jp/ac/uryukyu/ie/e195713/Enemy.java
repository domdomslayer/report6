package jp.ac.uryukyu.ie.e195713;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Enemy extends Numer0ner{
    ArrayList<Integer> number_list = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    HashMap<Integer, Integer> BITE_dict = new HashMap();
    HashMap<Integer, Integer> EAT_dict = new HashMap();

    Enemy(){
        ArrayList<Integer> number_list2 = (ArrayList<Integer>) number_list.clone();
        Collections.shuffle(number_list2);
        String pre_number = Integer.toString(number_list2.get(0)) + Integer.toString(number_list2.get(1)) + Integer.toString(number_list2.get(2));
        setNumber(pre_number);
    }
}