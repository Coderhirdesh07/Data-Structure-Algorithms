package DSA;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnderstandingRecursion {
    public static void main(String[] args) throws IOException {

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        String input = sc.readLine();
        int n = Integer.parseInt(input);
        char[][] board = new char[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(board[i],'.');
        }
//        List<List<String>> ans = new ArrayList<>();
//
//        NQueenProblem(0,board,n,ans);
//        System.out.println("This is the input " + input);

    }
    // TODO
//    public static void NQueenProblem(int col,char[][] board,int n,List<List<String>> ans){
//        if(col==n){
//            ans.add(construct(board,n));
//            return;
//        }
//        for(int row = 0;row<n;row++){
//            if(isValidPosition(board,row,col)){
//                board[row][col] = 'Q';
//                NQueenProblem(col+1,board,n,ans);
//                board[row][col] = '.';
//            }
//        }
//    }
//    public static List<String> construct(char[][] board,int n){
//        List<String> ans = new ArrayList<>();
//        for(int i=0;i<n;i++){
//            String res = new String(board[i]);
//            ans.add(res);
//        }
//        return ans;
//
//    }
//    public static  boolean isValidPosition(char[][] board,int row,int col){
//        int duprow = row;
//        int dupcol = col;
//        while(row>=0 && col>=0){
//            if(board[row][col] == 'Q'){
//                row--;
//                col--;
//            }
//        }
//        row = duprow;
//        col = dupcol;
//        while(col>=0){
//            if(board[row][col]=='Q'){
//                return false;
//            }
//            col--;
//        }
//        row = duprow;
//        col = dupcol;
//        while(row< board.length && col>=0){
//            if(board[row][col]=='Q'){
//                return false;
//            }
//            col--;
//            row++;
//        }
//        return true;
//
//    }

    // TODO THESE PROBLEMS

//    public static void RatInAMaze(){
//
//    }
//    public static void MColoringProblem(){
//
//    }
//    public static boolean SudokuSolver(char[][] board){
//        for(int i=0;i<board.length;i++){
//            for(int j=0;j<board[0].length;j++){
//                if(board[i][j]=='.'){
//                    for(char c = '1';c<='9';c++){
//                        if(isPositionValid(i,j,board,c)){
//                            board[i][j] = c;
//
//                            if(SudokuSolver(board)==true){
//                                return true;
//                            }
//                            else board[i][j] = '.';
//                        }
//                        return false;
//                    }
//                    return true;
//                }
//            }
//        }
//
//    }
//    public static boolean isPositionValid(int row,int col,char[][] board,char c){
//        for(int i=0;i<9;i++){
//            if(board[i][col]==c){
//                return false;
//            }
//            if(board[row][i]==c){
//                return false;
//            }
//            if(board[3*(row/3)*i/3][3*(col/3)*i%3]==c){
//                return false;
//            }
//        }
//        return true;
//    }

}
