package excepciones;

@SuppressWarnings("serial")
public class NoExistenCategoriasException extends Exception{
	public NoExistenCategoriasException(String string) {
        super(string);
  }
}
