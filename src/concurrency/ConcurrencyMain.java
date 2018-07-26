package concurrency;



public class ConcurrencyMain {

    public static void main(String args[]) {

        MyThread myThread = new MyThread();
        myThread.start();

    }

}

class MyThread extends Thread {

    int count;

    @Override
    public void run() {

        count=0;

        while(1>0) {

            try {
                Thread.sleep(10);
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(count>100) {
                System.out.println("My Thread instance is null");
                break;
            }

            System.out.println("My Thread instance running");

        }

    }

}

class MyRunnable implements Runnable {

    @Override
    public void run() {

        System.out.println("My Runnable instance running");

    }

}
