package excepciones;

@SuppressWarnings("serial")
public class NoExisteDepartamentoException extends Exception{
	 public NoExisteDepartamentoException(String string) {
	        super(string);
	 }
}