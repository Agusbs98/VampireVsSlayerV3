package control.Commands;

import excepciones.CommandParseException;
import logic.Game;

public class ResetCommand extends Command{

	public ResetCommand() {
		super("Reset", "R", "[r]eset", "reset game");		
	}
	
	public Command parse(String[] commandWords)  throws CommandParseException {
		
		return parseNoParamsCommand(commandWords);
	}

	  public boolean execute(Game game){
		  game.inicializa();
		  return  true;
	  }
}
