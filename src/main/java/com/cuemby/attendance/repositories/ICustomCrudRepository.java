package com.cuemby.attendance.repositories;

import java.util.Map;
import java.util.Optional;


public interface ICustomCrudRepository<T, S> {
	
	Map<S,T> findAll();

	Map<S,T> saveAll(Iterable<T> entities);

	T save(T entity);

	T update(T entity);

	Optional<T> getOne(S id);

}
