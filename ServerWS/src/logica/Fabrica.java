package logica;

public class Fabrica {

    private static Fabrica instancia;

    private Fabrica() {
    };

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IATYST getIATYST() {
        return new ControladorATYST();
    }
    public IDepartamento getIDepartamento() {
        return new ControladorDepartamento();
    }
    public IUsuario getIUsuario() {
    	return new ControladorUsuario();
    }
    public IPaquete getIPaquete() {
    	return new ControladorPaquete();
    }

}