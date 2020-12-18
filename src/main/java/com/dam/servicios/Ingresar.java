package com.dam.servicios;

import com.dam.modelos.Cuenta;
import com.dam.repositorios.GenericJPADAO;
import com.dam.utilidades.Service;

import daw.com.Teclado;

public class Ingresar implements Service {

	@Override
	public void execute() {

		System.out.println("\n-------------------------- INGRESAR EN CUENTA ----------------------------");

		GenericJPADAO<Cuenta, String> daoCuenta = new GenericJPADAO<Cuenta, String>(Cuenta.class);

		Cuenta c = daoCuenta.findById(Teclado.leerString("\nIntroduce el numero de cuenta:")).get();

		float cantidad = Teclado.leerFloat("\nIntroduce la cantidad a ingresar:");

		c.ingresar(cantidad);

		daoCuenta.update(c);

		System.out.println("\nSaldo actualizado: " + c.getSaldo() + " Euros");

		System.out.println("\n----------------------- FINAL INGRESAR EN CUENTA -------------------------");

	}

}
