package com.meupolitico.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.meupolitico.enumerators.EstadoEnum;

/**
 * 
 * @author kjanuaria <br>
 * 
 *         Representa um Político
 */
@Entity
public class Politico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8065794541938110610L;
	@Id
	@SequenceGenerator(name = "jpaSequenceOrders", sequenceName = "JPA_SEQUENCE_ORDERS", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequenceOrders")
	private Long id;
	private String nome;
	private String idade;
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	@OneToOne
	@JoinColumn(name = "PARTIDO_POLITICO")
	private PartidoPolitico partidoPolitico;
	private String cargo;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "PROCESSOS_JUDICIAIS")
	private List<ProcessoJudicial> processosJudiciais;

	public Politico() {
		// TODO Auto-generated method stub
	}

	public Politico(Long id, String nome, String idade, EstadoEnum estado, PartidoPolitico partidoPolitico,
			String cargo, List<ProcessoJudicial> processosJudiciais) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.estado = estado;
		this.setPartidoPolitico(partidoPolitico);
		this.cargo = cargo;
		this.processosJudiciais = processosJudiciais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ProcessoJudicial> getProcessosJudiciais() {
		return processosJudiciais;
	}

	public void setProcessosJudiciais(List<ProcessoJudicial> processosJudiciais) {
		this.processosJudiciais = processosJudiciais;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public PartidoPolitico getPartidoPolitico() {
		return partidoPolitico;
	}

	public void setPartidoPolitico(PartidoPolitico partidoPolitico) {
		this.partidoPolitico = partidoPolitico;
	}

}
