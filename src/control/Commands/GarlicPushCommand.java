package control.Commands;

import excepciones.CommandExecuteException;
import excepciones.CommandParseException;
import logic.Game;

public class GarlicPushCommand extends Command{
	public GarlicPushCommand() {
		super("garlic", "g" , "[g]arlic", "Empuja los vampiros una posicion atras");		
	}
	
	public Command parse(String[] commandWords)  throws CommandParseException {
		
		return parseNoParamsCommand(commandWords);
	}

	 public boolean execute(Game game) throws CommandExecuteException{
		 try {
		 return  game.garlic();
		 }catch(CommandExecuteException cee){
				System.out.println(cee.getMessage());
				throw new CommandExecuteException("[ERROR]:Failed to use "+ name+".");
		 }
	 }
}
