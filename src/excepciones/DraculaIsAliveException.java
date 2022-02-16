package excepciones;

public class DraculaIsAliveException extends CommandExecuteException{
	public DraculaIsAliveException() {super("[ERROR]: No hay suficientes monedas.");}
	
	public DraculaIsAliveException(String message){ super(message); }
	
	public DraculaIsAliveException(String message, Throwable cause){
		 super(message, cause);
	}

	public DraculaIsAliveException(Throwable cause){ super(cause); }	
	
	public DraculaIsAliveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		 super(message, cause, enableSuppression, writableStackTrace);
	}

}
