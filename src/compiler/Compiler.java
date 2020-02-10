/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Ibrahim Moursy
 */
public class Compiler {

    static String a[]=new String[2000];
    static String[] letters= new String[2000];
    public static String[] words = new String[2000];
    static String[] vars = new String[2000];
    static HashMap<String, String> hmap = new HashMap<String, String>();
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int i=0;
		int choice;
		//Scanner input= new Scanner(System.in);
		FileReader file = new FileReader("C:/Users/Ibrahim Moursy/Desktop/code.txt");
		BufferedReader reader= new BufferedReader( file);
		String text="";
		String line=reader.readLine();
                
                while(line != null){

			text+= line;
			
			a[i]=text;
			text="";
			i++;
			line=reader.readLine();
		}
                //System.out.println(a[0]);
                //separate();
                lexicalAnalyzer lex = new lexicalAnalyzer();
                lexicalAnalyzer.separate();
                
                //separate();
                //print(words);
                lexicalAnalyzer.isVar();
                //print(vars);
                int kk=0;
                while(words[kk] != null){
                boolean isIdi = lexicalAnalyzer.isIdentifier(words[kk]);
                boolean isOp = lexicalAnalyzer.isOperator(words[kk]);
                boolean isKey = lexicalAnalyzer.isKeyword(words[kk]);
                boolean isSep = lexicalAnalyzer.isSeparator(words[kk]);
                    if(isIdi)
                        hmap.put(words[kk], "Id");
                    else if(isOp)
                        hmap.put(words[kk], "Op");
                    else if(isKey)
                        hmap.put(words[kk], "Key");
                    else if(isSep)
                        hmap.put(words[kk], "Sep");  
                    else
                        System.out.println(words[kk] + " is not identified");
                    System.out.println("lexeme is: "+ words[kk] + " & Token is: "+ hmap.get(words[kk]));
                   // System.out.println();
                   
                    kk++;
                   
                }
                parser.run();
               //lexicalAnalyzer.print(words);
               
    }
    
    /*public static void print(String a[]){
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
            
            str=a[i];
            //myString= "";
            int flag=0;
            letters= str.split("(?!^)");
            int count=letters.length;
            while(j<count){
                //System.out.println(letters[j]);
                boolean p = Pattern.matches("[^A-Z0-9]",letters[j]);
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
                    k++;
                    if(Pattern.matches("[,()+*;]",letters[j])){
                        //to get the operations, commas, semi colons and bracket
                        index = index2;
                       // System.out.println(index);
                        //System.out.println(index2+1);
                        words[k]=str.substring(index,index2+1);
                             //System.out.println(words[k]); 
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
                
                
                    
                
                    
                //pointer++;
                 j++;
            }
            if(Pattern.matches("[^); ]",letters[j-1])){
                //to get the second words that have no false 
                    index2 = j;
                   // System.out.println(index);
                    //System.out.println(index2);
                    words[k]=str.substring(index,index2);
                    //index= index2 + 1;
                    //System.out.println(words[k]);
                    k++;
            }
            //myString= str.split("\\s+");
            //cout=myString.length;
            i++; 
            j=0;
            index=0;
            
            
        }
        
        //print(myString);
        
        
    }
    public static void isVar(){
       int i=0,j=0;
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
       
       separateVar(varNotComplete);
        
    }
    public static void separateVar(String nVar[]){
        int i=0,j=0;
        while(nVar[i] != null){
            //System.out.println("hi");
            if(!Pattern.matches(",",nVar[i])){
                vars[j]=nVar[i];
                //System.out.println(vars[j]);
                j++;
                i++;
            }
            else
                i++;
            
        }
         //System.out.println(i);   
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
    }*/
}
