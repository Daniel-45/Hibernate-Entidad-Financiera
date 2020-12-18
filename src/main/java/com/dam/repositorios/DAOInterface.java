package com.dam.repositorios;

import java.util.Optional;

/**
 * 
 * @author Daniel
 *
 * @param <T>
 * @param <K>
 */

public interface DAOInterface<T, K> {

	public Optional<T> findById(K key);

	public Iterable<T> findAll();

	public T delete(T ov);

	public T save(T ov);

	public T update(T ov);
}
