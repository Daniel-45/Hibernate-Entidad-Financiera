package com.dam.servicios;

import com.dam.modelos.Cuenta;
import com.dam.modelos.CuentaEmpresa;
import com.dam.modelos.CuentaPersonal;
import com.dam.repositorios.GenericJPADAO;
import com.dam.utilidades.Service;

import daw.com.Teclado;

public class BuscarCuenta implements Service {

	@Override
	public void execute() {

		System.out.println("\n------------------------ MOSTRAR DATOS CUENTA ---------------------------");

		GenericJPADAO<Cuenta, String> daoCuenta = new GenericJPADAO<Cuenta, String>(Cuenta.class);

		Cuenta c = daoCuenta.findById(Teclado.leerString("\nIntroduce el numero de cuenta a mostrar:")).get();

		if (c instanceof CuentaPersonal) {

			((CuentaPersonal) c).mostrarDatos();

		} else if (c instanceof CuentaEmpresa) {

			((CuentaEmpresa) c).mostrarDatos();
		}

		System.out.println("\n--------------------- FINAL  MOSTRAR DATOS CUENTA ------------------------");
	}

}
