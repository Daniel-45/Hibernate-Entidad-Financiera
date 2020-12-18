package com.dam.repositorios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.dam.utilidades.EntityManagerFactorySingleton;

public class GenericJPADAO<T, K> implements DAOInterface<T, K> {

	private final static String PERSITENCEUNITNAME = "instituto";

	private Class<T> entityClass;

	public GenericJPADAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public Optional<T> findById(K key) {
		// TODO Auto-generated method stub

		EntityManagerFactory emFactory = EntityManagerFactorySingleton.getInstance(PERSITENCEUNITNAME).getEmf();

		EntityManager em = emFactory.createEntityManager();

		Optional<T> result = Optional.ofNullable(em.find(entityClass, key));

		em.close();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<T> findAll() {
		// TODO Auto-generated method stub
		List<T> result;
		String jpaQuery;

		EntityManagerFactory emFactory = EntityManagerFactorySingleton.getInstance(PERSITENCEUNITNAME).getEmf();

		EntityManager em = emFactory.createEntityManager();

		jpaQuery = "SELECT o FROM " + entityClass.getSimpleName() + " o";
		Query query = em.createQuery(jpaQuery);

		result = query.getResultList();

		em.close();

		return result;
	}

	@Override
	public T delete(T ov) {
		// TODO Auto-generated method stub
		EntityManagerFactory emFactory = EntityManagerFactorySingleton.getInstance(PERSITENCEUNITNAME).getEmf();

		EntityManager em = emFactory.createEntityManager();

		try {
			em.getTransaction().begin();

			em.remove(ov);

			em.getTransaction().commit();

		} catch (Exception e) {
			ov = null;
		}

		finally {
			em.close();
		}

		return ov;

	}

	@Override
	public T save(T ov) {
		// TODO Auto-generated method stub
		EntityManagerFactory emFactory = EntityManagerFactorySingleton.getInstance(PERSITENCEUNITNAME).getEmf();

		EntityManager em = emFactory.createEntityManager();

		try {
			em.getTransaction().begin();

			em.persist(ov);

			em.getTransaction().commit();

		} catch (Exception e) {
			ov = null;
		}

		finally {
			em.close();
		}

		return ov;

	}

	@Override
	public T update(T ov) {
		// TODO Auto-generated method stub
		EntityManagerFactory emFactory = EntityManagerFactorySingleton.getInstance(PERSITENCEUNITNAME).getEmf();

		EntityManager em = emFactory.createEntityManager();

		try {
			em.getTransaction().begin();

			em.merge(ov);

			em.getTransaction().commit();

		} catch (Exception e) {
			ov = null;
		}

		finally {
			em.close();
		}

		return ov;
	}

}
