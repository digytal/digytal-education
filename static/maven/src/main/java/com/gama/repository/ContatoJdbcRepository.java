package com.gama.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gama.model.Contato;

public class ContatoJdbcRepository {
	private Connection connecton;
	
	public ContatoJdbcRepository() {
		try {
			connecton= DriverManager.getConnection("jdbc:hsqldb:file:/temp/db/sampledb", "SA", "");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
	}
	public void incluir(Contato contato) {
		try {
			PreparedStatement st = connecton.prepareStatement("INSERT INTO tab_contato(nome,telefone) values(?,?)");
			st.setString(1, contato.getNome());
			st.setString(2, contato.getTelefone());
			
			int i=st.executeUpdate();  
			System.out.println(i);
			
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void fechar() {
		try {
			connecton.close();
			System.exit(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
