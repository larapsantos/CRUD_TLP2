package com.lara.closetvirtual.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Peca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O tamanho deve ser informado")
	@Size(min = 1, message = "O tamanho deve ter no mínimo um caracter")
	private String tamanho;
	
	private String cor;
	
	@ManyToOne
	private Colecao colecao;
	
	@ManyToMany
	@JoinTable(name = "peca_estilo",
	joinColumns = @JoinColumn(name = "peca_id"),
	inverseJoinColumns = @JoinColumn(name = "estilo_id"))
	private List<Estilo> estilos;
	
	@NotBlank(message = "A marca deve ser informada")
	@Size(min = 1, message = "A marca deve ter no mínimo um caracter")
	private String marca;
	
	@NotBlank(message = "O tipo deve ser informado")
	@Size(min = 1, message = "O tipo deve ter no mínimo um caracter")
	private String tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Colecao getColecao() {
		return colecao;
	}

	public void setColecao(Colecao colecao) {
		this.colecao = colecao;
	}

	public List<Estilo> getEstilos() {
		return estilos;
	}

	public void setEstilos(List<Estilo> estilos) {
		this.estilos = estilos;
	}
	
	
	
}
