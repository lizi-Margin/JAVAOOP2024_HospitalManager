import java.io.*;

public class Main {
	public static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in),32768));
	public static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static int nextInt() throws IOException{ in.nextToken(); return (int)in.nval; }






	public static void main(String[] args) throws IOException{
        int N = nextInt();
        if (N < 1 || N > 50) {
            out.println("Input data error");
            out.close();
            return;
        }
        int[][] matrix = generateMatrix(N);
        printMatrix(matrix,N);

        out.close();
	}


    public static int[][] generateMatrix(int N) {
        int[][] matrix = new int[N][N];
        int num = 1;
        int topRow = 0, bottomRow = N - 1, leftCol = 0, rightCol = N - 1;

        while (num <= N * N) {
            for (int i = topRow; i <= bottomRow; i++) {
            matrix[i][rightCol] = num++;
            }
            rightCol--;

            for (int i = rightCol; i >= leftCol; i--) {
            matrix[bottomRow][i] = num++;
            }
            bottomRow--;
            for (int i = bottomRow; i >= topRow; i--) {
            matrix[i][leftCol] = num++;
            }
            leftCol++;

            for (int i = leftCol; i <= rightCol; i++) {
            matrix[topRow][i] = num++;
            }
            topRow++;
        }

        return matrix;
        }

        public static void printMatrix(int[][] matrix,int N) {
            for (int[] row : matrix) {
                StringBuilder sb = new StringBuilder();
                for (int i =0;i<N-1;i++) {
                    sb.append(row[i]);
                    sb.append(' ');
                }
                sb.append(row[N-1]);
                out.println(sb.toString());
            }   
        }
}
