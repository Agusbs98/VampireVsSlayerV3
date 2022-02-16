package control.Commands;

import excepciones.CommandExecuteException;
import excepciones.CommandParseException;
import logic.Game;

public class AddVampireCommand extends Command {
	private int fil;
	private int col;
	private String type;
	
	public AddVampireCommand() {
		super("vampire", "v", "[v]ampire <x> <y>", "add a vampire[TYPE] in position x, y");
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length==4) {
				this.fil=toInt(commandWords[3]);
				this.col=toInt(commandWords[2]);
				this.type = commandWords[1];
				return this;				
			}
			else if(commandWords.length==3) {
				this.fil=toInt(commandWords[2]);
				this.col=toInt(commandWords[1]);
				this.type = "v";
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
			  return game.addVampireType(col, fil, type);
		  }catch(CommandExecuteException cee){
				System.out.println(cee.getMessage());
				throw new CommandExecuteException("[ERROR]:Failed to add this "+ name+".");
		  }
	  }
	  
}