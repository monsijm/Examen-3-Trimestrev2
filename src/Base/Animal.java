package Base;

import java.io.Serializable;

import Utilidades.Utilidades;

public abstract class Animal implements Serializable {

	// AUTO-GENERATE
	private static final long serialVersionUID = 1L;

	// Contador statico para poder ponerle un codigo a los Animales
	private static int contador = 0;
	// boleano para asegurar que el contador solo se cambia a la hora de cargar el
	// fichero
	private static boolean contadorModificado = false;

	private String nombre = "";
	private String codigo = "";

	/**
	 * Constructor que otorga el Animal un nombre y un codigo autonumerico.
	 * 
	 * @param nombre
	 */
	protected Animal(String nombre) {

		if (nombre == null || nombre.equals("")) {
			this.nombre = "Sin Nombre";
		} else {
			this.nombre = nombre;
		}

		codigo = Utilidades.codigo(++contador);

	}

	@Override
	public String toString() {
		return "El codigo del animal es " + codigo + " y de nombre " + nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (!nombre.equals("") && nombre != null) {
			this.nombre = nombre;
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public static int getContador() {
		return Animal.contador;
	}

	public static void setContador(int contador) {

		if (!contadorModificado) {
			contadorModificado = true;
			Animal.contador = contador;
		}

	}

}
