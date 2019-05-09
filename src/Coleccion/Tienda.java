package Coleccion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Base.Animal;
import Exceptions.TiendaExceptions;
import Interfaz.ITienda;

public class Tienda implements ITienda {

	// AUTO-GENERATED
	private static final long serialVersionUID = 1L;

	// Nombre por defecto para la Tienda si no se introduce ninguno, o se le pasa un
	// String vacio
	private final String DEFAULT_SHOP_NAME = "Prueba";

	private Map<String, Animal> tienda = null;
	private String nombreTienda = "";

	/**
	 * Constructor que le otorga un nombre a la Tienda y se le pasa un
	 * Map<String,Animal> para cargar una tienda ya existente.
	 * 
	 * @param nombreTienda
	 * @param otraTienda
	 */
	public Tienda(String nombreTienda, Map<String, Animal> otraTienda) {
		if (nombreTienda.isEmpty()) {
			this.nombreTienda = DEFAULT_SHOP_NAME;
		} else {
			this.nombreTienda = nombreTienda;
		}

		if (otraTienda != null) {
			tienda = new TreeMap<>(otraTienda);
		} else {
			tienda = new TreeMap<>();
		}
	}

	/**
	 * Constructor que le otorga un nombre a la Tienda.
	 * 
	 * @param nombreTienda
	 */
	public Tienda(String nombreTienda) {
		this(nombreTienda, null);
	}

	/**
	 * Constructor que inicializa la tienda con el Map<String,Animal> pasado por
	 * parametro.
	 * 
	 * @param otraTienda
	 */
	public Tienda(Map<String, Animal> otraTienda) {
		this("", otraTienda);
	}

	/**
	 * Constructor por defecto.
	 */
	public Tienda() {
		this("", null);
	}

	@Override
	public boolean insertarAnimal(Animal a) throws TiendaExceptions {
		boolean insertado = false;

		if (a != null && !tienda.containsKey(a.getCodigo())) {
			insertado = true;
			tienda.put(a.getCodigo(), a);
		} else if (a == null) {
			throw new TiendaExceptions("ERROR, Animal null");
		} else {
			throw new TiendaExceptions("ERROR Desconocido");
		}

		return insertado;
	}

	@Override
	public boolean eliminarPorCodigo(String codigo) throws TiendaExceptions {
		boolean eliminado = false;

		if (tienda.containsKey(codigo)) {
			eliminado = true;
			tienda.remove(codigo);
		} else if (codigo.isEmpty()) {
			throw new TiendaExceptions("Codigo Erroneo, Vacio");
		} else if (codigo.length() != 4) {
			throw new TiendaExceptions("Codigo Erroneo, Longitud Incorrecta");
		} else if (tienda.isEmpty()) {
			throw new TiendaExceptions("ERROR, Tienda Vacia");
		}

		return eliminado;
	}

	@Override
	public List<Animal> eliminarPorNombre(String nombre) throws TiendaExceptions {
		List<Animal> temp = null;

		if (tienda.isEmpty()) {
			throw new TiendaExceptions("ERROR, Tienda Vacia");
		} else {
			if (nombre == null) {
				throw new TiendaExceptions("ERROR Null");
			} else if (nombre.isEmpty()) {
				throw new TiendaExceptions("Codigo Erroneo, Vacio");
			} else {
				temp = new ArrayList<>();
				Iterator<Animal> ite = tienda.values().iterator();
				while (ite.hasNext()) {
					Animal animal = (Animal) ite.next();
					if (animal.getNombre().equals(nombre)) {
						temp.add(animal);
						ite.remove();
					}
				}
			}
		}

		return temp;
	}

	@Override
	public Iterator<Animal> iterator() throws TiendaExceptions {
		Iterator<Animal> ite = null;

		if (tienda.isEmpty()) {
			throw new TiendaExceptions("ERROR, Tienda Vacia");
		} else {
			ite = tienda.values().iterator();
		}

		return ite;
	}

	/**
	 * Devuelve el nombre de la Tienda.
	 * 
	 * @return
	 */
	public String getNombreTienda() {
		return nombreTienda;
	}

}
