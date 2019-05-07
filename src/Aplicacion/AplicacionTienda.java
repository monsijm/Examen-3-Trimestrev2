package Aplicacion;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Base.Animal;
import Coleccion.Tienda;
import EntradaTeclado.LeerTeclado;
import Exceptions.TiendaExceptions;

public class AplicacionTienda {

	public static void main(String[] args) throws TiendaExceptions {
		Tienda tienda = HelperTienda.cargarTienda("Tienda");

		int opc = 0;

		do {
			String temp;
			System.out.println(HelperTienda.menu());
			opc = LeerTeclado.leerEntero("Escriba la opcion");
			switch (opc) {
			case 0:
				System.out.println("\n\nSalio del Programa\n\n");
				break;
			case 1:
				boolean insertadoAnimal = false;
				insertadoAnimal = HelperTienda.insertarAnimal(tienda);

				if (insertadoAnimal) {
					System.out.println("El animal se ha insertado correctamente");
				} else {
					System.out.println("El animal no se ha insertado");
				}

				break;
			case 2:
				boolean eliminarAnimal = false;
				temp = LeerTeclado.leerString("Escriba el codigo del animal que desea eliminar: ");
				eliminarAnimal = tienda.eliminarPorCodigo(temp);

				if (eliminarAnimal) {
					System.out.println("El animal se ha eliminado correctamente");
				} else {
					System.out.println("El animal no se ha eliminado");
				}

				break;
			case 3:
				temp = LeerTeclado.leerString("Escriba el nombre de los animales que desea eliminar: ");
				tienda.eliminarPorNombre(temp);
				break;
			case 4:
				List<Animal> tempPerros = HelperTienda.mostrarPerros(tienda, 50);
				if (tempPerros == null || tempPerros.size() == 0) {
					System.out.println(
							"No se muestra nada, o bien porque no existen o porque no hay animales en la tienda");
				} else {
					System.out.println(tempPerros.toString());
				}
				break;
			case 5:
				System.out.println(HelperTienda.mostrarAnimales(tienda));
				break;
			case 6:
				List<Animal> tempGatos = HelperTienda.eliminarGatos(tienda, 8);

				if (tempGatos == null || tempGatos.size() == 0) {
					System.out.println(
							"No se ha eliminado nada, o bien porque no existen o porque no hay animales en la tienda");
				} else {
					System.out.println("Se van a mostrar los gatos eliminados");
					System.out.println(tempGatos.toString());
				}

				break;
			case 7:
				List<Animal> aux = HelperTienda.obtenerList(tienda);
				if (aux == null || aux.size() == 0) {
					System.out.println("No hay animales");
				} else {
					Collections.sort(aux, new Comparator<Animal>() {
						@Override
						public int compare(Animal a1, Animal a2) {
							return a1.getNombre().compareTo(a2.getNombre());
						}
					});
					System.out.println(aux.toString());
				}
				break;
			case 8:
				HelperTienda.guardarTienda(tienda);
				System.out.println("\n\nGuardado\n\n");
				break;
			default:
				System.out.println("\n\nLa opcion introducida es erronea\n\n");
				break;
			}

		} while (opc != 0);

	}

}
