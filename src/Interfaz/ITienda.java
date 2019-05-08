package Interfaz;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import Base.Animal;
import Exceptions.TiendaExceptions;

public interface ITienda extends Serializable {

	/**
	 * Inserta un animal.
	 * 
	 * Si el Animal pasado es nulo o se intenta introducir otro Objecto lanza una
	 * Exception de Tienda.
	 * 
	 * @param a Animal
	 * @return boolean
	 * @throws TiendaExceptions
	 */
	public boolean insertarAnimal(Animal a) throws TiendaExceptions;

	/**
	 * Elimina un Animal, cuyo codigo sea igual al pasado por parametros
	 * 
	 * Si la Tienda esta vacia, el codigo es incorrecto ya sea por su longitud o
	 * porque este vacio lanza un error.
	 * 
	 * @param codigo String
	 * @return boolean
	 * @throws TiendaExceptions
	 */
	public boolean eliminarPorCodigo(String codigo) throws TiendaExceptions;

	/**
	 * Elimina los Animales que tengan el nombre pasado por parametro.
	 * 
	 * Si la Tienda esta vacia, el nombre esta vacio o es nulo lanza un Exception de
	 * Tienda.
	 * 
	 * @param nombre
	 * @return List<Animal>
	 * @throws TiendaExceptions
	 */
	public List<Animal> eliminarPorNombre(String nombre) throws TiendaExceptions;

	/**
	 * Devuelve un Iterador de los Animales que contiene la Tienda.
	 * 
	 * Si la Tienda esta vacia lanza una Exception de Tienda
	 * 
	 * @return Iterator<Animal>
	 * @throws TiendaExceptions
	 */
	public Iterator<Animal> iterator() throws TiendaExceptions;

}
