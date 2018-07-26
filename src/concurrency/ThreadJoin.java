package concurrency;

import java.time.LocalDateTime;

public class ThreadJoin {

    private Thread backgroundThread;

    public ThreadJoin(){

        backgroundThread = new Thread(() -> {
            System.out.println("STARTED Background Task "+Thread.currentThread().getName()+" "+LocalDateTime.now().toString());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("FINISHED Background Task "+Thread.currentThread().getName()+" "+LocalDateTime.now().toString());
        });

    }

    public void joinDemo(){

        backgroundThread.start();
        System.out.println("Started background thread. Waiting for it to finish...");
        try {
            backgroundThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Wait is over... background thread finished.");
        }

    }

    public static void main(String args[]) {

        ThreadJoin threadJoin = new ThreadJoin();
        threadJoin.joinDemo();

    }


}
