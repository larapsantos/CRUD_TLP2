package com.lara.closetvirtual.controle;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lara.closetvirtual.excecao.PecaNotFoundException;
import com.lara.closetvirtual.modelo.Colecao;
import com.lara.closetvirtual.modelo.Estilo;
import com.lara.closetvirtual.modelo.Peca;
import com.lara.closetvirtual.servico.ColecaoServico;
import com.lara.closetvirtual.servico.EstiloServico;
import com.lara.closetvirtual.servico.PecaServico;
import jakarta.validation.Valid;

@Controller
public class PecaControle {

	@Autowired
	private PecaServico pecaServico;
	
	@Autowired
	private ColecaoServico colecaoServico;
	
	@Autowired
	private EstiloServico estiloServico;
	
	@GetMapping("/")
	public String listarPecas(Model model) {
		List<Peca> lista = pecaServico.buscarTodasPecas();
		model.addAttribute("listaPecas", lista);
		return "/listaPecas";
	}
	
	@PostMapping("/buscar")
    public String buscarPecas(Model model, @Param("tipo") String tipo) {	
		if (tipo == null) {
			return "redirect:/";
		}
		List<Peca> pecas = pecaServico.buscarTodasPecasPorTipo(tipo);
		model.addAttribute("listaPecas",pecas);
		return "/listaPecas";
    }

	
	@GetMapping("/novaPeca")
	public String novaPeca(Model model) {
		Peca peca = new Peca();
		model.addAttribute("novaPeca", peca);
		
		List<Colecao> colecoes = colecaoServico.listar();
		model.addAttribute("novaColecao", colecoes);
		
		List<Estilo> estilos = estiloServico.listarEstilos();
		model.addAttribute("novoEstilo", estilos);

		return "/novaPeca";
	}
	
	@PostMapping("/gravar")
	public String gravarPeca(@ModelAttribute ("novaPeca") 
	@Valid Peca peca, BindingResult erros, RedirectAttributes attributes, Model model) {
		
		if(erros.hasErrors()) {
			List<Colecao> colecoes = colecaoServico.listar();
			model.addAttribute("novaColecao", colecoes);
			
			List<Estilo> estilos = estiloServico.listarEstilos();
			model.addAttribute("novoEstilo", estilos);
			return "/novaPeca";
		}
		
		pecaServico.criarPeca(peca);
		attributes.addFlashAttribute("mensagem", "Peca salva com sucesso!");
		
	return "redirect:/novaPeca";
		
	}
	
	@GetMapping("/apagar/{id}")
	public String apagarPeca(@PathVariable("id") long id, RedirectAttributes attributes) {
		try {
			pecaServico.apagarPeca(id);
		} catch (PecaNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		return "redirect:/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarForm(@PathVariable("id") long id, RedirectAttributes attributes,
			Model model) {
		try {
			Peca peca = pecaServico.buscarPecaPorId(id);
			model.addAttribute("objetoPeca", peca);
			
			List<Colecao> colecoes = colecaoServico.listar();
			model.addAttribute("novaColecao", colecoes);
			
			List<Estilo> estilos = estiloServico.listarEstilos();
			model.addAttribute("novoEstilo", estilos);
			
			return "/alterarPeca";
		} catch (PecaNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		return "redirect:/";
	}
	
	@PostMapping("/editar/{id}")
	public String editarPeca(@PathVariable("id") long id, 
								@ModelAttribute("objetoPeca") @Valid Peca peca, Model model,
								BindingResult erros) {
		if (erros.hasErrors()) {
			peca.setId(id);
			
			List<Colecao> colecoes = colecaoServico.listar();
			model.addAttribute("novaColecao", colecoes);
			
			List<Estilo> estilos = estiloServico.listarEstilos();
			model.addAttribute("novoEstilo", estilos);
			
	        return "/alterarPeca";
	    }
		pecaServico.alterarPeca(peca);
		return "redirect:/";
	}

	
}
