package com.lavssoft.examples.fork.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Lavs
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Comparable> ordenar = new ArrayList<>();
        
        for (int i = 0; i < 20; i++) {
            ordenar.add(1 + (int)(Math.random()* 200));
        }
        
        System.out.println(ordenar);
        
        System.out.println(ordenar(ordenar));
    }
    
    private final static int LIMITE = 1;
    
    public static List<? extends Comparable> ordenar(List<? extends Comparable> order){
        if(order.size() == 1 || order.isEmpty()){
            return order;
        }
        else{
            if(order.size() <= LIMITE){
                Collections.sort(order);
                return order;
            }
            else{
                int mitad = order.size() / 2;
                List<? extends Comparable> lista1 = ordenar(new ArrayList<>(order.subList(0, mitad)));
                List<? extends Comparable> lista2 = ordenar(new ArrayList<>(order.subList(mitad, order.size())));
                
                List<Comparable> newList = new ArrayList<>();
                
                Comparable elemento1 = null;
                Comparable elemento2 = null;
                
                while( lista1.size() > 0 ){
                    elemento1 = lista1.get(0);
                    
                    while(lista2.size() > 0){
                        elemento2 = lista2.get(0);
                        int compareResult = elemento1.compareTo(elemento2);
                        
                        if( compareResult > 0){
                            newList.add(lista2.remove(0));
                        }
                        else if( compareResult == 0){
                            lista1.remove(0);
                            newList.add(lista2.remove(0));
                        }
                        else{
                            newList.add(lista1.remove(0));
                        }
                    }
                }
                
                return newList;
                
            }
        }
    }
}
