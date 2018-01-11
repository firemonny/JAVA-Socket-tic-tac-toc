// Board.java 
// ENSF 409 - LAB 3 - Ex. C
// This file was originally written for ENGG 335 in fall 2001, and was 
// adapted for ENSF 409 in 2014
//
import java.io.PrintWriter;
/** 
 * Modified by: ADD STUDENT(S) NAME IN CASE THAT ANY CHANGES ARE MADE TO THIS FILE
 *
 * STUDENTS SHOULD ADD CLASS COMMENT, METHOD COMMENTS, FIELD COMMENTS 
 */

public class Board implements Constants {
	/**
	 * The 2-D array of char to store the symol in the board. 
	 */
	private char theBoard[][];
	/**
	 * The number of the mark in the board int from 0 - 9
	 */
	private int markCount;
/**
 * The constructor for the Board. To initialize the board with the markCount=0 and empty symbol in the board.
 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}
/**
 *  Return the mark on the 3x3 board. 
 * @param row The row value in the board.
 * @param col The colume value in the board.
 * @return the char 'x' or 'o' in the row colume.
 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}
/**
 * To check if the board was full. 
 * @return the boolean check the markcount is equal to 9. If yes return true If no return false
 */
	public boolean isFull() {
		return markCount == 9;
	}
/**
 * To check xplayer win or not 
 * @return If X win return 1 If false return 0;
 */
	public int xWins() {
		return checkWinner(LETTER_X);
	}
	/**
	 * To check oplayer win or not 
	 * @return If X win return 1 If false return 0;
	 */
	public int oWins() {
		return checkWinner(LETTER_O);
	}
	/**
	 * Display the board with the symbol.
	 */

	public void display(PrintWriter out) {
		displayColumnHeaders(out);
		addHyphens(out);
		for (int row = 0; row < 3; row++) {
			addSpaces(out);
				out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				out.print("|  " + getMark(row, col) + "  ");
			out.println("|");
			addSpaces(out);
			addHyphens(out);
		}
	}
/**
 * Add mark in the board. 
 * @param row The row number want to add
 * @param col The colume want to add
 * @param mark The mark want to be added 'x' or 'o'.
 */
	public void addMark(int row, int col, char mark) {
		theBoard[row][col] = mark;
		markCount++;
	}
/**
 * The function to clear the board.
 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}
/**
 * Check the xsymbal win or y symbal winn. 
 * @param mark The symbal want to be checked.
 * @return the Integer represent the the symbal enter win or not. 
 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}
/**
 * Display the ColummHeader 
 */
	void displayColumnHeaders(PrintWriter out) {
		out.print("          ");
		for (int j = 0; j < 3; j++)
			out.print("|col " + j);
		out.println();
	}
/**
 * Display the Hyphens.  +------ 
 */
	void addHyphens(PrintWriter out) {
		out.print("          ");
		for (int j = 0; j < 3; j++)
			out.print("+-----");
		out.println("+");
	}
/**
 * Display the space in the board.
 */
	void addSpaces(PrintWriter out) {
		out.print("          ");
		for (int j = 0; j < 3; j++)
			out.print("|     ");
		out.println("|");
	}
}
