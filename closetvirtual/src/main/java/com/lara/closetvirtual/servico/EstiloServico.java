package com.lara.closetvirtual.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lara.closetvirtual.modelo.Estilo;
import com.lara.closetvirtual.repositorio.EstiloRepositorio;

@Service
public class EstiloServico {

	@Autowired
	private EstiloRepositorio estiloRepositorio;
	
	public Estilo gravarEstilo(Estilo estilo) {
		return estiloRepositorio.save(estilo);
	}
	
	
	public List<Estilo> listarEstilos(){
		return estiloRepositorio.findAll();
	}
}
