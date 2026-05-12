package DSA;

import java.util.Scanner;

public class MultiDimensionalArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] array = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] = sc.nextInt();
            }
        }
          diagonalSwap(array, n, m);
//        int diagonal_sum = diagonalSum(array,3,3);
//        System.out.println(diagonal_sum);
//        rotateArray(array,3,3);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(array[i][j] + " ");
            }
        }
//            System.out.println();
//        for(int i=0;i<n;i++){
//            movementPrinting(array,i,0, n,m);
//        }
//        for(int j=1;j<m;j++){
//            movementPrinting(array,n-1,j,n,m);
//        }

            // 1 2 3      10 11 12
            // 4 5 6      13 14 15
            // 7 8 9      16 17 18

        }
//        public static int diagonalSum ( int[][] matrix, int n, int m){
//            int sum = 0;
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    if (i == j || i + j == n - 1) {
//                        sum += matrix[i][j];
//                    }
//                }
//            }
//            return sum;
//        }
        public static void diagonalSwap ( int[][] matrix, int n, int m){
            int i = 0;
            int j = 0;
            while (i < n && j < m) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][m - j - 1];
                matrix[i][m - j - 1] = temp;
                i++;
                j++;
            }

        }

//        public static void rotateArray ( int[][] matrix, int n, int m){
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    if (i != j && i < j) {
//                        int temp = matrix[i][j];
//                        matrix[i][j] = matrix[j][i];
//                        matrix[j][i] = temp;
//                    }
//                }
//            }
//            return;
//        }
//        public static void movementPrinting ( int[][] matrix, int row, int col, int n, int m){
//            while (row >= 0 && col < m) {
//                System.out.print(matrix[row][col] + " ");
//                row--;
//                col++;
//            }
//            System.out.println();
//        }
//    public static int[][] productOfMatrix(int[][] matrixA,int[][] matrixB){
//        int n = matrixA.length;
//        int m = matrixA[0].length;
//        int[][] ans = new int[n][m];
//
//         for(int i=0;i<n;i++){
//             for(int j=0;j<m;j++){
//                 ans[i][j] = product(matrixA,matrixB,i,j);
//             }
//         }
//        return ans;
//
//    }
//    public static int product(int[][] a,int[][] b,int row,int col){
//     for(int )
//
//
//    }
    }

