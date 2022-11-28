package logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import excepciones.NoExistenCategoriasException;

public class ManejadorCategorias {
	private Map<String, Categoria> categorias;
	private static ManejadorCategorias instancia = null;

    private ManejadorCategorias() {
        categorias = new HashMap<String, Categoria>();
    }

    public static ManejadorCategorias getinstance() {
        if (instancia == null) {
            instancia = new ManejadorCategorias();
        }
        return instancia;
    }

	public void darAltaCategoria(Categoria cat) {
        String nombreCat = cat.getNombre();
        categorias.put(nombreCat, cat);
	}

	public boolean existeCategoria(String nombre) {
		return ((Categoria) categorias.get(nombre)) != null;
	}

	public String[] listarCategorias() throws NoExistenCategoriasException {
		if (!categorias.isEmpty()) {
			Collection<Categoria> paqs = categorias.values();
			Object[] categorias = paqs.toArray();
			String[] result = new String[categorias.length];
			for (int i = 0; i < categorias.length; i++) {
				Categoria categoria = (Categoria) categorias[i];
				result[i] = categoria.getNombre();
			}
			return result;				
		} else {
        	throw new NoExistenCategoriasException("No existen Categorias registradas para la actividad");
        }
	}
}
