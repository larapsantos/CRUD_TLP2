package com.lara.closetvirtual.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lara.closetvirtual.modelo.Estilo;
import com.lara.closetvirtual.servico.EstiloServico;

import jakarta.validation.Valid;

@Controller
public class EstiloControle {

	@Autowired
	private EstiloServico estiloServico;
	
	@GetMapping("/novoEstilo")
	public String novoEstilo(Model model) {
		Estilo estilo = new Estilo();
		model.addAttribute("novoEstilo", estilo);
		return "/novoEstilo";
	}
	
	@PostMapping("/gravarEstilo")
	public String gravarEstilo(@ModelAttribute("novoEstilo") @Valid Estilo estilo, BindingResult erros, RedirectAttributes attributes) {
		
		if(erros.hasErrors()) {
			return "/novoEstilo";
		}
		
		estiloServico.gravarEstilo(estilo);
		attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso!");
		
	return "redirect:/novoEstilo";
		
	}
}
