package com.dam.servicios;

import com.dam.modelos.Cuenta;
import com.dam.repositorios.GenericJPADAO;
import com.dam.utilidades.Service;

import daw.com.Teclado;

public class EliminarCuenta implements Service {

	@Override
	public void execute() {

		System.out.println("\n--------------------------- ELIMINAR CUENTA ------------------------------");

		GenericJPADAO<Cuenta, String> daoCuenta = new GenericJPADAO<Cuenta, String>(Cuenta.class);

		Cuenta c = daoCuenta.findById(Teclado.leerString("\nIntroduce el numero de cuenta a eliminar:")).get();

		daoCuenta.delete(c);

		System.out.println("\n------------------------ FINAL ELIMINAR CUENTA ---------------------------");
	}

}
