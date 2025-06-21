package com.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService
{
	
	@Autowired
	private StudentRepository studentRepository;

	
	
	@Override
	public boolean register(StudentEntity studentEntity) 
	{
		try
		{
		studentRepository.save(studentEntity);
		
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}



	@Override
	public StudentEntity loginPage(String Email, String Password) 
	{
		StudentEntity validEmail = studentRepository.findByEmail(Email);
		
		if(validEmail!=null && validEmail.getPassword().equals(Password))
		{
			return validEmail;
		}
		
		return null;
	}
	

}
