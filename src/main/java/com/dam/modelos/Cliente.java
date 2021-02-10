package com.dam.modelos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import daw.com.Teclado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

/**
 * 
 * @author Daniel
 *
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "CLIENTES_HIBERNATE")
public class Cliente implements Serializable {

	// Atributos
	@Id
	@NonNull
	@Column(length = 10)
	@EqualsAndHashCode.Include
	private String dni;

	@Column(length = 25)
	private String nombre;

	private float aval;

	@Singular
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// Evitar tabla intermedia Cliente - Agenda
	@JoinColumn(name = "FK_CLIENTE_DNI")
	private Set<Telefono> telefonos;

	private static final long serialVersionUID = 1L;

	// Métodos

	// Leer datos
	public void leerDatos() {

		dni = Teclado.leerString("\nDNI cliente:");

		nombre = Teclado.leerString("\nNombre cliente:");

		aval = Teclado.leerFloat("\nAval cliente:");

		String seguir = "";

		telefonos = new HashSet<Telefono>();

		do {

			Telefono agenda = new Telefono();

			agenda.leerDatos();

			telefonos.add(agenda);

			seguir = Teclado.leerString("\n¿Quieres insertar más teléfonos? S/N:");

			if (!seguir.equalsIgnoreCase("S") && !seguir.equalsIgnoreCase("N")) {

				System.out.println("\nERROR!! Respuesta no válida.");

				System.out.println("\nPor favor introduce S/N");

				seguir = Teclado.leerString("\n¿Quieres insertar más teléfonos? S/N:");
			}

		} while (seguir.equalsIgnoreCase("S"));
	}

	// Mostrar datos
	public void mostrarDatos() {

		System.out.println("\n-------------------------------- CLIENTE ---------------------------------");

		System.out.println("\nDNI: " + dni);

		System.out.println("\nNombre: " + nombre);

		System.out.println("\nAval: " + aval + " Euros");

		for (Telefono a : telefonos) {

			a.mostrarDatos();
		}

		System.out.println("\n----------------------------- FINAL CLIENTE ------------------------------");
	}

}
