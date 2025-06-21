package com.register;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepository extends CrudRepository<StudentEntity, Integer>
{
	

	StudentEntity findByEmail(String Email);
	
}
