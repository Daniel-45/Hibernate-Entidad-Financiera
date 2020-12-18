package com.dam.servicios;

import com.dam.modelos.Cuenta;
import com.dam.repositorios.GenericJPADAO;
import com.dam.utilidades.Service;

import daw.com.Teclado;

public class Transferencia implements Service {

	@Override
	public void execute() {

		float cantidad;

		System.out.println("\n------------------------ REALIZAR TRANSFERENCIA --------------------------");

		// Cuenta origen
		GenericJPADAO<Cuenta, String> daoCuenta = new GenericJPADAO<Cuenta, String>(Cuenta.class);

		Cuenta c1 = daoCuenta.findById(Teclado.leerString("\nIntroduce el número de cuenta de origen:")).get();

		System.out.println("\nSaldo cuenta de origen: " + c1.getSaldo() + " Euros");

		// Cuenta destino
		Cuenta c2 = daoCuenta.findById(Teclado.leerString("\nIntroduce el número de cuenta de destino:")).get();

		System.out.println("\nSaldo cuenta de destino: " + c2.getSaldo() + " Euros");

		cantidad = Teclado.leerFloat("\nIntroduce la cantidad a transferir:");

		daoCuenta.update(c1);

		c2.ingresar(cantidad);

		daoCuenta.update(c2);

		c1.transferencia(c2, cantidad);

		// Mostrar numero de cuenta y saldo actualizado cuenta origen
		System.out.println("\nSaldo actualizado cuenta origen: " + c1.getSaldo() + " Euros");

		// Mostrar numero de cuenta y saldo actualizado cuenta
		System.out.println("\nSaldo actualizado cuenta destino: " + c2.getSaldo() + " Euros");

		System.out.println("\n--------------------- FINAL REALIZAR TRANSFERENCIA -----------------------");

	}

}
