package excepciones;

public class NoMoreVampireException extends CommandExecuteException{
	public NoMoreVampireException() {super("[ERROR]: No hay suficientes monedas.");}
	
	public NoMoreVampireException(String message){ super(message); }
	
	public NoMoreVampireException(String message, Throwable cause){
		 super(message, cause);
	}

	public NoMoreVampireException(Throwable cause){ super(cause); }	
	
	public NoMoreVampireException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		 super(message, cause, enableSuppression, writableStackTrace);
	}

}
