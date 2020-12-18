package com.dam.servicios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dam.modelos.Cuenta;
import com.dam.modelos.CuentaEmpresa;
import com.dam.modelos.CuentaPersonal;
import com.dam.repositorios.GenericJPADAO;
import com.dam.utilidades.Service;

import daw.com.Teclado;

public class MostrarSaldo implements Service {

	@Override
	public void execute() {

		Date fecha = new Date();

		System.out.println("\n-------------------- MOSTRAR SALDO DE UNA CUENTA ------------------------");

		GenericJPADAO<Cuenta, String> daoCuenta = new GenericJPADAO<Cuenta, String>(Cuenta.class);

		Cuenta c = daoCuenta.findById(Teclado.leerString("\nIntroduce el numero de cuenta:")).get();

		DateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		if (c instanceof CuentaPersonal) {

			System.out.println("\nFecha y Hora: " + formatoFechaHora.format(fecha));

			((CuentaPersonal) c).mostrarDatos();

		} else if (c instanceof CuentaEmpresa) {

			System.out.println("\nFecha y Hora: " + formatoFechaHora.format(fecha));

			((CuentaEmpresa) c).mostrarDatos();

		}

		System.out.println("\n----------------- FINAL MOSTRAR SALDO DE UNA CUENTA ---------------------");

	}

}
