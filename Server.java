import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.PrintWriter;
import java.net.ServerSocket; 
import java.net.Socket;
/**
 * The Server is a tic tac toc game. The server have a board. The oplayer and xplayer can play 
 * the game use the two client to coummnicate with the game server. 
 * @author Monny
 * @since Feb 29th 2016
 */
public class Server {
	/**
	 * Server socket
	 */
	private ServerSocket serversocket;	
	/**
	 * socket connect to client and game server
	 */
	private Socket socket;
	/**
	 * The  X and O was be place on the board.
	 */
	private Board board;
	/**
	 * Recive the message from the Client server
	 */
	private BufferedReader in;
	/**
	 * The message send out from the serveer.
	 */
	private PrintWriter out;
	/**
	 * Server constructor. In this game using channel 8989.
	 */
public Server(){
	board = new Board();
	try{
		serversocket = new ServerSocket(8989);
	}
	catch(IOException e){
		System.out.println("Create the new socket error");;
	}
	System.out.println("Server is running");
}
/**
 *  The main function to create allow to client server to connect. The xPlayer and oPlayer. The two player need to wait both player is ready.
 * @param args 
 * @throws InterruptedException 
 */
public static void main(String [] args)throws InterruptedException{
	try{
	Server gameserver = new Server();
	
	gameserver.socket = gameserver.serversocket.accept();
	gameserver.in = new BufferedReader(new InputStreamReader(gameserver.socket.getInputStream()));
	gameserver.out = new PrintWriter(gameserver.socket.getOutputStream(),true);
	Player xPlayer = new Player(gameserver.in,gameserver.out,'X',gameserver.board);
	Thread xthread =new Thread(xPlayer);
	xthread.start();
	
	gameserver.socket = gameserver.serversocket.accept();
	gameserver.in = new BufferedReader(new InputStreamReader(gameserver.socket.getInputStream()));
	gameserver.out = new PrintWriter(gameserver.socket.getOutputStream(),true);
	Player oPlayer = new Player(gameserver.in,gameserver.out,'O',gameserver.board);
	Thread othread =new Thread(oPlayer);
	othread.start();
	
	xPlayer.setOpponent(oPlayer);
	oPlayer.setOpponent(xPlayer);
	try{
		xthread.join();
		othread.join();
	}
	catch(InterruptedException ee){
	System.out.println("Error occured");
	}
xPlayer.play();
gameserver.in.close();
gameserver.out.close();

	
}
	catch(IOException e){
	System.out.println(e.getMessage());	
	}
	
	}
}
