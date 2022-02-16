package control.Commands;

import excepciones.CommandExecuteException;
import excepciones.CommandParseException;
import logic.Game;

public class LightFlashCommand extends Command{
	public LightFlashCommand() {
		super("light", "l" , "[l]ight", "elimina a los vampiros menos a dracula");		
	}
	
	public Command parse(String[] commandWords)  throws CommandParseException {
		
		return parseNoParamsCommand(commandWords);
	}

	 public boolean execute(Game game) throws CommandExecuteException{
		 try {
			 return game.light();
		 }catch(CommandExecuteException cee){
				System.out.println(cee.getMessage());
				throw new CommandExecuteException("[ERROR]:Failed to use "+ name+".");
		 }
	 }
}
