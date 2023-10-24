package Smokers;

import UI.CirclePanel;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public class Smoker extends Thread{
    private final String ingredient;
    private final Semaphore tableSemaphore;
    private final int index;
    private final CirclePanel circle;

    private final Index currentIndex;

    public Smoker(String ingredient, Semaphore tableSemaphore, int index, CirclePanel circle,
                  Index currentIndex) {
        this.ingredient = ingredient;
        this.tableSemaphore = tableSemaphore;
        this.index = index;
        this.circle = circle;
        this.currentIndex = currentIndex;
    }
// в отчёте указать недостаток - про активное ожидание
    @Override
    public void run() {
        System.out.println("Thread is created");
        while (!isInterrupted()) {
            try {
                if (this.index == currentIndex.getIndex()){
                    System.out.println("курильщик " + index + " освобождает стол");
                    tableSemaphore.release(2);
                    SwingUtilities.invokeLater(() -> {
                        circle.changeColor();
                        circle.repaint();
                    });

                    System.out.println("Курильщик " + index + " крутит и крутит сигарету " + ingredient);
                    Thread.sleep(5_000);
                    System.out.println("Курильщик " + index + " накурился " + ingredient + "\n");

                    SwingUtilities.invokeLater(() -> {
                        circle.changeColor();
                        circle.repaint();
                    });
                }
                else {
                    Thread.yield();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
