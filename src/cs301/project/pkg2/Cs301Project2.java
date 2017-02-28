/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs301.project.pkg2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.BigInteger;
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
        
        Real_Number n = new Real_Number();
        ArrayList<String> allInput = new ArrayList<String>();
       // n.print_number(n.divide(new Real_Number("2/3"),new Real_Number("4/5")));
        
        while (filein.hasNext()) {      // while there is another token to read
             
            
            allInput.add(toFraction(filein.nextDouble(), 10)); // 1/10000
        }
//        printArrayList(allInput);
       ArrayList<String> x = new ArrayList<String>();
       for(int i=0; i<(allInput.size()/2);i++){
           x.add(allInput.get(i));
       }
       ArrayList<String> y = new ArrayList<String>();
       for(int i=(allInput.size()/2); i<allInput.size();i++){
           y.add(allInput.get(i));
       }
       
//       printArrayList(x);
//       printArrayList(y);
       
       
       ArrayList<ArrayList<String>> columns = new ArrayList<ArrayList<String>>();
       columns.add(x);
       columns.add(y);
       while(columns.size() < (x.size()-1)){
           int i=0;
           int j=0;
           ArrayList<String> newColumn = new ArrayList<String>();
           int k=0;
           int p=j+2;
           while(newColumn.size() < columns.get(columns.size()-1).size()-1 ){
               n.print_number(
                       n.divide ( 
                                n.subtract( 
                                       new Real_Number(columns.get(j+1).get(i+1)), 
                                       new Real_Number(columns.get(j+1).get(i))
                                ),
                                n.subtract(
                                       new Real_Number(columns.get(0).get(p)), 
                                       new Real_Number(columns.get(0).get(k))
                               )));
               
               newColumn.add(
                       n.get_number(n.divide(
                               n.subtract( 
                                       new Real_Number(columns.get(j+1).get(i+1)), 
                                       new Real_Number(columns.get(j+1).get(i))
                                ),
                               n.subtract(
                                       new Real_Number(columns.get(0).get(p)), 
                                       new Real_Number(columns.get(0).get(k))
                               )
                       )
                       )
               );
               p++;
               k++;
               i++;
           }
           columns.add(newColumn);
           j++;
       }
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
    
    
}
