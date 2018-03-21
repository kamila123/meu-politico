package com.meupolitico.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * 
 * @author kjanuaria
 *
 *         Representa um Partido Politico
 */
@Entity(name = "PARTIDO_POLITICO")
public class PartidoPolitico {

	@Id
	@SequenceGenerator(name = "jpaSequenceOrders", sequenceName = "JPA_SEQUENCE_ORDERS", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequenceOrders")
	private Long id;
	private String sigla;
	private String coligacao;

	protected PartidoPolitico() {
	}

	public PartidoPolitico(Long id, String sigla, String coligacao) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.coligacao = coligacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getColigacao() {
		return coligacao;
	}

	public void setColigacao(String coligacao) {
		this.coligacao = coligacao;
	}
}
