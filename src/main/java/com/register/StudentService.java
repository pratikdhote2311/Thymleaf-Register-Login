package com.register;

public interface StudentService 
{
	public boolean register(StudentEntity studentEntity);
	
	public StudentEntity loginPage(String Email,String Password);

}
