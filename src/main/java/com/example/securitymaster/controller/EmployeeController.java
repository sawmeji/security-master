package com.example.securitymaster.controller;

import com.example.securitymaster.dao.EmployeeDao;
import com.example.securitymaster.ds.Employee;
import com.example.securitymaster.security.annotation.employee.EmployeeCreate;
import com.example.securitymaster.security.annotation.employee.EmployeeDelete;
import com.example.securitymaster.security.annotation.employee.EmployeePageView;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    @EmployeePageView
    @GetMapping("/employees")
    public ModelAndView findAllEmployees(){
        return new ModelAndView("employees","employees", employeeDao.findAll());
    }

    @EmployeeCreate
    @GetMapping("/employee-form")
    public String employeeForm(Model model){
        model.addAttribute("employee",new Employee());
        return "employee-form";
    }

    @EmployeeCreate
    @PostMapping("/employee-form")
    public String saveEmployee(@Valid Employee employee, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "employee-form";
        }
        employeeDao.save(employee);
        return "redirect:/employee/employees";
    }

    @EmployeeDelete
    @GetMapping("/employees/delete")
    public String deleteEmployee(@RequestParam("id") int id){
        if(employeeDao.existsById(id)){
            employeeDao.deleteById(id);
            return "redirect:/employee/employees";
        }
        else
            throw new EntityNotFoundException(id + " not found!");
    }
}
