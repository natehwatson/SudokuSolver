package src;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class SudokuReader{

    int[][] boardArray = new int[9][9];
    Board board;
    String bigString = "";
    Queue<Integer> q = new LinkedList<Integer>();
    Board solved;

    /**
     * takes a board from the big string of boards, and creates a 2d array to be solved
     */
    public void populateBoard(){
        String boardString;
        boardString = bigString.substring(0,81);
        bigString = bigString.replaceFirst(boardString, "");
        char [] boardSequence = boardString.toCharArray();
        int i = 0;
        for(int row=0; row < boardArray.length; row++){
            for(int col=0; col < boardArray[0].length; col++){
                boardArray[row][col] = boardSequence[i] - '0';
                i++;
            }
        }
        board = new Board(boardArray);
    }

    /**
     * calls the solve function
     * @return: true if the board is solved correctly
     */
    public boolean checkBoard(){
        SudokuChecker check = new SudokuChecker();
        return check.checkBoard(solve().getArray());
    }
    
    /**
     * reads a file of sudoku grids and converts the puzzles into one long string to be parsed
     */
    public void readFile(){
        try{
        FileInputStream file = new FileInputStream("sudokus.txt");
        Scanner in = new Scanner(file);
        
        while(in.hasNextLine()){
            String line = in.nextLine();
            if(line.contains("Grid")){
                continue;
            }
            bigString = bigString + line;
        }
        in.close();
        }catch(FileNotFoundException e){}
    }

    public Board solve(){
        sudokuTree sudTree = new sudokuTree();
        solved = sudTree.solve(board);
        return solved;
    }
    /**
     * prints the solved puzzles in a file "Answers.txt"
     * also prints in the console the sum of all the 3 digit numbers in the top left of each grid
     */
    public void printToFile(){
        try{
        PrintWriter out = new PrintWriter("Answers.txt");

        int gridNum=0;
        int sum=0;
        while(!(bigString.isEmpty())){
            gridNum++;
            populateBoard();
            solve();
            sum += solved.getFirstThree();
            out.println("Grid "+ gridNum);
            out.print(solved.toString());  
        }
        out.close();
        System.out.println(sum);
        }catch(FileNotFoundException e ){}
    }
    
    public static void main(String[] args){
        SudokuReader sudRead = new SudokuReader();
        sudRead.readFile();
        sudRead.printToFile();
        
    }

}