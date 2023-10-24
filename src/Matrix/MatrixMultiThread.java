package Matrix;

public class MatrixMultiThread {
    public static final int N = 3;
    public static final int NUM_OF_THREADS = N*N;
    public static void main(String args[])
    {
        int row;
        int col;
        int MatrixA[][] = {{1,4,5},{2,5,6},{3,6,7}};
        int MatrixB[][] = {{8,7,6},{5,4,3}, {2,1,0}};
        int MatrixC[][] = new int[N][N];
        int countOfThreads = 0;
        Thread[] treads = new Thread[NUM_OF_THREADS];
        try{
            for(row = 0 ; row < N; row++) {
                for (col = 0 ; col < N; col++ ){
                    treads[countOfThreads] = new Thread(new MultiplicationThreading(row, col,MatrixA, MatrixB, MatrixC));
                    treads[countOfThreads].start();
                    treads[countOfThreads].join();
                    countOfThreads++;
                }
            }
        }
        catch (InterruptedException ie){}
        // printing matrix A
        System.out.println(" Matrix A: ");
        for(row = 0 ; row < N; row++){
            for (col = 0 ; col < N; col++ )
                System.out.print(" "+MatrixA[row][col]);
            System.out.println(); }
        // printing matrix B
        System.out.println(" Matrix B: ");
        for(row = 0 ; row < N; row++) {
            for (col = 0 ; col < N; col++ )
                System.out.print(" "+MatrixB[row][col]);
            System.out.println(); }
        // printing resulting matrix C after multiplication
        System.out.println(" Resulting Matrix C: ");
        for(row = 0 ; row < N; row++) {
            for (col = 0 ; col < N; col++ )
                System.out.print(" "+MatrixC[row][col]);
            System.out.println();
        }
    }
}