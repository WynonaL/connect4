//Wynona Lam 1/19/22 Class that mimics a game of connect4 with ASCII art

import java.util.Scanner;
public class Board {
	
	private char[][] theBoard = new char[6][7];
	private int turnCount = 0; 
	private char winner = 'a';
	
	/**
	 * Constructs a new empty connect 4 game board with player X being the first player
	 * and player 'O' being the second player.
	 */
	public Board() {
		
		for(int i = 0; i <theBoard.length; i++) {
			for(int j = 0; j <theBoard[i].length; j++) {
				theBoard[i][j] = 'e';
			}
		}
	}

	/**
	 * Gets the current player (either 'X' or 'O')
	 * 
	 * @return the current player
	 */
	public char currentPlayer() {
		if(turnCount % 2 == 0) {
			return 'X';
		}else {
			return 'O';
		}
	}

	/**
	 * The current player tries to make a move in a given column.  If the move
	 * is valid, the board is updated and {@code true} is returned.  If the move
	 * is invalid (not a valid column number of the column is already full)
	 * the board remains unchanged and {@code false} is returned.  If the game is
	 * already over, a RuntimeException is thrown instead.
	 * 
	 * @param column the column in which to make a move.  For the move to be valid,
	 * the column value must an {@code int} between 1 and 7 inclusive, and
	 * there must have been fewer than 6 moves already made in the given column.
	 * @return {@code true} if the move is valid and false if it is not valid.
	 * @throws RuntimeException if the game is already over.
	 */
	public boolean play(int column) {
		
		//lets current player play until move is valid so make
		//so don't update turnCount if it is false

		if(this.gameOver()) { //wrong?
			throw new RuntimeException("Game is already over.");
		}
		
		if(column <=0 || column > 7) { //invalid column
			return false;
		}else { //is valid column
			//check if full
			for(int i = theBoard.length-1; i >= 0; i--) { 
				if(theBoard[i][column-1] == 'e') {
					theBoard[i][column-1] = currentPlayer();
					turnCount++;
					return true;
				}
			}
			//if it is full
			//return false
			return false;
		}
	}

	

	/**
	 * Determines if the game is already over.
	 * 
	 * @return {@code true} if the game is over and {@code false} if it is not over.
	 */
	public boolean gameOver() {
		//throw new RuntimeException("Not implemented");
		
		//runs wrong. says win when board is empty because they are all e's -corrected?
		//still runs wrong
		
		//horizontal 4 in a row
		for(int i = 0; i<theBoard.length; i++){
			for (int j = 0; j < theBoard[0].length - 3; j++){
				if(theBoard[i][j] == theBoard[i][j+1] && theBoard[i][j] == theBoard[i][j+2] && theBoard[i][j] == theBoard[i][j+3] && theBoard[i][j] != 'e' ){
					winner = theBoard[i][j];
					return true;
				}
			}			
		}
		
		//vertical 4 in a row 
		for(int i = 0; i<theBoard.length - 3; i++){
			for (int j = 0; j < theBoard[0].length; j++){
				if(theBoard[i][j] == theBoard[i+1][j] && theBoard[i][j] == theBoard[i+2][j] && theBoard[i][j] == theBoard[i+3][j] && theBoard[i][j] != 'e' ){
					winner = theBoard[i][j];
					return true;
				}
			}			
		}
		
		
		//(+) diagonal 4 in row
		for(int i = 3; i < theBoard.length; i++){
			for(int j = 0; j < theBoard[0].length - 3; j++){
				if(theBoard[i][j] == theBoard[i-1][j+1] && theBoard[i][j] == theBoard[i-2][j+2] && theBoard[i][j] == theBoard[i-3][j+3] && theBoard[i][j] != 'e' ){
					winner = theBoard[i][j];
					return true;
				}
			}
		}
		
		//(-) diagonal 4 in row
		for(int i = 0; i < theBoard.length - 3; i++){
			for(int j = 0; j < theBoard[0].length - 3; j++){
				if(theBoard[i][j] == theBoard[i+1][j+1] && theBoard[i][j] == theBoard[i+2][j+2] && theBoard[i][j] == theBoard[i+3][j+3] && theBoard[i][j] != 'e' ){
					winner = theBoard[i][j];
					return true;
				}
			}
		}
    
		//the whole board is filled and is a draw 
		for(int i = 0; i< theBoard.length; i++) {
			for(int j = 0; j < theBoard[i].length; j++) {
				if(theBoard[i][j] == 'e') {  
					return false;
				}
			}
		}
		return true;
		
	}

	/**
	 * Determine the winner (assuming the game is over).
	 * 
	 * @return {@code 'X'} or {@code 'O'} if either player has won and {@code ' '}
	 * if the game is not over or if the game is a draw.
	 */
	public char winner() {
		
		if(winner == 'O' || winner == 'X') {
			return winner;
		}else {
			return ' ';
		}
	}

	/**
	 * Construct a string describing the state of the game.  You are not required to implement
	 * this method, however, implementing this method will make debugging much easier so
	 * you are encouraged to implement this to return a string that looks like the game board.
	 * 
	 * @return a string representation of the game
	 */
	public String toString() {
		
		String s = "";
		
		for (int row = 0; row < theBoard.length; row++){
			s += "|";
			for (int col = 0; col < theBoard[0].length; col++){
				s += theBoard[row][col];
				s += "|";
			}
			System.out.println();
			s += "\n";
			s += "--------------- \n";
		}
		return s;
	}
	

	/*
	 * This main can be used to play a game of Connect 4.  In order to display the game board
	 * you must have defined the toString method.
	 */
	public static void main(String[] args) {
    Scanner in = new Scanner(System.in);  // Create a Scanner object
   
		Board b = new Board();
		while (!b.gameOver()) {
			boolean legalMove = false;
			while (!legalMove) {
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
				System.out.println(b);
				System.out.println("Current player: " + b.currentPlayer());
				System.out.println("Enter column number for next move: ");
				int col = Integer.parseInt(in.nextLine());
				legalMove = b.play(col);
			}
		}
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println(b);
		System.out.println("GAME OVER");
		if (b.winner() == ' ')
			System.out.println("It's a draw");
		else
			System.out.println(b.winner() + " WINS!!!");

    in.close();
	}
	
}
