package concurrency;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class VolatileVariableDemo {

    private volatile int currentTemp;
    private static final int MAX_ALLOWED_TEMPERATURE = 100;

    public VolatileVariableDemo() {

        currentTemp = 10;

    }

    private void monitorTemperature(){

        Thread thread = new Thread(
                () -> {

                    while (currentTemp<MAX_ALLOWED_TEMPERATURE) {

                        try {
                            Thread.sleep(10);
                            System.out.println(String.format("Current temperature %d is less than %d at %s", currentTemp, MAX_ALLOWED_TEMPERATURE, LocalDateTime.now().toString()));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    System.out.println(String.format("Current temperature %d is more than %d. Stopped monitoring.", currentTemp, MAX_ALLOWED_TEMPERATURE));

                }
        );
        thread.start();

    }

    public void setCurrentTemp(int currentTemp){

        this.currentTemp = currentTemp;

    }

    public int getCurrentTemp(){return currentTemp;}

    public static void main(String args[]) {

        VolatileVariableDemo demo = new VolatileVariableDemo();
        demo.monitorTemperature();

        ScheduledThreadPoolExecutor scheduledPool = new ScheduledThreadPoolExecutor(1);
        scheduledPool.scheduleAtFixedRate(
                () -> {
                    demo.setCurrentTemp(new Random().nextInt(101));
                    System.out.println("Current Temp is "+demo.getCurrentTemp());
                },
                300,
                150,
                TimeUnit.MILLISECONDS);


    }

}
