package control.Commands;

import excepciones.CommandParseException;
import logic.Game;

public class ExitCommand extends Command{
	private final static String name="exit";
	private final static String shortcut="e";
	private final static String details="[e]xit";
	private final static String help="exit game";


	
	public ExitCommand() {
		super(name, shortcut , details, help);		
	}
	
	public Command parse(String[] commandWords)  throws CommandParseException {
		
		return parseNoParamsCommand(commandWords);
	}

	 public boolean execute(Game game) {
		 game.exit();
		 return true;
	 }
}