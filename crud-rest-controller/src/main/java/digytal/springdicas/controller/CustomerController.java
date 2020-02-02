package digytal.springdicas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digytal.springdicas.domain.Customer;

@RestController
@RequestMapping("customer")
public class CustomerController extends Controller<Customer, Integer> {

}
