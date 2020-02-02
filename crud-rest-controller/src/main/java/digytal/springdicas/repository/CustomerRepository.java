package digytal.springdicas.repository;

import org.springframework.stereotype.Repository;

import digytal.springdicas.domain.Customer;
@Repository
public class CustomerRepository extends CrudRepository<Customer, Integer> {

}
