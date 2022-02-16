package control.Commands;

import excepciones.CommandParseException;
import logic.Game;

public class HelpCommand extends Command{

	public HelpCommand() {
		super("Help", "H", "[h]elp", "show this help");		
	}
	
	public Command parse(String[] commandWords)  throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

	  public boolean execute(Game game) {
		  System.out.println(CommandGenerator.commandHelp());
		  return true;
	  }
}
