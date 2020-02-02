package digytal.springdicas.components.repository;

import org.springframework.stereotype.Repository;

import digytal.springdicas.domain.model.Customer;
@Repository
public class CustomerRepository extends CrudRepository<Customer, Integer> {

}
