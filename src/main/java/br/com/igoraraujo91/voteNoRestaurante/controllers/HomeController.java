package br.com.igoraraujo91.voteNoRestaurante.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.igoraraujo91.voteNoRestaurante.dao.DAO;
import br.com.igoraraujo91.voteNoRestaurante.models.Restaurant;

@Controller
public class HomeController {
	
	@Autowired
	private DAO<Restaurant> restaurantsDAO;	
	
	@RequestMapping("/")
	public String list(){
		return "main.html";
	}
	
	@RequestMapping("/ftl")
	public String ftl(Model model){
		model.addAttribute("restaurants", this.restaurantsDAO.readAll());
		return "ftl-view.ftl";
	}

}
