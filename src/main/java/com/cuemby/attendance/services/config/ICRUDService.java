package com.cuemby.attendance.services.config;

import java.util.List;

public interface  ICRUDService<T> {
	List<?> listAll();
	
	T create(T domainObject);
	
}
