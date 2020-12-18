package com.dam.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import daw.com.Pantalla;
import daw.com.Teclado;

public class Libreria {

	// Pide un numero hasta que sea positivo
	public static int leerPositivo(String txt) {
		int n;

		do {
			n = Teclado.leerInt(txt);
		} while (n < 0);

		return n;
	}

	public static float leerPositivoFloat(String txt) {
		float n;

		do {
			n = Teclado.leerFloat(txt);
		} while (n < 0);

		return n;
	}

	/**
	 * Pide un numero que tiene que estar entre un inicio y un final. 
	 * Hay que introducir el mensaje a mostrar
	 */
	public static int leerEntre(int inicio, int fin, String mensaje) {
		int resultado;

		do {
			resultado = Teclado.leerInt(mensaje);
		} while (resultado > fin || resultado < inicio);

		return resultado;
	}

	// Dando segundos te lo muestra en pantalla en formato (hh:mm:ss)
	public static void mostrarHora(int s) {
		String resultado;

		int h, m;

		h = s / 3600;
		s = s % 3600;
		m = s / 60;
		s = s % 60;

		resultado = h + ":" + m + ":" + s;

		System.out.println(resultado);
	}

	// Genera un numero aleatorio
	public static int generarAleatorio(int inicio, int fin) {
		int valor;

		valor = (int) ((Math.random() * fin) + inicio);

		return valor;
	}

	// Dando segundos, te los devuelve en formato hora(hh:mm:ss) como string
	public static String calcularHora(int s) {
		String resultado;

		int h, m;

		h = s / 3600;

		s = s % 3600;

		m = s / 60;

		s = s % 60;

		resultado = h + ":" + m + ":" + s;

		return resultado;
	}

	// Convierte una hora en (hh/mm/ss) en segundos
	public static int convertirASegundos(int h, int m, int s) {
		int resultado = 0;

		resultado = (h * 3600) + (m * 60) + s;

		return resultado;
	}

	/**
	 * Da un número aleatorio desde 1, introduciendo el máximo primero. 
	 * Luego desde un limite que se introduce devuelve solo 0.5
	 */
	public static float aleatorioLimite(int maximo, int limite) {
		float resultado;

		resultado = (int) ((Math.random() * maximo) + 1);

		if (resultado > limite) {
			resultado = (float) 0.5;
		}

		return resultado;
	}

	// Pide como respuesta un si o un no, los comprueba y los devuelve en minuscula
	public static String siNo(String txt) {
		String resultado;

		do {
			resultado = Teclado.leerString(txt).toLowerCase();
		} while (!((resultado.equalsIgnoreCase("Si")) || (resultado.equalsIgnoreCase("No"))));

		return resultado;
	}

	public static int[] cargarArray(String txt, String txt2) {
		int tamanio, array[];

		tamanio = Libreria.leerPositivo(txt);

		array = new int[tamanio];

		Libreria.leerArray(array, txt2);

		return array;
	}

	// Pide un array ya inicializado y un texto para pedir los valores de un array
	public static void leerArray(int array[], String txt) {
		for (int i = 0; i < array.length; i++) {
			array[i] = leerPositivo(txt);
		}
	}

	// Lo mismo que el anterior pero con un array tipo String
	public static void leerArrayString(String array[], String txt) {
		for (int i = 0; i < array.length; i++) {
			array[i] = Teclado.leerString(txt);
		}
	}

	// Comprueba si una String se encuentra dentro de un array String
	public static boolean estaArrayString(String palabra, String array[]) {
		boolean respuesta = false;

		for (int i = 0; i < array.length && respuesta == false; i++) {
			if (palabra.equalsIgnoreCase(array[i])) {
				respuesta = true;
			}
		}

		return respuesta;
	}

	// Devuelve el número de veces que esta repetida una plabra en un array String
	public static int estaArrayStringVeces(String palabra, String array[]) {
		int nveces = 0;

		for (int i = 0; i < array.length; i++) {
			if (palabra.equalsIgnoreCase(array[i])) {
				nveces += 1;
			}
		}

		return nveces;
	}

	// Devuelve el número de veces que esta repetido un n�mero (int)
	public static boolean estaArray(int num, int array[]) {
		boolean respuesta = false;

		for (int i = 0; i < array.length && respuesta == false; i++) {
			if (num == array[i]) {
				respuesta = true;
			}
		}

		return respuesta;
	}

	// Copia un array en orden invertido
	public static int[] copiarArrayInverso(int array1[]) {
		int array2[], nArray;

		nArray = array1.length;

		array2 = new int[nArray];

		for (int i = array1.length - 1, j = 0; j < array2.length; i--, j++) {
			array2[j] = array1[i];
		}

		return array2;

	}

	// Lee entre un rango de números y muestra n aviso.
	public static int leerEntreAviso(int inicio, int fin, String mensaje, String aviso) {
		int resultado;

		do {
			resultado = Teclado.leerInt(mensaje);
			if (resultado < inicio || resultado > fin) {
				System.out.println();
				System.out.println(aviso);
				System.out.println();
			}
		} while (resultado > fin || resultado < inicio);

		return resultado;
	}

	// Comprueba que dos arrays sean iguales, tanto en valor como en longitud
	public static boolean arrayIgual(int array1[], int array2[]) {
		boolean respuesta = true;

		if (array1.length != array2.length) {
			respuesta = false;
		}

		for (int i = 0; i < array1.length && respuesta; i++) {
			if (array1[i] != array2[i]) {
				respuesta = false;
			}
		}

		return respuesta;
	}

	// Saca el mayor valor dentro de un array
	public static int mayorEnArray(int array[]) {
		int n = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] > n) {
				n = array[i];
			}
		}

		return n;
	}

	// Devulve la posición del mayor valor del array
	public static int mayorPosicionArray(int array[]) {
		int n = 0, aux = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] > aux) {
				aux = array[i];
				n = i + 1;
			}
		}

		return n;
	}

	public static boolean esPrimo(int n) {
		boolean primo = true;
		for (int i = 2; i <= n / 2 && primo; i++) {
			if (n % i == 0) {
				primo = false;
			}
		}

		return primo;
	}

	// Ordena un Array de menor a mayor
	public static void ordenarArray(int array[]) {

		int aux = 0;

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					aux = array[i];
					array[i] = array[j];
					array[j] = aux;
				}
			}
		}
	}

	// Muestra el array
	public static void mostrarArrayBidimensional(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println();
			for (int j = 0; j < a[i].length; j++) {
				System.out.println("\t" + a[i][j]);
			}
		}
	}

	// Muestra el array
	public static void mostrarArrayBidimensionalFloat(float[][] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println();
			for (int j = 0; j < a[i].length; j++) {
				System.out.println("\t" + a[i][j]);
			}
		}
	}

	// Carga el array
	public static int[][] cargarArrayBidimesional(int filas, int columnas) {
		int arrayBidimensional[][] = new int[filas][columnas];

		for (int i = 0; i < arrayBidimensional.length; i++) {
			for (int j = 0; j < arrayBidimensional[i].length; j++) {
				arrayBidimensional[i][j] = Libreria.leerPositivo("elemento " + i + "-" + j + " : ");
			}
		}

		return arrayBidimensional;
	}

	// Limpia el array
	public static void limpiarArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = 0;
		}
	}

	// Limpia el array
	public static void limpiarArrayString(String[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = "";
		}
	}

	// Limpia el array
	public static void limpiarArrayFloat(float[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = 0;
		}
	}

	// Lee una fecha con formato (dd-mm-yyyy)
	public static Date leerFecha() {
		Date fecha = null;

		String f;

		f = Teclado.leerString("Introduce la fecha (dd-mm-yyyy):");

		SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy");

		try {
			fecha = dt.parse(f);
		} catch (ParseException e) {
			Pantalla.escribirString("\nERROR!! Leyendo la fecha.");
		}

		return fecha;
	}

}
