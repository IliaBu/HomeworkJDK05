package gb.jdk;

import java.util.Random;

public class Philosopher extends Thread{

    public Thread philosopherThread;
    private static final Object plate = new Object();
    private int eatedcount;
    private final int speed;

    public Philosopher(String name) {
        this.philosopherThread = new Thread(this, name);
        eatedcount = 0;
        speed = new Random().nextInt(1, 5) * 1000;
    }

    public static Philosopher createAndStart(String name) {
        Philosopher philosopher = new Philosopher(name);
        philosopher.philosopherThread.start();
        return philosopher;
    }

    @Override
    public void run() {
        println("Философ " + this.philosopherThread.getName() + " присоединился к обеду.", Colors.MAGENTA);

        while (true) {
            synchronized (plate) {
                int eat = eatedcount+1;
                println("Философ " + this.philosopherThread.getName() + " кушает спагетти " + eat + "-й раз.", Colors.BLUE);
                eatedcount++;
                try {
                    Thread.sleep(400);
                    if (eatedcount == 3){
                        plate.notifyAll();
                        break;
                    }
                    println("Философ " + this.philosopherThread.getName() + " размышляет " + speed + " мс", Colors.YELLOW);
                    Thread.sleep(speed);
                    plate.notifyAll();
                    plate.wait();

                } catch (InterruptedException e) {
                    println("Философ " + this.philosopherThread.getName() + " неожиданно прервал трапезу.", Colors.RED);
                }
            }
        }
        println("Философ "+ this.philosopherThread.getName() + " насылится.", Colors.GREEN);
    }

    public static void println(String message, Enum displayColor) {
        System.out.println(displayColor + message + Colors.RESET);
    }

}
