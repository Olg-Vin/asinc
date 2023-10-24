package MatrixNew;


import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static final int N = 1551;
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        long endTime;

        long duration;


        int[][] matrixA = createMatrix(0);
        int[][] matrixB = createMatrix(1);
        int[][] matrixC = new int[N][N];

        printMatrixInFile(matrixA, "inputA");
        printMatrixInFile(matrixB, "inputB");

// отразить в отчёте эффективность при 1 - 16 и 1000 потоках

        ExecutorService executor = Executors.newFixedThreadPool(8);
        Future<?>[] futures = new Future[N];

        for (int i = 0; i < N; i++) {
            final int finalI = i;
            Supplier<Function<Integer, Integer>> taskGenerator = () -> {
                return (Integer j) -> {
                    int sum = 0;
                    for (int k = 0; k < N; k++) {
                        sum += matrixA[finalI][k] * matrixB[k][j];
                    }
                    return sum;
                };
            };
            futures[i] = executor.submit(() -> {
                Function<Integer, Integer> task = taskGenerator.get();
                for (int j = 0; j < N; j++) {
                    matrixC[finalI][j] = task.apply(j);
                }
            });
        }

        for (int i = 0; i < N; i++) {
            try {
                futures[i].get(); // Ожидаем завершения всех задач
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        printMatrixInFile(matrixC, "output");

        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        System.out.println(duration);
    }

    public static int[][] createMatrix(int flag){
        int[][] matrix = new int[N][N];
        if (flag == 0){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num = (int) (Math.random()*100);
                    matrix[i][j] = (num/10!=0) ? num:20;
                }
            }
            return matrix;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i==j){
                    matrix[i][j] = 1;
                }
                else {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;

    }
    public static void printMatrix(int[][] matrix){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void printMatrixFromFile(String s){
        String filename = "C:\\Users\\Vinio\\IdeaProjects\\Smokers\\src\\MatrixNew\\" + s;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(" ");
                for (String element : row) {
                    System.out.print(element + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void printMatrixInFile(int[][] matrix, String s){
        String filename = "C:\\Users\\Vinio\\IdeaProjects\\Smokers\\src\\MatrixNew\\" + s;
        try {
            FileWriter writer = new FileWriter(filename);

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    writer.write(matrix[i][j] + " ");
                }
                writer.write("\n");
            }

            writer.close();
            System.out.println("Матрица успешно записана в файл " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при записи матрицы в файл " + filename);
            e.printStackTrace();
        }
    }
}
