package excepciones;

public class CoinsException extends CommandExecuteException{
	public CoinsException() {super("[ERROR]: No hay suficientes monedas.");}
	
	public CoinsException(String message){ super(message); }
	
	public CoinsException(String message, Throwable cause){
		 super(message, cause);
	}

	public CoinsException(Throwable cause){ super(cause); }	
	
	public CoinsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		 super(message, cause, enableSuppression, writableStackTrace);
	}
}
