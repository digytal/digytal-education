package digytal.springdicas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import digytal.springdicas.domain.EntityId;
import digytal.springdicas.repository.CrudRepository;

public abstract class Controller<E extends EntityId, K> {
	@Autowired
	protected CrudRepository<E, K> repository;

	@PostMapping("/")
	public <K> K insert(@RequestBody E entity) {
		return repository.insert(entity);
	}

	@PutMapping("/")
	public <E> E update(@RequestBody E entity) {
		return repository.update(entity);
	}

	@DeleteMapping("/{id}")
	public void delete(K id) {
		repository.delete(id);
	}

	@GetMapping("/")
	public List<E> select() {
		return repository.select();
	}
}
