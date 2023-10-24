package Smokers;

import UI.CirclePanel;
import UI.CircleWindow;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        CircleWindow window = new CircleWindow();
        CirclePanel[] circles = window.getCircles();

        Index currentIndex = new Index();

        Semaphore tableSemaphore = new Semaphore(2);


        Smoker smoker1 = new Smoker("Табак", tableSemaphore, 0, circles[0], currentIndex);
        Smoker smoker2 = new Smoker("Бумага", tableSemaphore,1, circles[1], currentIndex);
        Smoker smoker3 = new Smoker("Спички", tableSemaphore,2, circles[2], currentIndex);
        Servant servant = new Servant(tableSemaphore, currentIndex);

        servant.start();
//        Thread.sleep(1000);
        smoker1.start();
        smoker2.start();
        smoker3.start();
    }
}
