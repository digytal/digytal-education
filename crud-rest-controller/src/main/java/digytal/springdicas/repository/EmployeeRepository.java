package digytal.springdicas.repository;

import org.springframework.stereotype.Repository;

import digytal.springdicas.domain.Employee;

@Repository
public class EmployeeRepository extends CrudRepository<Employee, Long> {

}
