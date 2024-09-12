package com.lara.closetvirtual.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lara.closetvirtual.modelo.Peca;

public interface PecaRepositorio extends JpaRepository<Peca, Long> {
	List<Peca> findByTipoContainingIgnoreCase(String tipo);
}
