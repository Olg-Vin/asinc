package Smokers;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Servant extends Thread{
    private final Semaphore tableSemaphore;
    private final Index currentIndex;
    int[] index = {0,1,2};
    Random random = new Random();

    public Servant(Semaphore tableSemaphore, Index currentIndex) {
        this.tableSemaphore = tableSemaphore;
        this.currentIndex = currentIndex;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                tableSemaphore.acquire(2);
                currentIndex.setIndex(index[random.nextInt(index.length)]);
                System.out.println("слуга выбрал index = " + currentIndex.getIndex());
                System.out.println("Слуга поставил ингридиенты\n");
//                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
