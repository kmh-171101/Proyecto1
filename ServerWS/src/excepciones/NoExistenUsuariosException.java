package excepciones;

@SuppressWarnings("serial")
public class NoExistenUsuariosException extends Exception{
	 public NoExistenUsuariosException(String string) {
	        super(string);
	  }
}