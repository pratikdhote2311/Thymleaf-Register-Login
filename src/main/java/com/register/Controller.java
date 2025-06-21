package com.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class Controller 
{

    private final StudentRepository studentRepository;
	
	@Autowired
	private StudentService studentService;

    Controller(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
	
	@GetMapping("/reg")
	public String registerPage(Model model)
	{
		
		model.addAttribute("student", new StudentEntity());
		return "register";
	}
	
	
	@PostMapping("/regForm")
	public String submitForm( @ModelAttribute("student") StudentEntity studentEntity,Model model)
	{
		boolean status=studentService.register(studentEntity);
		
		if(status)
		{
			model.addAttribute("success","User Registered Sucessfully");
		}
		else
		{
			model.addAttribute("failed","User are not Registered");
		}
		
		
		return "register";
		
	}

	
	
	@GetMapping("/login")
	public String loginPage(Model model)
	{
		model.addAttribute("student",new StudentEntity());
		
		return "login";
	}
	
	
	@PostMapping("logForm")
	public String loginForm(@ModelAttribute("student") StudentEntity studentEntity, Model model)
	{
		
		StudentEntity loginPage = studentService.loginPage(studentEntity.getEmail(),studentEntity.getPassword());
		
		if(loginPage!=null)
			
			
		{
			
			model.addAttribute("name",loginPage.getName());
			return "profile";
			
			
		}
		else
		{
			model.addAttribute("failed","Email and Password did'nt Matched");
			
			return "login";
		}
		
	}
	
	
	@GetMapping("/logOut")
	public String LogOut(HttpServletRequest request)
	{
		
		HttpSession session = request.getSession(false);
		
		
		if(session!=null)
			
			
		{
			
			session.invalidate();
		}

		
		
		
		return "redirect:/login";
	}
}
