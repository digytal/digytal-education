package digytal.springdicas.components.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digytal.springdicas.domain.model.Customer;

@RestController
@RequestMapping("customer")
public class CustomerController extends Controller<Customer, Integer> {

}
