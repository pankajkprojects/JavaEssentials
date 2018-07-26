package concurrency;

import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSwapDemo {

    AtomicInteger value;

    public CompareAndSwapDemo(int intValue){
        this.value = new AtomicInteger(intValue);
    }

    public void incrementValue(int increment){

        System.out.println(getPrefix("INC("+increment+"):", value.addAndGet(increment)));

    }

    public void decrementValue(int decrement){

        decrement = -1*decrement;
        System.out.println(getPrefix("DEC("+decrement+"):", value.addAndGet(decrement)));

    }

    public boolean compareAndSetValue(int oldValue, int newValue){

        boolean isSuccess = value.compareAndSet(oldValue, newValue);
        System.out.println(getPrefix("CAS("+oldValue+", "+newValue+"):", value.get()));
        return isSuccess;

    }

    private String getPrefix(String prefix, int val){

        return String.format("Thread: %s, %s CURR: %d", Thread.currentThread().getName(), prefix, val);

    }

    public int getValue(){
        return value.get();
    }

    public static void main(String args[]) throws InterruptedException {

        CompareAndSwapDemo casDemo = new CompareAndSwapDemo(10);
        int BOUND_RANDOM_NUMBER = 10;

        ScheduledThreadPoolExecutor scheduledPool = new ScheduledThreadPoolExecutor(3);
        scheduledPool.scheduleAtFixedRate(() -> {casDemo.decrementValue(new Random().nextInt(BOUND_RANDOM_NUMBER));}, 350, 20, TimeUnit.MILLISECONDS);
        scheduledPool.scheduleAtFixedRate(() -> {casDemo.incrementValue(new Random().nextInt(BOUND_RANDOM_NUMBER));}, 102, 12, TimeUnit.MILLISECONDS);
        scheduledPool.scheduleAtFixedRate(() -> {casDemo.compareAndSetValue(casDemo.getValue(), new Random().nextInt(BOUND_RANDOM_NUMBER));}, 102, 12, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            scheduledPool.shutdownNow();
        }

        if(scheduledPool.isTerminating())
            System.out.println("Scheduled Thread Pool is terminating");
        if(scheduledPool.isTerminated())
            System.out.println("Scheduled Thread Pool is terminated");
        if(scheduledPool.isShutdown())
            System.out.println("Scheduled Thread Pool is shut down");

        while (!scheduledPool.awaitTermination(60, TimeUnit.SECONDS))
            System.out.println("Awaiting termination of Scheduled Thread Pool");

    }


}
