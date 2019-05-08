package Utilidades;

public class Utilidades {

	/**
	 * Convierte un Integer en un String, siendo siempre el numero de tamaño 4
	 * 
	 * @param cod
	 * @return String
	 */
	public static String codigo(int cod) {
		String temp = Integer.toString(Math.abs(cod));

		while (temp.length() < 4) {
			temp = "0" + temp;
		}

		return temp.substring(0, 4);
	}

}
