package com.dam.modelos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import daw.com.Teclado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "CUENTAS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DIS", discriminatorType = DiscriminatorType.STRING)
public abstract class Cuenta implements Serializable {

	// Atributos
	@Id
	@EqualsAndHashCode.Include
	@Column(length = 25)
	private String ncc;

	private float saldo;

	@Singular
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// Evitar tabla intermedia Cuenta - Cliente
	@JoinColumn(name = "FK_NCC_CUENTA")
	private Set<Cliente> clientes;

	private static final long serialVersionUID = 1L;

	// Métodos

	// Leer datos
	public void leerDatos() {

		ncc = Teclado.leerString("\nNúmero de cuenta:");

		saldo = Teclado.leerFloat("\nSaldo:");

		String seguir = "";

		clientes = new HashSet<Cliente>();

		do {

			Cliente cliente = new Cliente();

			cliente.leerDatos();

			clientes.add(cliente);

			seguir = Teclado.leerString("\n¿Quieres añadir más clientes a la cuenta? S/N:");

			if (!seguir.equalsIgnoreCase("S") && !seguir.equalsIgnoreCase("N")) {

				System.out.println("\nERROR!! Respuesta no válida.");
				System.out.println("\nPor favor introduce S/N");

				seguir = Teclado.leerString("\n¿Quieres añadir más clientes a la cuenta? S/N:");
			}

		} while (seguir.equalsIgnoreCase("S"));
	}

	// Mostrar datos
	public void mostrarDatos() {

		System.out.println("\nNumero de cuenta: " + ncc);

		System.out.println("\nSaldo: " + saldo + " Euros");

		for (Cliente c : clientes) {

			c.mostrarDatos();
		}
	}

	// Calcular total avales clientes
	public float totalAvales() {

		float total = 0;

		for (Cliente c : clientes) {

			total += c.getAval();
		}

		return total;
	}

	// Ingresar dinero en cuenta
	public float ingresar(float cantidad) {

		if (cantidad < 0) {

			cantidad = 0;
		}

		return saldo += cantidad;
	}

	// Retirar dinero de cuenta
	public boolean retirar(float cantidad) {

		boolean exito = true;

		if (cantidad < 0) {

			cantidad = 0;
		}

		if (saldo - cantidad >= maximoNegativo()) {

			saldo -= cantidad;
		} else {

			exito = false;
		}

		return exito;
	}

	// Transferir dinero a una cuenta
	public boolean transferencia(Cuenta cuentaDestino, float cantidad) {

		boolean exito = true;

		float comision;

		if (cantidad < 0) {

			cantidad = 0;
		}

		if (retirar(cantidad)) {

			comision = calcularComision(cantidad);

			saldo -= comision;
		} else {
			exito = false;
		}

		return exito;
	}

	// Calcular el máximo negativo permitido
	public abstract float maximoNegativo();

	// Calcular la comisión por transferencia
	public abstract float calcularComision(float cantidad);
}
