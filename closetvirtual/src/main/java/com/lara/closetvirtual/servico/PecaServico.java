package com.lara.closetvirtual.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lara.closetvirtual.excecao.PecaNotFoundException;
import com.lara.closetvirtual.modelo.Peca;
import com.lara.closetvirtual.repositorio.PecaRepositorio;

@Service
public class PecaServico {

	@Autowired
	private PecaRepositorio pecaRepositorio;
	
	public Peca criarPeca(Peca peca) {
		return pecaRepositorio.save(peca);
	}
	
	public List<Peca> buscarTodasPecas(){
		return pecaRepositorio.findAll();
	}
	
	public Peca buscarPecaPorId(Long id) throws PecaNotFoundException {
		Optional<Peca> opt = pecaRepositorio.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
		} else {
			throw new PecaNotFoundException("Peca com o id "+ id +"não existe");
		}
	}
	
	public void apagarPeca(Long id) throws PecaNotFoundException {
		Peca peca = buscarPecaPorId(id);
		pecaRepositorio.delete(peca);
	}
	
	public Peca alterarPeca(Peca peca) {
		return pecaRepositorio.save(peca);
	}

	public List<Peca> buscarTodasPecasPorTipo(String tipo){
		return pecaRepositorio.findByTipoContainingIgnoreCase(tipo);
	}
}
