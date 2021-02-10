package com.dam.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import daw.com.Teclado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Daniel
 *
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TELEFONOS_CLIENTES_HIBERNATE")
public class Telefono implements Serializable {

	// Atributos
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "TELEFONOS", length = 20)
	private String numero;

	@Column(name = "COMPAÑIA", length = 20)
	private String compania;

	private static final long serialVersionUID = 1L;

	// Métodos

	// Leer datos
	public void leerDatos() {

		numero = Teclado.leerString("\nIntroduce el número de telefono:");

		compania = Teclado.leerString("\nIntroduce la compañía de telefonía:");
	}

	// Mostrar datos
	public void mostrarDatos() {

		System.out.println("\nNúmero de teléfono: " + numero);

		System.out.println("\nCompañía telefónica: " + compania);
	}

}
