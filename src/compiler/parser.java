/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;
import static compiler.Compiler.words;
import static compiler.Compiler.hmap;
import java.util.regex.Pattern;
/**
 *
 * @author Ibrahim Moursy
 */
public class parser {
    
    static int count=0;
    static int count2=0;
    static int flag=0;
    
    public static void run(){
        prog();
    }
    
    public static void prog(){
        int i=0,j=0;
        if(Pattern.matches("PROGRAM", words[i])){
            System.out.println("found PROGRAM");
            i++;
            if(progName(i)){
                i++;
             
                if(Pattern.matches("VAR", words[i])){
                    i++;
                   // System.out.println(i);
                    //j=idList(i);
                  //  System.out.println(j);
                   // System.out.println(words[j]);
                   //j!=i-1
                   boolean jj=idList(i);
                    System.out.println(jj);
                    if(jj){
                        
                        System.out.println("found idlist");
                        i=i+count-1;
                        if(Pattern.matches("BEGIN", words[i])){
                            System.out.println("found BEGIN");
                            i++;
                            //stmtList(i); //last
                        }else
                             System.out.println("syntax error"); //no BEGIN after idList or error in the list
                    }else 
                        System.out.println("syntax error");// no idList availble
                }else 
                System.out.println("syntax error"); //no VAR after prog-name
                    
            }else 
                System.out.println("syntax error"); //no programe name
        }
        else
            System.out.println("syntax error"); // NO PROGRAME at the start of code
            
    }
    public static boolean progName(int i){
        if(Pattern.matches("Id", hmap.get(words[i]))){
            System.out.println("found Id");
            return true;
        }
            System.out.println("syntax error");
            return false;
        
    }
    public static boolean idList(int i){
        
        //count++;
       //int flag=0;
        int j= i+1;
        //int t = i;
        //System.out.println("hi");
        boolean p = Pattern.matches("Id", hmap.get(words[i]));
        boolean k = Pattern.matches(",", words[i]);
        boolean l = Pattern.matches("Id", hmap.get(words[j]));
        boolean m = k&&l;
        //boolean q = idList(i);
        /*System.out.println(hmap.get(words[i]));
        
        System.out.println(words[i]);
        System.out.println(p);
        System.out.println(k);*/
       // System.out.println(jj);
        if(p || m){
            i++;
            count++;
            flag=1;
            //System.out.println(i);
            boolean jj = idList(i);
            //System.out.println(jj);
            return true;
            
        }
        return false;
        //System.out.println(i);
        //System.out.println(t);
        //return false;
    }
    
    public static int stmtList(int i){
        int j=i+1;
        boolean p = stmt(i);
        boolean k = Pattern.matches(";", words[i]);
        boolean l = stmt(j);
        boolean m = k&&l;
        if(p || m){
            System.out.println("Stmt true");
            count2++;
            i++;
            stmt(i);
        }
           
        return i+count2-1;
    }
    
    public static boolean stmt(int i){
        //System.out.println(words[i]);
        if(assign(i) || read(i) || write(i) )
            return true;
        else
            return false;
        
    }

    private static boolean assign(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean read(int i) {
        boolean p = Pattern.matches("READ", words[i]);
    }

    private static boolean write(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
