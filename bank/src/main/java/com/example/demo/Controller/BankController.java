package com.example.demo.Controller;

import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.EmployesRepository;
import com.example.demo.model.Client;
import com.example.demo.model.Compte;
import com.example.demo.model.CompteCC;
import com.example.demo.model.CompteEP;
import com.example.demo.model.Employes;
import com.example.demo.model.Groupes;
import com.example.demo.model.Operation;
import com.example.demo.service.IBankImplementation;
import com.example.demo.service.Ibank;

@Controller
public class BankController {
	
	@Autowired
	private Ibank banqueservise;
	
	@RequestMapping("/home")
	public String home() {
		return "home_admin";
	}
	
	@RequestMapping("/Operations")
	public String operation(Model model) {
		try {
			List<Operation> op=banqueservise.listOpertaion();
			model.addAttribute("Operation",op);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "opération";
	}
	
	@RequestMapping("/comptes")
	public String consulter(Model model) {
		try {
			List<Compte> cp=banqueservise.consulterCompteAll();
			model.addAttribute("Compte",cp);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "compte_admin";	
	}
	
	@RequestMapping("/consultercompte")
	public String consultercompte(String  codecompte,Model model) {
		try {
			Compte c =banqueservise.consulterCompte(codecompte);
			model.addAttribute("Compte",c);
		} catch (Exception e) {
			model.addAttribute("exception", e);
			List<Compte> cp=banqueservise.consulterCompteAll();
			model.addAttribute("Compte",cp );
		}
		return"compte_admin";
	}
	
	/*@RequestMapping("/verser")
	public String verser(Double montan,String codecompte, Model model) {
		try {
			banqueservise.verser(codecompte, montan);
			Compte c =banqueservise.consulterCompte(codecompte);
			model.addAttribute("Compte",c);
		}
		catch (Exception e) {
			model.addAttribute("versemen_exception", e);
			List<Compte> cp=banqueservise.consulterCompteAll();
			model.addAttribute("Compte",cp );
		}
		return "compte_admin";
	}*/
	
	@RequestMapping("/operation/{code}")
	public String operationPage(@PathVariable (value = "code") String code ,Model model) {
		
		model.addAttribute("code",code);
		return "operationClient";
	}
	
	@RequestMapping("/operationcompte")
	public String operationOk(String codecompte1 ,String codecompte2,Double montant,String op ,Model model) {
		
		try {
			if (op.equals("ver")) {
				banqueservise.verser(codecompte1, montant);
			}
			if (op.equals("ret")) {
				banqueservise.retirer(codecompte1, montant);
			}
			if(op.equals("vir")) {
				banqueservise.virement(codecompte1, codecompte2, montant);
			}
		}
		catch (Exception e) {
			List<Operation> operation=banqueservise.listOpertaion();
			model.addAttribute("Operation",operation);
			model.addAttribute("operation_exception", e);
			return "opération";
		}
		List<Operation> operation=banqueservise.listOpertaion();
		model.addAttribute("Operation",operation);
		model.addAttribute("succ","ok");
		return "opération";
	}
	
	@RequestMapping("/consultercompteoperation")
	public String consultercompteoperation(String  codecompte,Model model) {
		try {
			List<Operation> op =banqueservise.consultercompteoperation(codecompte);
			model.addAttribute("Operation",op);
		} catch (Exception e) {
			model.addAttribute("exception_vide", e);
			List<Operation> op=banqueservise.listOpertaion();
			model.addAttribute("Operation",op );
		}
		return"opération";
	}
	
	@RequestMapping("/employes")
	public String Employe(Model model) {
		try {
			List<Employes> em=banqueservise.consulterEmployesAll();
			model.addAttribute("Employes",em);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "employes";
	}
	@RequestMapping("/consulteremploye")
	public String consulteremploye(Model model, Long codeemplye) {
		try {
			Employes e =banqueservise.consulterEmployes(codeemplye);
			model.addAttribute("Employes",e);
		} catch (Exception e) {
			model.addAttribute("exception", e);
			List<Employes> em=banqueservise.consulterEmployesAll();
			model.addAttribute("Employes",em);
		}
		return "employes";
	}
	
	
	@RequestMapping("/groupes")
	public String groupe(Model model) {
		try {
		  List<Groupes> g=banqueservise.consulterGroupesAll();
		  model.addAttribute("Groupes",g);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "groupe";
	}
	
	@RequestMapping("/ajoutercompte")
	public String ajoutercompte(Model model, String type, Double solde , Long client, Long employe) {
		try {
			if(type.equals("CC")) {
				
				Random r = new Random();
				int i = r.nextInt();
				String s = String.valueOf(i);
				type = type.concat(s);
				CompteCC c = new CompteCC();
				c = banqueservise.ajoutercompteCC(type, solde, client, employe);
				
			}
			if (type.equals("EP")) {
				CompteEP c = new CompteEP();
				c = banqueservise.ajoutercompteEP(type, solde, client , employe);
			}
			
		
		} catch (Exception e) {
			model.addAttribute("exception_ajoute", e);
		}
		return "redirect:/comptes";
	}
	
	@RequestMapping("/ajouteremploye")
	public String ajouteremploye(Model model, String nom, String email , Long employesup, Long groupe) {
		try {
			
			Employes e = new Employes();
			e= banqueservise.ajouterEmployes(nom, email, employesup, groupe);
			List<Employes> em=banqueservise.consulterEmployesAll();
			model.addAttribute("Employes",em);
		
		} catch (Exception e) {
			model.addAttribute("exception_ajoute", e);
			List<Employes> em=banqueservise.consulterEmployesAll();
			model.addAttribute("Employes",em);
		}
		return "employes";
	}
	
	@RequestMapping("/ajoutergroupes")
	public String ajoutergroupes(Model model, String nom) {
		try {
			
			Groupes g = banqueservise.ajouterGroupes(nom);
			List<Groupes> gr=banqueservise.consulterGroupesAll();
			model.addAttribute("Groupes",gr);
		
		} catch (Exception e) {
			model.addAttribute("exception_ajoute", e);
			List<Groupes> gr=banqueservise.consulterGroupesAll();
			model.addAttribute("Groupes",gr);
		}
		return "groupe";
	}
	
	@RequestMapping("/consultergroupe")
	public String consultergroupe(Model model, Long codegroupes) {
		try {
			Groupes g =banqueservise.consultergroupes(codegroupes);
			model.addAttribute("Groupes",g);
		} catch (Exception e) {
			model.addAttribute("exception", e);
			List<Groupes> gr=banqueservise.consulterGroupesAll();
			model.addAttribute("Groupes",gr);
		}
		return "groupe";
	}
	
	@RequestMapping("/ajouteremployeGroupe")
	public String ajouteremployeGroupe(Model model,Long codeemlpoye, Long codegroupes ) {
		try {
			
			banqueservise.ajouteremployeGroupe(codeemlpoye, codegroupes);
			List<Groupes> gr=banqueservise.consulterGroupesAll();
			model.addAttribute("Groupes",gr);
		} catch (Exception e) {
			model.addAttribute("exception_ajouteremploye", e);
			List<Groupes> gr=banqueservise.consulterGroupesAll();
			model.addAttribute("Groupes",gr);
		}
		return "groupe";
	}
	
	@RequestMapping("/Client")
	public String consulterCleintAll(Model model) {
		try {
			List<Client> cl=banqueservise.consulterClientAll();
			model.addAttribute("Client",cl);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "client";	
	}
	
	@RequestMapping("/ajouterClient")
	public String ajouterclient(Model model, String nom, String email) {
		try {
			
			Client c = new Client();
			c = banqueservise.ajouterClient(nom, email);
			List<Client> cl=banqueservise.consulterClientAll();
			model.addAttribute("Client",cl);
		
		} catch (Exception e) {
			model.addAttribute("exception_ajoute", e);
			List<Client> cl=banqueservise.consulterClientAll();
			model.addAttribute("Client",cl);
		}
		return "client";
	}
}
