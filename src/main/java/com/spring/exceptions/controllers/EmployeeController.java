package com.spring.exceptions.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.exceptions.exceptions.EmployeeNotFoundException;
import com.spring.exceptions.model.Employee;
import com.spring.exceptions.model.ExceptionJSONInfo;

@Controller
public class EmployeeController {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model){
			
			model.addAttribute("employee", "Spring 4 Exception Handling");
			return "home";
		
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public String getEmployee(@PathVariable("id") int id, Model model)
			throws Exception {

		if (id == 1) {
			throw new EmployeeNotFoundException(id);
		} else if (id == 2) {
			throw new SQLException("SQLException, id=" + id);
		} else if (id == 3) {
			throw new IOException("IOException, id=" + id);
		} else if (id == 10) {
			Employee emp = new Employee();
			emp.setName("Amal");
			emp.setId(id);
			model.addAttribute("employee", emp);
			return "home";
		} else {
			throw new Exception("Generic Exception id + " + id);
		}
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ModelAndView handleEmployeeNotFoundException(HttpServletRequest req,
			Exception ex) {

		LOG.error("Requested URL=" + req.getRequestURL());
		LOG.error("Exception Raised=" + ex);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", ex);
		modelAndView.addObject("url", req.getRequestURL());

		modelAndView.setViewName("error");
		return modelAndView;

	}

    //Rewrite the EmployeeController handleEmployeeNotFoundException() method to return JSON response.
	
/*	@ExceptionHandler(EmployeeNotFoundException.class)
	public @ResponseBody ExceptionJSONInfo handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
		
		ExceptionJSONInfo response = new ExceptionJSONInfo();
		response.setUrl(request.getRequestURL().toString());
		response.setMessage(ex.getMessage());
		
		return response;
	}*/
	
	
	
	
	
	
	
	
	
	
	
}
