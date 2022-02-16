package control.Commands;

import excepciones.*;

public class CommandGenerator{
	private static int numCommands = 12;
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new BankCommand(),
			new GarlicPushCommand(),
			new LightFlashCommand(),
			new SuperCoinsCommand(),
			new AddVampireCommand(),
			new SerializeCommand(),
			new SaveCommand()
			};
	
	
	public static Command parseCommand(String[ ] commandWords) throws CommandParseException {
		int i=0;
		boolean encontrado=false;
		while (i<numCommands && !encontrado) {
			if (availableCommands[i].parse(commandWords)!=null) {
				encontrado=true;
				return availableCommands[i];
			}
			i++;
		}
		return null;
	}
	
	
	public static String commandHelp() {
		String ayuda = availableCommands[0].helpText();
		for (int i=1; i<numCommands; i++) {
			ayuda += availableCommands[i].helpText();
		}
		return ayuda;
	}
	
}