/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs301.project.pkg2;

import java.math.BigInteger;

/**
 *
 * @author Josh
 */
public class Real_Number {

    // Data Fields
    int numerator;
    int denominator;
    
    /**
     * Constructor 1 of 4 for the Real Number Class. Creates a new real number
     * with numerator a and denominator 1.
     * @param a Integer to be assigned to the numerator field
     */
    public Real_Number(int a){
        
        numerator = a;
        denominator = 1;
        
    }
    
    /**
     * Constructor 2 of 4 for the Real Number Class. Creates a new real number
     * with numerator a and denominator b.
     * @param a Integer to be assigned to the numerator field
     * @param b Integer to be assigned to the denominator field
     */
    public Real_Number(int a, int b){
        
        numerator = a;
        denominator = b;
        
    }
    
    public Real_Number(String s){
        String[] parts = s.split("\\/");
        if(parts.length>1){
            numerator = Integer.parseInt(parts[0]);
            denominator = Integer.parseInt(parts[1]);
        }else{
            numerator = Integer.parseInt(parts[0]);
            denominator = 1;
        }
    }
    public Real_Number(){
        
    }
    /**
     * Setter method for the numerator field
     * @param numerator Integer to be assigned to this object's numerator field
     */
    public void set_numerator(int numerator){
        
        this.numerator = numerator;
        
    }
    
    /**
     * Getter method for the numerator field
     * @return The calling object's numerator field value
     */
    public int get_numerator(){
        
        return numerator;
        
    }
    
    /**
     * Setter method for the numerator field
     * @param denominator Integer to be assigned to this object's denominator 
     *                    field
     */
    public void set_denominator(int denominator){
        
        this.denominator = denominator;
        
    }
    
    /**
     * Getter method for the denominator field
     * @return The calling object's denominator field value
     */
    public int get_denominator(){
        
        return denominator;
        
    }
    
    /**
     * Calculates the sum of two real numbers a and b
     * @param a The first real number a of a + b
     * @param b The second real number b of a + b
     * @return The result c = a + b
     */
    public Real_Number add(Real_Number a, Real_Number b){
        
        if(a.get_denominator() == b.get_denominator()){
            return new Real_Number(a.get_numerator() + b.get_numerator(), 
                                   a.get_denominator());
        }
        else{
            Real_Number result = new Real_Number(a.get_numerator() * 
                                 b.get_denominator()+ b.get_numerator() * 
                                 a.get_denominator(), a.get_denominator() * 
                                 b.get_denominator());
            result = simplify(result);
            return result;
            
        }
        
    }
    
    /**
     * Calculates the difference of two real numbers a and b
     * @param a The first real number a of a - b
     * @param b The second real number b of a - b
     * @return The result c = a - b 
     */
    public Real_Number subtract(Real_Number a, Real_Number b){
        
       if(a.get_denominator() == b.get_denominator()){
            Real_Number result = new Real_Number(a.get_numerator() - b.get_numerator(), 
                                     a.get_denominator());
            return result;
        }
        else{
            Real_Number result = new Real_Number(a.get_numerator() * 
                                 b.get_denominator()- b.get_numerator() * 
                                 a.get_denominator(), a.get_denominator() * 
                                 b.get_denominator());
            result = simplify(result);
            return result;
            
        }
        
    }
    
    /**
     * Calculates the product of two real numbers a and b
     * @param a The first real number a of ab
     * @param b The second real number b of ab
     * @return  The result c = ab
     */
    public Real_Number multiply(Real_Number a, Real_Number b){
        
        Real_Number result = new Real_Number(a.get_numerator() * b.get_numerator(),
                                     a.get_denominator() * b.get_denominator());
        result = simplify(result);
        
        return result;
        
    }
    
    /**
     * Calculates the quotient of two real numbers a and b
     * @param a The first real number a of a/b
     * @param b The second real number b of a/b
     * @return  The result c = a/b
     */
    public Real_Number divide(Real_Number a, Real_Number b){
        
        Real_Number result = new Real_Number(a.get_numerator() * b.get_denominator(),
                                     a.get_denominator() * b.get_numerator());
        result = simplify(result);
        
        return result;
        
    }
    
    public String divide(String a, String b){
        
        return get_number(divide(new Real_Number(a), new Real_Number(b)));
    }
    
    public String subtract(String a, String b){
        return get_number(subtract(new Real_Number(a), new Real_Number(b)));
    }
    
    
    /**
     * Simplifies a rational number so that the numerator and denominator have
     * no common factors
     * @param n The Real_Number to be simplified
     * @return The simplified number
     */
    public Real_Number simplify(Real_Number n){
        
        int divisor = gcd(n.get_numerator(),n.get_denominator());
        Real_Number result = new Real_Number(n.get_numerator() / divisor , 
                                             n.get_denominator() / divisor);
        
        return result;
        
    }
    
    /**
     * Calculates the greatest common denominator of two integers
     * @param a The first integer
     * @param b The second integer
     * @return 
     */
    public int gcd(int a, int b) {
        BigInteger b1 = BigInteger.valueOf(a);
        BigInteger b2 = BigInteger.valueOf(b);
        BigInteger gcd = b1.gcd(b2);
        return gcd.intValue();
    }
    
    /**
     * 
     * @param n 
     */
    public void print_number(Real_Number n){
        
        if(n.get_denominator() == 1)
            System.out.println(n.get_numerator());
        else
            System.out.println(n.get_numerator() + "/" + n.get_denominator());
        
    }
    
    public String get_number(Real_Number n){
        if(n.get_numerator()==0) return "0"; 
        
        if(Math.abs(n.get_denominator())==Math.abs(n.get_numerator())){
//            System.out.println("its 1" + n.get_numerator() + n.get_denominator());
            return Integer.toString(n.get_numerator()/n.get_denominator());
        }
        else{
            if(n.get_denominator()<0){
                n.set_numerator(n.get_numerator()*-1);
                n.set_denominator(n.get_denominator()*-1);
            }
        }
        if(n.get_denominator()==1) return Integer.toString(n.get_numerator());
        return String.valueOf(n.get_numerator()) + "/" +String.valueOf(n.get_denominator());
        
    }
    
}