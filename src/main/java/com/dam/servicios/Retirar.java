package com.dam.servicios;

import com.dam.modelos.Cuenta;
import com.dam.repositorios.GenericJPADAO;
import com.dam.utilidades.Service;

import daw.com.Teclado;

public class Retirar implements Service {

	@Override
	public void execute() {

		System.out.println("\n-------------------------- RETIRAR DE CUENTA -----------------------------");

		GenericJPADAO<Cuenta, String> daoCuenta = new GenericJPADAO<Cuenta, String>(Cuenta.class);

		Cuenta c = daoCuenta.findById(Teclado.leerString("\nIntroduce el numero de cuenta:")).get();

		float cantidad = Teclado.leerFloat("\nIntroduce la cantidad a retirar:");

		c.retirar(cantidad);

		daoCuenta.update(c);

		System.out.println("\nSaldo actualizado: " + c.getSaldo() + " Euros");

		System.out.println("\n----------------------- FINAL RETIRAR DE CUENTA --------------------------");
	}

}
