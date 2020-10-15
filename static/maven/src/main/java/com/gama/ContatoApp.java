package com.gama;

import java.util.List;

import com.gama.model.Contato;
import com.gama.repository.ContatoRepository;

public class ContatoApp {
	public static void main(String[] args) {
		ContatoRepository repository = new ContatoRepository();
		
		Contato contato = new Contato();
		contato.setNome("GLEYSON");
		contato.setTelefone("11 8798798798");
		
		repository.incluir(contato);
		
		contato = new Contato();
		contato.setNome("JEFERSON");
		contato.setTelefone("11 9879879878");
		
		repository.incluir(contato);
		
		List<Contato> contatos = repository.listar();
		
		
		for(Contato c: contatos) {
			System.out.println(c.getId() + " -- " + c.getNome());
		}
		
	}
}
