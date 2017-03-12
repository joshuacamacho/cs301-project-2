/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs301.project.pkg2;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Josh
 */
public class Polynomial {
    ArrayList<Double> p;
    public Polynomial(ArrayList<Double> input){
        p = new ArrayList<Double>();
        for(int i=0; i<input.size(); i++){
            p.add(input.get(i));
        }
    }
    public Polynomial(){
        p = new ArrayList<Double>();
        p.add(0.0);
    }
    
    public void setPolynomial(ArrayList<Double> input){
        p = new ArrayList<Double>();
        for(int i=0; i<input.size(); i++){
            p.add(input.get(i));
        }
    }
    public void addToSelf(Polynomial a){
        p = add(p,a.getList()).getList();
    }
    public Polynomial add(ArrayList<Double> a, ArrayList<Double>b){
        int size;
        boolean alargest;
        ArrayList<Double> array = new ArrayList<Double>();
        if(a.size()<= b.size()){
            alargest=false;
            size=a.size();
        }else{
            alargest=true;
            size=b.size();
        }
        for(int i=0; i<size; i++){
            array.add(a.get(i)+b.get(i));
        }
        if(alargest){
          for(int i=size; i<a.size(); i++){
            array.add(a.get(i));
          }  
        }else{
            for(int i=size; i<b.size(); i++){
            array.add(b.get(i));
          } 
        }
        return new Polynomial(array);
        
    }
    
    public void print(){
        for(int i=p.size()-1; i>=0; i--){
            if(i!=p.size()-1 && p.get(i)>0){
                System.out.print("+");
            }
            if(p.get(i)==0){
                
            }else{
                System.out.printf("%.4f",p.get(i));
            
                if(i>1) System.out.print("x^"+i);
                if(i==1) System.out.print("x");
            }
            
        }
    }
    public void multiplySelf(double c){
        for(int i=0; i<p.size(); i++){
            p.set(i, p.get(i)*c);
        }
        
    }
    public void multiplySelf(ArrayList<Double> a){
        p = multiply(p,a).getList();
    }
    public Polynomial multiply(ArrayList<Double> a){
        return multiply(p,a);
    }
    public ArrayList<Double> getList(){
        return p;
    }
    public Polynomial multiply(ArrayList<Double> a, ArrayList<Double> b){
        int newSize;
        boolean alargest;
        if(a.size()>=b.size()){
            alargest=true;
            newSize=a.size();
        }else{
            alargest=false;
            newSize=b.size();
        }
        
        ArrayList<Double> newList = new ArrayList<Double>(Collections.nCopies(newSize+1, 0.0));
        
        if(alargest){
            for(int i=0; i<b.size(); i++){
                int newPos=i;
                for(int j=0; j<a.size(); j++){
                    
                    newList.set(newPos, newList.get(newPos) + b.get(i)*a.get(j));
                    newPos++;
                }
            }
        }else{
            for(int i=0; i<a.size(); i++){
                int newPos=i;
                for(int j=0; j<b.size(); j++){
                    
                    newList.set(newPos, newList.get(newPos) + a.get(i)*b.get(j));
                    newPos++;
                }
            }
        }
        
        return new Polynomial(newList);
    }
}
