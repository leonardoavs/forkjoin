/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavssoft.examples.fork.join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leonardo
 */
public class SortTest {
    private static List<Integer> setToOrder;
    
    public SortTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        setToOrder = Utils.generateList();
        System.out.println("List Without Order: " + setToOrder);
        System.out.println("List without Order size: " + setToOrder.size());
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    //@Test
    public void normalMergeSort(){
        TimeCounter mergeSourceTimeCounter = new TimeCounter();
        
        List<? extends Comparable> firstToOrderList = Utils.mergeSort(new ArrayList(setToOrder));
        
        mergeSourceTimeCounter.stop();
        
        System.out.println("Execution time mergeSort: " + mergeSourceTimeCounter.toString());
    
        System.out.println("Orderer List: " + firstToOrderList);
        System.out.println("Orderer List size: " + firstToOrderList.size());
    }
    
    public void insertionSort(){
        TimeCounter insertionSortTimeCounter = new TimeCounter();
        
        Utils.<Integer>insertionSort(setToOrder);
        
        insertionSortTimeCounter.stop();

        System.out.println("Execution time insertionSort: " + insertionSortTimeCounter.toString());        
    }
    
    @Test
    public void mergeSortForkJoin(){
        TimeCounter forkJoinTimerCounter = new TimeCounter();
        
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        
        forkJoinPool.invoke(new MergeSortForkJoinTask<>(new ArrayList<>(setToOrder)));
        
        forkJoinTimerCounter.stop();
        
        System.out.println("Execution time ForkJoin: " + forkJoinTimerCounter.toString());        
    }
}