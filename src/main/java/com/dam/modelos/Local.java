package com.dam.modelos;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.google.gson.annotations.SerializedName;

import daw.com.Teclado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Daniel
 *
 */

//Indica que este POJO será parte de otra entidad
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Local implements Serializable {

	// Atributos
	@SerializedName("local")
	private String tipo;

	private static final long serialVersionUID = 1L;

	// Métodos

	// Leer datos
	public void leerDatos() {

		tipo = Teclado.leerString("\n¿La empresa tiene local propio o alquilado?");

		do {

			if (!tipo.equalsIgnoreCase("Propio") && !tipo.equalsIgnoreCase("Alquilado")) {

				System.out.println("\nERROR!! Respuesta no válida.");

				System.out.println("\nPor favor introduce Propio/Alquilado");

				tipo = Teclado.leerString("\n¿La empresa tiene local propio o alquilado?");
			}

		} while (!tipo.equalsIgnoreCase("Propio") && !tipo.equalsIgnoreCase("Alquilado"));
	}

	// Mostrar datos
	public void mostrarDatos() {

		System.out.println("\nTipo de local: " + tipo);
	}

}
