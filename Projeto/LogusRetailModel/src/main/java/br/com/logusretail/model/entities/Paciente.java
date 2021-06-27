package br.com.logusretail.model.entities;

import br.com.logusretail.model.base.BaseEntity;

public class Paciente extends BaseEntity {
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Paciente [nome=" + nome + "]";
	}

}
