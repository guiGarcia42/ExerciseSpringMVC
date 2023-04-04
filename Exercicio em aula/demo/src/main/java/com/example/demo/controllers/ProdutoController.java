package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Categoria;
import com.example.demo.models.Produto;
import com.example.demo.repositories.ProdutoRepository;

@Controller
@RequestMapping("/produto") // Mapeia a página que estamos rodando
public class ProdutoController {

	@Autowired //Injeção de dependências
	private ProdutoRepository produtoRepository;
	// Dessa forma nós podemos utilizar os métodos do repository sem instanciar
	
	@GetMapping("")
	public ModelAndView get() {
		ModelAndView model = new ModelAndView("produto/index");
		
		List<Produto> listaProduto = produtoRepository.findAll();
		// Faz um select na base
		
		model.addObject("produtos", listaProduto);
		return model;
	}
	
	@GetMapping("/edit/{id}") //embebe o id puxado do pathVariable
	public String getById(Model model, @PathVariable("id") Integer idProduto ) {
		model.addAttribute("idProduto", idProduto);
		return "produto/edit";
	}
	
	@GetMapping("/create") // Mapeia a URL, em seguida executa o método
	public String create() {
		
		// Depois de rodar todo o método ele retorna para carregar a página
		return "produto/create"; // Retorna o path da página html para ser executada
	}
	
	@PostMapping("/create") // Pega os dados inseridos na página para utilizar no método
	public String create(@ModelAttribute("produto") Produto objProduto) {
		// O dado passado no método identifica os atributos da classe Produto no html
		// o dado deve ser inserido no banco já, para inserir utilizamos o save
		produtoRepository.save(objProduto);
		// Após retornar os dados para a controller redireciona para a pagina inicial
		return "redirect:/produto";
	}
	
	@GetMapping("/categoria")
	@ResponseBody // converte o objeto em JSON para ser utilizado
	public Categoria getCategoria() {
		
		Categoria categoria = new Categoria();
		//o dado seria trazido de uma base de dados, mas para exemplo vamos instanciar
		categoria.setDescricao("Masculino");
		categoria.setId(1);
				
		return categoria;
	}
	
	
}
