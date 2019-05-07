package Coleccion;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import Base.Animal;
import Exceptions.TiendaExceptions;

public interface ITienda extends Serializable{

	public boolean insertarAnimal(Animal a) throws TiendaExceptions;
	
	public boolean eliminarPorCodigo(String codigo) throws TiendaExceptions;
	
	public List<Animal> eliminarPorNombre(String nombre) throws TiendaExceptions;
	
	public Iterator<Animal> iterator() throws TiendaExceptions;
	
}
