package com.dam.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.google.gson.annotations.SerializedName;

import daw.com.Teclado;
import lombok.AllArgsConstructor;
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
@DiscriminatorValue(value = "CUENTA_EMPRESA")
public class CuentaEmpresa extends Cuenta implements Serializable {

	// Atributos
	@Transient
	public transient static final float COMISION = 0.001f;

	@Transient
	public transient static final float MAXIMOCOMISION = 6f;

	@Column(name = "EMPRESA", length = 30)
	@SerializedName("Cuenta Empresa")
	private String nombre;

	@Column(length = 10)
	private String cif;

	@Embedded
	private Local local;

	private static final long serialVersionUID = 1L;

	// Métodos

	// Leer datos
	public void leerDatos() {

		super.leerDatos();

		nombre = Teclado.leerString("\nEmpresa:");

		cif = Teclado.leerString("\nCIF:");

		local = new Local();

		local.leerDatos();

	}

	// Mostrar datos
	public void mostrarDatos() {

		System.out.println("\n--------------------------- CUENTA DE EMPRESA ----------------------------");

		super.mostrarDatos();

		System.out.println("\nEmpresa: " + nombre);

		System.out.println("\nCIF: " + cif);

		local.mostrarDatos();

		System.out.println("\n------------------------ FINAL CUENTA DE EMPRESA -------------------------\n");
	}

	// Calcular el máximo negativo permitido
	@Override
	public float maximoNegativo() {

		float maximo = 0;

		maximo = totalAvales() * 2;

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
