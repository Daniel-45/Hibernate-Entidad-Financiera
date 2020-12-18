package com.dam.utilidades;

public class MenuItem {

	// Atributos
	private String mensaje;
	private int opcion;
	private Service action;

	// Constructores
	public MenuItem() {
		this("", 0, () -> {
		});
	}

	public MenuItem(String mensaje, int opcion, Service action) {
		this.mensaje = mensaje;
		this.opcion = opcion;
		this.action = action;
	}

	// Selectores y modificadores
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getOpcion() {
		return opcion;
	}

	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}

	public Service getAction() {
		return action;
	}

	public void setAction(Service action) {
		this.action = action;
	}

	// Métodos
	@Override
	public String toString() {
		return "MenuItem [Mensaje = " + mensaje + ", Opcion = " + opcion + ", Action ="
				+ action.getClass().getSimpleName() + "]";
	}

	@Override
	public boolean equals(Object o) {

		MenuItem menuItem = (MenuItem) o;

		return menuItem.opcion == this.opcion;
	}
}
