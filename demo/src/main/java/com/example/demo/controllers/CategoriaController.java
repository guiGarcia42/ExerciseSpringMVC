package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Categoria;
import com.example.demo.repositories.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired 
	private CategoriaRepository categoriaRepository;
	
	@GetMapping("")
	public ModelAndView Get() {
		ModelAndView model = new ModelAndView("categoria/index"); 
		List<Categoria> listCategoria = categoriaRepository.findAll();
		model.addObject("categorias", listCategoria);
		
		return model;
	}
	
	@GetMapping("/create")
	public ModelAndView Create() {
		ModelAndView model = new ModelAndView("categoria/create");
		
		return model;
	}
	
	@PostMapping("/create")
	public String Create(@ModelAttribute("categoria") Categoria objCategoria) {
		categoriaRepository.save(objCategoria);
		
		return "redirect:/categoria";
	}
}
