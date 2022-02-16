package control;

import java.io.IOException;
import java.util.Scanner;

import control.Commands.Command;
import control.Commands.CommandGenerator;
import excepciones.*;
import logic.Game;

public class Controller {
	
	public final String prompt = "Command > ";
	public static final String unknownCommandMsg ="Unknown command";

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    	this.scanner = scanner;
    }
    
    public void  printGame() {
   	 System.out.println(game);
   }
    
    public void run() throws GameException {
    	boolean refreshDisplay = true;

	    while (!game.fin()){	
        
	    	try {
	    		if (refreshDisplay) printGame();
	    		refreshDisplay = false;
	    		
	    		System.out.println(prompt);	
	    		String s = scanner.nextLine();
	    		String[] parameters = s.toLowerCase().trim().split(" ");
	    		System.out.println("[DEBUG] Executing: " + s);
	    		Command command = CommandGenerator.parseCommand(parameters);
    			refreshDisplay = command.execute(game);
	    		/*if (command != null) { 
	    			refreshDisplay = command.execute(game);
	    		}
	    		else {
	    			System.out.println("[ERROR]: "+ unknownCommandMsg);
	    		}*/
	    	}catch(GameException e) {
	    		System.out.println(e.getMessage());
	    	}catch(Exception e) {
	    		System.out.println("[ERROR]:" + e.getMessage());
	    	//}catch(IOException e) {
	    		
	    	}
		}
	    
    	if (refreshDisplay) printGame();
    	System.out.println ("[Game over] ");
    	if(game.modoFin()) {
    		System.out.print(game.getWinnerMessage());
		}
    	else if(!game.modoFin()){
    		System.out.print(game.getLoserMessage());
    	}
    }
}