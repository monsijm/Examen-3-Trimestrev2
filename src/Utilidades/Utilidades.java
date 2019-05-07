package Utilidades;

public class Utilidades {

	public static String codigo(int cod) {
		String temp = Integer.toString(Math.abs(cod));

		if (temp.length() >= 4) {
			temp = temp.substring(0, 4);
		} else {
			while (temp.length() < 4) {
				temp = "0" + temp;
			}
		}

		return temp;
	}

}
