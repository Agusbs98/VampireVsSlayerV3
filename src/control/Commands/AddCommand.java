package control.Commands;

import logic.Game;
import excepciones.*;


public class AddCommand extends Command {
	private int fil;
	private int col;
	
	public AddCommand() {
		super("add", "a", "[a]dd <x> <y>", "add a slayer in position x, y");
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length==3) {
				this.fil=toInt(commandWords[2]);
				this.col=toInt(commandWords[1]);
				return this;
			}
			else {
				throw new CommandParseException("[ERROR]:Command "+ name+" :"+incorrectNumberOfArgsMsg);
			}
		}else {
			return null;
		}
		
	}

	public  boolean execute(Game game) throws CommandExecuteException{
		try {
		  return game.addSlayer(col, fil);
		}catch(CommandExecuteException cee){
			System.out.println(cee.getMessage());
			throw new CommandExecuteException("[ERROR]:Failed to "+ name+" Slayer.");
		}
	}
	  
}