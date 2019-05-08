package Aplicacion;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Base.Animal;
import Base.Gato;
import Base.Perro;
import Coleccion.Tienda;
import EntradaTeclado.LeerTeclado;
import Exceptions.TiendaExceptions;

public class HelperTienda {

	/**
	 * Funcion que genera el mensaje que se muestra en el Menu.
	 * 
	 * @return menu
	 */
	public static String menu() {
		StringBuffer temp = new StringBuffer();

		temp.append("1- Insertar un Animal en la Tienda");
		temp.append("\n2- Eliminar un Animal por el codigo");
		temp.append("\n3- Eliminar todos los Animales de la tienda que tengan un nombre");
		temp.append(
				"\n4- Mostrar por pantalla toda la inormacion de los perros que tengan una longitud mayor de 50 centimetros");
		temp.append("\n5- Mostrar por pantalla toda la informacion de los Animales");
		temp.append("\n6- Eliminar de la tienda todos los gatos que tengan una edad mayor de 8 años");
		temp.append("\n7- Mostrar todos los animales ordendor por el nombre alfabeticamente de menor a mayor");
		temp.append("\n8- Guardar Tienda");
		temp.append("\n0- Salir del programa");

		return temp.toString();
	}

	/**
	 * Funcion que inserta un Animal en la Tienda, preguntando previamente si es un
	 * Perro o un Gato.
	 * 
	 * @param tienda
	 * @return boolean
	 * @throws TiendaExceptions
	 */
	public static boolean insertarAnimal(Tienda tienda) throws TiendaExceptions {
		boolean insertado = false;
		int opc = LeerTeclado.leerEntero("1-Perro \n2-Gato");
		String nombre = "";

		if (opc == 1 || opc == 2) { // Para que el mensaje no salga si la opcion es erronea
			nombre = LeerTeclado.leerString("Escriba un nombre para el animal");
		}

		switch (opc) {
		case 1:
			int temp1 = LeerTeclado.leerEntero("Escriba la longitud del perro");
			insertado = tienda.insertarAnimal(new Perro(nombre, temp1));
			break;
		case 2:
			int temp2 = LeerTeclado.leerEntero("Escriba la edad del gato");
			insertado = tienda.insertarAnimal(new Gato(nombre, temp2));
			break;
		default: // Si la opcion no es correcta se repite hasta que se introduce un animal
			insertado = insertarAnimal(tienda);
			break;
		}
		return insertado;
	}

	/**
	 * Funcion que muestra los Perros que en hay en una Tienda, que sean mayor a una
	 * logitud pasado por parametro.
	 * 
	 * @param tienda
	 * @param longitud
	 * @return List<Animal> o null si no hay ningun Perro.
	 * @throws TiendaExceptions
	 */
	public static List<Animal> eliminarPerros(Tienda tienda, int longitud) throws TiendaExceptions {
		List<Animal> temp = new ArrayList<>();
		Iterator<Animal> ite = tienda.iterator();

		while (ite.hasNext()) {
			Animal aux = (Animal) ite.next();
			if (aux instanceof Perro && (((Perro) aux).getLongitud() > longitud)) {
				temp.add(aux);
				tienda.eliminarPorCodigo(aux.getCodigo());
			}
		}

		return temp;
	}

	/**
	 * Funcion que muestra la informacion de todos los Animales que hay en una
	 * Tienda.
	 * 
	 * @param tienda
	 * @return Informacion de los Animales, si no hubiera animales devuelve 'No hay
	 *         ningun animal'
	 * @throws TiendaExceptions
	 */
	public static String mostrarAnimales(Tienda tienda) throws TiendaExceptions {
		StringBuffer temp = new StringBuffer();
		Iterator<Animal> ite = tienda.iterator();

		if (ite == null) {
			temp.append("No hay ningun animal");
		} else {
			while (ite.hasNext()) {
				temp.append(ite.next().toString() + "\n");
			}
		}

		return temp.toString();
	}

	/**
	 * Funcion que elimina los Gatos de una Tienda que cumplan la condicion de tener
	 * mas de la edad pasada por parametro.
	 * 
	 * 
	 * @param tienda
	 * @param edad
	 * @return List<Animal> o null si en la Tienda no hay ningun Gato.
	 * @throws TiendaExceptions
	 */
	public static List<Animal> eliminarGatos(Tienda tienda, int edad) throws TiendaExceptions {
		List<Animal> temp = new ArrayList<>();
		Iterator<Animal> ite = tienda.iterator();

		while (ite.hasNext()) {
			Animal aux = (Animal) ite.next();
			if (aux instanceof Gato && (((Gato) aux).getEdad() > edad)) {
				temp.add(aux);
				tienda.eliminarPorCodigo(aux.getCodigo());
			}
		}

		return temp;
	}

	public static List<Animal> obtenerList(Tienda tienda) throws TiendaExceptions {
		List<Animal> temp = new ArrayList<>();
		Iterator<Animal> ite = tienda.iterator();

		while (ite.hasNext()) {
			temp.add(ite.next());
		}

		return temp;
	}

	/**
	 * Funcion que carga la Tienda, en caso de no existir el Fichero, se inicializa
	 * una nueva Tienda.
	 * 
	 * @param nombre
	 * @return Tienda
	 * @throws TiendaExceptions
	 */
	public static Tienda cargarTienda(String nombre) throws TiendaExceptions {
		Tienda temp = null;
		Path path = Paths.get(nombre + ".txt");

		if (Files.exists(path) && path.toFile() != null && path.toFile().isFile()) {
			try {
				InputStream flujoDatos = new ObjectInputStream(new FileInputStream(path.toFile()));

				Animal.setContador(((ObjectInputStream) flujoDatos).readInt()); // Se lee el contador que se tenia de
																				// los Animales
				temp = (Tienda) ((ObjectInputStream) flujoDatos).readObject(); // Se lee la propia tienda ya existente

				flujoDatos.close();

			} catch (Exception e) {
				throw new TiendaExceptions("Fallo carga Fichero Tienda");
			}
		} else {
			temp = new Tienda(nombre);
			Animal.setContador(0); // Para que no se pueda modifar los codigos de los Animales.
		}

		return temp;
	}

	/**
	 * Funcion para leer el Fichero de Errores de la Tienda.
	 * 
	 * @param nombreTienda
	 * @return
	 * @throws TiendaExceptions
	 */
	@SuppressWarnings("unchecked")
	public static List<String> cargarErrores(String nombreTienda) throws TiendaExceptions {
		List<String> temp = null;

		Path path = Paths.get(nombreTienda + "Errores.txt");

		if (Files.exists(path) && path.toFile() != null && path.toFile().isFile()) {
			try {
				InputStream flujoDatos = new ObjectInputStream(new FileInputStream(path.toFile()));

				temp = (List<String>) ((ObjectInputStream) flujoDatos).readObject();

				flujoDatos.close();
			} catch (Exception e) {
				throw new TiendaExceptions("Fallo carga Fichero Errores");
			}
		} else {
			temp = new ArrayList<>();
		}

		return temp;
	}

	/**
	 * Funcion que Guarda en un Fichero el contador de los Animales, que sirve para
	 * el codigo, y la Tienda
	 * 
	 * @param tienda
	 * @throws TiendaExceptions
	 */
	public static void guardarTienda(Tienda tienda) throws TiendaExceptions {
		Path path = Paths.get(tienda.getNombreTienda() + ".txt");

		try {

			if (!Files.exists(path)) { // Creamos el fichero si no existe
				BufferedWriter bw = new BufferedWriter(new PrintWriter(tienda.getNombreTienda() + ".txt"));
				bw.close();
			}

			ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream(path.toFile()));
			flujoSalida.writeInt(Animal.getContador()); // Guardamos el contador de los Animales
			flujoSalida.writeObject(tienda); // Guardamos la Tienda

			flujoSalida.close();

		} catch (Exception e) {
			throw new TiendaExceptions("Fallo Al Guardar Fichero de la Tienda");
		}
	}

	/**
	 * Funcion que Guarda en un Fichero los Errores porducidos en la Tienda.
	 * 
	 * @param nombre
	 * @param errores
	 * @throws TiendaExceptions
	 */
	public static void guardarErrores(String nombre, List<String> errores) throws TiendaExceptions {
		Path path = Paths.get(nombre + "Errores.txt");

		try {

			if (!Files.exists(path)) { // Creamos el fichero si no existe
				BufferedWriter bw = new BufferedWriter(new PrintWriter(nombre + ".txt"));
				bw.close();
			}

			ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream(path.toFile()));

			flujoSalida.writeObject(errores); // Guardamos los Errores de la Tienda

			flujoSalida.close();

		} catch (Exception e) {
			throw new TiendaExceptions("Fallo Al Guardar Fichero de Errores");
		}

	}

}
