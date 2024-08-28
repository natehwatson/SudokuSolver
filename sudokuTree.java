package src;
import java.util.Stack;
import java.util.*;

public class sudokuTree {
    
    int[] digits = new int[]{1,2,3,4,5,6,7,8,9};
    SudokuChecker check = new SudokuChecker();
    Stack<Integer> numStack = new Stack<Integer>();
    int lastNum;

    /**
     * function solves an incomplete sudoku board
     * @param board: incomplete board to solve
     * @return: a new, solved board
     */
    public Board solve(Board board){
        Board curBoard;
        ArrayList<Integer> validNums;
        curBoard = check.copyBoard(board);
        Stack<Board> boardStack = new Stack<Board>();
        boardStack.push(curBoard);
        while(!check.checkBoard(curBoard.getArray())){
            try{
            validNums = curBoard.getValidNumbers();
            for(int x:validNums){
                numStack.add(x); //add all possible solutions for the next square to a stack of numbers
            }
            while(validNums.isEmpty()){
                boardStack.pop();
                validNums = boardStack.peek().getValidNumbers();
            }
            curBoard = boardStack.peek();
            curBoard = curBoard.testNum(numStack.pop());
            boardStack.push(curBoard);
            }catch(NoZerosException e){}
            
        }
        return curBoard;
    }
}

