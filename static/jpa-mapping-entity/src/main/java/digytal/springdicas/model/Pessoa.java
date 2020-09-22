package digytal.springdicas.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
@Entity
public class Pessoa {
	@Id
	private Integer id;
	private String nome;
	@OneToMany
	@JoinTable(name="pessoa_pais",
			joinColumns = { @JoinColumn(name = "id_pessoa") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_pai") })
	private List<Pessoa> pais;
}
