package Smokers;

public class Index {
    private int index;

    public synchronized int getIndex() {
        return index;
    }

    public synchronized void setIndex(int index) {
        this.index = index;
    }
}
