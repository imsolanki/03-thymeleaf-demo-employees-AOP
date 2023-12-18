package com.huntcode.springboot.thymeleafdemo.controller;

import com.huntcode.springboot.thymeleafdemo.entity.Employee;
import com.huntcode.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	@Autowired
	public  EmployeeController(EmployeeService theEmployeeService){
		employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee>theEmployeeList =employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", theEmployeeList);

		return "Employees/list-employees";
	}
///Step 2,for add button
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		Employee employee = new Employee();
		theModel.addAttribute("employee",employee);
		return "employees/employee-form";
	}

	///Step 4, for add button

	@PostMapping("/save")
	public  String save(@ModelAttribute("employee") Employee theEmployee){
		//save the employee
		employeeService.save(theEmployee);
		//use a redirect to prevent duplicate employee saving

		return "redirect:/employees/list";
	}
	//step 2 for update button
	//prefilled form with existing details of the employee
	@GetMapping("/showFormForUpdate")
	public  String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel){


		//Get the employee from the service

		Employee theEmployee = employeeService.findById(theId);


		//set the employee in the model to pre-populate the form

		theModel.addAttribute("employee",theEmployee);

		//send data over to our form

		return "employees/employee-form";
	}

	//step 2 for delete button

	@GetMapping("/delete")
		public  String delete(@RequestParam("employeeId") int theId){

		//deleting employee
		employeeService.deleteById(theId);

		//redirect to /employee/list

		return "redirect:/employees/list";
	}
}









