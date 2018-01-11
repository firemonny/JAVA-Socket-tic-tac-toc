import java.io.BufferedReader; 
import java.io.PrintWriter;
import java.net.Socket;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.PrintWriter;
import java.io.InputStreamReader;
/**
 * The Client server provide the player communicate with other player. 
 * @author Monny
 * @since Feb 29th 2016
 */
public class Client {
		/**
		 * The socket client was connected
		 */
		private Socket connect;
		/**
		 * The message receive from the server
		 */
		private BufferedReader in;
		/**
		 * The message send out to the server
		 */
		private PrintWriter out;
		/**
		 * The player enter the message.
		 */
		private BufferedReader screen;
		/**
		 * Constructor for the client server.
		 * @param serverName The server name.
		 * @param portNumber port name want to connect to.
		 */
	 public Client(String serverName, int portNumber){
		    	try{
		    		connect= new Socket(serverName,portNumber); 
		    		screen = new BufferedReader(new InputStreamReader(System.in)); 
		    		in= new BufferedReader(new InputStreamReader(connect.getInputStream()));
		    		out= new PrintWriter(connect.getOutputStream(),true);
		    		}
		    	catch (IOException e){
		    		out.println("Error of create connect");}
		    
		    }
		    /**
		     * This function provide the Client to communicate with game server
		     * @throws NullPointerException
		     */
	    public void communicate()throws NullPointerException{
	    	String outputmessage="";
	    	String response="";
	    	while (!(response.equals("QUIT"))){
	    		try{
	    			while(!(response =in.readLine()).equals("ENTER")){
	    			
	    			System.out.println(response);
	    			} 
	    			if (response.equals("QUIT")) {
	    			in.close();
	    			out.close();
	    			screen.close();
	    			connect.close();
	    			break;

	    			}
	    			else{
	    			outputmessage = screen.readLine();
	    			out.println(outputmessage);
	    			}
	    		}
	    		catch(IOException e){
	    			System.out.println("Sending error problem"+ e.getMessage());
	    		}
	    	}
	    		
	    	
	    }
	    /**
	     * The main function for the client server.
	     * @param args
	     * @throws NullPointerException
	     */
	   
	    public static void main (String[]args)throws NullPointerException{
	    	Client newclient = new Client("localhost",8989);
	    	newclient.communicate();    	
	    }

}
