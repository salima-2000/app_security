package com.aseds.inpt.appsecurity.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String ajouterClient(@RequestParam("image") MultipartFile image,@RequestParam int id,@RequestParam int age,@RequestParam String nom, @RequestParam String prenom, Model model) throws IOException {

			Client nouveauClient=new Client(id,nom,prenom,age,image.getBytes());
			clientRepertoire.saveAndFlush(nouveauClient);
			List<Client> clients=clientRepertoire.findAll();
			model.addAttribute("clients",clients);
			Client clientAffiche=clientRepertoire.getById(id);
			model.addAttribute("clientAffiche",clientAffiche);
			
			return "redirect:/clients";
	/*	String fileName = image.getOriginalFilename();
		String fe = "";
		if (fileName.contains(".")) {
			int i = fileName.lastIndexOf('.');
			fe = i > 0 ? fileName.substring(i + 1) : "";
		}
	
		if(fe.contains("png") ||fe.contains("PNG")|| fe.contains("jpg")) {
			Client nouveauClient=new Client(id,nom,prenom,age,image.getBytes());
			clientRepertoire.saveAndFlush(nouveauClient);
			List<Client> clients=clientRepertoire.findAll();
			model.addAttribute("clients",clients);
			Client clientAffiche=clientRepertoire.getById(id);
			model.addAttribute("clientAffiche",clientAffiche);
			
			return "redirect:/clients";
		}else {
			List<Client> clients=clientRepertoire.findAll();
			model.addAttribute("clients",clients);
			Client clientAffiche=clientRepertoire.getById(0);
			model.addAttribute("clientAffiche",clientAffiche);
			return "redirect:/clients";
		}*/
		
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
