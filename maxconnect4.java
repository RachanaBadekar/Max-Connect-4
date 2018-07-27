import java.io.*;
import java.util.Scanner;
/**
 * 
 * @author James Spargo
 * This class controls the game play for the Max Connect-Four game. 
 * To compile the program, use the following command from the maxConnectFour directory:
 * javac *.java
 *
 * the usage to run the program is as follows:
 * ( again, from the maxConnectFour directory )
 *
 *  -- for interactive mode:
 * java MaxConnectFour interactive [ input_file ] [ computer-next / human-next ] [ search depth]
 *
 * -- for one move mode
 * java maxConnectFour.MaxConnectFour one-move [ input_file ] [ output_file ] [ search depth]
 * 
 * description of arguments: 
 *  [ input_file ]
 *  -- the path and filename of the input file for the game
 *  
 *  [ computer-next / human-next ]
 *  -- the entity to make the next move. either computer or human. can be abbreviated to either C or H. This is only used in interactive mode
 *  
 *  [ output_file ]
 *  -- the path and filename of the output file for the game.  this is only used in one-move mode
 *  
 *  [ search depth ]
 *  -- the depth of the minimax search algorithm
 * 
 *   
 */

public class maxconnect4
{
  public static void main(String[] args) 
  {
    // check for the correct number of arguments
    if( args.length != 4 ) 
    {
      System.out.println("Four command-line arguments are needed:\n"
                         + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
                         + " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

      exit_function( 0 );
     }
		
    // parse the input arguments
    String game_mode = args[0].toString();				// the game mode
    String input = args[1].toString();					// the input game file
    int depthLevel = Integer.parseInt( args[3] );  		// the depth level of the ai search
		
    // create and initialize the game board
    GameBoard currentGame = new GameBoard( input );
    
    // create the Ai Player
    //AiPlayer calculon = new AiPlayer();           // This is the AI player setup ******************************************************************
    
    //  variables to keep up with in the game
    int playColumn = 99;				//  the players choice of column to play
    boolean playMade = false;			//  set to true once a play has been made
    //////////////// Interactive mode //////////////////////////
    if( game_mode.equalsIgnoreCase( "interactive" ) ) 
    {
//	System.out.println("interactive mode is currently not implemented\n");
//	return;
        int test=0;
        while( currentGame.getPieceCount() < 42 ) 
        {
            System.out.print("\nMaxConnect-4 game\n");
            System.out.print("game state before move:\n");
            //print the current board
            currentGame.printGameBoard();
            // print the current scores
            System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +
                                ", Player2 = " + currentGame.getScore( 2 ) + "\n " );
            //check for computer first or option for computer next available
            if(test==1 || (args[2].toString().equalsIgnoreCase("computer-next"))){
                int current_player = currentGame.getCurrentTurn();
                
                MiniMax calculon = new MiniMax(currentGame.getGameBoard(), current_player); // The next two lines were installed for new AI
                playColumn = calculon.minMax(depthLevel);                   //minmax with depth option (difficulty)
                //playColumn = calculon.findBestPlay( currentGame );        // option for AI to be random
                // play the piece
                currentGame.playPiece( playColumn );
                //saves the move to file called computer.txt
                currentGame.printGameBoardToFile("computer.txt");
                // display the current game board
                System.out.println("move " + currentGame.getPieceCount() 
                                + ": Player " + current_player
                                + ", column " + playColumn);
                System.out.print("game state after move:\n");
                currentGame.printGameBoard();

                // print the current scores
                System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +
                                    ", Player2 = " + currentGame.getScore( 2 ) + "\n " );
            }
            //Checks to make sure board has not filled up before player can move.
            if(currentGame.getPieceCount() < 42){
                //if human first then set option for computer next
                test=1;
                //print stats
                System.out.print("\nMaxConnect-4 game\n");
                System.out.print("game state before move:\n");
                //prints current score of board
                currentGame.printGameBoard();
                System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +
                                    ", Player2 = " + currentGame.getScore( 2 ) + "\n " );

                Scanner scan = new Scanner(System.in);
                //ask user for move between 1 and 7. makes sure move is valid
                
                System.out.println("\n Choose your column(1-7): ");
                playColumn=scan.nextInt();

                while(!(currentGame.isValidPlay(playColumn-1))){
                    System.out.println("\nError! Choose your column again(1-7): ");
                    playColumn=scan.nextInt();
                }
                //play the piece
                currentGame.playPiece((playColumn-1));
                //saves the move to file called human.txt
                currentGame.printGameBoardToFile("human.txt");
            }
        }
        System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
        exit_function( 0 );
    } 
			
    else if( !game_mode.equalsIgnoreCase( "one-move" ) ) 
    {
      System.out.println( "\n" + game_mode + " is an unrecognized game mode \n try again. \n" );
      return;
    }
    /////////////   one-move mode ///////////
    // get the output file name
    String output = args[2].toString();				// the output game file
    
    System.out.print("\nMaxConnect-4 game\n");
    System.out.print("game state before move:\n");
    
    //print the current game board.
    currentGame.printGameBoard();
    // print the current scores
    System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +
			", Player2 = " + currentGame.getScore( 2 ) + "\n " );
    
    // ****************** this chunk of code makes the computer play
    if( currentGame.getPieceCount() < 42 ) 
    {
        int current_player = currentGame.getCurrentTurn();
	// AI play - random play
        MiniMax calculon = new MiniMax(currentGame.getGameBoard(), current_player); // The next two lines were installed for new AI
        playColumn = calculon.minMax(depthLevel);                   //
	//playColumn = calculon.findBestPlay( currentGame );        // Removed for new install of Minimax AI **********************************
	// play the piece
	currentGame.playPiece( playColumn );
        	
        // display the current game board
        System.out.println("move " + currentGame.getPieceCount() 
                           + ": Player " + current_player
                           + ", column " + playColumn);
        System.out.print("game state after move:\n");
        currentGame.printGameBoard();
    
        // print the current scores
        System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +
                            ", Player2 = " + currentGame.getScore( 2 ) + "\n " );
        
        currentGame.printGameBoardToFile( output );
    } 
    else 
    {
	System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
    }
    //************************** end computer play
} // end of main()
	
  /**
   * This method is used when to exit the program prematurely.
   * @param value an integer that is returned to the system when the program exits.
   */
  private static void exit_function( int value )
  {
      System.out.println("exiting from MaxConnectFour.java!\n\n");
      System.exit( value );
  }
} // end of class connectFour