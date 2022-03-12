package com.examly.springapp.controller;

import com.examly.springapp.model.Employee;
import com.examly.springapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  @Autowired private EmployeeService employeeService;

  @GetMapping("/admin/getEmployee")
  public ResponseEntity<?> getEmployee() {
    return new ResponseEntity<>(employeeService.getEmployee(), HttpStatus.OK);
  }

  @GetMapping("/admin/getEmployee/{id}")
  public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
    try {
      Employee e = employeeService.getEmployeeId(id);
      return new ResponseEntity<>(e, HttpStatus.OK);
    } catch (Throwable e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/admin/delete/{id}")
  public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
    try {
      employeeService.deleteEmployee(id);
      return new ResponseEntity<>("Employee Deleted Successfully",
                                  HttpStatus.CREATED);
    } catch (Throwable e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/admin/editEmployee")
  public ResponseEntity<?> editEmployee(@RequestBody Employee employee) {
    try {
      employeeService.editEmployee(employee);
      return new ResponseEntity<>("Employee edited Successfully",
                                  HttpStatus.CREATED);
    } catch (Throwable e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/admin/saveEmployee")
  public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
    try {
      employeeService.saveEmployee(employee);
      return new ResponseEntity<>("Employee saved Successfully",
                                  HttpStatus.CREATED);
    } catch (Throwable e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
