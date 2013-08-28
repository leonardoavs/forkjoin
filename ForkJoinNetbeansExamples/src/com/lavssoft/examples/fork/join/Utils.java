package com.lavssoft.examples.fork.join;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Leonardo
 */
public class Utils {

    public static List<? extends Comparable> mergeSort(List<Comparable> set){
        
        if(set.size() <= Constants.THRESHOLD){
            for (int i = 1; i < set.size(); i++) {
                Comparable valueToInsert = set.get(i);
                int holePos = i;

                while(holePos > 0 && valueToInsert.compareTo(set.get(holePos - 1)) < 0){
                    set.set(holePos, (Comparable)set.get(holePos -1));
                    holePos = holePos -1;
                }

                set.set(holePos, valueToInsert);

            }

            return set;
        }
        else{
            int midle = set.size() / 2;
            List<? extends Comparable> listOne = mergeSort(new ArrayList<>(set.subList(0, midle)));
            List<? extends Comparable> listTwo = mergeSort(new ArrayList<>(set.subList(midle, set.size())));

            List<Comparable> newList = new ArrayList<>();

            while (listOne.size() > 0) {
                Comparable element1 = listOne.get(0);

                while (listTwo.size() > 0) {
                    Comparable element2 = listTwo.get(0);
                    int compareResult = element1.compareTo(element2);

                    if (compareResult > 0) {
                        newList.add(listTwo.remove(0));
                    } else if (compareResult == 0) {
                        listOne.remove(0);
                        newList.add(listTwo.remove(0));
                    } else {
                        newList.add(listOne.remove(0));
                    }

                    if (listOne.size() > 0) {
                        element1 = listOne.get(0);
                    } else {
                        while (listTwo.size() > 0) {
                            newList.add(listTwo.remove(0));
                        }
                    }
                }

                if (listTwo.isEmpty()) {
                    while (listOne.size() > 0) {
                        newList.add(listOne.remove(0));
                    }
                }
            }

            return newList;
        }
    }    
    
    public static <T extends Comparable<T>> List<T> insertionSort(List<T> set) {
        for (int i = 1; i < set.size(); i++) {
            T valueToInsert = set.get(i);
            int holePos = i;
            
            while(holePos > 0 && valueToInsert.compareTo(set.get(holePos - 1)) < 0){
                set.set(holePos, (T)set.get(holePos -1));
                holePos = holePos -1;
            }
            
            set.set(holePos, valueToInsert);
            
        }
        
        return set;
    }    
    
    public final static <T extends Comparable<T>> List<T> generateList(){
        Map<Comparable,Comparable> numbers = new HashMap<>();
        
        while (numbers.size() < Constants.COLLECTION_SIZE) {
            
            int generatedNumber = 1 + (int)(Math.random()* 1000_000_000 ); 
            
            if(!numbers.containsKey(generatedNumber)){
                numbers.put(generatedNumber, generatedNumber);                
            }
        }
        
        return new ArrayList(numbers.values());
    }
}
