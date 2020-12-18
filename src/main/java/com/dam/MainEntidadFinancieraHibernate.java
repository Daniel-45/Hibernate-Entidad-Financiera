package com.dam;

import javax.persistence.EntityManager;

import com.dam.modelos.Banco;
import com.dam.modelos.Cliente;
import com.dam.modelos.CuentaEmpresa;
import com.dam.modelos.CuentaPersonal;
import com.dam.modelos.Local;
import com.dam.modelos.Telefono;
import com.dam.servicios.BuscarCuenta;
import com.dam.servicios.CrearCuenta;
import com.dam.servicios.EliminarCuenta;
import com.dam.servicios.GenerarJSON;
import com.dam.servicios.Ingresar;
import com.dam.servicios.MostrarSaldo;
import com.dam.servicios.MostrarSaldoTotal;
import com.dam.servicios.Retirar;
import com.dam.servicios.Transferencia;
import com.dam.utilidades.AppMenu;
import com.dam.utilidades.EntityManagerFactorySingleton;
import com.dam.utilidades.MenuItem;

/**
 * 
 * @author Daniel
 *
 */

public class MainEntidadFinancieraHibernate extends AppMenu {

	// Atributos
	final MenuItem SALIR = new MenuItem("Salir de la aplicación", 0, () -> {});

	// Constructor
	public MainEntidadFinancieraHibernate() {
		super();
		addOpcion(new MenuItem("Crear cuenta bancaria", 1, new CrearCuenta()));
		addOpcion(new MenuItem("Eliminar cuenta bancaria", 2, new EliminarCuenta()));
		addOpcion(new MenuItem("Buscar una cuenta por clave", 3, new BuscarCuenta()));
		addOpcion(new MenuItem("Retirar dinero de una cuenta", 4, new Retirar()));
		addOpcion(new MenuItem("Ingresar dinero en una cuenta", 5, new Ingresar()));
		addOpcion(new MenuItem("Mostrar el saldo de una cuenta", 6, new MostrarSaldo()));
		addOpcion(new MenuItem("Generar fichero JSON de la entidad", 7, new GenerarJSON()));
		addOpcion(new MenuItem("Realizar una transferencia a una cuenta", 8, new Transferencia()));
		addOpcion(new MenuItem("Mostrar el saldo total de la entidad bancaria", 9, new MostrarSaldoTotal()));
		addOpcion(SALIR);
	}

	// Main
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MainEntidadFinancieraHibernate app = new MainEntidadFinancieraHibernate();

		Banco banco = cargaDatos();

		EntityManager em = EntityManagerFactorySingleton.getInstance("banco").getEmf().createEntityManager();

		em.getTransaction().begin();
		em.persist(banco);
		em.getTransaction().commit();

		em.clear();

		banco.mostrarDatos();

		app.run();

	}

	// Cargar datos
	public static Banco cargaDatos() {

		// Cuenta Empresa 1

		// Telefonos
		Telefono t1 = Telefono.builder().numero("648 11 99 48").compania("Movistar").build();
		Telefono t2 = Telefono.builder().numero("669 97 90 50").compania("Movistar").build();
		Telefono t3 = Telefono.builder().numero("91 676 99 81").compania("Movistar").build();

		// Clientes
		Cliente c1 = Cliente.builder().dni("53016164Z").nombre("Daniel Pompa Pareja").aval(20000).telefono(t1).build();

		Cliente c2 = Cliente.builder().dni("53443162A").nombre("Emma Ciambrino Baz").aval(30000).telefono(t2)
				.telefono(t3).build();

		// Local
		Local l1 = Local.builder().tipo("Propio").build();

		// Cuenta
		CuentaEmpresa ce1 = CuentaEmpresa.builder().ncc("ES-2100-1200-74-7865-9824").saldo(100000).cliente(c1)
				.cliente(c2).nombre("Emma Tech Solutions S.L.").cif("B45806294").local(l1).build();

		// Cuenta Personal 1

		// Telefonos
		Telefono t4 = Telefono.builder().numero("656 68 23 08").compania("Orange").build();

		// Clientes
		Cliente c3 = Cliente.builder().dni("53969852K").nombre("Oscar Pompa Pareja").aval(2000).telefono(t4).build();

		// Cuenta
		CuentaPersonal cp1 = CuentaPersonal.builder().nombre(c3.getNombre()).ncc("ES-2100-1200-76-6854-4306")
				.saldo(5000).cliente(c3).credito(true).build();

		// Cuenta Personal 2

		// Telefonos
		Telefono t5 = Telefono.builder().numero("656 28 68 54").compania("Vodafone").build();

		// Clientes
		Cliente c4 = Cliente.builder().dni("53675835L").nombre("Roberto Pompa Pareja").aval(5000).telefono(t5).build();

		// Cuenta
		CuentaPersonal cp2 = CuentaPersonal.builder().ncc("ES-2100-1200-76-3512-6948").nombre(c4.getNombre())
				.saldo(7000).cliente(c4).credito(true).build();

		// Cuenta Empresa 1

		// Telefonos
		Telefono t6 = Telefono.builder().numero("669 91 14 89").compania("Vodafone").build();
		Telefono t7 = Telefono.builder().numero("656 76 44 33").compania("Movistar").build();
		Telefono t8 = Telefono.builder().numero("91 676 18 90").compania("Movistar").build();
		Telefono t9 = Telefono.builder().numero("656 23 53 80").compania("Orange").build();

		// Clientes
		Cliente c5 = Cliente.builder().dni("53279853K").nombre("Razvan Illies").aval(20000).telefono(t6).telefono(t8)
				.build();

		Cliente c6 = Cliente.builder().dni("53819736C").nombre("Carmen Ciambrino Baz").aval(30000).telefono(t7)
				.telefono(t9).build();

		// Local
		Local l2 = Local.builder().tipo("Alquilado").build();

		// Cuenta
		CuentaEmpresa ce2 = CuentaEmpresa.builder().ncc("ES-2100-1200-74-3801-4263").saldo(30000).cliente(c5)
				.cliente(c6).nombre("Razvan Smart Solutions S.L.").cif("B8560040").local(l2).build();

		// Banco
		Banco banco = Banco.builder().id("01").nombre("BBVA").director("Carlos Martin Moreno").calle("C/ Villablanca")
				.numero(45).ciudad("Madrid").localidad("Vicalvaro").cuenta(ce1).cuenta(cp1).cuenta(cp2).cuenta(ce2)
				.build();

		return banco;
	}

}
