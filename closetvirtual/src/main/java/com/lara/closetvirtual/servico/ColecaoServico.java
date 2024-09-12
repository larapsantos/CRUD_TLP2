package com.lara.closetvirtual.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lara.closetvirtual.modelo.Colecao;
import com.lara.closetvirtual.repositorio.ColecaoRepositorio;

@Service
public class ColecaoServico {

	@Autowired
	private ColecaoRepositorio colecaoRepositorio;
	
	public Colecao gravarColecao(Colecao colecao) {
		return colecaoRepositorio.save(colecao);
	}
	
	public List<Colecao> listar(){
		return colecaoRepositorio.findAll();
	}
	
}
