package com.dam.servicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.dam.MainEntidadFinancieraHibernate;
import com.dam.modelos.Banco;
import com.dam.utilidades.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GenerarJSON implements Service {

	@Override
	public void execute() {

		Banco banco = MainEntidadFinancieraHibernate.cargaDatos();

		Gson creador = new GsonBuilder().setPrettyPrinting().create();

		// Guardar en un String el objeto (banco)
		String jsonCadena = creador.toJson(banco);

		// Mostrar por consola el json creado
		System.out.println(jsonCadena);

		PrintWriter ficheroBanco;

		try {
			ficheroBanco = new PrintWriter(new File("files/banco.json"));

			// Convertir objetos Java a JSON
			creador.toJson(banco, ficheroBanco);

			// IMPORTANTE!! Hay que cerrar el fichero
			ficheroBanco.close();

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();

		}
	}

}
