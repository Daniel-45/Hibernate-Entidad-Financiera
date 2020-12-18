package com.dam.servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.dam.utilidades.EntityManagerFactorySingleton;
import com.dam.utilidades.Service;

public class ClienteMayorSaldo implements Service {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {

		System.out.println("\n---------------- CLIENTE CON SALDO MAS ALTO DEL BANCO --------------------");

		EntityManager em = EntityManagerFactorySingleton.getInstance("banco").getEmf().createEntityManager();

		Query query = em.createQuery(
				"SELECT DISTINCT cliente.nombre, SUM(cuenta.saldo) FROM Cuenta cuenta INNER JOIN cuenta.clientes cliente "
						+ "GROUP BY cliente.dni ORDER BY SUM(cuenta.saldo) DESC");

		List<Object[]> listaDatos = query.getResultList();

		for (Object[] datos : listaDatos) {

			System.out.println("\nNombre: " + datos[0] + " Saldo: " + datos[1] + " Euros");
		}

		System.out.println("\n------------- FINAL CLIENTE CON SALDO MAS ALTO DEL BANCO -----------------");
	}

}
