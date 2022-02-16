package control.Commands;

import excepciones.CommandParseException;
import logic.Game;

public class UpdateCommand extends Command{

	public UpdateCommand() {
		super("none", "n", "[n]one | []", "update");		
	}
	
	public Command parse(String[] commandWords)  throws CommandParseException {
		if (parseNoParamsCommand(commandWords)!= null || commandWords[0].equals("")) {			
			return new UpdateCommand();
		}
		return null;
	}

	  public boolean execute(Game game){
		  game.update();
		  return true;
	  }
}

