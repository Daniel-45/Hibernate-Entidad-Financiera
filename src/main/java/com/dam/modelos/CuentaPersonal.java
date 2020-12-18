package com.dam.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.google.gson.annotations.SerializedName;

import daw.com.Teclado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 
 * @author Daniel
 *
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "CUENTA_PERSONAL")
public class CuentaPersonal extends Cuenta implements Serializable {

	// Atributos
	@Transient
	public transient static final float COMISION = 0.002f;

	@Transient
	public transient static final float MAXIMOCOMISION = 4f;

	@SerializedName("Cuenta Personal")
	@Builder.Default
	private String nombre = " ";

	@Column(name = "TARJETA_CREDITO")
	private boolean credito;

	private static final long serialVersionUID = 1L;

	// Métodos

	// Leer datos
	public void leerDatos() {

		super.leerDatos();

		String respuesta = Teclado.leerString("\n¿Tiene tarjeta de credito? S/N:");

		do {

			if (!respuesta.equalsIgnoreCase("S") && !respuesta.equalsIgnoreCase("N")) {

				System.out.println("\nERROR!! Respuesta no válida.");

				System.out.println("\nPor favor introduce S/N");

				respuesta = Teclado.leerString("\n¿Tiene tarjeta de credito? S/N:");
			}

			if (respuesta.equalsIgnoreCase("S")) {
				credito = true;
			} else {
				credito = false;
			}

		} while (!respuesta.equalsIgnoreCase("S") && !respuesta.equalsIgnoreCase("N"));

	}

	// Mostrar datos
	public void mostrarDatos() {

		System.out.println("\n---------------------------- CUENTA PERSONAL -----------------------------");

		super.mostrarDatos();

		System.out.println(nombre);

		if (credito == true) {

			System.out.println("\nEmitida tarjeta de credito: SI");
		} else {

			System.out.println("\nEmitida tarjeta de credito: NO");
		}

		System.out.println("\n------------------------- FINAL CUENTA PERSONAL --------------------------\n");
	}

	// Calcular el máximo negativo permitido
	@Override
	public float maximoNegativo() {

		float maximo = 0;

		maximo = totalAvales() / 2;

		return -maximo;
	}

	// Calcular la comisión por transferencia
	@Override
	public float calcularComision(float cantidad) {

		float comision = 0;

		if (cantidad < 0) {

			cantidad = 0;
		}

		comision = cantidad * COMISION;

		if (comision > MAXIMOCOMISION) {

			comision = MAXIMOCOMISION;
		}

		return comision;
	}

}
