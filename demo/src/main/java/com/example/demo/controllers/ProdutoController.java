package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Categoria;
import com.example.demo.models.Fornecedor;
import com.example.demo.models.Produto;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.FornecedoRepository;
import com.example.demo.repositories.ProdutoRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/produto")// Mapeia a página que estamos rodando
public class ProdutoController {
	
	@Autowired //injeção de dependência
	private ProdutoRepository produtoRepository; 
	// Dessa forma nós podemos utilizar os métodos do repository sem instanciar
	@Autowired
	private CategoriaRepository categoriaRepository; 
	
	@Autowired
	private FornecedoRepository fornecedorRepository; 
	
	@GetMapping("")
	public ModelAndView get() {
		ModelAndView model = new ModelAndView("produto/index");
		
		List<Produto> listaProduto = produtoRepository.findAll();
		// Faz um select na base
		
		model.addObject("produtos", listaProduto);
		return model;
	}
	
	@GetMapping("/edit")
	public ModelAndView edit() {
		ModelAndView model = new ModelAndView("produto/edit");
		return model;
	}
	
	@GetMapping("/edit/{id}") //embebe o id puxado do pathVariable
	public String getById(Model model, @PathVariable("id")Integer idProduto) {
		model.addAttribute("idProduto", idProduto);
		return "produto/edit";
	}
	
	@GetMapping("/create") // Mapeia a URL, em seguida executa o método
	public ModelAndView create() {
		ModelAndView model = new ModelAndView("produto/create");
		
		// No caso abaixo estamos injetando no produto listas de outros objetos.
		List<Categoria> categorias = categoriaRepository.findAll();
		List<Fornecedor> fornecedores = fornecedorRepository.findAll();
		
		// Agora nós utilizamos o model para passar 2 objetos diferentes
		model.addObject("fornecedores", fornecedores);
		model.addObject("categorias",categorias);
		
		// Depois de rodar todo o método ele retorna para carregar a página
		return model; // Retorna o path da página html para ser executada
	}
	
	@PostMapping("/create-form")
	public ResponseEntity<Produto> createFrom(@Valid  @RequestBody Produto objProduto) {
		produtoRepository.save(objProduto);
		return new ResponseEntity<Produto>(objProduto, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/create") // Pega os dados inseridos na página para utilizar no método
	public String create(@ModelAttribute("produto")Produto objProduto) {
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
		categoria.setDescricao("Outra");
		categoria.setId(1);
		
		return categoria;
	}
	
	@GetMapping("/categoria/{id}")
	@ResponseBody
	public List<Produto> getProdutoByCategoria(@PathVariable("id")Integer idCategoria){
		
		List<Produto> produtos = produtoRepository.findByIdCategoria(idCategoria);
		
		return produtos;
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id")Long id) {
		produtoRepository.deleteById(id);
		return "redirect:/produto";
	}
	
}
