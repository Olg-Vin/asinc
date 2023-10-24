package Matrix;

public class MultiplicationThreading implements Runnable
{
    private int row;
    private int col;
    private int MatrixA[][];
    private int MatrixB[][];
    private int MatrixC[][];
    public MultiplicationThreading(int row, int col,int MatrixA[][],
                                   int MatrixB[][], int MatrixC[][] )
    {
        this.row = row;
        this.col = col;
        this.MatrixA = MatrixA;
        this.MatrixB = MatrixB;
        this.MatrixC = MatrixC;
    }
    @Override
    public void run()
    {
        // multiplication perform here
        for(int k = 0; k < MatrixB.length; k++)
            MatrixC[row][col] += MatrixA[row][k] * MatrixB[k][col];
    } }

