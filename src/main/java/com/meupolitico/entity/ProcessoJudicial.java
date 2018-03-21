package com.meupolitico.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * 
 * @author kjanuaria<br>
 *
 *         Representa um Processo Judicial
 * 
 */
@Entity(name = "PROCESSO_JUDICIAL")
public class ProcessoJudicial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "jpaSequenceOrders", sequenceName = "JPA_SEQUENCE_ORDERS", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequenceOrders")
	private Long id;
	private String numero;
	private String descricao;
	private String status;
	private Politico politico;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Politico getPolitico() {
		return politico;
	}

	public void setPolitico(Politico politico) {
		this.politico = politico;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
