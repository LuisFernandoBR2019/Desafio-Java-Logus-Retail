package br.com.logusretail.model.entities;

import br.com.logusretail.model.base.BaseEntity;

public class Consultorio extends BaseEntity {
	private Integer numeroConsultorio;

	public Integer getNumeroConsultorio() {
		return numeroConsultorio;
	}

	public void setNumeroConsultorio(Integer numeroConsulta) {
		this.numeroConsultorio = numeroConsulta;
	}

	@Override
	public String toString() {
		return "Consultorio [numeroConsultorio=" + numeroConsultorio + "]";
	}

}
