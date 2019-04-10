/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author thanglongsp
 */
public class Exception {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String exTest = null;
        try{
            System.out.println(exTest.length());
        }catch(NullPointerException e){
            System.out.println(e);
        }finally{
            System.out.println("Luồng chương trình vẫn duy trì bình thường!");
        }
    }
}
