package control.Commands;

import excepciones.CommandParseException;
import logic.Game;

public class SerializeCommand extends Command{
	
	public SerializeCommand() {
		super("Serialize", "Z", "[Z](serialize)", "cambia el estado de la consola");		
	}
	
	public Command parse(String[] commandWords)  throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

	  public boolean execute(Game game) {
		  System.out.println(game.serialize());
		  return true;
	  }

}
