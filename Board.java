package src;

import java.util.ArrayList;

public class Board {

    int[][] boardArray;
    ArrayList<Integer> validNumbers = new ArrayList<Integer>();
    ArrayList<Integer> testedNumbers = new ArrayList<Integer>();
    int[] digits = new int[]{1,2,3,4,5,6,7,8,9};
    SudokuChecker check = new SudokuChecker();

    public Board(int[][]board){
        this.boardArray = board;
    }
    public Board(Board board){
        boardArray = board.getArray();
    }

    public int[][] getArray(){
        return boardArray;
    }

    /**
     * finds all the valid numbers to replace the next zero on a board
     * @return: ArrayList of valid numbers
     * @throws NoZerosException: if no zeros are found, the board is solved.
     */
    public ArrayList<Integer> getValidNumbers() throws NoZerosException{
        validNumbers.clear();
        int [] rowCol = check.findZero(getArray());
        for(int x:digits){
            if(check.isValidNumber(getArray(),rowCol[0],rowCol[1],x)&&!(testedNumbers.contains(x))){
                validNumbers.add(x);
            }
        }
        return validNumbers;
    }

    /**updates testedNums and validNums instance variables 
    *and returns a new board with desired number filling in the first zero found
    *@param num: number that is to be tested
    *@return: new board with the first zero found replaced with the tested number
    */
    public Board testNum(Integer num){
        testedNumbers.add(num);
        validNumbers.remove(num);
        int[][] newBoardArray = check.copyBoard(boardArray);
        int [] rowCol = findZero(newBoardArray);
        newBoardArray[rowCol[0]][rowCol[1]] = num;
        Board newBoard = new Board(newBoardArray);
        return newBoard;
    }

    public int length(){
        return boardArray.length;
    }

    /**
     * finds the first instance of zero in the sudoku board that called the function
     * @param board: 2d array representing a sudoku grid
     * @return: an int array containing the row number at index 0 and column number at index 1
     */
    public int [] findZero(){
        for(int row=0;row<boardArray.length;row++){
            for(int col=0;col<boardArray[0].length;col++){
                if(boardArray[row][col] == 0){
                    int[] RowCol = new int[]{row,col};
                    return RowCol;
                }
            }
        }
        int[] none = new int[]{100,100};
        return none;
    }

    /**
     * finds the first instance of zero in a sudoku board represented in a 2d array
     * @param board: 2d array representing a sudoku grid
     * @return: an int array containing the row number at index 0 and column number at index 1
     */
    public int [] findZero(int[][]board){
        for(int row=0;row<board.length;row++){
            for(int col=0;col<board[0].length;col++){
                if(boardArray[row][col] == 0){
                    int[] RowCol = new int[]{row,col};
                    return RowCol;
                }
            }
        }
        int[] none = new int[]{100,100};
        return none;
    }


    /**
     * returns a readable sudoku grid
     * @return: a 9x9 grid of numbers
     */
    public String toString(){
        String arrayString = "";
        int charCount=0;
        for(int row=0;row<boardArray.length;row++){
            for(int col=0;col<boardArray[0].length;col++){
                arrayString = arrayString + boardArray[row][col];
                charCount++;
                if(charCount==9){
                    arrayString = arrayString + "\n";
                    charCount = 0;
                }
            }
        }
        return arrayString;
    }

    public int getFirstThree(){
        return boardArray[0][0] *100 + boardArray[0][1] *10 + boardArray[0][2];
    }

}
