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
       
       ArrayList<String> x = new ArrayList<String>();
       for(int i=0; i<(allInput.size()/2);i++){
           x.add(allInput.get(i));
       }
       ArrayList<String> a = new ArrayList<String>();
       ArrayList<String> y = new ArrayList<String>();
       for(int i=(allInput.size()/2); i<allInput.size();i++){
           a.add(allInput.get(i));
           y.add(allInput.get(i));
       }
       
       int size = x.size();
//     
       ArrayList<ArrayList<String>> columns = new ArrayList<ArrayList<String>>();
       columns.add(spaceOutList(x));
       columns.add(spaceOutList(y));
        
       for(int j=0; j<size; j++){
           ArrayList<String> newCol = new ArrayList<String>();
           for(int i=size-1; i>j; i--){
               String newVal = rn.divide(rn.subtract(a.get(i), a.get(i-1)), rn.subtract(x.get(i), x.get(i-(j+1))));
                newCol.add(newVal);
               a.set(i,newVal );
           }
           columns.add(spaceOutList(newCol,j+1));
       }
      print2dList(columns);
       System.out.println("\nInterpolating polynomial is:");
       printNewtonForm(x,a);
       System.out.print("\nSimplified polynomial is:\n");
       printExpandedForm(x,a);
       System.out.print("\n");
//    
    }
    
   public static void printArrayList(ArrayList<String> n){
       for(int i=0; i<n.size();i++){
           System.out.println(n.get(i));
       }
   }
   
   public static void printDoubleList(ArrayList<Double> n){
       for(int i=0; i<n.size(); i++){
           System.out.println(n.get(i));
       }
   }
   public static void print2dList(ArrayList<ArrayList<String>> columns){
       System.out.printf("%8s","x");
       int commaCount=0;
       for(int i=0; i<columns.size()-2; i++){
           String s = "f[";
           for(int j=0; j<commaCount; j++){
               s = s+",";
           }
           s = s + "]";
           commaCount++;
           System.out.printf("%8s", s);
       }
       System.out.print("\n");
      for(int i=0; i<columns.get(0).size();i++){
        for(int j=0; j<columns.size(); j++){
          System.out.printf("%8s",columns.get(j).get(i));
        }
        System.out.print("\n");
      }
       
   }
   
    public static String toFraction(double d, int factor) {
    StringBuilder sb = new StringBuilder();
    boolean negative=false;
    if (d < 0) {
        negative=true;
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
    if(negative && sb.toString().charAt(0)!='-') return ("-"+sb.toString());
    return sb.toString();
}
 
public static void printExpandedForm(ArrayList<String> x, ArrayList<String> c){
    Polynomial complete = new Polynomial();
    for(int i=0; i<c.size(); i++){
        Polynomial cur=new Polynomial();
        Real_Number coef = new Real_Number(c.get(i));
        
        if(i==0){
            ArrayList<Double> temp = new ArrayList<Double>();
            temp.add((double)(coef.get_numerator()/coef.get_denominator()));
            complete.setPolynomial(temp);
            
        }else{
            for(int j=0; j<i; j++){
            
            ArrayList<Double> l = new ArrayList<Double>();
            
                Real_Number constant = new Real_Number(x.get(j));
                l.add((double)(-1.0*constant.get_numerator()/constant.get_denominator()));
                l.add(1.0);
                if(j==0){
                    cur.setPolynomial(l);
                }else{
                    cur.multiplySelf(l);
                }

//                cur.print();
               
            }
//            System.out.println("Cur");
//            cur.print();
            double constMult = ((double)coef.get_numerator()/(double)coef.get_denominator());
            cur.multiplySelf(constMult);
//            System.out.println("\nCur scaled by "+constMult);
//            cur.print();
//            System.out.println("\nComplete");
//            complete.print();
            complete.addToSelf(cur);
//            System.out.println("\nComplete + Curr");
//            complete.print();
        }
        
    }
    
    complete.print();
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
                if( x.get(j).charAt(0)=='-'){
                    System.out.print("+" + Integer.toString(Integer.parseInt(x.get(j))*-1));
                }else{
                    System.out.print("-"+x.get(j));
                }
                System.out.print(")");
            }
            
        }
     }
     System.out.print("\n");
    }
 
    public static ArrayList<String> spaceOutList(ArrayList<String> l){
        ArrayList<String> r = new ArrayList<String>();
        for(int i=0; i<l.size(); i++){
            r.add(l.get(i));
            r.add(" ");
        }
        return r;
    }
    public static ArrayList<String> spaceOutList(ArrayList<String> l, int offset){
        
        ArrayList<String> r = new ArrayList<String>();
        for(int i=0; i<offset; i++){
         r.add(" ");
        }
        for(int i=l.size()-1; i>=0; i--){
            r.add(l.get(i));
            r.add(" ");
        }
        for(int i=0; i<offset; i++){
         r.add(" ");
        }
        return r;
    }
    
}
