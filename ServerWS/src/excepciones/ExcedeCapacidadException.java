package excepciones;

@SuppressWarnings("serial")
public class ExcedeCapacidadException extends Exception{
	public ExcedeCapacidadException(String string) {
        super(string);
  }
}
