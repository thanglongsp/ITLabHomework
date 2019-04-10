/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thanglongsp
 */
public class Main {
    public static void main(String args[]) {
        // use Integer
        MyGeneric<Integer> case1 = new MyGeneric<Integer>();
        case1.add(23);
        System.out.println(case1.get());
 
        // use String
        MyGeneric<String> case2 = new MyGeneric<String>();
        case2.add("thanglong");
        System.out.println(case2.get());
    }
}
