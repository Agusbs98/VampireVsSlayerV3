package control.Commands;

import excepciones.*;
import logic.Game;

public class BankCommand extends Command{
	private int fil;
	private int col;
	private int comision;
	
	public BankCommand() {
		super("bank", "b", "[b]ank <x> <y>", "add a bank in position x, y, z");
	}
	
	public Command parse(String[] commandWords) throws CommandParseException{
		if (matchCommandName(commandWords[0])) {
			if(commandWords.length==4) {
				this.fil=toInt(commandWords[2]);
				this.col=toInt(commandWords[1]);
				this.comision = toInt(commandWords[3]);
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
		  
			  return game.addBank(col, fil,comision);
		  }catch(CommandExecuteException cee){
				System.out.println(cee.getMessage());
				throw new CommandExecuteException("[ERROR]:Failed to add "+ name+".");
		  }
	  }
	

}
