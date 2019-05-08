package Aplicacion;

import java.util.Collections;
import java.util.List;

import Base.Animal;
import Coleccion.Tienda;
import EntradaTeclado.LeerTeclado;
import Exceptions.TiendaExceptions;
import Interfaz.ITienda;

public class AplicacionTienda {

	public static void main(String[] args) {
		ITienda tienda = null;
		List<String> tiendaErrores = null;

		boolean darNombre = false;
		int opc = 0;

		do {

			try {

				if (!darNombre) {
					darNombre = true;
					tienda = HelperTienda.cargarTienda(LeerTeclado.leerString("Escribe un nombre para la Tienda"));
					tiendaErrores = HelperTienda.cargarErrores(((Tienda) tienda).getNombreTienda());
				}

				String temp;
				System.out.println(HelperTienda.menu());
				opc = LeerTeclado.leerEntero("Escriba la opcion");
				switch (opc) {
				case 0:

					System.out.println("\n\nSalio del Programa\n\n");
					break;

				case 1:

					if (HelperTienda.insertarAnimal((Tienda) tienda)) {
						System.out.println("El animal se ha insertado correctamente");
					}
					break;

				case 2:

					temp = LeerTeclado.leerString("Escriba el codigo del animal que desea eliminar: ");
					if (tienda.eliminarPorCodigo(temp)) {
						System.out.println("El animal se ha eliminado correctamente");
					} else {
						System.out.println("El animal no se ha eliminado");
					}
					break;

				case 3:

					temp = LeerTeclado.leerString("Escriba el nombre de los animales que desea eliminar: ");
					System.out.println(tienda.eliminarPorNombre(temp).toString());
					break;

				case 4:

					System.out.println(HelperTienda.eliminarPerros((Tienda) tienda, 50));
					break;

				case 5:

					System.out.println(HelperTienda.mostrarAnimales((Tienda) tienda));
					break;

				case 6:

					System.out.println(HelperTienda.eliminarGatos((Tienda) tienda, 8));
					break;

				case 7:

					List<Animal> aux = HelperTienda.obtenerList((Tienda) tienda);
					Collections.sort(aux, (a1, a2) -> ((Animal) a1).getNombre().compareTo(((Animal) a2).getNombre()));
					System.out.println(aux.toString());
					aux.clear();
					break;

				case 8:

					System.out.println("\n\nGuardado\n\n");
					HelperTienda.guardarTienda((Tienda) tienda);
					HelperTienda.guardarErrores(((Tienda) tienda).getNombreTienda(), tiendaErrores);
					break;

				default:

					System.out.println("\n\nLa opcion introducida es erronea\n\n");
					break;
				}

			} catch (TiendaExceptions e) {
				if (tiendaErrores != null) {
					tiendaErrores.add(e.getMessage());
				}
			}

		} while (opc != 0);

	}

}
