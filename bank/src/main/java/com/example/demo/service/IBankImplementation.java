package com.example.demo.service;

import java.util.Date;
import java.util.*;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.EmployesRepository;
import com.example.demo.dao.GroupesRepository;
import com.example.demo.dao.OperationRepository;
import com.example.demo.model.Client;
import com.example.demo.model.Compte;
import com.example.demo.model.CompteCC;
import com.example.demo.model.CompteEP;
import com.example.demo.model.Employes;
import com.example.demo.model.Groupes;
import com.example.demo.model.Operation;
import com.example.demo.model.Retrait;
import com.example.demo.model.Versement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional
public class IBankImplementation implements Ibank {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private CompteRepository CR;

	@Autowired
	private OperationRepository OR;

	@Autowired
	private EmployesRepository ER;

	@Autowired
	private GroupesRepository GR;
	
	private ClientRepository CLR;

	@Override
	public Compte consulterCompte(String codecompte) {
		
		Optional<Compte> r = CR.findById(codecompte);
		Compte c = r.get();
		if (c == null)
			throw new RuntimeException("Compte introuvable");
		return c;
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void verser(String codecompte, double montant) {

		Compte c = consulterCompte(codecompte);
		Versement v = new Versement(null, new Date(), montant, c, null);
		OR.save(v);
		c.setSolde(c.getSolde() + montant);
		CR.save(c);
	}

	@Override
	public void retirer(String codecompte, double montant) {

		Compte c = consulterCompte(codecompte);
		double Mretirer = 0;
		if (c instanceof CompteCC)
			Mretirer = ((CompteCC) c).getDecouvert();
		if (c.getSolde() + Mretirer < montant)
			throw new RuntimeException("Solde insuffisant");
		Retrait r = new Retrait(null, new Date(), montant, c, null);
		OR.save(r);
		c.setSolde(c.getSolde() - montant);
		CR.save(c);
	}

	@Override
	public void virement(String codecompte, String codecompte2, double montant) {

		if (codecompte.equals(codecompte2) || codecompte.toUpperCase().equals(codecompte2)
				|| codecompte.equals(codecompte2.toUpperCase())) {
			throw new RuntimeException("operation impossible");
		}
		retirer(codecompte, montant);
		verser(codecompte2, montant);

	}

	@Override
	public List<Operation> listOpertaion() {

		return OR.findAll(Sort.by(Sort.Direction.DESC, "dateOperation"));
	}

	@Override
	public List<Compte> consulterCompteAll() {

		return CR.listeComptes();
	}

	@Override
	public List<Operation> consultercompteoperation(String codecompte) {
		return OR.listeOperation(codecompte);
	}

	@Override
	public List<Employes> consulterEmployesAll() {
		
		return ER.findAll();
		
	}

	@Override
	public List<Groupes> consulterGroupesAll() {
		
		return GR.findAll();
		
	}

	@Override
	public CompteCC ajoutercompteCC(String type, Double solde , Long client , Long employe) {
		
		Employes e= ER.getReferenceById(employe);
		Client cl =CLR.getReferenceById(client);
		CompteCC c = new CompteCC(type, new Date(), null, solde, cl,e , null);
		em.persist(c);
		return c;
		
	}
	
	@Override
	public CompteEP ajoutercompteEP(String type, Double solde , Long client ,Long employe) {

	
		CompteEP c = new CompteEP(type, new Date(), null, solde, null, null, null);
		em.persist(c);
		return c;
	}

	@Override
	public Employes ajouterEmployes(String nom, String email, Long employesup, Long groupe) {
		
		Optional<Employes> es = ER.findById(employesup);
		Employes em = es.get();
		if (em == null)
			throw new RuntimeException("employe introuvable");
		Optional<Groupes> g = GR.findById(groupe);
		Groupes gr = g.get();
		if (gr== null)
			throw new RuntimeException("groupe introuvable");
		if (nom==null | nom=="")
			throw new RuntimeException("nom vide");
		Employes e =new Employes(null, nom, email, null, null);
		ER.save(e);
		return e;
		
	}

	@Override
	public Employes consulterEmployes(Long codeEmploye) {
		
		Optional<Employes> e = ER.findById(codeEmploye);
		Employes em = e.get();
		if (em == null)
			throw new RuntimeException("Compte introuvable");
		return em;
		
	}

	@Override
	public Groupes ajouterGroupes(String nom) {
		
		if (nom == null | nom=="")
		    throw new RuntimeException("champs vide");
		Groupes g =new Groupes(null, nom, null);
		GR.save(g);
		return g;
	}

	@Override
	public Groupes consultergroupes(Long codeGroupe) {
		
		Optional<Groupes> g = GR.findById(codeGroupe);
		Groupes gr = g.get();
		if (gr == null)
			throw new RuntimeException("Groupe introuvable");
		return gr;
	}

	@Override
	public void ajouteremployeGroupe(Long codeemploye, Long codegroupe) {

		Groupes g= GR.getReferenceById(codegroupe);
		Employes e = ER.getReferenceById(codeemploye);
		if(e==null)
			throw new RuntimeException("employe introuvable");
	    java.util.Collection<Employes> em = g.getEmployes();
		em.add(e);
		g.setEmployes(em);
			
	}

	@Override
	public List<Client> consulterClientAll() {
		return CLR.findAll();
	}

	@Override
	public Client consulterClient(Long codeclient) {
		Optional<Client> c = CLR.findById(codeclient);
		 Client cl = c.get();
		if (cl == null)
			throw new RuntimeException("Cleint introuvable");
		return cl;
	}

	@Override
	public Client ajouterClient(String nomClient, String email) {
		
		if (nomClient == null | nomClient=="")
		    throw new RuntimeException("champs vide");
		Client c =new Client(null, nomClient, email, null);
		CLR.save(c);
		
		return c;
	}

	

}
