package com.dam.servicios;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.dam.modelos.Cuenta;
import com.dam.utilidades.EntityManagerFactorySingleton;
import com.dam.utilidades.Service;

public class MostrarSaldoTotal implements Service {

	@Override
	public void execute() {

		System.out.println("\n----------------- SUMA TOTAL DE SALDOS DE LAS CUENTAS -------------------");

		EntityManager em = EntityManagerFactorySingleton.getInstance("banco").getEmf().createEntityManager();

		Query query = em.createQuery("SELECT SUM(c.saldo) FROM Cuenta c");

		System.out.println();

		Number totalSaldoBanco = (Number) query.getSingleResult();

		Set<Cuenta> cuentas = new HashSet<Cuenta>();

		for (Cuenta c : cuentas) {

			System.out.println("\nNumero de cuenta: " + c.getNcc() + " - " + "Saldo: " + c.getSaldo() + " Euros");
		}

		System.out.println("\nLa suma total de los saldos de las cuentas del banco es: " + totalSaldoBanco + " Euros");

		System.out.println("\n-------------- FINAL SUMA TOTAL DE SALDOS DE LAS CUENTAS ----------------");

	}

}
