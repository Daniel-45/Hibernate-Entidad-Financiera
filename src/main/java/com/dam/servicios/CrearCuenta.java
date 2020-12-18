package com.dam.servicios;

import java.util.HashSet;
import java.util.Set;

import com.dam.modelos.Cuenta;
import com.dam.modelos.CuentaEmpresa;
import com.dam.modelos.CuentaPersonal;
import com.dam.repositorios.GenericJPADAO;
import com.dam.utilidades.Service;

import daw.com.Teclado;

public class CrearCuenta implements Service {

	@Override
	public void execute() {

		Cuenta c = null;

		String tipo;

		Set<Cuenta> cuentas = new HashSet<Cuenta>();

		GenericJPADAO<Cuenta, String> daoCuenta = new GenericJPADAO<Cuenta, String>(Cuenta.class);

		do {

			tipo = Teclado.leerString("\nTipo de cuenta - Personal/Empresa:");

			while (!tipo.equalsIgnoreCase("Personal") && !tipo.equalsIgnoreCase("Empresa")) {

				System.out.println("\nERROR!! Respuesta no válida.");

				System.out.println("\nPor favor introduce Personal/Empresa");

				tipo = Teclado.leerString("\nTipo de cuenta - Personal/Empresa:");
			}

			if (tipo.equalsIgnoreCase("Personal")) {

				c = new CuentaPersonal();

			} else if (tipo.equalsIgnoreCase("Empresa")) {

				c = new CuentaEmpresa();
			}

			c.leerDatos();

			cuentas.add(c);

			daoCuenta.save(c);

		} while (Teclado.leerString("\n¿Quieres seguir añadiendo cuentas? S/N").equalsIgnoreCase("S"));

		System.out.println();

	}

}
