package com.aseds.inpt.appsecurity.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.aseds.inpt.appsecurity.dao.ClientRepertoire;
import com.aseds.inpt.appsecurity.dao.CommandeRepertoire;
import com.aseds.inpt.appsecurity.model.Client;
import com.aseds.inpt.appsecurity.model.Commande;

@Controller
public class CommandeController {

	@Autowired
	private CommandeRepertoire commandeRepertoire;
	@Autowired
	private ClientRepertoire clientRepertoire;
		
	
	@GetMapping("/commandes")
	public String afficherCommandes(Model model) {
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		List<Commande> commandes=commandeRepertoire.findAll();
		model.addAttribute("commandes",commandes);
		Commande commandeAffichee=commandes.get(0);
		model.addAttribute("commandeAffichee",commandeAffichee);
		return "pages/commandes";
	}
	@PostMapping("/commandes")

	public String afficherCommande(@RequestParam int id, Model model) {
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		List<Commande> commandes=commandeRepertoire.findAll();
		model.addAttribute("commandes",commandes);
		Commande commandeAffichee=commandeRepertoire.getById(id);
		model.addAttribute("commandeAffichee",commandeAffichee);
		return "pages/commandes";
	}
	@PostMapping("/commandes/ajouter")
	public String ajouterCommande(@RequestParam int id,@RequestParam String date, @RequestParam int idClient,Model model) {
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		Client client=clientRepertoire.getById(idClient);
        Commande newcommande = new Commande(id,date,client);
        commandeRepertoire.saveAndFlush(newcommande);
		List<Commande> commandes=commandeRepertoire.findAll();
		model.addAttribute("commandes",commandes);
		Commande commandeAffichee=commandeRepertoire.getById(id);
		model.addAttribute("commandeAffichee",commandeAffichee);
		return "pages/commandes";
	}
	@PostMapping("/commandes/modifier")
	public String modifierCommande(@RequestParam int id,@RequestParam String date, @RequestParam int idClient,Model model) {
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		Client client=clientRepertoire.getById(idClient);
        Commande commande = commandeRepertoire.getById(id);
        commande.setDate(date);
        commande.setClient(client);
        commandeRepertoire.saveAndFlush(commande);
		List<Commande> commandes=commandeRepertoire.findAll();
		model.addAttribute("commandes",commandes);
		Commande commandeAffichee=commandeRepertoire.getById(id);
		model.addAttribute("commandeAffichee",commandeAffichee);
		return "pages/commandes";
	}
    
	@PostMapping("/commandes/delete")
	public String supprimerCommande(@RequestParam int id,Model model) {
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		commandeRepertoire.deleteById(id);
		List<Commande> commandes=commandeRepertoire.findAll();
		model.addAttribute("commandes",commandes);
		Commande commandeAffichee=commandes.get(0);
		model.addAttribute("commandeAffichee",commandeAffichee);
		return "redirect:/commandes";
	}
	@GetMapping("/commandes/chercher")
	public String rechercherCommande(@RequestParam int recherche, Model model) {
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		List<Commande> commandes=commandeRepertoire.findAll();
		model.addAttribute("commandes",commandes);
		Commande commandeAffichee=commandeRepertoire.getById(recherche);
		model.addAttribute("commandeAffichee",commandeAffichee);
		return "pages/commandes";
	}
	@GetMapping("/commandes/filtrer")
	public String afficherCommandesParClient(Model model) {
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		Client client = clients.get(0);
		model.addAttribute("clientUtilise",client);
		List<Commande> commandes=client.getCommandes();
		model.addAttribute("commandes",commandes);
		
		return "pages/filtrer";
	}
	@GetMapping("/commandes/afficherParClient")
	public String afficher(@RequestParam int clientId ,Model model) {
		List<Client> clients=clientRepertoire.findAll();
		model.addAttribute("clients",clients);
		Client client = clientRepertoire.getById(clientId);
		model.addAttribute("clientUtilise",client);
		List<Commande> commandes=client.getCommandes();
		model.addAttribute("commandes",commandes);
		return "pages/filtrer";
	}

}
