package Base;

public class Perro extends Animal {

	// AUTO-GENERATE
	private static final long serialVersionUID = 1L;

	private int longitud = 0;

	public Perro(String nombre,int longitud) {
		super(nombre);

		if (longitud > 0 && longitud < 1000) {
			this.longitud = longitud;
		}

	}

	@Override
	public String toString() {
		StringBuffer temp = new StringBuffer(super.toString());
		if (longitud != 0) {
			temp.append(" y tiene " + longitud + " cm");
		} else {
			temp.append(" no se le añadio el valor al Perro");
		}

		return temp.toString();
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		if (longitud > this.longitud && longitud < 1000) {
			this.longitud = longitud;
		}
	}
}
