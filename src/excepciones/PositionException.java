package excepciones;

public class PositionException extends CommandExecuteException {
	public PositionException() {super("[ERROR]:No se puede aniadir en esa posicion.");}
	
	public PositionException(String message){ super(message); }
	
	public PositionException(String message, Throwable cause){
		 super(message, cause);
	}

	public PositionException(Throwable cause){ super(cause); }	
	
	public PositionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		 super(message, cause, enableSuppression, writableStackTrace);
	}
}
