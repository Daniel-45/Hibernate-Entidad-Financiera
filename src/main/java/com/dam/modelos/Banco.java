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

import com.google.gson.annotations.SerializedName;

import daw.com.Teclado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ENTIDADES_FINANCIERAS")
public class Banco implements Serializable {

	// Atributos
	@Id
	@EqualsAndHashCode.Include
	private String id;

	@Column(name = "ENTIDAD_FINANCIERA", length = 20)
	@SerializedName("banco")
	private String nombre;

	@Column(length = 20)
	private String director;

	private String calle;

	private int numero;

	private String ciudad;

	private String localidad;

	@Singular
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// Evitar tabla intermedia Banco - Cuentas
	@JoinColumn(name = "FK_ID_BANCO")
	private Set<Cuenta> cuentas;

	private static final long serialVersionUID = 1L;

	// Métodos

	// Leer datos
	public void leerDatos() {

		id = Teclado.leerString("\nID de la entidad:");

		nombre = Teclado.leerString("\nNombre de la entidad:");

		director = Teclado.leerString("\nDirector de la entidad:");

		calle = Teclado.leerString("\nIntroduce la calle:");

		numero = Teclado.leerInt("\nIntroduce el número:");

		ciudad = Teclado.leerString("\nIntroduce la ciudad:");

		localidad = Teclado.leerString("\nIntroduce la localidad:");

		String tipo;

		Cuenta c = null;

		cuentas = new HashSet<Cuenta>();

		do {

			tipo = Teclado.leerString("\nTipo de cuenta - Personal/Empresa:");

			while (!tipo.equalsIgnoreCase("Personal") && !tipo.equalsIgnoreCase("Empresa")) {

				System.out.println("\nERROR!! Respuesta no válida.");

				System.out.println("\nPor favor introduce Personal/Empresa");

				tipo = Teclado.leerString("\nTipo de cuenta - Personal/Empresa:");
			}

			if (tipo.equalsIgnoreCase("Personal")) {
				c = new CuentaPersonal();
				c.leerDatos();
			} else if (tipo.equalsIgnoreCase("Empresa")) {
				c = new CuentaEmpresa();
				c.leerDatos();
			}

			cuentas.add(c);

		} while (Teclado.leerString("\n¿Quieres seguir añadiendo cuentas? S/N").equalsIgnoreCase("S"));
	}

	// Mostrar datos
	public void mostrarDatos() {

		System.out.println("\n-------------------- MOSTRAR DATOS ENTIDAD FINANCIERA --------------------");

		System.out.println("\nID de la entidad: " + id);

		System.out.println("\nNombre de la entidad: " + nombre);

		System.out.println("\nDirector de la entidad: " + director);

		System.out.println("\nCalle: " + calle);

		System.out.println("\nNúmero: " + numero);

		System.out.println("\nCiudad: " + ciudad);

		System.out.println("\nLocalidad: " + localidad);

		for (Cuenta c : cuentas) {

			if (c instanceof CuentaPersonal) {

				((CuentaPersonal) c).mostrarDatos();
			} else if (c instanceof CuentaEmpresa) {

				((CuentaEmpresa) c).mostrarDatos();
			}
		}

		System.out.println("\n----------------- FINAL MOSTRAR DATOS ENTIDAD FINANCIERA -----------------");
	}

}
