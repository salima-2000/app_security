package com.aseds.inpt.appsecurity.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.aseds.inpt.appsecurity.dao.ClientRepertoire;
import com.aseds.inpt.appsecurity.model.Client;

@Controller
public class ClientController {

	@Autowired
	private ClientRepertoire clientRepertoire;
	
		
	
	@GetMapping("/clients")
	public String afficherClients(Model model) {
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		Client clientAffiche=clients.get(0);
		model.addAttribute("clientAffiche",clientAffiche);
		return "pages/clients";
	}

	@PostMapping("/clients")

	public String afficherClient(@RequestParam int id, Model model) {
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		Client clientAffiche=clientRepertoire.findById(id).get();
		model.addAttribute("clientAffiche",clientAffiche);
		return "pages/clients";
	}
	@PostMapping("/clients/nouveau")
	public String ajouterClient(@RequestParam int id,@RequestParam int age,@RequestParam String nom, @RequestParam String prenom, Model model) {
		Client nouveauClient=new Client(id,nom,prenom,age);
		clientRepertoire.saveAndFlush(nouveauClient);
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		Client clientAffiche=clientRepertoire.getById(id);
		model.addAttribute("clientAffiche",clientAffiche);
		return "pages/clients";
	
	}
	@PostMapping("/clients/delete")
	public String supprimerClient(@RequestParam int id,Model model) {
		clientRepertoire.deleteById(id);
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		Client clientAffiche=clients.get(0);
		model.addAttribute("clientAffiche",clientAffiche);
		return "redirect:/clients";
	}
	@PostMapping("/clients/modifier")
	
	public String modifierClient(@RequestParam int id,@RequestParam int age,@RequestParam String nom, @RequestParam String prenom, Model model) {
		Client clientExistant=clientRepertoire.getById(id);
		clientExistant.setNom(nom);
		clientExistant.setPrenom(prenom);
		clientExistant.setAge(age);
		clientRepertoire.saveAndFlush(clientExistant);
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		Client clientAffiche=clientRepertoire.getById(id);
		model.addAttribute("clientAffiche",clientAffiche);
		return "pages/clients";
	}
	@GetMapping("/clients/chercher")
	public String rechercherClient(@RequestParam int recherche, Model model) {
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		Client clientAffiche=clientRepertoire.getById(recherche);
		model.addAttribute("clientAffiche",clientAffiche);
		return "pages/clients";
	}

}
