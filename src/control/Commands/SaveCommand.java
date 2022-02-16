package control.Commands;

import excepciones.CommandParseException;
import logic.Game;
import java.io.*;

public class SaveCommand extends Command{
	String filename;
	FileWriter file = null;
	public SaveCommand() {
		super("Save", "S", "[S]ave", "save the game in [name].dat");		
	}
	
	public Command parse(String[] commandWords)  throws CommandParseException {
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length==2) {
				this.filename = commandWords[1]+".dat";
				return this;
			}
			else {
				throw new CommandParseException("[ERROR]:Command "+ name+" :"+incorrectNumberOfArgsMsg);
			}
		}else {
			return null;
		}
	}

	public boolean execute(Game game) throws IOException{
		BufferedWriter out=null;
		try {
		out = new BufferedWriter(new FileWriter(this.filename));
		out.write("Buffy the Vampires Slayer v3.0\n\n"+game.serialize());
			if(out != null) {
				  out.close();
				  System.out.println("Game successfully saved in file " +this.filename+".");
			}
		}finally {
			if(out != null) {
				out.close();
			}
		}
		return true;
	}

}
