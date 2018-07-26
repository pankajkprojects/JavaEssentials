package concurrency;

import java.awt.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskDemo {

    private Timer timer;

    public TimerTaskDemo(int seconds){

        timer = new Timer();
        timer.schedule(new RemindTask(), seconds);

    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            System.out.println(String.format("At the beep, time will be "+Calendar.getInstance().getTime().toString()));
            Toolkit.getDefaultToolkit().beep();
            timer.cancel();
        }
    }

    public static void main(String args[]) {

        TimerTaskDemo taskDemo = new TimerTaskDemo(3000);


    }

}
