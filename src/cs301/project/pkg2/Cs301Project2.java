/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs301.project.pkg2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Josh
 */
public class Cs301Project2 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner filein = new Scanner(new FileInputStream("input.txt"));
        
        Real_Number rn = new Real_Number();
        ArrayList<String> allInput = new ArrayList<String>();
       
        
        while (filein.hasNext()) {      // while there is another token to read
            allInput.add(toFraction(filein.nextDouble(), 10)); // 1/10000
        }
//        printArrayList(allInput);
       ArrayList<String> x = new ArrayList<String>();
       for(int i=0; i<(allInput.size()/2);i++){
           x.add(allInput.get(i));
       }
       ArrayList<String> a = new ArrayList<String>();
       for(int i=(allInput.size()/2); i<allInput.size();i++){
           a.add(allInput.get(i));
       }
//      
       int size = x.size();
//      
    
       for(int j=0; j<size; j++){
           for(int i=size-1; i>j; i--){
               String newVal = rn.divide(rn.subtract(a.get(i), a.get(i-1)), rn.subtract(x.get(i), x.get(i-(j+1))));
//               System.out.println("("+a.get(i)+"-"+ a.get(i-1)+")/("+x.get(i)+"-"+ x.get(i-(j+1))+")");
//               System.out.println("Set" + newVal);
               a.set(i,newVal );
           }
       }
//      

       printArrayList(a);
       printNewtonForm(x,a);
    }
    
   public static void printArrayList(ArrayList<String> n){
       for(int i=0; i<n.size();i++){
           System.out.println(n.get(i));
       }
   }

   
    public static String toFraction(double d, int factor) {
    StringBuilder sb = new StringBuilder();
    if (d < 0) {
        sb.append('-');
        d = -d;
    }
    long l = (long) d;
    if (l != 0) sb.append(l);
    //if(l==0) sb.append("0");
    d -= l;
    double error = Math.abs(d);
    int bestDenominator = 1;
    for(int i=2;i<=factor;i++) {
        double error2 = Math.abs(d - (double) Math.round(d * i) / i);
        if (error2 < error) {
            error = error2;
            bestDenominator = i;
        }
    }
    if (bestDenominator > 1)
        sb.delete(0,1).append(Math.round(d * bestDenominator) + (l*bestDenominator)).append('/') .append(bestDenominator);
    if(d==0 && l==0) sb.append("0");
    return sb.toString();
}
    
 public static void printNewtonForm(ArrayList<String> x, ArrayList<String> c){
     for(int i=0; i<c.size(); i++){
        if(i!=0 && c.get(i).charAt(0)=='-'){
            
        }else if(i!=0){
            System.out.print("+");
        }
        System.out.print(c.get(i));
        for(int j=0; j<i; j++){
            if(x.get(j).equals("0")){
                System.out.print("x");
            }else{
                System.out.print("(x");
                if(x.get(j).charAt(0)=='-'){
                    x.set(j, x.get(j).substring(1, x.get(j).length()-1));
                    System.out.print("+");
                }else{
                    System.out.print("-");
                }
                System.out.print(x.get(j)+")");
            }
            
        }
     }
 }   
}
