package src;

public class SudokuChecker {
    
    /**
     * checks to see if there are any duplicate numbers within a sudoku grid
     * @param board: 2d array representing a sudoku grid
     * @return: returns true if there are no duplicates or zeros, false otherwise
     */
    public boolean checkBoard(int[][] board){
        if(checkRows(board)&&checkCols(board)&&checkSquares(board)){
            return true;
        }
        return false;
    }

    /**
     * checks to see if there are any duplicate numbers within a row
     * @param board: 2d array representing a sudoku grid
     * @return: returns true if there are no duplicates or zeros, false otherwise
     */
    public boolean checkRows(int[][] board){
        String rowString;
        int correctRows =0;
        for(int row=0; row < board.length;row++){
            rowString = "";
            for(int col=0; col<board[0].length;col++){
                if(board[row][col] == 0){
                    return false;  //if any zeros are found, return false
                }
                rowString = rowString + Integer.toString(board[row][col]);
            }
            int dupe=0; // 9-dupe is the number of duplicate numbers in the row
            for(int i=1; i <10; i++){
                    if(rowString.contains(Integer.toString(i))){
                        dupe++;
                    }
            }
            if(dupe == 9){
                correctRows++;
            }
            else{
                return false; //if there are any duplicates, return false
            }
        }
        if(correctRows == 9){
            return true;
        }
        return false; // if not all the rows are correct, return false
    }

    /**
     * checks to see if there are any duplicate numbers within a column
     * @param board: 2d array representing a sudoku grid
     * @return: returns true if there are no duplicates or zeros, false otherwise
     */
    public boolean checkCols(int[][] board){
        String colString;
        int correctCols =0;
        for(int col=0; col < board.length;col++){
            colString = "";
            for(int row=0; row<board[0].length;row++){
                if(board[row][col] == 0){
                    return false; //if any zeros are found, return false
                }
                colString = colString + Integer.toString(board[row][col]);
            }
            int dupe=0; // 9-dupe is the number of duplicate numbers in the column
            for(int i=1; i <10; i++){
                    if(colString.contains(Integer.toString(i))){
                        dupe++;
                    }
            }
            if(dupe == 9){
                correctCols++;
            }
            else{
                return false; //if there are any duplicates, return false
            }
        }
        if(correctCols == 9){
            return true;
        }
        return false; // if not all the columns are correct, return false
    }

    /**
     * checks to see if there are duplicates in a 3x3 grid in a sudoku grid
     * @param board: 2d array representing a sudoku grid
     * @return: returns true if there are no duplicates or zeros, otherwise returns false
     */
    public boolean checkSquares(int[][]board){
        int correctSquares = 0;
        for(int rowi=0; rowi<3;rowi++){
            int rowMulti = rowi*3;
            for(int coli=0; coli <3; coli++){
                int colMulti = coli*3;
                String squareString = "";
                //following for loops get the numbers from a square
                for(int row=0;row<3;row++) {
                    for(int col=0; col<3;col++){
                        if(board[row+rowMulti][col+colMulti] == 0){
                            return false;
                        }
                        squareString = squareString + Integer.toString(board[row+rowMulti][col+colMulti]);
                    }   
                }
                //now we check that square
                int dupe=0;
                for(int i=1; i <10; i++){
                    if(squareString.contains(Integer.toString(i))){
                            dupe++;
                        }
                    }
                if(dupe == 9){
                    correctSquares++;
                }else{
                    return false;
                }
            }
        }
        if(correctSquares == 9){
            return true;
        }
        return false;
    }

    /**
     * checks to see if a given number is a valid option in a given location on a sudoku grid
     * @param board: 2d array representing a sudoku grid
     * @param row: index of the row at whcih num is located
     * @param col: index of the column at which num is located
     * @param num: number to be checked
     * @return: returns true if the number has no conflictions within the grid, false if there are conflicts
     */
    public boolean isValidNumber(int[][]board,int row, int col, int num){
        if(checkRowNumber(board, row, num)&&checkColNumber(board, col, num)&&checkSquareNumber(board, row, col, num)){
            return true;
        }
        return false;
    }

    /**
     * checks a given row in a sudoku grid for duplicates of a given number
     * @param board: 2d array representing a sudoku grid
     * @param row: index of row to be checked
     * @param num: number to be checked
     * @return: true if no duplicates are found, false if duplicates are found
     */
    public boolean checkRowNumber(int[][]board,int row, int num){
        for(int col=0;col<board[0].length;col++){
            if(board[row][col] == num){
                return false;
            }
        }
        return true;
    }

    /**
     * checks a given column in a sudoku grid for duplicates of a given number
     * @param board: 2d array representing a sudoku grid
     * @param col: index of column to be checked
     * @param num: number to be checked
     * @return: true if no duplicates are found, false if duplicates are found
     */
    public boolean checkColNumber(int[][]board,int col, int num){
        for(int row=0;row<board[0].length;row++){
            if(board[row][col] == num){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks a particular 3x3 grid to see if there are other instances of a given number
     * @param board: 2d array representing sudoku grid to be checked
     * @param row: row at which the number to be checked is located
     * @param col: column at which the number to be checked is located
     * @param num: number to be checked
     * @return: true if there is only one instance of num within the grid, false if there is at least one duplicate
     */
    public boolean checkSquareNumber(int[][]board,int row,int col, int num){
        int colMulti=0;
        int rowMulti=0;
        if(col <3){colMulti = 0;}
        if(col>=3&&col<6){colMulti=3;}
        if(col>=6&&col<9){colMulti=6;}
        if(row <3){rowMulti = 0;}
        if(row>=3&&row<6){rowMulti=3;}
        if(row>=6&&row<9){rowMulti=6;}
        for(int rowi=0;rowi<3;rowi++){
            for(int coli=0;coli<3;coli++){
                if(board[rowi+rowMulti][coli+colMulti] == num){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * creates a new board object identical to the entered board
     * @param board: board object to be copied
     * @return: new board identical to the parameter
     */
    public Board copyBoard(Board board){
        Board boardCopy = new Board(board);
        return boardCopy;
    }
    /**
     * creates a new 2d array identical to the entered 2d array
     * @param board: 2d array to be copied
     * @return: 2d array identical to the parameter
     */
    public int[][] copyBoard(int[][]board){
        int[][] newBoard = new int[board.length][board[0].length];
        for(int row=0;row<board.length;row++){
            for(int col=0; col<board[0].length;col++){
                newBoard[row][col] = board[row][col];
            }
        }
        return newBoard;
    }

    /**
     * finds the first zero (left to right, top to bottom) in a 2d array
     * @param board: 2d array representative of a sudoku grid
     * @return: int array with row number at index 0 and column number at index 1
     * @throws NoZerosException
     */
    public int [] findZero(int[][]board) throws NoZerosException{
        for(int row=0;row<board.length;row++){
            for(int col=0;col<board[0].length;col++){
                if(board[row][col] == 0){
                    int[] RowCol = new int[]{row,col};
                    return RowCol;
                }
            }
        }
        throw new NoZerosException();
    }
    
}
