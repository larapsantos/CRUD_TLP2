package com.lara.closetvirtual.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lara.closetvirtual.modelo.Colecao;
import com.lara.closetvirtual.modelo.Peca;
import com.lara.closetvirtual.servico.ColecaoServico;

import jakarta.validation.Valid;

@Controller
public class ColecaoControle {
	
	@Autowired
	private ColecaoServico colecaoServico;
	
	@GetMapping("/novaColecao")
	public String novaPeca(Model model) {
		Colecao colecao = new Colecao();
		model.addAttribute("novaColecao", colecao);
		return "/novaColecao";
	}
	
	@PostMapping("/gravarColecao")
	public String gravarColecao(@ModelAttribute ("novaColecao") 
	@Valid Colecao colecao, BindingResult erros, RedirectAttributes attributes) {
		
		if(erros.hasErrors()) {
			return "/novaColecao";
		}
		
		colecaoServico.gravarColecao(colecao);
		attributes.addFlashAttribute("mensagem", "Coleção salva com sucesso!");
		
	return "redirect:/novaColecao";
		
	}
}
