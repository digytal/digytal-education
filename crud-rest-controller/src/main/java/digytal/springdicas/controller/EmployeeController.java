package digytal.springdicas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digytal.springdicas.domain.Employee;

@RestController
@RequestMapping("employee")
public class EmployeeController extends Controller<Employee, Long> {

}
