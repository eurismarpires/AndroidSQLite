package br.com.exemplo.androidsqlite.model;

import java.io.Serializable;

public class Pessoa implements Serializable{

	private String id;
	private String nome;
	private String telefone;
	
	public Pessoa() {
		
	}

	public Pessoa(String id, String nome, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
}
