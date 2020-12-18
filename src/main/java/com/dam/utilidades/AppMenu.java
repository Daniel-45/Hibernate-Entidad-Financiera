package com.dam.utilidades;

import java.util.ArrayList;
import java.util.Iterator;

import daw.com.Teclado;

public class AppMenu {

	// Atributos
	private ArrayList<MenuItem> opciones;

	// Constructor
	public AppMenu() {

		opciones = new ArrayList<MenuItem>();
	}

	// Métodos
	public Iterator<MenuItem> getOpciones() {

		return opciones.iterator();
	}

	public boolean addOpcion(MenuItem opcion) {

		boolean insertado = false;

		if (!opciones.contains(opcion)) {

			opciones.add(opcion);

			insertado = true;
		}
		return insertado;
	}

	public boolean removeOpcion(MenuItem opcion) {

		return opciones.remove(opcion);
	}

	public boolean evaluarOpcion(int opc) {

		boolean exito = false;

		MenuItem item = getOpcion(opc);

		if (item != null) {

			exito = true;

			item.getAction().execute();
		}

		return exito;
	}

	public void mostrarOpciones() {

		for (MenuItem opcion : opciones) {

			System.out.println(opcion.getOpcion() + " . " + opcion.getMensaje());
		}
	}

	public int leerOpcion() {
		int opcion;

		do {

			opcion = Teclado.leerInt("\n\nSelecciona una opción:");

		} while (!valorCorrecto(opcion));

		return opcion;
	}

	public boolean valorCorrecto(int opcion) {

		boolean correcto = false;

		MenuItem item = getOpcion(opcion);

		if (item != null) {

			correcto = true;
		}

		return correcto;
	}

	public void run() {

		int opcion;

		do {

			mostrarOpciones();

			opcion = leerOpcion();

			evaluarOpcion(opcion);

		} while (!salir(opcion));

	}

	public boolean salir(int opcion) {
		boolean fin = false;

		MenuItem item = getOpcion(opcion);

		if (item != null && item.getOpcion() == 0) {

			fin = true;
		}

		return fin;

	}

	public MenuItem getOpcion(int opcion) {

		MenuItem item = null;

		for (int i = 0; i < opciones.size() && item == null; i++) {

			if (opciones.get(i).getOpcion() == opcion) {

				item = opciones.get(i);
			}
		}

		return item;
	}

}
