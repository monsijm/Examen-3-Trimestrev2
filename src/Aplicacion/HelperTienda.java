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
	
	public static boolean insertarAnimal(Tienda tienda) throws TiendaExceptions{
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
	
	public static List<Animal> mostrarPerros(Tienda tienda, int longitud) throws TiendaExceptions{
		List<Animal> temp = new ArrayList<>();
		Iterator<Animal> ite = tienda.iterator();

		if (ite == null) {
			temp = null;
		} else {
			while (ite.hasNext()) {
				Animal aux = (Animal) ite.next();
				if (aux instanceof Perro && ((Perro) aux).getLongitud() > longitud) {
					temp.add(aux);
				}
			}
		}

		return temp;
	}
	
	public static String mostrarAnimales(Tienda tienda) throws TiendaExceptions{
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

	public static List<Animal> eliminarGatos(Tienda tienda, int edad) throws TiendaExceptions{
		List<Animal> temp = new ArrayList<>();
		Iterator<Animal> ite = tienda.iterator();

		if (ite == null) {
			temp = null;
		} else {
			while (ite.hasNext()) {
				Animal aux = (Animal) ite.next();
				if (aux instanceof Gato && ((Gato) aux).getEdad() > edad) {
					temp.add(aux);
					tienda.eliminarPorCodigo(aux.getCodigo());
				}
			}
		}

		return temp;
	}

	public static List<Animal> obtenerList(Tienda tienda) throws TiendaExceptions{
		List<Animal> temp = new ArrayList<>();
		Iterator<Animal> ite = tienda.iterator();

		while (ite.hasNext()) {
			temp.add(ite.next());
		}

		return temp;
	}

	public static Tienda cargarTienda(String nombre) throws TiendaExceptions {
		Tienda temp = null;
		Path path = Paths.get(nombre + ".txt");

		if (Files.exists(path) && path.toFile() != null && path.toFile().isFile()) {
			try {
				InputStream flujoDatos = new ObjectInputStream(new FileInputStream(path.toFile()));

				temp = (Tienda) ((ObjectInputStream) flujoDatos).readObject();
				Animal.setContador(((ObjectInputStream)flujoDatos).readInt());
				flujoDatos.close();

			} catch (Exception e) {
				throw new TiendaExceptions("Fallo carga Fichero");
			}
		} else {
			temp = new Tienda(nombre);
		}

		return temp;
	}

	public static void guardarTienda(Tienda tienda) throws TiendaExceptions {
		Path path = Paths.get(tienda.getNombreTienda() + ".txt");

		try {
			
			if (!Files.exists(path)) {
				BufferedWriter bw = new BufferedWriter(new PrintWriter(tienda.getNombreTienda() + ".txt"));
				bw.close();
			}

			ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream(path.toFile()));
			flujoSalida.writeObject(tienda);
			flujoSalida.writeInt(Animal.getContador());
			flujoSalida.close();
			
		} catch (Exception e) {
			throw new TiendaExceptions("Fallo Al Guardar Fichero");
		}
	}
	
}
