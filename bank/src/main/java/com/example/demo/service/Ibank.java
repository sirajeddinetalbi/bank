package com.example.demo.service;

import java.util.List;


import java.util.Optional;

import com.example.demo.model.Client;
import com.example.demo.model.Compte;
import com.example.demo.model.CompteCC;
import com.example.demo.model.CompteEP;
import com.example.demo.model.Employes;
import com.example.demo.model.Groupes;
import com.example.demo.model.Operation;

public interface Ibank {
	
	public Compte consulterCompte(String codecompte);
	public List<Compte> consulterCompteAll();
	public void verser(String codecompte,double montant);
	public void retirer(String codecompte, double montant);
	public void virement(String codecompte, String codecompte2 , double monatnt);
	public List<Operation> listOpertaion();
	public List<Operation> consultercompteoperation(String codecompte);
	public List<Employes> consulterEmployesAll();
	public List<Groupes> consulterGroupesAll();
	
	public CompteCC ajoutercompteCC(String type,Double solde, Long client , Long employe);
	public CompteEP ajoutercompteEP(String type,Double solde , Long client, Long employe );
	
	public Employes ajouterEmployes(String nom, String email , Long employesup, Long groupe);
	public Employes consulterEmployes(Long codeEmploye);
	
	public Groupes ajouterGroupes(String nom);
	public Groupes consultergroupes(Long codeGroupe);
	
	public void ajouteremployeGroupe(Long codeemploye, Long codegroupe);
	
	public List<Client> consulterClientAll();
	
	public Client consulterClient(Long codeclient);
	
	public Client ajouterClient(String nomClient,String email);
	
	

}
