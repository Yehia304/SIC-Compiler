/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import static compiler.Compiler.a;
import static compiler.Compiler.letters;
import static compiler.Compiler.vars;
import static compiler.Compiler.words;
import java.util.regex.Pattern;

/**
 *
 * @author Ibrahim Moursy
 */
public class lexicalAnalyzer {
    public static void print(String a[]){
		int j=0;
		System.out.println("");
		while(a[j]!=null){

			System.out.println(a[j]);
			j++;
		}
		System.out.println("");
	}
    public static void separate() {
        int i=0,j=0,k=0,pointer=0,index=0;
        int index2=0;
        String str = null;
        
        while(a[i] != null){
            //splits every line into letters then separates the words in each line
            str=a[i];
            //myString= "";
            int flag=0;
            letters= str.split("(?!^)"); // splits into letters
            int count=letters.length;
            while(j<count){
                //System.out.println(letters[j]);
                boolean p = Pattern.matches("[^a-zA-Z0-9]",letters[j]);
               // System.out.println(p);
                if(p){
                    if(flag==1)
                        j=j+2;
                    index2 = j;
                    
                    flag=0;
                   //System.out.println(index);
                   //System.out.println(index2);
                    words[k]=str.substring(index,index2);
                   //System.out.println(words[k]);
                   if(!Pattern.matches("^\\d*",words[k])) // toget rid of null when putting spaces
                        k++;
                    if(Pattern.matches("[,()+*;]",letters[j])){
                        //to get the operations, commas, semi colons and bracket
                        index = index2;
                       // System.out.println(index);
                        //System.out.println(index2+1);
                        words[k]=str.substring(index,index2+1);
                            // System.out.println(words[k]); 
                             k++;
                        
                        
                    }else if(Pattern.matches(":",letters[j])){
                        //to get the :=
                        index = index2;
                        //index2= index2+2;
                        //System.out.println(index);
                        //System.out.println(index2+2);
                        words[k]=str.substring(index,index2+2);
                            // System.out.println(words[k]); 
                             k++;
                             flag=1; // to add the indexes only if it got here
                             
                             
                        
                    }
                       //System.out.println("zawed"+ j);
                       index= index2 + 1;
                       if(flag==1)
                           index++;
                    
                }
                
               /* if(Pattern.matches("^\\d*",words[k-1]))
                    k--;*/
                    
                
                    
                //pointer++;
                 j++;
            }
            //System.out.println(words[k-1]);
            if(Pattern.matches("[^); ]",letters[j-1])){
                //to get the second words that have no false 
                    index2 = j;
                   // System.out.println(index);
                    //System.out.println(index2);
                    words[k]=str.substring(index,index2);
                    if(!Pattern.matches("^\\d*",words[k])){
                        //toget rid of nulls
                    
                    //index= index2 + 1;
                   // System.out.println(words[k]);
                    k++;
                    }
                    
            }
            //myString= str.split("\\s+");
            //cout=myString.length;
            /*if(Pattern.matches("^\\d*",words[k]))
                   System.out.println("hi");    //toget rid of nulls*/
            //System.out.println(words[k-1]);
            i++; 
            j=0;
            index=0;
            
            
        }
        
        //print(myString);
        
        
    }
    public static void isVar(){
       int i=0,j=0,id;
       String[] varNotComplete= new String[200];
       
       while(words[i] != null){
               
        boolean p = Pattern.matches("VAR", words[i]);
           if(p){
               i++;
               while(!Pattern.matches("BEGIN",words[i])){
                   varNotComplete[j]=words[i];
                   //System.out.println(varNotComplete[j]);
                   j++;
                   i++;
                   
               }
                
           }
           i++;
       }
       
       id = separateVar(varNotComplete);
       progName(id);
        
    }
    public static int separateVar(String nVar[]){
        int i=0,j=0;
        while(nVar[i] != null){
            //System.out.println("hi");
            boolean p = Pattern.matches("[,;()]",nVar[i]);
           // boolean k = Pattern.matches("^\\d*",nVar[i]);
            //boolean l = p||k; 
            //System.out.println(p);
            //System.out.println(k);
            
            if(!p){
                vars[j]=nVar[i];
                //System.out.println(vars[j]);
                j++;
                i++;
            }
            else
                i++;
            
        }
         //System.out.println(i);   
         return j;
    }
    public static void progName(int id){
        int i=0;
        //System.out.println("heloooooooooooooo");
        //System.out.println(id);
       while(words[i] != null){
               //System.out.println("");
        boolean p = Pattern.matches("PROGRAM", words[i]);
           if(p){
               //i++;
               vars[id]=words[++i];
               //System.out.println(words[i]);
           }
             
           i++;
       }
        
    }
        
   
    public static boolean isIdentifier(String idi){
        int i=0,j=0;
            while(vars[j] != null){
               
                if(idi.equals(vars[j])){
                   
                    return true;
                    
                }
                else
                    j++;
            }
        return false;
    }
    public static boolean isOperator(String ope){
        boolean p = Pattern.matches("[+*]", ope) || Pattern.matches(":=", ope);
        return p;
        
    }
    public static boolean isKeyword(String key){
        if(key.equals("PROGRAM") || key.equals("BEGIN") || key.equals("VAR") || key.equals("END") || key.equals("READ") || key.equals("WRITE")){
            return true;
        }else
            return false;
        
    }
    public static boolean isSeparator(String sep){
        boolean p = Pattern.matches("[;( ),]", sep);
        return p;
    }
    
}
