package control.Commands;

import excepciones.CommandParseException;
import logic.Game;

public class SuperCoinsCommand extends Command{
	public SuperCoinsCommand() {
		super("coins", "c" , "[c]oins", "aniade 1000 coins al jugador");		
	}
	
	public Command parse(String[] commandWords)  throws CommandParseException {
		
		return parseNoParamsCommand(commandWords);
	}

	 public boolean execute(Game game){
		 game.superCoins();
		 return true;
	 }
}
