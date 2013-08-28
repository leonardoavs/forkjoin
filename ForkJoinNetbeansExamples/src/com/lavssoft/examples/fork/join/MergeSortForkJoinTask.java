package com.lavssoft.examples.fork.join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author Leonardo
 */
public class MergeSortForkJoinTask<T extends Comparable<T>> extends RecursiveTask<List<T>>{
    private List<T> set = null;
    
    public MergeSortForkJoinTask(List<T> set){
        this.set = set;
    }
    
    @Override
    protected List<T> compute() {
        if(set.size() <= Constants.THRESHOLD){
            for (int i = 1; i < set.size(); i++) {
                T valueToInsert = set.get(i);
                int holePos = i;

                while(holePos > 0 && valueToInsert.compareTo(set.get(holePos - 1)) < 0){
                    set.set(holePos, set.get(holePos -1));
                    holePos = holePos -1;
                }

                set.set(holePos, valueToInsert);
            }

            return set;
        }
        else{
            int midle = set.size() / 2;
            
            MergeSortForkJoinTask<T> left = new MergeSortForkJoinTask<>(new ArrayList<>(set.subList(0, midle)));
            MergeSortForkJoinTask<T> rigth = new MergeSortForkJoinTask<>(new ArrayList<>(set.subList(midle, set.size())));

            invokeAll(left, rigth);
            
//            left.fork();
//            
//            List<T> listOne = rigth.compute();
//
//            List<T> listTwo = left.join();

            return merge(left.join(), rigth.join());
        }
    }

    private List<T> merge(List<T> listOne, List<T> listTwo) {
        List<T> newList = new ArrayList<>();

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
