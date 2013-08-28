package com.lavssoft.examples.fork.join;

/**
 *
 * @author Leonardo
 */
public class TimeCounter {

    private long initialTime = System.currentTimeMillis();
    private long finishTime = 0;
    
    private long getFinishTime(){
        if(finishTime != 0){
            return finishTime;
        }
        
        return System.currentTimeMillis();
    }
    
    public TimeCounter stop(){
        return stop(TimeCounter.class.getName());
    }    
    
    public TimeCounter stop(String stopDescription){
        finishTime = System.currentTimeMillis();
        System.out.println("Counter " +  stopDescription +" stopped");
        return this;
    }
    
    public double getTimeInMillis(){
        return (getFinishTime() - initialTime);
    }
    
    public double getTimeInSeconds(){
        return getTimeInMillis() / 1000;
    }
    
    public double getTimeInMinutes(){
        return getTimeInSeconds() / 60;
    }
    
    public double getTimeInHours(){
        return getTimeInMinutes() / 60;
    }

    @Override
    public String toString() {
        return getTimeInHours() + " : " + getTimeInMinutes() + " : " + getTimeInSeconds() + " : " + getTimeInMillis();
    }
    
}
