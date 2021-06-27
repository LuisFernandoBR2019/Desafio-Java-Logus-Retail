package br.com.logusretail.model.entities;

import java.sql.Timestamp;
import java.util.List;

import br.com.logusretail.model.base.BaseEntity;

public class Consulta extends BaseEntity {
	private Paciente paciente;
	private List<Medico> listaMedico;
	private Consultorio consultorio;
	private Timestamp dataHora;
	private String dataHoraFormatada;

	public String getDataHoraFormatada() {
		return dataHoraFormatada;
	}

	public void setDataHoraFormatada(String dataHoraFormatada) {
		this.dataHoraFormatada = dataHoraFormatada;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Consultorio getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(Consultorio consultorio) {
		this.consultorio = consultorio;
	}

	public Timestamp getDataHora() {
		return dataHora;
	}

	public void setDataHora(Timestamp dataHora) {
		this.dataHora = dataHora;
	}

	public List<Medico> getListaMedico() {
		return listaMedico;
	}

	public void setListaMedico(List<Medico> listaMedico) {
		this.listaMedico = listaMedico;
	}

	@Override
	public String toString() {
		return "Consulta [paciente=" + paciente + ", listaMedico=" + listaMedico + ", consultorio=" + consultorio
				+ ", dataHora=" + dataHora + ", dataHoraFormatada=" + dataHoraFormatada + "]";
	}

}
