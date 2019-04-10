/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xulyfile5m;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import static com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author thanglongsp
 */
public class XuLyFile5M {

    /**
     * @param args the command line arguments
     */
    static double dung_luong;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        String fileIn = "..\\XuLyFile5M\\src\\file\\b4_raw.txt";

        FileWriter fileOut = new FileWriter("..\\XuLyFile5M\\src\\file\\output.txt");
        FileWriter fileOut2 = new FileWriter("..\\XuLyFile5M\\src\\file\\output2.txt");

        BufferedWriter out = new BufferedWriter(fileOut);
        BufferedWriter out2 = new BufferedWriter(fileOut2);
        
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        HashMap<String, Integer> map2 = new HashMap<String, Integer>();
        
        BufferedReader reader = new BufferedReader(new FileReader(fileIn));
        
        Scanner input = new Scanner(reader);
        int dem = 0;
        String word;
        String stamp1, stamp2;

        // thêm vào map
        while (input.hasNext()) {
            stamp1 = input.next(); // nguyên thủy
            stamp2 = stamp1.toLowerCase(); // chuyển về thường
            // lọc bỏ ký tự đặc biệt ở đầu
            for (int i = 0; i < stamp2.length(); i++) {
                if (stamp2.charAt(0) == '.' || stamp2.charAt(0) == '<' || stamp2.charAt(0) == '>' || stamp2.charAt(0) == '=' || stamp2.charAt(0) == ' ' || stamp2.charAt(0) == '/' || stamp2.charAt(0) == '\\' || stamp2.charAt(0) == ')' || stamp2.charAt(0) == '(' || stamp2.charAt(0) == ',' || stamp2.charAt(0) == '*' || stamp2.charAt(0) == ';' || stamp2.charAt(0) == '—' || stamp2.charAt(0) == '-' || stamp2.charAt(0) == '…' || stamp2.charAt(0) == '?' || stamp2.charAt(0) == '"' || stamp2.charAt(0) == '’' || stamp2.charAt(0) == '“' || stamp2.charAt(0) == ':' || stamp2.charAt(0) == ',' || stamp2.charAt(0) == '!' || stamp2.charAt(0) == '@' || stamp2.charAt(0) == '\'') {
                    stamp2 = stamp2.substring(1);
                }
            }

            //lọc bỏ ký tự đặc biệt ở cuối
            for (int i = 0; i < stamp2.length(); i++) {
                if (stamp2.charAt(stamp2.length() - 1) == '.' || stamp2.charAt(stamp2.length() - 1) == '<' || stamp2.charAt(stamp2.length() - 1) == '>' || stamp2.charAt(stamp2.length() - 1) == '=' || stamp2.charAt(stamp2.length() - 1) == ' ' || stamp2.charAt(stamp2.length() - 1) == '/' || stamp2.charAt(stamp2.length() - 1) == '\\' || stamp2.charAt(stamp2.length() - 1) == '‘' || stamp2.charAt(stamp2.length() - 1) == '”' || stamp2.charAt(stamp2.length() - 1) == ')' || stamp2.charAt(stamp2.length() - 1) == '(' || stamp2.charAt(stamp2.length() - 1) == ',' || stamp2.charAt(stamp2.length() - 1) == ';' || stamp2.charAt(stamp2.length() - 1) == '*' || stamp2.charAt(stamp2.length() - 1) == '—' || stamp2.charAt(stamp2.length() - 1) == '-' || stamp2.charAt(stamp2.length() - 1) == '…' || stamp2.charAt(stamp2.length() - 1) == '?' || stamp2.charAt(stamp2.length() - 1) == '"' || stamp2.charAt(stamp2.length() - 1) == '’' || stamp2.charAt(stamp2.length() - 1) == '“' || stamp2.charAt(stamp2.length() - 1) == ':' || stamp2.charAt(stamp2.length() - 1) == ',' || stamp2.charAt(stamp2.length() - 1) == '!' || stamp2.charAt(stamp2.length() - 1) == '@' || stamp2.charAt(stamp2.length() - 1) == '\'') {
                    stamp2 = stamp2.substring(0, stamp2.length() - 1);
                }
            }
            // Gán cho cần dùng
            word = stamp2;
            double bytes = word.length();
            double megabytes = (bytes / (1024*1024));
            dung_luong = dung_luong + megabytes;
            
            if (dung_luong > 3) {
                if (map2.containsKey(word)) {
                    map2.replace(word, map2.get(word) + 1);
                } else {
                    String key = word;
                    Integer value = 1;
                    map2.put(key, value);
                }
            } else {
                if (map.containsKey(word)) {
                    map.replace(word, map.get(word) + 1);
                } else {
                    String key = word;
                    Integer value = 1;
                    map.put(key, value);
                }
            }
        }
        
//      System.out.println(m1.getKey()+" : "+m1.getValue());
        for(Map.Entry<String,Integer> m :map.entrySet()){
            out.write(m.getKey() + " - " + m.getValue() + "\n");
            for(Map.Entry<String,Integer> m1 :map2.entrySet()){
                if(m.getKey().equals(m1.getKey()) == true){
                      out.write(m1.getKey() + " - " +(m1.getValue() + m.getValue()) + "\n");
//                    System.out.println("cc");
//                    map.remove(m.getKey());
//                    map2.remove(m1.getKey());
//                    continue;
                }
            }
        }
        
        for(Map.Entry<String,Integer> m :map.entrySet()){
            out2.write(m.getKey() + " - " + m.getValue() + "\n");
        }
        
        for(Map.Entry<String,Integer> m2 :map2.entrySet()){
            out2.write(m2.getKey() + " - " + m2.getValue() + "\n");
        }
        reader.close();
    }
}
