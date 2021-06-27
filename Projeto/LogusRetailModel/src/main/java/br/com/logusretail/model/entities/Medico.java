package br.com.logusretail.model.entities;

import br.com.logusretail.model.base.BaseEntity;

public class Medico extends BaseEntity {

	private String crm;
	private String nome;
	private String especialidade;
	private Integer idade;

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "Medico [crm=" + crm + ", nome=" + nome + ", especialidade=" + especialidade + ", idade=" + idade + "]";
	}

}
