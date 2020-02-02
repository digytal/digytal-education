package digytal.springdicas.components.repository;

import org.springframework.stereotype.Repository;

import digytal.springdicas.domain.model.Employee;

@Repository
public class EmployeeRepository extends CrudRepository<Employee, Long> {

}
