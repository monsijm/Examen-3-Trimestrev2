package Base;

public class Gato extends Animal {

	// AUTO-GENERATE
	private static final long serialVersionUID = 1L;

	private int edad = 0;

	public Gato(String nombre, int edad) {
		super(nombre);
		if (edad > 0 && edad < 15) {
			this.edad = edad;
		}
	}

	@Override
	public String toString() {
		StringBuffer temp = new StringBuffer(super.toString());

		if (edad != 0) {
			temp.append(" y tiene " + edad + " anios");
		} else {
			temp.append(" no se le añadio el valor al Gato");
		}

		return temp.toString();
	}

	public int getEdad() {
		return edad;
	}

	// Aseguramos que la edad solo se pueda modificar, si es un numero mayor al
	// pasado
	public void setEdad(int edad) {
		if (edad == this.edad + 1) {
			this.edad = edad;
		}
	}

}
