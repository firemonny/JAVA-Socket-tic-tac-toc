import java.util.Scanner;
import java.net.Socket;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.PrintWriter;
import java.io.InputStreamReader;
/**
 * Construct a player with the x or o player. The class can be use in the game and have their action to play tic tac toe game.  
 * @author Nung-Shun Chou Monny
 * @version 1.0
 * @since Feb 2th 2016
 */
public class Player implements Constants, Runnable{
/**
 * The player's name
 */
private String name;
/**
 * The mark belong to player 'x' or 'o'
 */
private char mark;
/**
 * The board player's belong to.
 */
private Board board;
/**
 * The opponent's player
 */
private Player opponent;
/**
 *  out sending out the message to the server
 */
public PrintWriter out;
/**
 *  in reciving the message form server.
 */
public BufferedReader in;
/**
 * The constructor for the player with the name mark and the board which the player belong to.
 * @param name The Player's name
 * @param mark The Player's mark 'x' or 'o'. 
 * @param board The player belongs to the board.
 */


public Player(String name,char mark,Board board){
	this.name =name;
	this.mark=mark;
	this.board=board;
}
public Player (BufferedReader input, PrintWriter output,char mark, Board board){
this.mark=mark; 
this.board=board; 
this.in=input; 
this.out=output;
}
/**
 * Runable function to provide player to create their name and also need to wait other player.
 */
public void run(){
	String temp=null;
	try{
		this.board.display(out);
		System.out.println("Player"+this.mark+" is connected");
		out.println("Please enter the name of the player with the mark" + this.mark +" : ");
		out.println("ENTER");
		temp= in.readLine();
		while(temp == null){
			out.println("Invalid Entry, PLease Try Again!");
			temp=in.readLine(); }
		this.name = temp;
		out.println("Opponent is trying to connect");
		}
	catch (IOException ee){
		System.out.println("error"); }
	}
	
	

/**
 *  The move function will call make Move function. It will check xplayer win , oplayer win or the tie game. If one of it be ture. The game was end.
 * @throws IOException
 */
synchronized public void play() throws IOException{
	while(true){
		this.makeMove();
		
		if(this.board.xWins()!=1){
		if(this.board.isFull()==true){ 
			this.board.display(out);
			this.board.display(opponent.out);
		out.println("Tie game");
		out.println("Game Ended ...");
		opponent.out.println("Tie game");
		opponent.out.println("Game Ended ...");
		this.board.clear();
		out.println("QUIT");
		opponent.out.println("QUIT");
		break;
		
		}
		else{
			this.board.display(out);
			this.board.display(opponent.out);
			this.opponent.makeMove();
		}
	}
	else
	{
		this.board.display(out);
		this.board.display(opponent.out);
		out.println("THE GAME IS OVER: "+this.name+" is the Winner!");
		opponent.out.println("THE GAME IS OVER: "+this.name+" is the Winner!");
		this.board.clear();
		out.println("Game Ended ...");
		this.board.clear();
		opponent.out.println("Game Ended ...");
		out.println("QUIT");
		opponent.out.println("QUIT");
		break;
			}
	if (this.board.oWins()!=1){
		this.board.display(out);
		this.board.display(opponent.out);
		if(this.board.isFull()==true){
		out.println("Tie game");
		opponent.out.println("Tie game");
		out.println("Game Ended ...");
		opponent.out.println("Game Ended ...");
		this.board.clear();
		out.println("QUIT");
		opponent.out.println("QUIT");
		break;
		}
	}
	else{
		this.board.display(out);
		this.board.display(opponent.out);
		out.println("THE GAME IS OVER: "+this.opponent.name+" is the Winner!");
		opponent.out.println("THE GAME IS OVER: "+this.opponent.name+" is the Winner!");
		out.println("Game Ended ...");
		opponent.out.println("Game Ended ...");
		this.board.clear();
		out.println("QUIT");
		opponent.out.println("QUIT");
		break;
	}
	}
	
}
/**
 * The function can set the opponent. The opponent is the Player class.
 * @param opponent The opponent player.
 */
public void setOpponent(Player opponent){this.opponent=opponent;}
/**
 * Return the player's name.
 * @return name of the player.
 */
public String name(){return this.name;}
/**
 *  The player's mark
 * @return the char 'o' or 'x'. The player's mark.
 */
public char mark(){return this.mark;}
/**
 * The function ask the player the row and the colume want to place. While the number was be enter the board will display.
 * @throws IOException
 */
synchronized public void makeMove() throws IOException{
	try{
		out.println(this.name+", what row should your next "+this.mark+" be placed in?");
		out.println("ENTER");
		
	String input = in.readLine();
	int row =Integer.parseInt(input); 
	while (row<0 || row>3){
		out.println("The row value must be between 0 - 2");
	input = in.readLine();
	row =Integer.parseInt(input);
		} 
		out.println(this.name+", what colume should your next "+this.mark+" be placed in?");
		out.println("ENTER");
	input = in.readLine();
	int col =Integer.parseInt(input);
	while (col<0 || col>3){
		out.println("The col value must be between 0 - 2");
		input = in.readLine();
		col =Integer.parseInt(input);
		} 
	if (this.board.getMark(row,col)!=SPACE_CHAR){
		out.print("The place have mark, please choose other one");
		this.makeMove();
	}
	else{
	this.board.addMark(row, col, this.mark);
	}
	}
	catch(IOException e){
		out.println("ERROR"); }
	}
}
