package concurrency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorDemo {

    ScheduledThreadPoolExecutor timerPool;

    public ScheduledThreadPoolExecutorDemo(ScheduledThreadPoolExecutor timerPool){
        this.timerPool = timerPool;
    }

    public void execute(){
        timerPool.schedule(getTask1(), 5, TimeUnit.SECONDS);
        timerPool.execute(getTask2());
        timerPool.scheduleAtFixedRate(getTask3(), 1, 5, TimeUnit.SECONDS);
        timerPool.scheduleWithFixedDelay(getTask4(), 7, 5, TimeUnit.SECONDS);
    }

    public static void main(String args[]) {

        ScheduledThreadPoolExecutorDemo demo = new ScheduledThreadPoolExecutorDemo(new ScheduledThreadPoolExecutor(2));

        demo.execute();

    }

    private Runnable getTask1(){
        return () -> System.out.println(Thread.currentThread().getName()+" Task 1 "+LocalDateTime.now().toString());
    }

    private Runnable getTask2(){
        return () -> System.out.println(Thread.currentThread().getName()+" Task 2 "+LocalDateTime.now().toString());
    }

    private Runnable getTask3(){
        return () -> System.out.println(Thread.currentThread().getName()+" Task 3 "+LocalDateTime.now().toString());
    }

    private Runnable getTask4(){
        return () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" Task 4 "+LocalDateTime.now().toString());
        };
    }


}
