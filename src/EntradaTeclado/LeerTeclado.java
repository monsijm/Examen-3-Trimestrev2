package EntradaTeclado;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LeerTeclado {

	private static Scanner teclado;

	static {
		teclado = new Scanner(System.in);
	}

	public static int leerEntero(String mensaje) {
		int num = 0;
		boolean salir = false;

		do {

			System.out.println(mensaje);

			try {
				num = teclado.nextInt();
				salir = true;
			} catch (InputMismatchException e) {
				System.out.println("Formato de numero erroneo");
			} finally {
				teclado.nextLine();
			}

		} while (!salir);

		return num;
	}

	public static float leerFloat(String mensaje) {
		float num = 0;
		boolean salir = false;

		do {

			System.out.println(mensaje);

			try {
				num = teclado.nextFloat();
				salir = true;
			} catch (InputMismatchException e) {
				System.out.println("Formato de numero erroneo");
			} finally {
				teclado.nextLine();
			}

		} while (!salir);

		return num;
	}

	public static double leerDouble(String mensaje) {
		double num = 0;
		boolean salir = false;

		do {

			System.out.println(mensaje);

			try {
				num = teclado.nextDouble();
				salir = true;
			} catch (InputMismatchException e) {
				System.out.println("Formato de numero erroneo");
			} finally {
				teclado.nextLine();
			}

		} while (!salir);

		return num;
	}

	public static long leerLong(String mensaje) {
		long num = 0;
		boolean salir = false;

		do {

			System.out.println(mensaje);

			try {
				num = teclado.nextLong();
				salir = true;
			} catch (InputMismatchException e) {
				System.out.println("Formato de numero erroneo");
			} finally {
				teclado.nextLine();
			}

		} while (!salir);

		return num;
	}

	public static String leerString(String mensaje) {
		String temp = "";
		boolean salir = false;
		do {

			System.out.println(mensaje);

			try {
				temp = teclado.nextLine();
				salir = true;
			} catch (Exception e) {
				System.out.println("Formato erroneo");
			}

		} while (!salir);

		return temp;
	}

	public static char leerChar(String mensaje) {
		char temp = 0;
		boolean salir = false;
		do {

			System.out.println(mensaje);

			try {
				temp = teclado.next().charAt(0);
				salir = true;
			} catch (Exception e) {
				System.out.println("Formato erroneo");
			} finally {
				teclado.nextLine();
			}

		} while (!salir);

		return temp;
	}

}
